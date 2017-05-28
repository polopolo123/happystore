package admin.dao;

import user.entity.Category;

public interface ACategoryDao {

	//���·���
	public void update(Category category) throws Exception;

	//ɾ������
	public void delete(String cid) throws Exception;

	//��ӷ���
	public void add(Category category) throws Exception;

}
