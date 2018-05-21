package org.jiaowei.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jiaowei.service.ExpertService;
import org.jiaowei.service.WriteAboutService;
import org.jiaowei.websoket.AppWebSocketHandler;
import org.jiaowei.wxutil.NavMenuInitUtils;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
    
	/*
	 *说说上传*
	 */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String imgUpload(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception {  
    	response.setContentType("text/html;charset=UTF-8");
    	String specialistOppenID = multipartRequest.getParameter("specialistOppenID"); 
    	String userOpenID = multipartRequest.getParameter("userOpenID");
    	String content = multipartRequest.getParameter("content").toString();  
    	String title  = multipartRequest.getParameter("title"); 
    	String name = multipartRequest.getParameter("name"); 
    	ArrayList<String> fileNames = new ArrayList<>();
    	//获取多个file  
    	for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {  
    		String key = (String) it.next();  
    		MultipartFile imgFile = multipartRequest.getFile(key);  
    		if (imgFile.getOriginalFilename().length() > 0) {  
    			String fileName = imgFile.getOriginalFilename();  
    			try {  
    				String uploadFileUrl = multipartRequest.getServletContext().getRealPath("/uploads");
    			    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
    			    String newFileName =  "IMAGE_" + String.valueOf(Calendar.getInstance().getTime().getTime()) + "." + prefix;
    				saveFileFromInputStream(imgFile.getInputStream(), uploadFileUrl, newFileName);
    				fileNames.add(newFileName);
    			} catch (Exception e) {  
    				e.printStackTrace();  
    			}  
    		}  
    	}  
    	return writeAboutService.specialist(multipartRequest,fileNames, specialistOppenID, content,name,title,userOpenID);
}  
    	  
    //保存文件  
    private void saveFileFromInputStream(InputStream stream, String path,  
    		String filename) throws IOException {  
    	File file = new File(path + "/" + filename);  
    	FileOutputStream fs = new FileOutputStream(file);  
    	byte[] buffer = new byte[1024 * 1024];  
    	int bytesum = 0;  
    	int byteread = 0;  
    	while ((byteread = stream.read(buffer)) != -1) {  
    		bytesum += byteread;  
    		fs.write(buffer, 0, byteread);  
    		fs.flush();  
    	}  
    	fs.close();  
    	stream.close();  
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
    		@RequestParam("phone")String phone,
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
        String baseUrl = basePath +"/bbs/jsp/particulars.jsp?tellID="+tellID;
        
        // 微信url
        String wxUrl = baseUrl +"&openID="+ openId;
        System.out.println(wxUrl);
        //获取对应的公众号
		String publicID =  NavMenuInitUtils.getInstance().userPublicIdMap.get(openId);
	
		String jsonContent = String.format("{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}",
				openId, "专家解答地址-><a href='"+wxUrl+"'>前去评论</a>");
		if(!openId.subSequence(0, 3).equals("app")){
			String returnStr = WeiXinOperUtil.sendMsgToWx(WeiXinOperUtil.getAccessToken(publicID), jsonContent);
	        JSONObject json = JSON.parseObject(returnStr);
	        if(!json.get("errcode").toString().equals("0")){//发送失败
	        	System.out.println("发送失败");
	        }
		}else{
			AppWebSocketHandler.sendMsgToApp(openId, jsonContent);
		}
		//发短信给专家
        String message = baseUrl +"%26openID="+ expertId;
        System.out.println(message);
        String url = rb.getString("notePath");
    	url = url.replace("telephone",phone);
    	url = url.replace("content", "专家解答地址，专家入口-> "+message);
    	System.out.println(url);
    	WeiXinOperUtil.callSendNote(url);
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
