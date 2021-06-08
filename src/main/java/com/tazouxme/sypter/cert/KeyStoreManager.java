package com.tazouxme.sypter.cert;

import java.security.PrivateKey;
import java.security.PublicKey;

import com.tazouxme.sypter.exception.SypterSecurityException;

public interface KeyStoreManager {

	/**
	 * Retrieve the {@link PublicKey} from the KeyStore for the initialized alias
	 * @return Found {@link PublicKey}
	 * @throws ResourceReflectionException
	 */
	public PublicKey getPublicKey() throws SypterSecurityException;
	
	/**
	 * Retrieve the {@link PrivateKey} from the KeyStore for the initialized alias
	 * @return Found {@link PrivateKey}
	 * @throws ResourceReflectionException
	 */
	public PrivateKey getPrivateKey() throws SypterSecurityException;

}
