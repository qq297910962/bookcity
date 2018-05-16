package cn.book.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.book.product.entities.Product;
import cn.book.user.util.PageHibernateCallback;

//商品持久层
public class ProductDao extends HibernateDaoSupport{
	
	//查询热门商品
	public List<Product> findHot() {
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//设定查询热门商品条件为is_host = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		//设定倒叙排序输出，显示最新的热门商品
		criteria.addOrder(Order.desc("pdate"));
		//执行查询,只收集10个
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
	}
	
	//查询最新商品
	public List<Product> findNew() {
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//设定按日期倒叙输出
		criteria.addOrder(Order.desc("pdate"));
		//执行查询,只收集10个
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
	}
	
	//根据商品ID查询商品
	public Product findByPid(Integer pid) {
		
		return this.getHibernateTemplate().get(Product.class, pid);
	}
	
	//根据一级分类Id查询商品个数
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//根据一级分类ID查询商品集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		//分页另一种写法：
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	//根据二级分类ID查询商品个数
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//根据二级分类ID查询商品集合
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//查询所有商品数量
	public int findCount() {
		String hql = "SELECT count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//分页查询商品
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback(hql, null, begin, limit));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//持久层保存商品
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	//持久层删除商品
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
		
	}

	//持久层修改商品
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

	

}
