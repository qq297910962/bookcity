package cn.book.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.book.order.dao.OrderDao;
import cn.book.order.entities.Order;
import cn.book.order.entities.OrderItem;
import cn.book.user.util.PageBean;

@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	//���涩����Ϣ
	public void save(Order order) {
		orderDao.save(order);
		
	}
	
	//ҵ����ҵĶ�����ѯ
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//���õ�ǰҳ
		pageBean.setPage(page);
		//����ÿһҳ��ʾ��
		int limit = 5;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount = orderDao.findCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//����ÿһҳ��ʾ�����ݼ���
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPageUid(uid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//ҵ�����ݶ�����ѯ����
	public Order findByOid(Integer oid) {
		
		return orderDao.findByOid(oid);
		
	}

	//ҵ����޸Ķ���
	public void update(Order currentOrder) {
		orderDao.update(currentOrder);
	}

	//ҵ������ҳ��ѯ����
	public PageBean<Order> findByPage(Integer page) {
		PageBean<Order> pageBean = new PageBean();
		int limit = 10;
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		int totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//ҵ�����ݶ���ID��ѯ������
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
	
}
