package org.jiaowei.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	 public List<Object> init(String openID,int num, int size){
		int begin = (num-1) * size;
		int end =    num* size;
		return mineService.init(openID,begin,end);
	}
	 
	 /*
	  * 用戶
	  */
	 @RequestMapping(value = "/initUser")
	 @ResponseBody
	 public String initUser(HttpServletRequest request,String openID){
		return mineService.initUser(request,openID);
	}
	 
	 @RequestMapping(value = "/initTransportation")
	 @ResponseBody
	 public List<Object> initTransportation(int begin, int end){
		return mineService.initTransportation(begin,end);
	}
	 
	 @RequestMapping(value = "/initSpecialist")
	 @ResponseBody
	 public List<Object> initSpecialist(int begin, int end){
		return mineService.initSpecialist(begin,end);
	}
}
