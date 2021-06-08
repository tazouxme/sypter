package com.tazouxme.sypter.crypter;

import java.security.GeneralSecurityException;

import com.tazouxme.sypter.crypter.cipher.ICipher;

public abstract class AbstractCrypter<INPUT, INTER, OUTPUT> implements ICrypter<INPUT, OUTPUT> {
	
	private ICipher<INTER, OUTPUT> asymmetricCipher;
	private ICipher<INPUT, INTER> symmetricCipher;
	
	public AbstractCrypter(ICipher<INTER, OUTPUT> asymmetricCipher, ICipher<INPUT, INTER> symmetricCipher) throws GeneralSecurityException {
		this.asymmetricCipher = asymmetricCipher;
		this.symmetricCipher = symmetricCipher;
	}
	
	public ICipher<INTER, OUTPUT> getAsymmetricCipher() {
		return asymmetricCipher;
	}
	
	public ICipher<INPUT, INTER> getSymmetricCipher() {
		return symmetricCipher;
	}

}
