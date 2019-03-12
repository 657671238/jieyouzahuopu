package com.gemptc.json;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gemptc.dao.UserDao;
import com.gemptc.dao.UserDaoImp;
import com.gemptc.entity.User;
import com.gemptc.util.JSONResult;

@WebServlet("/getAllUser")
public class GetAllUser extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String tempstart = request.getParameter("start");
		String templength = request.getParameter("length");
		UserDao dao = new UserDaoImp();
		if(tempstart!=null&&!tempstart.equals("")&&templength!=null&&!templength.equals("")) {
			try {
				int start = Integer.parseInt(tempstart);
				int length = Integer.parseInt(templength);
				ArrayList<User> array = dao.getAllUser(start,length);
				JSONResult.JSONReturnWithData("0", array, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				JSONResult.JSONReturnWithData("3", "参数格式错误", response);
			}catch(Exception e) {
				e.printStackTrace();
				JSONResult.JSONReturnWithData("2", "服务器异常", response);
			}
		}else {
			JSONResult.JSONReturnWithData("1", "缺少参数", response);
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
