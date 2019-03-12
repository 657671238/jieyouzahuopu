package com.gemptc.json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gemptc.dao.ProductDao;
import com.gemptc.dao.ProductDaoImp;
import com.gemptc.entity.Order;
import com.gemptc.util.JSONResult;

/**
 * Servlet implementation class GetUserOrdersServlet
 */
@WebServlet("/getUserOrders")
public class GetUserOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserOrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		JSONResult.JSONReturnWithData("1", "必须采用POST获取订单信息", response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String u_id = request.getParameter("u_id");
		String start = request.getParameter("start");
		String length = request.getParameter("length");
		if(u_id!=null&&!u_id.trim().equals("")&&start!=null&&!start.trim().equals("")&&length!=null&&!length.trim().equals("")) {
			ProductDao dao = new ProductDaoImp();
			try {
				List<Order> orders = dao.selectOrdersByUserId(u_id,start,length);
				if(orders.size()>0) {
					JSONResult.JSONReturnWithData("0", orders, response);
				}else {
					JSONResult.JSONReturnWithData("1", "暂无订单信息", response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JSONResult.JSONReturnWithData("3", "服务器繁忙", response);
			}
		}else {
			JSONResult.JSONReturnWithData("2", "必须提交当前用户id查询订单", response);
		}
	}
}
