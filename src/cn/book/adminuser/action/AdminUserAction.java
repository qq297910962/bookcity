package cn.book.adminuser.action;

import org.apache.struts2.ServletActionContext;

import cn.book.adminuser.entities.AdminUser;
import cn.book.adminuser.service.AdminUserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	private AdminUser adminUser= new AdminUser();
	private AdminUserService adminUserService;
	@Override
	public AdminUser getModel() {
		
		return this.adminUser;
	}
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	//后台登录方法
	public String login() {
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if(existAdminUser == null) {
			this.addActionError("用户名或密码错误！");
			return "loginFail";
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}

	}
	
}
