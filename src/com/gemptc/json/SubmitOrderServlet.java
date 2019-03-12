package com.gemptc.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gemptc.dao.ProductDao;
import com.gemptc.dao.ProductDaoImp;
import com.gemptc.entity.Order;
import com.gemptc.entity.OrderItem;
import com.gemptc.entity.Product;
import com.gemptc.entity.User;
import com.gemptc.util.JSONResult;
import com.gemptc.util.StringUUID;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class SubmitOrderServlet
 */
@WebServlet("/submitOrder")
public class SubmitOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		JSONResult.JSONReturnWithData("1", "必须采用POST方式提交订单", response);
		
		
		/*
		String u_id = request.getParameter("u_id"); 
		String o_name = request.getParameter("o_name");
		String o_tel = request.getParameter("o_tel"); 
		String o_address = request.getParameter("o_address"); 
		String selectCartList = request.getParameter("selectCartList");
		System.out.println(u_id+"---"+o_name+"--"+o_tel+"--"+o_address);
		System.out.println("购物车数据:"+selectCartList);
		response.getWriter().write("测试数据");
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//采用另外一种方式接收数据
	    //java的服务器接收json数据  不是FORM表格格式的数据
		//获取流对象   请求参数中的二进制流对象   这个跟前面的表单方式的数据是不一样的
		BufferedReader  br = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
		StringBuilder str = new StringBuilder();
		String line = null;
		while((line = br.readLine())!=null) {
			str.append(line);
		}
		System.out.println(str.toString());
		//将JSON字符串  转为 JAVA对象
		JSONObject object = JSONObject.fromObject(str.toString());
		String u_id = object.getString("u_id");
		String o_address = object.getString("o_address");
		String o_name = object.getString("o_name");
		String o_tel = object.getString("o_tel");
		double o_total = object.getDouble("o_total");
		String selectCartList = object.getString("selectCartList");
		if(u_id!=null&&o_address!=null&&o_name!=null&&o_tel!=null&&selectCartList!=null) {
			//现在讲数据存储到Order对象中
			Order order = new Order();
			order.setO_id(StringUUID.getAllUUID());
			order.setO_address(o_address);
			order.setO_name(o_name);
			order.setO_state(0); //0 表示没有支付
			order.setO_tel(o_tel);
			order.setO_total(o_total);//总价信息
			User user =new User();
			user.setU_id(Integer.parseInt(u_id));
			order.setUser(user);
			//订单项 selectCartList
		    JSONObject cartListObject = JSONObject.fromObject(selectCartList);
			//采用遍历key-value的方式
		    Iterator<String> it = cartListObject.keys(); 
		    //存储日期格式 订单项
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		    Date date = new Date();
	    	String currentDateStr = sdf.format(date);

	    	sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	order.setO_ordertime(sdf.format(date));

		    while(it.hasNext()){
		    	String key = it.next(); 
		    	String productStr = cartListObject.getString(key);    
		    	System.out.println("key: "+key+",value:"+productStr);
		    	JSONObject product = JSONObject.fromObject(productStr);
		    	OrderItem orderItem = new OrderItem();
		    	int pro_num = product.getInt("pro_num");
		    	double pro_price = product.getDouble("pro_price");
		    	orderItem.setO_count(product.getInt("pro_num"));
		    	orderItem.setO_itemid(currentDateStr+StringUUID.getAllUUID().substring(22));
		    	orderItem.setO_subtotal(pro_num*pro_price);
		    	orderItem.setOrder(order);
		    	Product tempProduct = new Product();
		    	tempProduct.setPro_id(Integer.parseInt(key));
		    	orderItem.setProduct(tempProduct);
		    	order.getOrderItems().add(orderItem);
		    }
		    ProductDao productDao = new ProductDaoImp();
		    try {
				if(productDao.submitOrder(order)) {
					JSONResult.JSONReturnWithData("0", order.getO_id(), response);
				}else {
					JSONResult.JSONReturnWithData("3", "订单提交失败", response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JSONResult.JSONReturnWithData("2", "服务器繁忙", response);
			}
		}else {
		   JSONResult.JSONReturnWithData("1", "提交POST的参数错误", response);	
		}
	}

}
