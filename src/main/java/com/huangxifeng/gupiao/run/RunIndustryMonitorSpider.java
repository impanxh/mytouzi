package com.huangxifeng.gupiao.run;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.huangxifeng.core.config.Config;
import com.huangxifeng.core.utils.DoubleUtil;
import com.huangxifeng.core.utils.FileUtil;
import com.huangxifeng.core.utils.HttpClientUtil;
import com.huangxifeng.core.utils.StringPool;
import com.huangxifeng.core.utils.ValidateUtil;
import com.huangxifeng.gupiao.vo.HangYeVO;

/**
 * 行业监控
 * 
 * @author admin
 *
 */
public class RunIndustryMonitorSpider {

	private static Map<String, HangYeVO> hymap = new HashMap<String, HangYeVO>();

	public static Map<String, HangYeVO> getHymap() {
		return hymap;
	}

	public static List<HangYeVO> getHyList()
	{
		List<HangYeVO> hylist = new ArrayList<HangYeVO>();
		for (String key : RunIndustryMonitorSpider.getHymap().keySet()) {
			HangYeVO vo = RunIndustryMonitorSpider.getHymap().get(key);
			hylist.add(vo);
		}
		return hylist;
	}
	
	/**
	 * 行业排序
	 * @param gplist
	 * @param px
	 */
	public static void sort(List<HangYeVO> hylist, String hpx)
	{
		// 行业排序
		if (hpx.equals("lr")) {
			Collections.sort(hylist, new Comparator<HangYeVO>() {
				@Override
				public int compare(HangYeVO o1, HangYeVO o2) {
					return ((int) (Double.valueOf(o2.getJlr()) * 100)) - ((int) (Double.valueOf(o1.getJlr()) * 100));
				}
			});
		} else if (hpx.equals("zdb")) {
			Collections.sort(hylist, new Comparator<HangYeVO>() {
				@Override
				public int compare(HangYeVO o1, HangYeVO o2) {
					return o2.getZdb() - o1.getZdb();
				}
			});
		} else {
			Collections.sort(hylist, new Comparator<HangYeVO>() {
				@Override
				public int compare(HangYeVO o1, HangYeVO o2) {
					return ((int) (Double.valueOf(o2.getZdf()) * 100)) - ((int) (Double.valueOf(o1.getZdf()) * 100));
				}
			});
		}
	}
	
	private static boolean isrun = false;


	public static void stop()
	{
		System.out.println("-- 暂停 - 行业采集跑数据 --");
		isrun = false;
	}
	
	public static boolean getIsRun() {
		return isrun;
	}
	
