package com.tazouxme.sypter.signer;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.util.encoders.Base64;

import com.tazouxme.sypter.cert.CertificateManager;
import com.tazouxme.sypter.cert.KeyStoreManager;
import com.tazouxme.sypter.exception.SypterSecurityException;
import com.tazouxme.sypter.signer.exception.SignerSecurityException;

public class Signer implements ISigner {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private KeyStoreManager keyStoreManager;
	private CertificateManager certificateManager;
	
	public Signer(KeyStoreManager keyStoreManager, CertificateManager certificateManager) {
		this.keyStoreManager = keyStoreManager;
		this.certificateManager = certificateManager;
	}

	@Override
	public String sign(byte[] text) throws SypterSecurityException {
		return sign(text, keyStoreManager.getPrivateKey());
	}
	
	@Override
	public String sign(byte[] text, PrivateKey key) throws SypterSecurityException {
		try {
			Signature signature = Signature.getInstance(SignerConstants.SIGNATURE_ALGO_RSA);
			signature.initSign(key);
			signature.update(text);
			
			return new String(Base64.encode(signature.sign()));
		} catch (Exception e) {
			logger.error("Unable to sign", e);
			throw new SignerSecurityException("Unable to sign", e);
		}
	}
	
	@Override
	public boolean verify(byte[] text, byte[] digitalSignature) throws SypterSecurityException {
		return verify(text, digitalSignature, certificateManager.getPublicKey());
	}
	
	@Override
	public boolean verify(byte[] text, byte[] digitalSignature, PublicKey key) throws SypterSecurityException {
		try {
			Signature signature = Signature.getInstance(SignerConstants.SIGNATURE_ALGO_RSA);
			signature.initVerify(key);
			
			signature.update(text);
			return signature.verify(Base64.decode(digitalSignature));
		} catch (Exception e) {
			logger.error("Unable to verify signature", e);
			throw new SignerSecurityException("Unable to verify signature", e);
		}
	}

}
