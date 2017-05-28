package admin.service.impl;

import user.entity.Category;
import admin.dao.ACategoryDao;
import admin.dao.impl.ACategoryDaoImpl;
import admin.service.ACategoryService;

public class ACategoryServiceImpl implements ACategoryService {

	//���·���
	@Override
	public void update(Category category) throws Exception {
		ACategoryDao aCategoryDao = new ACategoryDaoImpl();
		aCategoryDao.update(category);
	}

	//ɾ������
	@Override
	public void delete(String cid) throws Exception {
		ACategoryDao aCategoryDao = new ACategoryDaoImpl();
		aCategoryDao.delete(cid);
	}

	//��ӷ���
	@Override
	public void add(Category category) throws Exception {
		ACategoryDao aCategoryDao = new ACategoryDaoImpl();
		aCategoryDao.add(category);
	}

}
