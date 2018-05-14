package org.jiaowei.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jiaowei.entity.WxEntity;
import org.jiaowei.service.MeetingService;
import org.jiaowei.util.StringUtil;
import org.jiaowei.wxutil.ApiHttpUtils;
import org.jiaowei.wxutil.WeiXinConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MeetingController {

	private static Logger logger = Logger.getLogger(Login.class);
    @Autowired
    private MeetingService meetingService;
    
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
	@RequestMapping("/meeting/home")
	public  String queryList(HttpServletRequest request,Map<String,Object> map){
		String openId = request.getParameter("openId");
		String code = request.getParameter("code");
		if(StringUtil.isNotEmpty(code)){
			//获取openId
			openId = getOAuthOpenId(WeiXinConst.appId, WeiXinConst.appSecret, code); // appId 修改？？？？ openid
		}
		map.put("openId", openId);
		return "meeting/home";
	}
	
	
	/**
     * 查询列表返回json
     *
     * @return
     */
    @RequestMapping("/meeting/verify")
    public String query(HttpServletRequest request, Map<String,Object> map) {
    	String openId = request.getParameter("openId");
		String code = request.getParameter("code");
		String meetingType = request.getParameter("meetingType");
	    if (meetingType == null) {
	      meetingType = "";
	    }
		if(StringUtil.isNotEmpty(code)){
			//获取openId
			openId = getOAuthOpenId(WeiXinConst.appId, WeiXinConst.appSecret, code); // appId 修改？？？？// openid
		}
		map.put("openId", openId);
		map.put("meetingType", meetingType);
	    return "meeting/verify" + meetingType;
    }
	
   
    /**
     * 手机验证
     * @return
     */
    @RequestMapping("/meeting/mobileVerify")
    @ResponseBody
    public String delCollent(HttpServletRequest request) {
    	String mobile = request.getParameter("mobile");//0收藏、1热点、2高速、3站台、4意见
        String meetingType = request.getParameter("meetingType");
    	Map<String,Object> params = new HashMap<String, Object>();
    	params.put("meetPhone", mobile);
    	params.put("meetingType", meetingType);
    	int code = 0;
    	String msg = "成功！";
    	try {
    		List<Object[]> list = meetingService.queryObjectList(params);
    		if(list !=null && list.size() > 0){
    			
    		} else {
    			code = 1;
    			msg = "手机号码输入错误";
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		code = 1;
    		msg = "失败！";
    	}
    	return "{'code':'"+code+"','msg':'"+msg+"'}";
    }
    
    /**
     * 参会介绍
     *
     * @return
     */
    @RequestMapping("/meeting/introduce")
    public String introduce(HttpServletRequest request, Map<String,Object> map) {
		return "meeting/introduce";
    }
    /**
     * 会议议程
     *
     * @return
     */
    @RequestMapping("/meeting/agenda")
    public String agenda(HttpServletRequest request, Map<String,Object> map) {
    	return "meeting/agenda";
    }
    /**
     * 会议座次表
     *
     * @return
     */
    @RequestMapping("/meeting/seats")
    public String seats(HttpServletRequest request, Map<String,Object> map) {
    	return "meeting/seats";
    }
    /**
     * 资料下载
     *
     * @return
     */
    @RequestMapping("/meeting/datum")
    public String datum(HttpServletRequest request, Map<String,Object> map) {
    	return "meeting/datum";
    }
    /**
     * 查询列表返回json
     *
     * @return
     */
    @RequestMapping("/meeting/user")
    public String user(HttpServletRequest request, Map<String,Object> map) {
    	return "meeting/user";
    }
    /**
     * 查询列表返回json
     *
     * @return
     */
    @RequestMapping("/meeting/serviceCenter")
    public String serviceCenter(HttpServletRequest request, Map<String,Object> map) {
    	return "meeting/serviceCenter";
    }
    
    @RequestMapping({"/meeting/toPage"})
    public String pageJump(HttpServletRequest request, Map<String, Object> map)
    {
      String page = request.getParameter("page");
      return "meeting/pageJump" + page;
    }

}
