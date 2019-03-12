package com.gemptc.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gemptc.dao.LunBoDao;
import com.gemptc.dao.LunBoDaoImp;
import com.gemptc.util.JSONResult;

/**
 * Servlet implementation class DeleteProductJSONServlet
 */
@WebServlet("/deleteLunBoById")
public class DeleteLunBoByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteLunBoByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String lb_id = request.getParameter("lb_id");
		if(lb_id!=null&&!lb_id.trim().equals("")) {
			LunBoDao dao = new LunBoDaoImp();
			try {
				int id = Integer.parseInt(lb_id);
				if(dao.deleteLunBoById(id)) {
					JSONResult.JSONReturnWithData("0", "删除成功", response);
				}else {
					JSONResult.JSONReturnWithData("6", "不存在该商品", response);
				}
			}catch(NumberFormatException e) {
				e.printStackTrace();
				JSONResult.JSONReturnWithData("5", "参数异常", response);
			}catch (Exception e) {
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
