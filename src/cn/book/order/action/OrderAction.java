package cn.book.order.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cn.book.cart.vo.Cart;
import cn.book.cart.vo.CartItem;
import cn.book.order.entities.Order;
import cn.book.order.entities.OrderItem;
import cn.book.order.service.OrderService;
import cn.book.user.entities.User;
import cn.book.user.util.PageBean;
import cn.book.user.util.PaymentUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	private Order order = new Order();
	private OrderService orderService;
	private Integer page;
	// 接收付款成功后的参数:
	private String r3_Amt;
	private String r6_Order;
	
	//接受支付通道编码
	private String pd_FrpId;
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
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	//生成订单
	public String submit() {
		//保存订单数据到数据库
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		try {
			order.setOrdertime(sdf.parse(currentTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		order.setState(1);
		//从session获取购物车信息
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null) {
			this.addActionError("购物车为空，无法提交订单！");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//设置订单项集合内容，从购物项中获得内容
		for(CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		//设置订单所属用户
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser == null) {
			this.addActionError("请先登录！");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		//提交订单后清空购物车
		cart.clearCart();
		return "submitSuccess";
	}
	
	//查询当前用户的所有订单
	public String findByUid() {
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findByPageUid(existUser.getUid(),page);
		//将被分页的数据存入到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByUidSuccess";
	}
	
	//根据订单ID查询
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	
	//为订单付款的方法
	public String payOrder() throws IOException {
		//修改订单
		Order currentOrder = orderService.findByOid(order.getOid());
		currentOrder.setAddress(order.getAddress());
		currentOrder.setName(order.getName());
		currentOrder.setPhone(order.getPhone());
		orderService.update(currentOrder);
		//为订单付款
		String p0_Cmd = "Buy"; 	//业务类型
		String p1_MerId ="10001126856";	//商务编号
		String p2_Order = order.getOid().toString();	//订单编号
		String p3_Amt ="0.01";  //付款金额
		String p4_Cur = "CNY";	//交易币种
		String p5_Pid = "";		//商品名称
		String p6_Pcat = "";	//商品种类
		String p7_Pdesc = "";	//商品描述
		String p8_Url = "http://localhost:8080/bookCity/order_callBack.action";	//支付后跳转的页面
		String p9_SAF = "";	//送货地址
		String pa_MP = "";	//商户拓展信息
		String pd_FrpId = this.pd_FrpId;	//支付通道编码
		String pr_NeedResponse = "1";	//应答机制
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";	//秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);	//
		
		//向易宝发出
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		// 重定向:向易宝出发:
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		
		return NONE;
	}
	
	//付款成功后的转向
	public String callBack() {
		//修改订单状态为已付款
		Order order = orderService.findByOid(Integer.parseInt(r6_Order));
		order.setState(2);
		orderService.update(order);
		//在页面显示成功信息
		this.addActionMessage("支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
		return "msg";
		
	}
	
	//前台修改订单状态为已完成
	public String updateState() {
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateSuccess";
	}
}
