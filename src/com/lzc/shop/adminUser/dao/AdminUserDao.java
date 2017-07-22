package com.lzc.shop.adminUser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lzc.shop.adminUser.vo.AdminUser;

/**
 * 后台管理的持久层
 * 
 * @author lzc
 *
 */
public class AdminUserDao extends HibernateDaoSupport{

	/**
	 * 根据用户名查询后台管理员
	 * @param adminUsername
	 * @return
	 */
	public AdminUser findByAdminUsername(String adminUsername) {
		String hql = "from AdminUser a where a.username = ?";
		List<AdminUser> list = this.getHibernateTemplate().find(hql, adminUsername);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
}
