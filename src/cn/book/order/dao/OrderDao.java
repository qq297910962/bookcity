package cn.book.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.book.order.entities.Order;
import cn.book.order.entities.OrderItem;
import cn.book.user.util.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport{

	//持久层保存订单
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
		
	}

	//持久层根据用户ID查询订单个数
	public int findCountByUid(Integer uid) {
		String hql = "SELECT count(*) from Order o WHERE o.user.uid = ? ";
		List<Long> list = this.getHibernateTemplate().find(hql,uid);
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		
		return 0;
	}
	
	//根据用户分页查询集合
	public List<Order> findByPageUid(Integer uid, int begin, int limit) {
		String hql = "from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { uid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
		
	}

	//持久层根据订单ID查找订单
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	//持久层订单修改
	public void update(Order currentOrder) {
		this.getHibernateTemplate().update(currentOrder);
		
	}

	//持久层查询订单数量
	public int findCount() {
		String hql = "SELECT count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null&&list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//持久层分页查询订单
	public List<Order> findByPage(int begin, int limit) {
		String hql = "FROM Order order by ordertime desc";
		List<Order> list = getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//持久层根据订单ID查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "FROM OrderItem oi WHERE oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	

}
