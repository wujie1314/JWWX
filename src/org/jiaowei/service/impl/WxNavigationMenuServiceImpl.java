package org.jiaowei.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jiaowei.common.service.impl.CommonServiceImpl;
import org.jiaowei.controllers.ConnWeixinController;
import org.jiaowei.entity.NavigationMenuEntity;
import org.jiaowei.entity.WxStatusTmpTEntity;
import org.jiaowei.service.WxNavigationMenuService;
import org.jiaowei.service.WxStatusTmpService;
import org.jiaowei.util.CommonConstantUtils;
import org.jiaowei.wxutil.WeiXinConst;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.jiaowei.wxutil.WeixinUtils;
import org.jiaowei.wxutil.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxNavigationMenuServiceImpl extends CommonServiceImpl implements WxNavigationMenuService {
	private static Logger logger = Logger.getLogger(ConnWeixinController.class);
    @Autowired
    private WxStatusTmpService wxStatusTmpService;
	
	/**
	 * 首次导航菜单
	 * @param map  Navigation导航
	 * @param response
	 * @param openId
	 */
	public void sendNavigationMenuHintMsg(Map<String, String> map,HttpServletResponse response, String openId){
		String result="请选择服务项：\n<a href=\"http://cq96096.cn/videoImg/list?type=2&openId="+openId+"\">【1】高速路况查询</a>"
				+ "\n<a href=\"http://wxm.pmpsys.cn/wx-073f946c-0273/index.jspx\">【2】长途汽车购票</a>"
				+ "\n<a href=\"http://web.chelaile.net.cn/wwd/index?src=webapp_weixin_chongqing\">【3】公交车查询</a>"
				+ "\n<a href=\"###\">【4】轨道信息</a>"
				+ "\n<a href=\"http://mobile.12306.cn/weixin/wxcore/init\">【5】火车票查询</a>"
				+ "\n<a href=\"http://m.veryzhun.com/flight/?token=5cf2036c3db9fe08a7ee0c9b2077d37d\">【6】航班信息查询</a>"
				+ "\n<a href=\"###\">【7】其他信息查询</a>"
				+ "\n【8】高速公路事故报警"
				+ "\n【0】人工服务";
		
		NavigationMenuEntity menuEntity = new NavigationMenuEntity();
		menuEntity.setOpenId(openId);
		menuEntity.setState(0);
		menuEntity.setMenuKey("0");
		menuEntity.setDate(WeixinUtils.getNowDateTime());
		WeiXinConst.putMenuMap(openId, menuEntity);
		String returnStr = XmlUtil.genTextResponseMsg(map, result);
        WeiXinOperUtil.sendMsgToWX(response, returnStr);
        wxStatusTmpService.saveMsgDatebase(null, result, openId);
	}

	public void sendMsgOneWxHint(Map<String, String> map, HttpServletResponse response, String openId){
		String msgType = map.get("MsgType"), content = map.get("Content");
		NavigationMenuEntity menuEntity = WeiXinConst.navigationMenu.get(openId);
		if(menuEntity == null || !"text".equals(msgType) || "#".equals(content)){
			sendNavigationMenuHintMsg(map, response, openId);//第一次发送导航菜单
		} else {
			String key = menuEntity.getMenuKey();
			if(WeixinUtils.isNumeric(content)){
				key += "-" +  content;
			} else if("*".equals(content)){//返回上一层
				if("0".equals(key)){
					sendNavigationMenuHintMsg(map, response, openId);//第一次发送导航菜单
					return;
				} else {
					key = key.substring(0,key.lastIndexOf("-"));
				}
				
			} else {//是那层就那层
				
			}
			sendMenuWxHint(map, response, openId, key, menuEntity);
		}
    }
	
	private void sendMenuWxHint(Map<String, String> map, HttpServletResponse response, String openId, String menuKey, NavigationMenuEntity menuEntity){
		if("0-0".equals(menuKey)){
			//发送人工服务
			sendCityMenuHintMsg(map, response, openId, menuEntity);
		} else if("0-1".equals(menuKey)){
			String returnStr = XmlUtil.gen1ArticlesResponseMsg(map, "路况与截图", "获取路况与截图信息，请点击", "http://cq96096.cn/videoImg/list?type=2&openId="+openId);
            WeiXinOperUtil.sendMsgToWX(response, returnStr);
		} else if("0-2".equals(menuKey)){
			String returnStr = XmlUtil.gen1ArticlesResponseMsg(map, "长途汽车订票或查询", "长途汽车订票或查询，请点击", "http://wxm.pmpsys.cn/wx-073f946c-0273/index.jspx");
            WeiXinOperUtil.sendMsgToWX(response, returnStr);
		} else if("0-3".equals(menuKey)){
			String returnStr = XmlUtil.gen1ArticlesResponseMsg(map, "公交车查询", "公交车查询，请点击", "http://web.chelaile.net.cn/wwd/index?src=webapp_weixin_chongqing");
			WeiXinOperUtil.sendMsgToWX(response, returnStr);
		} else if("0-4".equals(menuKey)){
//			String returnStr = XmlUtil.gen1ArticlesResponseMsg(map, "轨道信息", "轨道信息，请点击", "http://mobile.12306.cn/weixin/wxcore/init");
			String returnStr = XmlUtil.genTextResponseMsg(map, "正在建设中...");
			WeiXinOperUtil.sendMsgToWX(response, returnStr);
		} else if("0-5".equals(menuKey)){
			String returnStr = XmlUtil.gen1ArticlesResponseMsg(map, "火车票查询", "火车票查询，请点击", "http://mobile.12306.cn/weixin/wxcore/init");
			WeiXinOperUtil.sendMsgToWX(response, returnStr);
		} else if("0-6".equals(menuKey)){
			String returnStr = XmlUtil.gen1ArticlesResponseMsg(map, "航班信息查询", "航班信息查询，请点击", "http://m.veryzhun.com/flight/?token=5cf2036c3db9fe08a7ee0c9b2077d37d");
            WeiXinOperUtil.sendMsgToWX(response, returnStr);
		} else if("0-7".equals(menuKey)){
			String returnStr = XmlUtil.genTextResponseMsg(map, "正在建设中...");
            WeiXinOperUtil.sendMsgToWX(response, returnStr);
		} else if("0-8".equals(menuKey)){
//			String returnStr = XmlUtil.genTextResponseMsg(map, "正在建设中...");
//            WeiXinOperUtil.sendMsgToWX(response, returnStr);
            //进入人工
			manualService(response, map, openId);
		} else if("0-0-1".equals(menuKey)){
			sendGsMenuHintMsg(map, response, openId, menuEntity);
		} else if("0-0-2".equals(menuKey)){
			sendCzcMenuHintMsg(map, response, openId, menuEntity);
		} else if("0-0-3".equals(menuKey)){//公交车
			manualService(response, map, openId);
		} else if("0-0-4".equals(menuKey)){
			sendCtqcMenuHintMsg(map, response, openId, menuEntity);
		} else if("0-0-5".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-6".equals(menuKey)){
			sendCountyMenuHintMsg(map, response, openId, menuEntity);
		} else if("0-0-0".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-1-1".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-1-2".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-1-3".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-2-1".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-2-2".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-2-3".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-4-1".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-4-2".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-6-1".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-6-2".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-6-3".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-6-4".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-6-5".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-6-6".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-6-7".equals(menuKey)){
			manualService(response, map, openId);
		} else if("0-0-6-8".equals(menuKey)){
			manualService(response, map, openId);
		} else {
			sendNavigationMenuHintMsg(map, response, openId);
		}
	}
	
	/**
	 * 发送人工服务
	 * @param map
	 * @param response
	 * @param openId
	 * @param menuEntity
	 */
//	public void sendLabourMenuHintMsg(Map<String, String> map, HttpServletResponse response, String openId, NavigationMenuEntity menuEntity){
//		//TODO:获取区域  判断如果是：万州、合川、永川、合川、大足、彭水、武隆 则直接进入进入相应的分中心
//		String result="请选择服务项：\n【1】区县分中心"
//				+ "\n【2】主城服务中心"
//				+ "\n【*】返回上层菜单"
//				+ "\n【#】返回首层菜单";
//		menuEntity.setState(1);
//		menuEntity.setMenuKey("0-0");
//		menuEntity.setDate(WeixinUtils.getNowDateTime());
//		String returnStr = XmlUtil.genTextResponseMsg(map, result);
//        WeiXinOperUtil.sendMsgToWX(response, returnStr);
//        wxStatusTmpService.saveMsgDatebase(null, result, openId);
//	}
	/**
	 * 区县菜单
	 * @param map
	 * @param response
	 * @param openId
	 * @param menuEntity
	 */
	public void sendCountyMenuHintMsg(Map<String, String> map, HttpServletResponse response, String openId, NavigationMenuEntity menuEntity){
		String result="请选择服务项：\n【1】万州分中心"
				+ "\n【2】合川分中心"
				+ "\n【3】永川分中心"
				+ "\n【4】大足分中心"
				+ "\n【5】武隆分中心"
				+ "\n【6】忠县分中心"
				+ "\n【7】彭水分中心"
				+ "\n【8】其他区县分中心"
				+ "\n【*】返回上层菜单"
				+ "\n【#】返回首层菜单";
		menuEntity.setState(1);
		menuEntity.setMenuKey("0-0-6");
		menuEntity.setDate(WeixinUtils.getNowDateTime());
		String returnStr = XmlUtil.genTextResponseMsg(map, result);
        WeiXinOperUtil.sendMsgToWX(response, returnStr);
        wxStatusTmpService.saveMsgDatebase(null, result, openId);
	}
	/**
	 * 发送主城区服务
	 * @param map
	 * @param response
	 * @param openId
	 * @param menuEntity
	 */
	public void sendCityMenuHintMsg(Map<String, String> map, HttpServletResponse response, String openId, NavigationMenuEntity menuEntity){
		String result="请选择服务项：\n【1】高速公路"
				+ "\n【2】出租车"
				+ "\n【3】公交车"
				+ "\n【4】长途汽车"
				+ "\n【5】轨道"
				+ "\n【6】非主城9区服务"
				+ "\n【0】其他"
				+ "\n【*】返回上层菜单"
				+ "\n【#】返回首层菜单";
		menuEntity.setState(1);
		menuEntity.setMenuKey("0-0");
		menuEntity.setDate(WeixinUtils.getNowDateTime());
		String returnStr = XmlUtil.genTextResponseMsg(map, result);
		WeiXinOperUtil.sendMsgToWX(response, returnStr);
		wxStatusTmpService.saveMsgDatebase(null, result, openId);
	}
	/**
	 * 发送高速公路服务
	 * @param map
	 * @param response
	 * @param openId
	 * @param menuEntity
	 */
	public void sendGsMenuHintMsg(Map<String, String> map, HttpServletResponse response, String openId, NavigationMenuEntity menuEntity){
		String result="请选择服务项：\n【1】高速公路事故报警"
				+ "\n【2】咨询及建议"
				+ "\n【3】ETC服务"
				+ "\n【*】返回上层菜单"
				+ "\n【#】返回首层菜单";
		menuEntity.setState(1);
		menuEntity.setMenuKey("0-0-1");
		menuEntity.setDate(WeixinUtils.getNowDateTime());
		String returnStr = XmlUtil.genTextResponseMsg(map, result);
		WeiXinOperUtil.sendMsgToWX(response, returnStr);
		wxStatusTmpService.saveMsgDatebase(null, result, openId);
	}
	/**
	 * 发送出租车服务
	 * @param map
	 * @param response
	 * @param openId
	 * @param menuEntity
	 */
	public void sendCzcMenuHintMsg(Map<String, String> map, HttpServletResponse response, String openId, NavigationMenuEntity menuEntity){
		String result="请选择服务项：\n【1】出租车投诉"
				+ "\n【2】失物招领"
				+ "\n【3】出租车电招"
				+ "\n【*】返回上层菜单"
				+ "\n【#】返回首层菜单";
		menuEntity.setState(1);
		menuEntity.setMenuKey("0-0-3");
		menuEntity.setDate(WeixinUtils.getNowDateTime());
		String returnStr = XmlUtil.genTextResponseMsg(map, result);
		WeiXinOperUtil.sendMsgToWX(response, returnStr);
		wxStatusTmpService.saveMsgDatebase(null, result, openId);
	}
	
	/**
	 * 发送长途汽车服务
	 * @param map
	 * @param response
	 * @param openId
	 * @param menuEntity
	 */
	public void sendCtqcMenuHintMsg(Map<String, String> map, HttpServletResponse response, String openId, NavigationMenuEntity menuEntity){
		String result="请选择服务项：\n【1】联网售票投诉及咨询"
				+ "\n【2】其他"
				+ "\n【*】返回上层菜单"
				+ "\n【#】返回首层菜单";
		menuEntity.setState(1);
		menuEntity.setMenuKey("0-0-4");
		menuEntity.setDate(WeixinUtils.getNowDateTime());
		String returnStr = XmlUtil.genTextResponseMsg(map, result);
		WeiXinOperUtil.sendMsgToWX(response, returnStr);
		wxStatusTmpService.saveMsgDatebase(null, result, openId);
	}
	/**
	 * 发送其它服务
	 * @param map
	 * @param response
	 * @param openId
	 * @param menuEntity
	 */
	public void sendQtfwMenuHintMsg(Map<String, String> map, HttpServletResponse response, String openId, NavigationMenuEntity menuEntity){
		String result="请选择服务项：\n【1】港航局"
				+ "\n【2】公路局"
				+ "\n【3】运管所"
				+ "\n【4】其他部门"
				+ "\n【*】返回上层菜单"
				+ "\n【#】返回首层菜单";
		menuEntity.setState(1);
		menuEntity.setMenuKey("0-0-2-6");
		menuEntity.setDate(WeixinUtils.getNowDateTime());
		String returnStr = XmlUtil.genTextResponseMsg(map, result);
		WeiXinOperUtil.sendMsgToWX(response, returnStr);
		wxStatusTmpService.saveMsgDatebase(null, result, openId);
	}
	
	private void manualService(HttpServletResponse response,
			Map<String, String> map, String openId) {
		List<WxStatusTmpTEntity> list = checkUserInMemery(openId);
		if(list == null || list.size() == 0){
			String returnStr = XmlUtil.genTextResponseMsg(map, CommonConstantUtils.input0SysHint());
			WeiXinOperUtil.sendMsgToWX(response, returnStr);
			WxStatusTmpTEntity entity = new WxStatusTmpTEntity();
			entity.setWxOpenid(openId);
			entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
			entity.setServiceStatus(0);
			entity.setLastChatTime(System.currentTimeMillis() / 1000);
			entity.setIntoWaitingMapTime(System.currentTimeMillis() / 1000);
//			WeiXinConst.waitingMap.put(openId, entity);
			wxStatusTmpService.saveMsgDatebase(null, CommonConstantUtils.input0SysHint(), openId);
		} else {
			String returnStr = XmlUtil.genTextResponseMsg(map, "座席忙，请稍等...");
            WeiXinOperUtil.sendMsgToWX(response, returnStr);
            wxStatusTmpService.saveMsgDatebase(null, "座席忙，请稍等...", openId);
		}
	}
	
	 /**
     * 检查用户是否已经在服务状态
     *
     * @param openId
     * @return
     */
    public static List<WxStatusTmpTEntity> checkUserInMemery(String openId) {

        List<WxStatusTmpTEntity> list = new ArrayList<WxStatusTmpTEntity>();
//        if (null != WeiXinConst.waitingMap.get(openId)) {
//            list.add(WeiXinConst.waitingMap.get(openId));
//        }
//        if (null != WeiXinConst.servicingMap.get(openId)) {
//            list.add(WeiXinConst.servicingMap.get(openId));
//        }
//        if (null != WeiXinConst.deletedMap.get(openId)) {
//            list.add(WeiXinConst.deletedMap.get(openId));
//        }
        if (list.size() > 1) {
            logger.info("排队的map数据发生了异常.size>1");
            return new ArrayList<WxStatusTmpTEntity>();
        }
        return list;
    }
}
