package cn.book.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import cn.book.adminuser.dao.AdminUserDao;
import cn.book.adminuser.entities.AdminUser;

//��̨����Աҵ���
@Transactional
public class AdminUserService {
	private AdminUserDao adminUserDao;
	
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	//ҵ����¼
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}
