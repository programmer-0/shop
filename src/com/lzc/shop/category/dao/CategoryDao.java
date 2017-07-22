package com.lzc.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lzc.shop.category.vo.Category;

/**
 * 一级分类的数据持久层
 * @author lzc
 *
 */
public class CategoryDao extends HibernateDaoSupport{

	/**
	 * 查询所有一级分类执行的方法
	 * @return
	 */
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> cList = this.getHibernateTemplate().find(hql);
		if(cList != null || cList.size() > 0){
			return cList;
		}
		return null;
	}

	/**
	 * 根据cid查询一级分类执行的方法
	 * 
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	/**
	 * 添加一级分类执行的方法
	 * 
	 * @param category
	 */
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	/**
	 * 删除一级分类执行的方法
	 * @param cid
	 */
	public void deleteCategory(Category category) {
		this.getHibernateTemplate().delete(category);
	}

}
