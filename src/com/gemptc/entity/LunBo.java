package com.gemptc.entity;

public class LunBo {
	private int lb_id;
	private String lb_image;
	private String lb_desc;
	private int lb_state;
	public int getLb_id() {
		return lb_id;
	}
	public void setLb_id(int lb_id) {
		this.lb_id = lb_id;
	}
	public String getLb_image() {
		return lb_image;
	}
	public void setLb_image(String lb_image) {
		this.lb_image = lb_image;
	}
	public String getLb_desc() {
		return lb_desc;
	}
	public void setLb_desc(String lb_desc) {
		this.lb_desc = lb_desc;
	}
	public int getLb_state() {
		return lb_state;
	}
	public void setLb_state(int lb_state) {
		this.lb_state = lb_state;
	}
	@Override
	public String toString() {
		return "LunBo [lb_id=" + lb_id + ", lb_image=" + lb_image + ", lb_desc=" + lb_desc + ", lb_state=" + lb_state
				+ "]";
	}
	
}
