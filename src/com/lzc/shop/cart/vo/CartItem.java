package com.lzc.shop.cart.vo;

import com.lzc.shop.product.vo.Product;

/**
 * 购物项的实体类
 * 
 * @author lzc
 * 
 */
public class CartItem {
	// 购物项商品信息
	private Product product;
	// 购物项小计
	private double subtotal;
	// 购物项的数量
	private int count;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getSubtotal() {
		return count * product.getShop_price();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
