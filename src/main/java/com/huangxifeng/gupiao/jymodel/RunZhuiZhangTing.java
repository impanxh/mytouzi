package com.huangxifeng.gupiao.jymodel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.huangxifeng.core.utils.DoubleUtil;
import com.huangxifeng.core.utils.HttpClientUtil;
import com.huangxifeng.core.utils.StringPool;
import com.huangxifeng.core.utils.ValidateUtil;
import com.huangxifeng.gupiao.run.RunUtils;
import com.huangxifeng.gupiao.vo.ZhangTingVO;

public class RunZhuiZhangTing {

	public static void main(String[] args)
	{
		run();
	}
	
	private static String cids = "";
	
	private static Map<String, ZhangTingVO> map = new HashMap<String, ZhangTingVO>();

	public static Map<String, ZhangTingVO> getMap() {
		return map;
	}
	
	public static List<ZhangTingVO> getZtList()
	{
		List<ZhangTingVO> list = new ArrayList<ZhangTingVO>();
		
		Map<String, ZhangTingVO> map = RunZhuiZhangTing.getMap();
		for (String key : map.keySet())
		{
			ZhangTingVO ztvo = map.get(key);
			if(Double.valueOf(ztvo.getDqj()).compareTo(0.1) < 0)
			{
				//System.out.println(dxvo.toBaseString());
				continue;
			}
			
			list.add(ztvo);
		}
		
		return list;
	}

	private static boolean isrun = false;

	public static void stop()
	{
		System.out.println("-- 暂停 - 个股 采集跑数据 --");
		isrun = false;
	}
	
	public static boolean getIsRun() {
		return isrun;
	}

