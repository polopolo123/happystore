package user.entity;

import java.io.Serializable;

public class OrderItem implements Serializable {

	private String itemid;
	private Integer count;
	private Double subtotal;
	private Integer is_ok;

	public Integer getIs_ok() {
		return is_ok;
	}

	public void setIs_ok(Integer is_ok) {
		this.is_ok = is_ok;
	}
	
	// 包含那个商品
	private Product product;

	// 属于那个订单
	private Order order;

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
