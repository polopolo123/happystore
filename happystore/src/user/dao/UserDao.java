package user.dao;

import user.entity.User;

public interface UserDao {

	// �û�ע��
	public void add(User user) throws Exception;

	// �û���Ϣ�޸�
	public void update(User user) throws Exception;

	// ͨ���û������������û������е�½
	public User getByUsernameAndPwd(String username, String password) throws Exception;

}
