package user.service.impl;

import user.dao.PromotionDao;
import user.dao.impl.PromotionDaoImpl;
import user.entity.Promotion;
import user.service.PromotionService;

public class PromotionServiceImpl implements PromotionService {

	// ͨ��id��ȡһ����������
	public Promotion getById(String pnid) throws Exception {

		// �����ݿ��л�ȡ
		PromotionDao promotionDao = new PromotionDaoImpl();
		return promotionDao.getById(pnid);
	}

}
