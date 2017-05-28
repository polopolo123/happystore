package business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import business.dao.BItemDao;
import business.entity.Order;
import business.entity.OrderItem;
import business.entity.Product;
import business.utils.DataSourceUtils;

public class BItemDaoImpl implements BItemDao {

	// ͨ��id��ȡ���̼ҵ����ж�������Ϣ
	@Override
	public List<OrderItem> getListById(String bid) throws Exception {
		List<OrderItem> orderItemList = new LinkedList<OrderItem>();
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where state >= 1";
		// ��ѯ���еĶ�����Ϣ
		List<Order> olist = qr.query(sql, new BeanListHandler<>(Order.class));
		// ��ѯ�̼����еĲ�Ʒ��Ϣ
		List<Product> plist = new BusinessDaoImpl().getListById(bid);
		
		for (Order order : olist) {
			for (Product product : plist) {
				sql = "select * from orderitem where oid = ? and pid = ?";
				List<OrderItem> list = qr.query(sql, new BeanListHandler<>(OrderItem.class),order.getOid(),product.getPid());
				for (OrderItem orderItem : list) {
					orderItem.setProduct(product);
					orderItem.setOrder(order);
					orderItemList.add(orderItem);
				}
			}
		}
		return orderItemList;
	}
	
	// ��ɶ����ķ�������
	public void send(String itemid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orderitem set is_ok = 1 where itemid = ?";
		qr.update(sql, itemid);
		
		sql = "select * from orderitem where itemid  = ? limit 1";
		OrderItem oItem = qr.query(sql, new ResultSetHandler() {
			@Override
			public OrderItem handle(ResultSet rs) throws SQLException {
				if (rs.next()) {
					OrderItem orderItem = new OrderItem();
					Order order = new Order();
					order.setOid(rs.getString("oid"));
					orderItem.setOrder(order);
					return orderItem;
				}
				return null;
			}
		},itemid);
		String oid = oItem.getOrder().getOid();
		
		// ��ʱҪ�����жϣ����orderitem���е�oid��ͬ�������ж�������
		sql = "select * from orderitem where oid  = ?";
		List<OrderItem> orderItemlist = qr.query(sql, new BeanListHandler<>(OrderItem.class),oid);
		boolean flag = true;
		for (OrderItem orderItem : orderItemlist) {
			if(orderItem.getIs_ok() == 0) {
				flag = false;
			}
		}
		if(flag) {
			sql = "update orders set state = 2 where oid = ?";
			qr.update(sql, oid);
		}
	}
	
}
