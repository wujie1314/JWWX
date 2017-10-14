package org.jiaowei.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OthersController {

	
	@RequestMapping("/others/home")
	public String othersList(){
		
		return "others/home";
	}
}
