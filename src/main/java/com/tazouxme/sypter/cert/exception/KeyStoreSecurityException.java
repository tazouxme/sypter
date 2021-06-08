package com.tazouxme.sypter.cert.exception;

import com.tazouxme.sypter.exception.SypterSecurityException;

public class KeyStoreSecurityException extends SypterSecurityException {

	public KeyStoreSecurityException(String message) {
		super(message);
	}

	public KeyStoreSecurityException(String message, Throwable e) {
		super(message, e);
	}
	
	@Override
	protected String getType() {
		return "KeyStoreManager";
	}

}
