package cn.book.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

//购物车实体类,防止出现Session序列化异常
public class Cart implements Serializable{
	//Map集合中的key为购物项中商品的id
	private Map<Integer,CartItem> map = new LinkedHashMap<Integer,CartItem>();
	//购物价格总计
	private double total;
	
	public double getTotal() {
		return total;
	}

	//获取所有购物项的集合
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	//将购物项添加到购物车
	public void addCartItem(CartItem cartItem) {
		//获取添加的购物项中商品的Id
		Integer pid = cartItem.getProduct().getPid();
		//判断购物车中是否已经存在该商品
		if(map.containsKey(pid)) {
			//存在：数量+1
			map.get(pid).setCount(map.get(pid).getCount() + cartItem.getCount());
		}else {
			//不存在，添加购物项
			map.put(pid, cartItem);
		}

		total += cartItem.getSubtotal();
	}

	//从购物车中移除购物项
	public void removeCartItem(Integer pid) {
		//移除购物项并减去小计
		total -= map.remove(pid).getSubtotal();

	}

	//清空购物车
	public void clearCart() {
		//清空购物项
		map.clear();
		//总计设为0
		total = 0;
	}
}
