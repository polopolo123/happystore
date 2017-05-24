package user.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import user.dao.ProductDao;
import user.entity.Product;
import user.utils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao {
	/*
	 * ͨ��pid��ѯ��Ʒ����Ϣ
	 */
	@Override
	public Product getByPid(String pid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid = ? limit 1";
		return qr.query(sql, new BeanHandler<>(Product.class), pid);
	}

	/*
	 * ��ҳ��ѯ��Ʒ�б�
	 */
	@Override
	public List<Product> findByPage(int currPage, int pageSize, String cid)
			throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid = ? limit ?,?";
		return qr.query(sql, new BeanListHandler<>(Product.class), cid,
				(currPage - 1) * pageSize, pageSize);
	}

	/*
	 * ��ѯ�ܼ�¼��
	 */
	@Override
	public int getTotalCount(String cid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid = ?";
		return ((Long) qr.query(sql, new ScalarHandler(), cid)).intValue();
	}
	
	/*
	 * ��ѯ������Ʒ
	 */
	@Override
	public List<Product> findNew() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product order by pdate desc limit 9";
		return qr.query(sql, new BeanListHandler<>(Product.class));
	}

	/*
	 * ��ѯ������Ʒ
	 */
	@Override
	public List<Product> findHot() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where is_hot = 1 order by pdate desc limit 9";
		return qr.query(sql, new BeanListHandler<>(Product.class));
	}
}
