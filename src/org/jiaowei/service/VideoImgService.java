package org.jiaowei.service;

import java.util.List;
import java.util.Map;

import org.jiaowei.common.service.CommonService;

public interface VideoImgService extends CommonService {

	/**
	 * 获取列表
	 * @param videType
	 * @param keyword
	 * @param parentId
	 * @param isHot 热点
	 * @param videId 
	 * @param openId
	 * @param isCollect 收藏
	 * @return
	 */
	public List<Object[]> queryVideoImgObjectList(Integer videType, String keyword, Long parentId, Integer isHot, Long videId, String openId, String isCollect, String videIds);
	
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
	public List<Map<String,Object>> queryVideoImgListMap(Integer videType, String keyword, Long parentId, Integer isHot, Long videId, String openId, String isCollect);
	
	/**
	 * 获取listMap
	 * @param type
	 * @param keyword
	 * @param parentId
	 * @param openId
	 * @return
	 */
	public List<Map<String, Object>> queryVideoImgListMap(String type, String keyword, Long parentId, String openId) ;
	public List<Map<String, Object>> queryVideoImgListMap(String isCollect,String videIds, String openId);
}
