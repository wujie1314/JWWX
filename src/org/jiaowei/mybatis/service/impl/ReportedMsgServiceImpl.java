package org.jiaowei.mybatis.service.impl;


import org.jiaowei.mybatis.dao.BaseDao;
import org.jiaowei.mybatis.dao.ReportedMsgDao;
import org.jiaowei.mybatis.service.ReportedMsgService;
import org.jiaowei.mybatis.vo.ReportedMsgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportedMsgServiceImpl extends BaseServiceImpl<ReportedMsgVo> implements ReportedMsgService {

	@Autowired
	private ReportedMsgDao reportedMsgDao;
	
	@Override
	protected BaseDao<ReportedMsgVo> getDao() {
		return reportedMsgDao;
	}

}
