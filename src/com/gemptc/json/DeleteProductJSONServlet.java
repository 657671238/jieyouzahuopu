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
 * Servlet implementation class DeleteProductJSONServlet
 */
@WebServlet("/deleteProductJSON")
public class DeleteProductJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductJSONServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String pro_id = request.getParameter("pro_id");
		if(pro_id!=null&&!pro_id.trim().equals("")) {
			ProductDao dao = new ProductDaoImp();
			try {
				Product p = dao.selectProductById(Integer.parseInt(pro_id));
				if(p!=null) {
					//开始删除
					if(dao.deleteProductById(pro_id)) {
						JSONResult.JSONReturnWithData("0", "删除成功", response);
					}else {
						JSONResult.JSONReturnWithData("1", "删除失败了", response);
					}
				}else {
					JSONResult.JSONReturnWithData("2", "对应的商品id不存在", response);
				}
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JSONResult.JSONReturnWithData("3", "数据库操作异常", response);
			}	
		}else {
			JSONResult.JSONReturnWithData("4", "没有给商品id参数", response);
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
