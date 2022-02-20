package com.huangxifeng.jijin;

import org.apache.http.client.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.huangxifeng.core.utils.FileUtil;
import com.huangxifeng.core.utils.HttpClientUtil;

public class MainApp {

	private static HttpClient httpClient = HttpClientUtil.getInstance().getHttpClient();

	private static String cookie;
	private static String token;

	public static void main(String[] args) {
		try {
			String jjid = "040016";
			String body = HttpClientUtil.getInstance().doGet("https://www.howbuy.com/fund/" + jjid + "/",
					"www.howbuy.com");
			StringBuffer buf = new StringBuffer();
			for (int n = 1; n <= 150; n++) {
				body = HttpClientUtil.getInstance()
						.doGet("https://www.howbuy.com/fund/ajax/gmfund/history/huobi.htm?jjdm=" + jjid
								+ "&flag=1&page=" + n + "&perPage=10", "https://www.howbuy.com/fund/" + jjid + "/",
						"www.howbuy.com");
				Document doc = Jsoup.parse(body);
				// System.out.println(body);
				// 根据id获取table
				Elements table = doc.getElementsByTag("table");
				Elements trs = table.get(0).select("tr");
				for (int i = 0; i < trs.size(); i++) {
					Element tr = trs.get(i);
					Elements tds = tr.select("td");
					if (tds.get(0).text().contains("净值时间")) {
						continue;
					}
					String text = tds.get(0).text() + "," + tds.get(1).text() + "," + tds.get(2).text() + ","
							+ tds.get(3).text();
					buf.append(text).append("\n");
					System.out.println(text);
				}

				Thread.sleep(200);
			}

			FileUtil.writeFile("D:\\SVN\\feng\\投资\\基金" + jjid + ".txt", buf.toString(), "UTF-8");

			// System.out.println(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
