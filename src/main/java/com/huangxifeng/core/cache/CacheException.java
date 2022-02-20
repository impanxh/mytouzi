/*
 * Copyright @2012 dnbiz.com. All rights reserved.
 */
package com.huangxifeng.core.cache;

/**
 *
 * @author JWinder.Huang (Jun 19, 2012)
 */
public class CacheException extends RuntimeException {
	
	private static final long serialVersionUID = 3514858390907908318L;

	public CacheException(String s) {
		super(s);
	}

	public CacheException(String s, Exception e) {
		super(s, e);
	}
	
	public CacheException(Exception e) {
		super(e);
	}
}

