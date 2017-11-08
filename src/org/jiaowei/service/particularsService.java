package org.jiaowei.service;

import java.util.Map;

import org.jiaowei.common.service.CommonService;

public interface particularsService extends CommonService{

	Map<String, Object> init(String tellID);
	
}
