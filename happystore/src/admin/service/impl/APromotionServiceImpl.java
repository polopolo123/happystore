package admin.service.impl;

import java.util.List;

import user.entity.Promotion;
import admin.dao.APromotionDao;
import admin.dao.impl.APromotionDaoImpl;
import admin.service.APromotionService;

public class APromotionServiceImpl implements APromotionService {

	
	@Override
	public List<Promotion> findAll() throws Exception {
		APromotionDao aPromotionDao = new APromotionDaoImpl();
		return aPromotionDao.findAll();
	}

	// ͨ��id��ȡһ������
	@Override
	public Promotion getById(String pnid) throws Exception {
		APromotionDao aPromotionDao = new APromotionDaoImpl();
		return aPromotionDao.getById(pnid);
	}

	//���´�������
	@Override
	public void update(Promotion promotion) throws Exception {
		APromotionDao aPromotionDao = new APromotionDaoImpl();
		aPromotionDao.update(promotion);
	}

	//ɾ����������
	@Override
	public void delete(String pnid) throws Exception {
		APromotionDao aPromotionDao = new APromotionDaoImpl();
		aPromotionDao.delete(pnid);
	}

	//��ӷ���
	@Override
	public void add(Promotion promotion) throws Exception {
		APromotionDao aPromotionDao = new APromotionDaoImpl();
		aPromotionDao.add(promotion);
	}
	
}
