package user.service.impl;

import java.util.List;

import user.dao.ProductDao;
import user.dao.impl.ProductDaoImpl;
import user.entity.PageBean;
import user.entity.Product;
import user.service.ProductService;

public class ProductServiceImpl implements ProductService{
	/**
	 * ��ѯ������Ʒ
	 */
	@Override
	public Product getByPid(String pid) throws Exception {
		ProductDao productDao=new ProductDaoImpl();
		return productDao.getByPid(pid);
	}

	/**
	 * ������ҳ��ѯ��Ʒ
	 */
	@Override
	public PageBean<Product> findByPage(int currPage, int pageSize, String cid) throws Exception {
		ProductDao productDao=new ProductDaoImpl();
		//��ǰҳ����
		List<Product> list=productDao.findByPage(currPage,pageSize,cid);
		
		//������
		int totalCount = productDao.getTotalCount(cid);
		
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}

	/*
	 * ��ѯ������Ʒ
	 */
	@Override
	public List<Product> findNew() throws Exception {
		ProductDao productDao=new ProductDaoImpl();
		return productDao.findNew();
	}

	/*
	 * ��ѯ������Ʒ
	 */
	@Override
	public List<Product> findHot() throws Exception {
		ProductDao productDao=new ProductDaoImpl();
		return productDao.findHot();
	}

}
