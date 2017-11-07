package org.jiaowei.controllers;

import java.util.Map;

import org.jiaowei.service.particularsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/particularsController")
public class particularsController {
	
	@Autowired
	private particularsService particularsService;
	
	@RequestMapping("/init")
	public Map<String, Object> init(String tellID){
		return particularsService.init(tellID);
	}
}
