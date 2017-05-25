package user.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import user.dao.CategoryDao;
import user.entity.Category;
import user.utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao{

	/*
	 * ��ѯ���з���
	 */
	@Override
	public List<Category> findAll() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from category";
		return qr.query(sql, new BeanListHandler<>(Category.class));
	}
	
	/*
	 * ͨ��id��ȡһ������
	 */
	@Override
	public Category getById(String cid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from category where cid = ? limit 1";
		return qr.query(sql, new BeanHandler<>(Category.class), cid);
	}
}