	public static void runZt() {
		
		try {
			
			String fileurl = StringPool.PROJECT_DIR + "\\data\\涨停列表.xlsx";

			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(fileurl));
			//int sheets = xssfWorkbook.getNumberOfSheets();

			StringBuffer s_cidsBuf = new StringBuffer();
			
			// 读取工作表
			XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

			// 获取最后一行的num，即总行数。此处从0开始计数
			int maxRow = sheet.getLastRowNum();
			System.out.println("总行数为：" + (maxRow + 1));

			for (int j = 1; j <= maxRow; j++)
			{
				ZhangTingVO vo = new ZhangTingVO();
				Row row = sheet.getRow(j);
				if(row.getLastCellNum() < 0)
				{
					continue;
				}
				vo.setCid(RunUtils.getCId(row.getCell(0).toString())); // Code
				vo.setName(row.getCell(1).toString());// 名称
				vo.setCate(row.getCell(2).toString());// 分类
				vo.setZtdays(Double.valueOf(row.getCell(3).toString()).intValue());
				s_cidsBuf.append(vo.getCid()).append(",");
				System.out.println(vo.toString());
				map.put(vo.getCid(), vo);
			}

			cids = s_cidsBuf.substring(0, s_cidsBuf.length() - 1);
			
			xssfWorkbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void run() {
		if (isrun == true) {
			return;
		}

		if (isrun == false) {
			isrun = true;
		}

		runZt();

		while (isrun) {

			System.out.println("---------------------隔2秒跑一次涨停个股-------------------------");

			try {

//				0: 未知
//				  1: 股票名字
//				  2: 股票代码
//				  3: 当前价格
//				  4: 昨收
//				  5: 今开
//				  6: 成交量（手）
//				  7: 外盘
//				  8: 内盘
//				  9: 买一
//				 10: 买一量（手）
//				 11-18: 买二 买五
//				 19: 卖一
//				 20: 卖一量
//				 21-28: 卖二 卖五
//				 29: 最近逐笔成交
//				 30: 时间
//				 31: 涨跌
//				 32: 涨跌%
//				 33: 最高
//				 34: 最低
//				 35: 价格/成交量（手）/成交额
//				 36: 成交量（手）
//				 37: 成交额（万）
//				 38: 换手率
//				 39: 市盈率
//				 40: 
//				 41: 最高
//				 42: 最低
//				 43: 振幅
//				 44: 流通市值
//				 45: 总市值
//				 46: 市净率
//				 47: 涨停价
//				 48: 跌停价
				 
				// 0: 未知
				// 1: 股票名称
				// 2: 股票代码
				// 3: 当前价格
				// 4: 涨跌
				// 5: 涨跌%
				// 6: 成交量（手）
				// 7: 成交额（万）
				// 8:
				// 9: 总市值
				String s_getbodys = HttpClientUtil.getInstance().doGet("http://qt.gtimg.cn/q=" + cids);

				//System.out.println("http://qt.gtimg.cn/q=" + cids);
				String[] s_gpstrs = s_getbodys.split(";");

				// 所有股票
				for (int i = 0; i < s_gpstrs.length; i++)
				{
					String sstr = s_gpstrs[i];
					//System.out.println(sstr);
					if (ValidateUtil.isNull(sstr))
					{
						continue;
					}
					
					String[] sspls = sstr.split("~");

					// String cname = sspls[1];
					String cid  = sspls[2];
					String dqjg = sspls[3]; //当前价格
					String zspj = sspls[4];  //昨收
					String jkpj = sspls[5];  //今开
					String zdf = sspls[32];  //涨跌%
					String zsz = sspls[9]; // 总市值

					ZhangTingVO dxvo = map.get(RunUtils.getCId(cid));
					dxvo.setZsz(zsz);
					dxvo.setZdf(zdf);
					dxvo.setDqj(Double.valueOf(dqjg));
					dxvo.setZtspj(Double.valueOf(zspj));
					dxvo.setJtkpj(Double.valueOf(jkpj));
					dxvo.setThisprice(dqjg);
					
					//开盘和昨天收盘涨跌比
					Double kpzdb = DoubleUtil.mul(DoubleUtil.div(DoubleUtil.sub(Double.valueOf(jkpj), Double.valueOf(zspj), 4) , Double.valueOf(zspj), 4) , 100.0 , 2);
					dxvo.setKpzdb(kpzdb);
					
					//开盘涨跌幅 赚亏比
					//System.out.println(dqjg + "-" + jkpj);
					if(Double.valueOf(jkpj).compareTo(0.0) > 0)
					{
						Double zkb = DoubleUtil.mul(DoubleUtil.div(DoubleUtil.sub(Double.valueOf(dqjg), Double.valueOf(jkpj)), Double.valueOf(jkpj), 4) , 100.0 , 2);
						dxvo.setZkb(zkb);
					}
					
					//System.out.println(dxvo.toBaseString() + "#" + zspj + "#" + jkpj + "#" + dqjg + "#" + zdf + "#" + kpzdb + "#" + dxvo.getZkb());
					
					map.put(dxvo.getCid(), dxvo);
				}

				Thread.sleep(2000);
			} catch (Exception e) {
				System.out.println("[" + cids + "]");
				e.printStackTrace();
			}
		}
	}
	
	// 新股均线多头
//	public static void getNewJunxianDuotou() {
//
//		System.out.println(
//				"股票ID#股票名称#分类#上市时间#上市天数#总市值(元)#流通市值(元)#市盈（TTM）#每股收益(元)#每股净资产(元)#营业总收入(元)#营业总收入同比增长(%)#净利润(元)#净利润同比增长(%)#上市时间#上市天数#最高价#最新收盘价#最低价#涨跌比例#量级#大涨天数#大跌天数");
//
//		List<GuPiaoCiXinVO> list = RunUtils.getNewList(false);
//		for (int i = 1; i < list.size(); i++) {
//			GuPiaoCiXinVO vo = list.get(i);
//			if (vo.getUpday() < 20) {
//				continue;
//			}
//			Double _5davp = RunUtils.getDayAvePrice(vo.getCid(), 5);
//			Double _10davp = RunUtils.getDayAvePrice(vo.getCid(), 10);
//			Double _20davp = RunUtils.getDayAvePrice(vo.getCid(), 20);
//			if (_5davp < _10davp) {
//				continue;
//			}
//			if (_10davp < _20davp) {
//				continue;
//			}
//
//			System.out.println(vo.toString());
//		}
//	}
//
//	// 15天内有2次涨停
//	// 最高价是一字或T字
//	public static void getDie15List() {
//
//		List<GuPiaoCiXinVO> list = RunUtils.getNewList(false);
//
//		System.out.println(
//				"股票ID#股票名称#分类#上市时间#上市天数#总市值(元)#流通市值(元)#市盈（TTM）#每股收益(元)#每股净资产(元)#营业总收入(元)#营业总收入同比增长(%)#净利润(元)#净利润同比增长(%)#上市时间#上市天数#最高价#最新收盘价#最低价#涨跌比例#量级#大涨天数#大跌天数");
//
//		for (int i = 1; i < list.size(); i++) {
//			GuPiaoCiXinVO vo = list.get(i);
//
//			try {
//				// System.out.println(vo.toString());
//				String gpmlist = RunUtils.getGpDayBody(vo.getCid());
//
//				String[] splist = gpmlist.split("\n");
//				String[] infos = splist[1].split(" ");
//
//				// 上市天数
//				int dnum = vo.getUpday();
//
//				// 上市时间
//				String startDate = vo.getUptime();
//
//				// 拿最多15天的交易数据
//				int num = splist.length - 3 > 25 ? 25 : splist.length - 3;
//				if (num < 4) {
//					// System.out.println(gpstr);
//					continue;
//				}
//
//				Double zgj = 0.0;// 最近最高价
//
//				// 今天最低价
//				String thisdayStr = splist[splist.length - 2].replace("\\n\\", "");
//				Double zdj = Double.valueOf(thisdayStr.split(" ")[4]); // 今天最低价
//				Double spj = Double.valueOf(thisdayStr.split(" ")[2]); // 今天收盘价
//
//				int ztnum = 0; // 涨停数量
//				int dtnum = 0; // 跌大于5%数量
//				long dayliangSum = 0; // 几天的量和
//				int dlnum = 0; // 几天的量数
//
//				// 15天内有2次涨停
//				for (int n = 1; n <= num; n++) {
//
//					String dayStr = splist[splist.length - 1 - n].replace("\\n\\", "");
//
//					// 时间，开盘，收盘，最高，最低，量
//					// 210325 3.10 3.07 3.12 3.04 569107
//					String[] spdayStr = dayStr.split(" ");
//					if (spdayStr.length != 6) {
//						System.out.println(dayStr);
//						continue;
//					}
//
//					// 当天收盘价
//					Double jtspj = Double.valueOf(spdayStr[2]);
//
//					int jtkpjInt = (int) (Double.valueOf(spdayStr[1]) * 100);
//					int jtspjInt = (int) (jtspj * 100);
//
//					// 昨天数据
//					String ztdayStr = splist[splist.length - 2 - n].replace("\\n\\", "");
//					// System.out.println("2:" + ztdayStr);
//					String[] ztspdayStr = ztdayStr.split(" ");
//					if (ztspdayStr.length != 6) {
//						continue;
//					}
//
//					// 昨天收盘价
//					Double ztspj = Double.valueOf(ztspdayStr[2]);
//
//					// 涨停算法 = （今天收盘价 - 昨天收盘价） / 昨天收盘价 > 9.8
//					if (DoubleUtil.div(DoubleUtil.sub(jtspj, ztspj), ztspj, 2) > 0.098 && jtkpjInt == jtspjInt) {
//						ztnum++;
//					}
//
//					// 跌5%算法 = （今天收盘价 - 昨天收盘价） / 昨天收盘价 > 5
//					if (DoubleUtil.div(DoubleUtil.sub(jtspj, ztspj), ztspj, 2) < -0.05) {
//						dtnum++;
//					}
//
//					// 最高价
//					if (DoubleUtil.sub(Double.valueOf(spdayStr[3]), zgj) > 0) {
//						zgj = Double.valueOf(spdayStr[3]);
//					}
//
//					// 当天价 x 当天量
//					long dayliang = (long) DoubleUtil.mul(Double.valueOf(spdayStr[2]),
//							Double.valueOf(Integer.valueOf(spdayStr[5]) * 100), 0);
//					dayliangSum += dayliang;
//					dlnum++;
//				}
//
//				int liang = (int) (dayliangSum / dlnum / 100000000);
//				// if( dayliangSum/dlnum <= 100000000)
//				// {
//				// continue;
//				// }
//
//				// 今天最低价/15天最高价 小于80% && 涨停大于2天
//				if (DoubleUtil.div(spj, zgj, 2) <= 0.85 && ztnum >= 1)
//
//				// if(ztnum >= 2)
//				{
//					System.out.println(vo.toString() + "#" + startDate + "#" + dnum + "#" + zgj + "#" + spj + "#" + zdj
//							+ "#" + DoubleUtil.div(spj, zgj, 2) + "#L" + liang + "#" + ztnum + "#" + dtnum);
//				}
//
//			} catch (Exception e) {
//				System.out.println("error:" + vo.toString());
//				e.printStackTrace();
//			}
//		}
//	}
}
