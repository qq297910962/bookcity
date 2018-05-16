package cn.book.category.adminaction;

import java.util.List;

import cn.book.category.entities.Category;
import cn.book.category.service.CategoryService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//后台一级分类的action
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
	
	//后台执行查询所有一级分类
	public String findAll() {
		List<Category> cList = categoryService.findAll();
		//将集合的数据放到值栈中
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	//后台添加一级分类
	public String addCategory() {
		categoryService.save(category);
		
		return "addSuccess";
	}
	
	//后台删除一级分类的方法
	public String delete() {
		//通过模型驱动接收cid先删除一级分类下二级分类再删除一级分类
		//查询
		category = categoryService.findByCid(category.getCid());
		//删除
		categoryService.delete(category);
		
		return "deleteSuccess";
	}
	
	//后台编辑一级分类
	public String edit() {
		//先查询将数据显示到页面上
		category = categoryService.findByCid(category.getCid());
		return "edit";
	}
	
	//后台执行修改一级分类操作
	public String update() {
		categoryService.update(category);
		return "updateSuccess";
	}
	
}
