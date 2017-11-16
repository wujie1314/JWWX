package org.jiaowei.service;

import java.util.Map;

import org.jiaowei.common.service.CommonService;

public interface mineService extends CommonService{

	Map<String, Object> init(String openID);

	String initUser(String openID);

	Map<String, Object> initTransportation(int begin, int end);

	Map<String, Object> initSpecialist(int begin, int end);
	
}
