package admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.entity.User;
import admin.service.AdminService;
import admin.service.impl.AdminServiceImpl;

public class AFindUserByStateServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0.��ȡ����
		String viewstate = request.getParameter("viewstate");
		
		// 1.����AdminService
		AdminService adminService = new AdminServiceImpl();
		List<User> listUser = null;
		try {
			listUser = adminService.findUserByState(viewstate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 2 ��ҳ����������ֱ�־���浽request��
		// �����list2.jsp�ж���viewnumber==1����ʾ��ע��ҳ��
		request.setAttribute("viewnumber", Integer.parseInt(viewstate));
		
		// 3.��listUser��ӵ�request�У�����ת��
		request.setAttribute("listUser", listUser);
		request.getRequestDispatcher("/admin/auser/list2.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
