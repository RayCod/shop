package cn.it.shop.service;

import java.util.List;

import cn.it.shop.model.Category;
import cn.it.shop.model.Product;

public interface ProductService extends BaseService<Product> {
	//根据关键字和分页的参数查询相应的数据
		public List<Product> queryJoinCategory(String name,int page,int size);
		 //根据关键字查询总记录数	
		public Long getCount(String name);
}
