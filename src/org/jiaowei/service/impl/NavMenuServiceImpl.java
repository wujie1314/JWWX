package org.jiaowei.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jiaowei.controllers.ConnWeixinController;
import org.jiaowei.entity.CustomerServiceHisEntity;
import org.jiaowei.entity.MsgFromWxEntity;
import org.jiaowei.entity.NavMenuEntity;
import org.jiaowei.entity.NavigationMenuEntity;
import org.jiaowei.entity.WeixinAutoRespondEntity;
import org.jiaowei.entity.WeixinPublicInfoEntity;
import org.jiaowei.entity.WxStatusTmpTEntity;
import org.jiaowei.service.*;
import org.jiaowei.util.CommonConstantUtils;
import org.jiaowei.util.FastJsonUtil;
import org.jiaowei.util.MyBeanUtils;
import org.jiaowei.util.StringUtil;
import org.jiaowei.websoket.AppWebSocketHandler;
import org.jiaowei.wxutil.NavMenuInitUtils;
import org.jiaowei.wxutil.WeiXinConst;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.jiaowei.wxutil.WeixinUtils;
import org.jiaowei.wxutil.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import activiti.PeopleServic;
import activiti.WaitQ;
import activiti.totalP;

@Service
public class NavMenuServiceImpl implements NavMenuService {

	private static Logger logger = Logger.getLogger(ConnWeixinController.class);
	@Autowired
	private WxStatusTmpService wxStatusTmpService;
	@Autowired
	private MsgFromWxService msgFromWxService;
	@Autowired
	private CustomerServiceHisService customerServiceHisService;
	@Autowired
	private WeixinPublicInfoService weixinPublicInfoService;

	@Autowired
	private WeixinAutoRespondService autoRespondService;
	private List<WeixinAutoRespondEntity> mainMenu = null;
	private Integer time = 0;
	private Map<Integer, List<WeixinAutoRespondEntity>> returnMenu = new HashMap<Integer, List<WeixinAutoRespondEntity>>();
	private Map<String, Map<Integer, List<WeixinAutoRespondEntity>>> reMenu = new HashMap<String, Map<Integer, List<WeixinAutoRespondEntity>>>();

	/**
	 * 微信用户第一次发送消息，进入导航菜单
	 */
	@Override
	public void sendMsgOneWxHint(Map<String, String> map,
			HttpServletResponse response, String openId) {
		String msgType = map.get("MsgType"), content = map.get("Content");
		if (StringUtils.isNoneBlank(content)) {
			content = content.toLowerCase();
		}
		NavigationMenuEntity menuEntity = WeiXinConst.navigationMenu
				.get(openId);
		String key = "0", beforeContent = null;
		String dept = NavMenuInitUtils.getInstance().userDeptMap.get(openId)
				.toString();
		if (null != dept) {
			key = dept;
		}
		if (menuEntity == null) {
			menuEntity = new NavigationMenuEntity();
			menuEntity.setMenuKey(key);
			menuEntity.setOpenId(openId);
			menuEntity.setDate(WeixinUtils.getNowDateTime());
			WeiXinConst.navigationMenu.put(openId, menuEntity);
		}

		if (!"text".equals(msgType) || "#".equals(content)) {
			// sendNavigationMenuHintMsg(map, response, openId);//第一次发送导航菜单
		} else {
			key = menuEntity.getMenuKey();
			beforeContent = key;
			// if(WeixinUtils.isNumeric(content)){
			// key += "-" + content;
			// } else if("*".equals(content)){//返回上一层
			// if("0".equals(key)){//第一次发送导航菜单
			// } else {
			// key = key.substring(0,key.lastIndexOf("-"));
			// }
			// }

			if ("*".equals(content)) {// 返回上一层
				if ("0".equals(key)) {// 第一次发送导航菜单
					key = "0";
				} else {
					key = key.substring(0, key.lastIndexOf("-"));
				}
			} else {
				key += "-" + content;
			}
		}
		sendMenuWxHint(map, response, openId, key, menuEntity, beforeContent);
	}

