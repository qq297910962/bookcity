package cn.book.index.action;

import java.util.List;

import cn.book.category.entities.Category;
import cn.book.category.service.CategoryService;
import cn.book.product.entities.Product;
import cn.book.product.service.ProductService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//访问首页的action
public class IndexAction extends ActionSupport{
	//注入一级分类的Service
	private CategoryService categoryService;
	//注入商品的Service
	private ProductService productService;
	
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	//执行访问首页
	public String execute(){
		//查询所有一级分类
		List<Category> cList = categoryService.findAll();
		//将一级分类存到Session的范围
		ActionContext.getContext().getSession().put("cList", cList);
		//查询热门商品
		List<Product> hList = productService.findHot();
		//将热门商品保存到值栈中
		ActionContext.getContext().getValueStack().set("hList", hList);
		//查询最新商品
		List<Product> nList = productService.findNew();
		//将最新商品保存到值栈中
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
}
