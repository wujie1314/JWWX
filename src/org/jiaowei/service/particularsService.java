package org.jiaowei.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jiaowei.common.service.CommonService;

public interface particularsService extends CommonService{

	Map<String, Object> init(String tellID);

	Map<String, Object> review(HttpServletRequest request, String tellID,
			String reviewData, String openID);
	
}
