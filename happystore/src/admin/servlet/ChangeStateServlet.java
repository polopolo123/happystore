package admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.AUserService;
import admin.service.impl.AUserServiceImpl;

public class ChangeStateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 获取参数
		String uid = request.getParameter("uid");
		String state = request.getParameter("state");
		
		// 2.调用AdminService
		AUserService aUserService = new AUserServiceImpl();
		try {
			aUserService.changeState(uid,state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 3.回到list页面
		request.getRequestDispatcher("/admin/AFindUser").forward(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
