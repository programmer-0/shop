package com.lzc.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车的实体类
 * 
 * @author lzc
 * 
 */
public class Cart {
	// 购物车用于存放购物项的集合，使用map是因为移除购物项方便
	Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();

	// 使用map.values()方法把map转换集合是因为map在页面中遍历比较麻烦
	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	// 购物车的总计
	private double total;

	public double getTotal() {
		return total;
	}

	/**
	 * 添加购物车
	 */
	public void addCart(CartItem cartItem) {
		/*
		 * * 如果购物车中存在商品 数量 = 新添加的数量 + 原来的数量 总计 = 购物车中的总计 + 新添加购物项的小计 *
		 * 如果购物车中不存在商品 添加商品 总计 = 购物车中的总计 + 新添加购物项的小计
		 */
		Integer pid = cartItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		} else {
			map.put(pid, cartItem);
		}
		total = total + cartItem.getSubtotal();
	}

	/**
	 * 移除购物车
	 */
	public void removeCart(Integer pid) {
		CartItem cartItem = map.remove(pid);
		// 总计 = 总计 - 移除的购物项的小计
		total = total - cartItem.getSubtotal();
	}

	/**
	 * 清空购物车
	 */
	public void clearCart() {
		map.clear();
		// 设置总计为0
		total = 0;
	}
}
