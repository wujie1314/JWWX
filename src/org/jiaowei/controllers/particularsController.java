package org.jiaowei.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jiaowei.service.particularsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/particularsController")
public class particularsController {
	
	@Autowired
	private particularsService particularsService;
	
	 @RequestMapping(value = "/init")
	 @ResponseBody
	 public Map<String, Object> init(String tellID){
		return particularsService.init(tellID);
	}
	 
	 @RequestMapping(value = "/review")
	 @ResponseBody
	 public Map<String, Object> review(HttpServletRequest request,String tellID,String reviewData, String openID){
		return particularsService.review(request,tellID,reviewData,openID);
	}
}
