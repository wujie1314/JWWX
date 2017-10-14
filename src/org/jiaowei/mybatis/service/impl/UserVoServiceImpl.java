package org.jiaowei.mybatis.service.impl;


import org.jiaowei.mybatis.dao.BaseDao;
import org.jiaowei.mybatis.dao.UserVoDao;
import org.jiaowei.mybatis.service.UserVoService;
import org.jiaowei.mybatis.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserVoServiceImpl extends BaseServiceImpl<UserVo> implements UserVoService {

	@Autowired
	private UserVoDao userVoDao;
	
	@Override
	protected BaseDao<UserVo> getDao() {
		return userVoDao;
	}

}
