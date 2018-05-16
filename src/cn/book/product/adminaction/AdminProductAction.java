package cn.book.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import cn.book.categorysecond.entities.CategorySecond;
import cn.book.categorysecond.service.CategorySecondService;
import cn.book.product.entities.Product;
import cn.book.product.service.ProductService;
import cn.book.user.util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//后台管理商品的action
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	
	private Product product = new Product();
	private ProductService productService;
	private Integer page;
	private CategorySecondService categorySecondService;
	//文件上传的3个参数
	File upload;
	String uploadFileName;
	String uploadContextType;
	@Override
	public Product getModel() {

		return product;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	//带分页的商品查询
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//跳转到商品添加页面
	public String addPage() {
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}
	
	//添加商品
	public String add(){
		//设置日期
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		try {
			product.setPdate(sdf.parse(currentTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//图片上传
		if(upload != null) {
			//获得文件的磁盘上的绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile = new File(realPath + "//" +uploadFileName);
			//文件内容复制
			try {
				FileUtils.copyFile(upload, diskFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "addSuccess";
	}
	
	//删除商品
	public String delete() {
		product = productService.findByPid(product.getPid());
		//删除图片
		String path = product.getImage();
		if(path != null) {
			path = ServletActionContext.getServletContext().getRealPath("/" + path);
			new File(path).delete();
		}
		productService.delete(product);
		return "deleteSuccess";
	}
	
	//跳转到商品修改页面
	public String edit() {
		product = productService.findByPid(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editPage";
	}
	
	//修改商品信息
	public String update() {
		//设置日期
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		try {
			product.setPdate(sdf.parse(currentTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//若有文件上传先删除原文件再上传
		if(upload != null) {
			//删除原来的文件
			String path = product.getImage();
			new File(ServletActionContext.getServletContext().getRealPath("/" + path)).delete();
			//获得上传文件的磁盘上的绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile = new File(realPath + "//" +uploadFileName);
			//文件内容复制
			try {
				FileUtils.copyFile(upload, diskFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
	}
}
