package com.lzc.shop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lzc.shop.categorysecond.dao.CategorySecondDao;
import com.lzc.shop.categorysecond.vo.CategorySecond;
import com.lzc.shop.utils.PageBean;

/**
 * 二级分类的业务层
 * @author lzc
 *
 */
@Transactional
public class CategorySecondService {
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	/**
	 * 显示所有二级分类执行的方法
	 * @return
	 */
	public PageBean<CategorySecond> findAll(int currentPage) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		// 补齐信息
		// 设置当前页
		pageBean.setCurrentPage(currentPage);
		// 设置总记录数
		int totalCount;
		totalCount = categorySecondDao.findTotalCount();
		pageBean.setTotalCount(totalCount);
		// 设置每页显示的记录数
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总页数
		int totalPage;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置二级分类的集合
		int begin = (currentPage - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findAll(currentPage, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 添加二级分类执行的方法
	 * @param categorySecond 
	 */
	public void addCategorySecond(CategorySecond categorySecond) {
		categorySecondDao.addCategorySecond(categorySecond);
	}

	/**
	 * 按二级分类id查询二级分类执行的方法
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}

	/**
	 * 更新二级分类执行的方法
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	/**
	 * 删除二级分类执行的方法
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	/**
	 * 查询所有二级分类执行的方法
	 * @return
	 */
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}
}
