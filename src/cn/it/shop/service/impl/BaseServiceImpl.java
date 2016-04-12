package cn.it.shop.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.it.shop.service.BaseService;

@SuppressWarnings("unchecked")
@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T> {
	
	private Class clazz;
	
	public BaseServiceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println("this代表的是当前调用构造方法的对象："+this);
		System.out.println("获取当前this对象的父类信息："+this.getClass().getSuperclass());
		System.out.println("获取当前this对象的父类信息："+this.getClass().getGenericSuperclass());
		
		ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class) type.getActualTypeArguments()[0];

	}
    
	@Resource //没有指定名称，默认是属性的名称与id捆绑
	private SessionFactory sessionFactory;	

	//使用注解就不需要下面代码
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
	
	protected Session getSession() {
		// 从当前线程获取session如果没有则创建session
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		getSession().save(t);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		getSession().update(t);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String hql="Delete "+clazz.getSimpleName()+" c WHERE id=:id";
		getSession().createQuery(hql).setInteger("id", id).executeUpdate();
	
	}

	@Override
	public T get(int id) {
		// TODO Auto-generated method stub
		return (T)getSession().get(clazz, id);
	}

	@Override
	public List<T> query() {
		// TODO Auto-generated method stub
		String hql="From "+clazz.getSimpleName();
		return getSession().createQuery(hql).list();
	}

}
