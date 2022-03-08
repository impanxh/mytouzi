package com.huangxifeng.gupiao.run;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huangxifeng.core.config.Config;
import com.huangxifeng.core.utils.DateUtil;
import com.huangxifeng.core.utils.DoubleUtil;
import com.huangxifeng.core.utils.FileUtil;
import com.huangxifeng.core.utils.HttpClientUtil;
import com.huangxifeng.core.utils.StringPool;
import com.huangxifeng.core.utils.ValidateUtil;
import com.huangxifeng.gupiao.vo.CaiWuVO;
import com.huangxifeng.gupiao.vo.GuPiaoBaseVO;
import com.huangxifeng.gupiao.vo.GuPiaoCiXinVO;
import com.huangxifeng.gupiao.vo.GuPiaoInfoVO;
import com.huangxifeng.gupiao.vo.HangYeVO;
import com.huangxifeng.gupiao.vo.JiBenMianVO;
import com.huangxifeng.gupiao.vo.ZhangDieBiVO;

public class RunUtils {

	final static String TYPE_1YI = "TYPE_1YI";
	final static String TYPE_GN = "GN";
	final static String TYPE_CATE = "CATE";

	final static Map<String, String> cateMap = new HashMap<String, String>();

	final static Map<String, String> cmap = new HashMap<String, String>();
	
	public static void main(String[] args)
	{
		//countBcje(9500.0, 500.0);
		
		// buildAllCateFile(); //生成分类
		// getAllCateFile(); //生成分类
		// buildAllGPCateFile(); //获取分类下的股票
		// buildGPCateFile(TYPE_GN, "308760#绿色电力");
		// buildGPCateFile(TYPE_GN, "301079#光伏概念");
		// buildGPCateFile(TYPE_GN, "300200#风电");
		// buildGPCateFile(TYPE_GN, "300733#锂电池");
		// buildGPCateFile(TYPE_GN, "300353#特高压");
		// buildGPCateFile(TYPE_GN, "308700#第三代半导体");
		// buildGPCateFile(TYPE_GN, "308712#医美");
		// buildGPCateFile(TYPE_GN, "300008#新能源车");
		// buildGPCateFile(TYPE_GN, "306380#储能");
		// buildGPCateFile(TYPE_CATE, "881166#国防军工");
		// buildGPCateFile(TYPE_CATE, "881126#汽车零部件");
		// buildGPCateFile(TYPE_CATE, "881121#半导体及元件");
		 //buildGPCateFile(TYPE_CATE, "881181#环保");
		// buildGPCateFile(TYPE_CATE, "881174#厨卫电器");

		// buildGPCateFile(TYPE_CATE, "881141#中药");
		// buildGPCateFile(TYPE_CATE, "881148#港口航运");

		// buildGPCateFile(TYPE_CATE, "881149#公路铁路运输");
		// buildGPCateFile(TYPE_CATE, "881145#电力");
		// buildGPCateFile(TYPE_CATE, "881170#小金属");
		// buildGPCateFile(TYPE_CATE, "881127#非汽车交运");
		// buildGPCateFile(TYPE_CATE, "881155#银行");
		// buildGPCateFile(TYPE_CATE, "881175#医疗服务");
		// buildGPCateFile(TYPE_CATE, "881144#医疗器械");
		// buildGPCateFile(TYPE_CATE, "881153#房地产开发");
		// buildGPCateFile(TYPE_CATE, "881112#钢铁");
		// buildGPCateFile(TYPE_CATE, "881103#农产品加工");
		// buildGPCateFile(TYPE_CATE, "881136#服装家纺");

		 //mergeAllGP();
		 //buildAllGPFile();//生成所有股票

		// buildZZ500();

		//这个每天晚上18点后执行这个，把每个个股的数据采集下来，不然都是昨天的
		//每天晚上18点后就执行这个就好
		downloadDayFile();

		//这里就5个列表，都要跑
		RunDatas.main(null);
		
		//这个类都是操作股票的工具
				
		//downloadMonthFile();

		get1YiList(true);
		// getSuoLiangList(true);

		// getNewList(true);
		//getZhangTingList();
		
		//getDieTingList();
		
		// getJiBenMianString("sz000723");
		// JiBenMianVO vo = getJiBenMianString("sh600438");
		// System.out.println(vo.getJzcsyl());

		// getJiJinCount("SH601919");

		// getZhangTingDays("sh603982", 100);
		
		//System.out.println(getDieTingDays("sz002349", 15));

//		System.out.println("伟明环保#SH603568");
//		ZhangDieBiVO zdbvo = getZdb("SH603568", 90);
//		zdbvo.p();
//
//		System.out.println("中国核电#SH601985");
//		zdbvo = getZdb("SH601985", 90);
//		zdbvo.p();
//
//		System.out.println("");
//
//		System.out.println("中国电建#SH601669");
//		zdbvo = getZdb("SH601669", 90);
//		zdbvo.p();
//
//		System.out.println("");
//
//		System.out.println("上海建工#SH600170");
//		zdbvo = getZdb("SH600170", 90);
//		zdbvo.p();
//
//		System.out.println("");
//
//		System.out.println("大唐发电#SH601991");
//		zdbvo = getZdb("SH601991", 90);
//		zdbvo.p();
//
//		System.out.println("");
//
//		System.out.println("盛新锂能#SZ002240");
//		zdbvo = getZdb("SZ002240", 90);
//		zdbvo.p();
//f
//		System.out.println("");
//
//		System.out.println("盐湖股份#SZ000792");
//		zdbvo = getZdb("SZ000792", 60);
//		zdbvo.p();
//
//		System.out.println("");
//
//		System.out.println("华泰股份#SH600308");
//		zdbvo = getZdb("SH600308", 90);
//		zdbvo.p();
//
//		System.out.println("");
//
//		System.out.println("云铝股份#SZ000807");
//		zdbvo = getZdb("SZ000807", 90);
//		zdbvo.p();
//
//		System.out.println("");
//
//		System.out.println("首创环保#SH600008");
//		zdbvo = getZdb("SH600008", 90);
//		zdbvo.p();

		// getCaiWuList("sh600438");

		// System.out.println(getDayAvePrice("sh688636", 25));
		// System.out.println(getDayAvePrice("sz002154", 25));
		// getDayAvePrice("sh605186", 20);
		// getDayAvePrice("sh605186", 30);

		// System.out.println(getDayLiangAve("sz002154", 25));

		//
	}

	public static List<String> getZhangTing2DaysList()
	{
		return getZhangTing2DaysList(DateUtil.format(new Date(), DateUtil.YYYYMMDD));
	}
	
	
	// 获取连着指定 2天指定日涨停股票
	public static List<String> getZhangTing2DaysList(String date) {

		String ztgpfile = Config.DATA_DIR + "/data/ztlist/2dztgp-" + date + ".txt";
		String ztdayfile = Config.DATA_DIR + "/data/run/2dztgp.txt";

		deleteIfexist(ztgpfile, ztdayfile);

		List<String> list = new ArrayList<String>();
		StringBuffer strbuf = new StringBuffer("股票ID#股票名称#行业#15天涨停#涨停类型#30日涨跌比\n");
		System.out.println("股票ID#股票名称#行业#15天涨停#涨停类型#30日涨跌比");
			
		// 读取所有股票文件方法
		List<GuPiaoBaseVO> allgplist = RunUtils.getAllGpVoList();
		DecimalFormat df = new DecimalFormat("0.00");

		int ztnum = 0;
		
		for (int i = 0; i < allgplist.size(); i++)
		{
			GuPiaoBaseVO vo = allgplist.get(i);

			if (!vo.isOK())
			{
				continue;
			}

			try {
				String gpdaylist = RunUtils.getGpDayBody(vo.getCid());

				String[] splist = gpdaylist.split("\n");
				String[] infos  = splist[1].split(" ");

				// 上市天数
				int dnum = Integer.valueOf(infos[1].replace("total:", ""));
				if(dnum == 0)
				{
					continue;
				}

				// 上市时间
				//String startDate = infos[2].replace("start:", "");
				
				// 时间，开盘，收盘，最高，最低，量
				// 210402 30.00 30.00 30.08 29.65 381350\n\
				// 今天价格数据
				String[] jtinfos = splist[splist.length - 2].split(" ");
				int jtkpj = (int) (Double.valueOf(jtinfos[1]) * 100); // 开盘价
				int jtspj = (int) (Double.valueOf(jtinfos[2]) * 100); // 收盘价
				int jtzgj = (int) (Double.valueOf(jtinfos[3]) * 100); // 最高价
				//Double zgjdb = Double.valueOf(jtinfos[3]);
				int jtzdj = (int) (Double.valueOf(jtinfos[4]) * 100); // 最低价

				// 昨天价格数据
				String[] ztinfos = splist[splist.length - 3].split(" ");
				int ztspj = (int) (Double.valueOf(ztinfos[2]) * 100); // 收盘价

				if(jtspj < ztspj)
				{
					continue;
				}
				// 涨跌幅小于9.8 过滤
				String zdf = df.format((float) (jtspj - ztspj) / ztspj);
				if ((int) (Double.valueOf(zdf) * 1000) < 98)
				{
					continue;
				}

				if(vo.is3068())
				{
					if ((int) (Double.valueOf(zdf) * 1000) < 190)
					{
						//System.out.println(vo.toString());
						continue;
					}
				}
				if(!vo.getName().equals("京城股份")) {
					//continue;
				}
					

				// 涨停类型
				String ztlx = "";
				if (jtkpj == jtspj && jtzgj == jtzdj && jtkpj == jtzdj)
				{
					ztlx = "一字板";
				}

				if (jtkpj == jtspj && jtzgj > jtzdj) {
					ztlx = "T字板";
				}
				if (jtkpj < jtzgj || jtzdj < jtzdj) { 
					ztlx = "T字板";
				}
				//----------------------------------两涨停判断
				
				 jtinfos = splist[splist.length - 3].split(" ");
				  jtkpj = (int) (Double.valueOf(jtinfos[1]) * 100); // 开盘价
				  jtspj = (int) (Double.valueOf(jtinfos[2]) * 100); // 收盘价
				  jtzgj = (int) (Double.valueOf(jtinfos[3]) * 100); // 最高价
				//Double zgjdb = Double.valueOf(jtinfos[3]);
				  jtzdj = (int) (Double.valueOf(jtinfos[4]) * 100); // 最低价

				// 昨天价格数据
				ztinfos = splist[splist.length - 4].split(" ");
				  ztspj = (int) (Double.valueOf(ztinfos[2]) * 100); // 收盘价

				if(jtspj < ztspj)
				{
					continue;
				}
				// 涨跌幅小于9.8 过滤
				  zdf = df.format((float) (jtspj - ztspj) / ztspj);
				if ((int) (Double.valueOf(zdf) * 1000) < 98)
				{
					continue;
				}

				if(vo.is3068())
				{
					if ((int) (Double.valueOf(zdf) * 1000) < 190)
					{
						//System.out.println(vo.toString());
						continue;
					}
				}
				
				
				// 分类
				if (ValidateUtil.isNull(vo.getCate())) {
					vo.setCate(getCate(vo.getCid()));
				}

				// 15天涨停数量
					int zt15dnum = getZhangTingDays(vo.getCid(), 15);
 

					// 30天涨跌比
				String _30dayzdb = "";
					if (dnum > 30)
					{
						ZhangDieBiVO ztvo = getZdb(vo.getCid(), 30);
						_30dayzdb = ztvo.getZhangb().toString();
					}
 
					String textinfo = vo.toString() + "#" + zt15dnum + "#" + ztlx + "#" + _30dayzdb ;
				
			//	System.out.println(textinfo);
				strbuf.append(textinfo).append("\n");
				list.add(textinfo);
				ztnum ++;
			} catch (Exception e) {
				System.out.println(vo.toString());
				e.printStackTrace();
			}
		}
		
		System.out.println("双涨停列表 一共：" + ztnum + "条数据");

		FileUtil.writeFile(ztgpfile, strbuf.toString());
		FileUtil.writeFile(ztdayfile, strbuf.toString());

		return list;
	}
		
