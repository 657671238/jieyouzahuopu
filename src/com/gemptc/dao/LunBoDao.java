package com.gemptc.dao;

import java.util.ArrayList;

import com.gemptc.entity.LunBo;

public interface LunBoDao {
	public boolean addLunBo(LunBo lb)throws Exception;

	public ArrayList<LunBo> getAllLunBo()throws Exception;

	public int getTotalLunBo()throws Exception;

	public boolean changeLunBo(int lb_id, int newstate)throws Exception;

	public boolean deleteLunBoById(int id)throws Exception;

	public ArrayList<LunBo> getUsingLunBo()throws Exception;
}
