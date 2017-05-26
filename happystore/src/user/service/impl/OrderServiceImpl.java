package user.service.impl;

import java.util.List;

import user.dao.OrderDao;
import user.dao.impl.OrderDaoImpl;
import user.entity.Order;
import user.entity.OrderItem;
import user.entity.PageBean;
import user.entity.User;
import user.service.OrderService;
import user.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {
	
	// ��Ӷ���
	@Override
	public void add(Order order) throws Exception {
		try {
			// 1.��������
			DataSourceUtils.startTransaction();

			OrderDao od = new OrderDaoImpl();
			// 2.��orders�������һ������
			od.add(order);

			// 3.��orderitem�����n������
			for (OrderItem oi : order.getItems()) {
				od.addItem(oi);
			}

			// 4.������
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}

	}

	// ��ҳ��ѯ�ҵĶ���
	@Override
	public PageBean<Order> findAllByPage(int currPage, int pageSize, User user)
			throws Exception {
		OrderDao od = new OrderDaoImpl();

		// ��ѯ��ǰҳ����
		List<Order> list = od.findAllByPage(currPage, pageSize, user.getUid());

		// ��ѯ������
		int totalCount = od.getTotalCount(user.getUid());
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}

	// ͨ������id��ȡ����
	@Override
	public Order getById(String oid) throws Exception {
		OrderDao od = new OrderDaoImpl();
		return od.getById(oid);
	}

	// ���¶�����Ϣ
	@Override
	public void update(Order order) throws Exception {
		OrderDao od = new OrderDaoImpl();
		od.update(order);
	}
	
	// ɾ������
	@Override
	public void delete(Order order) throws Exception {
		OrderDao od = new OrderDaoImpl();
		od.delete(order);
	}

}
