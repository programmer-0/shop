package com.lzc.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.lzc.shop.adminUser.vo.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		
		if(adminUser == null){
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("您还没登陆，请前往登录！");
			return "adminLogin";
		}else{
			return actionInvocation.invoke();
		}
	}
}
