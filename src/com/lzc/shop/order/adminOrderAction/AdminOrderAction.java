package com.lzc.shop.order.adminOrderAction;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.lzc.shop.order.service.OrderService;
import com.lzc.shop.order.vo.Order;
import com.lzc.shop.order.vo.OrderItem;
import com.lzc.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台订单管理的action
 * 
 * @author lzc
 * 
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {
	private int currentPage;
	private OrderService orderService;
	private Order order = new Order();
//	private int state;
//	private Integer oid;
//	
//	public void setOid(Integer oid) {
//		this.oid = oid;
//	}
//
//	public void setState(int state) {
//		this.state = state;
//	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public Order getModel() {
		return order;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * 分页查询所有的订单
	 * 
	 * @return
	 */
	public String findAllByPage() {
		PageBean<Order> pageBean = orderService.findAll(currentPage);
		ServletActionContext.getContext().getValueStack()
				.set("pageBean", pageBean);
		return "findAllByPage";
	}

	/**
	 * 按订单状态查询订单
	 * 
	 * @return
	 */
	public String findByState() {
		PageBean<Order> pageBean = orderService.findByState(currentPage, order.getState());
		ServletActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByState";
	}

	/**
	 * 更新订单状态
	 * 
	 * @return
	 */
	public String updateState() {
		order = orderService.findByOid(order.getOid());
		order.setState(order.getState() + 1);
		orderService.update(order);
		return "updateState";
	}
	
	/**
	 * 订单详情执行的方法
	 * @return
	 */
	public String findOrderItem(){
		System.out.println(order.getOid());
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
}
