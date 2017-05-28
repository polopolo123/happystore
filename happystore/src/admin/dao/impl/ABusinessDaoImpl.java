package admin.dao.impl;

import org.apache.commons.dbutils.QueryRunner;

import admin.dao.ABusinessDao;
import admin.utils.DataSourceUtils;

public class ABusinessDaoImpl implements ABusinessDao {

	// ����
	@Override
	public void changeaddStar(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update business set stars = stars + 1 where bid = ?";
		qr.update(sql,bid);
	}

	// ����
	@Override
	public void changejianStar(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update business set stars = stars - 1 where bid = ?";
		qr.update(sql,bid);
	}

	// �����û�
	@Override
	public void stopBusiness(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update business set state = 2 where bid = ?";
		qr.update(sql,bid);
	}

	// ����û�
	@Override
	public void openBusiness(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update business set state = 1 where bid = ?";
		qr.update(sql,bid);
	}

	//�������ע��
	@Override
	public void okbusiness(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update business set state = 1 where bid = ?";
		qr.update(sql,bid);
	}

}
