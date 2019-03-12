package com.gemptc.json;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gemptc.dao.UserDao;
import com.gemptc.dao.UserDaoImp;
import com.gemptc.entity.ResultObj;
import com.gemptc.entity.User;
import com.gemptc.thirdcode.GeetestConfig;
import com.gemptc.thirdcode.GeetestLib;
import com.gemptc.util.JSONResult;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddProductJSONServlet
 */
@WebServlet("/userRegister")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
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
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(), 
				GeetestConfig.isnewfailback());
			
		String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
		String validate = request.getParameter(GeetestLib.fn_geetest_validate);
		String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);
		
		//从session中获取gt-server状态
		int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
		
		//从session中获取userid
		String userid = (String)request.getSession().getAttribute("userid");
		
		//自定义参数,可选择添加
		HashMap<String, String> param = new HashMap<String, String>(); 
		param.put("user_id", userid); //网站用户id
		param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
		param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP
		
		System.out.println(param);
		
		int gtResult = 0;

		if (gt_server_status_code == 1) {
			//gt-server正常，向gt-server进行二次验证
				
			gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
			System.out.println(gtResult);
		} else {
			// gt-server非正常情况下，进行failback模式验证
				
			System.out.println("failback:use your own server captcha validate");
			gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
			System.out.println(gtResult);
		}
		
		if (gtResult == 1) {
			// 验证成功
			// 走后面 登录的逻辑   登录去验证用户名和密码
			String u_name = request.getParameter("name");
			String u_password = request.getParameter("password");
			UserDao dao = new UserDaoImp();
			if(u_name!=null&&!u_name.equals("")&&u_password!=null&&!u_password.equals("")) {
				User user = null;
				try {
					user = dao.searchFrontUserByUserName(u_name);
				} catch (Exception e1) {
					e1.printStackTrace();
					JSONResult.JSONReturnWithData("3", "服务器异常", response);
				}
				if(user == null) {
					//用户不存在，则可以注册用户
					User insertUser = new User();
					insertUser.setU_name(u_name);
					insertUser.setU_password(u_password);
					try {
						int count = dao.register(insertUser);
						if(count>0) {
							JSONResult.JSONReturnWithData("0", "注册成功", response);
						}else {
							JSONResult.JSONReturnWithData("4", "注册失败", response);
						}
					} catch (Exception e) {
						e.printStackTrace();
						JSONResult.JSONReturnWithData("3", "服务器异常", response);
					}
				}else {
					JSONResult.JSONReturnWithData("2", "用户已存在", response);
				}
			}else {
				JSONResult.JSONReturnWithData("1", "缺少参数", response);
			}
		}
		else {
			// 验证失败
			JSONResult.JSONReturnWithData("5", "验证码验证失败", response);
		}
		
		
	}

}
