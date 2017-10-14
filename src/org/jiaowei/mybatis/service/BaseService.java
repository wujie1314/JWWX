package org.jiaowei.mybatis.service;

import java.util.List;
import java.util.Map;


public interface BaseService<T> {


	/**
	 * 新增对象
	 * 
	 * @param t
	 */
	int insert(T t);

	/**
	 * 更新对象
	 * 
	 * @param t
	 */
	int update(T t);

	/**
	 * 批量新增对象
	 * 
	 * @param list
	 */
	int batchAdding(List<T> list);

	/**
	 * 批量更新列表
	 * 
	 * @param list
	 * @return
	 */
	int batchUpdate(List<T> list);

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 * @return
	 */
	int delete(Object id);

	/**
	 * 根据ID删除对象
	 * 
	 * @param list
	 *            包含ID的集合的对象
	 * @return
	 */
	int deleteByIds(Object[] ids);
	/**
	 * 根据主键获取单个对象
	 * @param id
	 * @return
	 */
	T get(Object id);
	/**
	 * 根据参数获取单个对象
	 * @param params
	 * @return
	 */
	T query(Map<String,Object> params);
	/**
	 * 获取条数
	 * @param params
	 * @return
	 */
	int queryCount(Map<String,Object> params);
	/**
	 * 获取全部列表
	 * @param params
	 * @return
	 */
	List<T> queryList(Map<String,Object> params);
	/**
	 * 分页获取列表
	 * @param params
	 * @return
	 */
	List<T> queryPageList(Map<String,Object> params);
}
