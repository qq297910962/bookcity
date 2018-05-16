package cn.book.category.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.book.categorysecond.entities.CategorySecond;

//一级分类的实体类,实现序列化接口以防服务器正常关闭硬盘自动序列化session对象出现Session序列化异常
public class Category implements Serializable{
	 private Integer cid;
	 private String cname;
	 //一个一级分类对应多个二级分类
	 private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	 
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	 
	 
}
