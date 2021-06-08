package com.tazouxme.sypter.entity;

/**
 * Class that encapsulates encrypted data and digital signature
 * @author Agred.io
 * @since 1.0
 */
public class Syptered {
	
	/**
	 * Encrypted data 
	 */
	private String object;
	
	/**
	 * Digital signature
	 */
	private String signature;
	
	/**
	 * Construct a new default {@link Syptered} 
	 */
	public Syptered() {
		this("", "");
	}
	
	/**
	 * Construct a new {@link Syptered} containg encrypted data and digital signature
	 * @param object
	 * @param signature
	 */
	public Syptered(String object, String signature) {
		this.object = object;
		this.signature = signature;
	}
	
	/**
	 * Get encrypted data
	 * @return
	 */
	public String getObject() {
		return object;
	}
	
	/**
	 * Get digital signature
	 * @return
	 */
	public String getSignature() {
		return signature;
	}

}
