package user.dao;

import java.util.List;

import user.entity.Order;
import user.entity.OrderItem;

public interface OrderDao {
	
	// ��Ӷ���
	public void add(Order order) throws Exception;

	// ��Ӷ�����
	public void addItem(OrderItem oi) throws Exception;

	// ��ҳ��ѯ�ҵĶ���
	public List<Order> findAllByPage(int currPage, int pageSize, String uid)
			throws Exception;

	// ͨ��id��ѯ�����ܼ�
	public int getTotalCount(String uid) throws Exception;

	// ͨ������id��ȡ����
	public Order getById(String oid) throws Exception;

	// ���¶�����Ϣ
	public void update(Order order) throws Exception;
	
	// ɾ������
	public void delete(Order order) throws Exception;
	
}
