package org.jiaowei.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.type.IntegerType;
import org.jiaowei.entity.SysUserEntity;
import org.jiaowei.entity.WxStatusTmpTEntity;
import org.jiaowei.mybatis.service.NewRoadSubscribeService;
import org.jiaowei.mybatis.service.RoadHtljService;
import org.jiaowei.mybatis.service.RoadSendMessageService;
import org.jiaowei.mybatis.vo.RoadHtljVo;
import org.jiaowei.mybatis.vo.RoadSendMessageVo;
import org.jiaowei.mybatis.vo.RoadSubscribeVo;
import org.jiaowei.service.MsgFromWxService;
import org.jiaowei.service.VideoImgService;
import org.jiaowei.service.WxStatusTmpService;
import org.jiaowei.util.CommonConstantUtils;
import org.jiaowei.util.StringUtil;
import org.jiaowei.wxutil.NavMenuInitUtils;
import org.jiaowei.wxutil.WeiXinConst;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.jiaowei.wxutil.WeixinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import test.java.junit.TestActiviti;
import activiti.PeopleServic;
import activiti.SeatW;
import activiti.WaitQ;
import activiti.helloword;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by alex on 15-12-28.
 */
@Component
public class AutoSchedule {


    private static Logger logger = Logger.getLogger(AutoSchedule.class);
    @Autowired
    private WxStatusTmpService wxStatusTmpService;
    @Autowired
    private MsgFromWxService msgFromWxService;
    @Autowired
    private NewRoadSubscribeService roadSubscribeService;
    @Autowired
    private RoadHtljService roadHtljService;
    @Autowired
    private RoadSendMessageService roadSendMessageService;
    
    @Autowired
    private VideoImgService videoImgService;
    
