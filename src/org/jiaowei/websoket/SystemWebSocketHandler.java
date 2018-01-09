/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jiaowei.websoket;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jiaowei.entity.CustomerServiceHisEntity;
import org.jiaowei.entity.MsgFromCustomerServiceEntity;
import org.jiaowei.entity.WxStatusTmpTEntity;
import org.jiaowei.mybatis.service.UserVoService;
import org.jiaowei.mybatis.vo.UserVo;
import org.jiaowei.service.MsgFromCustomerServiceService;
import org.jiaowei.service.SysUserService;
import org.jiaowei.service.WxStatusTmpService;
import org.jiaowei.util.StringUtil;
import org.jiaowei.wxutil.NavMenuInitUtils;
import org.jiaowei.wxutil.WeiXinConst;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import activiti.PeopleServic;
import activiti.SeatW;
import activiti.totalP;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author dayu
 */
@Component
public class SystemWebSocketHandler implements WebSocketHandler {

    private Logger logger = Logger.getLogger(SystemWebSocketHandler.class);

    /**
     * 座席用户名
     */
    private String userName;

    private String userId;

    private Integer csId;

    private String wxOpenId;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private UserVoService userVoService;
    @Autowired
    private WxStatusTmpService wxStatusTmpService;
    @Autowired
    private MsgFromCustomerServiceService msgFromCustomerService;

    /**
     * 座席登录时调用，一个座席可以建立多个socket,一个微信用户只能建立一个socket
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	try {
    		// 标记
    		System.out.println("-----------11111111.....");
            //获取用户名
            String currentTime = System.currentTimeMillis()+"";
            String queryString = session.getUri().getQuery();
            if (!queryString.contains("user") || !queryString.contains("openId")
                    || !queryString.contains("=") || !queryString.contains("&")) {
                logger.info(String.format("参数有误,方法：%s-%s", "afterConnectionEstablished", queryString));
            	System.out.println("==========1234.....");
                session.sendMessage(new TextMessage("传递的参数不正确."));
                session.close();
                return;
            }  
            System.out.println("==========12345.....");
            Map<String, String> pamars = parseQueryString(queryString);
            System.out.println("==========123456....."+pamars.size());
            if (4 == pamars.size()) {
                System.out.println("==========123457.....-----------"+pamars.get("user"));
                //参数传递个数正确
                Map<String,Object> params = new HashMap<String, Object>();
                params.put("userId", pamars.get("user"));
                UserVo userVo = userVoService.query(params);
//                List<SysUserEntity> list = sysUserService.findByProperty(SysUserEntity.class, "userId", pamars.get("user"));
//                SysUserEntity sysUserEntity = null;
//                if (null != list && list.size() > 0) {
//                    sysUserEntity = list.get(0);
//                    this.userName = sysUserEntity.getUserName();
//                    this.csId = sysUserEntity.getId();
//                    this.userId = sysUserEntity.getUserId();
//                }
                if(userVo != null){
                	this.userName = userVo.getUserName();
                    this.csId = userVo.getId();
                    this.userId = String.valueOf(userVo.getUserId());
                }
                this.wxOpenId = pamars.get("openId");
                //检查当前用户是否在正在服务的队列中
//                WxStatusTmpTEntity entity =  WeiXinConst.servicingMap.get(wxOpenId);
                WxStatusTmpTEntity entity =  NavMenuInitUtils.getInstance().getServiceEntity(wxOpenId);
                if(null != entity){
                    System.out.println("3333==========12345.....");
                    WeiXinConst.webSocketSessionMap.put(session.getId(), session);
                    entity.setMsgServiceId(pamars.get("serviceId"));
                    if("FROMWX".equals(pamars.get("type"))){//微信请求人工服务
//                        String jsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
//                                this.wxOpenId, String.format("您好，我是%s号座席，请问有什么可以帮您？", sysUserEntity.getUserId()));
                    	/*SeatW seatW=new SeatW();
                		seatW.completetask();
                		totalP totalPs=new totalP();
                		totalPs.completetaskU1();*/
                        String jsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
                        		this.wxOpenId, String.format("您好，我是%s号座席，请问有什么可以帮您？", this.userId));
                       /* PeopleServic peopleServic=new PeopleServic();
                   	 	peopleServic.startProcessInstance();*/
                        String publicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(this.wxOpenId);
