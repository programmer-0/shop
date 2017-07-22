package com.lzc.shop.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lzc.shop.user.dao.UserDao;
import com.lzc.shop.user.vo.User;
import com.lzc.shop.utils.PageBean;
import com.lzc.shop.utils.UUIDUtils;

/**
 * 用户模块业务层
 * @author 昌哥
 *
 */
@Transactional
public class UserService {
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 按用户名查询执行的方法
	 */
	public User findByUsername(User user){
		return userDao.findByUsername(user.getUsername());
	}

	/**
	 * 用户注册执行的方法
	 * @param user
	 */
	public void regist(User user) {
		// 完善user对象
		// 设置状态码，0表示未激活，1表示已激活
		user.setState(0);
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		
		userDao.regist(user);
	}

	/**
	 * 用户登录执行的方法
	 * @param user
	 * @return
	 */
	public User login(User user) {
		return userDao.login(user);
	}

	/**
	 * 后台用户管理查询所有用户执行的方法
	 * @param currentPage
	 * @return
	 */
	public PageBean<User> findAll(int currentPage) {
		PageBean<User> pageBean = new PageBean<User>();
		
		// 补齐pageBean对象的信息
		// 设置当前页
		pageBean.setCurrentPage(currentPage);
		// 设置每页显示的记录数
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总的记录数
		int totalCount;
		totalCount = userDao.findTotalCount();
		pageBean.setTotalCount(totalCount);
		// 设置总的页数
		int totalPage;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin;
		begin = (currentPage - 1) * limit;
		List<User> list = userDao.findAll(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 根据uid查询用户信息执行的方法
	 * @param uid
	 * @return
	 */
	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}

	/**
	 * 更新用户信息执行的方法
	 * 
	 * @param user
	 */
	public void update(User user) {
		userDao.update(user);
	}

	/**
	 * 删除用户执行的方法
	 * @param integer 
	 */
	public void delete(User user) {
		userDao.delete(user);
	}
}
