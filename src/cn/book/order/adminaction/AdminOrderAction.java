package cn.book.order.adminaction;

import java.util.List;

import cn.book.order.entities.Order;
import cn.book.order.entities.OrderItem;
import cn.book.order.service.OrderService;
import cn.book.user.util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//后台订单管理action
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
	private Order order= new Order();
	private OrderService orderService;
	private Integer page;
	@Override
	public Order getModel() {
		return order;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//带分页查询订单
	public String findAll() {
		PageBean<Order> pageBean = orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//根据订单ID查询订单项
	public String findOrderItem() {
		List<OrderItem> oiList = orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("oiList", oiList);
		return "findOrderItem";
	}
	
	//修改订单状态为已发货
	public String updateState() {
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateSuccess";
	}
}
