package business.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable {
	// ��Ź��ﳵ��ļ��� key:��Ʒ��id cartitem:���ﳵ�� ʹ��map���ϱ���ɾ���������ﳵ��
	private Map<String, CartItem> map = new LinkedHashMap<>();

	// �ܽ��
	private Double total = 0.0;

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * ��ȡ���еĹ��ﳵ��
	 */
	public Collection<CartItem> getItem() {
		return map.values();
	}

}