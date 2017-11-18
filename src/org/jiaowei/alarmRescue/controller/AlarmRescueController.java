package org.jiaowei.alarmRescue.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.annotation.Resource;

import org.jiaowei.alarmRescue.service.IAlarmRescueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		public Map<String, Object> alarmRescue(@RequestParam(value = "ID") String ID,
				@RequestParam(value="longitude") String longitude,
				@RequestParam(value="latitude") String latitude,
				@RequestParam(value="startPosition",required=false) String startPosition,
				@RequestParam(value="endPosition",required=false) String endPosition,
				@RequestParam(value="phoneNum",required=false) String phoneNum,
				@RequestParam(value="repairReason",required=false) String repairReason) throws UnsupportedEncodingException{
			
		   if(phoneNum != null && !phoneNum.isEmpty())
			   phoneNum=URLDecoder.decode(phoneNum,"utf-8");
		   if(repairReason != null && !repairReason.isEmpty())
				repairReason=URLDecoder.decode(repairReason,"utf-8");
		   if(startPosition != null && !startPosition.isEmpty())
			   startPosition=URLDecoder.decode(startPosition,"utf-8");
		   if(endPosition != null && !endPosition.isEmpty())
			   endPosition=URLDecoder.decode(endPosition,"utf-8");
		
			System.out.println("=========="+phoneNum + repairReason+startPosition);
			Map<String, Object> result = service.getAlarmInfo(ID, longitude, latitude, startPosition, endPosition, phoneNum, repairReason);
			return result;
		}
}
