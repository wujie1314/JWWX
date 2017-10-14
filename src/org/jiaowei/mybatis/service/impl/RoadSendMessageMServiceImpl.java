package org.jiaowei.mybatis.service.impl;


import org.jiaowei.mybatis.dao.BaseDao;
import org.jiaowei.mybatis.dao.RoadSendMessageDao;
import org.jiaowei.mybatis.service.RoadSendMessageService;
import org.jiaowei.mybatis.vo.RoadSendMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoadSendMessageMServiceImpl extends BaseServiceImpl<RoadSendMessageVo> implements RoadSendMessageService {

	@Autowired
	private RoadSendMessageDao roadSendMessageDao;
	
	@Override
	protected BaseDao<RoadSendMessageVo> getDao() {
		return roadSendMessageDao;
	}

}
