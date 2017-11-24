package org.jiaowei.service;

import java.util.List;

import org.jiaowei.common.service.CommonService;
import org.jiaowei.entity.WeixinAutoRespondEntity;

public interface WeixinAutoRespondService extends CommonService {

	List<WeixinAutoRespondEntity> getRespondMes(String content,Integer deptId);

	List<WeixinAutoRespondEntity> getRespondMenu();

	List<WeixinAutoRespondEntity> getJuniorMenu(String juniorID);

}
