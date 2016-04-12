package cn.it.shop.action;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Product;

@Controller
@Scope("prototype")
public class ProductAction extends BaseAction<Product> {

 //查询商品类别信息
	public String queryJoinCategory(){		
		//用来分页的数据
		 pageMap=new HashMap<String,Object>();
		 System.out.println("name"+model.getName());
		//根据关键字和分页的参数查询相应的数据
		 List<Product> productlist=productService.queryJoinCategory(model.getName(), page, rows);
		 //根据关键字查询总记录数
		 pageMap.put("rows",productlist);
		 pageMap.put("total", productService.getCount(model.getName())); 
		 return "jsonMap";
	}
	
	public void save() throws Exception{	
		String pic=fileUpload.uploadFile(fileImage);
		model.setPic(pic);
		productService.save(model);
	}
}
