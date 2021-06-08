package com.tazouxme.sypter.crypter.exception;

import com.tazouxme.sypter.exception.SypterSecurityException;

public class CrypterSecurityException extends SypterSecurityException {

	public CrypterSecurityException(String message) {
		super(message);
	}

	public CrypterSecurityException(String message, Throwable e) {
		super(message, e);
	}
	
	@Override
	protected String getType() {
		return "Crypter";
	}

}
