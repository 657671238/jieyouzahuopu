package com.gemptc.entity;

public class User {
	
	private int u_id;
	private String u_name;
	private String u_password;
	private String u_addtime;
	private String u_status;
	private String u_email;
	private String u_telephone;
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_password() {
		return u_password;
	}
	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	public String getU_addtime() {
		return u_addtime;
	}
	public void setU_addtime(String u_addtime) {
		this.u_addtime = u_addtime;
	}
	public String getU_status() {
		return u_status;
	}
	public void setU_status(String u_status) {
		this.u_status = u_status;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	public String getU_telephone() {
		return u_telephone;
	}
	public void setU_telephone(String u_telephone) {
		this.u_telephone = u_telephone;
	}
	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", u_name=" + u_name + ", u_password=" + u_password + ", u_addtime=" + u_addtime
				+ ", u_status=" + u_status + ", u_email=" + u_email + ", u_telephone=" + u_telephone + "]";
	}
}
