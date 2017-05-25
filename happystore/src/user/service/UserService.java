package user.service;

import user.entity.User;

public interface UserService {
	
	// �û�ע��
	public void regist(User user) throws Exception;

	// �����޸�
	public User updatePwd(String uid,String pwd) throws Exception;

	// �û�ע��
	public void cancel(String uid) throws Exception;
	
	// �û�������Ϣ�޸�
	public User updateUser(User user) throws Exception;
	
	// �û���½
	public User login(String username, String password) throws Exception;

	// ����Ƿ�ע��
	public boolean checkUser(String username) throws Exception;

}
