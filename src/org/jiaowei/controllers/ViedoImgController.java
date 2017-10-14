package org.jiaowei.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jiaowei.entity.RoadSubscribeEntity;
import org.jiaowei.entity.WxEntity;
import org.jiaowei.service.RoadSubscribeService;
import org.jiaowei.service.VideoImgService;
import org.jiaowei.util.PKGenerator;
import org.jiaowei.util.StringUtil;
import org.jiaowei.wxutil.ApiHttpUtils;
import org.jiaowei.wxutil.WeiXinConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ViedoImgController {

	private static Logger logger = Logger.getLogger(Login.class);
    @Autowired
    private VideoImgService videoImgService;
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
     * 查询列表
     * @param request
     * @param map
     * @return
     */
	@RequestMapping("/videoImg/list")
	public  String queryList(HttpServletRequest request,Map<String,Object> map){
		String type = request.getParameter("type");//0收藏、1热点、2高速、3站台、4意见
		String keyword = request.getParameter("keyword");
		String openId = request.getParameter("openId");
		String code = request.getParameter("code");
		Long parentId = StringUtil.isNotEmpty(request.getParameter("parentId")) ? Long.parseLong(request.getParameter("parentId")) : null;
		List<Map<String, Object>> videoList = new ArrayList<Map<String, Object>>();
		if(StringUtil.isNotEmpty(code)){
			//获取openId
			openId = getOAuthOpenId(WeiXinConst.appId, WeiXinConst.appSecret, code);
		}
		if(StringUtil.isEmpty(type)){
			type = "2";
		}
		try {
			if(StringUtil.isNotEmpty(keyword)){
				keyword = URLDecoder.decode(keyword, "utf-8");
			}
			
			if("2".equals(type) || "3".equals(type) || StringUtil.isEmpty(type)){
				parentId = -1l;
			}
			videoList = videoImgService.queryVideoImgListMap(type,keyword, parentId, openId);
			if("0".equals(type) && StringUtil.isNotEmpty(openId)){
				List<RoadSubscribeEntity> subscribeList = roadSubscribeService.queryRoadSubscribeList(openId, null, null, null, null);
				map.put("subscribeList", subscribeList);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		map.put("type", type);
		map.put("keyword", keyword);
		map.put("parentId", parentId);
		map.put("videoList", videoList);
		map.put("openId", openId);
		
		return "videoImg/list";
//		return "videoRoad/list";
	}
	
	 /**
     * 车站
     * @param request
     * @param map
     * @return
     */
	@RequestMapping("/videoImg/stationListApp")
	public  String stationListApp(HttpServletRequest request,Map<String,Object> map){
		String type = request.getParameter("type");//0收藏、1热点、2高速、3站台、4意见
		String keyword = request.getParameter("keyword");
		String openId = request.getParameter("openId");
		Long parentId = StringUtil.isNotEmpty(request.getParameter("parentId")) ? Long.parseLong(request.getParameter("parentId")) : null;
		List<Map<String, Object>> videoList = new ArrayList<Map<String, Object>>();
		if(StringUtil.isEmpty(type)){
			type = "3";
		}
		try {
			if(StringUtil.isNotEmpty(keyword)){
				keyword = URLDecoder.decode(keyword, "utf-8");
			}
			
			if("2".equals(type) || "3".equals(type) || StringUtil.isEmpty(type)){
				parentId = -1l;
			}
			videoList = videoImgService.queryVideoImgListMap(type,keyword, parentId, openId);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		map.put("type", type);
		map.put("videoList", videoList);
		return "videoImg/app/station";
	}
	
	/**
     * 车站
     * @param request
     * @param map
     * @return
     */
	@RequestMapping("/videoImg/highListApp")
	public  String highListApp(HttpServletRequest request,Map<String,Object> map){
		String type = request.getParameter("type");//0收藏、1热点、2高速、3站台、4意见
		String keyword = request.getParameter("keyword");
		String openId = request.getParameter("openId");
		Long parentId = StringUtil.isNotEmpty(request.getParameter("parentId")) ? Long.parseLong(request.getParameter("parentId")) : null;
		List<Map<String, Object>> videoList = new ArrayList<Map<String, Object>>();
		if(StringUtil.isEmpty(type)){
			type = "2";
		}
		try {
			if(StringUtil.isNotEmpty(keyword)){
				keyword = URLDecoder.decode(keyword, "utf-8");
			}
			
			if("2".equals(type) || "3".equals(type) || StringUtil.isEmpty(type)){
				parentId = -1l;
			}
			videoList = videoImgService.queryVideoImgListMap(type,keyword, parentId, openId);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		map.put("type", type);
		map.put("videoList", videoList);
		return "videoImg/app/high";
	}
	
	/**
	 * 查询列表
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoImg/listapp")
	public  String listapp(HttpServletRequest request,Map<String,Object> map){
		String type = request.getParameter("type");//0收藏、1热点、2高速、3站台、4意见
		String keyword = request.getParameter("keyword");
		Long parentId = StringUtil.isNotEmpty(request.getParameter("parentId")) ? Long.parseLong(request.getParameter("parentId")) : null;
		List<Map<String, Object>> videoList = new ArrayList<Map<String, Object>>();
		if(StringUtil.isEmpty(type)){
			type = "2";
		}
		try {
			if(StringUtil.isNotEmpty(keyword)){
				keyword = URLDecoder.decode(keyword, "utf-8");
			}
			
			if("2".equals(type) || "3".equals(type) || StringUtil.isEmpty(type)){
				parentId = -1l;
			}
			videoList = videoImgService.queryVideoImgListMap(type,keyword, parentId, null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		map.put("type", type);
		map.put("keyword", keyword);
		map.put("parentId", parentId);
		map.put("videoList", videoList);
		
		return "videoImg/app/list";
	}
	

	/**
	 * 进入二级页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoImg/nextList")
	public  String queryNextList(HttpServletRequest request,Map<String,Object> map){
		String type = request.getParameter("type");//0收藏、1热点、2高速、3站台、4意见
		String keyword = request.getParameter("keyword");
		Long parentId = StringUtil.isNotEmpty(request.getParameter("parentId")) ? Long.parseLong(request.getParameter("parentId")) : null;
		String parentName = request.getParameter("parentName");
		String openId = request.getParameter("openId");
		try {
			if(StringUtil.isNotEmpty(parentName)){
				parentName = URLDecoder.decode(parentName, "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> videoList = videoImgService.queryVideoImgListMap(type,keyword, parentId, openId);
		
		map.put("type", type);
		map.put("keyword", keyword);
		map.put("parentId", parentId);
		map.put("parentName", parentName);
		map.put("videoList", videoList);
		map.put("openId", openId);
		return "videoImg/nextList";
	}
	
	/**
	 * 进入二级页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoImg/nextListApp")
	public  String queryNextListApp(HttpServletRequest request,Map<String,Object> map){
		String type = request.getParameter("type");//0收藏、1热点、2高速、3站台、4意见
		String keyword = request.getParameter("keyword");
		Long parentId = StringUtil.isNotEmpty(request.getParameter("parentId")) ? Long.parseLong(request.getParameter("parentId")) : null;
		String parentName = request.getParameter("parentName");
		String openId = request.getParameter("openId");
		try {
			if(StringUtil.isNotEmpty(parentName)){
				parentName = URLDecoder.decode(parentName, "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> videoList = videoImgService.queryVideoImgListMap(type,keyword, parentId, openId);
		
		map.put("type", type);
		map.put("keyword", keyword);
		map.put("parentId", parentId);
		map.put("parentName", parentName);
		map.put("videoList", videoList);
		map.put("openId", openId);
		return "videoImg/app/nextList";
	}
	/**
	 * 进入详情页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoImg/details")
	public  String queryDetails(HttpServletRequest request,Map<String,Object> map){
		String type = request.getParameter("type");
		Long videId = StringUtil.isNotEmpty(request.getParameter("videId")) ? Long.parseLong(request.getParameter("videId")) : null;
		String openId = request.getParameter("openId");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = videoImgService.queryVideoImgListMap(new Integer(type), null, -2l, null, videId, openId, null);
		if(mapList != null && mapList.size() > 0){
			resultMap = mapList.get(0);
		}
		map.put("videId", videId);
		map.put("videoImg", resultMap);
		map.put("openId", openId);
		return "videoImg/details";
	}
	/**
	 * 进入详情页面
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/videoImg/detailsApp")
	public  String queryDetailsApp(HttpServletRequest request,Map<String,Object> map){
		String type = request.getParameter("type");
		Long videId = StringUtil.isNotEmpty(request.getParameter("videId")) ? Long.parseLong(request.getParameter("videId")) : null;
		String openId = request.getParameter("openId");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = videoImgService.queryVideoImgListMap(new Integer(type), null, -2l, null, videId, openId, null);
		if(mapList != null && mapList.size() > 0){
			resultMap = mapList.get(0);
		}
		map.put("videId", videId);
		map.put("videoImg", resultMap);
		map.put("openId", openId);
		return "videoImg/app/details";
	}
	
    /**
     * 查询列表返回json
     *
     * @return
     */
    @RequestMapping("/videoImg/queryList")
    @ResponseBody
    public List<Map<String, Object>> query(HttpServletRequest request) {
    	String type = request.getParameter("type");//0收藏、1热点、2高速、3站台、4意见
		String keyword = request.getParameter("keyword");
		Long parentId = StringUtil.isNotEmpty(request.getParameter("parentId")) ? Long.parseLong(request.getParameter("parentId")) : null;
		String openId = request.getParameter("openId");
		List<Map<String, Object>> videoList = videoImgService.queryVideoImgListMap(type,keyword, parentId, openId);
        return videoList;
    }

    /**
     * 收藏
     * @return
     */
    @RequestMapping("/videoImg/insertCollent")
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
			videoImgService.ExecSQL(sql);
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
    @RequestMapping("/videoImg/delCollent")
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
    		videoImgService.ExecSQL(sql,params);
    	} catch (Exception e) {
    		e.printStackTrace();
    		code = 1;
    		msg = "失败！";
    	}
    	return "{'code':'"+code+"','msg':'"+msg+"'}";
    }

}
