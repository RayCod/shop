package cn.it.shop.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class ConvertPwdPropertyConfigurer extends PropertyPlaceholderConfigurer {
	protected String convertProperty(String propertyName, String propertyValue) {
//		System.out.println("========"+propertyName+"======="+propertyValue);
//		if("user".equals(propertyName)){            		
//			return EncryptAndDecrypt.getInstance().decode(propertyValue);
//        }  
//        if("password".equals(propertyName)){          	
//        	return EncryptAndDecrypt.getInstance().decode(propertyValue);
//        }  
//        
//        return propertyValue; 
		
		return EncryptAndDecrypt.getInstance().decode(propertyValue);
	}

	
}
