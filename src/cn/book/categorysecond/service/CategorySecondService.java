package cn.book.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.book.categorysecond.dao.CategorySecondDao;
import cn.book.categorysecond.entities.CategorySecond;
import cn.book.user.util.PageBean;

//�����������ҵ���
@Transactional
public class CategorySecondService {
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	
	//ҵ����̨��ѯ����ҳ��������
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿһҳ��ʾ������¼��
		int limit = 10;
		pageBean.setLimit(10);
		//�����ܼ�¼��
		int totalCount = 0;
		totalCount = categorySecondDao.findCount();
		//������ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//�������ݼ���
		int begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//ҵ�����Ӷ�������
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
		
	}

	//ҵ���ɾ����������
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);		
	}

	//���ݶ�������ID��ѯ
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}

	//ҵ����޸Ķ�������
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	//ҵ����ѯ���ж�������
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}
	
}
