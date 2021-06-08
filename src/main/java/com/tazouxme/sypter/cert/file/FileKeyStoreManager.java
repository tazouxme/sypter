package com.tazouxme.sypter.cert.file;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.tazouxme.sypter.cert.KeyStoreManager;
import com.tazouxme.sypter.cert.exception.KeyStoreSecurityException;
import com.tazouxme.sypter.exception.SypterSecurityException;

/**
 * Manager to explore and find {@link PublicKey} / {@link PrivateKey} from a {@link KeyStore} using its path
 * @author Agred.io
 * @since 1.0
 */
public class FileKeyStoreManager implements KeyStoreManager {
	
	/**
	 * KeyStore containing Public / Private keys
	 */
	private KeyStore keyStore;

	/**
	 * Default Spring ResourceLoader to retrieve Certificate
	 */
	private ResourceLoader resourceLoader = new DefaultResourceLoader();
	
	/**
	 * Path where the KeyStore is located
	 */
	private String keyStorePath;
	
	/**
	 * KeyStore password
	 */
	private String keyStorePassword;
	
	/**
	 * Alias of the Key to be retrieved
	 */
	private String keyAlias;
	
	/**
	 * Password of the Key to be retrieved
	 */
	private String keyPassword;
	
	/**
	 * Construct a new {@link FileKeyStoreManager}
	 * @param keyStorePath
	 * @param keyStorePassword
	 * @param keyPassword
	 * @param keyAlias
	 */
	public FileKeyStoreManager(String keyStorePath, String keyStorePassword, String keyPassword, String keyAlias) {
		this.keyStorePath = keyStorePath;
		this.keyStorePassword = keyStorePassword;
		this.keyPassword = keyPassword;
		this.keyAlias = keyAlias;
	}

	/**
	 * Retrieve the {@link PublicKey} from the KeyStore for the initialized alias
	 * @return Found {@link PublicKey}
	 * @throws AgredioGenericSecurityException
	 */
	@Override
	public PublicKey getPublicKey() throws SypterSecurityException {
		try {
			if (keyStore == null) {
				keyStore = readKeystoreFromFile(keyStorePath, keyStorePassword);
			}
			
			Certificate cert = keyStore.getCertificate(keyAlias);
			return cert.getPublicKey();
		} catch (Exception e) {
			throw new KeyStoreSecurityException("Unable to get the PublicKey", e);
		}
	}
	
	/**
	 * Retrieve the {@link PrivateKey} from the KeyStore for the initialized alias
	 * @return Found {@link PrivateKey}
	 * @throws AgredioGenericSecurityException
	 */
	@Override
	public PrivateKey getPrivateKey() throws SypterSecurityException {
		try {
			if (keyStore == null) {
				keyStore = readKeystoreFromFile(keyStorePath, keyStorePassword);
			}
			
			return (PrivateKey) keyStore.getKey(keyAlias, keyPassword.toCharArray());
		} catch (Exception e) {
			throw new KeyStoreSecurityException("Unable to get the PrivateKey", e);
		}
	}

	/**
	 * Extract the {@link KeyStore} using the path of the KeyStore and its initialized password
	 * @param keyStorePath
	 * @param keyStorePassword
	 * @return Found {@link KeyStore}
	 * @throws AgredioGenericSecurityException
	 */
	private KeyStore readKeystoreFromFile(String keyStorePath, String keyStorePassword) throws SypterSecurityException {
		try {
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			Resource resource = resourceLoader.getResource(keyStorePath);
			keystore.load(resource.getInputStream(), keyStorePassword.toCharArray());
			return keystore;
		} catch (Exception e) {
			throw new KeyStoreSecurityException("Unable to get the KeyStore", e);
		}
	}

}
