package cn.book.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.book.user.dao.UserDao;
import cn.book.user.entities.User;
import cn.book.user.util.MailService;
import cn.book.user.util.RandomString;

//�û�ģ��ҵ���
@Transactional//���Ϊ����
public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//�����û�����ѯ�û�
	public User findUserName(String userName) {
		return userDao.findUserName(userName);
	}
	
	//�û�ע��
	public void save(User user) {
		user.setState(0);//0��ʾ���û�δ����
		String code = RandomString.getUUID()+RandomString.getUUID();
		user.setCode(code);
		userDao.save(user);
		//���ͼ����ʼ�
		MailService.sendMail(user.getEmail(), code);
	}
	
	//ҵ�����ݼ������ѯ�û��Ƿ����
	public User findCode(String code) {
		
		return userDao.findCode(code);
	}
	
	//�޸��û�
	public void update(User existUser) {
		userDao.update(existUser);
	}
	
	//ҵ����û���¼
	public User login(User user) {
		return userDao.login(user);
	}
}
