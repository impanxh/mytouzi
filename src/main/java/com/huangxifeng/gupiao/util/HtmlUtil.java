package com.huangxifeng.gupiao.util;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUtil {
	WebClient webClient;

	// 初始化
	public HtmlUtil() {
		webClient = new WebClient(BrowserVersion.CHROME);// 新建一个模拟谷歌Chrome浏览器的浏览器客户端对象
		webClient.getOptions().setThrowExceptionOnScriptError(false);// 当JS执行出错的时候是否抛出异常, 这里选择不需要
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);// 当HTTP的状态非200时是否抛出异常, 这里选择不需要
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setCssEnabled(false);// 是否启用CSS, 因为不需要展现页面, 所以不需要启用
		webClient.getOptions().setJavaScriptEnabled(true); // 很重要，启用JS。有些网站要开启！
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 很重要，设置支持AJAX
		webClient.getOptions().setTimeout(30000);
	}

	// 获取某个url的web客户端
	public String htmlUnitUrl(String url, WebClient webClient) {
		try {
			WebRequest request = new WebRequest(new URL(url), HttpMethod.GET);
			Map<String, String> additionalHeaders = new HashMap<String, String>();
			additionalHeaders.put("User-Agent",
					"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36");
			additionalHeaders.put("Accept-Language", "zh-CN,zh;q=0.8");
			additionalHeaders.put("Accept", "*/*");
			request.setAdditionalHeaders(additionalHeaders);
			// 获取某网站页面
			Page page = webClient.getPage(request);
			return page.getWebResponse().getContentAsString();
		} catch (Exception e) {

		}
		return null;
	}

	// 爬取某网页
	public String work(String openurl, String fetchDataUrl) {
		String content = "{}";
		try {
			HtmlPage page = webClient.getPage(openurl);// 打开网页
			// 当访问速度过快时，后台浏览器会禁止，在这里可加入适当延迟的代码
			/**
			 * 延迟执行的代码
			 */
			content = htmlUnitUrl(fetchDataUrl, webClient);
		} catch (IOException e) {
			e.printStackTrace();

		}
		return content;
	}

}