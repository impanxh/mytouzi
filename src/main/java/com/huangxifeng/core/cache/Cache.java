/*
 * Copyright @2012 dnbiz.com. All rights reserved.
 */
package com.huangxifeng.core.cache;

/**
 *
 * @author JWinder.Huang (Jun 19, 2012)
 */
public interface Cache {


	/**
	 * Get the name of the cache region
	 */
	public String getRegionName();
	
	/**
	 * Get an item from the cache, nontransactionally
	 * @param key
	 * @return the cached object or <tt>null</tt>
	 * @throws CacheException
	 */
	public Object get(Object key) throws CacheException;
	
	/**
	 * Add an item to the cache, nontransactionally, with
	 * failfast semantics
	 * @param key
	 * @param value
	 * @throws CacheException
	 */
	public void put(Object key, Object value) throws CacheException;

	/**
	 * Remove an item from the cache
	 */
	public void remove(Object key) throws CacheException;
	
	/**
	 * Clear the cache
	 */
	public void clear() throws CacheException;
}

