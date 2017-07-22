package com.lzc.shop.order.service;

import java.util.List;

import com.lzc.shop.order.dao.OrderDao;
import com.lzc.shop.order.vo.Order;
import com.lzc.shop.order.vo.OrderItem;
import com.lzc.shop.utils.PageBean;

public class OrderService {
	// 注入OrderDao对象
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// 保存订单执行的方法
	public void save(Order order) {
		orderDao.save(order);
	}

	/**
	 * 查询我的订单执行的方法
	 * @param uid
	 * @param currentPage
	 */
	public PageBean<Order> findByUid(Integer uid, int currentPage) {
		PageBean<Order> pageBean = new PageBean<Order>();
		
		// 补全pageBean的信息
		// 设置当前页
		pageBean.setCurrentPage(currentPage);
		// 设置每页显示的订单数量
		int limit = 5;
		pageBean.setLimit(limit);
		// 设置总的记录数
		int totalCount = 0;
		totalCount = orderDao.findTotalCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		System.out.println(totalCount);
		System.out.println(totalPage);
		// 设置订单的集合
		int begin = 0;
		begin = (currentPage - 1) * limit;
		List<Order> list = orderDao.findByUid(uid, begin, limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	/**
	 * 点击付款执行的方法
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}
	
	// 业务层修改订单的方法:
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	/**
	 * 分页查询所有的订单
	 * @param currentPage
	 * @return
	 */
	public PageBean<Order> findAll(int currentPage) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 补全信息
		// 设置当前页
		pageBean.setCurrentPage(currentPage);
		// 设置每页显示的记录
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总的记录数
		int totalCount = orderDao.findTotalCount();
		pageBean.setTotalCount(totalCount);
		// 设置总的页数
		int totalPage = 0;
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		int begin = (currentPage - 1) * limit;
		// 设置订单组成的集合
		List<Order> list = orderDao.findAll(begin, limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	/**
	 * 按状态查询订单
	 * @param state
	 * @param currentPage
	 * @return
	 */
	public PageBean<Order> findByState(int currentPage, int state) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setCurrentPage(currentPage);
		int limit = 10;
		pageBean.setLimit(limit);
		int totalCount = orderDao.findTotalCountByState(state);
		pageBean.setTotalCount(totalCount);
		int totalPage;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (currentPage - 1) * limit;
		List<Order> list = orderDao.findAllByState(begin, limit, state);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 订单详情执行的方法
	 * @param oid
	 * @return
	 */
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItemByOid(oid);
	}

}
