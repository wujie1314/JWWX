package org.jiaowei.service;

import java.util.List;
import java.util.Map;

import org.jiaowei.common.service.CommonService;

public interface MeetingService extends CommonService {

	/**
	 * 获取列表
	 * @param videType
	 * @param keyword
	 * @param parentId  -1查询为null的数据，-2不查询
	 * @param isHot
	 * @param videId
	 * @param openId
	 * @param isCollect
	 * @return
	 */
	public List<Object[]> queryObjectList(Map<String, Object> param);
	
	/**
	 * 获取列表
	 * @param videType
	 * @param keyword
	 * @param parentId
	 * @param isHot
	 * @param videId
	 * @param openId
	 * @param isCollect
	 * @return
	 */
	public List<Map<String,Object>> queryListMap(Map<String, Object> param);
	
	
}
