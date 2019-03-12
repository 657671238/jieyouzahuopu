package com.gemptc.entity;

public class OrderItem {
	
	private String o_itemid;
	
	private int o_count;
	
	private double o_subtotal;
	
	//商品信息--外键
	private Product product;
	//订单信息--外键
	private Order order;
	public String getO_itemid() {
		return o_itemid;
	}
	public void setO_itemid(String o_itemid) {
		this.o_itemid = o_itemid;
	}
	public int getO_count() {
		return o_count;
	}
	public void setO_count(int o_count) {
		this.o_count = o_count;
	}
	public double getO_subtotal() {
		return o_subtotal;
	}
	public void setO_subtotal(double o_subtotal) {
		this.o_subtotal = o_subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "OrderItem [o_itemid=" + o_itemid + ", o_count=" + o_count + ", o_subtotal=" + o_subtotal + ", product="
				+ product + ", order=" + order + "]";
	}
}
