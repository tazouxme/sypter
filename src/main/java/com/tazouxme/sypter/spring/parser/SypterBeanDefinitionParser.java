package com.tazouxme.sypter.spring.parser;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.tazouxme.sypter.spring.builder.FileSypterBuilder;

public class SypterBeanDefinitionParser implements BeanDefinitionParser {
	
	public static final String ATTRIBUTE_ID = "id";
	public static final String ATTRIBUTE_KEYSTORE = "keystore";
	public static final String ATTRIBUTE_KEYSTORE_PASS = "keystore-password";
	public static final String ATTRIBUTE_KEY_ALIAS = "key-alias";
	public static final String ATTRIBUTE_KEY_PASS = "key-password";
	public static final String ATTRIBUTE_CERTIFICATE = "certificate";

	@Override
	public BeanDefinition parse(Element element, ParserContext pc) {
		String id = element.getAttribute(ATTRIBUTE_ID);
		BeanDefinitionBuilder sypterBuilder = new FileSypterBuilder(element).build(pc);
		
		AbstractBeanDefinition sypter = sypterBuilder.getBeanDefinition();
		sypter.setScope(AbstractBeanDefinition.SCOPE_PROTOTYPE);
		
		pc.registerBeanComponent(new BeanComponentDefinition(sypter, id));
		return sypter;
	}

}
