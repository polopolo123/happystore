package business.dao;

import java.util.List;

import business.entity.OrderItem;

public interface BItemDao {

	// ͨ��id��ȡ���̼ҵ����ж�������Ϣ
	public List<OrderItem> getListById(String bid) throws Exception;
	
	// ��ɶ����ķ�������
	public void send(String itemid) throws Exception;
}
