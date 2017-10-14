package org.jiaowei.common.service;

import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface CommonService {
	///////////////////////////////////////////////////////////////////////
	public <T> Serializable save(T entity);

	public <T> void batchSave(List<T> entitys);

	public <T> void saveOrUpdate(T entity);

	public <T> void delete(T entity);

	public <T> List<T> loadAll(Class<T> entityClass);

	/**
	 *
	 * 根据实体名称和主键获取实体
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> entityName, Serializable id);

	/**
	 * 根据属性查找
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @param <T>
	 * @return
	 */
	public <T> List<T> findByProperty(Class<T> entityClass,
									  String propertyName, Object value);

	/**
	 * 根据实体名字获取唯一记录
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass,
									  String propertyName, Object value);

	/**
	 *根据ID删除实体
	 * @param entityClassName
	 * @param id
	 * @param <T>
	 */
	public <T> void deleteEntityById(Class<T> entityClassName, Serializable id);

	/**
	 * 删除实体集合
	 *
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteAllEntities(Collection<T> entities);

	/**
	 * 更新指定的实体
	 * @param <T>
	 * @param entity
	 */
	public <T> void updateEntity(T entity);


	/**
	 * 根据条件查询
	 * @param className
	 * @param map
	 * @param <T>
	 * @return
	 */
	public<T> List<T> findByMap(Class<T> className, Map<String, Object> map);

	/**
	 * 根据条件查询
	 * @param className
	 * @param criterions
	 * @param <T>
	 * @return
	 */
	public<T> List<T> findByCriteria(Class<T> className, List<Criterion> criterions);

	/**
	 * 根据条件查询，并排序
	 * @param className
	 * @param criterions
	 * @param orderPropertyName
	 * @param isAsc
	 * @param <T>
	 * @return
	 */
	public <T> List<T> findByCriteria(Class<T> className, List<Criterion> criterions, String orderPropertyName, boolean isAsc);

	/**
	 * 排序的分页查询
	 * @param className
	 * @param params
	 * @param orderPropertyName
	 * @param isAsc
	 * @param star
	 * @param end
	 * @param <T>
	 * @return
	 */
	public <T> List<T> findByCriteriaPaging(Class<T> className, Map<String, Object> params, String orderPropertyName,
											boolean isAsc, int star, int end);
	public <T> List<T> findByCriteriaPaging(Class<T> className, Map<String, Object> params, String orderPropertyName1,
											boolean isAsc1, String orderPropertyName2, boolean isAsc2, int star, int end);
	/**
	 * 获取当前页，根据传入排序map进行排序
	 * @param className
	 * @param params
	 * @param sortMap 排序map
	 * @param star
	 * @param end
	 * @param <T>
	 * @return
	 */
	public <T> List<T> findByCriteriaPaging(Class<T> className, Map<String, Object> params, Map<String, Object> sortMap, int star, int end);
	/**
	 *
	 * @param className
	 * @param params 精确查询参数
	 * @param likeParams 模糊查询参数
	 * @param orderPropertyName 排序字段1
	 * @param isAsc
	 * @param star
	 * @param end
	 * @param <T>
	 * @return
	 */
	public <T> List<T> findByCriteriaPaging(Class<T> className, Map<String, Object> params,
											Map<String, Object> likeParams,
											String orderPropertyName,
											boolean isAsc, int star, int end);
	/**
	 *
	 * @param className
	 * @param params
	 * @param likeParams
	 * @param <T>
	 * @return
	 */
	public <T> Long findByCriteriaPagingTotal(Class<T> className,
											  Map<String, Object> params, Map<String, Object> likeParams);
	public <T> Long findByCriteriaPagingTotal(Class<T> className,
											  Map<String, Object> params);
	/**
	 * 根据条件查询唯一结果
	 * @param className
	 * @param map
	 * @param <T>
	 * @return
	 */

	public <T> T findUniqueByCriteria(Class<T> className, Map<String, Object> map);

	/////////////////////////////////////////////////
	////////========hql查询==============/////////////
	/////////////////////////////////////////////////

	/**
	 * 根据查hql询
	 * @param hql
	 * @param <T>
	 * @return
	 */
	public <T> List<T> findByHql(String hql);

	/**
	 * 根据查hql询 带参数查询 位置参数
	 * @param hql
	 * @param params
	 * @param <T>
	 * @return
	 */
	public <T> List<T> findByHql(String hql, List<Object> params);

	/**
	 * 根据查hql询 带参数查询 命名参数
	 * @param hql
	 * @param map
	 * @param <T>
	 * @return
	 */
	public <T> List<T> findByHql(String hql, Map<String, Object> map);

	/**
	 * 执行HQL更新
	 * @param hql
	 * @param <T>
	 * @return
	 */
	public <T> int ExecHQL(String hql);

	
	public <T> int ExecSQL(String sql, Map<String, Object> params);
	
	public <T> int ExecSQL(String sql,List<Object> params);

	/////////////////////////////////////////////////
	////////========sql查询==============/////////////
	/////////////////////////////////////////////////

	/**
	 * 根据SQL查询
	 * @param sql
	 * @param <T>
	 * @return
	 */
	public <T> List<T> findBySQL(String sql);

	public Long findTotalBySQL(String sql);

	public <T> List<T> findBySQL(String sql, Class cls);

	/**
	 * 执行SQL更新
	 * @param sql
	 * @param <T>
	 * @return
	 */
	public <T> int ExecSQL(String sql);

	/**
	 * 根据查sql询 带参数查询 位置参数
	 * @param sql
	 * @param params
	 * @param <T>
	 * @return
	 */
	public <T> List<T> findBySQL(String sql, List<Object> params);

	/**
	 * 根据查sql询 带参数查询 位置参数
	 * @param sql
	 * @param params
	 * @param <T>
	 * @return
	 */
	public <T> List<T> findBySQL(String sql, Map<String, Object> params);

	/**
	 * 根据条件查询结果
	 * @param className
	 * @param map
	 * @param <T>
	 * @return
	 */

	<T> List<T> findByKey(Class<T> className, Map<String, Object> map, Map<String, Object> likemap);

}
