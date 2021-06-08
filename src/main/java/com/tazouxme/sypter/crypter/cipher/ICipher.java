package com.tazouxme.sypter.crypter.cipher;

import com.tazouxme.sypter.exception.SypterSecurityException;

public interface ICipher<INPUT, OUTPUT> {
	
	/**
	 * Encrypt data
	 * @param input
	 * @return encrypted data
	 * @throws SypterSecurityException
	 */
	public OUTPUT encrypt(INPUT input) throws SypterSecurityException;
	
	/**
	 * Decrypt data
	 * @param input
	 * @return decrypted data
	 * @throws SypterSecurityException
	 */
	public INPUT decrypt(OUTPUT input) throws SypterSecurityException;

}
