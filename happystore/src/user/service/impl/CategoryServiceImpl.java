package user.service.impl;

import java.util.List;

import user.dao.CategoryDao;
import user.dao.impl.CategoryDaoImpl;
import user.entity.Category;
import user.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	// �����ݿ��в�ѯ�����еķ���
	@Override
	public List<Category> findAll() throws Exception {

		// �����ݿ��л�ȡ
		CategoryDao categoryDao = new CategoryDaoImpl();

		return categoryDao.findAll();
	}
	
	// ͨ��id��ȡһ������
	public Category getById(String cid) throws Exception {
		
		// �����ݿ��л�ȡ
		CategoryDao categoryDao = new CategoryDaoImpl();

		return categoryDao.getById(cid);
	}

}
