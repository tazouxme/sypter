package com.tazouxme.sypter.cert;

import java.security.PublicKey;

import com.tazouxme.sypter.exception.SypterSecurityException;

public interface CertificateManager {

	/**
	 * Retrieve the {@link PublicKey} from the Certificate
	 * @return Found {@link PublicKey}
	 * @throws ResourceReflectionException
	 */
	public PublicKey getPublicKey() throws SypterSecurityException;

}
