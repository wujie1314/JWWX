package org.jiaowei.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jiaowei.mybatis.service.ReportedMsgService;
import org.jiaowei.mybatis.vo.ReportedMsgVo;
import org.jiaowei.util.DateUtils;
import org.jiaowei.util.PKGenerator;
import org.jiaowei.util.StringUtil;
import org.jiaowei.wxutil.ApiHttpUtils;
import org.jiaowei.wxutil.PastUtil;
import org.jiaowei.wxutil.WeiXinConst;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 活动页
 * @author limingxing
 *
 */
@Controller
public class ActivityController {

	private static Logger logger = Logger.getLogger(Login.class);
    @Autowired
    private ReportedMsgService reportedMsgService;
    
    
    /**
     * 活动首页首页
     * @param request
     * @param map
     * @return
     */
	@RequestMapping("/activity/home")
	public  String home(HttpServletRequest request,Map<String,Object> map){
		return "activity/home";
	}
	/**
	 * 活动首页首页
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/activity/reportedMsg")
	public  String reportedMsg(HttpServletRequest request,Map<String,Object> map){
		int type = StringUtil.isNotEmpty(request.getParameter("type")) ? Integer.parseInt(request.getParameter("type")) : 0;
		try {
			String jsapi_ticket = WeiXinOperUtil.getJsApiTicket();
	        String url = PastUtil.getUrl(request);
	        Map<String, String> params = PastUtil.sign(jsapi_ticket, url);
	        params.put("appid", WeiXinConst.appId); // appId 修改？？？？
	        map.put("sign", params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取微信信息error:", e);
		}
		
		map.put("type", type);
		return "activity/reportedMsg";
	}

	/**
     * 新增
     * @return
     */
    @RequestMapping(value="/activity/insert" , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String insertCollent(HttpServletRequest request, ReportedMsgVo vo) {
		int code = 0;
		String msg = "成功！";
		try {
			vo.setRepoId(PKGenerator.generateKey());
			vo.setRepoCreateTime(DateUtils.getDate());
			reportedMsgService.insert(vo);
		} catch (Exception e) {
			e.printStackTrace();
			code = 1;
			msg = "失败！";
		}
        return "{\"code\":\""+code+"\",\"msg\":\""+msg+"\"}";
    }

    @RequestMapping("/activity/getImageSrc")
    @ResponseBody
    public String getImageSrc(HttpServletRequest request,String serverId) throws IOException{
    	String strResult=downloadImageFromWx(serverId,"/upload/activity", request);
        return strResult;
    }
    
    /**
     * 从腾讯服务器上下载图片到本地
     *
     * @param mediaId
     * @param request
     * @throws IOException
     */
    public static String downloadImageFromWx(String mediaId, String failPath, HttpServletRequest request) throws IOException {
        String url = "http://api.weixin.qq.com/cgi-bin/media/get?access_token=" + WeiXinOperUtil.getAccessToken() + "&media_id=" + mediaId;//  从腾讯服务器上下载图片到本地
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        response = httpclient.execute(httpGet);
        String contentType = response.getEntity().getContentType().toString().trim();
        if (!StringUtil.isEmpty(contentType)) {
            contentType = contentType.split(":")[1].trim();
            if ("image/jpeg".equals(contentType)) {
                HttpEntity entity = response.getEntity();
                String path = request.getRealPath("/") + File.separator + failPath;
//                String path = "/workspace/idea/weixin/src/main/webapp"+File.separator + "upload";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String imagePath = File.separator + mediaId + ".jpg";
                file = new File(path + imagePath);
                FileOutputStream fileout = new FileOutputStream(file);
                entity.writeTo(fileout);
                fileout.close();
                return failPath + imagePath;
            } else if ("audio/amr".equals(contentType)) {
                HttpEntity entity = response.getEntity();
                String path = request.getRealPath("/") + File.separator + failPath;
//                String path = "/workspace/idea/weixin/src/main/webapp"+File.separator + "upload";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String imagePath = File.separator + mediaId + ".amr";
                file = new File(path + imagePath);
                FileOutputStream fileout = new FileOutputStream(file);
                entity.writeTo(fileout);
                fileout.close();

                //转换文件格式
                String cmd =String.format("ffmpeg -i %s %s",path +File.separator + imagePath, path+File.separator+mediaId+".mp3");
                Process process = Runtime.getRuntime().exec(cmd);

                return failPath + mediaId + ".mp3";
            } else if ("video/mpeg4".equals(contentType)) {
                HttpEntity entity = response.getEntity();
                String path = request.getRealPath("/") + File.separator + failPath;
//                String path = "/workspace/idea/weixin/src/main/webapp"+File.separator + "upload";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }
                String filePath = File.separator + mediaId + ".mp4";
                file = new File(path + filePath);
                FileOutputStream fileout = new FileOutputStream(file);
                entity.writeTo(fileout);
                fileout.close();
//                //转换文件格式
//                String cmd =String.format("ffmpeg -i %s %s",path +File.separator + imagePath, path+File.separator+mediaId+".mp3");
//                System.out.println("cmd is : " + cmd);
//                Process process = Runtime.getRuntime().exec(cmd);
                return failPath + mediaId + ".mp4";
            }else {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }
        }
        return null;
    }
    
    @RequestMapping("/activity/console/home")
	public  String consoleHome(HttpServletRequest request,Map<String,Object> map){
		return "console/activity/list";
	}

    
}
