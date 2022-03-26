package com.huangxifeng.gupiao.jymodel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.huangxifeng.core.config.Config;
import com.huangxifeng.core.utils.DoubleUtil;
import com.huangxifeng.core.utils.FileUtil;
import com.huangxifeng.core.utils.HttpClientUtil;
import com.huangxifeng.core.utils.StringPool;
import com.huangxifeng.core.utils.ValidateUtil;
import com.huangxifeng.gupiao.run.RunIndustryMonitorSpider;
import com.huangxifeng.gupiao.run.RunUtils;
import com.huangxifeng.gupiao.vo.ZhangTingDiXiVO;

public class RunZhangTingDiXi {

	public static void main(String[] args) {
		// runTiaoKongList();
				// runData();
				// run2Data();
				runZtDx();
				
//				try {
//					
//					String file = StringPool.PROJECT_DIR + "\\data\\dxlist\\dxgp.txt";
		//
//					List<String> list = FileUtil.readToStringList(file, StringPool.UTF_8);
		//
//					System.out.println("编号#名称#分类#标识#15天板#昨封板#连板#操作#15d成交均量#赚亏比#均线值#昨天涨跌#评分");
//					
//					for (String str : list)
//					{
//						ZhangTingDiXiVO vo = new ZhangTingDiXiVO();
//						String[] vostrs = str.split("#");
//						
//						if(vostrs[0].equals("编号"))
//						{
//							continue;
//						}
//						
//						vo.setCid(vostrs[0]); // Code
//						vo.setName(vostrs[1]);// 名称
//						vo.setCate(vostrs[2]);// 分类
//						vo.setBsstr(vostrs[3]); //标识
//						vo.setBan15d(Integer.valueOf(vostrs[4])); //15天涨停
//						vo.setFbstr(vostrs[5]); //昨封板
//						vo.setLbstr(Integer.valueOf(vostrs[6])); // 连板
//						vo.setCzstr(vostrs[7]); // 操作
//						vo.setLiang15d(vostrs[8] + "亿");
//						vo.setCmstr(vostrs[9]);
//						vo.setJxz(vostrs[10]);
//						vo.setZtzd(vostrs[11]);
//						vo.setScore(Integer.valueOf(vostrs[12]));
//						vo.setScore(score(vo));
//						
//						//开盘涨跌幅
//						GuPiaoInfoVO info = RunUtils.getGuPiaoInfo(vo.getCid());
//						
//						System.out.println(vo.toString() + "#" + info.getKpzdf());
//					}
		//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
	}
	
	private static String cids = "";
	
	private static Map<String, ZhangTingDiXiVO> map = new HashMap<String, ZhangTingDiXiVO>();

	public static Map<String, ZhangTingDiXiVO> getMap() {
		return map;
	}
	
	public static int score(ZhangTingDiXiVO vo)
	{
		StringBuffer sf = new StringBuffer("");
		
		// 连板  * 6分
		// 5日内涨停 * 4分
		// 15日内除开5日内涨停 * 3分
		// 15日内跌停 - 4分
		// 5均线上  + 2分
		// 昨涨   + 2分
		
		int lbfen = vo.getLbstr() * 6; 
		int d5fen = 0;
		int d15fen = 0;
		int dt10fen = 0;
		int jx5fen = 0;
		int zzfen = 0;
		
		int score = 0;
		if(vo.getLbstr() > 0)
		{
			sf.append(vo.getLbstr() + "连板 x 6：" + vo.getLbstr() * 6 + "分。");
		}
		
		int d5zt = RunUtils.getZhangTingDays(vo.getCid(), 5);
		if(d5zt > vo.getLbstr())
		{
			d5fen = (d5zt - vo.getLbstr()) * 4;
			
			sf.append("5天内除连板  x 4：" + (d5zt - vo.getLbstr()) * 4 + "分。");
			d15fen = (vo.getBan15d() - d5zt) * 3;
			
			sf.append("15天内除5天板  x 3：" + (vo.getBan15d() - d5zt) * 3 + "分。");
		} else {
			d15fen = (vo.getBan15d() - vo.getLbstr()) * 3; 
			sf.append("15天内除连板  x 3：" + ((vo.getBan15d() - vo.getLbstr()) * 3) + "分。");
		}
		
		dt10fen = vo.getDt10d() * 4;
		if(vo.getDt10d() > 0)
		{
			sf.append("跌停-4：-" + (vo.getDt10d() * 4) + "分。");
		}
		
		if(vo.getJxz().equals("5均线上"))
		{
			jx5fen = 2;
			sf.append("5均线上 + 2：" + 2 + "分。");
		}
		
		if(vo.getZtzd().equals("昨涨"))
		{
			zzfen = 2;
			sf.append("昨涨 + 2：" + 2 + "分。");
		}
		
		score = lbfen + d5fen + d15fen - dt10fen + jx5fen + zzfen;
		
		//封板资金大于1亿加分项
		if(vo.getFbstr().contains("亿"))
		{
			Double fbzj = Double.valueOf(vo.getFbstr().split("亿")[0]);
			if(fbzj.compareTo(5.0) > 0)
			{
				score += 4;
			}
			else if (fbzj.compareTo(3.0) > 0)
			{
				score += 3;
			}
			else if (fbzj.compareTo(1.0) > 0)
			{
				score += 2;
			}
		}
		
		sf.append("总分：" + score);
		
		System.out.println(vo.getName() + "#" + sf);
		
		return score;
	}
	
