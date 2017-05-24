package user.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.entity.PageBean;
import user.entity.Product;
import user.service.ProductService;
import user.service.impl.ProductServiceImpl;

/**
 * ��Ʒservlet
 */
public class ProductServlet extends BaseServlet {

	/**
	 * ͨ��id��ѯ������Ʒ����
	 */
	public String getById(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1.��ȡ��Ʒ��id
		String pid = request.getParameter("pid");

		// 2.����service
		ProductService ps = new ProductServiceImpl();
		Product product = ps.getByPid(pid);

		// 3.���������request�� ����ת��
		request.setAttribute("product", product);
		return "/user/jsp/product_info.jsp";
	}

	/**
	 * ��ҳ��ѯ����
	 */
	public String findByPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		int currPage = Integer.parseInt(request.getParameter("currPage"));
		int pageSize = 12; // ����ÿҳ��ʾ������

		// 2.����service ����ֵpagebean
		ProductService productService = new ProductServiceImpl();;
		PageBean<Product> pageBean = productService.findByPage(currPage, pageSize, cid);

		// 3.���������request�� ����ת��
		request.setAttribute("pageBean", pageBean);
		return "/user/jsp/product_list.jsp";
	}

}
