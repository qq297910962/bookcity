package cn.book.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.book.user.entities.User;

//�û�ģ��־ò�
public class UserDao extends HibernateDaoSupport{
	//�����û�����ѯ�û�
	public  User findUserName(String userName){
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, userName);
		if(list != null&&list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	//����û�
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}
	
	//���ݼ������ѯ�û�
	public User findCode(String code) {
		String hql = "from User where code = ?";
		List<User> list = this.getHibernateTemplate().find(hql,code);
		if(list != null&&list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	//�޸��û���Ϣ
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}
	
	//��¼��֤
	public User login(User user) {
		String hql = "FROM User WHERE username = ? and password = ? and state = ?";
		List<User> list = this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if(list != null&&list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
