package user.dao;

import user.entity.User;

public interface UserDao {

	// 用户注册
	public void add(User user) throws Exception;

	// 用户信息修改
	public void update(User user) throws Exception;

	// 通过用户名和密码获得用户，进行登陆
	public User getByUsernameAndPwd(String username, String password) throws Exception;

}
