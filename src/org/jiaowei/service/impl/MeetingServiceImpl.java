package org.jiaowei.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jiaowei.common.service.impl.CommonServiceImpl;
import org.jiaowei.service.MeetingService;
import org.springframework.stereotype.Service;

@Service("meetingServiceImpl")
public class MeetingServiceImpl  extends CommonServiceImpl implements MeetingService {
	private static Logger logger = Logger.getLogger(MeetingServiceImpl.class);

	@Override
	public List<Object[]> queryObjectList(Map<String, Object> param) {
		List<Object[]> objList = null;
		try {
			StringBuffer sql = new StringBuffer("select meetId,meetPhone from meeting_user where 1=1");
			Map<String, Object> params = new HashMap<String, Object>();
			if(param.get("meetPhone") != null){
				sql.append(" and meetPhone = :meetPhone ");
				params.put("meetPhone", param.get("meetPhone"));
			}
		      if (param.get("meetingType") != null)
		      {
		        sql.append(" and meetType = :meetType ");
		        params.put("meetType", param.get("meetingType"));
		      }			
			objList = findBySQL(sql.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("check videoImgList is error", e);
		}
		return objList;
	}

	@Override
	public List<Map<String, Object>> queryListMap(Map<String, Object> param) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		List<Object[]> objList = queryObjectList(param);
		if(objList != null && objList.size() > 0){
			for (Object[] obj : objList) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("meetId", obj[0]);
				map.put("meetPhone", obj[1]);
				resultList.add(map);
			}
		}
		return resultList;
	}
}
