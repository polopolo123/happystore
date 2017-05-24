package user.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.entity.PageBean;
import user.entity.Product;
import user.service.ProductService;
import user.service.impl.ProductServiceImpl;

/**
 * 商品servlet
 */
public class ProductServlet extends BaseServlet {

	/**
	 * 通过id查询单个商品详情
	 */
	public String getById(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1.获取商品的id
		String pid = request.getParameter("pid");

		// 2.调用service
		ProductService ps = new ProductServiceImpl();
		Product product = ps.getByPid(pid);

		// 3.将结果放入request中 请求转发
		request.setAttribute("product", product);
		return "/user/jsp/product_info.jsp";
	}

	/**
	 * 分页查询数据
	 */
	public String findByPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		int currPage = Integer.parseInt(request.getParameter("currPage"));
		int pageSize = 12; // 设置每页显示的条数

		// 2.调用service 返回值pagebean
		ProductService productService = new ProductServiceImpl();;
		PageBean<Product> pageBean = productService.findByPage(currPage, pageSize, cid);

		// 3.将结果放入request中 请求转发
		request.setAttribute("pageBean", pageBean);
		return "/user/jsp/product_list.jsp";
	}

}
