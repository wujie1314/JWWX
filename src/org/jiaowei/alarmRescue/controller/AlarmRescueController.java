package org.jiaowei.alarmRescue.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jiaowei.alarmRescue.service.IAlarmRescueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/alarmRescue")
public class AlarmRescueController {

	   @Resource
	   IAlarmRescueService service;
	   
	   @RequestMapping(value="/alarmRescueJSP")
		public String returnPage() {
			return "alarmRescue/alarmRescue";
		}
	   
	   @RequestMapping(value="/alarmRescue",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
		@ResponseBody
		public Map<String, Object> alarmRescue(@RequestParam(value = "ID",required=false) String ID,
				@RequestParam(value="currentLocation") String currentLocation,
				@RequestParam(value="longitude") String longitude,
				@RequestParam(value="latitude") String latitude,
				@RequestParam(value="startPosition",required=false) String startPosition,
				@RequestParam(value="endPosition",required=false) String endPosition,
				@RequestParam(value="contact_way",required=false) String phoneNum,
				@RequestParam(value="repairReason",required=false) String repairReason) throws UnsupportedEncodingException{
			
		   if(phoneNum != null && !phoneNum.isEmpty())
			   phoneNum=URLDecoder.decode(phoneNum,"utf-8");
		   if(currentLocation != null && !currentLocation.isEmpty())
			   currentLocation=URLDecoder.decode(currentLocation,"utf-8");
		   if(repairReason != null && !repairReason.isEmpty())
				repairReason=URLDecoder.decode(repairReason,"utf-8");
		   if(startPosition != null && !startPosition.isEmpty())
			   startPosition=URLDecoder.decode(startPosition,"utf-8");
		   if(endPosition != null && !endPosition.isEmpty())
			   endPosition=URLDecoder.decode(endPosition,"utf-8");
		
			Map<String, Object> result = service.getAlarmInfo(ID, currentLocation, longitude, latitude, startPosition, endPosition, phoneNum, repairReason);
			return result;
		}
	   
	   @RequestMapping(value = "/upload",method=RequestMethod.POST)
		@ResponseBody
		public String uploadFile(@RequestParam("file") MultipartFile[] file,
				@RequestParam("id") String id, HttpServletRequest req,
				HttpServletResponse response) throws IOException {
			PropertiesTool pe = new PropertiesTool();
			String imgPath = "";
			for (int i = 0; i < file.length; i++) {
				if (!file[i].isEmpty()) {
					String ID = EntityIDFactory.createId();// 文件ID
					String fileName = file[i].getOriginalFilename();// 获取文件全名
					String path = ""; // 实际文件存储路径
					String relativePath = "";// 文件的相对路径,加密后存入数据库
					String[] fileNames = fileName.split("\\.");// 将文件名以\.分割成一个数组
					String fileSuffixName = fileNames[fileNames.length - 1]
							.toLowerCase();
					if (fileSuffixName.equals("jpg") || fileSuffixName.equals("jpeg") || fileSuffixName.equals("bmp") || fileSuffixName.equals("svg") || fileSuffixName.equals("png") || fileSuffixName.equals("gif") || fileSuffixName.equals("mp4")
							 || fileSuffixName.equals("avi")|| fileSuffixName.equals("wmv") || fileSuffixName.equals("mpeg") || fileSuffixName.equals("mpg") || fileSuffixName.equals("rm") || fileSuffixName.equals("asf")) {
						path = pe.getSystemPram("imgPath") + "\\";
						for (int j = 0; j < fileNames.length; j++) {
							if (fileNames.length - j > 1) {
								path += fileNames[j];
								relativePath += fileNames[j];
							} else {
								path += "_" + ID + "." + fileNames[j];
								relativePath += "_" + ID + "." + fileNames[j];
							}
						}
						File targetFile = new File(path);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}

						try {
							file[i].transferTo(targetFile);
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						imgPath +=relativePath + ",";
					} else {
						return null;
					}
				}
			}
			imgPath = imgPath.substring(0,imgPath.length()-1);
			if (imgPath == "") {
				return "false";
			}
			else
				return "true";
		}
}
