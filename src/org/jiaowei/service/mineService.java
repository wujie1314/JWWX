package org.jiaowei.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jiaowei.common.service.CommonService;

public interface mineService extends CommonService{

	List<Object> initTransportation(int begin, int end);

	List<Object> initSpecialist(int begin, int end);

	String initUser(HttpServletRequest request, String openID);

	List<Object> init(String openID, int begin, int end);
	
}
