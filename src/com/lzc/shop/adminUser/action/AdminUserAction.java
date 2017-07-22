package com.lzc.shop.adminUser.action;

import org.apache.struts2.ServletActionContext;

import com.lzc.shop.adminUser.service.AdminUserService;
import com.lzc.shop.adminUser.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台管理员的action
 * 
 * @author lzc
 *
 */
public class AdminUserAction extends ActionSupport implements
		ModelDriven<AdminUser> {
	private AdminUser adminUser = new AdminUser();
	private AdminUserService adminUserService;

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	@Override
	public AdminUser getModel() {
		return adminUser;
	}

	/**
	 * 后台管理员登录执行的方法
	 * 
	 * @return
	 */
	public String login() {

		AdminUser existAdminUser = adminUserService
				.findByAdminUsername(adminUser.getUsername());

		if (existAdminUser != null
				&& existAdminUser.getUsername().equals(adminUser.getUsername())
				&& existAdminUser.getPassword().equals(adminUser.getPassword())) {
			ServletActionContext.getRequest().getSession()
					.setAttribute("existAdminUser", existAdminUser);
			return "loginsuccess";
		}
		this.addActionError("用户名或密码错误，请重新输入！");
		return "loginfail";
	}

}
