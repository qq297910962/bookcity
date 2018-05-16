package cn.book.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.book.order.entities.Order;
import cn.book.order.entities.OrderItem;
import cn.book.user.util.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport{

	//�־ò㱣�涩��
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
		
	}

	//�־ò�����û�ID��ѯ��������
	public int findCountByUid(Integer uid) {
		String hql = "SELECT count(*) from Order o WHERE o.user.uid = ? ";
		List<Long> list = this.getHibernateTemplate().find(hql,uid);
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		
		return 0;
	}
	
	//�����û���ҳ��ѯ����
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

	//�־ò���ݶ���ID���Ҷ���
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	//�־ò㶩���޸�
	public void update(Order currentOrder) {
		this.getHibernateTemplate().update(currentOrder);
		
	}

	//�־ò��ѯ��������
	public int findCount() {
		String hql = "SELECT count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null&&list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	//�־ò��ҳ��ѯ����
	public List<Order> findByPage(int begin, int limit) {
		String hql = "FROM Order order by ordertime desc";
		List<Order> list = getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//�־ò���ݶ���ID��ѯ������
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "FROM OrderItem oi WHERE oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	

}
