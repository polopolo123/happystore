package admin.service.impl;

import java.util.List;

import admin.dao.AdminDao;
import admin.dao.impl.AdminDaoImpl;
import admin.entity.Admin;
import admin.entity.Business;
import admin.entity.User;
import admin.service.AdminService;

public class AdminServiceImpl implements AdminService {

	// ͨ���˺ź������ȡAdmin
	@Override
	public Admin findAdmin(String aname, String password) throws Exception {
		AdminDao adminDao = new AdminDaoImpl();
		return adminDao.findAdmin(aname,password);
	}
	
	// ��ȡ�û��б�
	public List<User> findUser() throws Exception {
		AdminDao adminDao = new AdminDaoImpl();
		return adminDao.findUser();
	}

	// ͨ��״̬��ȡ�û��б�
	@Override
	public List<User> findUserByState(String viewstate) throws Exception {
		AdminDao adminDao = new AdminDaoImpl();
		return adminDao.findUserByState(viewstate);
	}

	// ��ȡ�̵��б�
	@Override
	public List<Business> findBusiness() throws Exception {
		AdminDao adminDao = new AdminDaoImpl();
		return adminDao.findBusiness();
	}

	// ��ѯ���õĵ���
	@Override
	public List<Business> findJYBusiness() throws Exception {
		AdminDao adminDao = new AdminDaoImpl();
		return adminDao.findJYBusiness();
	}

	// ��ѯ����ע��ĵ���
	@Override
	public List<Business> findSQBusiness() throws Exception {
		AdminDao adminDao = new AdminDaoImpl();
		return adminDao.findSQBusiness();
	}

}
