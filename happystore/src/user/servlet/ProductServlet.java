package user.servlet;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.Cookie;
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

	private String createValue(HttpServletRequest request,String id) {
		
		Cookie[] cookies = request.getCookies();
		String prodHist = null;
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("prodHist")){
					prodHist = cookie.getValue();
					break;
				}
			}
		}
		
		// null��û��prodHist
		if(cookies==null || prodHist==null){
			//ֱ�ӷ��ش����id
			return id;
		}
		
		//String -> String[] ->  Collection :Ϊ�˷����ж��ظ�id
		String[] ids = prodHist.split("-");
		Collection colls = Arrays.asList(ids); 
		// Collection -> LinkedList
		LinkedList list = new LinkedList(colls);
		
		//������7��
		if(list.size()<7){
			//id�ظ�
			if(list.contains(id)){
				//ȥ���ظ�id���Ѵ����id����ǰ��
				list.remove(id);
				list.addFirst(id);
			}else{
				//ֱ�ӰѴ����id����ǰ��
				list.addFirst(id);
			}
		}else{
			//id�ظ�
			if(list.contains(id)){
				//ȥ���ظ�id���Ѵ����id����ǰ��
				list.remove(id);
				list.addFirst(id);
			}else{
				//ȥ����id���Ѵ����id����ǰ��
				list.removeLast();
				list.addFirst(id);
			}
		}
		
		// LinedList -> String 
		StringBuffer sb = new StringBuffer();
		for (Object object : list) {
			sb.append(object+"-");
		}
		//ȥ������-��
		String result = sb.toString();
		result = result.substring(0, result.length()-1);
		return result;
	}
	
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
		
		// 3.�˴���Ҫ���������¼
		// 3.1.����cookie
		Cookie cookie = new Cookie("prodHist",createValue(request,pid));
		cookie.setMaxAge(1*7*24*60*60);//һ��
		// 3.2.����cookie
		response.addCookie(cookie);
		
		// 4.���������request�� ����ת��
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

		// 3.��ʾ���������Ʒ
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			List<Product> hisList = new LinkedList<Product>();
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("prodHist")){
					String prodHist = cookie.getValue(); 
					String[] ids = prodHist.split("-");
					//�������������Ʒid
					for (String id : ids) {
						Product product = productService.getByPid(id);
						hisList.add(product);
					}
				}
				
			}
			request.setAttribute("hisList", hisList);
		}
		
		// 4.���������request�� ����ת��
		request.setAttribute("pageBean", pageBean);
		return "/user/jsp/product_list.jsp";
	}

}
