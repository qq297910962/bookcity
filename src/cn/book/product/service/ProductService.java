package cn.book.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.book.product.dao.ProductDao;
import cn.book.product.entities.Product;
import cn.book.user.util.PageBean;

//商品业务层
@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	//首页上热门商品查询
	public List<Product> findHot() {
		
		return productDao.findHot();
	}
	
	//首页上最新商品查询
	public List<Product> findNew() {
		
		return productDao.findNew();
	}
	
	//根据ID查询商品
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
		
	}
	
	//根据一级分类的cid并带有分页的查询
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每一页显示上限
		int limit = 12;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		//totalCount % limit == 0?totalPage=totalCount/limit:totalPage=totalCount/limit+1;
		
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		
		return pageBean;
	}
	
	//根据二级分类查询商品信息
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每一页显示上限
		int limit = 12;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		//totalCount % limit == 0?totalPage=totalCount/limit:totalPage=totalCount/limit+1;
		
		if(totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	//业务层后台商品分页查询的方法
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

	//业务层保存商品
	public void save(Product product) {
		productDao.save(product);
	}

	//业务层删除商品
	public void delete(Product product) {
		productDao.delete(product);
	}

	//业务层修改商品
	public void update(Product product) {
		productDao.update(product);
		
	}
	
	
}
