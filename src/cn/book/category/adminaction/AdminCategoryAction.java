package cn.book.category.adminaction;

import java.util.List;

import cn.book.category.entities.Category;
import cn.book.category.service.CategoryService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//��̨һ�������action
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	private Category category = new Category();
	private CategoryService categoryService;
	
	@Override
	public Category getModel() {

		return category;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	//��ִ̨�в�ѯ����һ������
	public String findAll() {
		List<Category> cList = categoryService.findAll();
		//�����ϵ����ݷŵ�ֵջ��
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	//��̨���һ������
	public String addCategory() {
		categoryService.save(category);
		
		return "addSuccess";
	}
	
	//��̨ɾ��һ������ķ���
	public String delete() {
		//ͨ��ģ����������cid��ɾ��һ�������¶���������ɾ��һ������
		//��ѯ
		category = categoryService.findByCid(category.getCid());
		//ɾ��
		categoryService.delete(category);
		
		return "deleteSuccess";
	}
	
	//��̨�༭һ������
	public String edit() {
		//�Ȳ�ѯ��������ʾ��ҳ����
		category = categoryService.findByCid(category.getCid());
		return "edit";
	}
	
	//��ִ̨���޸�һ���������
	public String update() {
		categoryService.update(category);
		return "updateSuccess";
	}
	
}
