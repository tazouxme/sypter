package com.tazouxme.sypter.signer;

import java.security.PrivateKey;
import java.security.PublicKey;

import com.tazouxme.sypter.exception.SypterSecurityException;

public interface ISigner {
	
	/**
	 * Sign data
	 * @param text
	 * @return Signature
	 * @throws SypterSecurityException
	 */
	public String sign(byte[] text) throws SypterSecurityException;
	
	/**
	 * Sign data using a specific {@link PrivateKey}
	 * @param text
	 * @param key
	 * @return Signature
	 * @throws SypterSecurityException
	 */
	public String sign(byte[] text, PrivateKey key) throws SypterSecurityException;
	
	/**
	 * Verify the Signature
	 * @param text
	 * @param digitalSignature
	 * @return Whether the Signature is verified
	 * @throws SypterSecurityException
	 */
	public boolean verify(byte[] text, byte[] digitalSignature) throws SypterSecurityException;
	
	/**
	 * Verify the Signature using a specific {@link PublicKey}
	 * @param text
	 * @param digitalSignature
	 * @param key
	 * @return Whether the Signature is verified
	 * @throws SypterSecurityException
	 */
	public boolean verify(byte[] text, byte[] digitalSignature, PublicKey key) throws SypterSecurityException;

}
