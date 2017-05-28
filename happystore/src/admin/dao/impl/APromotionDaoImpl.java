package admin.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import user.entity.Category;
import user.entity.Promotion;
import user.utils.DataSourceUtils;
import admin.dao.APromotionDao;

public class APromotionDaoImpl implements APromotionDao {

	// ��ѯ���еĴ�������
	@Override
	public List<Promotion> findAll() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from promotion";
		return qr.query(sql, new BeanListHandler<>(Promotion.class));
	}
	
	// ͨ��id��ȡһ������
	@Override
	public Promotion getById(String pnid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from promotion where pnid = ? limit 1";
		return qr.query(sql, new BeanHandler<>(Promotion.class), pnid);
	}
	
	//���´�������
	@Override
	public void update(Promotion promotion) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update promotion set pnname = ? where pnid = ?";
		qr.update(sql,promotion.getPnname(),promotion.getPnid());
	}

	//ɾ����������
	@Override
	public void delete(String pnid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update product set pro_id = null where pro_id = ?";
		qr.update(sql, pnid);
		sql = "delete from promotion where pnid = ?";
		qr.update(sql,pnid);
	}

	//��ӷ���
	@Override
	public void add(Promotion promotion) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into promotion values(?,?)";
		qr.update(sql,promotion.getPnid(),promotion.getPnname());
	}

}
