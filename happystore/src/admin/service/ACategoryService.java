package admin.service;

import user.entity.Category;

public interface ACategoryService {

	//���·���
	public void update(Category category) throws Exception;

	//ɾ������
	public void delete(String cid) throws Exception;
	
	//��ӷ���
	public void add(Category category) throws Exception;
}

