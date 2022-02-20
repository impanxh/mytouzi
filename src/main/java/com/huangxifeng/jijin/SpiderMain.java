package com.huangxifeng.jijin;

import com.huangxifeng.core.utils.FileUtil;
import com.huangxifeng.core.utils.HttpClientUtil;
import com.huangxifeng.jijin.vo.JiJinVo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class SpiderMain {

	public static void main(String[] args) {

	}

	public static void getJjTopGupiao() {
		try {

			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("D:\\SVN\\feng\\投资\\alljj-指数及其他.xlsx"));
			// 读取第一个工作表
			XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

			// 获取最后一行的num，即总行数。此处从0开始计数
			int maxRow = sheet.getLastRowNum();
			System.out.println("总行数为：" + maxRow);

			List<JiJinVo> list = new ArrayList<JiJinVo>();
			for (int row = 0; row <= maxRow; row++) {
				JiJinVo vo = new JiJinVo();
				Cell cell1 = sheet.getRow(row).getCell(1);
				if (cell1.getCellType().equals(CellType.NUMERIC)) {
					vo.setId(String.valueOf(cell1.getNumericCellValue()).replace(".0", ""));
					System.out.println(vo.getId());
				}
				list.add(vo);
			}

			int num = 0;
			StringBuffer all = new StringBuffer();
			for (int j = 0; j < list.size(); j++) {
				num++;
				System.out.println("第个" + j + "基金");
				JiJinVo vo = list.get(j);
				try {
					String body = HttpClientUtil.getInstance()
							.doGet("http://fund.eastmoney.com/" + vo.getId() + ".html", "fund.eastmoney.com");
					Document doc = Jsoup.parse(body);
					System.out.println(body);
					// 根据id获取table
					Element table = doc.getElementById("position_shares");

					// 使用选择器选择该table内所有的<tr> <tr/>
					// 遍历该表格内的所有的<tr> <tr/>
					Elements trs = table.select("tr");

					StringBuffer gpstrs = new StringBuffer();
					for (int i = 0; i < trs.size(); ++i) {
						Element tr = trs.get(i);
						Elements tds = tr.select("td");
						if (tds.size() > 0) {
							Element td = tds.get(0);
							gpstrs.append(td.text()).append(",");
							Elements as = td.select("a");
							if (null != as && as.size() > 0) {
								gpstrs.append(as.get(0).attr("href").replace("http://quote.eastmoney.com/sh", "")
										.replace(".html", "").replace("http://quote.eastmoney.com/sz", "")
										.replace("http://quote.eastmoney.com/hk/", ""));
							}
							gpstrs.append("#");
						}
					}

					System.out.println(gpstrs);
					vo.setGupiaos(gpstrs.toString());
					all.append(vo.jijinAndGupiaoToString()).append("\n");

					Thread.sleep(500);

				} catch (Exception e) {
					e.printStackTrace();
				}

				// if(num % 500 == 0)
				// {
				// FileUtil.writeFile("D:\\SVN\\feng\\投资\\alljj-混合型.txt",
				// all.toString(), "UTF-8");
				// all = new StringBuffer();
				// }
			}

			FileUtil.writeFile("D:\\SVN\\feng\\投资\\alljj-指数及其他.txt", all.toString(), "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
