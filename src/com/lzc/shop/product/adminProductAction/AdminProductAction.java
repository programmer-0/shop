package com.lzc.shop.product.adminProductAction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.lzc.shop.categorysecond.service.CategorySecondService;
import com.lzc.shop.categorysecond.vo.CategorySecond;
import com.lzc.shop.product.service.ProductService;
import com.lzc.shop.product.vo.Product;
import com.lzc.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台商品管理的控制层
 * 
 * @author lzc
 * 
 */
public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {
	private Product product = new Product();
	private ProductService productService;
	private CategorySecondService categorySecondService;
	private int currentPage;

	// 文件上传操作需要的参数
	private File upload; // 这个属性名要跟页面input的属性名一致
	private String uploadFileName;
	private String uploadContextType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product getModel() {
		return product;
	}

	/**
	 * 显示所有商品执行的方法
	 * 
	 * @return
	 */
	public String findAll() {
		PageBean<Product> pageBean = productService.findAll(currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	/**
	 * 跳转到添加商品的页面
	 * 
	 * @return
	 */
	public String addPage() {
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}

	/**
	 * 添加商品执行的方法
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String save() throws IOException {
		product.setPdate(new Date());
		System.out.println(product.getPdate());
		
		if(upload != null){
			// 得到文件名的绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			System.out.println(realPath);
			// 创建一个文件
			File diskFile = new File(realPath + "//" + uploadFileName);
			
			FileUtils.copyFile(upload, diskFile);
			
			product.setImage("products/" + uploadFileName);
		}
		
		productService.save(product);
		return "save";
	}

	/**
	 * 跳转到商品编辑页面执行的方法
	 * 
	 * @return
	 */
	public String preEdit() {
		product = productService.findByPid(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "preEdit";
	}

	/**
	 * 更新商品信息执行的方法
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String update() throws IOException {
		product.setPdate(new Date());
		if(upload != null){
			// 得到文件名的绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			System.out.println(realPath);
			// 创建一个文件
			File diskFile = new File(realPath + "//" + uploadFileName);
			
			FileUtils.copyFile(upload, diskFile);
			
			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		return "update";
	}

	/**
	 * 删除商品信息执行的方法
	 * 
	 * @return
	 */
	public String delete() {
		productService.delete(product);
		return "delete";
	}
}
