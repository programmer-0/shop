package com.lzc.shop.adminUser.service;

import org.springframework.transaction.annotation.Transactional;

import com.lzc.shop.adminUser.dao.AdminUserDao;
import com.lzc.shop.adminUser.vo.AdminUser;

/**
 * 后台管理的业务逻辑层
 * 
 * @author lzc
 *
 */
@Transactional
public class AdminUserService {
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	/**
	 * 后台管理员登录执行的方法
	 * @return
	 */
	public AdminUser findByAdminUsername(String adminUsername) {
		return adminUserDao.findByAdminUsername(adminUsername);
	}

	
	
	
}
