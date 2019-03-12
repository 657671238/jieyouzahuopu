package com.gemptc.json;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gemptc.dao.CategoryDao;
import com.gemptc.dao.CategoryDaoImp;
import com.gemptc.entity.Category;
import com.gemptc.util.JSONResult;

/**
 * Servlet implementation class GetCategoryServlet
 */
@WebServlet("/getCategory")
public class GetCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		CategoryDao dao = new CategoryDaoImp();
		try {
			ArrayList<Category> allCategory = dao.getAllCategory();
			if(allCategory.size()>0) {
				JSONResult.JSONReturnWithData("0", allCategory, response);
			}else {
				JSONResult.JSONReturnWithData("1", "暂无分类信息", response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSONResult.JSONReturnWithData("2", "服务器繁忙", response);
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