	public static List<ZhangTingDiXiVO> getZtdxList()
	{
		List<ZhangTingDiXiVO> gplist = new ArrayList<ZhangTingDiXiVO>();
		
		Map<String, ZhangTingDiXiVO> map = RunZhangTingDiXi.getMap();
		for (String key : map.keySet())
		{
			ZhangTingDiXiVO dxvo = map.get(key);
			
			if(Double.valueOf(dxvo.getDqj()).compareTo(0.1) < 0)
			{
				//System.out.println(dxvo.toBaseString());
				continue;
			}
			
			if(ValidateUtil.isNotEmpty(RunIndustryMonitorSpider.getHymap()) && RunIndustryMonitorSpider.getHymap().containsKey(dxvo.getCate()))
			{
				dxvo.setHyzd(RunIndustryMonitorSpider.getHymap().get(dxvo.getCate()).getZdf());
				dxvo.setHyid(RunIndustryMonitorSpider.getHymap().get(dxvo.getCate()).getCid());
			}
			
			gplist.add(dxvo);
		}
		
		return gplist;
	}
	
	/**
	 * 个股排序
	 * @param gplist
	 * @param px
	 */
	public static void sort(List<ZhangTingDiXiVO> gplist, String px)
	{
		// 个股排序
		if (px.equals("sort")) {
			Collections.sort(gplist, new Comparator<ZhangTingDiXiVO>() {
				@Override
				public int compare(ZhangTingDiXiVO o1, ZhangTingDiXiVO o2) {
					return o1.getSort() - o2.getSort();
				}
			});
		} else if (px.equals("cate")) {
			Collections.sort(gplist, new Comparator<ZhangTingDiXiVO>() {
				@Override
				public int compare(ZhangTingDiXiVO o1, ZhangTingDiXiVO o2) {
					//return (int)(DoubleUtil.mul(o2.getHyzd() , 100.0 , 0) - DoubleUtil.mul(o1.getHyzd() , 100.0 , 0));
					return o2.getCate().hashCode() - o1.getCate().hashCode();
				}
			});
		} else if (px.equals("15d")) {
			Collections.sort(gplist, new Comparator<ZhangTingDiXiVO>() {
				@Override
				public int compare(ZhangTingDiXiVO o1, ZhangTingDiXiVO o2) {
					return o2.getBan15d() - o1.getBan15d();
				}
			});
		} else if (px.equals("zkb")) {
			Collections.sort(gplist, new Comparator<ZhangTingDiXiVO>() {
				@Override
				public int compare(ZhangTingDiXiVO o1, ZhangTingDiXiVO o2) {
					return (int)(DoubleUtil.mul(o2.getZkb() , 100.0 , 0) - DoubleUtil.mul(o1.getZkb() , 100.0 , 0));
				}
			});	
			
		} else {
			Collections.sort(gplist, new Comparator<ZhangTingDiXiVO>() {
				@Override
				public int compare(ZhangTingDiXiVO o1, ZhangTingDiXiVO o2) {
					return o1.getSort() - o2.getSort();
				}
			});
		}
	}
	