    @Scheduled(cron = "0/20 * *  * * ? ")   //每20秒执行一次
    public void autoAllotTask() {
    	long one = System.currentTimeMillis();
//    	logger.error("--------------->autoAllotTask start:"+one);
    	ConcurrentMap<Integer, ConcurrentMap<String, WxStatusTmpTEntity>> waitMap = NavMenuInitUtils.getInstance().waitMap;
//    	System.out.println("--->"+waitMap.size());
    	for (Integer deptId : waitMap.keySet()) {
			if(deptId != null){
				List<WxStatusTmpTEntity> waitList = NavMenuInitUtils.getInstance().getWaitTempEntityOrder(deptId);
				System.out.println("----waitList--->"+waitList.size());
				List<SysUserEntity> userList = NavMenuInitUtils.getInstance().getSysUserOrder(deptId);
				System.out.println("----userList--->"+userList.size());
				for (WxStatusTmpTEntity temp : waitList) {
					boolean isAllotSuccess = NavMenuInitUtils.getInstance().weixinAllotCs(temp, userList);
					System.out.println("----isAllotSuccess----->"+isAllotSuccess);
					if(isAllotSuccess){
				        //发送用户提示消息
				        String userJsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
				        		temp.getWxOpenid(), String.format(CommonConstantUtils.allotSysHint()));
						System.err.println(deptId);
						System.out.println("----userJsonContent----->"+userJsonContent);


						String publicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(temp.getWxOpenid()); //通过微信openid获取对应的公众号
						//发送給用户
						// 这里有点问题 获取不到对应的公众号accessToken
						WeiXinOperUtil.sendMsgToWx(WeiXinOperUtil.getAccessToken(publicID), userJsonContent);
						wxStatusTmpService.saveMsgDatebase(temp, CommonConstantUtils.allotSysHint(),  temp.getWxOpenid());
					}
				}
			}
		}
//    	WaitQ waitQ=new WaitQ();
//    	waitQ.startProcessInstance();
//    	TestActiviti textActiviti=new TestActiviti();
//  	textActiviti.createTable();
//       helloword helloword=new helloword();
//       helloword.deploymentProcessDefinition();
      /* WaitQ waitQ=new WaitQ();
       waitQ.deploymentProcessDefinition();*/
    /*  SeatW seatW=new SeatW();
      seatW.deploymentProcessDefinition();
      PeopleServic peopleServic=new PeopleServic();
      peopleServic.deploymentProcessDefinition();*/
    	
    	
//    	logger.error("--------------->autoAllotTask end:"+(System.currentTimeMillis()-one));
    }
    
    @Scheduled(cron = "0/30 * *  * * ? ")   //每30秒执行一次
    public void autoTask() {
    	long one = System.currentTimeMillis();
//    	logger.error("--------------->autoTask start:"+one);
    	checkWaitingMap();
//    	logger.error("--------------->autoTask end:"+(System.currentTimeMillis() - one));
    }
    @Scheduled(cron = "0/30 * *  * * ? ")   //每30秒执行一次
    public void autoServiceTask() {
    	long one = System.currentTimeMillis();
//    	logger.error("--------------->autoServiceTask start:"+one);
    	checkServiceMap();
//    	logger.error("--------------->autoServiceTask end:"+(one - System.currentTimeMillis()) + "------"+System.currentTimeMillis());
    }
    @Scheduled(cron = "0/10 * *  * * ? ")   //每10秒执行一次
    public void autoDelTask() {
    	long one = System.currentTimeMillis();
//    	logger.error("--------------->autoDelTask start:"+one);
    	WxStatusTmpTEntity entity = null;
    	Set<Integer> totalRemoveKeys = NavMenuInitUtils.getInstance().removeMap.keySet();
        for (Integer deptId : totalRemoveKeys) {
	       	 ConcurrentMap<String, WxStatusTmpTEntity> removeMap = NavMenuInitUtils.getInstance().removeMap.get(deptId);
	       	 if(removeMap == null){
	       		 break;
	       	 }
	       	 
	       	Set<String> deletedKeys = removeMap.keySet();
	    	long currentTime = System.currentTimeMillis()/1000;
	        for (String key : deletedKeys) {
	        	try {
	        		entity = removeMap.get(key);
	        		if ((currentTime - entity.getLastChatTime()) >= 20) {
	        			removeMap.remove(key);
	        			NavMenuInitUtils.getInstance().userDeptMap.remove(key);
	    	            sendMsgWx(entity, "感谢您对重庆交通服务热线96096的支持，本次服务结束。", entity.getWxOpenid());
	    	            wxStatusTmpService.saveMsgDatebase(entity, "感谢您对重庆交通服务热线96096的支持，本次服务结束。",  entity.getWxOpenid());
	            	}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("autoDelTask", e);
				}
	        	
	        }
	       	 
        }
    	
    	
//        logger.error("--------------->autoDelTask end:"+(one - System.currentTimeMillis()) + "------"+System.currentTimeMillis());
    }
    
    @Scheduled(cron = "0/30 * *  * * ? ")   //每30秒执行一次
    public void autoOnlineSeatTask() {
    	long one = System.currentTimeMillis();
//    	logger.error("--------------->autoOnlineSeatTask start:"+one);
    	Set<Integer> totalOnlineDeptKeys = NavMenuInitUtils.getInstance().onLineDeptCsMap.keySet();
        for (Integer deptId : totalOnlineDeptKeys) {
	       	 ConcurrentMap<String, SysUserEntity> onlineDeptMap = NavMenuInitUtils.getInstance().onLineDeptCsMap.get(deptId);
	       	 if(onlineDeptMap == null){
	       		 break;
	       	 }
	     	Set<String> seatKeys = onlineDeptMap.keySet();
	     	long currentTimes  = System.currentTimeMillis();
	     	for (String key : seatKeys) {
	     		try {
	     			SysUserEntity sysUser = onlineDeptMap.get(key);
	         		if(sysUser != null && (currentTimes -sysUser.getHeatTimes()) >= 120000){
						//该座席未接入的所有状态为1的用户重新拉入等待队列
	         			resetWaitMap(deptId, key);
	         			onlineDeptMap.remove(key);
	         			NavMenuInitUtils.getInstance().userDeptMap.remove(key);
	         		}
	 			} catch (Exception e) {
	 				e.printStackTrace();
	 				logger.error("autoOnlineSeatTask", e);
	 			}
	     		
	 		}
        }
//        logger.error("--------------->autoOnlineSeatTask end:"+(one - System.currentTimeMillis()));
    }
    
    private void resetWaitMap(Integer deptId, String csId){
    	ConcurrentMap<String, WxStatusTmpTEntity> waitMap = NavMenuInitUtils.getInstance().waitMap.get(deptId);
    	ConcurrentMap<String, WxStatusTmpTEntity> serviceMap = NavMenuInitUtils.getInstance().serviceMap.get(deptId);
    	if(serviceMap != null && serviceMap.size() > 0){
    		for (String openId : serviceMap.keySet()) {
    			WxStatusTmpTEntity temp = serviceMap.get(openId);
    			if(temp != null && csId.equals(temp.getCsId()) && temp.getServiceStatus() == 1){
    				temp.setCsId(null);
    				temp.setCreateTime(new Timestamp(System.currentTimeMillis()));
    				temp.setServiceStatus(0);
    				temp.setLastChatTime(System.currentTimeMillis() / 1000);
    				temp.setIntoWaitingMapTime(System.currentTimeMillis() / 1000 );
    				if(waitMap == null){
    					waitMap = new ConcurrentHashMap<String, WxStatusTmpTEntity>();
    					NavMenuInitUtils.getInstance().waitMap.put(deptId, waitMap);
    				}
    				waitMap.put(openId, temp);
    				serviceMap.remove(openId);
    				//发送提示消息
    				sendMsgWx(temp, "当前座席忙，正在重新为您接入，请稍等！", temp.getWxOpenid());
    	            wxStatusTmpService.saveMsgDatebase(temp, "感谢您对重庆交通服务热线96096的支持，本次服务结束。",  temp.getWxOpenid());
    			}
			}
    	}
    	
    }

    /**
     * 撒旦
     */
	/*@Scheduled(cron = "0/60 * *  * * ? ")   //每30秒执行一次
    public void autoRoadSubscribe() {
    	long one = System.currentTimeMillis();
//    	logger.error("--------------->autoRoadSubscribe start:"+one);
    	Date nowDateTime = WeixinUtils.getNowDateTime();
//    	List<RoadSubscribeEntity> roadList = roadSubscribeService.queryRoadSubscribeList(null, null, "0", nowDateTime, 0);
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("subsType", 0);
    	params.put("subsSendNextTimes", nowDateTime);
    	params.put("subsIsStart", 0);
    	try {
    		List<RoadSubscribeVo> roadList = (List<RoadSubscribeVo>) roadSubscribeService.queryList(params);
        	if(roadList != null && roadList.size() > 0){
//        		logger.error("roadList-------------------------------------------------------------------------------------->"+roadList.size());
        		//查询文字路况
        		String textRoad = getTextRoad();
        		for (RoadSubscribeVo road : roadList) {
//        			logger.error("road---->"+road.getSubsSendTimes()+","+road.getSubsSendNextTimes()+","+road.getSubsId());
        			//修改路况
        			if(road.getSubsRemindType() == 0){//不重复
    					//发送路况信息給用户并标记
    					road.setSubsIsStart(1);
        			} else {//重复
    					//发送路况信息給用户并标记
    					road.setSubsSendNextTimes(WeixinUtils.getNextSendDate(false, nowDateTime, road.getSubsRemindWeek(), road.getSubsRemindHour(), road.getSubsRemindMinute()));
        			}
//        			logger.error("road.getSubsSendNextTimes---->"+road.getSubsSendTimes()+","+road.getSubsSendNextTimes()+","+road.getSubsId());	
        			try {
        				road.setSubsSendTimes(WeixinUtils.getNowDateTime());
        				//TODO:发送路况信息給用户
        				if(road.getSubsTitleName().equals("图片")){
        					//循环遍历图片
        					String videIds = road.getSubsImg();
        					String openId = road.getSubsOpenId();
//        					System.out.println("===>videIds:"+videIds);
        					if(StringUtil.isNotEmpty(videIds) && videIds.endsWith(",")){
        						videIds = videIds.substring(0, videIds.length() - 1);
        					}
        					List<Map<String, Object>> videImgList = videoImgService.queryVideoImgListMap(null,videIds, openId);
        					
        					String url = "http://cq96096.cn/subscribe/details?subsId="+road.getSubsId();
        					String context = "{\"touser\":\""+openId+"\",\"msgtype\":\"news\",\"news\":{\"articles\": [";
        					if(videImgList != null && videImgList.size() > 0){
        						String picUrl = "http://www.cq96096.cn"+ videImgList.get(0).get("videUrl");
        						for (int i = 0; i < videImgList.size() ; i++) {
        							Map<String, Object> map = videImgList.get(i);
        							if(i == 0){
        								context += "{\"title\":\""+map.get("videShowName")+"\",\"description\":\"\",\"url\":\""+url+"\",\"picurl\":\""+picUrl+"\"}";
        							} else {
        								context += ",{\"title\":\""+map.get("videShowName")+"\",\"description\":\"\",\"url\":\""+url+"\",\"picurl\":\""+picUrl+"\"}";
        							}
    							}
        					} else {
        						String picUrl = "", desc = "您的路况订阅已发出，请点击查看全文！";
        						desc = "该路段当前未接到相关路况信息。";
        						context += "{\"title\":\"路况订阅\",\"description\":\""+desc+"\",\"url\":\""+url+"\",\"picurl\":\""+picUrl+"\"}";
        					}
        					context += "]}}";
//        					WeiXinOperUtil.sendWxKefuImgsMsg(WeiXinOperUtil.getAccessToken(), road.getSubsOpenId(), "路况订阅", title, "http://cq96096.cn/subscribe/details?subsId="+road.getSubsId(),picUrl);
        					WeiXinOperUtil.sendWxKefuImgsMsg(WeiXinOperUtil.getAccessToken(), context);
        				} else {
//        					String str=getTextRoadNews(road.getSubsCharacter());
        					String str=isSendTextRoad(textRoad ,road.getSubsCharacter());
        					if(StringUtils.isBlank(str)){
        						str = "该路段当前未接到相关路况信息。";
        					}
        					WeiXinOperUtil.sendWxKefuMsg(WeiXinOperUtil.getAccessToken(), road.getSubsOpenId(), "路况订阅", str, "");
        					
        				}
        				wxStatusTmpService.saveMsgDatebase(null, "发送路况订阅信息",  road.getSubsOpenId());
        				//修改时间
        				roadSubscribeService.update(road);
    				} catch (Exception e) {
    					e.printStackTrace();
    					logger.error("---->autoRoadSubscribe error:", e);
    				}
    	    			
        		}
//        		logger.error("roadList-----------------===========================================>");
        	}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--------------->autoRoadSubscribe error:", e);
		}
    	
//    	logger.error("--------------->autoRoadSubscribe end:");
    }*/
    /**
     * 及时推送 s as
     *//*
	@Scheduled(cron = "0/60 * *  * * ? ")   //每30秒执行一次
    public void autoTimelyRoadSubscribe() {
    	long one = System.currentTimeMillis();
//    	logger.error("--------------->autoTimelyRoadSubscribe start:"+one);
    	List<RoadSubscribeVo> roadList = new ArrayList<RoadSubscribeVo>();
    	try {
    		Map<String, Object> params = new HashMap<String, Object>();
    		params.put("subsType", 1);
        	params.put("subsIsStart", 0);
    		roadList = (List<RoadSubscribeVo>) roadSubscribeService.queryList(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("autoTimelyRoadSubscribe------queryRoadSubscribeList----error>",e);
		}
    	
    	if(roadList != null && roadList.size() > 0){
//    		logger.error("autoTimelyRoadSubscribe------>"+roadList.size());
    		//查询文字路况
    		String textRoad = getTextRoad();
//    		logger.error("autoTimelyRoadSubscribe------textRoad>"+textRoad);
    		for (RoadSubscribeVo road : roadList) {
    			try {
    				road.setSubsSendTimes(WeixinUtils.getNowDateTime());
    				//TODO:发送路况信息給用户
    				if(road.getSubsTitleName().equals("图片")){
//    					logger.error("autoTimelyRoadSubscribe---------->send is imager");
    				} else{
//    					String str=getTextRoadNews(road.getSubsCharacter());
//    					logger.error("autoTimelyRoadSubscribe---------->send is text");
    					String str=isSendTextRoad(textRoad ,road.getSubsCharacter());
//    					logger.error("autoTimelyRoadSubscribe---------->str:"+str);
    					if(StringUtils.isNotBlank(str)){
//    						logger.error("autoTimelyRoadSubscribe---------->sss:");
    						Map<String, Object> params = new HashMap<String, Object>();
    						params.put("messName", str);
    						params.put("messOpenId", road.getSubsOpenId());
    						List<RoadSendMessageVo> list = (List<RoadSendMessageVo>) roadSendMessageService.queryList(params);
    						if(list != null && list.size() > 0){
//    							logger.error("autoTimelyRoadSubscribe---------->is not null, list is not null"+list.size());
    						} else {
//    							logger.error("autoTimelyRoadSubscribe---------->is null, is send road");
    							//判断是否发送
        						WeiXinOperUtil.sendWxKefuMsg(WeiXinOperUtil.getAccessToken(), road.getSubsOpenId(), "路况订阅", str, "");    					
            					wxStatusTmpService.saveMsgDatebase(null, "发送路况订阅信息",  road.getSubsOpenId());
            					RoadSendMessageVo entity = new RoadSendMessageVo();
            					entity.setMessId(UUID.randomUUID().toString());
            					entity.setMessName(str.substring(0,20));
//            					entity.setMessName(str);
            					entity.setMessOpenId(road.getSubsOpenId());
            					entity.setMessSendTimes(new Timestamp(System.currentTimeMillis()));
            					roadSendMessageService.insert(entity);
//                				logger.error("autoTimelyRoadSubscribe---------->send road flashed");
    						}
    						
    					}
    					
    				}
    				
    			} catch (Exception e) {
    				e.printStackTrace();
//    				logger.error("autoTimelyRoadSubscribe---------->tow error:"+e);
    			}
    			
    		}
    	} else {
//    		logger.error("autoTimelyRoadSubscribe------>roadList is null");
    	}
//    	logger.error("autoTimelyRoadSubscribe----------====================>end");
    }*/

    private String getTextRoad(){
    	String url = "http://10.224.5.164/cqjt/getRoadNews?type=6&location=500000&limit=100";
    	StringBuilder json = new StringBuilder();
    	try {
    		URL oracle = new URL(url);
    		URLConnection yc = oracle.openConnection();
    		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"utf-8"));
    		String inputLine = null;
    		while ( (inputLine = in.readLine()) != null) {
    			json.append(inputLine);
    		}
    		in.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error("getTextRoad>",e);
    	}
    	return json.toString();
    }
    
    private String isSendTextRoad(String json,String subsCharacter){
    	String result = "";
    	try {
    		String[] ids=subsCharacter.split(",");
    		if(StringUtils.isNotBlank(json.toString())){
            	JSONObject jObject=JSONObject.parseObject(json.toString());
                JSONArray jsonArray = JSONObject.parseArray(jObject.get("data").toString());
                for(int i=0;i<jsonArray.size();i++){
                	JSONObject j=JSONObject.parseObject(jsonArray.get(i).toString());
                	String betweenNode=(String) j.get("betweenNode");
                	String[] bNodes=betweenNode.split("-");
                	if(bNodes.length==2){
                		if(checkZh(ids[1],ids[2],bNodes[0],bNodes[1])){
                			result+="    "+j.get("subject").toString()+j.get("content").toString()+"\r\n";
                		}
                	}else if(bNodes.length==1){
                		if(checkZh(ids[1],ids[2],bNodes[0],bNodes[0])){
                			result+="    "+j.get("subject").toString()+j.get("content").toString()+"\r\n";
                		}
                	}
                }
            }
		} catch (Exception e) {
			logger.error("isSendTextRoad>",e);
		}
    	return result;
    }
    
    public String getTextRoadNews(String id){
    	String result="";
    	String[] ids=id.split(",");
//    	for(int i=0;i<ids.length;i++){
//        	StringBuffer sql=new StringBuffer();
//        	sql.append(" SELECT ID,NAME,CODE,TRUNK,NO,\"ORDER\" ");
//        	sql.append(" FROM NODE_T");
//        	sql.append(" WHERE ID="+ids[i]);
//        	List<Object> nodeList=roadSubscribeService.findBySQL(sql.toString());
//        	if(nodeList!=null){
//        		Object[] obj=(Object[])nodeList.get(0);
//        		node+=StringUtil.nullObject2String(obj[1])+"|";
//        	}
//    	}
        String url = "http://10.224.5.164/cqjt/getRoadNews?type=6&location=500000&limit=100";
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"utf-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(StringUtils.isNotBlank(json.toString())){
        	JSONObject jObject=JSONObject.parseObject(json.toString());
            JSONArray jsonArray = JSONObject.parseArray(jObject.get("data").toString());
            for(int i=0;i<jsonArray.size();i++){
            	JSONObject j=JSONObject.parseObject(jsonArray.get(i).toString());
            	String betweenNode=(String) j.get("betweenNode");
            	String[] bNodes=betweenNode.split("-");
            	if(bNodes.length==2){
            		if(checkZh(ids[1],ids[2],bNodes[0],bNodes[1])){
            			result+="    "+j.get("subject").toString()+j.get("content").toString()+"\r\n";
            		}
            	}else if(bNodes.length==1){
            		if(checkZh(ids[1],ids[2],bNodes[0],bNodes[0])){
            			result+="    "+j.get("subject").toString()+j.get("content").toString()+"\r\n";
            		}
            	}
            }
        }
        return result;
    }
    
    private Map<String, RoadHtljVo> nameMap = new HashMap<String, RoadHtljVo>();
	private Map<String, RoadHtljVo> interflowNameMap = new HashMap<String, RoadHtljVo>();
    
    /**
     * 获取所有桩号
     * @return
     */
	private synchronized List<RoadHtljVo> queryAllRoadHtlj(){
    	List<RoadHtljVo> result = new ArrayList<RoadHtljVo>();
    	try {
    		List<RoadHtljVo> list=(List<RoadHtljVo>) roadHtljService.queryList(new HashMap<String,Object>());
    		
    		if(list != null && list.size() > 0){
    			for (RoadHtljVo r : list) {
    				nameMap.put(r.getName(), r);
    				interflowNameMap.put(r.getInterflowName(), r);
				}
    		}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("queryAllRoadHtlj:", e);
			
		}
    	return result;
    }
    
    private boolean checkZh(String node1,String node2,String node3,String node4){
    	boolean result=false;
    	if(nameMap.size() < 1){
    		queryAllRoadHtlj();
    	}
    	RoadHtljVo rEntity1=interflowNameMap.get(node1);
    	RoadHtljVo rEntity2=interflowNameMap.get(node2);
    	RoadHtljVo rEntity3=nameMap.get(node3);
    	RoadHtljVo rEntity4=nameMap.get(node4);
    	
    	if(rEntity1 != null && rEntity2 != null && rEntity3 != null && rEntity4 != null){
    		if(rEntity1.getRoadCode().equals(rEntity3.getRoadCode())){
    			result = true;
    		}
    		
    		//判断桩号是否对应上
        	if(result){
        		double pos1=new Double(rEntity1.getInterflowPos());
        		double pos2=new Double(rEntity2.getInterflowPos());
        		double pos3=new Double(rEntity3.getInterflowPos());
        		double pos4=new Double(rEntity4.getInterflowPos());
        		if(pos1<=pos3&&pos2>=pos3||pos2<=pos3&&pos1>=pos3||pos1<=pos4&&pos2>=pos4||pos2<=pos4&&pos1>=pos4){
        			result=true;
        		}else{
        			result=false;
        		}
        	}
    	}
    	return result;
    }
    
    /**
     * 判断桩号是否满足要求
     * @param node1
     * @param node2
     * @param node3
     * @param node4
     * @return
     */
