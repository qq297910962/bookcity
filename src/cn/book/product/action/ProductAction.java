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

//��ƷAction
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product = new Product();
	private ProductService productService;
	//����һ������ID
	private Integer cid;
	//ע��һ�������Service
	private CategoryService categoryService;
	//���յ�ǰҳ��
	private int page;
	//���ն�������ID
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

	//������ƷID��ѯ��Ʒ
	public String findByPid() {
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	//����һ������ID��ѯ��Ʒ
	public String findByCid() {
		//List<Category> cList = categoryService.findAll();
		//����һ�������ѯ��Ʒ������ҳ��ѯ
		PageBean<Product> pageBean = productService.findByPageCid(cid,page); 
		//��PageBean����ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//���ݶ�������ID��ѯ��Ʒ
	public String findByCsid() {
		//���ݶ��������ѯ����ҳ����Ʒ
		PageBean<Product> pageBean = productService.findByPageCsid(csid,page);
		//��PageBean����ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

	
}
