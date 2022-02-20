/*
 * Copyright @2010 Net365. All rights reserved.
 */
package com.huangxifeng.core.utils;

import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author:HuangFeng(2010-8-25)
 */
public class StringUtil extends StringUtils {

	public static String firstUpperCase(String name) {
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}
}
