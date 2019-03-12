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
@WebServlet("/totalReMen")
public class TotalCountReMen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalCountReMen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao dao = new ProductDaoImp();
		int cateid = Integer.parseInt(request.getParameter("cateid"));
		try {
			int total = dao.getTotalReMenByProduct(cateid);
			JSONResult.JSONReturnWithData("0", total, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSONResult.JSONReturnWithData("1", "服务器繁忙", response);
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
