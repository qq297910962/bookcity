package cn.book.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.book.categorysecond.entities.CategorySecond;
import cn.book.product.entities.Product;
import cn.book.user.util.PageBean;
import cn.book.user.util.PageHibernateCallback;

//�����������־ò�
public class CategorySecondDao extends HibernateDaoSupport{

	//��ѯ�������������
	public int findCount() {
		String hql = "SELECT count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//�־ò��ѯ����ҳ�Ķ�������
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback(hql, null, begin, limit));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//�־ò���Ӷ�������
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
		
	}
	
	//�־ò�ɾ����������
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	//�־ò���ݶ�������ID��ѯ
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class,csid);
	}

	//�־ò��޸Ķ�������
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);		
	}

	//�־ò��ѯ���ж�������
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		List<CategorySecond> list= this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	

}