	public static void main(String[] args) {
		//run();
		//spiderHangYe();
		run();
	}

	
	public static void run()
	{
		if (isrun == true) {
			return;
		}

		if (isrun == false) {
			isrun = true;
		}
		
		initHangYe();
		
		while (isrun)
		{

			CloseableHttpClient httpClient = HttpClientUtil.getInstance().getHttpClient();

			CloseableHttpResponse response = null;

			try {
				
				HttpGet httpget = new HttpGet("http://q.10jqka.com.cn/thshy/");
				httpget.addHeader("Host", "q.10jqka.com.cn");
				httpget.addHeader("Referer", "http://www.10jqka.com.cn/");

				response = httpClient.execute(httpget);
				HttpEntity entity = response.getEntity();

				String body = EntityUtils.toString(entity, "UTF-8");

				// log.info("========" + url + " 【结束 ========");

				Document document = Jsoup.parse(body);
				Element inputElements = document.getElementById("maincont");
				// System.out.println(inputElements.html());
				Elements trs = inputElements.getElementsByTag("tr");

				Map<String, String> thismap = new HashMap<String, String>();
				
				for (Element trment : trs)
				{
					Elements tds = trment.children();
					String cname = tds.get(1).text();
					//System.out.println(cname);
					if (cname.equals("板块"))
					{
						continue;
					}
					
					HangYeVO vo = hymap.get(cname);
					if (null == vo)
					{
						vo = new HangYeVO();
					}
					String cid   = tds.get(01).html().replace("<a href=\"http://q.10jqka.com.cn/thshy/detail/code/", "").replace("/\" target=\"_blank\">" + tds.get(01).text() + "</a>", "");
					
					vo.setCid(cid);
					vo.setCname(cname);
					vo.setZdf(Double.valueOf(tds.get(02).text()));
					vo.setCjl(Double.valueOf(tds.get(04).text()));
					vo.setJlr(Double.valueOf(tds.get(05).text()));
					vo.setZhang(Integer.valueOf(tds.get(06).text()));
					vo.setDie(Integer.valueOf(tds.get(07).text()));
					vo.setZdb(Double.valueOf(DoubleUtil.div(Double.valueOf(vo.getZhang() + ""), Double.valueOf((vo.getZhang() + vo.getDie()) + ""), 2) * 100.00).intValue());
					hymap.put(vo.getCname(), vo);
					
					thismap.put(cname, cid);
				}

//				for (String key  : hymap.keySet())
//				{
//					if(thismap.containsKey(key))
//					{
//						continue;
//					}
//					
//					HangYeVO vo = spiderHangYeInfo(thismap.get(key));
//					hymap.put(vo.getCname(), vo);
//				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				//System.out.println("-- 行业采集 5 秒钟跑一次 --");
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void initHangYe()
	{
		
		String filename = Config.DATA_DIR + "/data/cate.txt";
		List<String> strlist = FileUtil.readToStringList(filename, StringPool.UTF_8);
		for (String hy : strlist)
		{
			HangYeVO hyvo = new HangYeVO();
			hyvo.setCid(hy.split("#")[0]);
			hyvo.setCname(hy.split("#")[1]);
			hymap.put(hyvo.getCname(), hyvo);
		}
		
		System.out.println("总行数为：" + strlist.size());
	}
	
	public static HangYeVO spiderHangYeInfo(String cid)
	{
		
		HangYeVO vo = new HangYeVO();
		CloseableHttpClient httpClient = HttpClientUtil.getInstance().getHttpClient();
		CloseableHttpResponse response = null;
		try {
			
			HttpGet httpget = new HttpGet("http://q.10jqka.com.cn/thshy/");
			httpget.addHeader("Host", "q.10jqka.com.cn");
			httpget.addHeader("Referer", "http://www.10jqka.com.cn/");

			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();

			String body = EntityUtils.toString(entity, "UTF-8");

			// log.info("========" + url + " 【结束 ========");

			Document document = Jsoup.parse(body);
			Element inputElements = document.getElementById("maincont");
			// System.out.println(inputElements.html());
			Elements trs = inputElements.getElementsByTag("tr");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public static void spiderHangYe()
	{
		try {
			
			// 分类
			String url = "http://q.10jqka.com.cn/thshy/";

			String html = RunHtmlUnit.getInstance().getDocument(url);
			Document document = Jsoup.parse(html);
			Element elements = document.getElementById("maincont");
			Elements trs = elements.getElementsByTag("tr");
			for (Element trment : trs)
			{
				Elements tds = trment.children();
				String cname = tds.get(1).text();
				if ("板块".equals(cname))
				{
					continue;
				}

				HangYeVO vo = hymap.get(cname);
				if (null == vo) 
				{
					vo = new HangYeVO();
				}
				
				String cid = tds.get(1).html().replace("<a href=\"http://q.10jqka.com.cn/thshy/detail/code/", "").replace("/\" target=\"_blank\">", "").replace("</a>", "").replace(cname, "").trim();
				
				vo.setCid(cid);
				vo.setCname(cname);
				vo.setZdf(Double.valueOf(tds.get(02).text()));
				vo.setCjl(Double.valueOf(tds.get(04).text()));
				vo.setJlr(Double.valueOf(tds.get(05).text()));
				vo.setZhang(Integer.valueOf(tds.get(06).text()));
				vo.setDie(Integer.valueOf(tds.get(07).text()));
				vo.setZdb(Double.valueOf(DoubleUtil.div(Double.valueOf(vo.getZhang() + ""),
						Double.valueOf((vo.getZhang() + vo.getDie()) + ""), 2) * 100.00).intValue());
				hymap.put(vo.getCname(), vo);
				
				System.out.println(cid + "#" + vo.getCname());
			}

			System.out.println("第【1】页采集完成");
			System.out.println("第【2】页采集开始");

			html = RunHtmlUnit.getInstance().nextPage("下一页");
			if (ValidateUtil.isNull(html))
			{
				return;
			}
			
			System.out.println(html);
			
			document = Jsoup.parse(html);
			elements = document.getElementById("maincont");
			trs = elements.getElementsByTag("tr");

			System.out.println("现采集【2】 页采集结果");
			for (Element trment : trs)
			{
				Elements tds = trment.children();

				String cname = tds.get(1).text();
				if ("板块".equals(cname))
				{
					continue;
				}

				HangYeVO vo = hymap.get(cname);
				if (null == vo) 
				{
					vo = new HangYeVO();
				}
				
				String cid = tds.get(1).html().replace("<a href=\"http://q.10jqka.com.cn/thshy/detail/code/", "").replace("/\" target=\"_blank\">", "").replace("</a>", "").replace(cname, "").trim();
				
				vo.setCid(cid);
				vo.setCname(cname);
				vo.setZdf(Double.valueOf(tds.get(02).text()));
				vo.setCjl(Double.valueOf(tds.get(04).text()));
				vo.setJlr(Double.valueOf(tds.get(05).text()));
				vo.setZhang(Integer.valueOf(tds.get(06).text()));
				vo.setDie(Integer.valueOf(tds.get(07).text()));
				vo.setZdb(Double.valueOf(DoubleUtil.div(Double.valueOf(vo.getZhang() + ""),
						Double.valueOf((vo.getZhang() + vo.getDie()) + ""), 2) * 100.00).intValue());
				hymap.put(vo.getCname(), vo);
				
				System.out.println(cid + "#" + vo.getCname());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
