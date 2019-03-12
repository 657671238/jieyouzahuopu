package com.gemptc.dao;

import java.util.ArrayList;

import com.gemptc.entity.User;

public interface UserDao {
	
	//查询的t_admin表
	public User searchUserByUserName(String username) throws Exception;
    //查询的t_user表
	public User searchFrontUserByUserName(String username) throws Exception;
	
	public int register(User user)throws Exception;
	
	public int getTotalUser()throws Exception;
	
	public ArrayList<User> getAllUser(int start, int length)throws Exception;
	public boolean changeUserState(int u_id, int newstate)throws Exception;
	public User searchAdminByUserName(String username)throws Exception;

}
