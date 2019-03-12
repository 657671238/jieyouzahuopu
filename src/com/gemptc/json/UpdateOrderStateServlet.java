package com.gemptc.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gemptc.dao.ProductDao;
import com.gemptc.dao.ProductDaoImp;
import com.gemptc.util.JSONResult;

/**
 * Servlet implementation class UpdateOrderStateServlet
 */
@WebServlet("/updateOrderState")
public class UpdateOrderStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrderStateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		JSONResult.JSONReturnWithData("1", "必须采用POST方式提交订单", response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String o_id = request.getParameter("o_id");
		if(o_id!=null&&!o_id.trim().equals("")) {
			ProductDao dao = new ProductDaoImp();
			try {
				if(dao.updateOrderStateSuccess(o_id)) {
					JSONResult.JSONReturnWithData("0", "支付成功了", response);
				}else {
					JSONResult.JSONReturnWithData("3", "订单更新失败", response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JSONResult.JSONReturnWithData("2", "服务器繁忙", response);
			}
		}else {
			JSONResult.JSONReturnWithData("1", "没有订单号参数", response);
		}
	}

}
