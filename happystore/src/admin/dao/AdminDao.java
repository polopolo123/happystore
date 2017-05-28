package admin.dao;

import java.util.List;

import admin.entity.Admin;
import admin.entity.Business;
import admin.entity.User;

public interface AdminDao {
	
	// ͨ���˺ź������ȡAdmin
	public Admin findAdmin(String aname, String password) throws Exception;
	
	// ��ȡ�û��б�
	public List<User> findUser() throws Exception;

	// ͨ��״̬����ȡ�û��б�
	public List<User> findUserByState(String viewstate) throws Exception;

	// ��ȡ�����б�
	public List<Business> findBusiness() throws Exception;

	// ��ѯ���õĵ���
	public List<Business> findJYBusiness() throws Exception;

	// ��ѯ����ע��ĵ���
	public List<Business> findSQBusiness() throws Exception;
}
