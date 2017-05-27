package business.dao;

import java.util.List;

import business.entity.Business;
import business.entity.Category;
import business.entity.Product;
import business.entity.Promotion;

public interface BusinessDao {
	
	// ͨ��id��ȡһ���̼�
	public Business getById(String bid) throws Exception;
	
	// ͨ��id��ȡ���̼��ṩ����Ʒ�б�
	public List<Product> getListById(String bid) throws Exception;
	
	// ͨ��id��ȡɾ����Ʒ
	public void deleteById(String pid) throws Exception;
	
	// �����Ʒ
	public void add(Product product) throws Exception;
	
	// �༭��Ʒ
	public void update(Product product) throws Exception;
	
	// ��ѯ��������
	public List<Promotion> findPromotion() throws Exception;
	
	// ��ѯ��Ʒ����
	public List<Category> findCategory() throws Exception;
}