//                        Integer deptOD =  NavMenuInitUtils.getInstance().userDeptMap.get(this.wxOpenId);// 加入的
                    	if(!this.wxOpenId.subSequence(0, 3).equals("app")){
							WeiXinOperUtil.sendMsgToWx(WeiXinOperUtil.getAccessToken(publicID), jsonContent);
						}
                        //发送座席
                       //通知前端已经关閉
                        try {
                        	String msg = "{\"Content\":\"" + "您好，我是"+userId+"号座席，请问有什么可以帮您？" + "\",\"CreateTime\":\"" + System.currentTimeMillis() / 1000 + "\",\"ToUserName\":\"gh_45216f693385\",\"FromUserName\":\"" + wxOpenId+ "\",\"MsgType\":\"autotext\",\"MsgId\":\"12345678900\"}";
                            session.sendMessage(new TextMessage(msg));
                           /* peopleServic.completetask("P1");*/
                        } catch (IOException e) {
                        	e.printStackTrace();
                            logger.info("发送系统消息超时异常.", e);
                        }
                        wxStatusTmpService.saveMsgDatebase(entity, "您好，我是"+userId+"号座席，请问有什么可以帮您？", wxOpenId);
                        entity.setSendType(0);//计算微信用户时间
                    }else if("FROMCS".equals(pamars.get("type"))){//坐席主动拉人
                    	
                    	entity.setIsInitiative(1);//设置座席主动拉人
                    	entity.setSendType(1);//计算座席时间
                    	entity.setSessionId(session.getId());
                    	NavMenuInitUtils.getInstance().putServiceMap(wxOpenId, entity);
                    }else if("FROMCSYQ".equals(pamars.get("type"))){//坐席主动拉人
                    	entity.setIsInitiative(1);//设置座席主动拉人
                    	entity.setSendType(1);//计算座席时间
                    	if(WeiXinConst.servicingYqMap.get(wxOpenId)!=null){
                    		Map<String,String> map=new HashMap<String,String>();
                    		map=WeiXinConst.servicingYqMap.get(wxOpenId);
                    		map.put(csId+"",session.getId());
                    	}
                    }else if("FROMMESSAGE".equals(pamars.get("type"))){// 留言
                    	System.out.println("---------------------------------------------话务前台接入，开始回答留言");
                        //发送给用户
                        String jsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
                        		this.wxOpenId, String.format("您好，我是"+userId+"号座席，帮你解答留言？", this.userId));
                        String publicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(this.wxOpenId);
//                        Integer deptOD =  NavMenuInitUtils.getInstance().userDeptMap.get(this.wxOpenId);// 加入的
                    	if(!this.wxOpenId.subSequence(0, 3).equals("app")){
							WeiXinOperUtil.sendMsgToWx(WeiXinOperUtil.getAccessToken(publicID), jsonContent);
						}
                    	 //发送座席
                         try {
                         	String msg = "{\"Content\":\"" + "您好，我是"+userId+"号座席，帮你解答留言？" + "\",\"CreateTime\":\"" + System.currentTimeMillis() / 1000 + "\",\"ToUserName\":\"gh_45216f693385\",\"FromUserName\":\"" + wxOpenId+ "\",\"MsgType\":\"autotext\",\"MsgId\":\"12345678900\"}";
                         	session.sendMessage(new TextMessage(msg));
                         } catch (IOException e) {
                         	e.printStackTrace();
                             logger.info("发送系统消息超时异常.", e);
                         }
                         entity.setSendType(0);//计算微信用户时间
                    }
                    // 标记
                    System.out.println("444444==========12345.....");
                    if(1 ==entity.getServiceStatus()){
                        entity.setServiceStatus(2);
                        entity.setLastCSTime(System.currentTimeMillis()/1000);
                        entity.setChatOvertime(System.currentTimeMillis()/1000);
                        entity.setServiceHintNum(0);
                        entity.setMyTimestamp(currentTime);
                        entity.setSessionId(session.getId());
                    }
                }
                CustomerServiceHisEntity serviceHisEntity = new CustomerServiceHisEntity();
                serviceHisEntity.setStartTime(new Timestamp(System.currentTimeMillis()));
                serviceHisEntity.setWxOpenid(this.wxOpenId);
                serviceHisEntity.setServiceStatus(1);
                serviceHisEntity.setCsName(this.userName);
                serviceHisEntity.setCsId(this.csId);
