package user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.entity.Product;
import user.service.ProductService;
import user.service.impl.ProductServiceImpl;

public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ȥ���ݿ��в�ѯ������Ʒ��������Ʒ �����Ƿ���request���� ����ת��
		ProductService productService = new ProductServiceImpl();

		// ������Ʒ
		List<Product> newList = null;
		// ������Ʒ
		List<Product> hotList = null;

		try {
			newList = productService.findNew();
			hotList = productService.findHot();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ������list��������
		request.setAttribute("newList", newList);
		request.setAttribute("hotList", hotList);

		// ת����index.jspҳ��
		request.getRequestDispatcher("/user/jsp/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
