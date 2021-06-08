package com.tazouxme.sypter;

import java.util.function.Function;

import com.tazouxme.sypter.entity.Syptered;
import com.tazouxme.sypter.exception.SypterSecurityException;

public interface ISypter<T> {
	
	public Syptered sypt(T entity) throws SypterSecurityException;
	
	public <W extends T> T unsypt(Syptered syptered, Function<String, Class<W>> determinator) throws SypterSecurityException;

}
