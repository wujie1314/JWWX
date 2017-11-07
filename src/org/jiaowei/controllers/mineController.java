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
	 
	 @RequestMapping(value = "/init")
	 @ResponseBody
	 public Map<String, Object> init(String openID){
		return mineService.init(openID);
	}
	 
	 @RequestMapping(value = "/initUser")
	 @ResponseBody
	 public String initUser(String openID){
		return mineService.initUser(openID);
	}
}
