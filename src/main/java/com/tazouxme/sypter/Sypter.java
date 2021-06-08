package com.tazouxme.sypter;

import java.security.GeneralSecurityException;

import org.apache.commons.lang3.StringUtils;

import com.tazouxme.sypter.cert.CertificateManager;
import com.tazouxme.sypter.cert.KeyStoreManager;
import com.tazouxme.sypter.entity.Sypterable;

/**
 * Main class to create a {@link Sypter} object
 * @author Tazou
 * @since 1.0
 */
public class Sypter<T extends Sypterable> extends AbstractSypter<T> {

	public Sypter(KeyStoreManager keyStoreManager, CertificateManager certificateManager) throws GeneralSecurityException {
		super(keyStoreManager, certificateManager);
	}

	@Override
	protected boolean isEntityValid(T obj) {
		return obj != null && !StringUtils.isEmpty(obj.getId());
	}

	@Override
	protected String getId(T obj) {
		return obj.getId();
	}

}
