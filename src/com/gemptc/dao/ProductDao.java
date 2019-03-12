package com.gemptc.dao;

import java.util.ArrayList;
import java.util.List;

import com.gemptc.entity.Order;
import com.gemptc.entity.OrderItem;
import com.gemptc.entity.Product;

//Dao    Data Access Object
public interface ProductDao {
	
	//接口里面都是抽象的方法  需要类去实现
	public List<Product>selectAllProduct() throws Exception;

	public boolean insertProduct(Product insertProduct) throws Exception;
	
	public Product selectProductById(int pro_id) throws Exception;

	public boolean updateProduct(Product updateProduct) throws Exception;

	public boolean deleteProductById(String pro_id) throws Exception;


	//还需要分页的参数
	public ArrayList<Product> getProductByCateId(String cate_id,String start,String length) throws Exception;

	public ArrayList<Product> searchContent(String searchContent, String start, String length)throws Exception;

	public int getTotalCountByProduct(int cateid)throws Exception;

	public List<Product> selectProductByPage(String start, String length) throws Exception;

	public boolean insertOrder(Order order) throws Exception;
	
	public boolean insertOrderItem(OrderItem orderItem) throws Exception;

	//这个提交订单 包含上面的2个方法
	public boolean submitOrder(Order order);
	
	public boolean updateOrderStateSuccess(String o_id) throws Exception;

	public List<Order> selectOrdersByUserId(String u_id,String start,String length) throws Exception;

	public List<OrderItem> selectOrderItemByOrderId(String o_id) throws Exception;
	
	public List<Order> getOrdersByCate(int cateid, int start, int length)throws Exception;
	
	
	public ArrayList<Product> getReMenByCateId(String cate_id, String start, String length) throws Exception;

	public int getTotalReMenByProduct(int cateid)throws Exception;

	public boolean addReMen(String pro_id) throws Exception;

	public boolean removeReMen(String pro_id) throws Exception;

	public int getOrdersCountByUid(int u_id)throws Exception;

	public int getOrdersCountByCate(int cateid)throws Exception;

}
