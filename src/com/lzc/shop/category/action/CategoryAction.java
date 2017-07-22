package com.lzc.shop.category.action;

import com.lzc.shop.category.service.CategoryService;
import com.lzc.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 一级分类的控制层
 * @author lzc
 *
 */
public class CategoryAction extends ActionSupport implements ModelDriven<Category>{
	private Category category = new Category();
	private CategoryService categoryService;
	

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	@Override
	public Category getModel() {
		return category;
	}
	
}
