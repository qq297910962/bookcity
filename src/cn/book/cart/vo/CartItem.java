package cn.book.cart.vo;

import cn.book.product.entities.Product;

//������ʵ����
public class CartItem {
	private Product product;	//��Ʒ��Ϣ
	private int count;			//��Ʒ����
	private double subtotal;	//��Ʒ�۸�С��
	
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
	//С���Զ�����
	public double getSubtotal() {
		return product.getShop_price() * count;
	}
	
	
}
