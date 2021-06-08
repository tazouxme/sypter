package com.tazouxme.sypter.signer.exception;

import com.tazouxme.sypter.exception.SypterSecurityException;

public class SignerSecurityException extends SypterSecurityException {

	public SignerSecurityException(String message) {
		super(message);
	}

	public SignerSecurityException(String message, Throwable e) {
		super(message, e);
	}
	
	@Override
	protected String getType() {
		return "Signer";
	}

}
