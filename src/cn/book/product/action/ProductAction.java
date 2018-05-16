package cn.book.product.action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.book.category.service.CategoryService;
import cn.book.product.entities.Product;
import cn.book.product.service.ProductService;
import cn.book.user.util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//产品Action
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product = new Product();
	private ProductService productService;
	//接收一级分类ID
	private Integer cid;
	//注入一级分类的Service
	private CategoryService categoryService;
	//接收当前页数
	private int page;
	//接收二级分类ID
	private Integer csid;
	
	
	
	@Override
	public Product getModel() {
		return product;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public Integer getCid() {
		return cid;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}	
	public void setPage(int page) {
		this.page = page;
	}	
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	//根据商品ID查询商品
	public String findByPid() {
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	//根据一级分类ID查询商品
	public String findByCid() {
		//List<Category> cList = categoryService.findAll();
		//根据一级分类查询商品，带分页查询
		PageBean<Product> pageBean = productService.findByPageCid(cid,page); 
		//将PageBean存入值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//根据二级分类ID查询商品
	public String findByCsid() {
		//根据二级分类查询带分页的商品
		PageBean<Product> pageBean = productService.findByPageCsid(csid,page);
		//将PageBean存入值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

	
}
