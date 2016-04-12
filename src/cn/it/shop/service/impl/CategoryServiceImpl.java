package cn.it.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Category;
import cn.it.shop.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements
		CategoryService {

	public CategoryServiceImpl() {
		// TODO Auto-generated constructor stub
		super();
	}

	// 查询类别信息，级联管理员 并且分页setFirstResult setMaxResults
	@Override
	public List<Category> queryJoinAccount(String type, int page, int size) {
		String hql = "From Category  c LEFT JOIN  FETCH c.account   where c.type like :type";

		return getSession().createQuery(hql)
				.setString("type", "%" + type + "%")
				.setFirstResult((page - 1) * size).setMaxResults(size).list();
	}

	@Override
	public Long getCount(String type) {
		// TODO Auto-generated method stub

		String hql = "Select count(c) From Category  c   where c.type like :type";

		return (Long) getSession().createQuery(hql)
				.setString("type", "%" + type + "%").uniqueResult();
	}

	@Override
	public void deleteByIds(String ids) {
		// TODO Auto-generated method stub
		System.out.println("===CategoryServiceImpl======deleteByIds("+ids+")==");
		String hql="DELETE FROM Category where id in ("+ids+")";
		getSession().createQuery(hql).executeUpdate();		
	}

}
