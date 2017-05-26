package user.servlet;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.entity.Cart;
import user.entity.CartItem;
import user.entity.Order;
import user.entity.OrderItem;
import user.entity.PageBean;
import user.entity.User;
import user.service.OrderService;
import user.service.impl.OrderServiceImpl;

/**
 * ����ģ��
 */
public class OrderServlet extends BaseServlet {

	// ��Ӷ���
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 0.�ж��û��Ƿ��¼
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("msg",
					"������δ��¼������<a href='" + request.getContextPath()
							+ "/User?method=loginUI'>��¼</a>");
			return "/user/jsp/msg.jsp";
		}

		// 1.��װ����
		Order order = new Order();
		// 1.1 ����id
		order.setOid(UUID.randomUUID().toString().replace("-", "")
				.toUpperCase());

		// 1.2 ����ʱ��
		order.setOrdertime(new Date());

		// 1.3 �ܽ��
		// ��ȡsession��cart
		Cart cart = (Cart) request.getSession().getAttribute("cart");

		order.setTotal(cart.getTotal());

		// 1.4 ���������ж�����
		for (CartItem cartItem : cart.getItem()) {
			OrderItem oi = new OrderItem();

			// ����id
			oi.setItemid(UUID.randomUUID().toString().replace("-", "")
					.toUpperCase());

			// ���ù�������
			oi.setCount(cartItem.getCount());

			// ����С��
			oi.setSubtotal(cartItem.getSubtotal());

			// ����product
			oi.setProduct(cartItem.getProduct());

			// ����order
			oi.setOrder(order);

			// ��ӵ�list��
			order.getItems().add(oi);
		}

		// 1.5 �����û�
		order.setUser(user);

		// 2.����service ��Ӷ���
		OrderService os = new OrderServiceImpl();
		os.add(order);

		// 3.��order����request����,����ת��
		request.setAttribute("bean", order);

		// 4.��չ��ﳵ
		request.getSession().removeAttribute("cart");
		return "/user/jsp/order_info.jsp";
	}

	/*
	 * ��ҳ��ѯ�ҵĶ���
	 */
	public String findAllByPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1.��ȡ��ǰҳ
		int currPage = 0;
		if (request.getParameter("currPage") == null) {
			currPage = 1;
		} else {
			currPage = Integer.parseInt(request.getParameter("currPage"));
		}
		int pageSize = 3;

		// 2.��ȡ�û�
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("msg",
					"������δ��¼������<a href='" + request.getContextPath()
							+ "/User?method=loginUI'>��¼</a>");
			return "/user/jsp/msg.jsp";
		}

		// 3.����service
		OrderService os = new OrderServiceImpl();
		PageBean<Order> bean = os.findAllByPage(currPage, pageSize, user);

		// 4.��PageBean����request����
		request.setAttribute("pb", bean);

		return "/user/jsp/order_list.jsp";
	}

	/*
	 * ͨ��id��ȡ����
	 */
	public String getById(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1.��ȡoid
		String oid = request.getParameter("oid");

		// 2.����service ͨ��oid ����ֵ:order
		OrderService os = new OrderServiceImpl();
		Order order = os.getById(oid);

		// 3.��order����request����
		request.setAttribute("bean", order);

		return "/user/jsp/order_info.jsp";
	}

	/*
	 * �ύ֧���Ķ���
	 */
	public String pay(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ���ܲ���
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		String oid = request.getParameter("oid");

		// ͨ��id��ȡorder
		OrderService s = new OrderServiceImpl();
		Order order = s.getById(oid);

		order.setAddress(address);
		order.setName(name);
		order.setTelephone(telephone);

		// �޸Ķ���״̬Ϊ ��֧��
		order.setState(1);

		// ����order
		s.update(order);

		// ��ѯ�¶������������ת�������б�ҳ��
		String spath = findAllByPage(request, response);
		return spath;
	}

	/*
	 * ȷ���ջ�
	 */
	public String updateState(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1.��ȡ oid
		String oid = request.getParameter("oid");

		// 2.����service �޸Ķ���״̬
		OrderService os = new OrderServiceImpl();
		Order order = os.getById(oid);
		order.setState(3);
		os.update(order);

		// 3.�ض���
		response.sendRedirect(request.getContextPath()
				+ "/Order?method=findAllByPage&currPage=1");
		return null;
	}

	/*
	 * �˵�
	 */
	public String quitItem(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1.��ȡ oid
		String oid = request.getParameter("oid");

		// 2.����service ɾ������
		OrderService os = new OrderServiceImpl();
		Order order = os.getById(oid);
		os.delete(order);

		// 3.ת��
		request.setAttribute("msg", "�˵��ɹ�");
		return "/user/jsp/msg.jsp";
	}
	
	/*
	 * �˿�
	 */
	public String quitMoney(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1.��ȡ oid
		String oid = request.getParameter("oid");
		
		// 2.�����˿��һϵ�н�Ǯ����
		
		// 3.����service ɾ������
		OrderService os = new OrderServiceImpl();
		
		// 4.ɾ������
		Order order = os.getById(oid);
		double money = 0.0;
		money = order.getTotal();
		
		os.delete(order);

		// 5.ת��
		request.setAttribute("msg", "�˿�ɹ������ģ� '"+money+"' �Ѿ��˻����˺ţ�����к˲�!!");
		return "/user/jsp/msg.jsp";
	}
}
