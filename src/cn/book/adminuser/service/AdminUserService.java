package cn.book.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import cn.book.adminuser.dao.AdminUserDao;
import cn.book.adminuser.entities.AdminUser;

//后台管理员业务层
@Transactional
public class AdminUserService {
	private AdminUserDao adminUserDao;
	
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	//业务层登录
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}
