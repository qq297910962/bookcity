package cn.book.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.book.user.dao.UserDao;
import cn.book.user.entities.User;
import cn.book.user.util.MailService;
import cn.book.user.util.RandomString;

//用户模块业务层
@Transactional//标记为事务
public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//根据用户名查询用户
	public User findUserName(String userName) {
		return userDao.findUserName(userName);
	}
	
	//用户注册
	public void save(User user) {
		user.setState(0);//0表示该用户未激活
		String code = RandomString.getUUID()+RandomString.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送激活邮件
		MailService.sendMail(user.getEmail(), code);
	}
	
	//业务层根据激活码查询用户是否存在
	public User findCode(String code) {
		
		return userDao.findCode(code);
	}
	
	//修改用户
	public void update(User existUser) {
		userDao.update(existUser);
	}
	
	//业务层用户登录
	public User login(User user) {
		return userDao.login(user);
	}
}
