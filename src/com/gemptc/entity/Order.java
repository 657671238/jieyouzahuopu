package com.gemptc.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private String o_id;
	private String o_ordertime;
	private int o_state;
	private double o_total;
	private String o_address;
	private String o_tel;
	private String o_name;
	//订单项
	List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	//订单的用户信息
	private User user;

	public String getO_id() {
		return o_id;
	}

	public void setO_id(String o_id) {
		this.o_id = o_id;
	}

	public String getO_ordertime() {
		return o_ordertime;
	}

	public void setO_ordertime(String o_ordertime) {
		this.o_ordertime = o_ordertime;
	}

	public int getO_state() {
		return o_state;
	}

	public void setO_state(int o_state) {
		this.o_state = o_state;
	}

	public double getO_total() {
		return o_total;
	}

	public void setO_total(double o_total) {
		this.o_total = o_total;
	}

	public String getO_address() {
		return o_address;
	}

	public void setO_address(String o_address) {
		this.o_address = o_address;
	}

	public String getO_tel() {
		return o_tel;
	}

	public void setO_tel(String o_tel) {
		this.o_tel = o_tel;
	}

	public String getO_name() {
		return o_name;
	}

	public void setO_name(String o_name) {
		this.o_name = o_name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [o_id=" + o_id + ", o_ordertime=" + o_ordertime + ", o_state=" + o_state + ", o_total=" + o_total
				+ ", o_address=" + o_address + ", o_tel=" + o_tel + ", o_name=" + o_name + ", user=" + user + "]";
	}
	
	
	

}
