package user.dao;

import java.util.List;

import user.entity.Product;

public interface ProductDao {

	/*
	 * ͨ��pid��ѯ��Ʒ����Ϣ
	 */
	public Product getByPid(String pid) throws Exception;

	/*
	 * ��ҳ��ѯ��Ʒ�б� 
	 */
	public List<Product> findByPage(int currPage, int pageSize, String cid) throws Exception;

	/*
	 * ��ѯ�ܼ�¼�� 
	 */
	public int getTotalCount(String cid) throws Exception;
	
	/*
	 * ��ѯ������Ʒ
	 */
	public List<Product> findNew() throws Exception;

	/*
	 * ��ѯ������Ʒ
	 */
	public List<Product> findHot() throws Exception;
}
