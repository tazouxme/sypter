package com.tazouxme.sypter.generator.entity;

import java.security.Key;

import org.bouncycastle.util.encoders.Base64;

public class GeneratedValues {
	
	private Key key;
	private byte[] iv;
	private byte[] salt;
	
	public GeneratedValues(Key key, byte[] iv) {
		this.key = key;
		this.iv = iv;
	}
	
	public GeneratedValues(Key key, byte[] iv, byte[] salt) {
		this.key = key;
		this.iv = iv;
		this.salt = salt;
	}
	
	public byte[] getIv() {
		return iv;
	}
	
	public String getEncodedIv() {
		return new String(Base64.encode(iv));
	}
	
	public byte[] getSalt() {
		return salt;
	}
	
	public String getEncodedSalt() {
		return new String(Base64.encode(salt));
	}
	
	public Key getKey() {
		return key;
	}

}
