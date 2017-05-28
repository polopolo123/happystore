package admin.dao;

import java.util.List;

import user.entity.Promotion;

public interface APromotionDao {

	// ��ѯ���еĴ�������
	public List<Promotion> findAll() throws Exception;
	
	// ͨ��id��ȡһ������
	public Promotion getById(String pnid) throws Exception;
	
	//���´�������
	public void update(Promotion promotion) throws Exception;
	
	//ɾ����������
	public void delete(String pnid) throws Exception;
	
	//��ӷ���
	public void add(Promotion promotion) throws Exception;

}
