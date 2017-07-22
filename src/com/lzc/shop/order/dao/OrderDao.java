package com.lzc.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lzc.shop.order.vo.Order;
import com.lzc.shop.order.vo.OrderItem;
import com.lzc.shop.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport {

	// 向数据库保存订单执行的方法
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	// 查询订单向总的记录数
	public int findTotalCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Order> findByUid(Integer uid, int begin, int limit) {
		String hql = "from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { uid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 点击付款执行的方法
	 * 
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	// DAO层修改订单的方法:
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	/**
	 * 查询表中所有的记录数
	 * @return
	 */
	public int findTotalCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 查询order集合
	 * @param currentPage
	 * @param begin
	 * @return
	 */
	public List<Order> findAll(int begin, int limit) {
		String hql = "from Order";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/**
	 * 按状态查询总记录数
	 * @param state
	 * @return
	 */
	public int findTotalCountByState(int state) {
		String hql = "select count(*) from Order o where o.state = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, state);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 按状态查询订单集合
	 * @param currentPage
	 * @param begin
	 * @param limit
	 * @param state 
	 * @return
	 */
	public List<Order> findAllByState(int begin, int limit, int state) {
		String hql = "from Order o where o.state = ?";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{state}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/**
	 * 订单详情执行的方法
	 * @param oid
	 * @return
	 */
	public List<OrderItem> findOrderItemByOid(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
}
