package org.jiaowei.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.jiaowei.service.WriteAboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/WriteAboutController")
public class WriteAboutController {
	@Autowired
	private WriteAboutService writeAboutService;
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
    	JSONArray json = JSONArray.fromObject(imgFile);
    	for (int i = 0; i < json.size(); i++) {
			list.add((String) json.get(i));
		}
    	System.out.println(list.size());
    	//System.out.println(" oppennID=" + oppenID + " content " + content);
		return writeAboutService.announce(list, oppenID, content);
	}

	public List<String> getImgFile() {
		return imgFile;
	}

	public void setImgFile(List<String> imgFile) {
		this.imgFile = imgFile;
	}
}
