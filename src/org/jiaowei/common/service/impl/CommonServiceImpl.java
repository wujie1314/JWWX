package org.jiaowei.common.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.criterion.Criterion;
import org.jiaowei.common.dao.CommonDao;
import org.jiaowei.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {

	@Autowired
	public CommonDao commonDao;


	@Override
	public <T> Serializable save(T entity) {
		return commonDao.save(entity);
	}

	@Override
	public <T> void batchSave(List<T> entitys) {
		commonDao.batchSave(entitys);
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		commonDao.saveOrUpdate(entity);
	}

	@Override
	public <T> void delete(T entity) {
		commonDao.delete(entity);
	}

	@Override
	public <T> List<T> loadAll(Class<T> entityClass) {
		return commonDao.loadAll(entityClass);
	}


	@Override
	public <T> T get(Class<T> entityName, Serializable id) {
		return commonDao.get(entityName, id);
	}

	@Override
	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
		return commonDao.findByProperty(entityClass, propertyName, value);
	}

	@Override
	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value) {
		return commonDao.findUniqueByProperty(entityClass, propertyName, value);
	}

	@Override
	public <T> void deleteEntityById(Class<T> entityClassName, Serializable id) {
		commonDao.deleteEntityById(entityClassName, id);
	}

	@Override
	public <T> void deleteAllEntities(Collection<T> entities) {
		commonDao.deleteAllEntities(entities);
	}

	@Override
	public <T> void updateEntity(T entity) {
		commonDao.updateEntity(entity);
	}

	@Override
	public <T> List<T> findByMap(Class<T> className, Map<String, Object> map) {
		return commonDao.findByMap(className, map);
	}

	@Override
	public <T> List<T> findByCriteria(Class<T> className, List<Criterion> criterions) {
		return commonDao.findByCriteria(className, criterions);
	}

	@Override
	public <T> List<T> findByCriteria(Class<T> className, List<Criterion> criterions, String orderPropertyName, boolean isAsc) {
		return commonDao.findByCriteria(className,criterions,orderPropertyName,isAsc);
	}

	@Override
	public <T> List<T> findByCriteriaPaging(Class<T> className, Map<String, Object> params, String orderPropertyName, boolean isAsc, int star, int end) {
		return commonDao.findByCriteriaPaging(className, params, orderPropertyName, isAsc, star, end);
	}

	@Override
	public <T> List<T> findByCriteriaPaging(Class<T> className, Map<String, Object> params, String orderPropertyName1, boolean isAsc1, String orderPropertyName2, boolean isAsc2, int star, int end) {
		return commonDao.findByCriteriaPaging(className,params,orderPropertyName1,isAsc1,orderPropertyName2,isAsc2,star,end);
	}

	@Override
	public <T> List<T> findByCriteriaPaging(Class<T> className, Map<String, Object> params, Map<String, Object> sortMap, int star, int end) {
		return commonDao.findByCriteriaPaging(className,params,sortMap,star,end);
	}

	@Override
	public <T> List<T> findByCriteriaPaging(Class<T> className, Map<String, Object> params, Map<String, Object> likeParams, String orderPropertyName, boolean isAsc, int star, int end) {
		return commonDao.findByCriteriaPaging(className,params,likeParams,orderPropertyName,isAsc,star,end);
	}

	@Override
	public <T> Long findByCriteriaPagingTotal(Class<T> className, Map<String, Object> params, Map<String, Object> likeParams) {
		return commonDao.findByCriteriaPagingTotal(className, params, likeParams);
	}

	@Override
	public <T> Long findByCriteriaPagingTotal(Class<T> className, Map<String, Object> params) {
		return commonDao.findByCriteriaPagingTotal(className,params);
	}

	@Override
	public <T> T findUniqueByCriteria(Class<T> className, Map<String, Object> map) {
		return  commonDao.findUniqueByCriteria(className, map);
	}

	@Override
	public <T> List<T> findByHql(String hql) {
		return commonDao.findByHql(hql);
	}

	@Override
	public <T> List<T> findByHql(String hql, List<Object> params) {
		return commonDao.findByHql(hql,params);
	}

	@Override
	public <T> List<T> findByHql(String hql, Map<String, Object> map) {
		return commonDao.findByHql(hql,map);
	}

	@Override
	public <T> int ExecHQL(String hql) {
		return commonDao.ExecHQL(hql);
	}

	@Override
	public <T> List<T> findBySQL(String sql) {
		return commonDao.findBySQL(sql);
	}

	@Override
	public Long findTotalBySQL(String sql) {
		return commonDao.findTotalBySQL(sql);
	}

	@Override
	public <T> List<T> findBySQL(String sql, Class cls) {
		return commonDao.findBySQL(sql,cls);
	}

	@Override
	public <T> int ExecSQL(String sql) {
		return commonDao.ExecSQL(sql);
	}
	
	@Override
	public <T> int ExecSQL(String sql, Map<String, Object> params){
		return commonDao.ExecSQL(sql, params);
	}
	
	@Override
	public <T> int ExecSQL(String sql, List<Object> params){
		return commonDao.ExecSQL(sql, params);
	}

	@Override
	public <T> List<T> findBySQL(String sql, List<Object> params) {
		return commonDao.findBySQL(sql, params);
	}

	@Override
	public <T> List<T> findBySQL(String sql, Map<String, Object> params) {
		return commonDao.findBySQL(sql,params);
	}

	@Override
	public <T> List<T> findByKey(Class<T> className, Map<String, Object> map,Map<String,Object> likemap) {return commonDao.findByKey(className, map, likemap);}
}
