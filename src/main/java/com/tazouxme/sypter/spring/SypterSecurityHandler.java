package com.tazouxme.sypter.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.tazouxme.sypter.spring.parser.SypterBeanDefinitionParser;

public class SypterSecurityHandler extends NamespaceHandlerSupport {
	
	private static final String SYPTER_DEFINITION = "sypter";

	@Override
	public void init() {
		registerBeanDefinitionParser(SYPTER_DEFINITION, new SypterBeanDefinitionParser());
	}

}
