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
 * Servlet implementation class UpdateProductFileServlet
 */
@WebServlet("/changeLunBoState")
public class ChangeLunBoStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeLunBoStateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String temp_id = request.getParameter("lb_id");
		String tempstate = request.getParameter("newstate");
		if(temp_id!=null&&!temp_id.equals("")&&tempstate!=null&&!tempstate.equals("")) {
			try {
				int lb_id = Integer.parseInt(temp_id);
				int newstate = Integer.parseInt(tempstate);
				LunBoDao dao = new LunBoDaoImp();
				if(dao.changeLunBo(lb_id,newstate)) {
					JSONResult.JSONReturnWithData("0", "操作成功", response);
				}else {
					JSONResult.JSONReturnWithData("4", "操作失败", response);
				}
			}catch(NumberFormatException e){
				e.printStackTrace();
				JSONResult.JSONReturnWithData("3", "参数异常", response);
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
		doGet(request,response);
	}

}
