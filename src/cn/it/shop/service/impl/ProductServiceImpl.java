package cn.it.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Category;
import cn.it.shop.model.Product;
import cn.it.shop.service.ProductService;

@Service("productService")
@SuppressWarnings("unchecked")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {


	@Override
	public List<Product> queryJoinCategory(String name, int page, int size) {
		String hql = "From Product  p LEFT JOIN  FETCH p.category   where p.name like :name";
		return getSession().createQuery(hql)
				.setString("name", "%" + name + "%")
				.setFirstResult((page - 1) * size)
				.setMaxResults(size).list();
	}

	@Override
	public Long getCount(String name) {
		String hql = "Select count(p) From Product  p  where p.name like :name";
		return (Long) getSession().createQuery(hql)
				.setString("name", "%" + name + "%").uniqueResult();
	}


}
