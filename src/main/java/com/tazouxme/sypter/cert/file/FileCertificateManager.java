package com.tazouxme.sypter.cert.file;

import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.tazouxme.sypter.cert.CertificateManager;
import com.tazouxme.sypter.cert.exception.CertificateSecurityException;
import com.tazouxme.sypter.exception.SypterSecurityException;

/**
 * Manager to explore and find {@link PublicKey} from a Certificate using its path
 * @author Agred.io
 * @since 1.0
 */
public class FileCertificateManager implements CertificateManager {

	/**
	 * Default Spring ResourceLoader to retrieve Certificate
	 */
	private ResourceLoader resourceLoader = new DefaultResourceLoader();
	
	/**
	 * Path where the Certificate is located
	 */
	private String certificatePath;
	
	/**
	 * Construct a new {@link FileCertificateManager}
	 * @param certificatePath
	 */
	public FileCertificateManager(String certificatePath) {
		this.certificatePath = certificatePath;
	}

	/**
	 * Retrieve the {@link PublicKey} from the Certificate
	 * @return Found {@link PublicKey}
	 * @throws ResourceReflectionException
	 */
	@Override
	public PublicKey getPublicKey() throws SypterSecurityException {
		return getPublicKey(certificatePath);
	}

	/**
	 * Retrieve the {@link PublicKey} from the Certificate
	 * @return Found {@link PublicKey}
	 * @throws ResourceReflectionException
	 */
	protected PublicKey getPublicKey(String path) throws SypterSecurityException {
		try {
			Resource resource = resourceLoader.getResource(path);
			return extractFromCertificate(resource.getInputStream()).getPublicKey();
		} catch (Exception e) {
			throw new CertificateSecurityException("Unable to get the PublicKey", e);
		}
	}
	
	/**
	 * Extract the {@link X509Certificate} using the path of the certificate
	 * @return Found {@link X509Certificate}
	 * @throws ResourceReflectionException
	 */
	private X509Certificate extractFromCertificate(InputStream stream) throws SypterSecurityException {
		try {
			CertificateFactory f = CertificateFactory.getInstance("X.509");
			return (X509Certificate) f.generateCertificate(stream);
		} catch (Exception e) {
			throw new CertificateSecurityException("Unable to extract from the Certificate", e);
		}
	}

}
