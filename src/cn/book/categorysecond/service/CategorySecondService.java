package cn.book.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.book.categorysecond.dao.CategorySecondDao;
import cn.book.categorysecond.entities.CategorySecond;
import cn.book.user.util.PageBean;

//二级分类管理业务层
@Transactional
public class CategorySecondService {
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	
	//业务层后台查询带分页二级分类
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每一页显示的最大记录数
		int limit = 10;
		pageBean.setLimit(10);
		//设置总记录数
		int totalCount = 0;
		totalCount = categorySecondDao.findCount();
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//设置数据集合
		int begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//业务层添加二级分类
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
		
	}

	//业务层删除二级分类
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);		
	}

	//根据二级分类ID查询
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}

	//业务层修改二级分类
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	//业务层查询所有二级分类
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}
	
}
