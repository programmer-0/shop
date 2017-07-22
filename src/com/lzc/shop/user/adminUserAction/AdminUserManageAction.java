package com.lzc.shop.user.adminUserAction;

import com.lzc.shop.user.service.UserService;
import com.lzc.shop.user.vo.User;
import com.lzc.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台管理用户的action
 * 
 * @author lzc
 * 
 */
public class AdminUserManageAction extends ActionSupport implements
		ModelDriven<User> {
	private User user = new User();
	private int currentPage;
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public User getModel() {
		return user;
	}

	/**
	 * 后台查询所有用户执行的方法
	 * 
	 * @return
	 */
	public String findAll(){
		PageBean<User> pageBean = userService.findAll(currentPage);
		// 把pageBean对象放到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	/**
	 * 跳转到后台编辑用户信息的界面
	 * 
	 * @return
	 */
	public String preEdit(){
		user = userService.findByUid(user.getUid());
		return "preEdit";
	}
	
	/**
	 * 更新用户信息执行的方法
	 * 
	 * @return
	 */
	public String update(){
		userService.update(user);
		return "update";
	}
	
	/**
	 * 删除用户信息执行的方法
	 * 
	 * @return
	 */
	public String delete(){
		userService.delete(user);
		return "delete";
	}
}
