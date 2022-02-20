package com.huangxifeng.core.utils;

import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @Title: Context.java
 * @author HuangFeng
 * @date Nov 15, 2016 5:33:36 PM
 */
public class HttpClientUtil {

	protected final Log log = LogFactory.getLog(this.getClass());

	private BasicCookieStore cookieStore = null;
	private CloseableHttpClient httpclient = null;

	public HttpClientUtil() {
		cookieStore = new BasicCookieStore();
		RequestConfig config = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
		HttpClientBuilder builder = HttpClients.custom();
		builder.setDefaultCookieStore(cookieStore);
		builder.setUserAgent(
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
		builder.setDefaultRequestConfig(config);
		httpclient = builder.build();
	}

	private static HttpClientUtil instance = null;

	public static HttpClientUtil getInstance() {
		if (instance == null) {
			instance = new HttpClientUtil();
		}
		return instance;
	}

	public CloseableHttpClient getHttpClient() {
		return httpclient;
	}

	@SneakyThrows
	public void closeHttpClient() {
		try {
			httpclient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BasicCookieStore getBasicCookieStore() {
		return cookieStore;
	}

	public void clearCookieStore() {
		cookieStore.clear();
	}

	public String doGet(String url) throws Exception {
		return this.doGet(url, null, null);
	}

	public String doGet(String url, String host) throws Exception {
		return this.doGet(url, null, host);
	}

	public String doGet(String url, String referer, String host) throws Exception {
		// log.info("========" + url + " 【开始 ========");
		CloseableHttpResponse response = null;

		try {
			HttpGet httpget = new HttpGet(url);

			response = httpclient.execute(httpget);

			if (ValidateUtil.isNotNull(host)) {
				httpget.addHeader("Host", host);
			}
			if (ValidateUtil.isNotNull(referer)) {
				httpget.addHeader("Referer", referer);
				// log.info("Referer:" + referer);
			}

			HttpEntity entity = response.getEntity();

			String body = EntityUtils.toString(entity, "UTF-8");

			// log.info("========" + url + " 【结束 ========");

			return body;

		} finally {
			if (null != response) {
				response.close();
			}
		}

	}

	public String doPost(String url, Map<String, String> params, String host) throws Exception {
		return this.doPost(url, params, null, host);
	}

	public String doPost(String url, Map<String, String> params, String referer, String host) throws Exception {

		log.info("========" + url + " 【开始 ========");
		CloseableHttpResponse response = null;

		try {

			RequestBuilder reqbuilder = RequestBuilder.post();
			reqbuilder.setUri(url);
			if (ValidateUtil.isNotEmpty(params)) {
				for (String key : params.keySet()) {
					reqbuilder.addParameter(key, params.get(key));
					log.info(key + ":" + params.get(key));
				}
			}

			HttpUriRequest httpreq = reqbuilder.build();

			if (ValidateUtil.isNotNull(referer)) {
				httpreq.addHeader("Host", host);
				httpreq.addHeader("Origin", "http://" + host);
				httpreq.addHeader("Referer", referer);
				log.info("Referer:" + referer);
			}

			response = httpclient.execute(httpreq);

			HttpEntity entity = response.getEntity();

			/*
			 * Header[] res_hds = response.getAllHeaders(); for (Header header :
			 * res_hds) { System.out.println(header.getName() + ">>>>>>" +
			 * header.getValue()); }
			 * 
			 * Header[] req_hds = httpreq.getAllHeaders(); for (Header header :
			 * req_hds) { System.out.println(header.getName() + ">>>>>>" +
			 * header.getValue()); }
			 */

			String body = EntityUtils.toString(entity);

			log.info("========" + url + " 【结束 ========");

			return body;

		} finally {
			if (null != response) {
				response.close();
			}
		}
	}
}
