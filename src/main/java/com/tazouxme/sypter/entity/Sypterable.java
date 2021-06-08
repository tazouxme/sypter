package com.tazouxme.sypter.entity;

import java.io.Serializable;

/**
 * Abstract default class that is used for sypter process. <b>Id parameter is mandatory.</b>
 * @author Agred.io
 * @since 1.0
 */
public interface Sypterable extends Serializable {
	
	/**
	 * Get the Id
	 * @return
	 */
	public String getId();

}
