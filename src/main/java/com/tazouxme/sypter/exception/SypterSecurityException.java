package com.tazouxme.sypter.exception;

import org.apache.commons.lang3.StringUtils;

public class SypterSecurityException extends Exception {
	
	private int code = 500;
	private String reason = "";

	public SypterSecurityException(String message) {
		super(message);
	}

	public SypterSecurityException(String message, Throwable e) {
		super(message, e);
	}

	public SypterSecurityException(int code, String message) {
		this(code, message, message);
	}

	public SypterSecurityException(int code, String reason, String message) {
		this(message);
		this.code = code;
		this.reason = reason;
	}

	public SypterSecurityException(int code, String message, Throwable e) {
		this(code, message, message, e);
	}

	public SypterSecurityException(int code, String reason, String message, Throwable e) {
		this(message, e);
		this.code = code;
		this.reason = reason;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getReason() {
		return reason;
	}
	
	@Override
	public String getMessage() {
		StringBuilder b = new StringBuilder();
		b.append(code + " ");
		
		if (StringUtils.isNotEmpty(reason)) {
			b.append("(" + reason + "): ");
		}
		
		b.append("Agred.io Security [");
		b.append(getType());
		b.append("] ");
		b.append(super.getMessage());
		
		return b.toString();
	}

	protected String getType() {
		return "Sypter";
	}

}
