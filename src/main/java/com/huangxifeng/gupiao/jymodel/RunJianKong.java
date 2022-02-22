package com.huangxifeng.gupiao.jymodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huangxifeng.core.utils.DoubleUtil;
import com.huangxifeng.core.utils.FileUtil;
import com.huangxifeng.core.utils.HttpClientUtil;
import com.huangxifeng.core.utils.StringPool;
import com.huangxifeng.core.utils.ValidateUtil;
import com.huangxifeng.gupiao.run.RunUtils;
import com.huangxifeng.gupiao.vo.JianKongVO;

public class RunJianKong
{
	public static void main(String[] args)
	{
		run();
	}
	
	private static String cids = "";
	
	private static Map<String, List<JianKongVO>> listmap = new HashMap<String, List<JianKongVO>>();
	
	private static Map<String, JianKongVO> map = new HashMap<String, JianKongVO>();
	
	private static Map<String, String> ztmap = new HashMap<String, String>();
	private static Map<String, String> dtmap = new HashMap<String, String>();
	private static Map<String, String> gbmap = new HashMap<String, String>();
	private static Map<String, String> hymap = new HashMap<String, String>();

	public static Map<String, JianKongVO> getMap()
	{
		return map;
	}
	
	public static List<JianKongVO> getList(String type)
	{
		return listmap.get(type);
	}
	
	/**
	 * 个股排序
	 * @param gplist
	 * @param px
	 */
	public static void sort(List<JianKongVO> gplist, String px)
	{
		if(null == gplist)
		{
			return ;
		}
		
		// 个股排序
		if (px.equals("cate")) {
			Collections.sort(gplist, new Comparator<JianKongVO>() {
				@Override
				public int compare(JianKongVO o1, JianKongVO o2) {
					return Integer.valueOf(RunUtils.getCateId(o2.getCate())) - Integer.valueOf(RunUtils.getCateId(o1.getCate()));
				}
			});
		} else if (px.equals("15d")) {
			Collections.sort(gplist, new Comparator<JianKongVO>() {
				@Override
				public int compare(JianKongVO o1, JianKongVO o2) {
					return o2.getBan15d().hashCode() - o1.getBan15d().hashCode();
				}
			});
		} else if (px.equals("zkb")) {
			Collections.sort(gplist, new Comparator<JianKongVO>() {
				@Override
				public int compare(JianKongVO o1, JianKongVO o2) {
					return (int)(DoubleUtil.mul(o2.getZkb() , 100.0 , 0) - DoubleUtil.mul(o1.getZkb() , 100.0 , 0));
				}
			});	
		}
	}

	private static boolean isrun = false;

	public static void stop()
	{
		System.out.println("-- 暂停 - 监控个股 采集跑数据 --");
		isrun = false;
	}
	
	public static boolean getIsRun()
	{
		return isrun;
	}

