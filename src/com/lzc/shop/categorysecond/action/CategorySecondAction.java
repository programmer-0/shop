package com.lzc.shop.categorysecond.action;

import com.lzc.shop.categorysecond.service.CategorySecondService;
import com.lzc.shop.categorysecond.vo.CategorySecond;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 二级分类的控制层
 * 
 * @author lzc
 * 
 */
public class CategorySecondAction extends ActionSupport implements
		ModelDriven<CategorySecond> {
	private CategorySecondService categorySecondService;
	private CategorySecond categorySecond = new CategorySecond();

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}

}
