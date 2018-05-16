package cn.book.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.book.user.entities.User;

//用户模块持久层
public class UserDao extends HibernateDaoSupport{
	//根据用户名查询用户
	public  User findUserName(String userName){
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, userName);
		if(list != null&&list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	//添加用户
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}
	
	//根据激活码查询用户
	public User findCode(String code) {
		String hql = "from User where code = ?";
		List<User> list = this.getHibernateTemplate().find(hql,code);
		if(list != null&&list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	//修改用户信息
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}
	
	//登录验证
	public User login(User user) {
		String hql = "FROM User WHERE username = ? and password = ? and state = ?";
		List<User> list = this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if(list != null&&list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
