package cn.it.shop.util;

import cn.it.shop.model.FileImage;

public interface FileUpload {

	//3.实现文件上传功能，返回上传后新的文件名称
	public abstract String uploadFile(FileImage fileImage);

}