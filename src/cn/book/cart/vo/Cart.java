package cn.book.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

//���ﳵʵ����,��ֹ����Session���л��쳣
public class Cart implements Serializable{
	//Map�����е�keyΪ����������Ʒ��id
	private Map<Integer,CartItem> map = new LinkedHashMap<Integer,CartItem>();
	//����۸��ܼ�
	private double total;
	
	public double getTotal() {
		return total;
	}

	//��ȡ���й�����ļ���
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	//����������ӵ����ﳵ
	public void addCartItem(CartItem cartItem) {
		//��ȡ��ӵĹ���������Ʒ��Id
		Integer pid = cartItem.getProduct().getPid();
		//�жϹ��ﳵ���Ƿ��Ѿ����ڸ���Ʒ
		if(map.containsKey(pid)) {
			//���ڣ�����+1
			map.get(pid).setCount(map.get(pid).getCount() + cartItem.getCount());
		}else {
			//�����ڣ���ӹ�����
			map.put(pid, cartItem);
		}

		total += cartItem.getSubtotal();
	}

	//�ӹ��ﳵ���Ƴ�������
	public void removeCartItem(Integer pid) {
		//�Ƴ��������ȥС��
		total -= map.remove(pid).getSubtotal();

	}

	//��չ��ﳵ
	public void clearCart() {
		//��չ�����
		map.clear();
		//�ܼ���Ϊ0
		total = 0;
	}
}
