package org.jiaowei.mybatis.service.impl;


import org.jiaowei.mybatis.dao.BaseDao;
import org.jiaowei.mybatis.dao.RoadSubscribeDao;
import org.jiaowei.mybatis.service.NewRoadSubscribeService;
import org.jiaowei.mybatis.vo.RoadSubscribeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewRoadSubscribeServiceImpl extends BaseServiceImpl<RoadSubscribeVo> implements NewRoadSubscribeService {

	@Autowired
	private RoadSubscribeDao roadSubscribeDao;
	
	@Override
	protected BaseDao<RoadSubscribeVo> getDao() {
		return roadSubscribeDao;
	}

}