//    private boolean checkLkdy(String node1,String node2,String node3,String node4){
//    	boolean b=false;
//    	RoadHtljEntity rEntity1=new RoadHtljEntity();
//    	RoadHtljEntity rEntity2=new RoadHtljEntity();
//    	RoadHtljEntity rEntity3=new RoadHtljEntity();
//    	RoadHtljEntity rEntity4=new RoadHtljEntity();
//    	List<RoadHtljEntity> rList1=roadSubscribeService.findByProperty(RoadHtljEntity.class, "interflowName", node1);
//    	List<RoadHtljEntity> rList2=roadSubscribeService.findByProperty(RoadHtljEntity.class, "interflowName", node2);
//    	List<RoadHtljEntity> rList3=roadSubscribeService.findByProperty(RoadHtljEntity.class, "name", node3);
//    	List<RoadHtljEntity> rList4=roadSubscribeService.findByProperty(RoadHtljEntity.class, "name", node4);
//    	//判断是否是一条路
//    	if(rList1!=null&&rList1.size()>0&&rList3!=null&&rList3.size()>0&&rList2!=null&&rList2.size()>0&&rList4!=null&&rList4.size()>0){
//    		rEntity1=rList1.get(0);
//    		rEntity3=rList3.get(0);
//    		rEntity2=rList2.get(0);
//    		rEntity4=rList4.get(0);
//    		if(rEntity1.getRoadCode().equals(rEntity3.getRoadCode())){
//    			b=true;
//    		}
//    	}
//    	//判断桩号是否对应上
//    	if(b){
//    		double pos1=new Double(rEntity1.getInterflowPos());
//    		double pos2=new Double(rEntity2.getInterflowPos());
//    		double pos3=new Double(rEntity3.getInterflowPos());
//    		double pos4=new Double(rEntity4.getInterflowPos());
//    		if(pos1<=pos3&&pos2>=pos3||pos2<=pos3&&pos1>=pos3||pos1<=pos4&&pos2>=pos4||pos2<=pos4&&pos1>=pos4){
//    			b=true;
//    		}else{
//    			b=false;
//    		}
//    	}
//    	return b;
//    }
//    @Scheduled(cron = "0/30 * *  * * ? ")   //每30秒执行一次
//    public void navigationMenu() {
//    	long nowTimes= WeixinUtils.getNowDateTime().getTime();
//    	List<String> list = new ArrayList<String>();
//    	for (String key : WeiXinConst.navigationMenu.keySet()) {
//    		NavigationMenuEntity menu =  WeiXinConst.navigationMenu.get(key);
//    		if((nowTimes - menu.getDate().getTime()) > 120){
//    			list.add(menu.getOpenId());
//    		}
//		}
//    	
//    	if(list.size() > 0){
//    		for (String s : list) {
//    			WeiXinConst.removeMenuMap(s);
//			}
//    	}
//    }
    
    /**
     * 检查等待中的状态
     */
    private void checkWaitingMap(){
    	 WxStatusTmpTEntity entity = null;
         Long currentTime = null;
         
         Set<Integer> totalWaitingKeys = NavMenuInitUtils.getInstance().waitMap.keySet();
//         System.out.println("========totalWaitingKeys========>"+totalWaitingKeys.size());
         for (Integer deptId : totalWaitingKeys) {
        	 ConcurrentMap<String, WxStatusTmpTEntity> waitingMap = NavMenuInitUtils.getInstance().waitMap.get(deptId);
        	 if(waitingMap == null){
        		 break;
        	 }
        	 Set<String> waitingKeys = waitingMap.keySet();
             for (String key : waitingKeys) {
//            	 System.out.println("========key========>"+key);
            	 try {
            		 entity = waitingMap.get(key);
                     currentTime = System.currentTimeMillis() / 1000;
                     Long tempTimes = currentTime - entity.getIntoWaitingMapTime();
                     String wxOpenId = entity.getWxOpenid();
                     if(tempTimes >= CommonConstantUtils.wait1Times() && tempTimes < CommonConstantUtils.wait2Times()){
//                    	 System.out.println("========CommonConstantUtils.wait1Times()========>"+CommonConstantUtils.wait1Times());
                    	 if(entity.getWaitingHintNum() == 0){
//                    		 System.out.println("========CommonConstantUtils.wait1SysHint()========>"+CommonConstantUtils.wait1SysHint());
                    		 sendMsgWx(entity, CommonConstantUtils.wait1SysHint(), wxOpenId);
                    		 entity.setWaitingHintNum(1);
                    		 wxStatusTmpService.saveMsgDatebase(entity, CommonConstantUtils.wait1SysHint(), wxOpenId);
                    	 }
                     } else if(tempTimes >= CommonConstantUtils.wait2Times() && tempTimes < CommonConstantUtils.wait3Times()){
//                    	 System.out.println("========CommonConstantUtils.wait2Times()========>"+CommonConstantUtils.wait2Times());
                    	 if(entity.getWaitingHintNum() == 1){
//                    		 System.out.println("========CommonConstantUtils.wait2SysHint()========>"+CommonConstantUtils.wait2SysHint());
                    		 String content = CommonConstantUtils.wait2SysHint().replaceAll("xx", ""+NavMenuInitUtils.getInstance().getFrontWaitNum(wxOpenId));
                    		 sendMsgWx(entity, content, wxOpenId);
        	        		 entity.setWaitingHintNum(2);
//        	        		 System.out.println("----content========>"+content);
        	        		 wxStatusTmpService.saveMsgDatebase(entity, content, wxOpenId);
                    	 }
                     } else if(tempTimes >= CommonConstantUtils.wait3Times()){
//                    	 System.out.println("========CommonConstantUtils.wait3Times()========>"+CommonConstantUtils.wait3Times());
                    	 if(entity.getWaitingHintNum() == 2){
//                    		 System.out.println("========CommonConstantUtils.wait3SysHint()========>"+CommonConstantUtils.wait3SysHint());
                    		 sendMsgWx(entity, CommonConstantUtils.wait3SysHint(), wxOpenId);
                    		 wxStatusTmpService.saveMsgDatebase(entity, CommonConstantUtils.wait3SysHint(), wxOpenId);
                    	 }
                    	 waitingMap.remove(key);
                    	 NavMenuInitUtils.getInstance().userDeptMap.remove(key);
                    	 WaitQ waitQ=new WaitQ();
             			 waitQ.completetask();
                    	 
                     }
    			} catch (Exception e) {
    				e.printStackTrace();
    				logger.error("checkWaitingMap:", e);
    			}
                 
             }
		}
         
         
    }
    
    
    /**
     * 检查服务中的状态
     */
    private void checkServiceMap(){
    	WxStatusTmpTEntity entity = null;
        Long currentTime = null;//当前时间
        
        Set<Integer> totalServiceKeys = NavMenuInitUtils.getInstance().serviceMap.keySet();
        for (Integer deptId : totalServiceKeys) {
	       	 ConcurrentMap<String, WxStatusTmpTEntity> serviceMap = NavMenuInitUtils.getInstance().serviceMap.get(deptId);
	       	 if(serviceMap == null){
	       		 break;
	       	 }
//	       	 Set<String> servicingKeys = WeiXinConst.servicingMap.keySet();
	       	Set<String> servicingKeys = serviceMap.keySet();
	        for (String key : servicingKeys) {
	        	try {
//	        		entity = WeiXinConst.servicingMap.get(key);
	        		entity = serviceMap.get(key);
	            	currentTime = System.currentTimeMillis()/1000;
	            	String wxOpenId = entity.getWxOpenid();
	            	if(1==entity.getServiceStatus().intValue()){
	            		long tempTimes = currentTime - entity.getIntoWaitingMapTime();
	            		checkServiceState1(tempTimes, entity, wxOpenId, key);//等待队列
	            	} else if (2 == entity.getServiceStatus().intValue()) {
	            		long tempTimes = currentTime - entity.getChatOvertime();
	            		checkServiceState2(tempTimes, entity, wxOpenId, key);//服务队列
	            	}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("checkServiceMap:", e);
				}
	        }
        }
        
        
    }

    /**
     * 分配座席后，还未建立通道
     * @param tempTimes
     * @param entity
     * @param wxOpenId
     * @param key
     */
    private void checkServiceState1(long tempTimes, WxStatusTmpTEntity entity, String wxOpenId, String key){
    	if(tempTimes >= CommonConstantUtils.serviceState11Times() && tempTimes < CommonConstantUtils.serviceState12Times()){
    			SeatW seatW=new SeatW();
    			seatW.startProcessInstance();
    		if(entity.getWaitingHintNum() == 0){
    			sendMsgWx(entity, CommonConstantUtils.serviceState11SysHint(), wxOpenId);
       		 	entity.setWaitingHintNum(1);
       		 wxStatusTmpService.saveMsgDatebase(entity, CommonConstantUtils.serviceState11SysHint(), wxOpenId);
       	 	}
    	} else if(tempTimes >= CommonConstantUtils.serviceState12Times() && tempTimes < CommonConstantUtils.serviceState13Times()){
       	 	if(entity.getWaitingHintNum() == 1){
       	 		sendMsgWx(entity, CommonConstantUtils.serviceState12SysHint(), wxOpenId);
       		 	entity.setWaitingHintNum(2);
       		 	wxStatusTmpService.saveMsgDatebase(entity, CommonConstantUtils.serviceState12SysHint(), wxOpenId);
       	 	}
    	} else if(tempTimes >= CommonConstantUtils.serviceState13Times()){
    		
//    		WeiXinConst.servicingMap.remove(key);
    		NavMenuInitUtils.getInstance().removeServiceMap(key);
    		NavMenuInitUtils.getInstance().userDeptMap.remove(key);
    		SeatW seatW=new SeatW();
    		seatW.completetask();
       	 	if(entity.getWaitingHintNum() == 2){
       	 		sendMsgWx(entity, CommonConstantUtils.serviceState13SysHint(), wxOpenId);
       	 		wxStatusTmpService.saveMsgDatebase(entity, CommonConstantUtils.serviceState13SysHint(), wxOpenId);
       	 	}
    	}
    }
   /* 超时判断*/
    
    private void checkServiceState2(long tempTimes, WxStatusTmpTEntity entity, String wxOpenId, String key){
    	//判断是用户超时，还是座席超时  0 用户超时，1座席超时
    	if(entity.getIsInitiative() == 1){
    		if(tempTimes >=CommonConstantUtils.serviceInitiativeFinishTimes()){
//    			WeiXinConst.servicingMap.remove(key);
    			NavMenuInitUtils.getInstance().removeServiceMap(key);
    			NavMenuInitUtils.getInstance().userDeptMap.remove(key);
    			PeopleServic peopleServic= new PeopleServic();
    			peopleServic.completetask("P2");
        		sendMsgToService(entity.getSessionId(), CommonConstantUtils.sessionFinishSysHint(), wxOpenId, true);
        		wxStatusTmpService.saveMsgDatebase(entity, CommonConstantUtils.sessionFinishSysHint(), wxOpenId);
    		}
    		return;
    	}
    	if(entity.getSendType() == 0){//用户超时
    		if(tempTimes >= CommonConstantUtils.serviceState2User11Times() && tempTimes <= CommonConstantUtils.serviceState2User12Times()){
    			//判断只发送一次
    			if(entity.getUserOffHintNum() == 0){
    				sendMsgWxService(entity, CommonConstantUtils.serviceState2User1Hint(), wxOpenId, false);
    				entity.setUserOffHintNum(1);
    				wxStatusTmpService.saveMsgDatebase(entity, CommonConstantUtils.serviceState2User1Hint(), wxOpenId);
    			}
    		} else if(tempTimes >= CommonConstantUtils.serviceState2UserTimes()){
//    			WeiXinConst.servicingMap.remove(key);
    			NavMenuInitUtils.getInstance().removeServiceMap(key);
    			PeopleServic peopleServic= new PeopleServic();
    			peopleServic.completetask("P2");
    			sendMsgWxService(entity, CommonConstantUtils.serviceState2UserHint(), wxOpenId, false);
    			sendMsgToService(entity.getSessionId(), CommonConstantUtils.sessionFinishSysHint(), wxOpenId, true);
    			wxStatusTmpService.saveMsgDatebase(entity, CommonConstantUtils.sessionFinishSysHint(), wxOpenId);
    		}
    	} else if(entity.getSendType() == 1){//座席超时
    		if(tempTimes >= CommonConstantUtils.serviceState21Times() && tempTimes < CommonConstantUtils.serviceState22Times()){
    			if(entity.getServiceHintNum() == 0){
    				sendMsgWxService(entity, CommonConstantUtils.serviceState21SysHint(), wxOpenId, false);
                    entity.setServiceHintNum(1);
                    wxStatusTmpService.saveMsgDatebase(entity, CommonConstantUtils.serviceState21SysHint(), wxOpenId);
				}
    		} else if(tempTimes >= CommonConstantUtils.serviceState22Times() && tempTimes < CommonConstantUtils.serviceState23Times()){
    			if(entity.getServiceHintNum() == 1){
    				sendMsgWxService(entity, CommonConstantUtils.serviceState22SysHint(), wxOpenId, false);
    				entity.setTwoSecFlag(true);
                    entity.setServiceHintNum(2);
                    wxStatusTmpService.saveMsgDatebase(entity, CommonConstantUtils.serviceState22SysHint(), wxOpenId);
				} else if(tempTimes >=  CommonConstantUtils.serviceState231Times() && tempTimes <= CommonConstantUtils.serviceState232Times()){
//					System.out.println("----------->:tempTimes:"+tempTimes+","+CommonConstantUtils.serviceState231Times()+","+CommonConstantUtils.serviceState232Times());
					if(entity.getServiceOffHintNum() == 0){
	    				sendMsgToService(entity.getSessionId(), CommonConstantUtils.serviceState231SysHint(), wxOpenId, false);
	                    entity.setServiceOffHintNum(1);
	                    wxStatusTmpService.saveMsgDatebase(entity, CommonConstantUtils.serviceState231SysHint(), wxOpenId);
					}
				}
    		} else if(tempTimes >=  CommonConstantUtils.serviceState23Times()){
    			if(entity.getServiceHintNum() == 2){
//    				WeiXinConst.servicingMap.remove(key);
    				NavMenuInitUtils.getInstance().removeServiceMap(key);
    				NavMenuInitUtils.getInstance().userDeptMap.remove(key);
    				PeopleServic peopleServic= new PeopleServic();
        			peopleServic.completetask("P2");
    				sendMsgWxService(entity, CommonConstantUtils.serviceState23SysHint(), wxOpenId, false);
    				sendMsgToService(entity.getSessionId(), CommonConstantUtils.sessionFinishSysHint(), wxOpenId, true);
    				wxStatusTmpService.saveMsgDatebase(entity, CommonConstantUtils.sessionFinishSysHint(), wxOpenId);
				}
    		}
    	}
    }

    /**
     * 系统提示同时发送用户和座席
     * @param entity
     * @param content
     * @param wxOpenId
     */
	private void sendMsgWxService(WxStatusTmpTEntity entity, String content, String wxOpenId, boolean isOffSocket) {
		//发送給用户
		sendMsgWx(entity, content, wxOpenId);
		//发送給座席
		sendMsgToService(entity.getSessionId(), content, wxOpenId, isOffSocket);
	}
	
	/**
	 * 发送用户
	 * @param entity
	 * @param content
	 * @param wxOpenId
	 */
	private void sendMsgWx(WxStatusTmpTEntity entity, String content, String wxOpenId){
//		System.out.println("- weixin--->:wxOpenId:"+wxOpenId+",content:"+content);
		String userJsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
		        wxOpenId, String.format(content));
		//发送給用户
		String publicID = NavMenuInitUtils.getInstance().userPublicIdMap.get(wxOpenId);
