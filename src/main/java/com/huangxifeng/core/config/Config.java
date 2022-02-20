package com.huangxifeng.core.config;

import java.util.ResourceBundle;

public class Config {

	private static ResourceBundle config = ResourceBundle.getBundle("config/config");

	/** 当前系统环境，开发环境还是生产环境， 开发环境：development，生产环境：production */
	public static final String SYSTEM_ENVIRONMENT = Config.getString("system.environment");
	
	/** 正式环境为 true，测试环境为false */
	public static final boolean IS_SYSTEM_ENVIRONMENT_PRO()
	{
		if("development".equals(SYSTEM_ENVIRONMENT))
		{
			return false;
		}
		
		if("production".equals(SYSTEM_ENVIRONMENT))
		{
			return true;
		}
		
		return false;
	}
	
	
	/** API URL */
	public static final String API_URL = Config.getString("api.url");
	
	/** API name */
	public static final String API_NAME = Config.getString("api.name");
	
	/** API验证的secret */
	public static final String API_SECRET = Config.getString("api.secret");

	/** 微信小程序APPID */
	public static final String WX_API_APPID = Config.getString("wx.api.appid");
	
	/** 微信小程序SECRET */
	public static final String WX_API_SECRET = Config.getString("wx.api.secret");
	
	/** 微信小程序支付接口 商户ID */
	public static final String WX_API_PAY_MCHID = Config.getString("wx.api.pay.mchid");
	
	/** 微信支付API安全KEY */
	public static final String WX_API_PAY_KEY = Config.getString("wx.api.pay.key");
	
	/** 微信支付API回调URL */
	public static final String WX_API_PAY_NOTIFY_URL = Config.getString("wx.api.pay.notifyurl");
	
	/** 微信支付IP白名单 */
	public static final String WX_API_PAY_EFFECTIVE_IP =  Config.getString("wx.api.pay.effective.ip");
	
	/** 微信支付API 证书地址 */
	public static final String WX_API_PAY_APICLIENT_CERT = Config.getString("wx.api.pay.apiclient.cert.dir");
	
	
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
		Config.getString("api.name");
	}
}