	public static List<String> getZhangTingList()
	{
		return getZhangTingList(DateUtil.format(new Date(), DateUtil.YYYYMMDD));
	}
	
	// 获取指定日涨停股票
	public static List<String> getZhangTingList(String date)
	{

		String ztgpfile = Config.DATA_DIR + "/data/ztlist/ztgp-" + date + ".txt";
		String ztdayfile = Config.DATA_DIR + "/data/run/ztgp.txt";

		deleteIfexist(ztgpfile, ztdayfile);

		List<String> list = new ArrayList<String>();
		//StringBuffer strbuf = new StringBuffer(
				//"股票ID#股票名称#行业#上市时间#上市天数#总市值#流通市值#市盈率TTM#15天涨停#涨停类型#最高价#最高价日#最高价比#8个月最高价#最高月#30日涨#30日跌#30日涨跌比\n");
		//System.out.println("股票ID#股票名称#行业#上市时间#上市天数#总市值#流通市值#市盈率TTM#涨跌幅#涨停类型#最高价#最高价日#最高价比#8个月最高价#最高月#30日涨#30日跌#30日涨跌比");

		StringBuffer strbuf = new StringBuffer("股票ID#股票名称#行业#15天涨停#涨停类型#30日涨跌比\n");
		System.out.println("股票ID#股票名称#行业#15天涨停#涨停类型#30日涨跌比");
			
		// 读取所有股票文件方法
		List<GuPiaoBaseVO> allgplist = RunUtils.getAllGpVoList();
		DecimalFormat df = new DecimalFormat("0.00");

		int ztnum = 0;
		
		for (int i = 0; i < allgplist.size(); i++)
		{
			GuPiaoBaseVO vo = allgplist.get(i);

			if (!vo.isOK())
			{
				continue;
			}

			try {
				// System.out.println(vo.toString());
				String gpdaylist = RunUtils.getGpDayBody(vo.getCid());

				String[] splist = gpdaylist.split("\n");
				String[] infos  = splist[1].split(" ");

				// 上市天数
				int dnum = Integer.valueOf(infos[1].replace("total:", ""));
				if(dnum == 0)
				{
					continue;
				}

				// 上市时间
				//String startDate = infos[2].replace("start:", "");
				
				// 时间，开盘，收盘，最高，最低，量
				// 210402 30.00 30.00 30.08 29.65 381350\n\
				// 今天价格数据
				String[] jtinfos = splist[splist.length - 2].split(" ");
				int jtkpj = (int) (Double.valueOf(jtinfos[1]) * 100); // 开盘价
				int jtspj = (int) (Double.valueOf(jtinfos[2]) * 100); // 收盘价
				int jtzgj = (int) (Double.valueOf(jtinfos[3]) * 100); // 最高价
				//Double zgjdb = Double.valueOf(jtinfos[3]);
				int jtzdj = (int) (Double.valueOf(jtinfos[4]) * 100); // 最低价

				// 昨天价格数据
				String[] ztinfos = splist[splist.length - 3].split(" ");
				int ztspj = (int) (Double.valueOf(ztinfos[2]) * 100); // 收盘价

				if(jtspj < ztspj)
				{
					continue;
				}
				
				// 涨跌幅小于9.8 过滤
				String zdf = df.format((float) (jtspj - ztspj) / ztspj);
				if ((int) (Double.valueOf(zdf) * 1000) < 98)
				{
					continue;
				}

				if(vo.is3068())
				{
					if ((int) (Double.valueOf(zdf) * 1000) < 190)
					{
						//System.out.println(vo.toString());
						continue;
					}
				}
				
				// 涨停类型
				String ztlx = "";
				if (jtkpj == jtspj && jtzgj == jtzdj && jtkpj == jtzdj)
				{
					ztlx = "一字板";
				}

				if (jtkpj == jtspj && jtzgj > jtzdj) {
					ztlx = "T字板";
				}

				// 分类
				if (ValidateUtil.isNull(vo.getCate())) {
					vo.setCate(getCate(vo.getCid()));
				}

				// 15天涨停数量
				int zt15dnum = getZhangTingDays(vo.getCid(), 15);

				// System.out.println(vo.toString());

				// 获取近60天最高价
//				Double _60dayZgj = zgjdb;
//				String _60dayTime = jtinfos[0];
//				if (dnum > 90) {
//					for (int n = 1; n <= 90; n++) {
//
//						String dayStr = splist[splist.length - 2 - n].replace("\\n\\", "");
//						// System.out.println(dayStr);
//						// 时间，开盘，收盘，最高，最低，量
//						// 210325 3.10 3.07 3.12 3.04 569107
//						String[] spdayStr = dayStr.split(" ");
//						if (spdayStr.length != 6) {
//							System.out.println(dayStr);
//							continue;
//						}
//
//						// 当天最高价
//						Double ztzgj = Double.valueOf(spdayStr[3]);
//						// System.out.println(ztzgj.compareTo(_60dayZgj));
//						if (ztzgj.compareTo(_60dayZgj) > 0) {
//							_60dayZgj = ztzgj;
//							_60dayTime = spdayStr[0];
//						}
//					}
//				}
//
//				// 获取近8个月最高价 跟 最高价比
//				// 近几个月最高价
//				Double _moZgj = 0.0;
//				String _moTime = "";
//				String gpmolist = RunUtils.getGpMonthBody(vo.getCid());
//				String[] spmolist = gpmolist.split("\n");
//
//				if (spmolist.length >= 10) {
//					for (int n = 0; n < 8; n++) {
//						// System.out.println(vo.getName() + "-" +
//						// spmolist[spmolist.length - 2 - n]);
//						String[] mos = spmolist[spmolist.length - 2 - n].split(" ");
//						Double maxmo = Double.valueOf(mos[3]);
//						if (maxmo.compareTo(_moZgj) > 0) {
//							_moZgj = maxmo;
//							_moTime = mos[0].substring(0, 4);
//						}
//					}
//				}
//
//				if (_moZgj.compareTo(_60dayZgj) > 0) {
//					_60dayZgj = _moZgj;
//					_60dayTime = _moTime;
//				}
//
//				// 最高价跟当前价比率
//				Double zgjzhang = DoubleUtil.mul(DoubleUtil.div(DoubleUtil.sub(zgjdb, _60dayZgj, 2), zgjdb, 2), 100.0, 2);

				// System.out.println(_60dayZgj + "-" + _60dayTime + "-" +
				// zgjzhang);

				// 30天涨跌比
				String _30dayzdb = "";
				if (dnum > 30)
				{
					ZhangDieBiVO ztvo = getZdb(vo.getCid(), 30);
					_30dayzdb = ztvo.getZhangb().toString();
				}

				// 基本面
				//JiBenMianVO jbmvo = getJiBenMianString(vo.getCid());

				// 股票ID#股票名称#行业#上市时间#上市天数#总市值#流通市值#市盈率TTM#涨跌幅#涨停类型#最高价#最高价日#最高价比#30日涨#30日跌#30日涨跌比
//				String textinfo = vo.toString() + "#" + startDate + "#" + dnum + "#" + jbmvo.getZsz() + "#"
//						+ jbmvo.getLtsz() + "#" + jbmvo.getSylTTM() + "#" + ztnum + "#" + ztlx + "#" + _60dayZgj
//						+ "#" + _60dayTime + "#" + zgjzhang + "%#" + _moZgj + "#" + _moTime + "#" + _30dayzdb;

				// 股票ID#股票名称#行业#上市时间#上市天数#总市值#流通市值#市盈率TTM#涨跌幅#涨停类型#最高价#最高价日#最高价比#30日涨#30日跌#30日涨跌比
				String textinfo = vo.toString() + "#" + zt15dnum + "#" + ztlx + "#" + _30dayzdb ;
				
			//	System.out.println(textinfo);
				strbuf.append(textinfo).append("\n");
				list.add(textinfo);
				ztnum ++;
			} catch (Exception e) {
				System.out.println(vo.toString());
				e.printStackTrace();
			}
		}
		
		System.out.println("涨停列表 一共：" + ztnum + "条数据");

		FileUtil.writeFile(ztgpfile, strbuf.toString());
		FileUtil.writeFile(ztdayfile, strbuf.toString());

		return list;
	}

