package com.huangxifeng.gupiao.run;

import java.io.IOException;
import java.net.URL;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class RunHtmlUnit {

	private static WebClient webClient = null;
	private static HtmlPage htmlPage = null;

	public RunHtmlUnit() {
		webClient = new WebClient(BrowserVersion.CHROME);
		// 是否使用不安全的SSL
		webClient.getOptions().setUseInsecureSSL(true);
		// 启用JS解释器，默认为true
		webClient.getOptions().setJavaScriptEnabled(true);
		// 禁用CSS
		webClient.getOptions().setCssEnabled(false);
		// js运行错误时，是否抛出异常
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		// 状态码错误时，是否抛出异常
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		// 是否允许使用ActiveX
		webClient.getOptions().setActiveXNative(false);
		// 等待js时间
		webClient.waitForBackgroundJavaScript(600 * 1000);
		// 设置Ajax异步处理控制器即启用Ajax支持
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		// 设置超时时间
		webClient.getOptions().setTimeout(1000000);
		// 不跟踪抓取
		webClient.getOptions().setDoNotTrackEnabled(false);
	}

	private static RunHtmlUnit instance = null;

	public static RunHtmlUnit getInstance() {
		if (instance == null) {
			instance = new RunHtmlUnit();
		}
		return instance;
	}

	/**
	 * 根据URL获取HTML
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String getDocument(String url) throws Exception {
		WebRequest request = new WebRequest(new URL(url));
		request.setAdditionalHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
		try {

			htmlPage = webClient.getPage(request);

			webClient.waitForBackgroundJavaScript(3 * 1000);
			String xml = htmlPage.asXml();
			// System.out.println("HTML内容 = " + xml);
			return xml;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取下一页
	 * 
	 * @return
	 */
	public String nextPage(String nextStr) {
		try {
			HtmlAnchor htmlAnchor = htmlPage.getAnchorByText(nextStr);
			HtmlPage nextPage = htmlAnchor.click();
			webClient.waitForBackgroundJavaScript(10 * 1000);
			String str = nextPage.asXml();
			// System.out.println("下一页 HTML内容 = " + str);
			return str;
		} catch (Exception e) {
			// System.out.println("-[" + nextStr + "]-");
			e.printStackTrace();
			return null;
		}
	}

}
