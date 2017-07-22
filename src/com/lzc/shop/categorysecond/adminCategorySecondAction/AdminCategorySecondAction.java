package com.lzc.shop.categorysecond.adminCategorySecondAction;

import java.util.List;

import com.lzc.shop.category.service.CategoryService;
import com.lzc.shop.category.vo.Category;
import com.lzc.shop.categorysecond.service.CategorySecondService;
import com.lzc.shop.categorysecond.vo.CategorySecond;
import com.lzc.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台二级分类管理的控制层
 * 
 * @author lzc
 * 
 */
public class AdminCategorySecondAction extends ActionSupport implements
		ModelDriven<CategorySecond> {
	private CategorySecond categorySecond = new CategorySecond();
	private CategorySecondService categorySecondService;
	private CategoryService categoryService;
	private int currentPage;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}

	/**
	 * 显示所有二级分类执行的方法
	 * 
	 * @return
	 */
	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService
				.findAll(currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	/**
	 * 跳转到添加二级分类页面执行的方法
	 * 
	 * @return
	 */
	public String addPage() {
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPage";
	}

	/**
	 * 添加二级分类执行的方法
	 * 
	 * @return
	 */
	public String addCategorySecond() {
		categorySecondService.addCategorySecond(categorySecond);
		return "addCategorySecond";
	}
	
	/**
	 * 跳转到二级分类编辑页面执行的方法
	 * @return
	 */
	public String preEdit(){
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "preEdit";
	}
	
	/**
	 * 编辑二级分类执行的方法
	 * @return
	 */
	public String update(){
		categorySecondService.update(categorySecond);
		return "update";
	}
	
	/**
	 * 删除二级分类执行的方法
	 * @return
	 */
	public String delete(){
		categorySecondService.delete(categorySecond);
		return "delete";
	}
}
