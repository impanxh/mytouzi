package com.huangxifeng.jijin;

import com.huangxifeng.core.utils.DoubleUtil;
import com.huangxifeng.core.utils.PoiParser;
import com.huangxifeng.jijin.vo.JiJinVo;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BuyJijin {

	static String year = "201920年整";
	static int bjxs = 1;

	public static void main(String[] args) {
		try {

			// List<String> jzlist =
			// FileUtil.readToStringList("D:\\SVN\\feng\\投资\\基金202023.txt",
			// "UTF-8");
			// List<JiJinVo> list = new ArrayList<JiJinVo>();
			// for (int row = 0; row < jzlist.size(); row++)
			// {
			// String rowstr = jzlist.get(row);
			// String[] rows = rowstr.split(",");
			//
			// JiJinVo vo = new JiJinVo();
			// vo.setTime(rows[0]);
			// vo.setUnitzhi(Double.valueOf(rows[2]));
			// vo.setZhangzhi(PoiParser.getRealStringValueOfDouble(rows[3]));
			//
			// list.add(vo);
			// }

			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("D:\\SVN\\feng\\投资\\基金202023.xlsx"));
			System.out.println("xssfWorkbook对象：" + xssfWorkbook);

			// 读取第一个工作表
			XSSFSheet sheet = xssfWorkbook.getSheetAt(6);
			System.out.println("sheet对象：" + sheet);

			// 获取最后一行的num，即总行数。此处从0开始计数
			int maxRow = sheet.getLastRowNum();
			System.out.println("总行数为：" + maxRow);

			List<JiJinVo> list = new ArrayList<JiJinVo>();
			for (int row = maxRow; row >= 0; row--) {
				JiJinVo vo = new JiJinVo();
				Cell cell0 = sheet.getRow(row).getCell(0);
				if (HSSFDateUtil.isCellDateFormatted(cell0)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					vo.setTime(sdf.format(HSSFDateUtil.getJavaDate(cell0.getNumericCellValue())).toString());
				}

				Cell cell1 = sheet.getRow(row).getCell(2);
				vo.setUnitzhi(cell1.getNumericCellValue());

				Cell cell3 = sheet.getRow(row).getCell(3);
				vo.setZhangzhi(PoiParser.getCellValue(cell3));

				list.add(vo);
			}

			gddingtou(list);

			////////////////////////////////////////////////////////////////////////////////////////
			diedingtou(list);

			////////////////////////////////////////////////////////////////////////////////////////

			die_1_dingtou(list);

			////////////////////////////////////////////////////////////////////////////////////////

			die_135_dingtou(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void gddingtou(List<JiJinVo> list) {
		// 定投30000元三年后
		double bj = 19000.00 * bjxs;
		double gnum = DoubleUtil.div(bj, list.get(0).getUnitzhi(), 2);

		JiJinVo vo = null;
		for (int i = 1; i < list.size(); i++) {
			vo = list.get(i);
			vo.setNumber(gnum);
			double money = DoubleUtil.mul(gnum, vo.getUnitzhi(), 2);
			vo.setMoney(money);
			// System.out.println(vo.toString());
		}

		System.out.println(vo.toString());
		String rs = "赎回总额(" + vo.getMoney() + ") - 本金(" + bj + ") / 本金(" + bj + ") = " + year + "年回报比("
				+ (DoubleUtil.div(DoubleUtil.sub(vo.getMoney(), bj), bj, 4) * 100) + "%)";
		System.out.println("一次性本金买入（" + bj + "）投资" + year + "年最后结果->" + rs);
		System.out.println();
	}

	private static void diedingtou(List<JiJinVo> list) {
		// 跌就跟投
		JiJinVo vo = null;
		double bj = 10000.00 * bjxs; // 本金
		double gnum = DoubleUtil.div(bj, list.get(0).getUnitzhi(), 2); // 第一次购买份额
		double dtmoney = 30.00 * bjxs; // 定投金额
		double tr = 0.0; // 定投对应份额累加

		for (int i = 1; i < list.size(); i++) {
			vo = list.get(i);

			// 如果跌了就定投100元
			if (vo.getZhangzhi().startsWith("-")) {
				// 100元除以当前估值确认份额
				double trnum = DoubleUtil.div(dtmoney, vo.getUnitzhi(), 2);
				gnum = DoubleUtil.sum(gnum, trnum);
				tr = DoubleUtil.sum(tr, dtmoney);
			}
			vo.setAddmoney(tr);
			vo.setNumber(gnum);
			double money = DoubleUtil.mul(gnum, vo.getUnitzhi(), 2);
			vo.setMoney(money);
			// System.out.println(vo.toString());
		}

		System.out.println(vo.toString());
		String rs = "赎回总额(" + vo.getMoney() + ") - 本金(" + (bj + vo.getAddmoney()) + ") / 本金(" + (bj + vo.getAddmoney())
				+ ") = " + year + "年回报比("
				+ (DoubleUtil.div(DoubleUtil.sub(vo.getMoney(), (bj + vo.getAddmoney())), (bj + vo.getAddmoney()), 4)
						* 100)
				+ "%)";
		System.out.println("一次性本金买入（" + bj + "）后期跌就定投（" + dtmoney + "） 投资" + year + "年最后结果->" + rs);
		System.out.println();

		// 3年定投
		// 24573.09-20470/20470 = 0.20 年化6.88%
	}

	private static void die_1_dingtou(List<JiJinVo> list) {
		// 跌1%才定投
		JiJinVo vo = null;
		double bj = 10000.00 * bjxs; // 本金
		double gnum = DoubleUtil.div(bj, list.get(0).getUnitzhi(), 2); // 第一次购买份额
		double dtmoney = 100.00 * bjxs; // 定投金额
		double tr = 0.0; // 定投对应份额累加
		for (int i = 1; i < list.size(); i++) {
			vo = list.get(i);

			// 如果跌了就定投100元
			if (vo.getZhangzhi().startsWith("-")) {
				String zzstr = vo.getZhangzhi().replace("-", "");
				BigDecimal a = new BigDecimal(zzstr);
				BigDecimal b = new BigDecimal("0.01");

				// 跌幅超过1%才定投
				if (a.compareTo(b) == 1 || a.compareTo(b) == 0) {
					// System.out.println(vo.getZhangzhi());
					// 100元除以当前估值确认份额
					double trnum = DoubleUtil.div(dtmoney, vo.getUnitzhi(), 2);
					gnum = DoubleUtil.sum(gnum, trnum);
					tr = DoubleUtil.sum(tr, dtmoney);
				}
			}
			vo.setAddmoney(tr);
			vo.setNumber(gnum);
			double money = DoubleUtil.mul(gnum, vo.getUnitzhi(), 2);
			vo.setMoney(money);
			// System.out.println(vo.toString());
		}

		System.out.println(vo.toString());
		String rs = "赎回总额(" + vo.getMoney() + ") - 本金(" + (bj + vo.getAddmoney()) + ") / 本金(" + (bj + vo.getAddmoney())
				+ ") = " + year + "年回报比("
				+ (DoubleUtil.div(DoubleUtil.sub(vo.getMoney(), (bj + vo.getAddmoney())), (bj + vo.getAddmoney()), 4)
						* 100)
				+ "%)";
		System.out.println("一次性本金买入（" + bj + "）后期跌1%就定投（" + dtmoney + "） 投资" + year + "年最后结果->" + rs);
		System.out.println();

		// 3年定投
		// 22859.94-18800/18800 = 0.216 年化7%
	}

	private static void die_135_dingtou(List<JiJinVo> list) {
		// 跌1%才定投
		JiJinVo vo = null;
		double bj = 10000.00 * bjxs; // 本金
		double gnum = DoubleUtil.div(bj, list.get(0).getUnitzhi(), 2); // 第一次购买份额
		double dtmoney = 100.00 * bjxs; // 定投金额
		double tmoney = 300.00 * bjxs;// 定投金额
		double smoney = 500.00 * bjxs;// 定投金额
		double tr = 0.0; // 定投对应份额累加
		for (int i = 1; i < list.size(); i++) {
			vo = list.get(i);

			// 如果跌了就定投100元
			if (vo.getZhangzhi().startsWith("-")) {
				String zzstr = vo.getZhangzhi().replace("-", "");
				BigDecimal a = new BigDecimal(zzstr);
				BigDecimal b = new BigDecimal("0.01");
				BigDecimal c = new BigDecimal("0.02");
				BigDecimal d = new BigDecimal("0.03");

				// 跌幅超过1% 小于 2% 才定投
				if ((a.compareTo(b) == 1 || a.compareTo(b) == 0) && a.compareTo(c) == -1) {
					// System.out.println("跌幅超过1% 小于 2% 才定投:" +
					// vo.getZhangzhi());
					// 100元除以当前估值确认份额
					double trnum = DoubleUtil.div(dtmoney, vo.getUnitzhi(), 2);
					gnum = DoubleUtil.sum(gnum, trnum);
					tr = DoubleUtil.sum(tr, dtmoney);
				}

				// 跌幅超过2% 小于 3%才定投
				if ((a.compareTo(c) == 1 || a.compareTo(c) == 0) && a.compareTo(d) == -1) {
					// System.out.println("跌幅超过2% 小于 3%才定投:" +
					// vo.getZhangzhi());
					// 100元除以当前估值确认份额
					double trnum = DoubleUtil.div(tmoney, vo.getUnitzhi(), 2);
					gnum = DoubleUtil.sum(gnum, trnum);
					tr = DoubleUtil.sum(tr, tmoney);
				}

				// 跌幅超过3% 定投
				if (a.compareTo(d) == 1 || a.compareTo(d) == 0) {
					// System.out.println("跌幅超过3% 定投:" + vo.getZhangzhi());
					// 100元除以当前估值确认份额
					double trnum = DoubleUtil.div(smoney, vo.getUnitzhi(), 2);
					gnum = DoubleUtil.sum(gnum, trnum);
					tr = DoubleUtil.sum(tr, smoney);
				}
			}
			vo.setAddmoney(tr);
			vo.setNumber(gnum);
			double money = DoubleUtil.mul(gnum, vo.getUnitzhi(), 2);
			vo.setMoney(money);
			// System.out.println(vo.toString());
		}

		System.out.println(vo.toString());
		String rs = "赎回总额(" + vo.getMoney() + ") - 本金(" + (bj + vo.getAddmoney()) + ") / 本金(" + (bj + vo.getAddmoney())
				+ ") = " + year + "年回报比("
				+ (DoubleUtil.div(DoubleUtil.sub(vo.getMoney(), (bj + vo.getAddmoney())), (bj + vo.getAddmoney()), 4)
						* 100)
				+ "%)";
		System.out.println("一次性本金买入（" + bj + "）后期跌1%投（" + dtmoney + "） ，2%投（" + tmoney + "） ，3%以上投（" + smoney + "） ， 投资"
				+ year + "年最后结果->" + rs);
		System.out.println();

		// 3年定投
		// 22859.94-18800/18800 = 0.216 年化7%
	}
}
