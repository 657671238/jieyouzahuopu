package com.gemptc.json;

import java.io.IOException;
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
 * Servlet implementation class UpdateProductJSONServlet
 */
@WebServlet("/updateProductJSON")
public class UpdateProductJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProductJSONServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取参数
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String pro_name = request.getParameter("pro_name");
		String pro_id = request.getParameter("pro_id");
		String pro_price = request.getParameter("pro_price");
		String pro_desc = request.getParameter("pro_desc");
		String pro_create = request.getParameter("pro_create");
		Product updateProduct = new Product();
		updateProduct.setPro_id(Integer.parseInt(pro_id));
		updateProduct.setPro_name(pro_name);
		updateProduct.setPro_price(Double.parseDouble(pro_price));
		updateProduct.setPro_desc(pro_desc);
		updateProduct.setPro_create(pro_create);
		// 判断获取的参数是否为空的逻辑
		ProductDao dao = new ProductDaoImp();
		try {
			if (dao.updateProduct(updateProduct)) {
				JSONResult.JSONReturnWithData("0", "更新成功", response);
			} else {
				JSONResult.JSONReturnWithData("1", "更新失败了", response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSONResult.JSONReturnWithData("3", "服务器繁忙", response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
