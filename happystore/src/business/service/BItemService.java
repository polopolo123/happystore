package business.service;

import java.util.List;

import business.entity.OrderItem;

public interface BItemService {
	
	// ͨ��id��ȡ���̼ҵ����ж�������Ϣ
	public List<OrderItem> getListById(String bid) throws Exception;
	
	// ��ɶ����ķ�������
	public void send(String itemid) throws Exception;
}
