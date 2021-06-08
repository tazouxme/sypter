package com.tazouxme.sypter.spring.builder;

import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.tazouxme.sypter.Sypter;
import com.tazouxme.sypter.spring.builder.certificate.FileCertificateManagerBuilder;
import com.tazouxme.sypter.spring.builder.keystore.FileKeyStoreManagerBuilder;
import com.tazouxme.sypter.spring.parser.SypterBeanDefinitionParser;

public class FileSypterBuilder {
	
	private static final String KEYSTORE_PREFIX = "sypter-keyStoreManager-";
	private static final String CERTIFICATE_PREFIX = "sypter-certificateManager-";
	
	private Element element;
	
	public FileSypterBuilder(Element element) {
		super();
		this.element = element;
	}
	
	public BeanDefinitionBuilder build(ParserContext pc) {
		if (element == null) {
			throw new UnsupportedOperationException("XML Element not initialized");
		}
		
		BeanDefinitionBuilder sypterBuilder = BeanDefinitionBuilder.genericBeanDefinition(Sypter.class);
		sypterBuilder.addConstructorArgReference(obtainKeyStoreParameters(pc));
		sypterBuilder.addConstructorArgReference(obtainCertificateParameters(pc));
		
		return sypterBuilder;
	}
	
	private String obtainKeyStoreParameters(ParserContext pc) {
		String id = element.getAttribute(SypterBeanDefinitionParser.ATTRIBUTE_ID);
		String keystore = element.getAttribute(SypterBeanDefinitionParser.ATTRIBUTE_KEYSTORE);
		String keystorePassword = element.getAttribute(SypterBeanDefinitionParser.ATTRIBUTE_KEYSTORE_PASS);
		String keyAlias = element.getAttribute(SypterBeanDefinitionParser.ATTRIBUTE_KEY_ALIAS);
		String keyPassword = element.getAttribute(SypterBeanDefinitionParser.ATTRIBUTE_KEY_PASS);
		
		FileKeyStoreManagerBuilder builder = new FileKeyStoreManagerBuilder();
		BeanDefinitionBuilder keyStoreManagerBuilder = builder.generateKeyStoreManager(keystore, keystorePassword, keyAlias, keyPassword);

		AbstractBeanDefinition beanDefinition = keyStoreManagerBuilder.getBeanDefinition();
		BeanComponentDefinition component = new BeanComponentDefinition(beanDefinition, KEYSTORE_PREFIX + id);
		
		pc.registerBeanComponent(component);
		return component.getBeanName();
	}
	
	private String obtainCertificateParameters(ParserContext pc) {
		String id = element.getAttribute(SypterBeanDefinitionParser.ATTRIBUTE_ID);
		String certificate = element.getAttribute(SypterBeanDefinitionParser.ATTRIBUTE_CERTIFICATE);
		
		FileCertificateManagerBuilder builder = new FileCertificateManagerBuilder();
		BeanDefinitionBuilder certificateManagerBuilder = builder.generateCertificateManager(certificate);

		AbstractBeanDefinition beanDefinition = certificateManagerBuilder.getBeanDefinition();
		BeanComponentDefinition component = new BeanComponentDefinition(beanDefinition, CERTIFICATE_PREFIX + id);
		
		pc.registerBeanComponent(component);
		return component.getBeanName();
	}

}
