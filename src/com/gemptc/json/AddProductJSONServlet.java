package com.gemptc.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gemptc.dao.ProductDao;
import com.gemptc.dao.ProductDaoImp;
import com.gemptc.entity.Product;
import com.gemptc.entity.ResultObj;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddProductJSONServlet
 */
@WebServlet("/addProductJSON")
public class AddProductJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductJSONServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		ResultObj result = new ResultObj();
		result.setRetcode("4");
		result.setData("请采用POST提交数据");
		response.getWriter().write(JSONObject.fromObject(result).toString());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String pro_name = request.getParameter("pro_name");
		String pro_create = request.getParameter("pro_create");
		System.out.println("pro_create:"+pro_create);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date tempDate = new Date(new Long(pro_create));
		pro_create = sdf.format(tempDate);
		
		ResultObj result = new ResultObj();
		double pro_price = 0;
		try {
			pro_price = Double.parseDouble(request.getParameter("pro_price"));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			result.setRetcode("5");
			result.setData("没有提交价格参数");
			response.getWriter().write(JSONObject.fromObject(result).toString());
			return;
		}
		System.out.println("continue");
		String pro_desc = request.getParameter("pro_desc");
		System.out.println("desc:"+pro_desc);
		System.out.println("name:"+pro_name);
		
		if(pro_name!=null&&!pro_name.trim().equals("")&&pro_desc!=null&&!pro_desc.trim().equals("")) {
			//插入数据库
			Product insertProduct = new Product();
			insertProduct.setPro_name(pro_name);
			insertProduct.setPro_price(pro_price);
			insertProduct.setPro_desc(pro_desc);
			insertProduct.setPro_create(pro_create);
			ProductDao dao = new ProductDaoImp();
			try {
				if(dao.insertProduct(insertProduct)) {
					result.setRetcode("0");
					result.setData("插入成功");
					response.getWriter().write(JSONObject.fromObject(result).toString());
				}else {
					result.setRetcode("1");
					result.setData("插入失败");
					response.getWriter().write(JSONObject.fromObject(result).toString());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.setRetcode("2");
				result.setData("服务器繁忙");
				response.getWriter().write(JSONObject.fromObject(result).toString());
			}
		}else {
			result.setRetcode("3");
			result.setData("没有提交参数");
			response.getWriter().write(JSONObject.fromObject(result).toString());
		}
	}

}
