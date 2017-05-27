package business.service.impl;

import java.util.List;

import business.dao.BusinessDao;
import business.dao.impl.BusinessDaoImpl;
import business.entity.Category;
import business.entity.Product;
import business.entity.Promotion;
import business.service.BusinessService;

public class BusinessServiceImpl implements BusinessService{
	
	// ͨ��id��ȡ���̼��ṩ����Ʒ�б�
	public List<Product> getListById(String bid) throws Exception {
		BusinessDao businessDao = new BusinessDaoImpl(); 
		return businessDao.getListById(bid);
	}
	
	// ͨ��id��ȡɾ����Ʒ
	@Override
	public void deleteById(String pid) throws Exception {
		BusinessDao businessDao = new BusinessDaoImpl(); 
		businessDao.deleteById(pid);
	}

	// �����Ʒ
	@Override
	public void add(Product product) throws Exception {
		BusinessDao businessDao = new BusinessDaoImpl(); 
		businessDao.add(product);
	}
	
	// �༭��Ʒ
	@Override
	public void update(Product product) throws Exception {
		BusinessDao businessDao = new BusinessDaoImpl(); 
		businessDao.update(product);
	}

	// ��ѯ��������
	@Override
	public List<Promotion> findPromotion() throws Exception {
		BusinessDao businessDao = new BusinessDaoImpl(); 
		return businessDao.findPromotion();
	}

	// ��ѯ��Ʒ����
	@Override
	public List<Category> findCategory() throws Exception {
		BusinessDao businessDao = new BusinessDaoImpl(); 
		return businessDao.findCategory();
	}
}
