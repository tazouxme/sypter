package com.tazouxme.sypter.crypter;

import java.security.GeneralSecurityException;

import com.tazouxme.sypter.cert.CertificateManager;
import com.tazouxme.sypter.cert.KeyStoreManager;
import com.tazouxme.sypter.crypter.cipher.AsymmetricSypterCipher;
import com.tazouxme.sypter.crypter.cipher.SymmetricSypterCipher;
import com.tazouxme.sypter.crypter.exception.CrypterSecurityException;
import com.tazouxme.sypter.entity.AsymmetricSypterObject;
import com.tazouxme.sypter.entity.SymmetricSypterObject;
import com.tazouxme.sypter.exception.SypterSecurityException;

public class SypterCrypter extends AbstractCrypter<byte[], SymmetricSypterObject, AsymmetricSypterObject> {
	
	public SypterCrypter(KeyStoreManager keyStoreManager, CertificateManager certificateManager) throws GeneralSecurityException {
		super(new AsymmetricSypterCipher(keyStoreManager, certificateManager), new SymmetricSypterCipher());
	}
	
	@Override
	public AsymmetricSypterObject encrypt(byte[] b) throws SypterSecurityException {
		if (getAsymmetricCipher() == null || getSymmetricCipher() == null) {
			throw new CrypterSecurityException("'asymmetricCipher' and 'symmetricCipher' cannot be empty");
		}
		
		return getAsymmetricCipher().encrypt(getSymmetricCipher().encrypt(b));
	}
	
	@Override
	public byte[] decrypt(AsymmetricSypterObject b) throws SypterSecurityException {
		if (getAsymmetricCipher() == null || getSymmetricCipher() == null) {
			throw new CrypterSecurityException("'asymmetricCipher' and 'symmetricCipher' cannot be empty");
		}
		
		return getSymmetricCipher().decrypt(getAsymmetricCipher().decrypt(b));
	}

}
