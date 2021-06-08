package com.tazouxme.sypter;

import java.security.GeneralSecurityException;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tazouxme.sypter.cert.CertificateManager;
import com.tazouxme.sypter.cert.KeyStoreManager;
import com.tazouxme.sypter.crypter.ICrypter;
import com.tazouxme.sypter.crypter.SypterCrypter;
import com.tazouxme.sypter.entity.AsymmetricSypterObject;
import com.tazouxme.sypter.entity.Syptered;
import com.tazouxme.sypter.exception.SypterSecurityException;
import com.tazouxme.sypter.signer.ISigner;
import com.tazouxme.sypter.signer.Signer;
import com.tazouxme.sypter.signer.SignerConstants;

public abstract class AbstractSypter<T> implements ISypter<T> {
	
	/**
	 * Crypter which performs the double encryption
	 */
	private ICrypter<byte[], AsymmetricSypterObject> crypter;
	
	/**
	 * Signer which digitally signs
	 */
	private ISigner signer;
	
	/**
	 * Constructor that takes a {@link KeyStoreManager} and a {@link CertificateManager} as input to build up {@link SypterCrypter} and {@link Signer}
	 * @param keyStoreManager
	 * @param certificateManager
	 * @throws GeneralSecurityException
	 */
	public AbstractSypter(KeyStoreManager keyStoreManager, CertificateManager certificateManager) throws GeneralSecurityException {
		this.crypter = new SypterCrypter(keyStoreManager, certificateManager);
		this.signer = new Signer(keyStoreManager, certificateManager);
	}

	@Override
	public Syptered sypt(T entity) throws SypterSecurityException {
		if (!isEntityValid(entity)) {
			throw new SypterSecurityException("Entity cannot be validated");
		}
		
		String object = new String(Base64.encode(encrypt(entity).getBytes()));
		return new Syptered(object, sign(entity, object));
	}

	@Override
	public T unsypt(Syptered syptered, Function<String, Class<T>> determinator) throws SypterSecurityException {
		if (syptered == null || StringUtils.isBlank(syptered.getObject()) || StringUtils.isBlank(syptered.getSignature())) {
			throw new SypterSecurityException("Syptered data cannot be empty");
		}
		
		try {
			T entity = decrypt(new String(Base64.decode(syptered.getObject())), determinator);
			if (!verify(entity, syptered.getObject(), syptered.getSignature())) {
				throw new SypterSecurityException("Cannot verify the digital signature");
			}
			
			return entity;
		} catch (Exception e) {
			throw new SypterSecurityException("Cannot unsypt data", e);
		}
	}
	
	private String encrypt(T entity) throws SypterSecurityException {
		String json = toJSON(entity);
		
		try {
			AsymmetricSypterObject encrypt = crypter.encrypt(json.getBytes());
			return toJSON(encrypt);
		} catch (Exception e) {
			throw new SypterSecurityException("Cannot encrypt data", e);
		}
	}
	
	private T decrypt(String json, Function<String, Class<T>> determinator) throws SypterSecurityException {
		AsymmetricSypterObject object = fromJSON(json, AsymmetricSypterObject.class);
		
		try {
			String o = new String(crypter.decrypt(object));
			return fromJSON(o, determinator.apply(o));
		} catch (SypterSecurityException e) {
			throw new SypterSecurityException("Cannot decrypt data", e);
		}
	}
	
	private String sign(T obj, String json) throws SypterSecurityException {
		String id = getId(obj);
		String toSign = id + SignerConstants.ID_SEPARATOR + json;
		
		try {
			return signer.sign(toSign.getBytes());
		} catch (Exception e) {
			throw new SypterSecurityException("Cannot sign data", e);
		}
	}
	
	private boolean verify(T obj, String json, String signature) throws SypterSecurityException {
		String id = getId(obj);
		String toVerify = id + SignerConstants.ID_SEPARATOR + json;
		
		try {
			return signer.verify(toVerify.getBytes(), signature.getBytes());
		} catch (SypterSecurityException e) {
			throw new SypterSecurityException("Cannot verify signature", e);
		}
	}

	private String toJSON(Object input) throws SypterSecurityException {
		if (input == null) {
			throw new SypterSecurityException("Cannot convert nothing to JSON object");
		}
		
		try {
			return new ObjectMapper().writeValueAsString(input);
		} catch (JsonProcessingException e) {
			throw new SypterSecurityException("Cannot convert to JSON object", e);
		}
	}

	private <O> O fromJSON(String json, Class<O> c) throws SypterSecurityException {
		if (StringUtils.isBlank(json)) {
			throw new SypterSecurityException("Cannot convert empty JSON object");
		}
		
		if (c == null) {
			throw new SypterSecurityException("Target class cannot be null");
		}
		
		try {
			return new ObjectMapper().readValue(json, c);
		} catch (JsonProcessingException e) {
			throw new SypterSecurityException("Cannot convert from JSON object", e);
		}
	}
	
	protected abstract boolean isEntityValid(T obj);
	
	protected abstract String getId(T obj);

}
