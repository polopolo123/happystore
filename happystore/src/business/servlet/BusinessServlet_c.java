package business.servlet;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.entity.Business;
import business.entity.OrderItem;
import business.service.BItemService;
import business.service.impl.BItemServiceImpl;

/**
 * �̼Ҷ��������ģ��
 */
public class BusinessServlet_c extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 *	��ѯ�õ�Һ�̨�Ķ���������б�չʾ 
	 */
	public String findAllByBid(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String bid = null;
		
		// 1.��ȡbid
		if(request.getSession().getAttribute("Business") == null) {
			throw new RuntimeException();
		}else {
			Business business = (Business) request.getSession().getAttribute("Business");
			bid = business.getBid();
		}

		// 2.����BusinessService ͨ��bid��ȡ��Ʒ�б�
		BItemService bItemService = new BItemServiceImpl();
		List<OrderItem> orderItemList = bItemService.getListById(bid);

		// 3.��ӵ�request�У�����ת��
		request.setAttribute("orderItemList", orderItemList);
		return "/business/orderItem/list.jsp";
	}
	
	/**
	 *	��ѯ�õ�Һ�̨�Ķ�����ж��Ƿ񷢻�����ʾδ��������Ϣ
	 */
	public String findNoByBid(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String bid = null;
		
		// 1.��ȡbid
		if(request.getSession().getAttribute("Business") == null) {
			throw new RuntimeException();
		}else {
			Business business = (Business) request.getSession().getAttribute("Business");
			bid = business.getBid();
		}

		// 2.����BusinessService ͨ��bid��ȡ��Ʒ�б�
		BItemService bItemService = new BItemServiceImpl();
		List<OrderItem> orderItemList = new LinkedList<OrderItem>();

		
		// 3.�����ж�
		for (OrderItem orderItem : bItemService.getListById(bid)) {
			if(orderItem.getIs_ok() == 0 ) {
				orderItemList.add(orderItem);
			}
		}
		
		// 4.��ӵ�request�У�����ת��
		request.setAttribute("orderItemList", orderItemList);
		return "/business/orderItem/list.jsp";
	}
	
	/**
	 *	��ѯ�õ�Һ�̨�Ķ�����ж��Ƿ񷢻�����ʾ��������Ϣ
	 */
	public String findYesByBid(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String bid = null;
		
		// 1.��ȡbid
		if(request.getSession().getAttribute("Business") == null) {
			throw new RuntimeException();
		}else {
			Business business = (Business) request.getSession().getAttribute("Business");
			bid = business.getBid();
		}

		// 2.����BusinessService ͨ��bid��ȡ��Ʒ�б�
		BItemService bItemService = new BItemServiceImpl();
		List<OrderItem> orderItemList = new LinkedList<OrderItem>();

		
		// 3.�����ж�
		for (OrderItem orderItem : bItemService.getListById(bid)) {
			if(orderItem.getIs_ok() == 1 ) {
				orderItemList.add(orderItem);
			}
		}
		
		// 4.��ӵ�request�У�����ת��
		request.setAttribute("orderItemList", orderItemList);
		return "/business/orderItem/list.jsp";
	}

	/**
	 *	���ж�����ķ�������
	 */
	public String send(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 1.��ȡ������id
		String itemid = request.getParameter("itemid");
		
		// 2.����BItemService ��ɶ����ķ�������
		BItemService bItemService = new BItemServiceImpl();
		bItemService.send(itemid);

		// 3.��ӵ�request�У�����ת��
		return findAllByBid(request, response);
	}
	
}
