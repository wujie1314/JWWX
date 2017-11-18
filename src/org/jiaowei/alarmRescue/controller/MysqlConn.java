package org.jiaowei.alarmRescue.controller;

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
		String driver = "com.mysql.jdbc.Driver";
	    // URL指向要访问的数据库名
	    String url = "jdbc:mysql://127.0.0.1:3306/jwwx2?useUnicode=true&characterEncoding=UTF-8";
	    // MySQL配置时的用户名
	    String user = "root"; 
	    // MySQL配置时的密码
	    String password = "123456";
			
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
	     Statement statement = (Statement) conn.createStatement();

	     // 要执行的SQL语句
	     String sql = "";
	     
			if (map.get("ID") == "") {
				sql += "insert into bst_work_order(content_desc,STATUS,data_type,jwd,contact_way,create_time) "
						+ " values('"
						+ map.get("content_desc")
						+ "','"
						+ 1
						+ "','"
						+ 1306
						+ "','"
						+ map.get("jwd")
						+ "','"
						+ map.get("contact_way")
						+ "','"
						+ map.get("create_time") + "')";
				System.out.println("=======" + sql);
			} else {
				sql += "update bst_work_order set content_desc='"
						+map.get("content_desc") + "',contact_way='"
						+ map.get("contact_way") + "', jwd='" + map.get("jwd")
						+ "',update_time='" + map.get("update_time")
						+ "' where id=" + map.get("ID");
				System.out.println("=======" + sql);
			}
 
		statement.execute(sql);

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
}
