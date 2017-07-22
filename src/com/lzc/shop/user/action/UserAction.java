package com.lzc.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lzc.shop.user.service.UserService;
import com.lzc.shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户的action
 * 
 * @author 昌哥
 * 
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	private UserService userService;

	// 接收验证码:
	private String checkCode;

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User getModel() {
		return user;
	}

	/**
	 * 用户跳转到注册执行的方法
	 * 
	 * @return
	 */
	public String registPage() {

		return "registPage";
	}

	/**
	 * AJAX进行异步校验用户名的执行方法
	 * 
	 * @throws IOException
	 */
	public String findByUsername() throws IOException {
		// 调用userService的findByUsername()方法
		User existUser = userService.findByUsername(user);
		// 获取response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if (user.getUsername().equals("")) {
			response.getWriter().print("用户名不能为空！");
			return NONE;
		}

		if (existUser != null) {
			response.getWriter().print("<font color='red'>用户名已经存在</font>");
		} else {
			response.getWriter().print("<font color='green'>用户名可以用</font>");
		}
		return NONE;
	}

	/**
	 * 用户注册执行的方法
	 */
	public String regist() {
		String checkCode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		if (!checkCode1.equalsIgnoreCase(checkCode)) {
			// 验证码不正确
			this.addActionError("验证码不正确");
			return "checkImgFail";
		}
		// 调用service的regist方法
		userService.regist(user);
		this.addActionMessage("注册成功！请前往邮箱激活！");
		return "msg";
	}

	/**
	 * 跳转到用户登录界面执行的方法
	 */
	public String loginPage() {
		return "loginPage";
	}

	/**
	 * 用户登录执行的方法
	 */
	public String login() {
		String checkCode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkCode1.equalsIgnoreCase(checkCode)){
			// 验证码不正确
			this.addActionError("验证码不正确");
			return "checkImgFail1";
		}
		User existUser = userService.login(user);
		if (existUser == null) {
			this.addActionError("登录失败：用户名或密码错误！");
			return "login";
		} else {
			ServletActionContext.getRequest().getSession()
					.setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}

	/**
	 * 用户退出登录执行的方法
	 */
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}

}
