package cn.book.categorysecond.adminaction;

import java.util.List;

import cn.book.category.entities.Category;
import cn.book.category.service.CategoryService;
import cn.book.categorysecond.entities.CategorySecond;
import cn.book.categorysecond.service.CategorySecondService;
import cn.book.user.util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//��̨�����������action
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	private CategorySecond categorySecond = new CategorySecond();
	private CategorySecondService categorySecondService;
	private CategoryService categoryService;
	//����ҳ��
	private Integer page;
	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	public void setPage(Integer page) {
		this.page = page;
	}	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	//��̨��ѯ����ҳ�Ķ�������
	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//��ת����Ӷ��������ҳ��
	public String addPage() {
		List<Category> cList = categoryService.findAll();
		//��һ���������ݴ���ҳ�������˵���ʾ
		ActionContext.getContext().getValueStack().set("cList", cList);
		
		return "addPage";
	}
	
	//��Ӷ�������
	public String add() {
		categorySecondService.save(categorySecond);
		return "addSuccess";
	}
	
	//ɾ����������
	public String delete() {
		//�������ɾ������Ҫ�Ȳ�ѯ������cascade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}

	//��ת���޸�ҳ��
	public String edit() {
		//��ѯ���ݽ�������ʾ��ҳ����
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "edit";
	}
	
	//���������޸Ĳ���
	public String update() {
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
