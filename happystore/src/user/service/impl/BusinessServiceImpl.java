package user.service.impl;

import user.dao.BusinessDao;
import user.dao.impl.BusinessDaoImpl;
import user.entity.Business;
import user.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {

	// ͨ��id��ȡһ���̼�
	public Business getById(String bid) throws Exception {
		
		// �����ݿ��л�ȡ
		BusinessDao businessDao = new BusinessDaoImpl();

		return businessDao.getById(bid);
	}

}
