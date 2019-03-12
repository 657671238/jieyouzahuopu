package com.gemptc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.gemptc.entity.User;
import com.gemptc.util.DruidDataSourceUtils;
import com.gemptc.util.JDBCUtils;

public class UserDaoImp implements UserDao {

	@Override
	public User searchUserByUserName(String username) throws Exception {
		Connection conn = DruidDataSourceUtils.getConnection();
		String sql = "SELECT * FROM t_user WHERE u_name=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, username);
		ResultSet rs = psmt.executeQuery();
		User resultUser = null;
		if(rs.next()) {
			resultUser = new User();
			resultUser.setU_id(rs.getInt("u_id"));
			resultUser.setU_name(rs.getString("u_name"));
			resultUser.setU_addtime(rs.getString("u_addtime"));
			resultUser.setU_email(rs.getString("u_email"));
			resultUser.setU_status(rs.getString("u_status"));
			resultUser.setU_password(rs.getString("u_password"));
			resultUser.setU_telephone(rs.getString("u_telephone"));
		}
		DruidDataSourceUtils.release(rs, psmt, conn);
		return resultUser;
	}

	@Override
	public User searchFrontUserByUserName(String username) throws Exception {
		//采用JDBC连接数据库实现
		Connection conn = DruidDataSourceUtils.getConnection();
		String sql = "SELECT * FROM t_user WHERE u_name=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, username);
		ResultSet rs = psmt.executeQuery();
		User resultUser = null;
		if(rs.next()) {
			resultUser = new User();
			resultUser.setU_id(rs.getInt("u_id"));
			resultUser.setU_name(rs.getString("u_name"));
			resultUser.setU_addtime(rs.getString("u_addtime"));
			resultUser.setU_email(rs.getString("u_email"));
			resultUser.setU_status(rs.getString("u_status"));
			resultUser.setU_password(rs.getString("u_password"));
			resultUser.setU_telephone(rs.getString("u_telephone"));
		}
		DruidDataSourceUtils.release(rs, psmt, conn);
		return resultUser;
	}

	@Override
	public int register(User user) throws Exception {
		Connection conn = DruidDataSourceUtils.getConnection();
		String sql = "INSERT INTO t_user (u_name,u_password) VALUES (?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getU_name());
		ps.setString(2, user.getU_password());
		int count = ps.executeUpdate();
		DruidDataSourceUtils.release(null, ps, conn);
		return count;
	}

	@Override
	public int getTotalUser() throws Exception {
		Connection conn = DruidDataSourceUtils.getConnection();
		String sql = "SELECT count(*) AS count FROM t_user";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int count = 0;
		while(rs.next()) {
			count = rs.getInt("count");
		}
		DruidDataSourceUtils.release(rs, ps, conn);
		return count;
	}

	@Override
	public ArrayList<User> getAllUser(int start, int length) throws Exception {
		Connection conn = DruidDataSourceUtils.getConnection();
		String sql = "SELECT * FROM t_user LIMIT ?,?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, start);
		ps.setInt(2, length);
		ResultSet rs = ps.executeQuery();
		ArrayList<User> array = new ArrayList<User>();
		while(rs.next()) {
			User user = new User();
			user.setU_id(rs.getInt("u_id"));
			user.setU_name(rs.getString("u_name"));
			user.setU_password(rs.getString("u_password"));
			user.setU_addtime(rs.getString("u_addtime"));
			user.setU_status(rs.getString("u_status"));
			array.add(user);
		}
		DruidDataSourceUtils.release(rs, ps, conn);
		return array;
	}

	@Override
	public boolean changeUserState(int u_id, int newstate) throws Exception {
		Connection conn = DruidDataSourceUtils.getConnection();
		String sql = "UPDATE t_user SET u_status=? where u_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, newstate);
		ps.setInt(2, u_id);
		int count = ps.executeUpdate();
		if(count!=0) {
			JDBCUtils.release(null, ps, conn);
			return true;
		}
		DruidDataSourceUtils.release(null, ps, conn);
		return false;
	}

	@Override
	public User searchAdminByUserName(String username) throws Exception {
		Connection conn = DruidDataSourceUtils.getConnection();
		String sql = "SELECT * FROM t_admin WHERE u_name=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, username);
		ResultSet rs = psmt.executeQuery();
		User resultAdmin = null;
		if(rs.next()) {
			resultAdmin = new User();
			resultAdmin.setU_id(rs.getInt("u_id"));
			resultAdmin.setU_name(rs.getString("u_name"));
			resultAdmin.setU_addtime(rs.getString("u_addtime"));
			resultAdmin.setU_email(rs.getString("u_email"));
			resultAdmin.setU_status(rs.getString("u_status"));
			resultAdmin.setU_password(rs.getString("u_password"));
			resultAdmin.setU_telephone(rs.getString("u_telephone"));
		}
		DruidDataSourceUtils.release(rs, psmt, conn);
		return resultAdmin;
	}

}
