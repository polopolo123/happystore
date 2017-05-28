package business.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.service.ProductService;
import user.service.impl.ProductServiceImpl;
import business.entity.Business;
import business.entity.Category;
import business.entity.Product;
import business.entity.Promotion;
import business.service.BusinessService;
import business.service.impl.BusinessServiceImpl;

/**
 * �̼Ҳ�Ʒ����ģ��
 */
public class BusinessServlet_b extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ��ѯ�õ���ṩ����Ʒ�������б�չʾ
	 */
	public String findAllByBid(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 1.��ȡbid
		Business business = (Business) request.getSession().getAttribute(
				"Business");
		String bid = business.getBid();

		// 2.����BusinessService ͨ��bid��ȡ��Ʒ�б�
		BusinessService businessService = new BusinessServiceImpl();
		List<Product> productList = businessService.getListById(bid);

		// 3.��ӵ�request�У�����ת��
		request.setAttribute("productList", productList);
		return "/business/product/list.jsp";
	}

	/**
	 * �Ȳ�ѯ�������ͺͲ�Ʒ���࣬��ת�����ҳ�棬
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ����BusinessService ��ȡ��Ʒ�����б�
		BusinessService businessService = new BusinessServiceImpl();
		List<Category> categoryList = businessService.findCategory();
		List<Promotion> promotionList = businessService.findPromotion();

		// ��ӵ�request�У�����ת��
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("promotionList", promotionList);
		return "/business/product/add.jsp";
	}

	/**
	 * ͨ��pidɾ����Ʒ
	 */
	public String deleteById(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 1.��ȡpid
		String pid = request.getParameter("pid");

		// 2.����BusinessService ͨ��pidɾ����Ʒ
		BusinessService businessService = new BusinessServiceImpl();
		businessService.deleteById(pid);

		// 3.����ת��
		return "/Business_Pro?method=findAllByBid";
	}

	/**
	 * ��ȡ��pid����ѯ���ݺ󷵻ص�edit.jsp
	 */
	public String editUI(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 1.��ȡpid
		String pid = request.getParameter("pid");

		// 2.����Service ͨ��pid��ȡ��Ʒ
		ProductService ProductService = new ProductServiceImpl();
		user.entity.Product product = ProductService.getByPid(pid);

		// 3.����BusinessService ��ȡ��Ʒ�����б�
		BusinessService businessService = new BusinessServiceImpl();
		List<Category> categoryList = businessService.findCategory();
		List<Promotion> promotionList = businessService.findPromotion();

		// ��ӵ�request�У�����ת��
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("promotionList", promotionList);
		request.setAttribute("bproduct", product);
		return "/business/product/edit.jsp";
	}

}
