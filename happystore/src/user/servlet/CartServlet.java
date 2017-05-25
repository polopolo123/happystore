package user.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.entity.Cart;
import user.entity.CartItem;
import user.entity.Product;
import user.service.ProductService;
import user.service.impl.ProductServiceImpl;

/**
 * ���ﳵģ��
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ��ȡ���ﳵ
	 */
	public Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// �жϹ��ﳵ�Ƿ�Ϊ��
		if (cart == null) {
			// ����һ��cart
			cart = new Cart();

			// ��ӵ�session��
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}

	/**
	 * ��ӵ����ﳵ
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 1.��ȡpid������
		String pid = request.getParameter("pid");
		int count = Integer.parseInt(request.getParameter("count"));

		// 2.����productservice ͨ��pid��ȡһ����Ʒ
		ProductService ps = new ProductServiceImpl();
		Product product = ps.getByPid(pid);

		// 3.��װ��CartItem
		CartItem cartItem = new CartItem(product, count);

		// 4.��ӵ����ﳵ
		Cart cart = getCart(request);
		// 4.1���жϹ��ﳵ�����޸���Ʒ
		if (cart.getMap().containsKey(pid)) {
			// ��
			// ���ù������� ��Ҫ��ȡ����Ʒ֮ǰ�Ĺ�������+���ڵĹ�������(item.getCount)
			// ��ȡ���ﳵ�й��ﳵ��
			CartItem oItem = cart.getMap().get(pid);
			oItem.setCount(oItem.getCount() + cartItem.getCount());
		} else {
			// û�� �����ﳵ����ӽ�ȥ
			cart.getMap().put(pid, cartItem);
		}
		// 4.2.������֮�� �޸Ľ��
		cart.setTotal(cart.getTotal() + cartItem.getSubtotal());

		// 5.�ض���
		response.sendRedirect(request.getContextPath() + "/user/jsp/cart.jsp");
		return null;
	}

	/**
	 * �ӹ��ﳵ���Ƴ����ﳵ��
	 */
	public String remove(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1.��ȡ��Ʒ��pid����ͨ��pid���Cart
		String pid = request.getParameter("pid");
		Cart cart = getCart(request);

		// 2.ͨ��pidɾ��map
		CartItem removeItem = cart.getMap().remove(pid);

		// 3.�޸Ľ��
		cart.setTotal(cart.getTotal() - removeItem.getSubtotal());

		// 4.�ض���
		response.sendRedirect(request.getContextPath() + "/user/jsp/cart.jsp");
		return null;
	}

	/**
	 * ��չ��ﳵ
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ��ȡ���ﳵ ���
		Cart cart = getCart(request);
		// 1.map�ÿ�
		cart.getMap().clear();

		// 2.������
		cart.setTotal(0.0);
		response.sendRedirect(request.getContextPath() + "/user/jsp/cart.jsp");
		return null;
	}

}
