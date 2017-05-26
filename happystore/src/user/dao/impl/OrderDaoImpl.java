package user.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import user.dao.OrderDao;
import user.entity.Order;
import user.entity.OrderItem;
import user.entity.Product;
import user.utils.DataSourceUtils;

public class OrderDaoImpl implements OrderDao {

	// ��Ӷ���
	@Override
	public void add(Order order) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(), sql, order.getOid(),
				order.getOrdertime(), order.getTotal(), order.getState(),
				order.getAddress(), order.getName(), order.getTelephone(),
				order.getUser().getUid());
	}

	// ��Ӷ�����
	@Override
	public void addItem(OrderItem oi) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(), sql, oi.getItemid(), oi
				.getCount(), oi.getSubtotal(), oi.getProduct().getPid(), oi
				.getOrder().getOid());
	}

	// ��ҳ��ѯ�ҵĶ���
	@Override
	public List<Order> findAllByPage(int currPage, int pageSize, String uid)
			throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where uid = ? order by ordertime desc limit ? , ?";
		List<Order> list = qr.query(sql, new BeanListHandler<>(Order.class),
				uid, (currPage - 1) * pageSize, pageSize);

		// ������������ ��װÿ�������Ķ������б�
		sql = "select * from orderitem oi,product p where oi.pid=p.pid and oi.oid = ?";
		for (Order order : list) {
			// ��ǰ������������������
			List<Map<String, Object>> mList = qr.query(sql,
					new MapListHandler(), order.getOid());
			// map��key:�ֶ��� value:�ֶ�ֵ
			for (Map<String, Object> map : mList) {
				// ��װproduct
				Product p = new Product();
				BeanUtils.populate(p, map);

				// ��װorderItem
				OrderItem oi = new OrderItem();
				BeanUtils.populate(oi, map);

				oi.setProduct(p);

				// ��orderItem������ӵ���Ӧ��order�����list������
				order.getItems().add(oi);
			}
		}
		return list;
	}

	// ͨ��id��ѯ�����ܼ�
	@Override
	public int getTotalCount(String uid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from orders where uid = ?";
		return ((Long) qr.query(sql, new ScalarHandler(), uid)).intValue();
	}

	// ͨ������id��ȡ����
	@Override
	public Order getById(String oid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where oid = ?";
		Order order = qr.query(sql, new BeanHandler<>(Order.class), oid);

		// ��װorderitems
		sql = "select * from orderitem oi,product p where oi.pid = p.pid and oi.oid = ?";
		List<Map<String, Object>> query = qr.query(sql, new MapListHandler(),
				oid);
		for (Map<String, Object> map : query) {
			// ��װproduct
			Product product = new Product();
			BeanUtils.populate(product, map);

			// ��װorderitem
			OrderItem oi = new OrderItem();
			BeanUtils.populate(oi, map);
			oi.setProduct(product);

			// ��orderitem������order��items��
			order.getItems().add(oi);
		}
		return order;
	}

	// ���¶�����Ϣ
	@Override
	public void update(Order order) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orders set state=?,address=?,name=?,telephone=? where oid=?";
		qr.update(sql, order.getState(), order.getAddress(), order.getName(),
				order.getTelephone(), order.getOid());
	}

	// ɾ��������Ϣ
	@Override
	public void delete(Order order) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from orderitem where oid = ?";
		qr.update(sql, order.getOid());
		sql = "delete from orders where oid = ?";
		qr.update(sql, order.getOid());
	}

}
