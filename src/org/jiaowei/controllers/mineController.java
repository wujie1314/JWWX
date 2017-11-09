package org.jiaowei.controllers;

import java.util.Map;

import net.sf.json.JSONObject;

import org.jiaowei.service.mineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mineController")
public class mineController {
	 @Autowired
	 private mineService mineService;
	 
	 /*
	  * 初始化我的界面的信息
	  */
	 @RequestMapping(value = "/init")
	 @ResponseBody
	 public Map<String, Object> init(String openID){
		return mineService.init(openID);
	}
	 
	 /*
	  * 用戶
	  */
	 @RequestMapping(value = "/initUser")
	 @ResponseBody
	 public String initUser(String openID){
		return mineService.initUser(openID);
	}
	 
	 @RequestMapping(value = "/initTransportation")
	 @ResponseBody
	 public Map<String, Object> initTransportation(int begin, int end){
		return mineService.initTransportation(begin,end);
	}
	 
	 @RequestMapping(value = "/initSpecialist")
	 @ResponseBody
	 public Map<String, Object> initSpecialist(int begin, int end){
		return mineService.initSpecialist(begin,end);
	}
}
