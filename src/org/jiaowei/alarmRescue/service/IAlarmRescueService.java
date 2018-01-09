package org.jiaowei.alarmRescue.service;

import java.util.Map;

public interface IAlarmRescueService {
	public Map<String, Object> getAlarmInfo(String ID, String currentLocation, String longitude, String latitude, String startPosition, String endPosition, String phoneNum, String repairReason);
}
