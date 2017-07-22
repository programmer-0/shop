package com.lzc.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lzc.shop.category.dao.CategoryDao;
import com.lzc.shop.category.vo.Category;

/**
 * 一级分类的业务层
 * 
 * @author lzc
 * 
 */
@Transactional
public class CategoryService {
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * 查询所有一级分类执行的方法
	 * 
	 * @return
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	/**
	 * 根据cid查询一级分类执行的方法
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}

	/**
	 * 添加一级分类执行的方法
	 * @param category
	 */
	public void save(Category category) {
		categoryDao.save(category);
	}

	/**
	 * 删除一级分类执行的方法
	 * @param cid
	 */
	public void deleteCategory(Category category) {
		categoryDao.deleteCategory(category);
	}

}
