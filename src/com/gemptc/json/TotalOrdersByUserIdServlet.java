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
 * Servlet implementation class TotalCountProductServlet
 */
@WebServlet("/totalOrders")
public class TotalOrdersByUserIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalOrdersByUserIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		ProductDao dao = new ProductDaoImp();
		if(userid!=null&&!userid.equals("")) {
			try {
				int u_id = Integer.parseInt(userid);
				int count = dao.getOrdersCountByUid(u_id);
				JSONResult.JSONReturnWithData("0", count, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				JSONResult.JSONReturnWithData("3", "参数格式异常", response);
			}catch(Exception e) {
				e.printStackTrace();
				JSONResult.JSONReturnWithData("2", "服务器异常", response);
			}
		}else {
			JSONResult.JSONReturnWithData("1", "缺少参数", response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
