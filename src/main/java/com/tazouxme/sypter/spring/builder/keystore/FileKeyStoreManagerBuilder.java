package com.tazouxme.sypter.spring.builder.keystore;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import com.tazouxme.sypter.cert.file.FileKeyStoreManager;

public class FileKeyStoreManagerBuilder {
	
	public BeanDefinitionBuilder generateKeyStoreManager(String keystore, String keystorePassword, String keyAlias, String keyPassword) {
		if (StringUtils.isEmpty(keystore)) {
			throw new UnsupportedOperationException("'keystore' property cannot be empty");
		}
		
		if (StringUtils.isEmpty(keystorePassword)) {
			throw new UnsupportedOperationException("'keystorePassword' property cannot be empty");
		}
		
		if (StringUtils.isEmpty(keyAlias)) {
			throw new UnsupportedOperationException("'keyAlias' property cannot be empty");
		}
		
		if (StringUtils.isEmpty(keyPassword)) {
			throw new UnsupportedOperationException("'keyPassword' property cannot be empty");
		}
		
		BeanDefinitionBuilder keystoreManagerBuilder = BeanDefinitionBuilder.genericBeanDefinition(FileKeyStoreManager.class);
		keystoreManagerBuilder.addConstructorArgValue(keystore);
		keystoreManagerBuilder.addConstructorArgValue(keystorePassword);
		keystoreManagerBuilder.addConstructorArgValue(keyPassword);
		keystoreManagerBuilder.addConstructorArgValue(keyAlias);
		
		return keystoreManagerBuilder;
	}

}
