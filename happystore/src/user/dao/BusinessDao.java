package user.dao;

import user.entity.Business;

public interface BusinessDao {
	
	// ͨ��id��ȡһ���̼�
	public Business getById(String bid) throws Exception;
}
