package org.jiaowei.wxutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jiaowei.controllers.Login;
import org.jiaowei.entity.WxEntity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.Base64;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeixinUtils {
	private static Logger logger = Logger.getLogger(Login.class);
	
    public static String getOAuthOpenId(String appid, String secret, String code ) {
        String openId = null;
        String o_auth_openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        String requestUrl = o_auth_openid_url.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
        ApiHttpUtils client = new ApiHttpUtils(requestUrl);
		try {
			String result = client.doGet();
			logger.error("---client----->result:"+result +" ,o_auth_openid_url:"+requestUrl);
			ObjectMapper mapper = new ObjectMapper();
		    WxEntity wx =  mapper.readValue(result, WxEntity.class);
		    openId = wx.getOpenid();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---client-----error>",e);
		}
		logger.error("---client----->openId:"+openId);
        return openId;
    }
	
	public static String parseString(Object obj){
		String result = null;
		if(obj != null){
			result = String.valueOf(obj);
			if("".equals(result)){
				result = null;
			}
		}
		return result;
	}
	
	public static Long parseLong(Object obj){
		Long result = null;
		String s = parseString(obj);
		if(s != null){
			result = Long.valueOf(s);
		}
		return result;
	}
	
	public static Integer parseInteger(Object obj){
		Integer result = null;
		String s = parseString(obj);
		if(s != null){
			result = Integer.valueOf(s);
		}
		return result;
	}
	
	public static Date parseDateTime(Object obj){
		Date result = null;
		String s = parseString(obj);
		if(s != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				result = format.parse(s);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static Date parseDate(Object obj){
		Date result = null;
		String s = parseString(obj);
		if(s != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				result = format.parse(s);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String parseDateStr(Object obj){
		String result = null;
		if(obj != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			result = format.format(obj);
		}
		return result;
	}
	public static String parseDateTimeStr(Object obj){
		String result = null;
		if(obj != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result = format.format(obj);
		}
		return result;
	}
	
	//得到当前时间 2011-01-12 12:20:20
	public static Date getNowDateTime() {
		return new Timestamp(System.currentTimeMillis());
	} 
	public static void clearTime(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}
	//得到当前时间 如：2011-01-12 00:00:00
	public static Date getNowDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		clearTime(calendar);
		return new Timestamp(calendar.getTimeInMillis());
	}
	/**
	 * 获取相差天数的日期
	 * @param date
	 * @param days 正数表示后几天日期，负责表示前几天日期
	 * @return
	 */
	public static Date getDateOfDays(Date date, int days){
		Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.add(Calendar.DAY_OF_YEAR, days);  
        return cal.getTime();
	}
	
	public static int getWeekOfDate(Date dt) {
//        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
//        return weekDays[w];
        return w;
    }
	
	public static void main(String[] args) {
//		Date nowTimes = getNowDateTime();
//		System.out.println(getWeekOfDate(getNowDateTime()));
//		String str = "0";
//		
//		int[] weekDays = {0,1,2,3,4,5,6};
//		Map<String, Date> weekMap = new HashMap<String, Date>();
//		for (int i = 1; i <= 7; i++) {
//			Date date = getDateOfDays(nowTimes, i);
//			int w = getWeekOfDate(date);
//			System.out.println("date:"+date +",week:" + w);
//			weekMap.put(""+w, date);
//		}
//		
//		System.out.println("--------------------------");
//		for (String i : weekMap.keySet()) {
//			System.out.println("date:"+weekMap.get(i) +",week:" + i);
//		}
//		List<Date> dateList = new ArrayList<Date>();
//		for (String s : str.split(",")) {
//			dateList.add(weekMap.get(s));
//		}
//		Collections.sort(dateList, new Comparator<Date>() {
//            public int compare(Date arg0, Date arg1) {
////            	int ret = 0;
////            	if((arg0.getTime() - arg1.getTime()) > 0){
////            		ret = 1;
////            	}
//            	Long t = arg0.getTime();
//            	Long t1 = arg1.getTime();
//            	int ret = t.compareTo(t1);
//            	System.out.println("-------->ret:"+ret);
//                return ret;
//            }
//        });
//		for (Date date : dateList) {
//			System.out.println("--------->"+date);
//		}
//		Date date = getNextSendDate("2016-03-17", "15", "20");
//		System.out.println("date.0:"+ parseDateTimeStr(date));
		Date nowDate = getNowDate();
		String subsRemindWeek="0,1,2,3,4,5,6", hour = "12", minute = "00";
		Date d = getNextSendDate(false,nowDate, subsRemindWeek, hour, minute);
		System.out.println("---d:"+parseDateTimeStr(d));
	}
	
	/**
	 * 获取下一次时间
	 * @param isNow  订阅设置时需要判断是否是今天，定时任务则不需要判断
	 * @param nowDate
	 * @param subsRemindWeek
	 * @param hour
	 * @param minute
	 * @return
	 */
	public static Date getNextSendDate(boolean isNow, Date nowDate,String subsRemindWeek, String hour, String minute) {
		Date resultDate = null;
		String nowDateStr = WeixinUtils.parseDateStr(nowDate);
		String nowDateTime = nowDateStr + " " + hour + ":" + minute + ":00";
		Date tempDate = WeixinUtils.parseDateTime(nowDateTime);
		int noww = getWeekOfDate(nowDate);
		if(isNow && subsRemindWeek.contains(""+noww) && nowDate.getTime() <= tempDate.getTime()){
			resultDate = tempDate;
		} else {
			Map<String, Date> weekMap = new HashMap<String, Date>();
			for (int i = 1; i <= 7; i++) {
				Date date = WeixinUtils.getDateOfDays(tempDate, i);
				int w = WeixinUtils.getWeekOfDate(date);
				weekMap.put(""+w, date);
			}
			
			List<Date> dateList = new ArrayList<Date>();
			for (String s : subsRemindWeek.split(",")) {
				dateList.add(weekMap.get(s));
			}
			Collections.sort(dateList, new Comparator<Date>() {
	            public int compare(Date arg0, Date arg1) {
	            	Long t = arg0.getTime();
	            	Long t1 = arg1.getTime();
	            	int ret = t.compareTo(t1);
	                return ret;
	            }
	        });
			resultDate = dateList.get(0);
		}
		logger.debug("-----resultDate-------getNextSendDate:"+resultDate);
		return resultDate;
	}
	
	public static Date getNextSendDate(String subsRemindDate, String hour, String minute){
		Date result = null;
		String nowDateTime = subsRemindDate + " " + hour + ":" + minute + ":00";
		result = WeixinUtils.parseDateTime(nowDateTime);
		return result;
	}
	
	/**
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
	
	public static String getBaiDuLocationXY(String x, String y) {
		String result="";
        String url = "http://api.map.baidu.com/ag/coord/convert?from=0&to=4&x="+x+"&y="+y+"";
        System.out.println(x);
        System.out.println(y);
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"utf-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json.toString());
        JSONObject map=JSONObject.parseObject(json.toString());

        byte[] xbuff = Base64.decodeFast(map.get("x").toString());
        byte[] ybuff = Base64.decodeFast(map.get("y").toString());
        result = new String(xbuff) + "," + new String(ybuff);
        return result;
	}
}
