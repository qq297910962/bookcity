package cn.book.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.book.category.entities.Category;

//一级分类的持久层对象
public class CategoryDao extends HibernateDaoSupport{
	
	//持久层查询所有一级分类
	public List<Category> findAll() {
		String hql = "from Category";
		
		return this.getHibernateTemplate().find(hql);
	}

	//持久层保存一级分类
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	
	//持久层根据cid查询一级分类
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class,cid);
	}

	//持久层删除一级分类
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	//持久层修改一级分类
	public void update(Category category) {
		this.getHibernateTemplate().update(category);		
	}

}
