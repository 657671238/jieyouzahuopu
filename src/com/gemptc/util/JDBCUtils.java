package com.gemptc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Utils 工具类  访问数据库操作的
public class JDBCUtils {
	
	public static Connection getConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		//JAVA的反射机制  由字符串来获取对象
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop?useUnicode=true&characterEncoding=utf-8", "root", "123456");
	    
		return conn;
	}
	
	public static void release(ResultSet rs,PreparedStatement psmt,Connection conn) throws Exception {

		if(rs!=null) {
			rs.close();
		}
		if(psmt!=null) {
			psmt.close();
		}
		if(conn!=null){
			conn.close();
		}		
	}
	

}
