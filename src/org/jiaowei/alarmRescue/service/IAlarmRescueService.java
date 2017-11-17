package org.jiaowei.alarmRescue.service;

import java.util.Map;

public interface IAlarmRescueService {
	public Map<String, Object> getAlarmInfo(String ID, String longitude, String latitude, String phoneNum, String repairReason);
}
