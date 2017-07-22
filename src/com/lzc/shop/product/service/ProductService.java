package com.lzc.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lzc.shop.product.dao.ProductDao;
import com.lzc.shop.product.vo.Product;
import com.lzc.shop.utils.PageBean;

/**
 * 商品业务逻辑层
 * @author lzc
 *
 */
@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * 热门商品的查询
	 * @return
	 */
	public List<Product> findHot() {
		return productDao.findHot();
	}

	/**
	 * 最新商品的查询
	 * @return
	 */
	public List<Product> findNew() {
		return productDao.findNew();
	}

	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	/**
	 * 根据当前页和cid查询商品
	 * @param currentPage
	 * @param cid
	 * @return
	 */
	public PageBean<Product> findByCurrentCid(int currentPage, Integer cid) {
		PageBean<Product> pageBean = new PageBean<Product>(); 
		// 设置当前页
		pageBean.setCurrentPage(currentPage);
		
		// 设置每页显示的记录数
		int limit = 8;
		pageBean.setLimit(limit);
				
		// 查询并设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		
		// 设置总页数
		int totalPage = 0;
		totalPage = (int) Math.ceil(totalCount / limit);
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		// 指定页显示的商品集合
		// 查询商品从哪里开始
		int begin = (currentPage - 1) * limit;
		List<Product> list = productDao.findByPageCid(cid, begin, limit);
		
		// 设置显示商品集合
		pageBean.setList(list);
		
		return pageBean;
	}

	/**
	 * 根据二级分类id查询商品
	 * @param csid
	 */
	public PageBean<Product> findByCsid(int currentPage,Integer csid) {
		PageBean<Product> pageBean = new PageBean<Product>();
		
		// 设置当前页
		pageBean.setCurrentPage(currentPage);
		
		// 设置每页显示的记录数
		int limit = 8;
		pageBean.setLimit(limit);
				
		// 查询并设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		
		// 设置总页数
		int totalPage = 0;
//		totalPage = (int) Math.ceil(totalCount / limit);
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		// 指定页显示的商品集合
		// 查询商品从哪里开始
		int begin = (currentPage - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid, begin, limit);
		
		// 设置显示商品集合
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 查找所有商品执行的方法
	 * @param currentPage 
	 * @return
	 */
	public PageBean<Product> findAll(int currentPage) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 补齐信息
		pageBean.setCurrentPage(currentPage);
		int totalCount;
		totalCount = productDao.findTotalCount();
		pageBean.setTotalCount(totalCount);
		int limit = 10;
		pageBean.setLimit(limit);
		int totalPage;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (currentPage - 1) * limit;
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 添加商品执行的方法
	 * @param product
	 */
	public void save(Product product) {
		productDao.save(product);
	}

	/**
	 * 更新商品信息执行的方法
	 * @param product
	 */
	public void update(Product product) {
		productDao.update(product);
	}

	/**
	 * 删除指定商品执行的方法
	 * @param product
	 */
	public void delete(Product product) {
		productDao.delete(product);
	}
	
	
}
