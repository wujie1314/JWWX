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
			
//			String driver = "com.mysql.jdbc.Driver";
//		    // URL指向要访问的数据库名
//		    String url = "jdbc:mysql://10.224.5.53:3306/xdcloud?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
//		    // MySQL配置时的用户名
//		    String user = "bstdbuser"; 
//		    // MySQL配置时的密码
//		    String password = "bstdbuser";

	    try { 
	     // 加载驱动程序
	     Class.forName(driver);
	     // 连数据库
	     Connection conn = DriverManager.getConnection(url, user, password);
	     if(!conn.isClosed()) 
	      System.out.println("连接成功!");
	     // statement用来执行SQL语句
	     ResultSet result = null;// 创建一个结果集对象
	     
	     // 要执行的SQL语句
	     String sql = "insert into HNII.HNII_ALARM_CREATE_TEST(ID,CJSJ,CJHM,CALLREASON,CONTENT,LXFS,WZ,CREATEORG,REMARK,DLJD,DLWD,UDID,MYID)"
	     		+ "values(?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?,?,?,?,?,?,?) ";
	     PreparedStatement pre =  conn.prepareStatement(sql);
	     pre.setString(1,"wx"+System.currentTimeMillis());
	     pre.setString(2,map.get("create_time").toString());
	     pre.setString(3,"51123132");
	     pre.setString(4,map.get("type").toString());
	     pre.setString(5,map.get("content_desc").toString());
	     pre.setString(6,map.get("contact_way").toString());
	     pre.setString(7,"位置");
	     pre.setString(8,"101");
	     pre.setString(9,"备注");
	     pre.setString(10,map.get("longitude").toString());
	     pre.setString(11,map.get("latitude").toString());
	     pre.setString(12,"123456444");
	     pre.setString(13,map.get("ID").toString());
	     
	     System.out.println(pre.toString());
	     result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
//			if (true) {
//				sql = "insert into HNII.HNII_ALARM_CREATE_TEST(ID,CJSJ,CJHM,CALLREASON,CONTENT,LXFS,WZ,CREATEORG,GETDATE,STATE,REMARK,DLJD,DLWD,UDID,MYID) "
//						+ " values('"
//						+ "SSJ"+map.get("create_time")
//						+ "',"
//						+ "to_date('"+map.get("create_time")+"','yyyy-mm-dd hh24:mi:ss')"
//						+ ",'"
//						+ "51123132"
//						+ "','"
//						+ map.get("type")
//						+ "','"
//						+ map.get("content_desc") //内容
//						+ "','"
//						+ map.get("contact_way") // fangs
//						+ "','"
//						+ "位置"
//						+ "','"
//						+ "153154687" // shouji
//						+ "',"
//						+ "to_date('"+map.get("create_time")+"','yyyy-mm-dd hh24:mi:ss')"
//						+ ",'"
//						+ " 1"
//						+ "','"
//						+ "备注"
//						+ "','"
//						+ map.get("longitude")
//						+ "','"
//						+ map.get("latitude") 
//						+ "','"
//						+ "123456444"
//						+ "','"
//						+ map.get("ID")
//						+ "')";
				//			} else {
//				sql += "update HNII.HNII_ALARM_CREATE_TEST  SET CALLREASON='"
//						+map.get("type") + "',CONTENT='"
//						+ map.get("content_desc") + "', LXFS='" + map.get("contact_way")
//						+ "',DLJD='" + map.get("longitude")
//						+ "',DLWD='" + map.get("latitude")
//						+ "'id=" + map.get("ID");
//				System.out.println("=======" + sql);
//			}
//
//			statement.execute(sql);

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
		map.put("ID", "asd");
		map.put("create_time","2012-05-01 01:01:00");
		map.put("content_desc", "asd");
		map.put("contact_way", "15223786299");
		map.put("longitude", "20.314");
		map.put("latitude", "20.1564");
		map.put("type", "报警");
		dataManipulation(map);
	}
}
