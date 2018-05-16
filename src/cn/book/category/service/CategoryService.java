package cn.book.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.book.category.dao.CategoryDao;
import cn.book.category.entities.Category;

//һ�������ҵ���
@Transactional
public class CategoryService {
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	//ҵ����ѯ����һ������
	public List<Category> findAll() {
		
		return categoryDao.findAll();
	}

	//ҵ��㱣��һ������
	public void save(Category category) {
		categoryDao.save(category);
		
	}

	//ҵ������cid��ѯһ������
	public Category findByCid(Integer cid) {

		return categoryDao.findByCid(cid);
	}

	//ҵ���ɾ��һ������
	public void delete(Category category) {
		categoryDao.delete(category);
		
	}

	//ҵ����޸�һ������
	public void update(Category category) {
		categoryDao.update(category);	
	}
	
	
}
