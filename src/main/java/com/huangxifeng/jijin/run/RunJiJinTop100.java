package com.huangxifeng.jijin.run;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.huangxifeng.core.utils.HttpClientUtil;

public class RunJiJinTop100 {

	public static void main(String[] args) throws Exception {

		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("E:\\feng\\投资\\基金\\2021Top100.xlsx"));
		// 读取第一个工作表
		XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

		// 获取最后一行的num，即总行数。此处从0开始计数
		int maxRow = sheet.getLastRowNum();
		System.out.println("总行数为：" + maxRow);

		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int row = 1; row <= maxRow; row++) {
			try {
				Cell cell1 = sheet.getRow(row).getCell(1);
				String id = cell1.getStringCellValue();

				String body = HttpClientUtil.getInstance().doGet("http://fund.eastmoney.com/" + id + ".html",
						"fund.eastmoney.com");
				Document doc = Jsoup.parse(body);
				// System.out.println(body);
				// 根据id获取table
				Element table = doc.getElementById("position_shares");
				// 使用选择器选择该table内所有的<tr> <tr/>
				// 遍历该表格内的所有的<tr> <tr/>
				Elements trs = table.select("tr");

				for (int i = 0; i < trs.size(); ++i) {
					Element tr = trs.get(i);
					Elements tds = tr.select("td");
					if (tds.size() > 0) {
						Element td = tds.get(0);
						String name = td.text();
						String code = "";
						Elements as = td.select("a");
						if (null != as && as.size() > 0) {
							code = as.get(0).attr("href").replace("http://quote.eastmoney.com/sh", "")
									.replace(".html", "").replace("http://quote.eastmoney.com/sz", "")
									.replace("http://quote.eastmoney.com/hk/", "");
						}

						Integer num = 1;

						if (map.containsKey(name + "," + code)) {
							num = map.get(name + "," + code);
							num++;
						}

						map.put(name + "," + code, num);
						System.out.println(name + "," + code);
					}
				}

				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (String key : map.keySet()) {
			System.out.println(key + "=" + map.get(key));
		}
	}
}