	public void sendMenuWxHint(Map<String, String> map,
			HttpServletResponse response, String openId, String menuKey,
			NavigationMenuEntity menuEntity, String beforeMenuKey) {

		String dept = NavMenuInitUtils.getInstance().userDeptMap.get(openId)
				.toString();
		// System.out.println("dept----------0----------" + dept);
		NavMenuEntity navMenu = NavMenuInitUtils.getInstance().navMenuMap
				.get(menuKey);
		if (navMenu == null) {
			String sendContent = "您输入错误，请重新输入！";
			NavMenuEntity beforeNavMenu = new NavMenuEntity();
			if (StringUtils.isNotBlank(beforeMenuKey)) {
				beforeNavMenu = NavMenuInitUtils.getInstance().navMenuMap
						.get(beforeMenuKey);
				if (beforeNavMenu == null) {
					beforeNavMenu = NavMenuInitUtils.getInstance().navMenuMap
							.get(dept + "-0");
				}
			} else {
				beforeNavMenu = NavMenuInitUtils.getInstance().navMenuMap
						.get(dept + "-0");
			}
			sendContent += "\n"
					+ beforeNavMenu.getNavmMsgDesc().replaceAll(
							"openIdReplaceAll", openId);
			// System.out.println("--->sendContent:"+sendContent);
			String returnStr = XmlUtil.genTextResponseMsg(map, sendContent);
			WeiXinOperUtil.sendMsgToWX(response, returnStr);
			wxStatusTmpService.saveMsgDatebase(null, sendContent, openId);
		} else {
			String title = navMenu.getNavmMsgTitle();
			String desc = navMenu.getNavmMsgDesc();
			String url = navMenu.getNavmMsgUrl();
			int type = navMenu.getNavmType();

			if (0 == type) {// 人工服务
				manualService(response, map, openId, navMenu.getNavmDeptId());
				// 删掉导航菜单
				WeiXinConst.navigationMenu.remove(openId);
			} else if (1 == type) {// 发送普通消息
				String returnStr = XmlUtil.genTextResponseMsg(map,
						desc.replaceAll("openIdReplaceAll", openId));
				WeiXinOperUtil.sendMsgToWX(response, returnStr);
				wxStatusTmpService.saveMsgDatebase(null, returnStr, openId);
				menuEntity.setMenuKey(menuKey);
				menuEntity.setDate(WeixinUtils.getNowDateTime());
			} else if (2 == type) {// 发送链接消息
				String returnStr = XmlUtil.gen1ArticlesResponseMsg(map, title,
						desc, url.replaceAll("openIdReplaceAll", openId));
				WeiXinOperUtil.sendMsgToWX(response, returnStr);
				wxStatusTmpService.saveMsgDatebase(null, returnStr, openId);
				// menuEntity.setMenuKey(menuKey);
				menuEntity.setDate(WeixinUtils.getNowDateTime());
			} else if (3 == type) {// 发送图片链接消息
				String picUrl = "http://www.cq96096.cn/image/meeting/banner_04.jpg";
				String context = "{\"touser\":\"" + openId
						+ "\",\"msgtype\":\"news\",\"news\":{\"articles\": [";
				context += "{\"title\":\"" + title
						+ "\",\"description\":\"\",\"url\":\""
						+ url.replaceAll("openIdReplaceAll", openId)
						+ "\",\"picurl\":\"" + picUrl + "\"}";
				context += "]}}";
				WeiXinOperUtil.sendWxKefuImgsMsg(
						WeiXinOperUtil.getAccessToken(map.get("ToUserName")),
						context);
				wxStatusTmpService.saveMsgDatebase(null, context, openId);
				menuEntity.setDate(WeixinUtils.getNowDateTime());
			}
		}

	}

