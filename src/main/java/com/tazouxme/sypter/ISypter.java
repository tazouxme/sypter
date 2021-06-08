package com.tazouxme.sypter;

import java.util.function.Function;

import com.tazouxme.sypter.entity.Syptered;
import com.tazouxme.sypter.exception.SypterSecurityException;

public interface ISypter<T> {
	
	/**
	 * Encrypt and Sign data
	 * @param entity
	 * @return Object containing encrypted data and digital signature
	 * @throws SypterSecurityException
	 */
	public Syptered sypt(T entity) throws SypterSecurityException;
	
	/**
	 * Decrypt and Verify data
	 * @param syptered - encrypted data and digital signature
	 * @param determinator - returns expected object
	 * @return Expected object
	 * @throws SypterSecurityException
	 */
	public T unsypt(Syptered syptered, Function<String, Class<T>> determinator) throws SypterSecurityException;

}
