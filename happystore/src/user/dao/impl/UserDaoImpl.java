package user.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import user.dao.UserDao;
import user.entity.User;
import user.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao {

	// �û�ע��
	public void add(User user) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?);";
		qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(),
				user.getName(), user.getEmail(), user.getTelephone(),
				user.getBirthday(), user.getSex(), 0);

	}

	// �û�������Ϣ�޸�
	public void updateUser(User user) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set username = ?,name = ?,email = ?,birthday = ?,telephone = ?,sex = ? where uid =? ";
		qr.update(sql, user.getUsername(), user.getName(), user.getEmail(),
				user.getBirthday(), user.getTelephone(), user.getSex(),
				user.getUid());
	}

	// ͨ���û������������û�
	public User getByUsernameAndPwd(String username, String password)
			throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), username, password);
	}

	// �޸�����
	@Override
	public User updatePwd(String uid, String pwd) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set password = ? where uid =?";
		qr.update(sql, pwd, uid);
		sql = "select * from user where uid = ? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), uid);
	}

	// �û�ע��
	@Override
	public void cancel(String uid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set state = ? where uid =?";
		qr.update(sql, 1, uid);
	}

}
