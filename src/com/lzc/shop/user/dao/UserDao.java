package com.lzc.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lzc.shop.user.vo.User;
import com.lzc.shop.utils.PageHibernateCallback;

/**
 * 用户模块持久层
 * @author 昌哥
 *
 */
public class UserDao extends HibernateDaoSupport{
	
	/**
	 * 按用户名查询执行的方法
	 */
	public User findByUsername(String username){
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 用户注册执行的方法
	 * @param user
	 */
	public void regist(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * 用户登录执行的方法
	 * @param user
	 * @return
	 */
	public User login(User user) {
		String hql = "from User where username = ? and password = ?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getUsername(),user.getPassword());
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查询用户总数执行的方法
	 * @return
	 */
	public int findTotalCount() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 查询用户集合执行的方法
	 * @return
	 */
	public List<User> findAll(int begin, int limit) {
		String hql = "from User";
		List<User> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<User>(hql, new Object[]{}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/**
	 * 根据uid查询用户信息执行的方法
	 * @param uid
	 * @return
	 */
	public User findByUid(Integer uid) {
		String hql = "from User u where u.uid = ?";
		return this.getHibernateTemplate().get(User.class, uid);
	}

	/**
	 * 更新用户信息执行的方法
	 * 
	 * @param user
	 */
	public void update(User user) {
		this.getHibernateTemplate().update(user);
	}

	/**
	 * 删除用户信息执行的方法
	 * @param integer
	 */
	public void delete(User user) {
		this.getHibernateTemplate().delete(user);
	}
}
