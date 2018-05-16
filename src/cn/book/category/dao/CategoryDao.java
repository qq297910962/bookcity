package cn.book.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.book.category.entities.Category;

//һ������ĳ־ò����
public class CategoryDao extends HibernateDaoSupport{
	
	//�־ò��ѯ����һ������
	public List<Category> findAll() {
		String hql = "from Category";
		
		return this.getHibernateTemplate().find(hql);
	}

	//�־ò㱣��һ������
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	
	//�־ò����cid��ѯһ������
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class,cid);
	}

	//�־ò�ɾ��һ������
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	//�־ò��޸�һ������
	public void update(Category category) {
		this.getHibernateTemplate().update(category);		
	}

}
