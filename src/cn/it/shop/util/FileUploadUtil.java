package cn.it.shop.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.it.shop.model.FileImage;


/**
 * 用来实现文件上传的业务逻辑
 * @author THINK
 *
 */

@Component("fileUpload")
public class FileUploadUtil implements FileUpload {
	
	
	private String filePath="C:/";
	
	//通过public.properties 中的filePath注入
	@Value("#{prop.filePath}")
	public void setFilePath(String filePath) {
		System.out.println("filePath:"+filePath);
		this.filePath = filePath;
	}
	//1.通过文件名获取后缀名
	private String getFileExt(String fileName){
		String ext=FilenameUtils.getExtension(fileName);
		return ext;
	}
	
	//2.生成UUID 随机数作为新的文件名
	private String newFileName(String fileName){
		String ext=getFileExt(fileName);
		return UUID.randomUUID().toString()+"."+ext;
	}
	
	//3.实现文件上传功能，返回上传后新的文件名称
	/* (non-Javadoc)
	 * @see cn.it.shop.util.FileUpload#uploadFile(cn.it.shop.model.FileImage)
	 */
	@Override
	public String uploadFile(FileImage fileImage){
		//获取
		String pic=newFileName(fileImage.getFilename());
		try {
			FileUtil.copyFile(fileImage.getFile(), new File(filePath,pic));
			return pic;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			fileImage.getFile().delete();
		}
		
	}
	

}
