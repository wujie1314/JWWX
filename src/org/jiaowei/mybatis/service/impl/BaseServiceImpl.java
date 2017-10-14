package org.jiaowei.mybatis.service.impl;

import java.util.List;
import java.util.Map;

import org.jiaowei.mybatis.dao.BaseDao;
import org.jiaowei.mybatis.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class BaseServiceImpl<T> implements BaseService<T> {

	protected final static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	
	protected abstract BaseDao<T> getDao();


	@Override
	public int insert(T t) {
		return getDao().insert(t);
	}


	@Override
	public int update(T t) {
		return getDao().update(t);
	}


	@Override
	public int batchAdding(List<T> list) {
		return getDao().batchAdding(list);
	}


	@Override
	public int batchUpdate(List<T> list) {
		return getDao().batchUpdate(list);
	}


	@Override
	public int delete(Object id) {
		return getDao().delete(id);
	}


	@Override
	public int deleteByIds(Object[] ids) {
		return getDao().deleteByIds(ids);
	}


	@Override
	public T get(Object id) {
		return getDao().get(id);
	}


	@Override
	public T query(Map<String, Object> params) {
		return getDao().query(params);
	}


	@Override
	public int queryCount(Map<String, Object> params) {
		return getDao().queryCount(params);
	}


	@Override
	public List<T> queryList(Map<String, Object> params) {
		return getDao().queryList(params);
	}


	@Override
	public List<T> queryPageList(Map<String, Object> params) {
		return getDao().queryPageList(params);
	}

	

}
