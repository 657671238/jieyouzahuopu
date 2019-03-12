package com.gemptc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

public class DruidDataSourceUtils {
	private static String driverClass = null;
	private static String url = null;
	private static String name = null;
	private static String password = null;
	private static DruidDataSource dataSource = new DruidDataSource();
	//静态代码块
	static {
		try {
			ClassLoader loader = DruidDataSourceUtils.class.getClassLoader();
			InputStream inputStream = loader.getResourceAsStream("./com/gemptc/util/db.properties");
			Properties props = new Properties();
			props.load(inputStream);
			url = props.getProperty("url");
			name = props.getProperty("name");
			password = props.getProperty("password");
			driverClass = props.getProperty("driverClass");
			System.out.println(url+"---"+name+"---"+password);
			
			dataSource
			.setDriverClassName(driverClass);
			dataSource.setUrl(url);
			dataSource.setUsername(name);
			dataSource.setPassword(password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	//连接 需要  配置    
	// 设置连接的参数   url  root  password
	// 读取 配置文件  一次性读取   不需要每次都读取配置文件
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
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