//                  serviceHisEntity.setWxComment("5");
                serviceHisEntity.setSessionId(currentTime);
                wxStatusTmpService.save(serviceHisEntity);
            }
            System.out.println("2222222222==========12345.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


    //前端传来的消息格式请参看文档
    @Override
    public void handleMessage(WebSocketSession wss, WebSocketMessage<?> wsm) throws Exception {
        String message = wsm.getPayload().toString();
        JSONObject jsonObject = JSON.parseObject(message);
        String type = jsonObject.get("msgtype").toString();
        String toUser = jsonObject.get("touser").toString();
        Integer id = Integer.parseInt(jsonObject.get("id").toString());
        this.wxOpenId = toUser;
//        WxStatusTmpTEntity tmpTEntity=  WeiXinConst.servicingMap.get(wxOpenId);
        WxStatusTmpTEntity tmpTEntity=  NavMenuInitUtils.getInstance().getServiceEntity(wxOpenId);
        //如果退出了，前端页面将不能再发送信息.
        if(null == tmpTEntity){
            return;
        }
        tmpTEntity.setLastCSTime(System.currentTimeMillis()/1000);
        tmpTEntity.setServiceHintNum(0);
        String jsonContent = null;
        if ("text".equals(type)) {
            //发过来的文本消息
            if (message.contains("text") && message.contains("content")) {
                jsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
                        toUser, JSON.parseObject(jsonObject.get("text").toString()).get("content"));
            }
        } else if ("image".equals(type)) {
            //发过来了图片消息
            if (message.contains("image") && message.contains("media_id")) {
                jsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}",
                        toUser, JSON.parseObject(jsonObject.get("image").toString()).get("media_id"));
            }
        } else if ("voice".equals(type)) {
            //发过来了图片消息
            if (message.contains("voice") && message.contains("media_id")) {
                jsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}",
                        toUser, JSON.parseObject(jsonObject.get("voice").toString()).get("media_id"));
            }
        }
        if (null != jsonContent) {
            String publicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(toUser);
//            Integer deptID = NavMenuInitUtils.getInstance().userDeptMap.get(toUser);
            // 存入数据库
            List<MsgFromCustomerServiceEntity> list=msgFromCustomerService.findByProperty(MsgFromCustomerServiceEntity.class, "id", id);
         	MsgFromCustomerServiceEntity entity=list.get(0);
         	entity.setIsSuccess(1);
         	msgFromCustomerService.saveOrUpdate(entity);
            if(wxOpenId != null && wxOpenId.length() >3 && !wxOpenId.subSequence(0, 3).equals("app")){
            	 String returnStr = WeiXinOperUtil.sendMsgToWx(WeiXinOperUtil.getAccessToken(publicID), jsonContent);// 获取对应accessToken  已改
                 JSONObject json = JSON.parseObject(returnStr);
            	 if(!json.get("errcode").toString().equals("0")){//发送失败
                 	
                 	//通知客服发送失败
                 	String openId = entity.getToUser();
//                 	WxStatusTmpTEntity tmp = ConnWeixinController.getTempMemery(openId);
                 	WxStatusTmpTEntity tmp = NavMenuInitUtils.getInstance().getTmpTEntity(openId);
                 	String sessionId = tmp.getSessionId();
                 	WebSocketSession session = WeiXinConst.webSocketSessionMap.get(sessionId);
                 	String msg = "{\"Content\":\"" + "发送失败" + "\",\"CreateTime\":\"" + System.currentTimeMillis() / 1000 + "\",\"ToUserName\":\"service\",\"FromUserName\":\"" + openId+ "\",\"MsgType\":\"autotext\",\"MsgId\":\""+id+"\"}";
                    session.sendMessage(new TextMessage(msg));
                 }
                 logger.info(String.format("座席发送信息到微信用户返回：%s", returnStr));
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession wss, Throwable thrwbl) throws Exception {
        if (wss.isOpen()) {
            wss.close();
            WeiXinConst.webSocketSessionMap.remove(wss.getId());
        }
        logger.info("websocket handleTransportError : " + thrwbl.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession wss, CloseStatus cs) throws Exception {
        String sessionId = wss.getId();
        if (wss.isOpen()) {
//            wss.close();
        }
        try {
        	WebSocketSession session = WeiXinConst.webSocketSessionMap.get(sessionId);
            if (null != session) {
                WeiXinConst.webSocketSessionMap.remove(sessionId);
                String queryString = session.getUri().getQuery();
                if (!queryString.contains("user") || !queryString.contains("openId")
                        || !queryString.contains("=") || !queryString.contains("&")) {
                    logger.info(String.format("参数有误,方法：%s-%s", "afterConnectionEstablished", queryString));
//                session.sendMessage(new TextMessage("传递的参数不正确."));
                    session.close();
                    return;
                }
            }

            String queryString = wss.getUri().getQuery();
            //获取用户名


            Map<String, String> pamars = parseQueryString(queryString);
            this.wxOpenId = pamars.get("openId");
            //通知微信用户
            String jsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
                    this.wxOpenId, "您已经断开与96096座席的连接.");

            String publicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(this.wxOpenId);
//            Integer deptID = NavMenuInitUtils.getInstance().userDeptMap.get(this.wxOpenId);
            
        	if(!this.wxOpenId.subSequence(0, 3).equals("app")){
				WeiXinOperUtil.sendMsgToWx(WeiXinOperUtil.getAccessToken(publicID), jsonContent);
			}

//            WxStatusTmpTEntity entity = WeiXinConst.servicingMap.get(wxOpenId);
            WxStatusTmpTEntity entity = NavMenuInitUtils.getInstance().getServiceEntity(wxOpenId);
//            entity.setServiceStatus(3);
            wxStatusTmpService.saveMsgDatebase(entity, "您已经断开与96096座席的连接.", wxOpenId);
            NavMenuInitUtils.getInstance().removeWaitMap(wxOpenId);
            NavMenuInitUtils.getInstance().removeServiceMap(wxOpenId);
            NavMenuInitUtils.getInstance().removeRemoveMap(wxOpenId);
            NavMenuInitUtils.getInstance().messageMap.remove(wxOpenId);

           /* PeopleServic peopleServic= new PeopleServic();
			peopleServic.completetask("P2");
			totalP totalPpP=new totalP();
			totalPpP.completetaskP1();*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
        
//        WeiXinConst.waitingMap.remove(wxOpenId);
//        WeiXinConst.servicingMap.remove(wxOpenId);
//        WeiXinConst.servicingYqMap.remove(wxOpenId);
//        if(entity.getIsInitiative() == 1){
        	//主动拉人不发生消息
//        } else {
//        	WeiXinConst.deletedMap.put(wxOpenId,entity);
//        }
    }
    //    oIC-iuCO5dvBux3XQOLvOOMisJyE
//    <xml>
//    <ToUserName><![CDATA[oIC-iuCO5dvBux3XQOLvOOMisJyE]]></ToUserName>
//    <FromUserName><![CDATA[gh_dee6b999ec42]]></FromUserName>
//    <CreateTime>1449472270</CreateTime>
//    <MsgType><![CDATA[text]]></MsgType>
//    <Content><![CDATA[您好！很高兴为你.]]></Content>
//    </xml>
    @Override
    public boolean supportsPartialMessages() {
        System.out.println("supportsPartialMessages ..............");
        return false;
    }

    private Map<String, String> parseQueryString(String queryStr) {
        Map<String, String> map = new HashMap<String, String>();
        if (!StringUtil.isEmpty(queryStr)) {
            if (queryStr.contains("&")) {
                String[] tmp = queryStr.split("&");
                for (String tmp1 : tmp) {
                    if (tmp1.contains("=")) {
                        String[] tmp2 = tmp1.split("=");
                        if (tmp2.length > 1)
                            map.put(tmp2[0], tmp2[1]);
                    }
                }
            } else if (queryStr.contains("=")) {
                String[] tmp2 = queryStr.split("=");
                if (tmp2.length > 1)
                    map.put(tmp2[0], tmp2[1]);
            }
        }
        return map;
    }


}
