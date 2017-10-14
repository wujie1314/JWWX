package org.jiaowei.service;

import java.util.List;

import org.jiaowei.common.service.CommonService;
import org.jiaowei.entity.RoadSendMessageEntity;

public interface RoadSendMessageService extends CommonService {

	public List<Object[]> querySendMessageObjectList(String messName, String openId);
	
	public List<RoadSendMessageEntity> querySendMessageList(String messName, String openId);
	
	public boolean insertSendMessage(RoadSendMessageEntity entity);
	
	public boolean editSendMessage(RoadSendMessageEntity entity);
	
}
