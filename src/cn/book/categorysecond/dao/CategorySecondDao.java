package cn.book.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.book.categorysecond.entities.CategorySecond;
import cn.book.product.entities.Product;
import cn.book.user.util.PageBean;
import cn.book.user.util.PageHibernateCallback;

//二级分类管理持久层
public class CategorySecondDao extends HibernateDaoSupport{

	//查询二级分类的数量
	public int findCount() {
		String hql = "SELECT count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//持久层查询带分页的二级分类
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback(hql, null, begin, limit));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//持久层添加二级分类
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
		
	}
	
	//持久层删除二级分类
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	//持久层根据二级分类ID查询
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class,csid);
	}

	//持久层修改二级分类
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);		
	}

	//持久层查询所有二级分类
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		List<CategorySecond> list= this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	

}
