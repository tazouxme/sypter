package com.tazouxme.sypter.crypter;

import java.security.spec.MGF1ParameterSpec;

import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

public class CryptoConstants {
	
	public static final String AES = "AES";
	public static final String PBKDF2 = "PBKDF2WithHmacSHA256";
	public static final String RSA = "RSA";
	
	public static final String CIPHER_SYMMETRIC_ALGO_AES = "AES/CBC/PKCS7PADDING";
	public static final String CIPHER_ASYMMETRIC_ALGO_RSA = "RSA/ECB/OAEPPadding";
	
	public static final OAEPParameterSpec CRYPTER_ASYMMETRIC_OAEP_SPEC = 
			new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);

}
