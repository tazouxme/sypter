package com.tazouxme.sypter.crypter.cipher;

import java.security.GeneralSecurityException;
import java.security.Key;

import javax.crypto.Cipher;

import org.bouncycastle.util.encoders.Base64;

import com.tazouxme.sypter.cert.CertificateManager;
import com.tazouxme.sypter.cert.KeyStoreManager;
import com.tazouxme.sypter.crypter.CryptoConstants;
import com.tazouxme.sypter.crypter.exception.CrypterSecurityException;
import com.tazouxme.sypter.entity.AsymmetricSypterObject;
import com.tazouxme.sypter.entity.SymmetricSypterObject;
import com.tazouxme.sypter.exception.SypterSecurityException;

public class AsymmetricSypterCipher extends AbstractCipher<SymmetricSypterObject, AsymmetricSypterObject> {
	
	private KeyStoreManager keyStoreManager;
	private CertificateManager certificateManager;
	
	public AsymmetricSypterCipher(KeyStoreManager keyStoreManager, CertificateManager certificateManager) throws GeneralSecurityException {
		super(CryptoConstants.CIPHER_ASYMMETRIC_ALGO_RSA);
		
		this.keyStoreManager = keyStoreManager;
		this.certificateManager = certificateManager;
	}

	@Override
	protected AsymmetricSypterObject encrypt(SymmetricSypterObject s, Cipher cipher) throws SypterSecurityException {
		if (certificateManager == null) {
			throw new CrypterSecurityException("'certificateManager' cannot be empty");
		}
		
		try {
			String encryptedSecretKey = encrypt(s.getSecretKey().getBytes(), cipher, certificateManager.getPublicKey());
			String encryptedIv = encrypt(s.getIv().getBytes(), cipher, certificateManager.getPublicKey());
			
			return new AsymmetricSypterObject(s.getEncryptedData(), encryptedSecretKey, encryptedIv);
		} catch (Exception e) {
			throw new CrypterSecurityException("Unable to encrypt", e);
		}
	}
	
	@Override
	protected SymmetricSypterObject decrypt(AsymmetricSypterObject input, Cipher cipher) throws SypterSecurityException {
		if (keyStoreManager == null) {
			throw new CrypterSecurityException("'keyStoreManager' cannot be empty");
		}
		
		try {
			String secretKey = new String(decrypt(input.getEncryptedSecretKey(), cipher, keyStoreManager.getPrivateKey()));
			String iv = new String(decrypt(input.getEncryptedIv(), cipher, keyStoreManager.getPrivateKey()));
			
			return new SymmetricSypterObject(input.getEncryptedData(), secretKey, iv);
		} catch (Exception e) {
			throw new CrypterSecurityException("Unable to decrypt", e);
		} 
	}
	
	private String encrypt(byte[] b, Cipher cipher, Key key) throws SypterSecurityException {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key, CryptoConstants.CRYPTER_ASYMMETRIC_OAEP_SPEC);
			return new String(Base64.encode(cipher.doFinal(b)));
		} catch (Exception e) {
			throw new CrypterSecurityException("Unable to encrypt", e);
		} 
	}
	
	private byte[] decrypt(String text, Cipher cipher, Key key) throws SypterSecurityException {
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, CryptoConstants.CRYPTER_ASYMMETRIC_OAEP_SPEC); 
			return cipher.doFinal(Base64.decode(text));
		} catch (Exception e) {
			throw new CrypterSecurityException("Unable to decrypt", e);
		} 
	}

}
