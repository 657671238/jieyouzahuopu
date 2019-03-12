package com.gemptc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

public class MyDataSourceUtils {
	private static DruidDataSource dataSource = (DruidDataSource) DruidDataSourceUtils.getDataSource();
	// 当前的线程对象  单例对象  唯一的
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	public static Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}
	
	//当前唯一的Connectin对象  获取同一个连接对象
	public static Connection getCurrentConnection() throws Exception{
		Connection conn = tl.get();  //从当前线程对象中获取连接对象 
		if(conn==null) {			//判断是否为空   为空就从连接池拿一个  绑定到当前线程中
			conn = getConnection();   
			tl.set(conn);
		}
		return conn;
	}
	
	//开启事务
	public static void startTransaction() throws Exception{
		getCurrentConnection().setAutoCommit(false);
	}
	//提交事务
	public static void commit() throws Exception{
		Connection conn = getCurrentConnection();
		conn.commit();
		//将连接从当前对象移除了   然后关闭连接
		tl.remove();
		conn.close();
	}
	//回滚事务
	public static void rollback() throws Exception{
		Connection conn = getCurrentConnection();
		conn.rollback();
		//将连接从当前对象移除了   然后关闭连接
		tl.remove();
	    conn.close();
	}
	
	public static void release(ResultSet rs,PreparedStatement psmt,Connection con){
    	 if(rs!=null) {
    		 try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    	 }
    	 if(psmt!=null) {
    		 try {
				psmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	 }
    	 if(con!=null) {
    		 try {
    			 // 重新close() 方法 实现 close 不是关闭 是归还一个连接到连接池中
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    	 }
     }
}
