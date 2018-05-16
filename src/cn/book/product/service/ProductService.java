package cn.book.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.book.product.dao.ProductDao;
import cn.book.product.entities.Product;
import cn.book.user.util.PageBean;

//��Ʒҵ���
@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	//��ҳ��������Ʒ��ѯ
	public List<Product> findHot() {
		
		return productDao.findHot();
	}
	
	//��ҳ��������Ʒ��ѯ
	public List<Product> findNew() {
		
		return productDao.findNew();
	}
	
	//����ID��ѯ��Ʒ
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
		
	}
	
	//����һ�������cid�����з�ҳ�Ĳ�ѯ
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿһҳ��ʾ����
		int limit = 12;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		//totalCount % limit == 0?totalPage=totalCount/limit:totalPage=totalCount/limit+1;
		
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//ÿҳ��ʾ�����ݼ���
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		
		return pageBean;
	}
	
	//���ݶ��������ѯ��Ʒ��Ϣ
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿһҳ��ʾ����
		int limit = 12;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		//totalCount % limit == 0?totalPage=totalCount/limit:totalPage=totalCount/limit+1;
		
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//ÿҳ��ʾ�����ݼ���
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	//ҵ����̨��Ʒ��ҳ��ѯ�ķ���
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean();
		pageBean.setPage(page);
		int limit = 10;
		pageBean.setLimit(limit);
		int totalCount = 0;
		totalCount = productDao.findCount();
		int totalPage = 0;
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//ҵ��㱣����Ʒ
	public void save(Product product) {
		productDao.save(product);
	}

	//ҵ���ɾ����Ʒ
	public void delete(Product product) {
		productDao.delete(product);
	}

	//ҵ����޸���Ʒ
	public void update(Product product) {
		productDao.update(product);
		
	}
	
	
}
