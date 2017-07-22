package com.lzc.shop.category.adminCategoryAction;

import java.util.List;

import com.lzc.shop.category.service.CategoryService;
import com.lzc.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements
		ModelDriven<Category> {
	private Category category = new Category();
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public Category getModel() {
		return category;
	}

	/**
	 * 查询所有一级分类执行的方法
	 * 
	 * @return
	 */
	public String findAll() {
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);;
		return "findAll";
	}
	
	/**
	 * 跳转到编辑一级分类界面执行的方法
	 * 
	 * @return
	 */
	public String preEdit(){
		category = categoryService.findByCid(category.getCid());
		return "editPage";
	}
	
	/**
	 * 删除一级分类执行的方法
	 * @return
	 */
	public String delete(){
		
		return "delete";
	}
	
	/**
	 * 添加一级分类执行的方法
	 * @return
	 */
	public String addCategory(){
		categoryService.save(category);
		return "addCategory";
	}
	
	/**
	 * 删除一级分类执行的方法
	 * @return
	 */
	public String deleteCategory(){
		categoryService.deleteCategory(category);
		return "deleteCategory";
	}

}
