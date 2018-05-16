package cn.book.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.book.cart.vo.Cart;
import cn.book.cart.vo.CartItem;
import cn.book.product.service.ProductService;

import com.opensymphony.xwork2.ActionSupport;

//���ﳵ��action
public class CartAction extends ActionSupport{
	
	private Integer pid;
	private Integer count;
	//ע����Ʒ��Service
	private ProductService productService;
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//��ʾ�ҵĹ��ﳵ
	public String myCart() {
		return "myCart";
	}
	
	//����������ӵ����ﳵ
	public String addCartItem() {
		//��װһ��CartItem����
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(productService.findByPid(pid));//������Ʒ��Ϣ
		//��Session�л�ȡ���ﳵ����
		getCart().addCartItem(cartItem);
		
		return "myCart";
	}
	
	//��Session�л�ȡ���ﳵ����
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null) {
			 cart = new Cart();
			 ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	//��չ��ﳵ
	public String clearCart() {
		getCart().clearCart();
		return "myCart";
	}
	
	//�Ƴ�������
	public String removeCartItem() {
		getCart().removeCartItem(pid);
		return "myCart";
	}
}
