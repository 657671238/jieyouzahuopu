package com.gemptc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.gemptc.entity.Category;
import com.gemptc.util.DruidDataSourceUtils;

public class CategoryDaoImp implements CategoryDao {

	@Override
	public ArrayList<Category> getAllCategory() throws Exception {
		// 采用JDBC连接数据库实现
		Connection conn = DruidDataSourceUtils.getConnection();
		String sql = "SELECT * FROM t_category ";
		PreparedStatement psmt = conn.prepareStatement(sql);
		ResultSet rs = psmt.executeQuery();
		ArrayList<Category> categoryList = new ArrayList<>();
		while (rs.next()) {
			Category cate = new Category();
			cate.setC_id(rs.getInt("c_id"));
			cate.setC_name(rs.getString("c_name"));
			categoryList.add(cate);
		}
		DruidDataSourceUtils.release(rs, psmt, conn);
		return categoryList;
	}

}
