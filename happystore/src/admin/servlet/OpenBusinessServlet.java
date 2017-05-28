package admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.ABusinessService;
import admin.service.impl.ABusinessServiceImpl;

public class OpenBusinessServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. ��ȡ����
		String bid = request.getParameter("bid");

		// 2.����ABusinessService
		ABusinessService aBusinessService = new ABusinessServiceImpl();
		try {
			aBusinessService.openBusiness(bid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 3.�ص�listҳ��
		request.getRequestDispatcher("/admin/AFindBusiness").forward(request,
				response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}