package com.tazouxme.sypter.crypter.cipher;

import java.security.GeneralSecurityException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64;

import com.tazouxme.sypter.crypter.CryptoConstants;
import com.tazouxme.sypter.crypter.exception.CrypterSecurityException;
import com.tazouxme.sypter.entity.SymmetricSypterObject;
import com.tazouxme.sypter.exception.SypterSecurityException;
import com.tazouxme.sypter.generator.DefaultGenerator;
import com.tazouxme.sypter.generator.Generator;
import com.tazouxme.sypter.generator.entity.GeneratedValues;

public class SymmetricSypterCipher extends AbstractCipher<byte[], SymmetricSypterObject> {
	
	private Generator generator;

	public SymmetricSypterCipher() throws GeneralSecurityException {
		this(new DefaultGenerator());
	}

	public SymmetricSypterCipher(Generator generator) throws GeneralSecurityException {
		super(CryptoConstants.CIPHER_SYMMETRIC_ALGO_AES);
		this.generator = generator;
	}
	
	@Override
	protected SymmetricSypterObject encrypt(byte[] text, Cipher cipher) throws SypterSecurityException {
		try {
			GeneratedValues values = generator.generateValues();
			Key key = values.getKey();
			
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(values.getIv())); 
			String encryptedData = new String(Base64.encode(cipher.doFinal(text)));
			
			return new SymmetricSypterObject(encryptedData, new String(Base64.encode(key.getEncoded())), values.getEncodedIv());
		} catch (Exception e) {
			throw new CrypterSecurityException("Unable to encrypt", e);
		}
	}
	
	@Override
	protected byte[] decrypt(SymmetricSypterObject s, Cipher cipher) throws SypterSecurityException {
		try {
			Key key = new SecretKeySpec(Base64.decode(s.getSecretKey()), CryptoConstants.AES);
			IvParameterSpec ivSpec = new IvParameterSpec(Base64.decode(s.getIv()));
			
			cipher.init(Cipher.DECRYPT_MODE, key, ivSpec); 
			return cipher.doFinal(Base64.decode(s.getEncryptedData()));
		} catch (Exception e) {
			throw new CrypterSecurityException("Unable to decrypt", e);
		}
	}

}
