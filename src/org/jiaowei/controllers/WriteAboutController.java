package org.jiaowei.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.jiaowei.entity.ExpertEntity;
import org.jiaowei.entity.MsgFromCustomerServiceEntity;
import org.jiaowei.entity.WxStatusTmpTEntity;
import org.jiaowei.service.ExpertService;
import org.jiaowei.service.WriteAboutService;
import org.jiaowei.wxutil.NavMenuInitUtils;
import org.jiaowei.wxutil.WeiXinConst;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/WriteAboutController")
public class WriteAboutController {
	
	private static ResourceBundle rb;
	static {
		rb = ResourceBundle.getBundle("weixin");
	}
	
	@Autowired
	private WriteAboutService writeAboutService;
	
	@Autowired ExpertService expertService;
	private List<String> imgFile;
	
	/*
	 *说说上传*
	 */
    @RequestMapping(value = "/announce")
    @ResponseBody
	public String announce(HttpServletRequest request,HttpServletResponse response){
    	List<String> list = new ArrayList<String>();
    	String oppenID = request.getParameter("oppenID");  
    	String content = request.getParameter("content").toString();  
    	String imgFile = request.getParameter("imgFile"); 
    	String title = request.getParameter("title"); 

    	JSONArray json = JSONArray.fromObject(imgFile);
    	for (int i = 0; i < json.size(); i++) {
			list.add((String) json.get(i));
		}
    	
    	System.out.println(list.size());
    	//System.out.println(" oppennID=" + oppenID + " content " + content);
		return writeAboutService.announce(request,list, oppenID, content, title);
	}
    
	/*
	 *说说上传*
	 */
    @RequestMapping(value = "/specialist")
    @ResponseBody
	public String specialist(HttpServletRequest request,HttpServletResponse response){
    	List<String> list = new ArrayList<String>();
    	String specialistOppenID = request.getParameter("specialistOppenID"); 
    	String userOpenID = request.getParameter("userOpenID");
    	String content = request.getParameter("content").toString();  
    	String imgFile = request.getParameter("imgFile"); 
    	String title  = request.getParameter("title"); 
    	String name = request.getParameter("name"); 
    	JSONArray json = JSONArray.fromObject(imgFile);
    	for (int i = 0; i < json.size(); i++) {
			list.add((String) json.get(i));
		}
    	System.out.println(list.size());
    	//System.out.println(" oppennID=" + oppenID + " content " + content);
		return writeAboutService.specialist(request,list, specialistOppenID, content,name,title,userOpenID);
	}
    
    /**
     * 推送链接给微信用户和给专家发短信
     * 
     * @param openId
     * @param expertId
     * @param tellID
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/sendLink")
    @ResponseBody
    public String sendLinkToWxAndExpert(@RequestParam("openId")String openId,
    		@RequestParam("expertId")String expertId,
    		@RequestParam("tellId")String tellID,
    		HttpServletRequest request,HttpServletResponse response){
    	if(openId == null || openId.isEmpty()){
    		return -1 +""; //微信用户ID为空
    	}
    	if(expertId == null || expertId.isEmpty()){
    		return -2 +""; //专家ID为空
    	}
    	if(tellID == null || tellID.isEmpty()){
    		return -3 +"" ;//帖子ID为空
    	}
    	
        String basePath = rb.getString("basePath");
        //拼接url链接
        String baseUrl = basePath +"/bbs/jsp/particulars.jsp?tellID="+tellID+"&openID=";
        
        // 微信url
        String wxUrl = baseUrl + openId;
        System.out.println(wxUrl);
        //获取对应的公众号
		String publicId =  NavMenuInitUtils.getInstance().userPublicIdMap.get(openId);
	
		String jsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
				openId, "专家解答地址-><a href='"+wxUrl+"'>专家解答</a>");
		
		String returnStr = 	WeiXinOperUtil.sendMsgToWx(WeiXinOperUtil.getAccessToken(publicId),jsonContent );
        JSONObject json = JSON.parseObject(returnStr);
        if(!json.get("errcode").toString().equals("0")){//发送失败
        	
        }
		//发短信给专家
		// 缺接口
    	
    	return 1 +"";
    }
    
    @RequestMapping(value="/expertInfo",method=RequestMethod.GET)
    @ResponseBody
    public String getExpertInfo(){
    	List<Object> result =  expertService.getExpertInfo();
    	String jsonString =  JSONObject.toJSON(result).toString();
    	System.out.println(jsonString);
    	return jsonString;
    }
    
	public List<String> getImgFile() {
		return imgFile;
	}

	public void setImgFile(List<String> imgFile) {
		this.imgFile = imgFile;
	}
	
	
	
}
