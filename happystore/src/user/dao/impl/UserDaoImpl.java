package user.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.utils.DataSourceUtils;

import user.dao.UserDao;
import user.entity.User;

public class UserDaoImpl implements UserDao{

	public void add(User user) throws Exception {
		
	}

	public void update(User user) throws Exception {
		
	}

	public User getByUsernameAndPwd(String username, String password)
			throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from user where username = ? and password = ? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), username,password);
	}

 
}
