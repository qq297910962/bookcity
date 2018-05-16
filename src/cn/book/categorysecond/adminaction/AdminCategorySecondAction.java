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

//后台二级分类管理action
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	private CategorySecond categorySecond = new CategorySecond();
	private CategorySecondService categorySecondService;
	private CategoryService categoryService;
	//接收页数
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
	
	
	//后台查询带分页的二级分类
	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//跳转到添加二级分类的页面
	public String addPage() {
		List<Category> cList = categoryService.findAll();
		//将一级分类数据传到页面下拉菜单显示
		ActionContext.getContext().getValueStack().set("cList", cList);
		
		return "addPage";
	}
	
	//添加二级分类
	public String add() {
		categorySecondService.save(categorySecond);
		return "addSuccess";
	}
	
	//删除二级分类
	public String delete() {
		//如果级联删除，需要先查询，配置cascade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}

	//跳转到修改页面
	public String edit() {
		//查询数据将数据显示在页面上
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "edit";
	}
	
	//二级分类修改操作
	public String update() {
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
