package com.tazouxme.sypter.spring;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import com.tazouxme.sypter.spring.builder.certificate.FileCertificateManagerBuilder;

public class CertificateManagerBuilderTest {
	
	private static final String CERTIFICATE = "certificate";
	
	@Test
	public void testGenerateKeyStoreManagerSuccess() {
		FileCertificateManagerBuilder builder = new FileCertificateManagerBuilder();
		BeanDefinitionBuilder definitionBuilder = builder.generateCertificateManager(CERTIFICATE);
		
		assertNotNull(definitionBuilder);
	}
	
	@Test
	public void testGenerateKeyStoreManagerEmptyArgumentsFail() {
		FileCertificateManagerBuilder builder = new FileCertificateManagerBuilder();
		
		assertThrows(UnsupportedOperationException.class, () -> builder.generateCertificateManager(""));
		assertThrows(UnsupportedOperationException.class, () -> builder.generateCertificateManager(null));
	}

}