	public static void init()
	{
		
		try {
			
			//跌停列表
			String dtfileurl = StringPool.PROJECT_DIR + "/data/run/dtgp.txt";
			List<String> list = FileUtil.readToStringList(dtfileurl, StringPool.UTF_8);
			System.out.println("=========== 读取跌停列表  ===========");
			for (int i = 0; i < list.size(); i++)
			{
				String txt = list.get(i);
				if(txt.contains("编号"))
				{
					continue;
				}
				//System.out.println(txt);
				JianKongVO vo = new JianKongVO();
				vo.valueOf(txt);
				vo.setType(JianKongVO.Type.DT_LIST);
				dtmap.put(vo.getCid(), vo.getCid());
				map.put(vo.getCid(), vo);
			}
			System.out.println("一共读取 " + list.size() + " 条数据");
			
			//涨停列表
			String ztfileurl = StringPool.PROJECT_DIR + "/data/run/ztgp.txt";
			list = FileUtil.readToStringList(ztfileurl, StringPool.UTF_8);
			System.out.println("=========== 读取涨停列表  ===========");
			for (int i = 0; i < list.size(); i++)
			{
				String txt = list.get(i);
				if(txt.contains("编号"))
				{
					continue;
				}
				//System.out.println(txt);
				JianKongVO vo = new JianKongVO();
				vo.valueOf(txt);
				vo.setType(JianKongVO.Type.ZT_LIST);
				ztmap.put(vo.getCid(), vo.getCid());
				map.put(vo.getCid(), vo);
			}
			System.out.println("一共读取 " + list.size() + " 条数据");
			
			//高标列表
			String gbfileurl = StringPool.PROJECT_DIR + "/data/run/gbgp.txt";
			list = FileUtil.readToStringList(gbfileurl, StringPool.UTF_8);
			System.out.println("=========== 读取高标列表  ===========");
			for (int i = 0; i < list.size(); i++)
			{
				String txt = list.get(i);
				if(txt.contains("编号"))
				{
					continue;
				}
				//System.out.println(txt);
				JianKongVO vo = new JianKongVO();
				vo.valueOf(txt);
				vo.setType(JianKongVO.Type.GB_LIST);
				gbmap.put(vo.getCid(), vo.getCid());
				map.put(vo.getCid(), vo);
			}
			System.out.println("一共读取 " + list.size() + " 条数据");
			
			//行业列表
			String hyfileurl = StringPool.PROJECT_DIR + "/data/run/hygp.txt";
			list = FileUtil.readToStringList(hyfileurl, StringPool.UTF_8);
			System.out.println("=========== 读取行业列表  ===========");
			for (int i = 0; i < list.size(); i++)
			{
				String txt = list.get(i);
				if(txt.contains("编号"))
				{
					continue;
				}
				//System.out.println(txt);
				JianKongVO vo = new JianKongVO();
				vo.valueOf(txt);
				vo.setType(JianKongVO.Type.HY_LIST);
				hymap.put(vo.getCid(), vo.getCid());
				map.put(vo.getCid(), vo);
			}
			System.out.println("一共读取 " + list.size() + " 条数据");
			
			StringBuffer s_cidsBuf = new StringBuffer();
			for (String key : map.keySet())
			{
				//System.out.println(key);
				s_cidsBuf.append(key).append(",");
			}
			cids = s_cidsBuf.substring(0, s_cidsBuf.length() - 1);

			System.out.println("去重总 " + map.size() + " 条数据");
			//System.out.println("去重总 " + cids.split(",").length + " 条数据");
			
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

		init();

		while (isrun) {

			System.out.println("---------------------隔1秒跑一次监控个股-------------------------");

			try {

//				  0: 未知
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
				String s_getbodys = HttpClientUtil.getInstance().doGet("http://qt.gtimg.cn/q=" + cids);

				//System.out.println("http://qt.gtimg.cn/q=" + cids);
				String[] s_gpstrs = s_getbodys.split(";");

				//System.out.println(s_gpstrs.length);
				
				List<JianKongVO> ztlist = new ArrayList<JianKongVO>();
				List<JianKongVO> dtlist = new ArrayList<JianKongVO>();
				List<JianKongVO> gblist = new ArrayList<JianKongVO>();
				List<JianKongVO> hylist = new ArrayList<JianKongVO>();
				
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

					String cid  = sspls[2];
					
					Double ztspj = Double.valueOf(sspls[4]); // 昨天收盘价
					Double jtkpj = Double.valueOf(sspls[5]); // 今天开盘价
					Double jtzdj = Double.valueOf(sspls[34]); // 今天最低价
					Double jtzgj = Double.valueOf(sspls[33]); // 今天最高价
					Double dqj   = Double.valueOf(sspls[3]); // 今天当前价
					Double zdf   = Double.valueOf(sspls[32]); // 当前涨跌幅
					Double jtspj = Double.valueOf(sspls[3]); // 今天收盘价

					JianKongVO dxvo = map.get(RunUtils.getCId(cid));
					dxvo.setZtspj(ztspj);
					dxvo.setJtkpj(jtkpj);
					dxvo.setJtzdj(jtzdj);
					dxvo.setJtzgj(jtzgj);
					dxvo.setDqj(dqj);
					dxvo.setZdf(zdf);
					dxvo.setJtspj(jtspj);
					
					//// 今天开盘涨跌幅 开盘价 - 昨收盘价 / 昨收盘价
					Double kpzdf = DoubleUtil.mul(DoubleUtil.div(DoubleUtil.sub(Double.valueOf(jtkpj), Double.valueOf(ztspj), 4) , Double.valueOf(ztspj), 4) , 100.0 , 2);
					dxvo.setKpzdf(kpzdf);
					
					//开盘涨跌幅 赚亏比
					//System.out.println(dqjg + "-" + jkpj);
					if(jtkpj.compareTo(0.0) > 0)
					{
						Double zkb = DoubleUtil.mul(DoubleUtil.div(DoubleUtil.sub(Double.valueOf(dqj), Double.valueOf(jtkpj)), Double.valueOf(jtkpj), 4) , 100.0 , 2);
						dxvo.setZkb(zkb);
					}
					
					map.put(dxvo.getCid(), dxvo);
					
					if(ztmap.containsKey(dxvo.getCid()))
					{
						ztlist.add(dxvo);
					}
					
					if(dtmap.containsKey(dxvo.getCid()))
					{
						dtlist.add(dxvo);
					}
					
					if(gbmap.containsKey(dxvo.getCid()))
					{
						gblist.add(dxvo);
					}
					
					if(hymap.containsKey(dxvo.getCid()))
					{
						hylist.add(dxvo);
					}
				}
				
				listmap.put(JianKongVO.Type.DT_LIST, dtlist);
				listmap.put(JianKongVO.Type.ZT_LIST, ztlist);
				listmap.put(JianKongVO.Type.GB_LIST, gblist);
				listmap.put(JianKongVO.Type.HY_LIST, hylist);

				Thread.sleep(1000);
				
			} catch (Exception e) {
				System.out.println("[" + cids + "]");
				e.printStackTrace();
			}
		}
	}
}
