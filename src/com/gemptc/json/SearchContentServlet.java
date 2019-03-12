package com.gemptc.json;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gemptc.dao.ProductDao;
import com.gemptc.dao.ProductDaoImp;
import com.gemptc.entity.Product;
import com.gemptc.util.JSONResult;

/**
 * Servlet implementation class SearchContentServlet
 */
@WebServlet("/searchContent")
public class SearchContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		String searchContent = request.getParameter("search");
		String start = request.getParameter("start");
		String length = request.getParameter("length");
		if(searchContent!=null&&!searchContent.trim().equals("")) {
			ProductDao dao = new ProductDaoImp();
			try {
				ArrayList<Product> searchResult = dao.searchContent(searchContent,start,length);
				if(searchResult.size()>0) {
					JSONResult.JSONReturnWithData("0", searchResult, response);
				}else {
					JSONResult.JSONReturnWithData("2", "查询的商品不存在", response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JSONResult.JSONReturnWithData("3", "服务器繁忙", response);
			}		
		}else {
			JSONResult.JSONReturnWithData("1", "没有对应的参数", response);
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
