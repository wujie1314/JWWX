package org.jiaowei.alarmRescue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.Statement;

public class MysqlConn {
	public static void dataManipulation(Map<String, Object> map) {
		String driver = "oracle.jdbc.driver.OracleDriver";
	    // URL指向要访问的数据库名
	    String url = "jdbc:oracle:thin:@10.224.2.180:1521:ORCL";
	    // MySQL配置时的用户名
	    String user = "wssj"; 
	    // MySQL配置时的密码
	    String password = "wssj";
			
//			String driver = "oracle.jdbc.driver.OracleDriver";
//		    // URL指向要访问的数据库名
//		    String url = "jdbc:oracle:thin:@superc102.vicp.cc:1522:jwwx";
//		    // MySQL配置时的用户名
//		    String user = "jwwx"; 
//		    // MySQL配置时的密码
//		    String password = "All4Icode";

	    try { 
	     // 加载驱动程序
	     Class.forName(driver);
	     // 连数据库
	     Connection conn = DriverManager.getConnection(url, user, password);
	     if(!conn.isClosed()) 
	      System.out.println("连接成功!");
	     // statement用来执行SQL语句
	    int result =0;// 创建一个结果集对象
	     String sql = "";
	     
	     
	     if (map.get("ID") == "") {
	     // 要执行的SQL语句
	     sql = "insert into HNII.HNII_ALARM_CREATE_TEST(ID,CJSJ,CJHM,CALLREASON,CONTENT,LXFS,WZ,CREATEORG,REMARK,DLJD,DLWD,UDID,MYID)"
	     		+ "values(?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?,?,?,?,?,?,?)";
	     PreparedStatement pre =  conn.prepareStatement(sql);
	     pre.setString(1,"wx"+System.currentTimeMillis());
	     pre.setString(2,map.get("create_time").toString());
	     pre.setString(3,"51123132");
	     pre.setString(4,map.get("type").toString());
	     pre.setString(5,map.get("content_desc").toString());
	     pre.setString(6,map.get("contact_way").toString());
	     pre.setString(7,map.get("currentLocation").toString());
	     pre.setString(8,"102");
	     pre.setString(9,map.get("content_desc").toString());
	     pre.setString(10,map.get("longitude").toString());
	     pre.setString(11,map.get("latitude").toString());
	     pre.setString(12,"123456444");
	     pre.setString(13,map.get("ID").toString());
	     
	     System.out.println(pre.toString());
	     result = pre.executeUpdate();// 执行查询，注意括号中不需要再加参数
	     System.out.println();
	     }
	     else {
	    	  sql = "insert into HNII.HNII_ALARM_CREATE_TEST(ID,CJSJ,CJHM,CALLREASON,CONTENT,LXFS,WZ,CREATEORG,REMARK,DLJD,DLWD,MYID)"
	  	     		+ "values(?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?,?,?,?,?,?)";
	  	     PreparedStatement pre =  conn.prepareStatement(sql);
	  	     pre.setString(1,"wx"+System.currentTimeMillis());
	  	     pre.setString(2,map.get("create_time").toString());
	  	     pre.setString(3,"51123132");
	  	     pre.setString(4,map.get("type").toString());
	  	     pre.setString(5,map.get("content_desc").toString());
	  	     pre.setString(6,map.get("contact_way").toString());
	  	     pre.setString(7,map.get("currentLocation").toString());
	  	     pre.setString(8,"102");
	  	     pre.setString(9,map.get("content_desc").toString());
	  	     pre.setString(10,map.get("longitude").toString());
	  	     pre.setString(11,map.get("latitude").toString());
	  	     pre.setString(12,map.get("ID").toString());
	  	     
	  	     System.out.println(pre.toString());
		     int num = pre.executeUpdate();// 执行查询，注意括号中不需要再加参数
	     }

	     conn.close();

	    } catch(ClassNotFoundException e) {
	     System.out.println("连接失败!"); 
	     e.printStackTrace();
	     
	    } catch(SQLException e) {

	     e.printStackTrace();

	    } catch(Exception e) {

	     e.printStackTrace();

	    } 		
	}
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ID", "wx1515417650490");
		map.put("create_time","2018-05-01 01:01:00");
		map.put("content_desc", "asd");
		map.put("contact_way", "15223786299");
		map.put("longitude", "20.314");
		map.put("latitude", "20.1564");
		map.put("type", "求助");
		dataManipulation(map);
	}
}