	public void manualService(HttpServletResponse response,
			Map<String, String> map, String openId, Integer deptId) {
		WxStatusTmpTEntity list = NavMenuInitUtils.getInstance().getTmpTEntity(
				openId);
		if (list == null) {
			String returnStr = XmlUtil.genTextResponseMsg(map,
					CommonConstantUtils.input0SysHint());
			WeiXinOperUtil.sendMsgToWX(response, returnStr);
			long times = System.currentTimeMillis();

			String publicID = map.get("ToUserName").toString();

			WxStatusTmpTEntity entity = new WxStatusTmpTEntity();
			entity.setWxOpenid(openId);
			entity.setCreateTime(new Timestamp(times));
			entity.setServiceStatus(0);
			entity.setLastChatTime(times / 1000);
			entity.setIntoWaitingMapTime(times / 1000);
			entity.setIntoWaitingTime(times);
			// 工作流
			/*
			 * WaitQ waitQ=new WaitQ(); waitQ.startProcessInstance();
			 */
			/*
			 * totalP totalPs=new totalP(); totalPs.startProcessInstance();
			 */

			if (deptId == null) { // 需要改的地方
				// 一个微信用户对应一个部门
				deptId = NavMenuInitUtils.getInstance().userDeptMap.get(openId);
				WeixinPublicInfoEntity publicInfo = weixinPublicInfoService
						.getPublicInfoById(publicID);
				;
				deptId = Integer.parseInt(publicInfo.getDeptId().toString());

			}

			if (!NavMenuInitUtils.getInstance().userPublicIdMap
					.containsKey(openId)) {
				NavMenuInitUtils.getInstance().userPublicIdMap.put(openId,
						publicID);// 试试
			} else if (!NavMenuInitUtils.getInstance().userPublicIdMap.get(
					openId).equals(publicID)) {
				System.err.println("不同公众号里 有重复的openID \n ");
				System.err
						.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.err.println(openId + " :　" + publicID);
				System.err
						.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				System.err.println(openId
						+ " :  "
						+ NavMenuInitUtils.getInstance().userPublicIdMap
								.get(openId));
			}
			// 在进入人工服务是存入用户与部门
			NavMenuInitUtils.getInstance().userDeptMap.put(openId, deptId);
			NavMenuInitUtils.getInstance().putWaitMap(deptId, openId, entity);
			wxStatusTmpService.saveMsgDatebase(null,
					CommonConstantUtils.input0SysHint(), openId);
		} else {
			String returnStr = XmlUtil.genTextResponseMsg(map, "座席忙，请稍等...");
			WeiXinOperUtil.sendMsgToWX(response, returnStr);
			wxStatusTmpService.saveMsgDatebase(null, "座席忙，请稍等...", openId);
		}
	}

