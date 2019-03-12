package com.gemptc.json;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gemptc.dao.LunBoDao;
import com.gemptc.dao.LunBoDaoImp;
import com.gemptc.entity.LunBo;
import com.gemptc.util.JSONResult;

@WebServlet("/getLunBo")
public class GetLunBoServlet extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		LunBoDao dao = new LunBoDaoImp();
		try {
			ArrayList<LunBo> array = dao.getAllLunBo();
			JSONResult.JSONReturnWithData("0", array, response);
		} catch (Exception e) {
			e.printStackTrace();
			JSONResult.JSONReturnWithData("1", "服务器异常", response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
