package com.lzc.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lzc.shop.categorysecond.vo.CategorySecond;
import com.lzc.shop.utils.PageBean;
import com.lzc.shop.utils.PageHibernateCallback;

/**
 * 二级分类的数据持久层
 * @author lzc
 *
 */
public class CategorySecondDao extends HibernateDaoSupport{

	/**
	 * 显示所有二级分类执行的方法
	 * @param limit 
	 * @param begin 
	 * @param currentPage 
	 * @return
	 */
	public List<CategorySecond> findAll(int currentPage, int begin, int limit) {
		String hql = "from CategorySecond";
		List<CategorySecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql, new Object[]{}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/**
	 * 查找二级分类总记录数执行的方法
	 * @return
	 */
	public int findTotalCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 添加二级分类执行的方法
	 * @param categorySecond 
	 */
	public void addCategorySecond(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	/**
	 * 按二级分类id查询二级分类
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	/**
	 * 更新二级分类
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	/**
	 * 删除二级分类执行的方法
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	/**
	 * 查找所有二级分类
	 * @return
	 */
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		List<CategorySecond> csList = this.getHibernateTemplate().find(hql);
		if(csList != null && csList.size() > 0){
			return csList;
		}
		return null;
	}
	
}
