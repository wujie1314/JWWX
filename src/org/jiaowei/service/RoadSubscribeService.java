package org.jiaowei.service;

import java.util.Date;
import java.util.List;

import org.jiaowei.common.service.CommonService;
import org.jiaowei.entity.RoadSubscribeEntity;

public interface RoadSubscribeService extends CommonService {

	public List<Object[]> queryRoadSubscribeObjectList(String openId, Long id, String isStart, Date nextSendDate, Integer subsType);
	
	public List<RoadSubscribeEntity> queryRoadSubscribeList(String openId, Long id, String isStart, Date nextSendDate, Integer subsType);
	
	public boolean insertRoadSubscribe(RoadSubscribeEntity road);
	
	public boolean editRoadSubscribe(RoadSubscribeEntity road);
	
	public boolean delRoadSubscribe(Long id);
	
//	public Date getNextSendDate(Date nowDate,String subsRemindWeek, String hour, String minute);
//	public Date getNextSendDate(String subsRemindWeek, String hour, String minute);
}
