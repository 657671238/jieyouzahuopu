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
import com.gemptc.entity.Product;
import com.gemptc.entity.ResultObj;
import com.gemptc.util.JSONResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SelectProductJSONServlet
 */
@WebServlet("/selectProductJSON")
public class SelectProductJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectProductJSONServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao  dao = new ProductDaoImp();  
		//多态
		//try...catch  和  throws Exception有什么区别  有区别的？
		// 需要判断各种状态 
		// 1 数据库没有数据
		// 2 查询异常了
		// 3服务器异常了
		response.setContentType("text/html;charset=UTF-8");
		String start = request.getParameter("start");
		String length = request.getParameter("length");
		if(start!=null&&!start.trim().equals("")&&length!=null&&!length.trim().equals("")) {
			ResultObj result = new ResultObj();
			try {
				 List<Product> allProduct = dao.selectProductByPage(start,length);
				 if(allProduct.size()>0) {
					 result.setRetcode("0");
					 result.setData(allProduct);
					//JAVA中需要将JAVA对象转换为JSON字符串   实现前后端的数据分离
					 String resultStr = JSONObject.fromObject(result).toString();
					 response.getWriter().write(resultStr);
				 }else {
					 result.setRetcode("1");
					 result.setData("数据库暂时没有数据");
					 String resultStr = JSONObject.fromObject(result).toString();
					 response.getWriter().write(resultStr);
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.setRetcode("2");
				result.setData("服务器繁忙");
				String resultStr = JSONObject.fromObject(result).toString();
				response.getWriter().write(resultStr);
			}
		}else {
			  JSONResult.JSONReturnWithData("3", "没有分页的参数信息", response);
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
