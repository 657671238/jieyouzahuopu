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
 * Servlet implementation class QueryProductByIdJSONServlet
 */
@WebServlet("/queryProductByIdJSON")
public class QueryProductByIdJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryProductByIdJSONServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//需要查询数据
		String pro_id = request.getParameter("pro_id");
		if(pro_id!=null&&!pro_id.trim().equals("")) {
			ProductDao dao = new ProductDaoImp();
			try {
				Product product = dao.selectProductById(Integer.parseInt(pro_id));
				if(product!=null) {
					JSONResult.JSONReturnWithData("0", product, response);
				}else {
					JSONResult.JSONReturnWithData("2", "商品不存在", response);
				}
			} catch (Exception e) {
				JSONResult.JSONReturnWithData("3", "查询数据库异常", response);
			}
		}else {
			//告诉客户端一个状态吗  告诉它错误的信息   5行 代码    封装为1行代码  这个是一工具  自定义的工具
			JSONResult.JSONReturnWithData("1", "没有提交pro_d参数或者参数值为空", response);
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
