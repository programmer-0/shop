package com.lzc.shop.index.action;

import java.util.List;

import com.lzc.shop.category.service.CategoryService;
import com.lzc.shop.category.vo.Category;
import com.lzc.shop.product.service.ProductService;
import com.lzc.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页访问的action
 * 
 * @author 昌哥
 * 
 */
public class IndexAction extends ActionSupport {
	private CategoryService categoryService;
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 访问首页执行的方法
	 */
	public String execute() {
		// 查询所有一级分类
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getSession().put("cList", cList);
		
		// 查询热门商品 
		List<Product> hList = productService.findHot();
		// 使用值栈是因为热门商品只在首页显示一次
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		// 查询最新商品
		List<Product> nList = productService.findNew();
		// 保存到值栈中
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		return "index";
	}
}
