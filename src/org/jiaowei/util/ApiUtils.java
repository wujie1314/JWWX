/**
 * 
 */
package org.jiaowei.util;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletInputStream;


/**
 * @author mx.li
 * @version 2013-8-12 上午9:09:31 
 */
public class ApiUtils {

	public static String parseString(ServletInputStream sis){
		String result = null;
		try{
			if(sis != null){
				ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		        int i = -1; 
		        while((i=sis.read())!=-1){ 
		        	baos.write(i); 
		        } 
		        result = new String(baos.toByteArray(), "utf-8");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static String obj2Str(Object obj){
		return obj == null ? null : obj.toString();
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
	
	//得到当前时间 2011-01-12 12:20:20
	public static Date getNowDateTime() {
		return new Timestamp(System.currentTimeMillis());
	} 
	
	/**
	 * 日期转换字符串
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date){
		String result = null;
		if(date != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			result =  format.format(date);
		} 
		return result;
	}
	
	/**
	 * 时间转换字符串
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date){
		String result = null;
		if(date != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result = format.format(date);
		}
		return result;
	}
	
	/**
	 * 时间转换字符串2
	 * @param date
	 * @return
	 */
	public static String formatDateTime2(Date date){
		String result = null;
		if(date != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			result = format.format(date);
		}
		return result;
	}
	
	/**
	 * 字符串转换日期
	 * @param s
	 * @return
	 */
	public static Date parseDate(String s){
		Date result = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			result = format.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 字符串转换时间
	 * @param s
	 * @return
	 */
	public static Date parseDateTime(String s){
		Date result = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			result = format.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static int dynamic(){
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
		    int index = rand.nextInt(i);
		    int tmp = array[index];
		    array[index] = array[i - 1];
		    array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < 6; i++)
		    result = result * 10 + array[i];
		
		System.out.println(result);
		return result;
	}
	
	public static int cookieOverTime(){
		String dateStr = ApiUtils.formatDate(new Date());//获取当前日期
		long currentt = System.currentTimeMillis();//获取系统时间与1970年毫秒数
		long dateOutT = ApiUtils.parseDateTime(dateStr+" 23:59:59").getTime();
		
		int minutes = (int) ((dateOutT - currentt)/(1000)); 
		return minutes;
	}
	
	public static void main(String[] args) {
		ApiUtils.cookieOverTime();
	}
}
