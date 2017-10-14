package org.jiaowei.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jiaowei.util.PKGenerator;
import org.jiaowei.wxutil.WeiXinOperUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {

    private static Logger logger = Logger.getLogger(FileUploadController.class);

    @RequestMapping(value = "/saveVoice")
    @ResponseBody
    public String saveVoice(@RequestParam("upload_file[filename]") MultipartFile file,
                           HttpServletRequest request) throws Exception {
        String uploadDir = request.getRealPath("/") + "/upload";
        File dirPath = new File(uploadDir);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        String sep = System.getProperty("file.separator");
        String fn = file.getOriginalFilename();
        String[] suffix = fn.split("\\.");
        String fileName = System.currentTimeMillis() + ".mp3" ;
        String fullPath = uploadDir + sep + fileName;
        File uploadedFile = new File(fullPath);
        //保存文件到服务器
        OutputStream os = new FileOutputStream(uploadedFile);
        os.write(file.getBytes());
        os.close();
        //上传文件到微信服务器
        String weiXinReturnString = null;
        weiXinReturnString = WeiXinOperUtil.uploadMedia(new File(fullPath), "voice");
        //TODO：
//        String tmp = "media_url:/upload/" +fileName;
//        weiXinReturnString.substring(0,weiXinReturnString.length())
        JSONObject obj = JSON.parseObject(weiXinReturnString);
        obj.put("media_url","/upload/"+fileName);
        weiXinReturnString = obj.toString();
        return weiXinReturnString;
    }

    @RequestMapping(value = "/save")
    public void saveFile(@RequestParam MultipartFile file,
                           HttpServletRequest request,HttpServletResponse response) throws Exception {
        String uploadDir = request.getRealPath("/") + "/upload";
        File dirPath = new File(uploadDir);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        String sep = System.getProperty("file.separator");
        String fn = file.getOriginalFilename();
        String[] suffix = fn.split("\\.");
        String fileName = System.currentTimeMillis() + "." + suffix[suffix.length - 1];
        String fullPath = uploadDir + sep + fileName;
        File uploadedFile = new File(fullPath);
        //保存文件到服务器
        OutputStream os = new FileOutputStream(uploadedFile);
        os.write(file.getBytes());
        os.close();
        //上传文件到微信服务器
        String weiXinReturnString = null;
        weiXinReturnString = WeiXinOperUtil.uploadMedia(new File(fullPath), "image");
        JSONObject json=new JSONObject();
        json.put("webData",fileName);
        json.put("wxData",weiXinReturnString);
        response.setContentType("text/html");
        response.getWriter().print(json.toJSONString());
        //return json.toJSONString();
    }

    public String saveImage(String path, String fileName, MultipartFile file) throws IOException {
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        OutputStream os = new FileOutputStream(path + "/" + fileName);
        os.write(file.getBytes());
        os.close();
        return path + fileName;
    }

    @SuppressWarnings("deprecation")
	@RequestMapping(value = "/saveActivityImage" , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String saveActivityImage(MultipartFile file, HttpServletRequest request) throws Exception {
    	int code = 0;
    	String msg = "成功！";
    	long fileId = PKGenerator.generateKey();
    	long one = System.currentTimeMillis();
    	System.out.println();
    	try {
    		String uploadDir = request.getRealPath("/") + "/upload/activity";
	        File dirPath = new File(uploadDir);
	        if (!dirPath.exists()) {
	            dirPath.mkdirs();
	        }
	        String fn = file.getOriginalFilename();
	        String[] suffix = fn.split("\\.");
	        String fileName = fileId + ".jpg";
	        String fullPath = uploadDir + "/" + fileName;
	        System.out.println("---->fullPath:"+fullPath);
	        File uploadedFile = new File(fullPath);
	        OutputStream os = new FileOutputStream(uploadedFile);
	        os.write(file.getBytes());
	        os.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("saveActivityImage is error!", e);
			code = 1;
			msg = "失败";
		}
    	long two = System.currentTimeMillis();
    	System.out.println("=====---------------====>"+(two-one));
        return "{\"code\":"+code+",\"msg\":\""+msg+"\",\"fileId\":\""+fileId+"\"}";
    }
    
}
