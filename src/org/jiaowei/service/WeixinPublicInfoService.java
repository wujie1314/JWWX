package org.jiaowei.service;

import org.hibernate.type.IntegerType;
import org.jiaowei.common.service.CommonService;
import org.jiaowei.entity.WeixinPublicInfoEntity;

import java.util.Map;

public interface WeixinPublicInfoService extends CommonService{

	WeixinPublicInfoEntity getPublicInfoById(String public_Id);
	
	Map<String,Object> getPublicInfoByDeptID(Integer deptID);

}
