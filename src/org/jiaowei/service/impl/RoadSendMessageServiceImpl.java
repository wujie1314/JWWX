package org.jiaowei.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jiaowei.common.service.impl.CommonServiceImpl;
import org.jiaowei.controllers.Login;
import org.jiaowei.entity.RoadSendMessageEntity;
import org.jiaowei.service.RoadSendMessageService;
import org.jiaowei.util.StringUtil;
import org.springframework.stereotype.Service;

@Service("roadSendMessageServiceImpl")
public class RoadSendMessageServiceImpl extends CommonServiceImpl implements
		RoadSendMessageService {

	private static Logger logger = Logger.getLogger(Login.class);

	@Override
	public List<Object[]> querySendMessageObjectList(String messName,
			String openId) {
		List<Object[]> objList = null;
		try {
			StringBuffer sql = new StringBuffer("select "
					+ "messName,messOpenId,messSendTimes "
					+ "from road_send_message where 1=1");
			Map<String, Object> params = new HashMap<String, Object>();
			if(StringUtil.isNotEmpty(messName)){
				sql.append(" and messName = :messName ");
				params.put("messName", messName);
			}
			if(StringUtil.isNotEmpty(openId)){
				sql.append(" and messOpenId = :openId ");
				params.put("openId", openId);
			}
			objList = findBySQL(sql.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("querySendMessageObjectList is error", e);
		}
		return objList;
	}

	@Override
	public List<RoadSendMessageEntity> querySendMessageList(String messName,
			String openId) {
		
		return null;
	}

	@Override
	public boolean insertSendMessage(RoadSendMessageEntity entity) {
		boolean result = false;
		List<Object> params = new ArrayList<Object>();
		String sql = "insert into road_send_message(messName,messOpenId,messSendTimes,messId) "
				+ "values(?,?,?,?)";
		
		params.add(entity.getMessName());
		params.add(entity.getMessOpenId());
		params.add(entity.getMessSendTimes());
		params.add(entity.getMessId());
	
		try {
			ExecSQL(sql, params);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean editSendMessage(RoadSendMessageEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
