package com.tazouxme.sypter.cert.exception;

import com.tazouxme.sypter.exception.SypterSecurityException;

public class CertificateSecurityException extends SypterSecurityException {

	public CertificateSecurityException(String message) {
		super(message);
	}

	public CertificateSecurityException(String message, Throwable e) {
		super(message, e);
	}
	
	@Override
	protected String getType() {
		return "CertificateManager";
	}

}
