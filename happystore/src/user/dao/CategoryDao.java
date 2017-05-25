package user.dao;

import java.util.List;

import user.entity.Category;

public interface CategoryDao {
	
	// �����ݿ��в�ѯ�����еķ���
	public List<Category> findAll() throws Exception;
	
	// ͨ��id��ȡһ������
	public Category getById(String cid) throws Exception;
}
