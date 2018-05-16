package cn.book.index.action;

import java.util.List;

import cn.book.category.entities.Category;
import cn.book.category.service.CategoryService;
import cn.book.product.entities.Product;
import cn.book.product.service.ProductService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//������ҳ��action
public class IndexAction extends ActionSupport{
	//ע��һ�������Service
	private CategoryService categoryService;
	//ע����Ʒ��Service
	private ProductService productService;
	
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	//ִ�з�����ҳ
	public String execute(){
		//��ѯ����һ������
		List<Category> cList = categoryService.findAll();
		//��һ������浽Session�ķ�Χ
		ActionContext.getContext().getSession().put("cList", cList);
		//��ѯ������Ʒ
		List<Product> hList = productService.findHot();
		//��������Ʒ���浽ֵջ��
		ActionContext.getContext().getValueStack().set("hList", hList);
		//��ѯ������Ʒ
		List<Product> nList = productService.findNew();
		//��������Ʒ���浽ֵջ��
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
}
