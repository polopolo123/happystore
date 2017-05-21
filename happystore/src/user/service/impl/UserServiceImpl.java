package user.service.impl;

import user.dao.UserDao;
import user.dao.impl.UserDaoImpl;
import user.entity.User;
import user.service.UserService;

public class UserServiceImpl implements UserService {

	// �û�ע��
	@Override
	public void regist(User user) throws Exception {
		UserDao dao = new UserDaoImpl();
		if (user == null) {
			throw new RuntimeException();
		}
		dao.add(user);
	}

	// �����޸�
	@Override
	public User updatePwd(String uid, String pwd) throws Exception {
		UserDao dao = new UserDaoImpl();
		return dao.updatePwd(uid, pwd);
	}

	// �û�ע��
	@Override
	public void cancel(String uid) throws Exception {
		UserDao dao = new UserDaoImpl();
		if (uid == null) {
			throw new RuntimeException();
		}
		dao.cancel(uid);
	}

	// �˺Ż�����Ϣ�޸�
	@Override
	public User updateUser(User user) throws Exception {
		UserDao dao = new UserDaoImpl();
		if (user != null) {
			throw new RuntimeException();
		}
		dao.updateUser(user);
		return dao.getByUsernameAndPwd(user.getName(),user.getPassword());
	}

	// �û���½
	@Override
	public User login(String username, String password) throws Exception {
		UserDao dao = new UserDaoImpl();
		return dao.getByUsernameAndPwd(username, password);
	}

}
