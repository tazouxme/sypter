package com.tazouxme.sypter.generator;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.RandomStringUtils;

import com.tazouxme.sypter.crypter.CryptoConstants;
import com.tazouxme.sypter.generator.entity.GeneratedValues;

public class DefaultGenerator implements Generator {
	
	@Override
	public GeneratedValues generateValues() throws NoSuchAlgorithmException, InvalidKeySpecException {
		return new GeneratedValues(generateSecretKey(), generateIv());
	}
	
	@Override
	public GeneratedValues generateValues(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = generateRandomBytes(8);
		Key key = generateSecretKey(password, salt);
		
		return new GeneratedValues(key, generateIv(), salt);
	}

	@Override
	public Key generateSecretKey() {
		return new SecretKeySpec(generateRandomBytes(32), CryptoConstants.AES);
	}

	@Override
	public Key generateSecretKey(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance(CryptoConstants.PBKDF2);
		KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
		
		return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), CryptoConstants.AES);
	}
	
	@Override
	public String generateId(String prefix, int length) {
		String r = RandomStringUtils.randomAlphanumeric(length);
		return prefix + r;
	}
	
	@Override
	public byte[] generateIv() {
		return generateRandomBytes(16);
	}
	
	private byte[] generateRandomBytes(int length) {
		byte[] bytes = new byte[length];
		Random r = new Random();
		r.nextBytes(bytes);
		
		return bytes;
	}
	
	protected String generateRandomNumerics(int length) {
		return RandomStringUtils.randomNumeric(length);
	}
	
	protected String generateRandomAlphaNumerics(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

}