//		Integer deptID = NavMenuInitUtils.getInstance().userDeptMap.get(wxOpenId); // 加入的
		WeiXinOperUtil.sendMsgToWx(WeiXinOperUtil.getAccessToken(publicID), userJsonContent);// 已改
		
	}
	
    /**
     * 发送系统消息
     * @param sessionId
     * @param content
     * @param wxOpenId
     */
    private void sendMsgToService(String sessionId, String content, String wxOpenId, boolean isOffSocket){
    	content = content.replaceAll("\\n", "<br>");
//    	System.out.println("----service--->:wxOpenId:"+wxOpenId+",content:"+content);
    	//通知前端已经关閉
        String msg = "{\"Content\":\"" + content + "\",\"CreateTime\":\"" + System.currentTimeMillis() / 1000 + "\",\"ToUserName\":\"gh_45216f693385\",\"FromUserName\":\"" + wxOpenId+ "\",\"MsgType\":\"autotext\",\"MsgId\":\"12345678900\"}";
        if (!StringUtil.isEmpty(sessionId)) {
            WebSocketSession session = WeiXinConst.webSocketSessionMap.get(sessionId);
            if(null == session){
            	
            }
            try {
                session.sendMessage(new TextMessage(msg));
            } catch (IOException e) {
            	e.printStackTrace();
                logger.info("发送系统消息超时异常.", e);
            }
            if(isOffSocket){
            	try {
            		WeiXinConst.webSocketSessionMap.remove(sessionId);
            	 } catch (Exception e) {
            		 e.printStackTrace();
            		 logger.info("关闭socket错误.", e);
            	 }
            }
        }
    }
    

}
