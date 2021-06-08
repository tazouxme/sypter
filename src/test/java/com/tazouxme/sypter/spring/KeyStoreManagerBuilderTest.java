package com.tazouxme.sypter.spring;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import com.tazouxme.sypter.spring.builder.keystore.FileKeyStoreManagerBuilder;

public class KeyStoreManagerBuilderTest {
	
	private static final String KEYSTORE = "keystore";
	private static final String KEYSTORE_PASSWORD = "keystorePassword";
	private static final String KEY_ALIAS = "keyAlias";
	private static final String KEY_PASSWORD = "keyPassword";
	
	@Test
	public void testGenerateKeyStoreManagerSuccess() {
		FileKeyStoreManagerBuilder builder = new FileKeyStoreManagerBuilder();
		BeanDefinitionBuilder definitionBuilder = builder.generateKeyStoreManager(KEYSTORE, KEYSTORE_PASSWORD, KEY_ALIAS, KEY_PASSWORD);
		
		assertNotNull(definitionBuilder);
	}
	
	@Test
	public void testGenerateKeyStoreManagerEmptyArgumentsFail() {
		FileKeyStoreManagerBuilder builder = new FileKeyStoreManagerBuilder();
		
		assertThrows(UnsupportedOperationException.class, () -> builder.generateKeyStoreManager("", KEYSTORE_PASSWORD, KEY_ALIAS, KEY_PASSWORD));
		assertThrows(UnsupportedOperationException.class, () -> builder.generateKeyStoreManager(null, KEYSTORE_PASSWORD, KEY_ALIAS, KEY_PASSWORD));
		assertThrows(UnsupportedOperationException.class, () -> builder.generateKeyStoreManager(KEYSTORE, "", KEY_ALIAS, KEY_PASSWORD));
		assertThrows(UnsupportedOperationException.class, () -> builder.generateKeyStoreManager(KEYSTORE, null, KEY_ALIAS, KEY_PASSWORD));
		assertThrows(UnsupportedOperationException.class, () -> builder.generateKeyStoreManager(KEYSTORE, KEYSTORE_PASSWORD, "", KEY_PASSWORD));
		assertThrows(UnsupportedOperationException.class, () -> builder.generateKeyStoreManager(KEYSTORE, KEYSTORE_PASSWORD, null, KEY_PASSWORD));
		assertThrows(UnsupportedOperationException.class, () -> builder.generateKeyStoreManager(KEYSTORE, KEYSTORE_PASSWORD, KEY_ALIAS, ""));
		assertThrows(UnsupportedOperationException.class, () -> builder.generateKeyStoreManager(KEYSTORE, KEYSTORE_PASSWORD, KEY_ALIAS, null));
	}

}
