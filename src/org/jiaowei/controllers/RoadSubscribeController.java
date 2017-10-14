package org.jiaowei.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jiaowei.entity.RoadLxfdEntity;
import org.jiaowei.entity.RoadSubscribeEntity;
import org.jiaowei.service.RoadSubscribeService;
import org.jiaowei.service.VideoImgService;
import org.jiaowei.util.StringUtil;
import org.jiaowei.wxutil.WeiXinConst;
import org.jiaowei.wxutil.WeixinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoadSubscribeController {
	
    @Autowired
    private RoadSubscribeService roadSubscribeService;
    @Autowired
    private VideoImgService videoImgService;
    /**
     * 订阅列表
     * @param request
     * @param map
     * @return
     */
	@RequestMapping("/subscribe/list")
	public  String queryList(HttpServletRequest request,Map<String,Object> map){

		String openId = request.getParameter("openId");
		String code = request.getParameter("code");
		if(StringUtil.isNotEmpty(code)){
			//获取openId
			openId = WeixinUtils.getOAuthOpenId(WeiXinConst.appId, WeiXinConst.appSecret, code); // appId 修改？？？？
		}
		try {
			List<RoadSubscribeEntity> subscribeList = roadSubscribeService.queryRoadSubscribeList(openId, null, null, null, null);
			map.put("openId", openId);
			map.put("subscribeList", subscribeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "roadSubscribe/list";
	}

	
	/**
	 * 初始化新增页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/subscribe/initAdd")
	public  String initAdd(HttpServletRequest request,Map<String,Object> map){
		String openId = request.getParameter("openId");
		//查询用户收藏的图片
		List<Map<String, Object>> videoList = videoImgService.queryVideoImgListMap("1",null, openId);
		RoadSubscribeEntity road = new RoadSubscribeEntity();
		road.setSubsRemindDate(WeixinUtils.parseDateStr(WeixinUtils.getNowDate()));
		road.setSubsRemindTimeHour("09:00");
		//查询文字列表
		map.put("openId", openId);
		map.put("videoList", videoList);
		map.put("road", road);
		return "roadSubscribe/add";
	}

	/**
	 * 初始化新增页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/subscribe/initAddText")
	public  String initAddText(HttpServletRequest request,Map<String,Object> map){
		String openId = request.getParameter("openId");
		//查询用户订阅的路段
//    	StringBuffer sql=new StringBuffer();
//    	sql.append(" SELECT ID,NAME,CODE,TRUNK,NO,\"ORDER\",NODE,DESCRIPT,TRUNK_NO ");
//    	sql.append(" FROM NODE_T");
//    	sql.append(" ORDER BY TRUNK_NO ASC,\"ORDER\" ASC");
//    	List<Object> nodeList=videoImgService.findBySQL(sql.toString());
//    	List<NodeEntity> list=new ArrayList<NodeEntity>();
//    	for(int i=0;i<nodeList.size();i++){
//    		NodeEntity entity=new NodeEntity();
//    		Object[] obj=(Object[])nodeList.get(i);
//    		entity.setId(new Integer(obj[0].toString()));
//    		entity.setName(StringUtil.nullObject2String(obj[1]));
//    		entity.setCode(StringUtil.nullObject2String(obj[2]));
//    		entity.setTrunk(StringUtil.nullObject2String(obj[3]));
//    		entity.setNo(StringUtil.nullObject2String(obj[4]));
//    		entity.setOrder(Double.valueOf(obj[5].toString()));
//    		entity.setNode(obj[6].toString());
//    		entity.setDescript(StringUtil.nullObject2String(obj[7]));
//    		entity.setTrunkNo(new Integer(obj[8].toString()));
//    		list.add(entity);
//    	}
    	RoadSubscribeEntity road = new RoadSubscribeEntity();
		road.setSubsRemindDate(WeixinUtils.parseDateStr(WeixinUtils.getNowDate()));
		road.setSubsRemindTimeHour("09:00");
		//查询文字列表
		map.put("openId", openId);
		//map.put("nodeList", nodeList);
		map.put("road", road);
//		request.setAttribute("nodeList", list);
		
		return "roadSubscribe/addText";
	}
	@RequestMapping("/subscribe/initEditText")
	public  String initEditText(HttpServletRequest request,Map<String,Object> map){
		String id = request.getParameter("id");
		StringBuffer sql=new StringBuffer();
    	sql.append(" SELECT ROAD_CODE,ROAD_NAME,LD_NAME,ROAD_DIR,START_NAME,END_NAME,DESCRIPT");
    	sql.append(" FROM ROAD_LXFD");
    	sql.append(" ORDER BY ROAD_CODE ASC");
    	List<Object> nodeList=videoImgService.findBySQL(sql.toString());
    	List<RoadLxfdEntity> list=new ArrayList<RoadLxfdEntity>();
    	try{
        	for(int i=0;i<nodeList.size();i++){
        		RoadLxfdEntity entity=new RoadLxfdEntity();
        		Object[] obj=(Object[])nodeList.get(i);
        		entity.setRoadCode(StringUtil.nullObject2String(obj[0]));
        		entity.setRoadName(StringUtil.nullObject2String(obj[1]));
        		entity.setLdName(StringUtil.nullObject2String(obj[2]));
        		entity.setRoadDir(StringUtil.nullObject2String(obj[3]));
        		entity.setStartName(StringUtil.nullObject2String(obj[4]));
        		entity.setEndName(StringUtil.nullObject2String(obj[5]));
        		entity.setDescript(StringUtil.nullObject2String(obj[6]));
        		list.add(entity);
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		//查询用户订阅的路段
		List<RoadSubscribeEntity> list2 = roadSubscribeService.queryRoadSubscribeList(null, Long.parseLong(id), null, null, null);
		RoadSubscribeEntity road = new RoadSubscribeEntity();
		for(int i=0;i<list2.size();i++){
			road = list2.get(i);
			if(road.getSubsTitleName().equals("文字"))break;
		}
		if(road.getSubsTitleName()!=null){
			road.setSubsRemindTimeHour(road.getSubsRemindHour() + ":" + road.getSubsRemindMinute());
		}else{
			road = new RoadSubscribeEntity();
			road.setSubsRemindDate(WeixinUtils.parseDateStr(WeixinUtils.getNowDate()));
			road.setSubsRemindTimeHour("09:00");
		}
		//查询文字列表
		map.put("openId", road.getSubsOpenId());
        request.setAttribute("list", list);
		map.put("road", road);
		return "roadSubscribe/addText";
	}
	/**
	 * 初始化修改页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/subscribe/initEdit")
	public  String initEdit(HttpServletRequest request,Map<String,Object> map){
		String id = request.getParameter("id");
		//查询用户收藏的图片
		List<RoadSubscribeEntity> list = roadSubscribeService.queryRoadSubscribeList(null, Long.parseLong(id), null, null, null);
		RoadSubscribeEntity road = new RoadSubscribeEntity();
		if(list != null && list.size() > 0){
			road = list.get(0);
		}
		road.setSubsRemindTimeHour(road.getSubsRemindHour() + ":" + road.getSubsRemindMinute());
		//查询用户收藏的图片
		List<Map<String, Object>> videoList = videoImgService.queryVideoImgListMap("0", null, road.getSubsOpenId());
		map.put("videoList", videoList);
		map.put("openId", road.getSubsOpenId());
		map.put("road", road);
		return "roadSubscribe/add";
	}

    /**
     * 新增
     * @return
     */
    @RequestMapping("/subscribe/add")
    @ResponseBody
    public Map<String,Object> add(HttpServletRequest request, RoadSubscribeEntity road) {
    	Date nowDateTime = WeixinUtils.getNowDateTime();
    	road.setSubsCreateTime(nowDateTime);
    	int code = 0;
    	String msg = "订阅成功！";
    	try {
    		//计算时间
    		Date nextSendDate = null;
    		String time = road.getSubsRemindTimeHour();
    		if(StringUtils.isBlank(time)){
    			time = "09:00";
    		}
    		road.setSubsRemindHour(time.substring(0,time.indexOf(":")));
    		road.setSubsRemindMinute(time.substring(time.indexOf(":")+1));
    		if(road.getSubsRemindType() == 1){
    			nextSendDate = WeixinUtils.getNextSendDate(true, nowDateTime, road.getSubsRemindWeek(), road.getSubsRemindHour(), road.getSubsRemindMinute());
    		} else {
    			nextSendDate = WeixinUtils.getNextSendDate(road.getSubsRemindDate(), road.getSubsRemindHour(), road.getSubsRemindMinute());
    		}
    		if(nextSendDate.getTime() > nowDateTime.getTime()){
    		} else {
    			nextSendDate = WeixinUtils.getDateOfDays(nextSendDate, 1);
    		}
    		road.setSubsSendNextTimes(nextSendDate);
    		int result = (Integer) roadSubscribeService.save(road);
			if(result == 0){
				code = 1;
	    		msg = "订阅失败！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = 3;
    		msg = "订阅异常！";
		}
    	
    	Map<String,Object> resultMap = new HashMap<String, Object>();
    	resultMap.put("code", code);
    	resultMap.put("msg", msg);
    	return resultMap;
    }
	
	
    /**
     * 修改
     * @return
     */
    @RequestMapping("/subscribe/edit")
    @ResponseBody
    public Map<String,Object> edit(HttpServletRequest request, RoadSubscribeEntity road) {
    	if(road.getSubsRemindWeek() == null){
    		road.setSubsRemindWeek("");
    	}
    	//计算时间
    	Date nowDateTime = WeixinUtils.getNowDateTime();
		Date nextSendDate = null;
		String time = road.getSubsRemindTimeHour();
		if(StringUtils.isBlank(time)){
			time = "09:00";
		}
		road.setSubsRemindHour(time.substring(0,time.indexOf(":")));
		road.setSubsRemindMinute(time.substring(time.indexOf(":")+1));
		if(road.getSubsRemindType() == 1){
			nextSendDate = WeixinUtils.getNextSendDate(true, nowDateTime, road.getSubsRemindWeek(), road.getSubsRemindHour(), road.getSubsRemindMinute());
		} else {
			nextSendDate = WeixinUtils.getNextSendDate(road.getSubsRemindDate(), road.getSubsRemindHour(), road.getSubsRemindMinute());
		}
		road.setSubsSendNextTimes(nextSendDate);
		road.setSubsSendTimes(WeixinUtils.getDateOfDays(nowDateTime, -1));
    	boolean result = roadSubscribeService.editRoadSubscribe(road);
    	int code = 0;
    	String msg = "成功！";
    	if(!result){
    		code = 1;
    		msg = "失败！";
    	}
    	Map<String,Object> resultMap = new HashMap<String, Object>();
    	resultMap.put("code", code);
    	resultMap.put("msg", msg);
    	return resultMap;
    }
    /**
     * 删除
     * @return
     */
    @RequestMapping("/subscribe/del")
    @ResponseBody
    public Map<String,Object> del(HttpServletRequest request) {
    	Long id = WeixinUtils.parseLong(request.getParameter("id"));
    	boolean result = roadSubscribeService.delRoadSubscribe(id);
    	int code = 0;
    	String msg = "成功！";
    	if(!result){
    		code = 1;
    		msg = "失败！";
    	}
    	Map<String,Object> resultMap = new HashMap<String, Object>();
    	resultMap.put("code", code);
    	resultMap.put("msg", msg);
    	return resultMap;
    }
    
    /**
     * 订阅列表
     * @param request
     * @param map
     * @return
     */
	@RequestMapping("/subscribe/details")
	public  String queryDetails(HttpServletRequest request,Map<String,Object> map){
		long subsId = WeixinUtils.parseLong(request.getParameter("subsId"));
		List<Map<String, Object>> videImgList = new ArrayList<Map<String, Object>>();
		String openId = null;
		try {
			List<RoadSubscribeEntity> subscribeList = roadSubscribeService.queryRoadSubscribeList(null, subsId, null, null, null);
			RoadSubscribeEntity subscribe = null;
			String videIds = null;
			if(subscribeList != null && subscribeList.size() > 0){
				subscribe = subscribeList.get(0);
				videIds = subscribe.getSubsImg();
				openId = subscribe.getSubsOpenId();
				if(StringUtil.isNotEmpty(videIds) && videIds.endsWith(",")){
					videIds = videIds.substring(0, videIds.length() - 1);
				}
				videImgList = videoImgService.queryVideoImgListMap(null,videIds, openId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put("subsId", subsId);
		map.put("videImgList", videImgList);
		map.put("openId", openId);
		map.put("dateTime", WeixinUtils.parseDateTimeStr(WeixinUtils.getNowDateTime()));
		return "roadSubscribe/subscribe";
	}
	
	public static void main(String[] args){
	}
}
