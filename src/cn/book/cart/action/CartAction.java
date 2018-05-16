package cn.book.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.book.cart.vo.Cart;
import cn.book.cart.vo.CartItem;
import cn.book.product.service.ProductService;

import com.opensymphony.xwork2.ActionSupport;

//购物车的action
public class CartAction extends ActionSupport{
	
	private Integer pid;
	private Integer count;
	//注入商品的Service
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

	//显示我的购物车
	public String myCart() {
		return "myCart";
	}
	
	//将购物项添加到购物车
	public String addCartItem() {
		//封装一个CartItem对象
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(productService.findByPid(pid));//设置商品信息
		//从Session中获取购物车对象
		getCart().addCartItem(cartItem);
		
		return "myCart";
	}
	
	//从Session中获取购物车对象
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null) {
			 cart = new Cart();
			 ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	//清空购物车
	public String clearCart() {
		getCart().clearCart();
		return "myCart";
	}
	
	//移除购物项
	public String removeCartItem() {
		getCart().removeCartItem(pid);
		return "myCart";
	}
}
