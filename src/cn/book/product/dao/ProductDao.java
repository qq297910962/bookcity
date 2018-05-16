package cn.book.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.book.product.entities.Product;
import cn.book.user.util.PageHibernateCallback;

//��Ʒ�־ò�
public class ProductDao extends HibernateDaoSupport{
	
	//��ѯ������Ʒ
	public List<Product> findHot() {
		//ʹ������������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//�趨��ѯ������Ʒ����Ϊis_host = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		//�趨���������������ʾ���µ�������Ʒ
		criteria.addOrder(Order.desc("pdate"));
		//ִ�в�ѯ,ֻ�ռ�10��
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
	}
	
	//��ѯ������Ʒ
	public List<Product> findNew() {
		//ʹ������������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//�趨�����ڵ������
		criteria.addOrder(Order.desc("pdate"));
		//ִ�в�ѯ,ֻ�ռ�10��
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
	}
	
	//������ƷID��ѯ��Ʒ
	public Product findByPid(Integer pid) {
		
		return this.getHibernateTemplate().get(Product.class, pid);
	}
	
	//����һ������Id��ѯ��Ʒ����
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//����һ������ID��ѯ��Ʒ����
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		//��ҳ��һ��д����
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	//���ݶ�������ID��ѯ��Ʒ����
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//���ݶ�������ID��ѯ��Ʒ����
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//��ѯ������Ʒ����
	public int findCount() {
		String hql = "SELECT count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//��ҳ��ѯ��Ʒ
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback(hql, null, begin, limit));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//�־ò㱣����Ʒ
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	//�־ò�ɾ����Ʒ
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
		
	}

	//�־ò��޸���Ʒ
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

	

}