	private static void deleteIfexist(String ztgpfile, String ztdayfile) {
		try {

			if (FileUtil.isExist(ztgpfile)) {
				FileUtil.removeFile(ztgpfile, true);
			}

			if (FileUtil.isExist(ztdayfile)) {
				FileUtil.removeFile(ztdayfile, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> getDieTingList()
	{
		return getDieTingList(DateUtil.format(new Date(), DateUtil.YYYYMMDD));
	}
	
	public static List<String> getDieTingList(String date)
	{

		String dtgpfile = Config.DATA_DIR + "/data/dtlist/dtgp-" + date + ".txt";
		String dtdayfile = Config.DATA_DIR + "/data/run/dtgp.txt";

		deleteIfexist(dtgpfile, dtdayfile);

		List<String> list = new ArrayList<String>();

		StringBuffer strbuf = new StringBuffer("股票ID#股票名称#行业#15天涨停#跌停类型#30日涨跌比\n");
		//System.out.println("股票ID#股票名称#行业#15天涨停#跌停类型#30日涨跌比");
			
		// 读取所有股票文件方法
		List<GuPiaoBaseVO> allgplist = RunUtils.getAllGpVoList();
		DecimalFormat df = new DecimalFormat("0.00");

		int dtnum = 0;
		
		for (int i = 0; i < allgplist.size(); i++)
		{
			GuPiaoBaseVO vo = allgplist.get(i);

			if (!vo.isOK())
			{
				continue;
			}

			try {
				// System.out.println(vo.toString());
				String gpdaylist = RunUtils.getGpDayBody(vo.getCid());

				String[] splist = gpdaylist.split("\n");
				String[] infos  = splist[1].split(" ");

				// 上市天数
				int dnum = Integer.valueOf(infos[1].replace("total:", ""));
				if(dnum == 0)
				{
					continue;
				}

				// 上市时间
				//String startDate = infos[2].replace("start:", "");
				
				// 时间，开盘，收盘，最高，最低，量
				// 210402 30.00 30.00 30.08 29.65 381350\n\
				// 今天价格数据
				String[] jtinfos = splist[splist.length - 2].split(" ");
				int jtkpj = (int) (Double.valueOf(jtinfos[1]) * 100); // 开盘价
				int jtspj = (int) (Double.valueOf(jtinfos[2]) * 100); // 收盘价
				int jtzgj = (int) (Double.valueOf(jtinfos[3]) * 100); // 最高价
				//Double zgjdb = Double.valueOf(jtinfos[3]);
				int jtzdj = (int) (Double.valueOf(jtinfos[4]) * 100); // 最低价

				// 昨天价格数据
				String[] ztinfos = splist[splist.length - 3].split(" ");
				int ztspj = (int) (Double.valueOf(ztinfos[2]) * 100); // 收盘价

				//今天收盘价 > 昨天收盘价 跳过
				if(jtspj > ztspj)
				{
					continue;
				}
				
				// 涨跌幅小于9.8 过滤
				String zdf = df.format((float) (ztspj - jtspj) / ztspj);
				if ((int) (Double.valueOf(zdf) * 1000) < 98)
				{
					continue;
				}

				if(vo.is3068())
				{
					if ((int) (Double.valueOf(zdf) * 1000) < 190)
					{
						//System.out.println(vo.toString());
						continue;
					}
				}
				
				// 涨停类型
				String ztlx = "";
				if (jtkpj == jtspj && jtzgj == jtzdj && jtkpj == jtzdj)
				{
					ztlx = "一字跌停";
				}

				if (jtkpj == jtspj && jtzgj > jtzdj) {
					ztlx = "T字跌停";
				}

				// 分类
				if (ValidateUtil.isNull(vo.getCate())) {
					vo.setCate(getCate(vo.getCid()));
				}

				// 15天涨停数量
				int zt15dnum = getZhangTingDays(vo.getCid(), 15);

				
				// 30天涨跌比 
				String _30dayzdb = "";
				if (dnum > 30)
				{
					ZhangDieBiVO ztvo = getZdb(vo.getCid(), 30);
					_30dayzdb = ztvo.getZhangb().toString();
				}
				
				String textinfo = vo.toString() + "#" + zt15dnum + "#" + ztlx + "#" + _30dayzdb ;
				
				//System.out.println(textinfo);
				strbuf.append(textinfo).append("\n");
				list.add(textinfo);
				
				dtnum ++;  
				
			} catch (Exception e) {
				System.out.println(vo.toString());
				e.printStackTrace();
			}
		}
		
		System.out.println("跌停列表 一共：" + dtnum + "条数据");

		FileUtil.writeFile(dtgpfile, strbuf.toString());
		FileUtil.writeFile(dtdayfile, strbuf.toString());

		return list;
	}
	
	// 获取次新股票
	// 判断10个月内上市的公司 200个交易日
	// isnew 是否重新生成
	public static List<GuPiaoCiXinVO> getNewList(boolean isnew) {
		List<GuPiaoCiXinVO> list = new ArrayList<GuPiaoCiXinVO>();

		// 次新股票
		String cxgpfile = Config.DATA_DIR + "/data/cxgp.txt";
		if (!isnew && FileUtil.isExist(cxgpfile)) {
			List<String> rdcxStrs = FileUtil.readToStringList(cxgpfile, StringPool.UTF_8);
			for (int i = 1; i < rdcxStrs.size(); i++) {
				GuPiaoCiXinVO cxvo = new GuPiaoCiXinVO();
				cxvo.valueOf(rdcxStrs.get(i));
				list.add(cxvo);
			}

			return list;
		}

		if (isnew) {
			try {
				if (FileUtil.isExist(cxgpfile)) {
					FileUtil.removeFile(cxgpfile, true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		StringBuffer strbuf = new StringBuffer(
				"股票ID#股票名称#分类#上市时间#上市天数#总市值(元)#流通市值(元)#市盈（TTM）#每股收益(元)#每股净资产(元)#营业总收入(元)#营业总收入同比增长(%)#净利润(元)#净利润同比增长(%)\n");

		// 读取所有股票文件方法
		List<GuPiaoBaseVO> allgplist = getAllGpVoList();

		for (int i = 0; i < allgplist.size(); i++) {
			GuPiaoBaseVO vo = allgplist.get(i);
			if (vo.isOK()) {
				GuPiaoCiXinVO cxvo = new GuPiaoCiXinVO();
				cxvo.setCid(vo.getCid());
				cxvo.setName(vo.getName());
				cxvo.setCate(vo.getCate());

				try {
					// System.out.println(vo.toString());
					String gpmlist = getGpDayBody(vo.getCid());

					String[] splist = gpmlist.split("\n");
					String[] infos = splist[1].split(" ");
					int dnum = Integer.valueOf(infos[1].replace("total:", ""));
					if (dnum > 160) {
						continue;
					}

					String startDate = infos[2].replace("start:", "");
					cxvo.setUptime(startDate);
					cxvo.setUpday(dnum);
					JiBenMianVO jbm = getJiBenMianString(cxvo.getCid());
					cxvo.valueOfJBM(jbm);

					//System.out.println(cxvo.toString());
					strbuf.append(cxvo.toString()).append("\n");
					list.add(cxvo);

				} catch (Exception e) {
					System.out.println(vo.toString());
					e.printStackTrace();
				}
			}
		}

		FileUtil.writeFile(cxgpfile, strbuf.toString());

		System.out.println(list.size());
		return list;
	}

	public static ZhangDieBiVO getZdb(String cid, int day) {
		ZhangDieBiVO vo = new ZhangDieBiVO();

		int zhang = 0; // 涨数量
		int die = 0; // 跌数量
		int ping = 0; // 平
		int zt = 0; // 涨停数量
		int dt = 0; // 跌停数据
		int z5 = 0; // 涨大于5%的数量
		int d5 = 0; // 跌大于5%的数量

		Double sumzhang = 0.0; // 平均涨
		Double sumdie = 0.0; // 平均跌

		Double maxzhang = 0.0; // 最大涨
		Double maxdie = 0.0; // 最大跌

		List<Double> zdflist = new ArrayList<Double>();

		try {

			// System.out.println(vo.toString());
			String gpmlist = RunUtils.getGpDayBody(cid);

			// System.out.println(gpmlist);

			String[] splist = gpmlist.split("\n");
			String[] infos = splist[1].split(" ");

			for (int n = (splist.length - 2); n > (splist.length - day - 2); n--) {
				// System.out.println(splist[n]);
				String[] str = splist[n].split(" ");

				// Double kpj = Double.valueOf(str[1]);//今日开盘价
				Double spj = Double.valueOf(str[2]);// 今日收盘价
				// Double zgj = Double.valueOf(str[3]);//今日最高价
				// Double zdj = Double.valueOf(str[4]);//今日最低价100

				String[] ztstr = splist[n - 1].split(" ");
				Double ztspj = Double.valueOf(ztstr[2]);// 昨日收盘价

				// 涨的比例
				Double zdf = DoubleUtil.mul(DoubleUtil.div(DoubleUtil.sub(spj, ztspj, 2), ztspj, 4), 100, 2);
				zdflist.add(zdf);
				// System.out.println("zdf=" + zdf);

				if (zdf == 0) {
					ping = ping + 1;
				} else if (zdf < 0) {
					die = die + 1;
					// System.out.println("跌+1=" + die);
					sumdie = DoubleUtil.sum(sumdie, zdf);
				} else {
					zhang = zhang + 1;
					// System.out.println("涨+1=" + zhang);
					sumzhang = DoubleUtil.sum(sumzhang, zdf);
				}

				int zdfint = ((int) DoubleUtil.mul(zdf, 100.00, 0));

				// 涨停
				if (zdfint >= 980) {
					zt++;
					// System.out.println("zt=" + zt);
				}

				// 涨5
				if (zdfint >= 500) {
					z5++;
					// System.out.println("z5=" + z5);
				}

				// 跌停
				if (zdfint <= -980) {

					dt++;
					// System.out.println("dt=" + dt);
				}

				// 跌5
				if (zdfint <= -500) {
					d5++;
					// System.out.println("d5=" + d5);
				}

				if (zdf.compareTo(maxzhang) > 0) {
					maxzhang = zdf;
				}

				if (zdf.compareTo(maxdie) < 0) {
					maxdie = zdf;
				}
			}

			// System.out.println(zhang + "#" + die + "#" + zt + "#" + dt + "#"
			// + z5 + "#" + d5);

			vo.setDay(day);
			vo.setZhang(zhang);
			vo.setZt(zt);
			vo.setZ5(z5);
			vo.setDie(die);
			vo.setDt(dt);
			vo.setD5(d5);
			vo.setPing(ping);

			Double zhangb = DoubleUtil.mul(DoubleUtil.div(Double.valueOf(zhang), Double.valueOf(zhang + die), 2), 100.0,
					2);
			Double dieb = DoubleUtil.mul(DoubleUtil.div(Double.valueOf(die), Double.valueOf(zhang + die), 2), 100.0, 2);
			vo.setZhangb(zhangb);
			vo.setDieb(dieb);

			vo.setMaxdie(maxdie);
			vo.setMaxzhang(maxzhang);

			vo.setAvezhang(DoubleUtil.div(sumzhang, Double.valueOf(zhang), 2));
			vo.setAvedie(DoubleUtil.div(sumdie, Double.valueOf(die), 2));

			vo.setSumzhang(sumzhang);
			vo.setSumdie(sumdie);

			Double ave2zhang = DoubleUtil.div(vo.getAvezhang(), 2.0, 2);
			Double ave2die = DoubleUtil.div(vo.getAvedie(), 2.0, 2);

			int ave2zhangnum = 0;
			int avezhangmaxnum = 0;

			int ave2dienum = 0;
			int avediemaxnum = 0;

			for (int i = 0; i < zdflist.size(); i++) {

				Double zdfs = zdflist.get(i);

				// 平均一半 ~ 平均涨比 数及占比
				if (zdfs.compareTo(ave2zhang) > 0 && zdfs.compareTo(vo.getAvezhang()) < 0) {
					// System.out.println(zdfs);
					ave2zhangnum++;
				}

				// 大于平均涨比 数及占比
				else if (zdfs.compareTo(vo.getAvezhang()) > 0) {
					// System.out.println(zdfs);
					avezhangmaxnum++;
				}

				// 平均一半 ~ 平均跌比 数及占比
				else if (zdfs.compareTo(ave2die) < 0 && zdfs.compareTo(vo.getAvedie()) > 0) {

					ave2dienum++;
				}

				// 大于平均涨比 数及占比
				else if (zdfs.compareTo(vo.getAvedie()) < 0) {

					avediemaxnum++;
				}
			}

			vo.setAve2zhangnum(ave2zhangnum);
			vo.setAvezhangmaxnum(avezhangmaxnum);

			vo.setAve2zhangb(
					DoubleUtil.mul(DoubleUtil.div(Double.valueOf(ave2zhangnum), Double.valueOf(zhang), 2), 100.0, 2));
			vo.setAvezhangmaxb(
					DoubleUtil.mul(DoubleUtil.div(Double.valueOf(avezhangmaxnum), Double.valueOf(zhang), 2), 100.0, 2));

			vo.setAve2dienum(ave2dienum);
			vo.setAvediemaxnum(avediemaxnum);

			vo.setAve2dieb(
					DoubleUtil.mul(DoubleUtil.div(Double.valueOf(ave2dienum), Double.valueOf(die), 2), 100.0, 2));
			vo.setAvediemaxb(
					DoubleUtil.mul(DoubleUtil.div(Double.valueOf(avediemaxnum), Double.valueOf(die), 2), 100.0, 2));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("出错：" + cid);
		}
		return vo;
	}

	// //获取所有股票里月成交量及50天成交量大于1亿的股票
	// public static List<GuPiaoDuanXian> get1YiList(boolean flag)
	// {
	//
	// //1亿文件是否是存在，是不需要操作
	// String yyfile = Config.DATA_DIR + "\\data\\1yilist\\all.txt";
	// if (FileUtil.isExist(yyfile))
	// {
	//
	// try {
	// if(flag)
	// {
	// FileUtil.removeFile(yyfile, true);
	// } else {
	// List<GuPiaoDuanXian> yilist = new ArrayList<GuPiaoDuanXian>();
	// List<String> yistrlist = FileUtil.readToStringList(yyfile,"UTF-8");
	// for (String yistr : yistrlist)
	// {
	// GuPiaoDuanXian vo = new GuPiaoDuanXian();
	// vo.valueOfBase(yistr);
	// yilist.add(vo);
	// }
	// return yilist;
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// List<GuPiaoDuanXian> yilist = new ArrayList<GuPiaoDuanXian>();
	// StringBuffer yibuf = new StringBuffer();
	//
	// //读取所有股票文件方法
	// List<GuPiaoBaseVO> list = getAllGpVoList();
	//
	// //StringBuffer strbuf = new
	// StringBuffer("股票ID#股票名称#分类#上市时间#上市天数#总市值(元)#流通市值(元)#市盈（TTM）#每股收益(元)#每股净资产(元)#营业总收入(元)#营业总收入同比增长(%)#净利润(元)#净利润同比增长(%)#");
	//
	// //strbuf.append("30天成交均额#");
	// //strbuf.append("30天涨跌比#");
	// //strbuf.append("30天涨停数\n");
	//
	// //StringBuffer strbuf = new StringBuffer("股票ID#股票名称");
	//
	// for (int i = 0; i < list.size(); i++)
	// {
	// GuPiaoBaseVO vo = list.get(i);
	//
	// if (!vo.isOK())
	// {
	// continue;
	// }
	//
	// try {
	//
	// String gpmlist = RunUtils.getGpDayBody(vo.getCid());
	// String[] splist = gpmlist.split("\n");
	// String[] infos = splist[1].split(" ");
	// int dnum = Integer.valueOf(infos[1].replace("total:", ""));
	// String startDate = infos[2].replace("start:", "");
	//
	// //新股不考虑，至少大于250个交易日 小于1年的票
	// if (dnum < 250)
	// {
	// continue;
	// }
	//
	// Double _30liangAve = getDayLiangAve(vo.getCid(), 30);
	// if(_30liangAve < 1.5)
	// {
	// continue;
	// }
	//
	// String sstr =
	// HttpClientUtil.getInstance().doGet("http://qt.gtimg.cn/q=s_" +
	// vo.getCid());
	// String[] sspls = sstr.split("~");
	// Double zsz = Double.valueOf(sspls[9]);
	// //if(zsz < 50.0)
	// //{
	// // continue;
	// //}
	//
	// //30日涨停比
	// ZhangDieBiVO zdbvo = getZdb(vo.getCid(), 60);
	//
	// Double zhangb = zdbvo.getZhangb();
	// int zhangting = zdbvo.getZt();
	// if(zhangting == 0)
	// {
	// continue;
	// }
	//
	// //昨日交易形态（阴阳-涨比-量能）
	// //3月交易涨跌比（趋势）低高点上移（全红）
	// //5周交易涨跌比（趋势）低高点上移（3周低点下移或高点下移并绿柱则弱）（3周阳）
	// //25日交易涨跌比-低高点上移
	// //日周月均线多头趋势
	//
	// //if(ValidateUtil.isNull(vo.getCate()))
	// //{
	// // vo.setCate(getCate(vo.getCid()));
	// //}
	//
	// GuPiaoDuanXian dxvo = new GuPiaoDuanXian();
	// dxvo.setCid(vo.getCid());
	// dxvo.setName(vo.getName());
	// dxvo.setCate(vo.getCate());
	// dxvo.setUptime(startDate);
	// dxvo.setUpday(dnum);
	// dxvo.setZsz(String.valueOf(zsz));
	// dxvo.set_30liangAve(_30liangAve);
	// dxvo.set_30zdb(zhangb);
	// dxvo.set_30zt(zhangting);
	//// JiBenMianVO jbm = getJiBenMianString(dxvo.getCid());
	//// dxvo.valueOfJBM(jbm);
	//
	// System.out.println(dxvo.toBaseString());
	// //yibuf.append(dxvo.toBaseString() + "\n");
	// yilist.add(dxvo);
	//
	// } catch (Exception e) {
	// System.out.println(vo.toString());
	// e.printStackTrace();
	// }
	// }
	//
	// FileUtil.writeFile(yyfile, yibuf.toString());
	//
	// return yilist;
	// }

	// 获取所有股票里月成交量及25天成交量大于1亿的股票
	public static List<GuPiaoBaseVO> get1YiList(boolean flag)
	{
		// 1亿文件是否是存在，是不需要操作
		String yyfile = Config.DATA_DIR + "/data/1yilist/all.txt";
		if (FileUtil.isExist(yyfile))
		{

			try {
				if (flag)
				{
					FileUtil.removeFile(yyfile, true);
				} else {
					List<GuPiaoBaseVO> yilist = new ArrayList<GuPiaoBaseVO>();
					List<String> yistrlist = FileUtil.readToStringList(yyfile, "UTF-8");
					for (String yistr : yistrlist)
					{
						// System.out.println(yistr);
						GuPiaoBaseVO vo = new GuPiaoBaseVO();
						vo.valueOf(yistr);
						yilist.add(vo);
					}
					return yilist;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<GuPiaoBaseVO> yilist = new ArrayList<GuPiaoBaseVO>();
		StringBuffer yibuf = new StringBuffer();

		// 读取所有股票文件方法
		List<GuPiaoBaseVO> list = getAllGpVoList();

		//System.out.println("股票ID#股票名称#分类");

		for (int i = 0; i < list.size(); i++)
		{
			GuPiaoBaseVO vo = list.get(i);

			if (!vo.isOK() || vo.is3068())
			{
				continue;
			}

			try {

				int dnum = RunUtils.getUpday(vo.getCid());
				
				// 新股不考虑，至少大于250个交易日 小于1年的票
				if (dnum < 30)
				{
					continue;
				}

				//20天平均1亿
				Double liangAve = getDayLiangAve(vo.getCid(), 20);
				if (liangAve.compareTo(1.0) < 0)
				{
					continue;
				}
				
//				int ztnum = RunUtils.getZhangTingDays(vo.getCid(), 38);
//				if (ztnum < 1) {
//					continue;
//				}
//				
//				ztnum = RunUtils.getZhangTingDays(vo.getCid(), 88);
//				if (ztnum > 3) {
//					continue;
//				}
//				
//				GuPiaoInfoVO info = getGuPiaoInfo(vo.getCid());
//				if(info.getLtsz().compareTo(50.00) < 0)
//				{
//					continue;
//				}
//				
//				if(info.getLtsz().compareTo(300.00) > 0)
//				{
//					continue;
//				}
//				
//				System.out.println(vo.toString() + "#" + ztnum + "#" + info.getZsz() + "#" + info.getSyl());
				System.out.println(vo.toString());
				yibuf.append(vo.toString() + "\n");
				yilist.add(vo);

			} catch (Exception e) {
				System.out.println(vo.toString());
				e.printStackTrace();
			}
		}

		FileUtil.writeFile(yyfile, yibuf.toString());

		System.out.println(" 获取股票 一共：" + yilist.size() + "条数据");

		return yilist;
	}

	// 获取所有股票里68天内涨停1次，最近7天缩量
	public static List<GuPiaoBaseVO> getSuoLiangList(boolean flag) {

		String liangfile = Config.DATA_DIR + "/data/lianglist/all.txt";
		if (FileUtil.isExist(liangfile)) {

			try {
				if (flag) {
					FileUtil.removeFile(liangfile, true);
				} else {
					List<GuPiaoBaseVO> yilist = new ArrayList<GuPiaoBaseVO>();
					List<String> yistrlist = FileUtil.readToStringList(liangfile, "UTF-8");
					for (String yistr : yistrlist) {
						// System.out.println(yistr);
						GuPiaoBaseVO vo = new GuPiaoBaseVO();
						vo.valueOf(yistr);
						yilist.add(vo);
					}
					return yilist;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<GuPiaoBaseVO> lianglist = new ArrayList<GuPiaoBaseVO>();
		StringBuffer liangbuf = new StringBuffer();

		// 读取所有股票文件方法
		List<GuPiaoBaseVO> list = getAllGpVoList();

		System.out.println("股票ID#股票名称#分类");

		for (int i = 0; i < list.size(); i++) {
			GuPiaoBaseVO vo = list.get(i);

			if (!vo.isOK()) {
				continue;
			}

			try {

				int dnum = RunUtils.getUpday(vo.getCid());
				// 新股不考虑，至少大于200个交易日 小于1年的票
				if (dnum < 200) {
					continue;
				}

				// 68天内必须涨停>1
				ZhangDieBiVO zdbvo = RunUtils.getZdb(vo.getCid(), 68);
				if (zdbvo.getZt() < 1) {
					continue;
				}

				// 7天的量小于 3千万
				Double liangAve = RunUtils.getDayLiangAve(vo.getCid(), 7);
				if (liangAve.compareTo(0.3) > 0) {
					continue;
				}

				// 小于38亿跳过
				Double zsz = RunUtils.getZsz(vo.getCid());
				if (zsz.compareTo(38.0) < 0) {
					continue;
				}

				// 连续5年扣非净利润>0元
				boolean isCwFu = false;
				List<CaiWuVO> cwlist = RunUtils.getCaiWuList(vo.getCid());
				if (ValidateUtil.isEmpty(cwlist)) {
					continue;
				}
				for (CaiWuVO caiWuVO : cwlist) {
					if (Double.valueOf(caiWuVO.getKfjlr()) < 0) {
						isCwFu = true;
						break;
					}
				}

				if (isCwFu) {
					continue;
				}

				// 最近财务更新
				// CaiWuVO cwvo = cwlist.get(0);
				// if(Double.valueOf(cwvo.getKfjlr()) < 0)
				// {
				// System.out.println("最近财务扣非净利润【" + cwvo.getKfjlr() + "】<0：" +
				// vo.toBaseString());
				// continue;
				// }

				System.out.println(vo.toString());
				liangbuf.append(vo.toString() + "\n");
				lianglist.add(vo);

			} catch (Exception e) {
				System.out.println(vo.toString());
				e.printStackTrace();
			}
		}

		FileUtil.writeFile(liangfile, liangbuf.toString());

		System.out.println("一共：" + lianglist.size() + "条数据");

		return lianglist;
	}

	// 下载天文件
	public static void downloadDayFile() {
		String cidfile = Config.DATA_DIR + "/data/daylist/";
		if (FileUtil.isExist(cidfile)) {
			try {
				FileUtil.removeFile(cidfile, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<GuPiaoBaseVO> list = getAllGpVoList();
		float count = 0;
		int length = list.size();
		DecimalFormat decimalFormat = new DecimalFormat(".00");
		for (int i = 0; i < length; i++) {
			GuPiaoBaseVO vo = list.get(i);
			// if(vo.isOK())
			{
				try {
					
					if( (count % 300) == 0){
						float bl = new Float(  ((float)(count/length*100))).floatValue() ;
						System.out.println("downMeta: " + length + " - "+ (int)count +" - " + (int) bl +"%"); 
					}
					count++;
					
					getGpDayBody(vo.getCid());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 下载月文件
	public static void downloadMonthFile() {
		String cidfile = Config.DATA_DIR + "/data/monthlist/";
		if (FileUtil.isExist(cidfile)) {
			try {
				FileUtil.removeFile(cidfile, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<GuPiaoBaseVO> list = getAllGpVoList();

		for (int i = 0; i < list.size(); i++) {
			GuPiaoBaseVO vo = list.get(i);
			if (vo.isOK()) {
				try {
					System.out.println(vo.toString());
					getGpMonthBody(vo.getCid());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getCId(String cid) {
		cid = cid.toLowerCase();
		if (cid.startsWith("sz") == false && cid.startsWith("sh") == false) {
			if (cid.startsWith("6")) {
				cid = "sh" + cid;
				// txt = txt + "#";
			} else {
				cid = "sz" + cid;
			}
		}

		return cid;
	}

	// 获取涨停天数
	// days 多少天里有多少涨停
	public static int getZhangTingDays(String cid, int days) {
		int ztnum = 0;

		try {

			String gpmlist = RunUtils.getGpDayBody(cid);

			String[] splist = gpmlist.split("\n");
			//String[] infos = splist[1].split(" ");

			// 拿最多15天的交易数据
			int num = splist.length - 2 > days ? days : splist.length - 2;
			if (num < 1)
			{
				return 0;
			}

			// 15天内有2次涨停
			for (int n = 1; n <= num; n++) {

				String dayStr = splist[splist.length - 1 - n].replace("\\n\\", "");

				// System.out.println(dayStr);

				// 时间，开盘，收盘，最高，最低，量
				// 210325 3.10 3.07 3.12 3.04 569107
				String[] spdayStr = dayStr.split(" ");
				if (spdayStr.length != 6) {
					// System.out.println(dayStr);
					continue;
				}

				// 当天收盘价
				Double jtspj = Double.valueOf(spdayStr[2]);

				// 昨天数据
				String ztdayStr = splist[splist.length - 2 - n].replace("\\n\\", "");
				// System.out.println("2:" + ztdayStr);
				String[] ztspdayStr = ztdayStr.split(" ");
				if (ztspdayStr.length != 6) {
					continue;
				}

				// 昨天收盘价
				Double ztspj = Double.valueOf(ztspdayStr[2]);

				// 涨停算法 = （今天收盘价 - 昨天收盘价） / 昨天收盘价 > 9.8
				if (DoubleUtil.div(DoubleUtil.sub(jtspj, ztspj), ztspj, 2) > 0.098) {
					ztnum++;
				}
			}

		} catch (Exception e) {

			System.out.println(cid);
			e.printStackTrace();
		}
		return ztnum;
	}
	
	// 获取跌停天数
	// days 多少天里有多少跌停
	public static int getDieTingDays(String cid, int days)
	{
		int dtnum = 0;

		try {

			String gpmlist = RunUtils.getGpDayBody(cid);

			String[] splist = gpmlist.split("\n");
			//String[] infos = splist[1].split(" ");

			// 拿最多15天的交易数据
			int num = splist.length - 2 > days ? days : splist.length - 2;
			if (num < 1)
			{
				return 0;
			}

			// 15天内有2次涨停
			for (int n = 1; n <= num; n++)
			{

				String dayStr = splist[splist.length - 1 - n].replace("\\n\\", "");

				// System.out.println(dayStr);

				// 时间，开盘，收盘，最高，最低，量
				// 210325 3.10 3.07 3.12 3.04 569107
				String[] spdayStr = dayStr.split(" ");
				if (spdayStr.length != 6) {
					// System.out.println(dayStr);
					continue;
				}

				// 当天收盘价
				Double jtspj = Double.valueOf(spdayStr[2]);

				// 昨天数据
				String ztdayStr = splist[splist.length - 2 - n].replace("\\n\\", "");
				// System.out.println("2:" + ztdayStr);
				String[] ztspdayStr = ztdayStr.split(" ");
				if (ztspdayStr.length != 6) {
					continue;
				}

				// 昨天收盘价
				Double ztspj = Double.valueOf(ztspdayStr[2]);
				//System.out.println(dayStr);
				//System.out.println(DoubleUtil.div(DoubleUtil.sub(jtspj, ztspj), ztspj, 4) * 100);
				
				// 涨停算法 = （今天收盘价 - 昨天收盘价） / 昨天收盘价 > 9.8
				if (DoubleUtil.div(DoubleUtil.sub(jtspj, ztspj), ztspj, 4) * 100 < - 9.8)
				{
					dtnum++;
				}
			}

		} catch (Exception e) {

			System.out.println(cid);
			e.printStackTrace();
		}
		return dtnum;
	}

	// 上市天数
	public static Integer getUpday(String cid) {
		try {
			String gpdlist = RunUtils.getGpDayBody(cid);
			String[] splist = gpdlist.split("\n");
			String[] infos = splist[1].split(" ");
			int dnum = Integer.valueOf(infos[1].replace("total:", ""));
			return dnum;
		} catch (Exception e) {
			System.out.println(cid);
			e.printStackTrace();

		}

		return 0;
	}

	// 上市日期
	public static String getUptime(String cid) {
		try {
			String gpdlist = RunUtils.getGpDayBody(cid);
			String[] splist = gpdlist.split("\n");
			String[] infos = splist[1].split(" ");
			return infos[2].replace("start:", "");
		} catch (Exception e) {
			System.out.println(cid);
			e.printStackTrace();
		}

		return "";
	}

	// 总市值
	public static Double getZsz(String cid) {
		try {
			String sstr = HttpClientUtil.getInstance().doGet("http://qt.gtimg.cn/q=s_" + cid);
			String[] sspls = sstr.split("~");
			return Double.valueOf(sspls[9]);
		} catch (Exception e) {
			System.out.println(cid);
			e.printStackTrace();
		}

		return 0.0;
	}

	// 平均交易量(亿为单位)
	public static Double getDayLiangAve(String cid, int days) {
		Double _dayAvePrice = 0.0;
		try {

			String daylist = RunUtils.getGpDayBody(cid);
			String[] splist = daylist.split("\n");

			Double sumliang = 0.0;
			int gs = 100;

			if (getCId(cid).startsWith("sh68")) {
				gs = 1;
			}

			for (int n = 1; n <= days; n++) {
				String dayStr = splist[splist.length - 1 - n].replace("\\n\\", "");
				// 时间，开盘，收盘，最高，最低，量
				// 210325 3.10 3.07 3.12 3.04 569107
				String[] spdayStr = dayStr.split(" ");
				Double dayliang = DoubleUtil.div(DoubleUtil.mul(Double.valueOf(spdayStr[2]),
						Double.valueOf(Integer.valueOf(spdayStr[5]) * gs), 2), 100000000.00, 2);

				// System.out.println(dayStr + "#" + dayliang);
				sumliang = DoubleUtil.sum(sumliang, dayliang);
			}

			_dayAvePrice = DoubleUtil.div(sumliang, Double.valueOf(days), 2);
			// System.out.println(_dayAvePrice);
		} catch (Exception e) {
			System.out.println(cid);
			e.printStackTrace();
		}

		return _dayAvePrice;
	}

	// 均线方法 均线价格
	public static Double getDayAvePrice(String cid, int days)
	{
		Double _dayAvePrice = 0.0;
		try {

			String daylist = RunUtils.getGpDayBody(cid);
			String[] splist = daylist.split("\n");

			Double sum = 0.0;
			for (int n = 1; n <= days; n++)
			{
				String dayStr = splist[splist.length - 1 - n].replace("\\n\\", "");
				// 时间，开盘，收盘，最高，最低，量
				// 210325 3.10 3.07 3.12 3.04 569107
				String[] spdayStr = dayStr.split(" ");
				sum = DoubleUtil.sum(sum, Double.valueOf(spdayStr[2]));
			}

			_dayAvePrice = DoubleUtil.div(sum, Double.valueOf(days), 2);
			// System.out.println(_dayAvePrice);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return _dayAvePrice;
	}

	public static GuPiaoBaseVO getGuPiaoBaseVO(String gptxt)
	{
		String[] txtarray = gptxt.split("#");

		String cid  = txtarray[0].toLowerCase(); // ID
		String name = txtarray[1]; // 名称
		String cate = "";
		if (txtarray.length >= 3)
		{
			cate = txtarray[2]; // 分类
		}

		GuPiaoBaseVO vo = new GuPiaoBaseVO();
		vo.setName(name);

		// ST，退市，退
		if (gptxt.indexOf("ST") != -1 || gptxt.indexOf("退市") != -1 || gptxt.indexOf("退") != -1 || gptxt.startsWith("83") || gptxt.startsWith("43"))
		{
			vo.setOK(false);
		}
		
		if (gptxt.startsWith("30") || gptxt.startsWith("sz30"))
		{
			vo.setIs30(true);
		}
		
		if (gptxt.startsWith("68") || gptxt.startsWith("sh68"))
		{
			vo.setIs68(true);
		}
		
		//先跳过300和688的票 - 8
		if (gptxt.startsWith("30") || gptxt.startsWith("68") || gptxt.startsWith("sz30") || gptxt.startsWith("sh68"))
		{
			vo.setIs3068(true);
		}

		if (cid.startsWith("sz") == false && cid.startsWith("sh") == false)
		{
			if (cid.startsWith("6"))
			{
				cid = "sh" + cid;
			} else {
				cid = "sz" + cid;
			}
		}

		vo.setCid(cid);
		vo.setCate(cate);

		return vo;
	}

	// 生成日文件
	public static String getGpDayBody(String cid) throws Exception {

		// 读取日交易数据
		String cidfile = Config.DATA_DIR + "/data/daylist/" + cid + ".txt";
		// System.out.println(cidfile);
		if (FileUtil.isExist(cidfile)) {
			return FileUtil.readLine(cidfile, "UTF-8");
			// System.out.println("file read:" + daybody);
		} else {
			// 获取个股日线表
			String daylistapi = "https://data.gtimg.cn/flashdata/hushen/latest/daily/" + cid + ".js?maxage=43201";
			String daybody = HttpClientUtil.getInstance().doGet(daylistapi);
			// System.out.println("url read:" + daybody);
			FileUtil.writeFile(cidfile, daybody);
			return daybody;
		}
	}

	// 生成月文件
	public static String getGpMonthBody(String cid) throws Exception {
		String cidfile = Config.DATA_DIR + "/data/monthlist/" + cid + ".txt";
		// System.out.println(cidfile);
		if (FileUtil.isExist(cidfile)) {
			return FileUtil.readLine(cidfile, "UTF-8");
			// System.out.println("file read:" + daybody);
		} else {

			String listapi = "https://data.gtimg.cn/flashdata/hushen/monthly/" + cid + ".js?maxage=43201";
			String body = HttpClientUtil.getInstance().doGet(listapi);
			// System.out.println("url read:" + daybody);
			FileUtil.writeFile(cidfile, body);
			return body;
		}
	}

	public static String getCate(String cid) {

		String cate = "";
		try {

			if (null == cateMap || cateMap.isEmpty())
			{
				List<String> gpcatelist = FileUtil.readToStringList(Config.DATA_DIR + "/data/gpcate.txt", StringPool.UTF_8);
				for (String catestr : gpcatelist)
				{
					if (ValidateUtil.isNull(catestr))
					{
						continue;
					}
					// System.out.println(catestr);
					GuPiaoBaseVO vo = RunUtils.getGuPiaoBaseVO(catestr);
					cateMap.put(vo.getCid(), vo.getCate());
				}
			}

			cate = cateMap.get(getCId(cid));

			if (ValidateUtil.isNotNull(cate)) {
				return cate;
			}

			// 股东研究
			String body = HttpClientUtil.getInstance().doGet(
					"http://f10.eastmoney.com/f10_v2/ShareholderResearch.aspx?code=" + cid,
					"http://quote.eastmoney.com/" + cid + ".html", "f10.eastmoney.com");
			body = HttpClientUtil.getInstance().doGet(
					"http://f10.eastmoney.com/IndustryAnalysis/IndustryAnalysisAjax?code=" + cid + "&icode=456",
					"f10.eastmoney.com");
			JSONObject thdbobj = JSONObject.parseObject(body);
			// System.out.println(body);
			// System.out.println("行业");
			JSONObject czxbj = JSONObject.parseObject(thdbobj.get("czxbj").toString());
			JSONArray czxbjarray = JSONObject.parseArray(czxbj.get("data").toString());

			JSONObject mcobj = JSONObject.parseObject(czxbjarray.get(0).toString());
			// System.out.println("ID：" + mcobj.getString("dm"));
			// System.out.println("企业：" + mcobj.getString("jc"));
			// System.out.print(mcobj.getString("dm") + "#");
			// System.out.print(mcobj.getString("jc") + "#");

			JSONObject hymcobj = JSONObject.parseObject(czxbjarray.get(1).toString());
			// System.out.println("行业：" + hymcobj.getString("dm"));
			// System.out.println(hymcobj.getString("dm") );

			return hymcobj.getString("dm").replace("2019", "").replace("2020", "");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	// 财务信息
	public static List<CaiWuVO> getCaiWuList(String cid) {
		List<CaiWuVO> list = new ArrayList<CaiWuVO>();

		try {

			// 当前年及季度的财务情况
			CaiWuVO cwvo = new CaiWuVO();
			cwvo.setCid(cid);

			String body = HttpClientUtil.getInstance().doGet(
					"http://f10.eastmoney.com/NewFinanceAnalysis/Index?type=web&code=" + cid,
					"http://quote.eastmoney.com/" + cid + ".html", "f10.eastmoney.com");
			body = HttpClientUtil.getInstance().doGet(
					"http://f10.eastmoney.com/NewFinanceAnalysis/ZYZBAjaxNew?type=0&code=" + cid, "f10.eastmoney.com");

			JSONObject obj1 = JSONObject.parseObject(body);
			JSONArray array1 = JSONObject.parseArray(obj1.get("data").toString());
			JSONObject data1 = JSONObject.parseObject(array1.get(0).toString());
			cwvo.setYyzsr(data1.get("TOTALOPERATEREVE").toString());
			cwvo.setYyzsrtbzz(data1.get("TOTALOPERATEREVETZ").toString());
			cwvo.setGsjlr(data1.get("PARENTNETPROFIT").toString());
			cwvo.setGsjlrtbzz(data1.get("PARENTNETPROFITTZ").toString());
			cwvo.setKfjlr(data1.get("KCFJCXSYJLR").toString());

			cwvo.setKfjlrtbzz("0.0");
			if (ValidateUtil.isNotNull(data1.get("KCFJCXSYJLRTZ"))) {
				cwvo.setKfjlrtbzz(data1.get("KCFJCXSYJLRTZ").toString());
			}
			// cwvo.setKfjlrtbzz(data1.get("KCFJCXSYJLRTZ").toString());
			cwvo.setCwdate(data1.get("REPORT_DATE").toString().split(" ")[0]);
			cwvo.setJzcsyl(data1.getString("ROEJQ").toString());

			// cwvo.p();

			list.add(cwvo);

			body = HttpClientUtil.getInstance().doGet(
					"http://f10.eastmoney.com/NewFinanceAnalysis/ZYZBAjaxNew?type=1&code=" + cid, "f10.eastmoney.com");
			JSONObject obj2 = JSONObject.parseObject(body);
			JSONArray array2 = JSONObject.parseArray(obj2.get("data").toString());

			int ynum = array2.size() > 4 ? 4 : array2.size();
			for (int i = 0; i < ynum; i++) {
				// System.out.println(i);
				CaiWuVO ncwvo = new CaiWuVO();
				JSONObject ndata = JSONObject.parseObject(array2.get(i).toString());

				ncwvo.setYyzsr("0.0");
				if (ValidateUtil.isNotNull(ndata.get("TOTALOPERATEREVE"))) {
					ncwvo.setYyzsr(ndata.get("TOTALOPERATEREVE").toString());
				}

				ncwvo.setYyzsrtbzz("0.0");
				if (ValidateUtil.isNotNull(ndata.get("TOTALOPERATEREVETZ"))) {
					ncwvo.setYyzsrtbzz(ndata.get("TOTALOPERATEREVETZ").toString());
				}

				ncwvo.setGsjlr("0.0");
				if (ValidateUtil.isNotNull(ndata.get("PARENTNETPROFIT"))) {
					ncwvo.setGsjlr(ndata.get("PARENTNETPROFIT").toString());
				}

				ncwvo.setGsjlrtbzz("0.0");
				if (ValidateUtil.isNotNull(ndata.get("PARENTNETPROFITTZ"))) {
					ncwvo.setGsjlrtbzz(ndata.get("PARENTNETPROFITTZ").toString());
				}

				ncwvo.setKfjlr("0.0");
				if (ValidateUtil.isNotNull(ndata.get("KCFJCXSYJLR"))) {
					ncwvo.setKfjlr(ndata.get("KCFJCXSYJLR").toString());
				}

				ncwvo.setKfjlrtbzz("0.0");
				if (ValidateUtil.isNotNull(ndata.get("KCFJCXSYJLRTZ"))) {
					ncwvo.setKfjlrtbzz(ndata.get("KCFJCXSYJLRTZ").toString());
				}

				ncwvo.setCwdate("0.0");
				if (ValidateUtil.isNotNull(ndata.get("REPORT_DATE"))) {
					ncwvo.setCwdate(ndata.get("REPORT_DATE").toString());
				}

				ncwvo.setJzcsyl("0.0");
				if (ValidateUtil.isNotNull(ndata.get("ROEJQ"))) {
					ncwvo.setJzcsyl(ndata.get("ROEJQ").toString());
				}
				// ncwvo.setJzcsyl(ndata.getString("ROEJQ").toString());

				// ncwvo.setYyzsrtbzz(ndata.get("TOTALOPERATEREVETZ").toString());
				// ncwvo.setGsjlr(ndata.get("PARENTNETPROFIT").toString());
				// ncwvo.setGsjlrtbzz(ndata.get("PARENTNETPROFITTZ").toString());
				// ncwvo.setKfjlr(ndata.get("KCFJCXSYJLR").toString());
				// ncwvo.setKfjlrtbzz(ndata.get("KCFJCXSYJLRTZ").toString());
				// ncwvo.setCwdate(ndata.get("REPORT_DATE").toString().split("
				// ")[0]);

				// ncwvo.p();

				list.add(ncwvo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(cid);
		}
		return list;
	}

	// 基本面
	public static JiBenMianVO getJiBenMianString(String cid) {
		JiBenMianVO jbmvo = new JiBenMianVO();
		try {

			jbmvo.setCid(cid);

			String body = "";

			body = HttpClientUtil.getInstance().doGet("http://qt.gtimg.cn/q=" + cid);
			String[] sspls = body.split("~");
			jbmvo.setName(sspls[1]);
			jbmvo.setZsz(sspls[45]);
			jbmvo.setLtsz(sspls[44]);
			jbmvo.setSylTTM(sspls[39]);

			// 操盘必读
			body = HttpClientUtil.getInstance().doGet(
					"http://f10.eastmoney.com/OperationsRequired/Index?type=web&code=" + cid,
					"http://quote.eastmoney.com/" + cid + ".html", "f10.eastmoney.com");
			body = HttpClientUtil.getInstance().doGet(
					"http://f10.eastmoney.com/OperationsRequired/OperationsRequiredAjax?times=1&code=" + cid,
					"f10.eastmoney.com");
			JSONObject thdbobj = JSONObject.parseObject(body);

			// 核心题材
			JSONArray hxtcArray = JSONObject.parseArray(thdbobj.get("hxtc").toString());
			JSONObject hxtcObj = JSONObject.parseObject(hxtcArray.get(0).toString());
			// System.out.println("所属板块:" + hxtcObj.get("ydnr").toString());
			// System.out.println("指标名称:" + thdbobj.get("zxzb1").toString());
			jbmvo.setHxtc(hxtcObj.get("ydnr").toString());

			// 最新指标
			Document document = Jsoup.parse(thdbobj.get("zxzb1").toString());
			Elements trs = document.getElementsByTag("tr");
			Elements zxzbTds1 = trs.get(1).children();
			Elements zxzbTds2 = trs.get(2).children();
			Elements zxzbTds3 = trs.get(3).children();
			// System.out.println("每股收益(元)：" + zxzbTds1.get(1).text());
			// System.out.println("每股净资产(元)：" + zxzbTds1.get(3).text());
			// System.out.println("扣非每股收益(元)：" + zxzbTds2.get(1).text());
			jbmvo.setJbmgsy(zxzbTds1.get(1).text()); // 每股收益(元)
			jbmvo.setMgjzc(zxzbTds1.get(3).text()); // 每股净资产(元)
			jbmvo.setKfmgsy(zxzbTds2.get(1).text()); // 扣非每股收益(元)
			jbmvo.setZgb(zxzbTds2.get(5).text()); // 总股本(万股)
			jbmvo.setLtgb(zxzbTds3.get(5).text());// 流通股本(万股)

			document = Jsoup.parse(thdbobj.get("zxzb2").toString());
			trs = document.getElementsByTag("tr");
			zxzbTds3 = trs.get(1).children();
			// System.out.println("资产负债率(%)：" + zxzbTds3.get(7).text());
			jbmvo.setZcfzl(zxzbTds3.get(7).text()); // 资产负债率(%)

			// 净资产收益率(最新)
			JSONArray zyzbArray = JSONObject.parseArray(thdbobj.get("zyzb_abgq").toString());
			JSONObject zyzbObj = JSONObject.parseObject(zyzbArray.get(0).toString());
			jbmvo.setJzcsyl(zyzbObj.get("jqjzcsyl").toString());
			// System.out.println("净资产收益率(加权)(%):" + zyzbObj.get("jqjzcsyl"));

			// 往年
			// body =
			// HttpClientUtil.getInstance().doGet("http://emweb.securities.eastmoney.com/PC_HSF10/NewFinanceAnalysis/ZYZBAjaxNew?type=1&code="
			// + cid , "f10.eastmoney.com");
			// JSONObject jzcbj = JSONObject.parseObject(body);
			// JSONArray array2 =
			// JSONObject.parseArray(jzcbj.get("data").toString());
			// int ynum = array2.size() > 3 ? 3 : array2.size();
			// for (int i = 0; i < ynum; i++)
			// {
			// JSONObject ndata =
			// JSONObject.parseObject(array2.get(i).toString());
			// System.out.println(ndata.get("REPORT_DATE") + "：" +
			// ndata.get("ROEJQ"));
			// }

			// 基金持仓总数
			body = HttpClientUtil.getInstance().doGet(
					"http://emweb.securities.eastmoney.com/PC_HSF10/ShareholderResearch/PageAjax?code=" + cid,
					"f10.eastmoney.com");
			//System.out.println(body);
			JSONObject jsobj = JSONObject.parseObject(body);
			// System.out.println("基金持仓总数");
			// System.out.println(jsobj.get("zlcc").toString());
			JSONArray array = JSONObject.parseArray(jsobj.get("jgcc").toString());
			if (array.size() == 0) {
				// System.out.print(",");
				// buf.append("0");
				jbmvo.setCcjs("0"); // 基金持仓总数
				jbmvo.setCcjjzb(""); // 基金持仓比例
			} else {
				JSONObject ccjs = JSONObject.parseObject(array.get(1).toString());
				// System.out.println("基金持仓总数：" + ccjs.get("ccjs"));
				// buf.append(ccjs.get("ccjs"));

				jbmvo.setCcjjzb(ccjs.getString("TOTAL_SHARES_RATIO"));
				jbmvo.setCcjs(ccjs.getString("TOTAL_ORG_NUM"));
			}

			// 股东人数
			JSONArray gdrsArray = JSONObject.parseArray(jsobj.get("gdrs").toString());
			JSONObject gdrsObj = JSONObject.parseObject(gdrsArray.get(0).toString());
			jbmvo.setGdrs(gdrsObj.get("HOLDER_TOTAL_NUM").toString());
			jbmvo.setGdrs_jsqbh(gdrsObj.get("TOTAL_NUM_RATIO").toString());
			jbmvo.setRjltg(gdrsObj.get("AVG_FREE_SHARES").toString());
			// if(ValidateUtil.isNull(jbmvo.getQsdgdcghj()))
			// {

			// 十大流通股股东占比
			JSONArray ltgdccb = JSONObject.parseArray(jsobj.get("sdltgd").toString());
			//JSONObject zxltgdccb = JSONObject.parseObject(ltgdccb.get(0).toString());
			//System.out.println(jsobj.get("sdltgd").toString());
			//ltgdccb = JSONObject.parseArray(zxltgdccb.get("sdltgd").toString());
			Double sdltgzb = 0.0; // 十大流通股股东占比
			String hkzyjs = ""; // 香港中央结算有限公司
			Double sbcc = 0.0; // 全国社保基金
			for (Object obj : ltgdccb) {
				JSONObject zxgd = JSONObject.parseObject(obj.toString());
				Double gdzbl = Double.valueOf(zxgd.get("FREE_HOLDNUM_RATIO").toString().replace("%", ""));
				sdltgzb = DoubleUtil.sum(sdltgzb, gdzbl);

				if (zxgd.get("HOLDER_NAME").toString().startsWith("香港中央结算有限公司")) {
					hkzyjs = zxgd.get("FREE_HOLDNUM_RATIO").toString();
				}

				if (zxgd.get("HOLDER_NAME").toString().startsWith("全国社保基金")) {
					sbcc = DoubleUtil.sum(Double.valueOf(zxgd.get("FREE_HOLDNUM_RATIO").toString().replace("%", "")), sbcc);
				}
			}

			jbmvo.setQsdltgdcghj(sdltgzb.toString());
			jbmvo.setHkprice(hkzyjs);
			jbmvo.setGjjprice(sbcc.toString() + "%");

			// 十大股东占比
			JSONArray gdccb = JSONObject.parseArray(jsobj.get("sdgd").toString());
			//JSONObject zxgdccb = JSONObject.parseObject(gdccb.get(0).toString());
			// System.out.println(zxgdccb.get("sdltgd"));
			//gdccb = JSONObject.parseArray(zxgdccb.get("sdgd").toString());
			Double sdgdzb = 0.0;
			for (Object obj : gdccb) {
				JSONObject zxgd = JSONObject.parseObject(obj.toString());
				Double gdzbl = Double.valueOf(zxgd.get("HOLD_NUM_RATIO").toString().replace("%", ""));
				sdgdzb = DoubleUtil.sum(sdgdzb, gdzbl);
				// System.out.println(gdzbl);
			}
			// System.out.println(sdgdzb);
			jbmvo.setQsdgdcghj(sdgdzb.toString());
			// }

			// jbmvo.p_base();
			return jbmvo;

		} catch (Exception e) {
			System.out.println(cid);
			e.printStackTrace();
		}

		return jbmvo;
	}

	public static String getJiJinCount(String cid) {
		try {

			String body = HttpClientUtil.getInstance().doGet(
					"http://f10.eastmoney.com/f10_v2/ShareholderResearch.aspx?code=" + cid,
					"http://quote.eastmoney.com/" + cid + ".html", "f10.eastmoney.com");
			body = HttpClientUtil.getInstance().doGet(
					"http://f10.eastmoney.com/ShareholderResearch/ShareholderResearchAjax?code=" + cid,
					"f10.eastmoney.com");

			// System.out.println(body);
			JSONObject jsobj = JSONObject.parseObject(body);

			// 基金持仓总数
			// System.out.println("基金持仓总数");
			// System.out.println(jsobj.get("zlcc").toString());
			JSONArray array = JSONObject.parseArray(jsobj.get("zlcc").toString());
			JSONObject ccjs = JSONObject.parseObject(array.get(0).toString());

			StringBuffer buf = new StringBuffer();
			// 20210930资金持仓
			buf.append(ccjs.get("ccjs").toString());
			buf.append("#");
			buf.append(ccjs.get("zltgbl").toString());
			buf.append("#");

			// 20210630资金持仓
			body = HttpClientUtil.getInstance().doGet(
					"http://f10.eastmoney.com/ShareholderResearch/MainPositionsHodlerAjax?date=2021-06-30&code=" + cid,
					"f10.eastmoney.com");
			JSONArray _20201231Array = JSONObject.parseArray(body);
			JSONObject _20201231Obj = JSONObject.parseObject(_20201231Array.get(0).toString());
			buf.append(_20201231Obj.get("ccjs").toString());
			buf.append("#");
			buf.append(_20201231Obj.get("zltgbl").toString());
			buf.append("#");

			// 20210331资金持仓
			body = HttpClientUtil.getInstance().doGet(
					"http://f10.eastmoney.com/ShareholderResearch/MainPositionsHodlerAjax?date=2021-03-31&code=" + cid,
					"f10.eastmoney.com");
			JSONArray _20200930Array = JSONObject.parseArray(body);
			JSONObject _20200930Obj = JSONObject.parseObject(_20200930Array.get(0).toString());
			buf.append(_20200930Obj.get("ccjs").toString());
			buf.append("#");
			buf.append(_20200930Obj.get("zltgbl").toString());
			buf.append("#");

			// 20201231资金持仓
			body = HttpClientUtil.getInstance().doGet(
					"http://f10.eastmoney.com/ShareholderResearch/MainPositionsHodlerAjax?date=2020-12-31&code=" + cid,
					"f10.eastmoney.com");
			JSONArray _20200630Array = JSONObject.parseArray(body);
			JSONObject _20200630Obj = JSONObject.parseObject(_20200630Array.get(0).toString());
			buf.append(_20200630Obj.get("ccjs").toString());
			buf.append("#");
			buf.append(_20200630Obj.get("zltgbl").toString());
			buf.append("#");

			// 20200930资金持仓
			body = HttpClientUtil.getInstance().doGet(
					"http://f10.eastmoney.com/ShareholderResearch/MainPositionsHodlerAjax?date=2020-09-30&code=" + cid,
					"f10.eastmoney.com");
			JSONArray _20200331Array = JSONObject.parseArray(body);
			JSONObject _20200331Obj = JSONObject.parseObject(_20200331Array.get(0).toString());
			buf.append(_20200331Obj.get("ccjs").toString());
			buf.append("#");
			buf.append(_20200331Obj.get("zltgbl").toString());

			// System.out.println(buf.toString());
			return buf.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	static List<String> getAllGpStr() {
		return FileUtil.readToStringList(Config.DATA_DIR + "/data/allgp.txt", StringPool.UTF_8);
	}

	public static List<GuPiaoBaseVO> getAllGpVoList() {
		List<GuPiaoBaseVO> volist = new ArrayList<GuPiaoBaseVO>();

		List<String> gplist = FileUtil.readToStringList(Config.DATA_DIR + "/data/allgp.txt", StringPool.UTF_8);

		// List<String> gpcatelist =
		// FileUtil.readToStringList(Config.DATA_DIR +
		// "\\data\\gpcate.txt", StringPool.UTF_8);

		// Map<String, String> catemap = new HashMap<String, String>();
		// for (String catestr : gpcatelist)
		// {
		// if(ValidateUtil.isNull(catestr))
		// {
		// continue;
		// }
		// //System.out.println(catestr);
		// GuPiaoBaseVO vo = RunUtils.getGuPiaoBaseVO(catestr);
		// catemap.put(vo.getCid(), vo.getCate());
		// }

		for (int i = 0; i < gplist.size(); i++) {
			String txt = gplist.get(i);
			// System.out.println(txt);
			GuPiaoBaseVO vo = getGuPiaoBaseVO(txt);
			// vo.setCate(catemap.get(vo.getCid()));
			volist.add(vo);
		}

		return volist;
	}

	// 生成分类下的股票
	public static void buildAllGPCateFile() {
		try {

			List<String> allcate = FileUtil.readToStringList(Config.DATA_DIR + "/data/cate.txt", StringPool.UTF_8);
			System.out.println(allcate.size());

			// 所有分类
			// for (int i = 0 ; i < allcate.size(); i++)
			// {
			// String cate = allcate.get(i);
			// HangYeVO vo = new HangYeVO();
			// vo.valueOf(cate);
			//
			// String file = Config.DATA_DIR + "\\data\\catelist\\" +
			// vo.getCid() + "-" + vo.getCname() + ".txt";
			// List<String> gpcatelist = FileUtil.readToStringList(file,
			// StringPool.UTF_8);
			// if(vo.getNum() != gpcatelist.size())
			// {
			// System.out.println(vo.toCateString() + "-" + gpcatelist.size());
			//
			// buildGPCateFile(TYPE_CATE, cate);
			// }
			// }

			for (int i = 0; i < allcate.size(); i++) {
				String cate = allcate.get(i);
				buildGPCateFile(TYPE_CATE, cate);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getCateId(String cname)
	{
		try {

			if(cmap.isEmpty())
			{
				List<String> allcate = FileUtil.readToStringList(Config.DATA_DIR + "/data/cate.txt", StringPool.UTF_8);
				System.out.println(allcate.size());
				// 所有分类
				for (int i = 0 ; i < allcate.size(); i++)
				{
					String cate = allcate.get(i);
					HangYeVO vo = new HangYeVO();
					vo.valueOf(cate);
					cmap.put(vo.getCname(), vo.getCid());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cmap.get(cname);
	}
	
	public static void buildGPCateFile(String type, String cate) {
		try {

			Map<String, String> cmap = new HashMap<String, String>();

			String cid = cate.split("#")[0];
			String cname = cate.split("#")[1];

			String flist = "catelist";
			// 分类
			String url = "http://q.10jqka.com.cn/thshy/detail/code/" + cid + "/";
			if (TYPE_GN.equals(type)) {
				// 概念
				url = "http://q.10jqka.com.cn/gn/detail/code/" + cid + "/";
				flist = "gnlist";
			}

			// System.out.println(url);

			String html = RunHtmlUnit.getInstance().getDocument(url);
			// System.out.println(html);

			Document document = Jsoup.parse(html);
			Element inputElements = document.getElementById("maincont");
			Elements trs = inputElements.getElementsByTag("tr");
			for (Element trment : trs) {
				Elements tds = trment.children();
				String id = tds.get(1).text();
				String name = tds.get(2).text();
				if ("代码".equals(id)) {
					continue;
				}
				System.out.println(id + "#" + name + "#" + cname);
				cmap.put(id, id + "#" + name + "#" + cname);
			}

			System.out.println("第【1】页采集完成");
			Element mpele = document.getElementById("m-page");
			// System.out.println(mpele);
			if (ValidateUtil.isNotNull(mpele.text())) {
				Elements pageEles = document.getElementsByClass("page_info");
				int maxpages = Integer.valueOf(pageEles.get(0).text().split("/")[1]);
				System.out.println("共需采集【" + maxpages + "】页");
				for (int num = 1; num < maxpages; num++) {
					System.out.println("现采集【" + (1 + num) + "】页");

					html = RunHtmlUnit.getInstance().nextPage("下一页");
					// System.out.println("[" + html + "]");
					if (ValidateUtil.isNull(html)) {
						// System.out.println("来了" + num);
						continue;
					}
					document = Jsoup.parse(html);
					inputElements = document.getElementById("maincont");
					trs = inputElements.getElementsByTag("tr");

					System.out.println("现采集【" + (1 + num) + "】 页采集结果");
					for (Element trment : trs) {
						Elements tds = trment.children();
						if (tds.size() == 1) {
							continue;
						}
						String id = tds.get(1).text();
						String name = tds.get(2).text();
						if ("代码".equals(id)) {
							continue;
						}
						System.out.println(id + "#" + name + "#" + cname);
						cmap.put(id, id + "#" + name + "#" + cname);
					}
					System.out.println("现采集【" + (1 + num) + "】 页采集结束");
				}
			}

			// 输出文件
			StringBuffer buf = new StringBuffer();
			for (String key : cmap.keySet()) {
				System.out.println(cmap.get(key));
				buf.append(cmap.get(key)).append("\n");
			}

			String file = Config.DATA_DIR + "/data/" + flist + "/" + cid + "-" + cname + ".txt";
			System.out.println(cate + "所以采集成功 及 结果 共计：【" + cmap.keySet().size() + "】条");
			if (FileUtil.isExist(file)) {
				// System.out.println("del" + cate);
				// return ;
				FileUtil.removeFile(file, true);
			}

			FileUtil.writeFile(file, buf.toString());

			buf = null;
			buf = new StringBuffer();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void mergeAllGP() {
		String filedir = Config.DATA_DIR + "/data/catelist/";
		if (!FileUtil.isExist(filedir)) {
			return;
		}

		File files = new File(filedir);

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < files.list().length; i++) {
			// System.out.println(i);
			String file = files.list()[i];
			// System.out.println((i +1) + "-" + file);
			String rfstr = FileUtil.readLine(filedir + "/" + file, "UTF-8");
			System.out.println(rfstr);
			buf.append(rfstr);
		}

		String gpcatefile = Config.DATA_DIR + "/data/gpcate.txt";

		try {
			FileUtil.removeFile(gpcatefile, true);
			FileUtil.writeFile(gpcatefile, buf.toString(), StringPool.UTF_8);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	public static List<GuPiaoBaseVO> getGpList(String file) {
		List<GuPiaoBaseVO> gplist = new ArrayList<GuPiaoBaseVO>();

		if (FileUtil.isExist(file)) {
			try {

				List<String> yistrlist = FileUtil.readToStringList(file, StringPool.UTF_8);
				for (String yistr : yistrlist) {
					// System.out.println(yistr);
					GuPiaoBaseVO vo = new GuPiaoBaseVO();
					vo.valueOf(yistr);
					gplist.add(vo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return gplist;
	}

	public static void buildZZ500() {
		String yyfile = Config.DATA_DIR + "/data/zz500.txt";

		List<GuPiaoBaseVO> yilist = new ArrayList<GuPiaoBaseVO>();
		List<String> yistrlist = FileUtil.readToStringList(yyfile, "UTF-8");
		for (String yistr : yistrlist) {
			System.out.println(yistr);
			GuPiaoBaseVO vo = new GuPiaoBaseVO();
			vo.valueOf(yistr);
			if (ValidateUtil.isNull(vo.getCate())) {
				vo.setCate(getCate(vo.getCid()));
			}
			yilist.add(vo);
		}

		StringBuffer buf = new StringBuffer();

		for (GuPiaoBaseVO vo : yilist) {
			buf.append(vo.toString()).append("\n");
		}

		FileUtil.writeFile(yyfile, buf.toString(), StringPool.UTF_8);
	}

	// 生成所有股票
	public static void buildAllGPFile() {
		try {

			List<String> allgp = getAllGpStr();
			System.out.println(allgp.size());

			Map<String, String> map = new HashMap<String, String>();
			for (String gp : allgp) {
				System.out.println(gp);
				map.put(gp.split("#")[0], gp);
			}

			String body = HttpClientUtil.getInstance().doGet("http://data.10jqka.com.cn/ipo/xgsr/", "", "data.10jqka.com.cn");
			Document document = Jsoup.parse(body);
			Element el = document.getElementById("datacenter_change_content");
			Elements trs = el.getElementsByTag("tr");
			for (Element trment : trs) {
				Elements tds = trment.children();
				if (tds.get(1).text().toString().startsWith("股票代码")) {
					continue;
				}

				if (map.containsKey(tds.get(1).text().toString())) {
					continue;
				}
				System.out.println(tds.get(1).text() + "#" + tds.get(2).text());
				allgp.add(tds.get(1).text() + "#" + tds.get(2).text());
			}

			System.out.println(allgp.size());

			StringBuffer buf = new StringBuffer();
			for (String gp : allgp) {
				buf.append(gp).append("\n");
			}

			FileUtil.writeFile(Config.DATA_DIR + "/data/allgp.txt", buf.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 生成分类文件
	public static void buildAllCateFile() {
		try {

			StringBuffer buf = new StringBuffer();

			String url = "http://q.10jqka.com.cn/thshy/";
			String html = RunHtmlUnit.getInstance().getDocument(url);
			Document document = Jsoup.parse(html);
			// Element inputElements = document.getElementById("maincont");
			Elements trs = document.getElementsByClass("cate_items");
			for (Element trment : trs) {

				// Document chdDoc = Jsoup.parse(trment.html());
				Elements as = trment.select("a");

				for (Element td : as) {
					String cid = td.attr("href").replace("http://q.10jqka.com.cn/thshy/detail/code/", "").replace("/",
							"");
					String cname = td.text();
					buf.append(cid + "#" + cname).append("\n");
					System.out.println(cid + "#" + cname);
				}
			}

			FileUtil.writeFile(Config.DATA_DIR + "/data/cate.txt", buf.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getAllCateFile() {
		try {

			List<HangYeVO> list = new ArrayList<HangYeVO>();

			StringBuffer buf = new StringBuffer();

			String url = "http://q.10jqka.com.cn/thshy/";
			String html = RunHtmlUnit.getInstance().getDocument(url);
			Document document = Jsoup.parse(html);

			Element inputElements = document.getElementById("maincont");
			Elements trs = inputElements.getElementsByTag("tr");

			Integer count = 0;

			for (Element trment : trs) {
				Elements tds = trment.children();

				String cid = tds.get(1).html().replace("<a href=\"http://q.10jqka.com.cn/thshy/detail/code/", "")
						.replace("/\" target=\"_blank\"> " + tds.get(1).text() + " </a>", "");

				if (cid.equals("板块")) {
					continue;
				}

				String cname = tds.get(1).text();
				// Integer sum = Integer.valueOf(tds.get(6).text()) +
				// Integer.valueOf(tds.get(7).text());
				System.out.println(cid + "#" + cname);

				HangYeVO vo = new HangYeVO();
				vo.setCid(cid);
				vo.setCname(cname);
				list.add(vo);
			}

			Element mpele = document.getElementById("m-page");

			if (ValidateUtil.isNotNull(mpele.text())) {
				Elements pageEles = document.getElementsByClass("page_info");
				int maxpages = Integer.valueOf(pageEles.get(0).text().split("/")[1]);
				for (int num = 1; num < maxpages; num++) {
					html = RunHtmlUnit.getInstance().nextPage("下一页");
					if (ValidateUtil.isNull(html)) {
						continue;
					}
					document = Jsoup.parse(html);
					inputElements = document.getElementById("maincont");
					trs = inputElements.getElementsByTag("tr");

					for (Element trment : trs) {
						Elements tds = trment.children();
						if (tds.size() == 1) {
							continue;
						}

						String cid = tds.get(1).html()
								.replace("<a href=\"http://q.10jqka.com.cn/thshy/detail/code/", "")
								.replace("/\" target=\"_blank\"> " + tds.get(1).text() + " </a>", "");

						if (cid.equals("板块")) {
							continue;
						}

						String cname = tds.get(1).text();

						System.out.println(cid + "#" + cname);

						HangYeVO vo = new HangYeVO();
						vo.setCid(cid);
						vo.setCname(cname);
						list.add(vo);
					}
				}
			}

			for (int i = 0; i < list.size(); i++) {
				HangYeVO vo = list.get(i);

				if (i < 70) {
					System.out.println(i + ":" + vo.toCateString());
					continue;
				}

				String urlstr = "http://q.10jqka.com.cn/thshy/detail/code/" + vo.getCid() + "/";
				System.out.println("采集【" + vo.getCname() + "】数量 - " + urlstr);
				String body = RunHtmlUnit.getInstance().getDocument(urlstr);
				document = Jsoup.parse(body);
				inputElements = document.getElementById("maincont");
				trs = inputElements.getElementsByTag("tr");
				Element trment = trs.last();
				Elements tds = trment.children();

				mpele = document.getElementById("m-page");

				if (mpele.text().contains("尾页")) {
					body = RunHtmlUnit.getInstance().nextPage("尾页");

					document = Jsoup.parse(body);
					inputElements = document.getElementById("maincont");
					trs = inputElements.getElementsByTag("tr");
					trment = trs.last();
					tds = trment.children();
					if ("暂无成份股数据".equals(tds.get(0).text())) {
						body = RunHtmlUnit.getInstance().nextPage("上一页");

						document = Jsoup.parse(body);
						inputElements = document.getElementById("maincont");
						trs = inputElements.getElementsByTag("tr");
						trment = trs.last();
						tds = trment.children();
					}
				}

				Integer num = Integer.valueOf(tds.get(0).text());

				count += num;

				vo.setNum(num);

				buf.append(vo.toCateString()).append("\n");
				System.out.println(vo.toCateString());
			}

			System.out.println("一共【" + count + "】只票");
			FileUtil.writeFile(Config.DATA_DIR + "/data/cate.txt", buf.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取股票实时信息
	 * @param cid 股票ID
	 */
	public static GuPiaoInfoVO getGuPiaoInfo(String cid)
	{
		GuPiaoInfoVO ifvo = new GuPiaoInfoVO();
		
		try {
//			  0: 未知
//			  1: 股票名字
//			  2: 股票代码
//			  3: 当前价格
//			  4: 昨收
//			  5: 今开
//			  6: 成交量（手）
//			  7: 外盘
//			  8: 内盘
//			  9: 买一
//			 10: 买一量（手）
//			 11-18: 买二 买五
//			 19: 卖一
//			 20: 卖一量
//			 21-28: 卖二 卖五
//			 29: 最近逐笔成交
//			 30: 时间
//			 31: 涨跌
//			 32: 涨跌%
//			 33: 最高
//			 34: 最低
//			 35: 价格/成交量（手）/成交额
//			 36: 成交量（手）
//			 37: 成交额（万）
//			 38: 换手率
//			 39: 市盈率
//			 40: 
//			 41: 最高
//			 42: 最低
//			 43: 振幅
//			 44: 流通市值
//			 45: 总市值
//			 46: 市净率
//			 47: 涨停价
//			 48: 跌停价
			
			String s_getbodys = HttpClientUtil.getInstance().doGet("http://qt.gtimg.cn/q=" + cid);
			String[] sspls = s_getbodys.split("~");
			
			String name  = sspls[1];
			Double ztspj = Double.valueOf(sspls[4]);  // 昨天收盘价
			Double jtkpj = Double.valueOf(sspls[5]);  // 今天开盘价
			Double jtzdj = Double.valueOf(sspls[34]); // 今天最低价
			Double jtzgj = Double.valueOf(sspls[33]); // 今天最高价
			Double jtssj = Double.valueOf(sspls[3]);  // 今天当前价
			Double jtspj = Double.valueOf(sspls[3]);  // 今天收盘价
			Double zdf   = Double.valueOf(sspls[32]); // 当前涨跌幅
			Double cje   = DoubleUtil.div(Double.valueOf(sspls[37]), 10000.0, 2); // 成交额（亿）
			Double zsz   = Double.valueOf(sspls[45]); // 总市值
			Double ltsz  = Double.valueOf(sspls[44]); // 流通市值
			Double hsl   = Double.valueOf(sspls[38]); // 换手率
			Double syl   = Double.valueOf(sspls[39]); // 市盈率
			Double sjl   = Double.valueOf(sspls[46]); // 市净率
			
			ifvo.setCid(cid);
			ifvo.setName(name);
			ifvo.setZtspj(ztspj);
			ifvo.setJtkpj(jtkpj);
			ifvo.setJtzdj(jtzdj);
			ifvo.setJtzgj(jtzgj);
			ifvo.setJtssj(jtssj);
			ifvo.setJtspj(jtspj);
			ifvo.setZdf(zdf);
			ifvo.setCje(cje);
			ifvo.setZsz(zsz);
			ifvo.setLtsz(ltsz);
			ifvo.setHsl(hsl);
			ifvo.setSyl(syl);
			ifvo.setSjl(sjl);
			
			// 涨跌幅小于9.8 过滤
			if ((int) (Double.valueOf(zdf) * 1000) >= 98)
			{
				ifvo.setIszt(true);
			}

			if(ifvo.is3068())
			{
				if ((int) (Double.valueOf(zdf) * 1000) >= 190)
				{
					ifvo.setIszt(true);
				}
			}
			
			//跌停
			if (zdf.compareTo(-9.80) < 0 && zdf.compareTo(-10.11) > 0)
			{
				ifvo.setIsdt(true);
			}

			if(ifvo.is3068())
			{
				if (zdf.compareTo(-19.00) < 0)
				{
					ifvo.setIsdt(true);
				}
			}
			
			Double kpzdf = DoubleUtil.mul(DoubleUtil.div(DoubleUtil.sub(jtkpj, ztspj, 2) , ztspj , 4) , 100 , 2);
			ifvo.setKpzdf(kpzdf);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ifvo;
	}
	
	/**
	 * 补仓金额计算
	 */
	public static Double countBcje(Double dqsz, Double ksje)
	{
		Double ftb  = 0.03;   // 反弹比
		
		Double jcje = DoubleUtil.sub(DoubleUtil.div(ksje, ftb, 0) , dqsz);
	
		System.out.println("补仓金额：" + jcje);
		
		return jcje;
	}
	
}
