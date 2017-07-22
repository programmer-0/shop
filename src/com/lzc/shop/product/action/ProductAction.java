package com.lzc.shop.product.action;

import com.lzc.shop.product.service.ProductService;
import com.lzc.shop.product.vo.Product;
import com.lzc.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements
		ModelDriven<Product> {
	private Product product = new Product();
	private ProductService productService;

	// 接收一级分类cid
	private Integer cid;
	// 接收当前的页数
	private int currentPage;
	// 接收二级分类的id
	private Integer csid;

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product getModel() {
		return product;
	}

	/**
	 * 查看商品详情的方法
	 * 
	 * @return
	 */
	public String findByPid() {
		// Product existProduct = productService.findById(product);
		// ActionContext.getContext().getValueStack().set("existProduct",
		// existProduct);
		// 因为实现了模型驱动，product就是在栈顶，所以不用存入值栈就可以在页面显示了
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}

	/**
	 * 根据分类id查询二级分类和商品（带分页）
	 * 
	 * @return
	 */
	public String findByCid() {
		// 调用productService的方法查询pageBean对象
		PageBean<Product> pageBean = productService.findByCurrentCid(
				currentPage, cid);
		// 把pageBean保存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}

	/**
	 * 根据二级分类查询商品(带分页)
	 * 
	 * @return
	 */
	public String findByCsid() {
		// 调用productService的findByCsid方法，查询二级分类的商品，并返回一个PageBean对象
		PageBean<Product> pageBean = productService.findByCsid(currentPage, csid);
		
		// 把pageBean保存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByCsid";
	}
}
