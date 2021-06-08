package com.tazouxme.sypter.crypter;

import com.tazouxme.sypter.exception.SypterSecurityException;

public interface ICrypter<INPUT, OUTPUT> {
	
	/**
	 * Encrypt the data
	 * @param data
	 * @return encrypted data
	 * @throws SypterSecurityException
	 */
	public OUTPUT encrypt(INPUT data) throws SypterSecurityException;
	
	/**
	 * Decrypt data
	 * @param b
	 * @return decrypted data
	 * @throws SypterSecurityException
	 */
	public INPUT decrypt(OUTPUT b) throws SypterSecurityException;

}
