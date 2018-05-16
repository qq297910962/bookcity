package cn.book.cart.vo;

import cn.book.product.entities.Product;

//购物项实体类
public class CartItem {
	private Product product;	//商品信息
	private int count;			//商品数量
	private double subtotal;	//商品价格小计
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//小计自动计算
	public double getSubtotal() {
		return product.getShop_price() * count;
	}
	
	
}
