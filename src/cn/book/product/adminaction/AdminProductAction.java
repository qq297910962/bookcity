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

//��̨������Ʒ��action
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	
	private Product product = new Product();
	private ProductService productService;
	private Integer page;
	private CategorySecondService categorySecondService;
	//�ļ��ϴ���3������
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
	//����ҳ����Ʒ��ѯ
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//��ת����Ʒ���ҳ��
	public String addPage() {
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}
	
	//�����Ʒ
	public String add(){
		//��������
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		try {
			product.setPdate(sdf.parse(currentTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//ͼƬ�ϴ�
		if(upload != null) {
			//����ļ��Ĵ����ϵľ���·��
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//����һ���ļ�
			File diskFile = new File(realPath + "//" +uploadFileName);
			//�ļ����ݸ���
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
	
	//ɾ����Ʒ
	public String delete() {
		product = productService.findByPid(product.getPid());
		//ɾ��ͼƬ
		String path = product.getImage();
		if(path != null) {
			path = ServletActionContext.getServletContext().getRealPath("/" + path);
			new File(path).delete();
		}
		productService.delete(product);
		return "deleteSuccess";
	}
	
	//��ת����Ʒ�޸�ҳ��
	public String edit() {
		product = productService.findByPid(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editPage";
	}
	
	//�޸���Ʒ��Ϣ
	public String update() {
		//��������
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		try {
			product.setPdate(sdf.parse(currentTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//�����ļ��ϴ���ɾ��ԭ�ļ����ϴ�
		if(upload != null) {
			//ɾ��ԭ�����ļ�
			String path = product.getImage();
			new File(ServletActionContext.getServletContext().getRealPath("/" + path)).delete();
			//����ϴ��ļ��Ĵ����ϵľ���·��
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//����һ���ļ�
			File diskFile = new File(realPath + "//" +uploadFileName);
			//�ļ����ݸ���
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
