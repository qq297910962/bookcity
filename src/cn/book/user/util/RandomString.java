package cn.book.user.util;

import java.util.UUID;

//��������ַ�������
public class RandomString {
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
