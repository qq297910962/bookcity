package cn.book.user.util;

import java.util.UUID;

//生成随机字符串的类
public class RandomString {
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
