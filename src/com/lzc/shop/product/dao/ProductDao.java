package com.lzc.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lzc.shop.product.vo.Product;
import com.lzc.shop.utils.PageHibernateCallback;

/**
 * 商品数据持久层
 * 
 * @author lzc
 * 
 */
public class ProductDao extends HibernateDaoSupport {

	/**
	 * 热门商品的查询
	 * 
	 * @return
	 */
	public List<Product> findHot() {
		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 查询条件
		criteria.add(Restrictions.eq("is_hot", 1));
		// 最新的热门商品，进行倒序排序
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 10);
		return list;
	}

	/**
	 * 最新商品的查询
	 * 
	 * @return
	 */
	public List<Product> findNew() {
		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 最新的热门商品，进行倒序排序
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 10);
		return list;
	}

	public Product findByPid(Integer pid) {

		Product existProduct = this.getHibernateTemplate().get(Product.class,pid);
		return existProduct;
	}

	/**
	 * 根据分类id查询商品的总个数
	 * 
	 * @return
	 */
	public int findCountByCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 查询分页显示的商品集合
	 * 
	 * @param cid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, new Object[] { cid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 根据二级分类id查询总记录数
	 * 
	 * @param csid
	 * @return
	 */
	public int findCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 根据二级分类和当前页查询商品集合
	 * 
	 * @param csid
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid=?";

		List<Product> list = this.getHibernateTemplate().executeFind(
				new PageHibernateCallback<Product>(hql, new Object[] { csid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 查找所有商品记录数
	 * @return
	 */
	public int findTotalCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 查找所有商品集合
	 * @param currentPage
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product p order by p.pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/**
	 * 添加商品执行的方法
	 * @param product
	 */
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	/**
	 * 更新商品信息执行的方法
	 * @param product
	 */
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

	/**
	 * 删除指定商品执行的方法
	 * @param product
	 */
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

}
