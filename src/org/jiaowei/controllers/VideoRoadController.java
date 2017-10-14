package org.jiaowei.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jiaowei.entity.RoadLxfdEntity;
import org.jiaowei.entity.RoadSubscribeEntity;
import org.jiaowei.entity.WxEntity;
import org.jiaowei.service.RoadSubscribeService;
import org.jiaowei.service.VideoRoadService;
import org.jiaowei.util.PKGenerator;
import org.jiaowei.util.StringUtil;
import org.jiaowei.wxutil.ApiHttpUtils;
import org.jiaowei.wxutil.WeiXinConst;
import org.jiaowei.wxutil.WeixinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class VideoRoadController {

	private static Logger logger = Logger.getLogger(Login.class);
    @Autowired
    private VideoRoadService videoRoadService;
    @Autowired
    private RoadSubscribeService roadSubscribeService;
    
    public String getOAuthOpenId(String appid, String secret, String code ) {
        String openId = null;
        String o_auth_openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        String requestUrl = o_auth_openid_url.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx915ad295909bf037&secret=b26b2f2432620fb532d117fe6d132afa&code=031b09dca914ed8273b9f83ebf6298eh&grant_type=authorization_code";
        ApiHttpUtils client = new ApiHttpUtils(requestUrl);
		try {
			String result = client.doGet();
			ObjectMapper mapper = new ObjectMapper();
		    WxEntity wx =  mapper.readValue(result, WxEntity.class);
		    openId = wx.getOpenid();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---client-----error>",e);
		}
        return openId;
    }
    
    /**
     * 图片路况首页
     * @param request
     * @param map
     * @return
     */
	@RequestMapping("/videoRoad/home")
	public  String queryList(HttpServletRequest request,Map<String,Object> map){
		String openId = request.getParameter("openId");
		String code = request.getParameter("code");
		if(StringUtil.isNotEmpty(code)){
			//获取openId
			openId = getOAuthOpenId(WeiXinConst.appId, WeiXinConst.appSecret, code);
		}
		map.put("openId", openId);
		return "videoRoad/home";
	}
	/**
	 * 城区
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoRoad/city")
	public  String city(HttpServletRequest request,Map<String,Object> map){
		String openId = request.getParameter("openId");
		String code = request.getParameter("code");
		if(StringUtil.isNotEmpty(code)){
			//获取openId
			openId = getOAuthOpenId(WeiXinConst.appId, WeiXinConst.appSecret, code);
		}
		map.put("openId", openId);
		return "videoRoad/city";
	}
	/**
	 * 高速
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoRoad/high_speed")
	public  String high_speed(HttpServletRequest request,Map<String,Object> map){
		String openId = request.getParameter("openId");
		String code = request.getParameter("code");
		if(StringUtil.isNotEmpty(code)){
			//获取openId
			openId = getOAuthOpenId(WeiXinConst.appId, WeiXinConst.appSecret, code);
		}
		map.put("openId", openId);
		return "videoRoad/high_speed";
	}
	/**
	 * 高速二级页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoRoad/high_speed_next")
	public  String high_speed_next(HttpServletRequest request,Map<String,Object> map){
		String openId = request.getParameter("openId");
		String parentId = request.getParameter("parentId");
		String parentName = request.getParameter("parentName");
		try {
			parentName = URLDecoder.decode(parentName,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String code = request.getParameter("code");
		if(StringUtil.isNotEmpty(code)){
			//获取openId
			openId = getOAuthOpenId(WeiXinConst.appId, WeiXinConst.appSecret, code);
		}
		map.put("openId", openId);
		map.put("parentId", parentId);
		map.put("parentName", parentName);
		return "videoRoad/high_speed_next";
	}
	
	/**
	 * 车站页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoRoad/station")
	public  String station(HttpServletRequest request,Map<String,Object> map){
		String openId = request.getParameter("openId");
		String code = request.getParameter("code");
		if(StringUtil.isNotEmpty(code)){
			//获取openId
			openId = getOAuthOpenId(WeiXinConst.appId, WeiXinConst.appSecret, code);
		}
		map.put("openId", openId);
		return "videoRoad/station";
	}
	
	/**
	 * 高速二级页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoRoad/station_next")
	public  String station_next(HttpServletRequest request,Map<String,Object> map){
		String openId = request.getParameter("openId");
		String parentId = request.getParameter("parentId");
		String parentName = request.getParameter("parentName");
		try {
			parentName = URLDecoder.decode(parentName,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String code = request.getParameter("code");
		if(StringUtil.isNotEmpty(code)){
			//获取openId
			openId = getOAuthOpenId(WeiXinConst.appId, WeiXinConst.appSecret, code);
		}
		map.put("openId", openId);
		map.put("parentId", parentId);
		map.put("parentName", parentName);
		return "videoRoad/station_next";
	}
	
	/**
	 * 路况页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoRoad/road")
	public  String road(HttpServletRequest request,Map<String,Object> map){
		String openId = request.getParameter("openId");
		String code = request.getParameter("code");
		if(StringUtil.isNotEmpty(code)){
			//获取openId
			openId = getOAuthOpenId(WeiXinConst.appId, WeiXinConst.appSecret, code);
		}
		map.put("openId", openId);
		return "videoRoad/road";
	}
	
	/**
	 * 路况页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoRoad/appRoad")
	public  String appRoad(HttpServletRequest request,Map<String,Object> map){
		return "videoRoad/app/road";
	}
	
	
	/**
	 * 进入详情页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoRoad/details")
	public  String queryDetails(HttpServletRequest request,Map<String,Object> map){
		Long videId = StringUtil.isNotEmpty(request.getParameter("videId")) ? Long.parseLong(request.getParameter("videId")) : null;
		String openId = request.getParameter("openId");
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		List<Map<String, Object>> mapList = videoRoadService.queryVideoImgListMap(null, null, null, null, videId, openId, null);
//		if(mapList != null && mapList.size() > 0){
//			resultMap = mapList.get(0);
//		}
		map.put("videId", videId);
//		map.put("videoImg", resultMap);
		map.put("openId", openId);
		return "videoRoad/details";
	}
	
	/**
     * 查询列表返回json
     *
     * @return
     */
    @RequestMapping("/videoRoad/query")
    @ResponseBody
    public Map<String, Object> query(HttpServletRequest request) {
    	Long videId = StringUtil.isNotEmpty(request.getParameter("videId")) ? Long.parseLong(request.getParameter("videId")) : null;
		String openId = request.getParameter("openId");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = videoRoadService.queryVideoImgListMap(null, null, null, null, videId, openId, null);
		if(mapList != null && mapList.size() > 0){
			resultMap = mapList.get(0);
		}
        return resultMap;
    }
	
    /**
     * 查询列表返回json
     *
     * @return
     */
    @RequestMapping("/videoRoad/queryList")
    @ResponseBody
    public List<Map<String, Object>> queryList(HttpServletRequest request) {
    	Integer type = StringUtil.isNotEmpty(request.getParameter("type")) ? Integer.parseInt(request.getParameter("type")) : null;//2高速、3站台、4城区
		String keyword = request.getParameter("keyword");
		Long parentId = StringUtil.isNotEmpty(request.getParameter("parentId")) ? Long.parseLong(request.getParameter("parentId")) : null;
		String openId = request.getParameter("openId");
		String isCollect = request.getParameter("isCollect");//是否收藏
		List<Map<String, Object>> videoList = new ArrayList<Map<String, Object>>();
		try {
			videoList = videoRoadService.queryVideoImgListMap(type, keyword, parentId, null, null, openId, isCollect);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return videoList;
    }
   
    /**
     * 收藏
     * @return
     */
    @RequestMapping("/videoRoad/insertCollent")
    @ResponseBody
    public String insertCollent(HttpServletRequest request) {
    	String videId = request.getParameter("videId");//0收藏、1热点、2高速、3站台、4意见
		String openId = request.getParameter("openId");
		Map<String,Object> params = new HashMap<String, Object>();
		String sql = "insert into video_img_collect(collid,collvideid,collwxopenid) values('"+PKGenerator.generateKey()+"','"+videId+"','"+openId+"')";
		params.put("collId", PKGenerator.generateKey());
		params.put("videId", videId);
		params.put("openId", openId);
		int code = 0;
		String msg = "成功！";
		try {
			videoRoadService.ExecSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
			code = 1;
			msg = "失败！";
		}
        return "{'code':'"+code+"','msg':'"+msg+"'}";
    }
    /**
     * 收藏
     * @return
     */
    @RequestMapping("/videoRoad/delCollent")
    @ResponseBody
    public String delCollent(HttpServletRequest request) {
    	String videId = request.getParameter("videId");//0收藏、1热点、2高速、3站台、4意见
    	String openId = request.getParameter("openId");
    	Map<String,Object> params = new HashMap<String, Object>();
    	String sql = "delete video_img_collect where collvideid = :videId and collwxopenid = :openId";
    	params.put("videId", videId);
    	params.put("openId", openId);
    	int code = 0;
    	String msg = "成功！";
    	try {
    		videoRoadService.ExecSQL(sql,params);
    	} catch (Exception e) {
    		e.printStackTrace();
    		code = 1;
    		msg = "失败！";
    	}
    	return "{'code':'"+code+"','msg':'"+msg+"'}";
    }
    
    
    /**
	 * 进入详情页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoRoad/mySubscribe")
	public  String mySubscribe(HttpServletRequest request,Map<String,Object> map){
		String openId = request.getParameter("openId");
		map.put("openId", openId);
		return "videoRoad/mySubscribe";
	}

	 /**
     * 查询列表返回json
     *
     * @return
     */
    @RequestMapping("/videoRoad/queryMySubscribe")
    @ResponseBody
    public List<RoadSubscribeEntity> queryMySubscribe(HttpServletRequest request) {
    	String openId = request.getParameter("openId");
    	List<RoadSubscribeEntity> subscribeList = new ArrayList<RoadSubscribeEntity>();
    	try {
    		subscribeList = roadSubscribeService.queryRoadSubscribeList(openId, null, null, null, null);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return subscribeList;
    }

    /**
	 * 添加文字订阅
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoRoad/addTextSubscribe")
	public  String addTextSubscribe(HttpServletRequest request,Map<String,Object> map){
		String openId = request.getParameter("openId");
		map.put("openId", openId);
		RoadSubscribeEntity road = new RoadSubscribeEntity();
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			//查询用户订阅的路段
			List<RoadSubscribeEntity> list2 = roadSubscribeService.queryRoadSubscribeList(null, Long.parseLong(id), null, null, null);
			for(int i=0;i<list2.size();i++){
				road = list2.get(i);
				if(road.getSubsTitleName().equals("文字"))break;
			}
			if(road.getSubsTitleName()!=null){
				if(road.getSubsRemindDate() == null){
					road.setSubsRemindDate(WeixinUtils.parseDateStr(WeixinUtils.getNowDate()));
				}
				road.setSubsRemindTimeHour(road.getSubsRemindHour() + ":" + road.getSubsRemindMinute());
			}else{
				road = new RoadSubscribeEntity();
				road.setSubsRemindDate(WeixinUtils.parseDateStr(WeixinUtils.getNowDate()));
				road.setSubsRemindTimeHour("09:00");
			}
		} else {
			road.setSubsRemindDate(WeixinUtils.parseDateStr(WeixinUtils.getNowDate()));
			road.setSubsRemindTimeHour("09:00");
		}
		map.put("road", road);
		map.put("sectionList", querySectionList());
		return "videoRoad/saveTextSubscribe";
	}
	
	private List<RoadLxfdEntity> querySectionList(){
		List<RoadLxfdEntity> list=new ArrayList<RoadLxfdEntity>();
		StringBuffer sql=new StringBuffer();
    	sql.append(" SELECT ROAD_CODE,ROAD_NAME,LD_NAME,ROAD_DIR,START_NAME,END_NAME,DESCRIPT,ROAD_CODE1");
    	sql.append(" FROM ROAD_LXFD");
    	sql.append(" ORDER BY ROAD_CODE1 ASC");
    	List<Object> nodeList=roadSubscribeService.findBySQL(sql.toString());
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
        		entity.setRoadCode1(StringUtil.nullObject2String(obj[7]));
        		list.add(entity);
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
	}
	
	/**
     * 查询路段列表
     *
     * @return
     */
    @RequestMapping("/videoRoad/querySectionList")
    @ResponseBody
    public List<RoadLxfdEntity> querySectionList(HttpServletRequest request) {
    	List<RoadLxfdEntity> list=new ArrayList<RoadLxfdEntity>();

		StringBuffer sql=new StringBuffer();
    	sql.append(" SELECT ROAD_CODE,ROAD_NAME,LD_NAME,ROAD_DIR,START_NAME,END_NAME,DESCRIPT,ROAD_CODE1");
    	sql.append(" FROM ROAD_LXFD");
    	sql.append(" ORDER BY ROAD_CODE1 ASC");
    	List<Object> nodeList=roadSubscribeService.findBySQL(sql.toString());
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
        		entity.setRoadCode1(StringUtil.nullObject2String(obj[7]));
        		list.add(entity);
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
    }
    
    /**
	 * 初始化图片订阅
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoRoad/saveImgSubscribe")
	public  String saveImgSubscribe(HttpServletRequest request,Map<String,Object> map){
		String openId = request.getParameter("openId");
		map.put("openId", openId);
		RoadSubscribeEntity road = new RoadSubscribeEntity();
		//查询用户收藏的图片
		List<Map<String, Object>> videoList = videoRoadService.queryVideoImgListMap(null, null, null, null, null, openId, "true");
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			//查询用户订阅的路段
			List<RoadSubscribeEntity> list2 = roadSubscribeService.queryRoadSubscribeList(null, Long.parseLong(id), null, null, null);
			for(int i=0;i<list2.size();i++){
				road = list2.get(i);
			}
			if(road.getSubsTitleName()!=null){
				if(road.getSubsRemindDate() == null){
					road.setSubsRemindDate(WeixinUtils.parseDateStr(WeixinUtils.getNowDate()));
				}
				road.setSubsRemindTimeHour(road.getSubsRemindHour() + ":" + road.getSubsRemindMinute());
			}else{
				road = new RoadSubscribeEntity();
				road.setSubsRemindDate(WeixinUtils.parseDateStr(WeixinUtils.getNowDate()));
				road.setSubsRemindTimeHour("09:00");
			}
		} else {
			road.setSubsRemindDate(WeixinUtils.parseDateStr(WeixinUtils.getNowDate()));
			road.setSubsRemindTimeHour("09:00");
		}
		map.put("road", road);
		map.put("videoList", videoList);
		return "videoRoad/saveImgSubscribe";
	}
}
