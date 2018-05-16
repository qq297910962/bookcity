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

	//保存订单信息
	public void save(Order order) {
		orderDao.save(order);
		
	}
	
	//业务层我的订单查询
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//设置当前页
		pageBean.setPage(page);
		//设置每一页显示数
		int limit = 5;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = orderDao.findCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每一页显示的数据集合
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPageUid(uid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//业务层根据订单查询订单
	public Order findByOid(Integer oid) {
		
		return orderDao.findByOid(oid);
		
	}

	//业务层修改订单
	public void update(Order currentOrder) {
		orderDao.update(currentOrder);
	}

	//业务层带分页查询订单
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

	//业务层根据订单ID查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
	
}