	private static boolean isrun = false;

	public static void stop()
	{
		System.out.println("-- 暂停 - 自选股采集跑数据 --");
		isrun = false;
	}
	
	public static boolean getIsRun() {
		return isrun;
	}

	public static void runZtDx()
	{
		
		try {
			
			boolean flag = false;
			StringBuffer s_cidsBuf = new StringBuffer();
			
			System.out.println("=========== 读取 自选低吸 列表  ===========");
			
			if(flag)
			{
				String fileurl = Config.DATA_DIR + "/data/超短低吸.xlsx";

				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(fileurl));
				//int sheets = xssfWorkbook.getNumberOfSheets();
				
				// 读取工作表
				XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

				// 获取最后一行的num，即总行数。此处从0开始计数
				int maxRow = sheet.getLastRowNum();
				System.out.println("总行数为：" + (maxRow + 1));

				for (int j = 1; j <= maxRow; j++)
				{
					ZhangTingDiXiVO vo = new ZhangTingDiXiVO();
					Row row = sheet.getRow(j);
					if(row.getLastCellNum() < 0)
					{
						continue;
					}
					vo.setCid(RunUtils.getCId(row.getCell(0).toString())); // Code
					vo.setName(row.getCell(1).toString());// 名称
					vo.setCate(row.getCell(2).toString());// 分类
					vo.setBsstr(row.getCell(3).toString()); //标识
					vo.setLbstr(Double.valueOf(row.getCell(4).toString()).intValue()); // 连板
					vo.setBan15d(Double.valueOf(row.getCell(5).toString()).intValue()); //15天涨停
					vo.setFbstr(row.getCell(6).toString()); //昨封板
					vo.setCmstr(row.getCell(7).toString());
					vo.setLiang15d(row.getCell(8).toString() + "亿");
					vo.setJxz(row.getCell(9).toString());
					vo.setZtzd(row.getCell(10).toString());
					vo.setScore(Double.valueOf(row.getCell(11).toString()).intValue());
					vo.setCzstr(row.getCell(12).toString()); // 操作
					vo.setSort(j+1);
					s_cidsBuf.append(vo.getCid()).append(",");
					System.out.println(vo.toString());
					map.put(vo.getCid(), vo);
				}
				xssfWorkbook.close();
				
			} else {
				
				String fileurl = Config.DATA_DIR + "/data/run/dxgp.txt";
				List<String> list = FileUtil.readToStringList(fileurl, StringPool.UTF_8);
				System.out.println("总行数为：" + list.size());
				
				for (int i = 0; i < list.size(); i++)
				{
					String txt = list.get(i);
					if(txt.contains("编号"))
					{
						continue;
					}
					
					String[] sps = txt.split("#");
					
					ZhangTingDiXiVO vo = new ZhangTingDiXiVO();
					vo.setCid(sps[0]); // Code
					vo.setName(sps[1]);// 名称
					vo.setCate(sps[2]);// 分类
					vo.setBsstr(sps[3]); //标识
					vo.setLbstr(Integer.valueOf(sps[4])); // 连板
					vo.setBan15d(Integer.valueOf(sps[5])); //15天涨停
					vo.setFbstr(sps[6]); //昨封板
					vo.setCmstr(sps[7]);
					vo.setLiang15d(sps[8] + "亿");
					vo.setJxz(sps[9]);
					vo.setZtzd(sps[10]);
					vo.setScore(Integer.valueOf(sps[11]));
					vo.setCzstr(sps[12]); // 操作
					vo.setSort(i+1);
					s_cidsBuf.append(vo.getCid()).append(",");
					System.out.println(vo.toString());
					map.put(vo.getCid(), vo);
				}
			}

			cids = s_cidsBuf.substring(0, s_cidsBuf.length() - 1);
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

		runZtDx();

		while (isrun) {

			System.out.println("---------------------隔1秒跑一次自选股-------------------------");

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

					ZhangTingDiXiVO dxvo = map.get(RunUtils.getCId(cid));
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
					
					map.put(dxvo.getCid(), dxvo);
				}

				
			} catch (Exception e) {
				System.out.println("[" + cids + "]");
				e.printStackTrace();
				
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
}
