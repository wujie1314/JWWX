package org.jiaowei.mybatis.service.impl;


import org.jiaowei.mybatis.dao.BaseDao;
import org.jiaowei.mybatis.dao.RoadHtljDao;
import org.jiaowei.mybatis.service.RoadHtljService;
import org.jiaowei.mybatis.vo.RoadHtljVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoadHtljServiceImpl extends BaseServiceImpl<RoadHtljVo> implements RoadHtljService {

	@Autowired
	private RoadHtljDao roadHtljDao;
	
	@Override
	protected BaseDao<RoadHtljVo> getDao() {
		return roadHtljDao;
	}

}