	/**
	 * 用户发送789，系统自动回复 defaultContent 不是789发送消息
	 * 
	 * @return
	 */
	@Override
	public boolean sendMsgWxHint(Map<String, String> map, String openId,
			String defaultContent, boolean isOffSocket, WxStatusTmpTEntity tmp) {

		boolean result = true;
		String returnStr = null;
		if ("text".equals(map.get("MsgType"))
				&& ("7".equals(map.get("Content")) || "【7】".equals(map
						.get("Content")))) {
			tmp.setIntoWaitingMapTime(System.currentTimeMillis() / 1000);
			tmp.setWaitingHintNum(1);
			returnStr = "请稍等...";
		} else if ("text".equals(map.get("MsgType"))
				&& ("8".equals(map.get("Content")) || "【8】".equals(map
						.get("Content")))) {
			// 从等待队列删除
			NavMenuInitUtils.getInstance().removeWaitMap(openId);
			NavMenuInitUtils.getInstance().removeServiceMap(openId);
			/*
			 * totalP totalPtP=new totalP(); totalPtP.completetaskU1end();
			 * totalPtP.completetaskS1end();
			 */
			// 加入导航队列
			tmp.setMessage(true);
			tmp.setBeginTimestamp(System.currentTimeMillis());
			tmp.setServiceStatus(4);
			// 加入留言队列
			NavMenuInitUtils.getInstance().messageMap.put(openId, tmp);
			// returnStr = "即将上线，敬请期待。请留言";
			returnStr = "请在两分钟内留言！\n 我们将通知客服人员及时为你解答";
		} else if ("text".equals(map.get("MsgType"))
				&& ("9".equals(map.get("Content")) || "【9】".equals(map
						.get("Content")))) {
			returnStr = "您已退出！";

			NavMenuInitUtils.getInstance().removeWaitMap(openId);
			NavMenuInitUtils.getInstance().removeServiceMap(openId);
			/*
			 * totalP totalPtP=new totalP(); totalPtP.completetaskU1end();
			 * totalPtP.completetaskS1end();
			 */
			// 是否关闭通道
			if (isOffSocket) {
				sendMsgToService(tmp.getSessionId(),
						CommonConstantUtils.sessionFinishSysHint(), openId,
						true);
			}
		} else {
			returnStr = defaultContent;
			result = false;
		}
		
		if(openId != null && openId.length() >3 && !openId.subSequence(0, 3).equals("app")){
			WeiXinOperUtil.sendMsgWx(returnStr, openId);
		}else{
			String userJsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
					openId, String.format(returnStr));
			AppWebSocketHandler.sendMsgToApp(openId, userJsonContent);
		}
		return result;
	}

	/**
	 * 系统用户发送文字消息给坐席
	 */
	@Override
	public void sendMsgToService(String sessionId, String content,
			String wxOpenId, boolean isOffSocket) {
		content = content.replaceAll("\\n", "<br>");
		// System.out.println("----service--->:wxOpenId:"+wxOpenId+",content:"+content);
		// 通知前端已经关閉
		String msg = "{\"Content\":\"" + content + "\",\"CreateTime\":\""
				+ System.currentTimeMillis() / 1000
				+ "\",\"ToUserName\":\"gh_45216f693385\",\"FromUserName\":\""
				+ wxOpenId
				+ "\",\"MsgType\":\"autotext\",\"MsgId\":\"12345678900\"}";
		if (!StringUtil.isEmpty(sessionId)) {

			WebSocketSession session = WeiXinConst.webSocketSessionMap
					.get(sessionId);
			try {
				session.sendMessage(new TextMessage(msg));
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("发送系统消息超时异常.", e);
			}
			if (isOffSocket) {
				try {
					WeiXinConst.webSocketSessionMap.remove(sessionId);
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("关闭socket错误.", e);
				}
			}
		}
	}

	/**
	 * 处理微信用户发送的消息
	 */
	@Override
	public void sendCustomerService(Map<String, String> map,
			HttpServletResponse response, HttpServletRequest request) {
		String openId = map.get("FromUserName");

		// 检查用户是否已经在服务状态 ,用来判断是否用自动回复功能
		WxStatusTmpTEntity tmp = NavMenuInitUtils.getInstance().getTmpTEntity(
				openId);
		// 微信用户发来信息保存数据库
		saveMsgFromWeixin(map, request, tmp);

		if (tmp == null) {
			// 第一次发来信息
			// sendMsgOneWxHint(map, response, openId);
			sendMsgOneWxHintOverride(map, response, openId);
		} else {
			if (tmp.isMessage()) {
				System.out.println("现在处于留言状态 ：" + tmp.isMessage());
				return;
			}
			int status = tmp.getServiceStatus();
			if (0 == status) { // 0: 表示微信用户 将到达，没有拉入座席
				sendMsgWxHint(map, openId, "座席忙，请稍等...", false, tmp);
				wxStatusTmpService.saveMsgDatebase(null, "座席忙，请稍等...", openId);
			} else if (1 == status) { // 1：表示微信用户 已经接入到座席 通道未建立
				sendMsgWxHint(map, openId, "座席忙，请稍等....", false, tmp);
				wxStatusTmpService.saveMsgDatebase(null, "座席忙，请稍等...", openId);
			} else if (2 == status) { // 2：表示微信用户 已经接入到座席 通道建立
				if (tmp.getServiceHintNum() == 2) {
					sendMsgWxHint(map, openId, "座席忙，请稍等...", false, tmp);
				}
				String sessionId = tmp.getSessionId();
				if (!StringUtil.isEmpty(sessionId)) {
					WebSocketSession session = WeiXinConst.webSocketSessionMap
							.get(sessionId);
					sendMsgToCS(request, map, session);
					// 判断是否涉及群聊，并发送信息
					if (WeiXinConst.servicingYqMap.get(openId) != null) {
						Map<String, String> map2 = WeiXinConst.servicingYqMap
								.get(openId);
						Set<String> keys = map2.keySet();
						for (String key : keys) {
							session = WeiXinConst.webSocketSessionMap.get(map2
									.get(key));
							sendMsgToCS(request, map, session);
						}
					}
					tmp.setIsInitiative(0);
				}
				// 记录用户发送消息的时间，如果座席未回复消息，则不计时
				if (tmp.getSendType() == 0) {
					tmp.setUserOffHintNum(0);// 重置用户退出时提示消息的次数
					tmp.setSendType(1);
					tmp.setChatOvertime(System.currentTimeMillis() / 1000);
				}
			} else if (4 == status) { // 留言队列里
				WeiXinOperUtil.sendMsgWx("你当前在留言队列里，是否退出\n【9】退出 ", openId);
				if ("text".equals(map.get("MsgType"))
						&& ("9".equals(map.get("Content")) || "【9】".equals(map
								.get("Content")))) {
					NavMenuInitUtils.getInstance().removeMessageMap(openId);
					WeiXinOperUtil.sendMsgWx("你已退出！", openId);
				}
				wxStatusTmpService.saveMsgDatebase(null, "你当前在留言队列里，是否退出",
						openId);
			} else { // 3：表示已删除，关闭通道

				// 处理評分
				comment(map, tmp);
				// WeiXinConst.deletedMap.remove(openId);
				NavMenuInitUtils.getInstance().removeRemoveMap(openId);

				WeiXinOperUtil.sendMsgWx("感谢您对我们服务的评分", openId);
				wxStatusTmpService.saveMsgDatebase(tmp, "感谢您对我们服务的评分。", openId);
				/*
				 * PeopleServic peopleServic= new PeopleServic();
				 * peopleServic.completetask("P2"); totalP totalPpP=new
				 * totalP(); totalPpP.completetaskP1();
				 */

			}
		}
	}

	/**
	 * 保存微信用户发送的消息道数据库
	 */
	@Override
	public void saveMsgFromWeixin(Map<String, String> map,
			HttpServletRequest request, WxStatusTmpTEntity tmp) {
		// 保存信息到数据库
		MsgFromWxEntity msgFromWxEntity = new MsgFromWxEntity();
		try {
			MyBeanUtils.copyMap2Bean(msgFromWxEntity,
					XmlUtil.changeKeyFirstToLower(map));
			if ("image".equals(msgFromWxEntity.getMsgType())) {
				if (map.get("MediaId").equals("app")) {
					msgFromWxEntity.setImageUrl(WeiXinOperUtil.downloadFromUrl(
							map.get("PicUrl"), request));
				} else {
					msgFromWxEntity.setImageUrl(WeiXinOperUtil
							.downloadImageFromWx(map, request));
				}

			} else if ("voice".equals(msgFromWxEntity.getMsgType())) {
				msgFromWxEntity.setVoiceUrl(WeiXinOperUtil.downloadImageFromWx(
						map, request));
			} else if ("shortvideo".equals(msgFromWxEntity.getMsgType())) {
				msgFromWxEntity.setVideoUrl(WeiXinOperUtil.downloadImageFromWx(
						map, request));
			} else if ("video".equals(msgFromWxEntity.getMsgType())) {
				msgFromWxEntity.setVideoUrl(WeiXinOperUtil.downloadImageFromWx(
						map, request));
			} else if ("location".equals(map.get("MsgType"))) {
				msgFromWxEntity.setLocationX(map.get("Location_X"));
				msgFromWxEntity.setLocationY(map.get("Location_Y"));
				msgFromWxEntity.setLable(map.get("Label"));
				msgFromWxEntity.setContent(map.get("Lable"));
				msgFromWxEntity.setScale(Integer.parseInt(map.get("Scale")
						.toString()));

			} else if ("event".equals(msgFromWxEntity.getMsgType())) {// 其他信息不记录进数据库

				return;
			}
		} catch (Exception e) {
			logger.info("map->bean发生了异常：" + e.getMessage());
		}
		msgFromWxEntity
				.setCreateTime(new Timestamp(System.currentTimeMillis()));
		if (tmp != null) {
			msgFromWxEntity.setWorkServiceId(tmp.getMsgServiceId());
		}
		msgFromWxService.save(msgFromWxEntity);
	}

	/**
	 * 微信用户发送消息给坐席（所有格式，如：图片、视频、音频等）
	 */
	@Override
	public void sendMsgToCS(HttpServletRequest request,
			Map<String, String> map, WebSocketSession session) { // map.get("MediaId")
																	// 改成 map
		try {
			if ("image".equals(map.get("MsgType").toLowerCase().trim())) {
				map.put("MediaUrl",
						WeiXinOperUtil.downloadImageFromWx(map, request));
			} else if ("voice".equals(map.get("MsgType").toLowerCase().trim())) {
				map.put("MediaUrl",
						WeiXinOperUtil.downloadImageFromWx(map, request));
			} else if ("shortvideo".equals(map.get("MsgType").toLowerCase()
					.trim())) {
				map.put("MediaUrl",
						WeiXinOperUtil.downloadImageFromWx(map, request));
			} else if ("video".equals(map.get("MsgType").toLowerCase().trim())) {
				map.put("MediaUrl",
						WeiXinOperUtil.downloadImageFromWx(map, request));
			} else if ("location".equals(map.get("MsgType").toLowerCase()
					.trim())) {
				System.out.println(map);
			}
			if (null != session) {
				session.sendMessage(new TextMessage(FastJsonUtil.toJson(map)));
				logger.error("------------>sendCSMessage: "
						+ FastJsonUtil.toJson(map));
			}
		} catch (Exception e) {
			logger.info("[发生异常了]: " + e.getMessage());
		}
	}

	@Override
	public void comment(Map<String, String> map, WxStatusTmpTEntity entity) {
		// TODO Auto-generated method stub
		if (null == entity || 0 == map.size()
				|| StringUtil.isEmpty(entity.getMyTimestamp())) {
			return;
		}
		List<CustomerServiceHisEntity> li = customerServiceHisService
				.findByProperty(CustomerServiceHisEntity.class, "sessionId",
						entity.getMyTimestamp());
		CustomerServiceHisEntity hisEntity = null;
		if (null != li && li.size() > 0) {
			hisEntity = li.get(0);
			if ("text".equals(map.get("MsgType"))
					&& ("1".equals(map.get("Content")) || "【1】".equals(map
							.get("Content"))) && 3 == entity.getServiceStatus()
					&& null != hisEntity) {
				NavMenuInitUtils.getInstance().removeServiceMap(
						entity.getWxOpenid());
				// WeiXinConst.servicingMap.remove(entity.getWxOpenid());
				hisEntity.setWxComment("1");
				customerServiceHisService.saveOrUpdate(hisEntity);
			} else if ("text".equals(map.get("MsgType"))
					&& ("2".equals(map.get("Content")) || "【2】".equals(map
							.get("Content"))) && 3 == entity.getServiceStatus()
					&& null != hisEntity) {
				NavMenuInitUtils.getInstance().removeServiceMap(
						entity.getWxOpenid());
				hisEntity.setWxComment("2");
				customerServiceHisService.saveOrUpdate(hisEntity);
			}
		}
	}

	/**
	 * 自动回复消息
	 * 
	 * @author wujie
	 * @data 2018年4月20日 下午10:14:31
	 */
	@Override
	public void sendMsgOneWxHintOverride(Map<String, String> map,
			HttpServletResponse response, String openId) {

		List<WeixinAutoRespondEntity> menu = WeiXinConst.navAutoMenu
				.get(openId);

		String publicId = map.get("ToUserName");

		WeixinPublicInfoEntity publicInfo = weixinPublicInfoService
				.getPublicInfoById(publicId);
		;
		Integer deptId = publicInfo.getDeptId();

		String msgType = map.get("MsgType"), content = map.get("Content");
		if (StringUtils.isNoneBlank(content)) {
			content = content.toLowerCase();
		}
		String returnString = "";
		List<WeixinAutoRespondEntity> result = null;
		if(content.trim().equals("观摩会")){
			returnString = XmlUtil.gen1ArticlesResponseMsg1(map,"观摩会", "重庆市交通委员会运行监测与应急调度系统综合演练观摩", "http://cq96096.cn/meeting/verify".replaceAll("openIdReplaceAll", openId));
			WeiXinOperUtil.sendMsgToWX(response, returnString);
			//wxStatusTmpService.saveMsgDatebase(null, returnString, openId);
			WeiXinConst.navAutoMenu.remove(openId); // 清空该次自动回复信息
			return;
		}
		
		// mainMenu=autoRespondService.getRespondMes(content,deptId);
		if ("text".equals(msgType)) { // 正常的文本信息
			// 判断是否第一次自动返回菜单信息
			if (menu == null) {

				result = autoRespondService.getRespondMes(content, deptId);

				if (content.trim().equals("0")) { // 直接进入人工服务
					result = autoRespondService
							.getManualService("ManualService"+"_"+publicId); // 0096096表示人工服务
				}
				if (result != null && result.size() != 0) {
					if(result.size()==1){
						manualService(response, map, openId,
								null);

						// 删掉导航菜单
						WeiXinConst.navAutoMenu.remove(openId);
						return;
						
					}else{
					returnString += "请选择以下服务项： \n";
					for (int i = 0; i < result.size(); i++) {
						returnString += "【" + (i + 1) + "】"
								+ result.get(i).getContent() + "\n";
					}
					returnString += "【#】返回上层菜单";
					}
					if (result.get(0).getJuniorID() == null
							|| !result.get(0).getJuniorID().equals("ManualService"+"_"+publicId)){
					returnString += "【0】人工服务 \n";
					}
					// returnMenu.put(openId, result);
				}

			} else if (Character.isDigit(content.charAt(0))) {// 已有菜单信息
				int n = Integer.parseInt(String.valueOf(content.charAt(0)));// 判断内容是否按菜单里来

				if (n == 0) {
					// 进入人工服务
					System.out.println("从自动回复进入人工服务-----------------");
					returnString += "请选择以下服务项进入人工服务： \n";
					result = autoRespondService
							.getManualService("ManualService"+"_"+publicId); // 0096096表示人工服务
					if(result.size()==1){
						manualService(response, map, openId,
								null);

						// 删掉导航菜单
						WeiXinConst.navAutoMenu.remove(openId);
						return;
						
					}else{
					/*if (menu.get(4).getDeptID() != null
							&& menu.get(0).equals(0)) {
						// 其他服务进交委热线总中心人工服务
						manualService(response, map, openId, menu.get(0)
								.getDeptID());
					}*/
					// 删掉导航菜单
					WeiXinConst.navAutoMenu.remove(openId);
					}
				} else if (n >= 1 && n <= menu.size()) {
					// 在菜单内
					WeixinAutoRespondEntity autoMenu = menu.get(n - 1);

					// 获取下级菜单信息,如果有的话
					if (autoMenu.getJuniorID() != null
							&& !autoMenu.getJuniorID().isEmpty()) {

						// 如果是人工服务直接不获取菜单自己进入人工服务了
						if (autoMenu.getJuniorID().equals("ManualService"+"_"+publicId)) {
							manualService(response, map, openId,
									autoMenu.getDeptID());

							// 删掉导航菜单
							WeiXinConst.navAutoMenu.remove(openId);
							return;
						}
						result = autoRespondService.getJuniorMenu(autoMenu
								.getJuniorID());
						// returnString += "请输入序列号获取服务 \n";

					}

					else { // 已是最下层信息，返回信息，或者返回链接，目前只考虑url 链接
						result = new ArrayList<WeixinAutoRespondEntity>();
						result.add(autoMenu);

					}
				} else {
					// 不在菜单内
					// 继续返回这级菜单,
					result = menu;
					// 加句提示信息
					// returnString += "请输入序列号获取服务 \n";
				}
				returnString += "请输入序列号获取服务 \n";
				for (int i = 0; i < result.size(); i++) {
					returnString += "【" + (i + 1) + "】"
							+ result.get(i).getContent() + "\n";
				}

				if (result.get(0).getJuniorID() == null
						|| !result.get(0).getJuniorID().equals("ManualService"+"_"+publicId)) { // 如果是人工服务，就不加人工服务
					returnString += "【0】人工服务 \n";
				}
				returnString += "【#】返回上层菜单";
			} else if ("#".equals(content)) {// 是否返回上一级

				result = reMenu.get(openId).get(time - 2);
				if(result==null){
					result = autoRespondService.getRespondMes("", deptId);
				}
				returnString += "请输入序列号获取服务 \n";
				for (int i = 0; i < result.size(); i++) {
					returnString += "【" + (i + 1) + "】"
							+ result.get(i).getContent() + "\n";
				}
				if (result.get(0).getJuniorID() == null
						|| !result.get(0).getJuniorID().equals("ManualService"+"_"+publicId)){
				returnString += "【0】人工服务 \n";
				}
				if (result.size() != reMenu.get(openId).get(0).size())
					returnString += "【#】返回上层菜单";
				time -= 2;

			} else {
				// 不在菜单内
				// 继续返回这级菜单,
				result = menu;
				// 加句提示信息
				returnString += "请输入序列号获取服务 \n";
				for (int i = 0; i < result.size(); i++) {
					returnString += "【" + (i + 1) + "】"
							+ result.get(i).getContent() + "\n";
				}
				if (result.get(0).getJuniorID() == null
						|| !result.get(0).getJuniorID().equals("ManualService"+"_"+publicId)){
				returnString += "【0】人工服务 \n";
				}
				returnString += "【#】返回上层菜单";
				// returnMenu.put(openId, result);
			}
		}
		if (result == null || result.size() == 0) {
			returnString += "未找到对应指令\n-----" + content
					+ "-----\n请重新输入相应服务选项: \n";
			result = autoRespondService.getRespondMes("", deptId);
			for (int i = 0; i < result.size(); i++) {
				returnString += "【" + (i + 1) + "】"
						+ result.get(i).getContent() + "\n";
			}
			returnString += "【0】人工服务 \n";
			// returnMenu.put(openId, result);
		}
		// 存入对应的自动回复信息状态
		WeiXinConst.navAutoMenu.put(openId, result);

		// 判断是否是最后一级，是返回一个链接图文信息
		if (result.size() == 1 && result.get(0).getUrl() != null) {
			// 是的直接覆盖前面写的String;
			if(!openId.subSequence(0, 3).equals("app")){
				returnString = XmlUtil.gen1ArticlesResponseMsg(map, result.get(0)
						.getContent(), result.get(0).getContent(), result.get(0)
						.getUrl().replaceAll("openIdReplaceAll", openId));
				WeiXinOperUtil.sendMsgToWX(response, returnString);
				wxStatusTmpService.saveMsgDatebase(null, returnString, openId);
			}else{
				returnString = String.format("{\"touser\":\"%s\",\"msgtype\":\"url\",\"text\":{\"content\":\"%s\"},\"url\":\"%s\"}",
						openId, result.get(0).getContent(),result.get(0)
						.getUrl().replaceAll("openIdReplaceAll", openId));
				AppWebSocketHandler.sendMsgToApp(openId, returnString);
			}
			WeiXinConst.navAutoMenu.remove(openId); // 清空该次自动回复信息
			return;
		}
		String returnStr = XmlUtil.genTextResponseMsg(map, returnString);
		WeiXinOperUtil.sendMsgToWX(response, returnStr);
		wxStatusTmpService.saveMsgDatebase(null, returnString, openId);
		returnMenu.put(time, result);
		time++;
		reMenu.put(openId, returnMenu);
	}

}
