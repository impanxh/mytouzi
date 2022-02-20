package com.huangxifeng.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.huangxifeng.core.utils.HttpClientUtil;

public class TestSpiderNews {

	public static void main(String[] args)
	{
		try {
			String body = HttpClientUtil.getInstance().doGet("https://www.miit.gov.cn/xwdt/szyw/index.html");
			System.out.println(body);
			body = HttpClientUtil.getInstance().doGet("https://www.miit.gov.cn/api-gateway/jpaas-publish-server/front/page/build/unit?parseType=buildstatic&webId=8d828e408d90447786ddbe128d495e9e&tplSetId=209741b2109044b5b7695700b2bec37e&pageType=column&tagId=%E5%8F%B3%E4%BE%A7%E5%86%85%E5%AE%B9&editType=null&pageId=6333578be1d646aabc3e0e79406688c9");
			System.out.println(body);
			Document document = Jsoup.parse(body);
			Element inputElements = document.getElementById("右侧内容");
			System.out.println(inputElements.html());
			Elements trs = inputElements.getElementsByTag("li");
			System.out.println(trs.get(0).text());
		
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
				
	}
}
