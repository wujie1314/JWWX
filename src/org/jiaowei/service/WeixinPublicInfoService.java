package org.jiaowei.service;

import org.hibernate.type.IntegerType;
import org.jiaowei.common.service.CommonService;

import java.util.Map;

public interface WeixinPublicInfoService extends CommonService{

	Map<String,Object> getPublicInfoById(String public_Id);

	Map<String,Object> getPublicInfoByDeptID(Integer deptID);

}
