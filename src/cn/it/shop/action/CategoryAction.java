package cn.it.shop.action;


import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Category;
import cn.it.shop.service.CategoryService;

import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * @author THINK
 * ModelDriven:此接口必须实现getModel()方法，此方法会把返回对象压到栈顶中
 */
@Controller
@Scope("prototype")
public class CategoryAction extends BaseAction<Category>{
	
	public String queryJoinAccount(){		
		//用来分页的数据
		 pageMap=new HashMap<String,Object>();
		//根据关键字和分页的参数查询相应的数据
		 List<Category> categorylist=categoryService.queryJoinAccount(model.getType(), page, rows);
		 //根据关键字查询总记录数
		 pageMap.put("rows",categorylist);
		 pageMap.put("total", categoryService.getCount(model.getType())); 
		 return "jsonMap";
	}
	
	public String deleteByIds(){
		System.out.println("======要删除的ids为:"+ids);
		categoryService.deleteByIds(ids);
		//返回流的格式
		inputStream=new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	
	
	public void update(){	
		System.out.println(model);
		categoryService.update(model);
	}
	
	public void save(){
		System.out.println(model);
		categoryService.save(model);
	}
	
	public String query(){		
		jsonList=categoryService.query();
		
		return "jsonList";
	}
	

	

}
