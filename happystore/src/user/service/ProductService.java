package user.service;

import java.util.List;

import user.entity.PageBean;
import user.entity.Product;


public interface ProductService {

	/*
	 * ͨ��pid�����ϸ��Ϣ
	 */
	public Product getByPid(String pid) throws Exception;

	/*
	 * ��ҳ��ѯ��Ʒ
	 */
	public PageBean<Product> findByPage(int currPage, int pageSize, String cid) throws Exception;

	/*
	 * ��ѯ������Ʒ
	 */
	public List<Product> findNew() throws Exception;

	/*
	 * ��ѯ������Ʒ
	 */
	public List<Product> findHot() throws Exception;
	
}
