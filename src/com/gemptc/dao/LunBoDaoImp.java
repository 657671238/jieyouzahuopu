package com.gemptc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.gemptc.entity.LunBo;
import com.gemptc.util.DruidDataSourceUtils;
import com.gemptc.util.JDBCUtils;

public class LunBoDaoImp implements LunBoDao{

	@Override
	public boolean addLunBo(LunBo lb) throws Exception {
		Connection conn = DruidDataSourceUtils.getConnection();
		String sql = "INSERT INTO t_lunbo (lb_image,lb_desc) VALUES(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, lb.getLb_image());
		ps.setString(2, lb.getLb_desc());
		int count = ps.executeUpdate();
		if(count!=0) {
			JDBCUtils.release(null, ps, conn);
			return true;
		}
		DruidDataSourceUtils.release(null, ps, conn);
		return false;
	}

	@Override
	public ArrayList<LunBo> getAllLunBo() throws Exception {
		Connection conn = DruidDataSourceUtils.getConnection();
		String sql = "SELECT * FROM t_lunbo";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<LunBo> array = new ArrayList<LunBo>();
		while(rs.next()) {
			LunBo lb = new LunBo();
			lb.setLb_id(rs.getInt("lb_id"));
			lb.setLb_image(rs.getString("lb_image"));
			lb.setLb_desc(rs.getString("lb_desc"));
			lb.setLb_state(rs.getInt("lb_state"));
			array.add(lb);
		}
		DruidDataSourceUtils.release(rs, ps, conn);
		return array;
	}

	@Override
	public int getTotalLunBo() throws Exception {
		Connection conn = DruidDataSourceUtils.getConnection();
		String sql = "SELECT count(*) AS count FROM t_lunbo";
		PreparedStatement ps = conn.prepareStatement(sql);
		int count = 0;
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			count = rs.getInt("count");
		}
		DruidDataSourceUtils.release(rs, ps, conn);
		return count;
	}

	@Override
	public boolean changeLunBo(int lb_id, int newstate) throws Exception {
		Connection conn = DruidDataSourceUtils.getConnection();
		String sql = "UPDATE t_lunbo SET lb_state=? where lb_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, newstate);
		ps.setInt(2, lb_id);
		int count = ps.executeUpdate();
		if(count!=0) {
			JDBCUtils.release(null, ps, conn);
			return true;
		}
		DruidDataSourceUtils.release(null, ps, conn);
		return false;
	}

	@Override
	public boolean deleteLunBoById(int id) throws Exception {
		Connection conn = DruidDataSourceUtils.getConnection();
		String sql = "DELETE FROM t_lunbo WHERE lb_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		int count = ps.executeUpdate();
		if(count!=0) {
			JDBCUtils.release(null, ps, conn);
			return true;
		}
		DruidDataSourceUtils.release(null, ps, conn);
		return false;
	}

	@Override
	public ArrayList<LunBo> getUsingLunBo() throws Exception {
		Connection conn = DruidDataSourceUtils.getConnection();
		String sql = "SELECT * FROM t_lunbo WHERE lb_state=1";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<LunBo> array = new ArrayList<LunBo>();
		while(rs.next()) {
			LunBo lb = new LunBo();
			lb.setLb_id(rs.getInt("lb_id"));
			lb.setLb_image(rs.getString("lb_image"));
			lb.setLb_desc(rs.getString("lb_desc"));
			lb.setLb_state(rs.getInt("lb_state"));
			array.add(lb);
		}
		DruidDataSourceUtils.release(rs, ps, conn);
		return array;
	}

}
