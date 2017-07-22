package com.lzc.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.lzc.shop.cart.vo.Cart;
import com.lzc.shop.cart.vo.CartItem;
import com.lzc.shop.product.service.ProductService;
import com.lzc.shop.product.vo.Product;

/**
 * 购物车的控制层
 * 
 * @author lzc
 * 
 */
public class CartAction {
	// 购物项的数量
	private int count;
	// 商品的id
	private Integer pid;
	// 注入商品的service
	private ProductService productService;

	public void setCount(int count) {
		this.count = count;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 添加购物车执行的方法
	 * 
	 * @return
	 */
	public String addCart() {
		// 封装一个CartItem对象
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		Cart cart = getCart();
		cart.addCart(cartItem);
		return "addCart";
	}

	/**
	 * 从session中获得购物车
	 * @return
	 */
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	/**
	 * 移除购物项执行的方法
	 * @return
	 */
	public String removeCart(){
		Cart cart = getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	
	/**
	 * 清空购物车执行的方法
	 * @return
	 */
	public String clearCart(){
		Cart cart = getCart();
		cart.clearCart();
		return "clearCart";
	}
	
	/**
	 * 转向到我的购物车页面
	 * @return
	 */
	public String myCart(){
		return "myCart";
	}
}
