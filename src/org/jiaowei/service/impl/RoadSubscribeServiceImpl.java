package org.jiaowei.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jiaowei.common.service.impl.CommonServiceImpl;
import org.jiaowei.controllers.Login;
import org.jiaowei.entity.RoadSubscribeEntity;
import org.jiaowei.service.RoadSubscribeService;
import org.jiaowei.util.StringUtil;
import org.jiaowei.wxutil.WeixinUtils;
import org.springframework.stereotype.Service;

@Service("roadSubscribeServiceImpl")
public class RoadSubscribeServiceImpl extends CommonServiceImpl implements
		RoadSubscribeService {

	private static Logger logger = Logger.getLogger(Login.class);
	/**
	 * 获取数据库原始列表
	 */
	@Override
	public List<Object[]> queryRoadSubscribeObjectList(String openId, Long id, String isStart, Date nextSendDate,Integer subsType) {
		
		List<Object[]> objList = null;
		try {
			StringBuffer sql = new StringBuffer("select "
					+ "subsId,subsOpenId,subsCharacter,subsImg,subsCreateTime,subsRemindDate,subsRemindHour,subsRemindMinute,subsRemindWeek,subsRemindType,subsIsStart,subsSendTimes,subsSendNextTimes,subsTitleName,subsType "
					+ "from road_subscribe where 1=1");
			Map<String, Object> params = new HashMap<String, Object>();
			String par = null;
			if(StringUtil.isNotEmpty(openId)){
				sql.append(" and subsOpenId = :openId ");
				params.put("openId", openId);
				par+=",openId:"+openId;
			}
			
			if(id != null && id > 0){
				sql.append(" and subsId = :subsId ");
				params.put("subsId", id);
				par+=",subsId:"+id;
			}
			
			if(isStart != null){
				sql.append(" and subsIsStart = :subsIsStart ");
				params.put("subsIsStart", isStart);
				par+=",subsIsStart:"+isStart;
			}
			if(nextSendDate != null){
				sql.append(" and subsSendNextTimes <= :subsSendNextTimes ");
				params.put("subsSendNextTimes", nextSendDate);
				par+=",nextSendDate:"+nextSendDate;
			}
			if(subsType != null){
				sql.append(" and subsType = :subsType ");
				params.put("subsType", subsType);
				par+=",subsType:"+subsType;
			}
			
			
			sql.append(" order by subsRemindDate desc, subsId desc");
			objList = findBySQL(sql.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("check videoImgList is error", e);
		}
		return objList;
		
	}

	/**
	 * 处理查询结果为实体类列表
	 */
	@Override
	public List<RoadSubscribeEntity> queryRoadSubscribeList(String openId,Long id, String isStart, Date nextSendDate,Integer subsType) {
		List<RoadSubscribeEntity> roadList = new ArrayList<RoadSubscribeEntity>();
		List<Object[]> objList = queryRoadSubscribeObjectList(openId, id, isStart, nextSendDate, subsType);
		if(objList != null && objList.size() > 0){
			
			for (int i=0;i<objList.size();i++) {
				Object[] obj=objList.get(i);
				RoadSubscribeEntity road = new RoadSubscribeEntity();
				road.setSubsId(WeixinUtils.parseInteger(obj[0]));
				road.setSubsOpenId(WeixinUtils.parseString(obj[1]));
				road.setSubsCharacter(WeixinUtils.parseString(obj[2]));
				road.setSubsImg(WeixinUtils.parseString(obj[3]));
				road.setSubsCreateTime(WeixinUtils.parseDateTime(obj[4]));
				road.setSubsRemindDate(WeixinUtils.parseString(obj[5]));
				road.setSubsRemindHour(WeixinUtils.parseString(obj[6]));
				road.setSubsRemindMinute(WeixinUtils.parseString(obj[7]));
				road.setSubsRemindWeek(WeixinUtils.parseString(obj[8]));
				road.setSubsRemindType(WeixinUtils.parseInteger(obj[9]));
				road.setSubsIsStart(WeixinUtils.parseInteger(obj[10]));
				road.setSubsSendTimes(WeixinUtils.parseDateTime(obj[11]));
				road.setSubsSendNextTimes(WeixinUtils.parseDateTime(obj[12]));
				road.setSubsTitleName(WeixinUtils.parseString(obj[13]));
				road.setSubsType(WeixinUtils.parseInteger(obj[14]));
				roadList.add(road);
			}
		}
		return roadList;
	}

	/**
	 * 添加
	 */
	@Override
	public boolean insertRoadSubscribe(RoadSubscribeEntity road) {
		boolean result = false;
//		Map<String,Object> params = new HashMap<String, Object>();
//		String sql = "insert into road_subscribe(subsId,subsOpenId,subsCharacter,subsImg,subsCreateTime,subsRemindDate,subsRemindHour,subsRemindMinute,subsRemindWeek,subsRemindType,subsIsStart,subsSendTimes) "
//				+ "values(:subsId,:subsOpenId,:subsCharacter,:subsImg,:subsCreateTime,:subsRemindDate,"
//				+ ":subsRemindHour,:subsRemindMinute,:subsRemindWeek,:subsRemindType,"
//				+ ":subsIsStart,:subsSendTimes )";
//		System.out.println("sql:"+sql);
//		params.put("subsId", road.getSubsId());
//		params.put("subsOpenId", road.getSubsOpenId());
//		params.put("subsCharacter", road.getSubsCharacter());
//		params.put("subsImg", road.getSubsImg());
//		params.put("subsCreateTime", road.getSubsCreateTime());
//		params.put("subsRemindDate", road.getSubsRemindDate());
//		params.put("subsRemindHour", road.getSubsRemindHour());
//		params.put("subsRemindMinute", road.getSubsRemindMinute());
//		params.put("subsRemindWeek", road.getSubsRemindWeek());
//		params.put("subsRemindType", road.getSubsRemindType());
//		params.put("subsIsStart", road.getSubsIsStart());
//		params.put("subsSendTimes", road.getSubsSendTimes());
		List<Object> params = new ArrayList<Object>();
		String sql = "insert into road_subscribe(subsId,subsOpenId,subsCharacter,subsImg,subsCreateTime,subsRemindDate,subsRemindHour,subsRemindMinute,subsRemindWeek,subsRemindType,subsIsStart,subsSendTimes) "
				+ "values(?,?,?,?,?,?,"
				+ "?,?,?,?,"
				+ "?,?)";
//		road.setSubsCharacter("123213");
//		road.setSubsRemindWeek("1");
//		road.setSubsSendTimes(WeixinUtils.getNowDateTime());
		
		params.add(road.getSubsId());
		params.add(road.getSubsOpenId());
		params.add(road.getSubsCharacter());
		params.add(road.getSubsImg());
		params.add(road.getSubsCreateTime());
		params.add(road.getSubsRemindDate());
		params.add(road.getSubsRemindHour());
		params.add(road.getSubsRemindMinute());
		params.add(road.getSubsRemindWeek());
		params.add(road.getSubsRemindType());
		params.add(road.getSubsIsStart());
		params.add(road.getSubsSendTimes());
		try {
			ExecSQL(sql, params);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean editRoadSubscribe(RoadSubscribeEntity road) {
		boolean result = false;
		Map<String,Object> params = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer("update road_subscribe set subsOpenId= :subsOpenId");
		params.put("subsOpenId", road.getSubsOpenId());
		
		if(road.getSubsCharacter() != null){
			sql.append(" ,subsCharacter = :subsCharacter ");
			params.put("subsCharacter", road.getSubsCharacter());
		}
		if(road.getSubsImg() != null){
			sql.append(" ,subsImg = :subsImg ");
			params.put("subsImg", road.getSubsImg());
		}
		if(StringUtil.isNotEmpty(road.getSubsRemindDate())){
			sql.append(" ,subsRemindDate = :subsRemindDate ");
			params.put("subsRemindDate", road.getSubsRemindDate());
		}
		if(StringUtil.isNotEmpty(road.getSubsRemindHour())){
			sql.append(" ,subsRemindHour = :subsRemindHour ");
			params.put("subsRemindHour", road.getSubsRemindHour());
		}
		if(StringUtil.isNotEmpty(road.getSubsRemindMinute())){
			sql.append(" ,subsRemindMinute = :subsRemindMinute ");
			params.put("subsRemindMinute", road.getSubsRemindMinute());
		}
		
		if(road.getSubsRemindWeek() != null){
			sql.append(" ,subsRemindWeek = :subsRemindWeek ");
			params.put("subsRemindWeek", road.getSubsRemindWeek());
		}
		if(StringUtil.isNotEmpty(road.getSubsRemindType())){
			sql.append(" ,subsRemindType = :subsRemindType ");
			params.put("subsRemindType", road.getSubsRemindType());
		}
		if(StringUtil.isNotEmpty(road.getSubsIsStart())){
			sql.append(" ,subsIsStart = :subsIsStart ");
			params.put("subsIsStart", road.getSubsIsStart());
		}
		if(road.getSubsSendTimes() != null){
			sql.append(" ,subsSendTimes = :subsSendTimes ");
			params.put("subsSendTimes", road.getSubsSendTimes());
		}
		
		if(road.getSubsSendNextTimes() != null){
			sql.append(" ,subsSendNextTimes = :subsSendNextTimes ");
			params.put("subsSendNextTimes", road.getSubsSendNextTimes());
		}
		if(road.getSubsType() != null){
			sql.append(" ,subsType = :subsType ");
			params.put("subsType", road.getSubsType());
		}
		sql.append(" where subsId = :subsId");
		params.put("subsId", road.getSubsId());
		try {
			ExecSQL(sql.toString(), params);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delRoadSubscribe(Long id) {
		boolean result = false;
		Map<String,Object> params = new HashMap<String, Object>();
		String sql = "delete from road_subscribe where subsId = :subsId ";
		params.put("subsId", id);
		try {
			ExecSQL(sql, params);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

//	@Override
//	public Date getNextSendDate(Date nowDate,String subsRemindWeek, String hour, String minute) {
//		Date resultDate = null;
//		String nowDateStr = WeixinUtils.parseDateStr(nowDate);
//		String nowDateTime = nowDateStr + " " + hour + ":" + minute + ":00";
//		Date tempDate = WeixinUtils.parseDateTime(nowDateTime);
//		//判断是不是今天
//		int noww = WeixinUtils.getWeekOfDate(nowDate);
//		if(subsRemindWeek.contains(""+noww) && nowDate.getTime() <= tempDate.getTime()){
//			resultDate = tempDate;
//		} else {
//			Map<String, Date> weekMap = new HashMap<String, Date>();
//			for (int i = 1; i <= 7; i++) {
//				Date date = WeixinUtils.getDateOfDays(tempDate, i);
//				int w = WeixinUtils.getWeekOfDate(date);
//				weekMap.put(""+w, date);
//			}
//			
//			List<Date> dateList = new ArrayList<Date>();
//			for (String s : subsRemindWeek.split(",")) {
//				dateList.add(weekMap.get(s));
//			}
//			Collections.sort(dateList, new Comparator<Date>() {
//	            public int compare(Date arg0, Date arg1) {
//	            	Long t = arg0.getTime();
//	            	Long t1 = arg1.getTime();
//	            	int ret = t.compareTo(t1);
//	                return ret;
//	            }
//	        });
//			resultDate = dateList.get(0);
//			System.out.println("date:" + resultDate);
//		}
//		return resultDate;
//	}
//
//	public Date getNextSendDate(String subsRemindDate, String hour, String minute){
//		Date result = null;
//		String nowDateTime = subsRemindDate + " " + hour + ":" + minute + ":00";
//		result = WeixinUtils.parseDateTime(nowDateTime);
//		return result;
//	}
	
}
