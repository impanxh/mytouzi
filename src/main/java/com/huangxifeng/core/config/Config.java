package com.huangxifeng.core.config;

import java.util.ResourceBundle;

public class Config {

	private static ResourceBundle config = ResourceBundle.getBundle("config/config");
	
	public static final String DATA_DIR = Config.getString("data.dir");
	
	
	/**
	 * config.preprotis文件key=value信息
	 * @param key
	 * @return value
	 *
	 * @author JWinder.Huang (Jun 20, 2012)
	 */
	public static String getString(String key)
	{
		if(null == config)
		{
			return null;
		}

		return config.getString(key);
	}

	public static int getInteger(String key)
	{
		if(null == config)
		{
			return 0;
		}
		return Integer.valueOf(config.getString(key));
	}

	public static void reload()
	{
		config = ResourceBundle.getBundle("config/config");
	}
	
	public static void main(String[] args) {
		System.out.println(DATA_DIR);
	}
}
