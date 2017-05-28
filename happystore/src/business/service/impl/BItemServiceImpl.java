package business.service.impl;

import java.util.List;

import business.dao.BItemDao;
import business.dao.impl.BItemDaoImpl;
import business.entity.OrderItem;
import business.service.BItemService;

public class BItemServiceImpl implements BItemService{

	// ͨ��id��ȡ���̼ҵ����ж�������Ϣ
	@Override
	public List<OrderItem> getListById(String bid) throws Exception {
		BItemDao bItemServiceDao = new BItemDaoImpl(); 
		return bItemServiceDao.getListById(bid);
	}
	
	// ��ɶ����ķ�������
	public void send(String itemid) throws Exception {
		BItemDao bItemServiceDao = new BItemDaoImpl(); 
		bItemServiceDao.send(itemid);
	}
	
}
