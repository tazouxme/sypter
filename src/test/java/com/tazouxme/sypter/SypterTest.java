package com.tazouxme.sypter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.security.GeneralSecurityException;

import org.junit.jupiter.api.Test;

import com.tazouxme.sypter.cert.file.FileCertificateManager;
import com.tazouxme.sypter.cert.file.FileKeyStoreManager;
import com.tazouxme.sypter.entity.Syptered;
import com.tazouxme.sypter.entity.TestSypterEntity;
import com.tazouxme.sypter.exception.SypterSecurityException;

public class SypterTest {
	
	private static final String KEYSTORE_PATH = "classpath:cert/x-test.p12";
	private static final String KEYSTORE_PASSWORD = "xstorepass";
	private static final String KEY_ALIAS = "x-test";
	private static final String KEY_PASSWORD = "xstorepass";
	private static final String CERTIFICATE_PATH = "classpath:cert/x-test.crt";
	
	private FileKeyStoreManager keyStoreManager = new FileKeyStoreManager(KEYSTORE_PATH, KEYSTORE_PASSWORD, KEY_PASSWORD, KEY_ALIAS);
	private FileCertificateManager certificateManager = new FileCertificateManager(CERTIFICATE_PATH);
	
	@Test
	public void testSyptSuccess() {
		TestSypterEntity entity = new TestSypterEntity();
		entity.setId("ID");
		entity.setText("TEXT");
		
		try {
			Sypter<TestSypterEntity> sypter = new Sypter<>(keyStoreManager, certificateManager);
			
			Syptered encryptedEntity = sypter.sypt(entity);
			TestSypterEntity decryptedEntity = sypter.unsypt(encryptedEntity, (object) -> TestSypterEntity.class);
			
			assertEquals(entity.getId(), decryptedEntity.getId());
		} catch (GeneralSecurityException e) {
			fail("Failed initializing crypter/signer", e);
		} catch (SypterSecurityException e) {
			fail("Failed sypting data", e);
		}
	}
	
	@Test
	public void testSyptEmptyIdFail() {
		TestSypterEntity entity = new TestSypterEntity();
		entity.setId("");
		entity.setText("TEXT");
		
		try {
			Sypter<TestSypterEntity> sypter = new Sypter<>(keyStoreManager, certificateManager);
			
			assertThrows(SypterSecurityException.class, () -> { sypter.sypt(entity); });
		} catch (GeneralSecurityException e) {
			fail("Failed initializing crypter/signer", e);
		}
	}

	@Test
	public void testSyptWrongKeyAliasFail() {
		FileKeyStoreManager keyStoreManager = new FileKeyStoreManager(KEYSTORE_PATH, KEYSTORE_PASSWORD, KEY_PASSWORD, "abcd");
		FileCertificateManager certificateManager = new FileCertificateManager(CERTIFICATE_PATH);

		TestSypterEntity entity = new TestSypterEntity();
		entity.setId("ID");
		entity.setText("TEXT");
		
		try {
			Sypter<TestSypterEntity> sypter = new Sypter<>(keyStoreManager, certificateManager);
			
			assertThrows(SypterSecurityException.class, () -> { sypter.sypt(entity); });
		} catch (GeneralSecurityException e) {
			fail("Failed initializing crypter/signer", e);
		}
	}

	@Test
	public void testSyptWrongKeyPasswordFail() {
		FileKeyStoreManager keyStoreManager = new FileKeyStoreManager(KEYSTORE_PATH, KEYSTORE_PASSWORD, "abcd", KEY_ALIAS);
		FileCertificateManager certificateManager = new FileCertificateManager(CERTIFICATE_PATH);

		TestSypterEntity entity = new TestSypterEntity();
		entity.setId("ID");
		entity.setText("TEXT");
		
		try {
			Sypter<TestSypterEntity> sypter = new Sypter<>(keyStoreManager, certificateManager);
			
			assertThrows(SypterSecurityException.class, () -> { sypter.sypt(entity); });
		} catch (GeneralSecurityException e) {
			fail("Failed initializing crypter/signer", e);
		}
	}

	@Test
	public void testSyptWrongKeystorePasswordFail() {
		FileKeyStoreManager keyStoreManager = new FileKeyStoreManager(KEYSTORE_PATH, "abcd", KEY_PASSWORD, KEY_ALIAS);
		FileCertificateManager certificateManager = new FileCertificateManager(CERTIFICATE_PATH);

		TestSypterEntity entity = new TestSypterEntity();
		entity.setId("ID");
		entity.setText("TEXT");
		
		try {
			Sypter<TestSypterEntity> sypter = new Sypter<>(keyStoreManager, certificateManager);
			
			assertThrows(SypterSecurityException.class, () -> { sypter.sypt(entity); });
		} catch (GeneralSecurityException e) {
			fail("Failed initializing crypter/signer", e);
		}
	}

}
