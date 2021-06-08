package com.tazouxme.sypter.crypter.cipher;

import java.security.GeneralSecurityException;
import java.security.Security;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.tazouxme.sypter.crypter.exception.CrypterSecurityException;
import com.tazouxme.sypter.exception.SypterSecurityException;

public abstract class AbstractCipher<INPUT, OUTPUT> implements ICipher<INPUT, OUTPUT> {

	private Cipher cipher;
	
	protected AbstractCipher(String algo) throws GeneralSecurityException {
		Security.addProvider(new BouncyCastleProvider());
		
		this.cipher = Cipher.getInstance(algo);
	}
	
	@Override
	public OUTPUT encrypt(INPUT input) throws SypterSecurityException {
		if (cipher == null) {
			throw new CrypterSecurityException("'cipher' cannot be empty");
		}
		
		return encrypt(input, cipher);
	}
	
	protected abstract OUTPUT encrypt(INPUT input, Cipher cipher) throws SypterSecurityException;
	
	@Override
	public INPUT decrypt(OUTPUT input) throws SypterSecurityException {
		if (cipher == null) {
			throw new CrypterSecurityException("'cipher' cannot be empty");
		}
		
		return decrypt(input, cipher);
	}
	
	protected abstract INPUT decrypt(OUTPUT input, Cipher cipher) throws SypterSecurityException;

}
