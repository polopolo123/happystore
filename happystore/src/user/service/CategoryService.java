package user.service;

import java.util.List;

import user.entity.Category;

public interface CategoryService {
	
	// �����ݿ��в�ѯ�����еķ���
	public List<Category> findAll () throws Exception;
	
	// ͨ��id��ȡһ������
	public Category getById(String cid) throws Exception;
}
