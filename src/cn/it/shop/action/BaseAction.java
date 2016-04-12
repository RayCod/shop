package cn.it.shop.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.FileImage;
import cn.it.shop.service.AccountService;
import cn.it.shop.service.CategoryService;
import cn.it.shop.service.ProductService;
import cn.it.shop.util.FileUpload;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Struts执行流程：先创建Action,再调用拦截器，拦截器访问成功再调用Action的方法
 * 
 * 在项目启动的时候Struts的过滤器，已经把相应的内置对象和内置对象对应的Map存储到了ActionContext和值栈(ValueStack)中
 * 如果实现了相应的****Aware接口，就会从ActionContext中获取相应的Map进行传入，实现的拦截器为：servletConfig
 * 
 * @author THINK
 * 
 */

@Controller
@Scope("prototype")
public class BaseAction<T> extends ActionSupport implements RequestAware,
		SessionAware, ApplicationAware, ModelDriven<T> {
	
	@Resource
	protected AccountService accountService;

	@Resource
	protected CategoryService categoryService;
	
	@Resource
	protected ProductService productService ;
	@Resource
	protected FileUpload fileUpload;
	
	// 在调用构造方法的时候给model赋值
	protected T model;

	
	//获取要删除的ids	
	protected String ids;
	
	protected List<T> jsonList=null;

	protected FileImage fileImage;
	
	//流
	protected InputStream inputStream;
	
	protected Integer page;
	
	protected Integer rows;
	
	protected Map<String,Object> pageMap=null;
	public Map<String, Object> getPageMap() {
		System.out.println("===getPageMap=====");
		return pageMap;
	}

	protected Map<String, Object> request;

	protected Map<String, Object> session;

	protected Map<String, Object> application;
	
	@Override
	// 返回的对象将要压栈
	public T getModel() {
		return model;
	}
	
	public BaseAction() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		Class clazz = (Class) type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		// TODO Auto-generated method stub
		this.application = application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;

	}
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getIds() {
		return ids;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public List<T> getJsonList() {
		return jsonList;
	}
	
	public FileImage getFileImage() {
		return fileImage;
	}

	public void setFileImage(FileImage fileImage) {
		this.fileImage = fileImage;
	}

}
