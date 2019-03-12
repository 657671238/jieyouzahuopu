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
@WebServlet("/getOrdersByCate")
public class GetOrdersByCateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrdersByCateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String tempcateid = request.getParameter("cateid");
		String tempstart = request.getParameter("start");
		String templength = request.getParameter("length");
		if(tempcateid!=null&&!tempcateid.trim().equals("")&&tempstart!=null&&!tempstart.trim().equals("")&&templength!=null&&!templength.trim().equals("")) {
			ProductDao dao = new ProductDaoImp();
			try {
				int cateid = Integer.parseInt(tempcateid);
				int start = Integer.parseInt(tempstart);
				int length = Integer.parseInt(templength);
				List<Order> orders = dao.getOrdersByCate(cateid,start,length);
				if(orders.size()>0) {
					JSONResult.JSONReturnWithData("0", orders, response);
				}else {
					JSONResult.JSONReturnWithData("4", "暂无订单信息", response);
				}
			}catch(NumberFormatException e){
				e.printStackTrace();
				JSONResult.JSONReturnWithData("3", "参数格式异常", response);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JSONResult.JSONReturnWithData("2", "服务器繁忙", response);
			}
		}else {
			JSONResult.JSONReturnWithData("1", "缺少参数", response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
