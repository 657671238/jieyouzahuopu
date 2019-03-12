package com.gemptc.entity;

public class Product {
	private int pro_id;
	private String pro_name;
	private double pro_price;
	private String pro_desc;
	//思考 如果采用 Date 怎么实现
	private String pro_create;
	
	private String pro_image;
	//将分类的对象 作为 Product的属性     类似  外键的概念
	// SSH   SpringMVC  Spring      Hibernate (使用比较少)
	// SSH   Struts2    Spring      Hibernate
	          
	//Service(Servlet)  管理所有的对象的创建     dao层  根据数据库进行操作的
	
	// SSM   SpringMVC  Spring      Mybatis  (使用比较多)
	// SSM   Struts2    Spring      Mybatis
	private Category cate;
	
	private int remen;
	
	public int getRemen() {
		return remen;
	}
	public void setRemen(int remen) {
		this.remen = remen;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public double getPro_price() {
		return pro_price;
	}
	public void setPro_price(double pro_price) {
		this.pro_price = pro_price;
	}
	public String getPro_desc() {
		return pro_desc;
	}
	public void setPro_desc(String pro_desc) {
		this.pro_desc = pro_desc;
	}
	public String getPro_create() {
		return pro_create;
	}
	public void setPro_create(String pro_create) {
		this.pro_create = pro_create;
	}
	public Category getCate() {
		return cate;
	}
	public void setCate(Category cate) {
		this.cate = cate;
	}
	public String getPro_image() {
		return pro_image;
	}
	public void setPro_image(String pro_image) {
		this.pro_image = pro_image;
	}
	@Override
	public String toString() {
		return "Product [pro_id=" + pro_id + ", pro_name=" + pro_name + ", pro_price=" + pro_price + ", pro_desc="
				+ pro_desc + ", pro_create=" + pro_create + ", pro_image=" + pro_image + ", cate=" + cate
				+ ",remen=" + remen +  "]";
	}
}
