package com.tazouxme.sypter.generator;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.tazouxme.sypter.generator.entity.GeneratedValues;

public interface Generator {
	
	public GeneratedValues generateValues() throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	public GeneratedValues generateValues(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	public Key generateSecretKey();
	
	public Key generateSecretKey(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	public String generateId(String prefix, int lenth);
	
	public byte[] generateIv();

}
