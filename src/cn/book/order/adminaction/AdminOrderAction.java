package cn.book.order.adminaction;

import java.util.List;

import cn.book.order.entities.Order;
import cn.book.order.entities.OrderItem;
import cn.book.order.service.OrderService;
import cn.book.user.util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//��̨��������action
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
	
	//����ҳ��ѯ����
	public String findAll() {
		PageBean<Order> pageBean = orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//���ݶ���ID��ѯ������
	public String findOrderItem() {
		List<OrderItem> oiList = orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("oiList", oiList);
		return "findOrderItem";
	}
	
	//�޸Ķ���״̬Ϊ�ѷ���
	public String updateState() {
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateSuccess";
	}
}
