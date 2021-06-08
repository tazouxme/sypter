package com.tazouxme.sypter.spring.builder.certificate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import com.tazouxme.sypter.cert.file.FileCertificateManager;

public class FileCertificateManagerBuilder {
	
	public BeanDefinitionBuilder generateCertificateManager(String certificatePath) {
		if (StringUtils.isEmpty(certificatePath)) {
			throw new UnsupportedOperationException("'certificatePath' property cannot be empty");
		}
		
		BeanDefinitionBuilder certificateManagerBuilder = BeanDefinitionBuilder.genericBeanDefinition(FileCertificateManager.class);
		certificateManagerBuilder.addConstructorArgValue(certificatePath);
		
		return certificateManagerBuilder;
	}

}
