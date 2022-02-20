package com.huangxifeng.core.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huangxifeng.core.utils.ValidateUtil;

/**
 * 内存缓存
 * @author HuangFeng (2014-12-1)
 */
public class MemCache
{
	static Map<Object, Object> cache = new HashMap<Object , Object>();

	public static void add(Object key , Object value)
	{
		cache.put(key, value);
	}
	
	public static void remove(Object key)
	{
		cache.remove(key);
	}
	
	public static Object get(Object key)
	{
		return cache.get(key);
	}
	
	public static Map<Object, Object> getCache()
	{
		return cache;
	}
	
	public static void setCache(Map<Object, Object> cache)
	{
		MemCache.cache = cache;
	}
	
	public static void addCache(Map<Object, Object> cache)
	{
		for(Object key : cache.keySet())
		{
			MemCache.add(key, cache.get(key));
		}
	}
	
	public static boolean isExist(String key)
	{
		Object obj = get(key);
		if(ValidateUtil.isNull(obj))
		{
			return false;
		}
		
		if(obj instanceof java.util.List)
		{
			if(ValidateUtil.isEmpty((List<?>)obj))
			{
				return false;
			}
		}
		
		if(obj instanceof java.util.Map)
		{
			if(ValidateUtil.isEmpty((Map<?,?>)obj))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static void clear()
	{
		cache.clear();
	}
}

