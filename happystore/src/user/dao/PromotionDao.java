package user.dao;

import user.entity.Promotion;

public interface PromotionDao {
	
	// ͨ��id��ȡһ����������
	public Promotion getById(String pnid) throws Exception;
}
