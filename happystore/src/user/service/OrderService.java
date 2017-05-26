package user.service;

import user.entity.Order;
import user.entity.PageBean;
import user.entity.User;

public interface OrderService {
	
	// ��Ӷ���
	public void add(Order order) throws Exception;

	// ��ҳ��ѯ�ҵĶ���
	public PageBean<Order> findAllByPage(int currPage, int pageSize, User user) throws Exception;

	// ͨ������id��ȡ����
	public Order getById(String oid) throws Exception;

	// ���¶�����Ϣ
	public void update(Order order) throws Exception;
	
	// ɾ������
	public void delete(Order order) throws Exception;
}
