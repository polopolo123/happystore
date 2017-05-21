package user.dao;

import user.entity.User;

public interface UserDao {

	// �û�ע��
	public void add(User user) throws Exception;

	// �û�������Ϣ�޸�
	public void updateUser(User user) throws Exception;

	// ͨ���û������������û�
	public User getByUsernameAndPwd(String username, String password) throws Exception;

	// �޸�����
	public User updatePwd(String uid,String pwd) throws Exception;
	
	// �û�ע��
	public void cancel(String uid) throws Exception;

}
