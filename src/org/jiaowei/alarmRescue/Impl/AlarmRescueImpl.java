package org.jiaowei.alarmRescue.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jiaowei.alarmRescue.controller.MysqlConn;
import org.jiaowei.alarmRescue.service.IAlarmRescueService;
import org.springframework.stereotype.Service;

@Service("alarmRescue")
public class AlarmRescueImpl implements IAlarmRescueService {	
	@Override
	public Map<String, Object> getAlarmInfo(String ID, String longitude, String latitude, String startPosition, String endPosition, String phoneNum, String repairReason) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		String content_desc = "方向  起点:"+startPosition+" 终点:"+endPosition+"  维修原因:"+repairReason;
		String contact_way = phoneNum;
		String jwd = longitude + "," + latitude;
		map.put("ID", ID);
		map.put("content_desc", content_desc);
		map.put("contact_way", contact_way);
		map.put("jwd", jwd);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = dateFormat.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		if(ID != null && !ID.isEmpty())
			map.put("update_time", date);			
		else
			map.put("create_time", date);
		System.out.println("==========="+date);
		MysqlConn.dataManipulation(map);
		return null;
	}

}
