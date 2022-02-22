package com.huangxifeng.gupiao.run;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.huangxifeng.core.utils.DateUtil;
import com.huangxifeng.core.utils.DoubleUtil;
import com.huangxifeng.core.utils.FileUtil;
import com.huangxifeng.core.utils.HttpClientUtil;
import com.huangxifeng.core.utils.StringPool;
import com.huangxifeng.core.utils.ValidateUtil;
import com.huangxifeng.gupiao.jymodel.RunZhangTingDiXi;
import com.huangxifeng.gupiao.vo.CaiWuVO;
import com.huangxifeng.gupiao.vo.GuPiaoBaseVO;
import com.huangxifeng.gupiao.vo.GuPiaoInfoVO;
import com.huangxifeng.gupiao.vo.JiBenMianVO;
import com.huangxifeng.gupiao.vo.ZhangDieBiVO;
import com.huangxifeng.gupiao.vo.ZhangTingDiXiVO;

public class RunDatas {

	static Double _cxj = 2.0;

	public static void main(String[] args) {
		
		// 2005-2107涨3倍以上的股
		// run3BeiGu();

		// 业绩选股
		// runYejiGP();

		// 中短业绩线
		// runFilterGP();

		// 行业股票
		// 龙头 市值TOP3
		// 业绩增长100%
		// 5年业绩全正
		// runHangYeGP("881164-传媒.txt");
		// runHangYeGP("881166-国防军工.txt");
		// runHangYeGP("881126-汽车零部件.txt");
		// runHangYeGP("881121-半导体及元件.txt");
		// runHangYeGP("308760-绿色电力.txt");
		// runHangYeGP("306380-储能.txt");
		// runHangYeGP("300200-风电.txt");
		// runHangYeGP("301079-光伏概念.txt");
		// runHangYeGP("300008-新能源车.txt");
		// runHangYeGP("300733-锂电池.txt");
		// runHangYeGP("308712-医美.txt");

		// runHangYeGP("881141-中药.txt");
		//runHangYeGP("881181-环保.txt");

		//runJBMDatas();

		// 60天，30天涨跌比
		// zhangdiefu();

		// 翻倍股
		// runFanBeiGP();

		// 底背离股票
		//runDibeiliGP();

		// 涨停回撤股
		//runZhangtingHuiceGP();
		
		//这里都是交易模式的数据 个股
		
		
		// 涨停龙头低吸
		RunDatas.runLongtouDixiGP(true);  //我主要做这个
		
//		// 涨停列表
//		RunUtils.getZhangTingList();
//		
//		// 跌停列表
//		RunUtils.getDieTingList();
//		
//		//高板股票 15天5天涨停列表
//		RunDatas.runGaoBanGP();
//		
//		//行业监控票（15天5次涨停，3天1次涨停）
//		RunDatas.runHangYeJKGP();

		// 大跌震荡行情股票
		//runZhengdangGP();

		// 大票
		// runDaGP();
		
		//近10天内有连续2-3天涨停且往前数60天没有涨停的票+最后一次涨停回调比例超过10%+今天收了十字星的票(正负3个点以内都可以)
		//runZt23HuiceGP();
		

	}

	public static void runYejiGP() {
		try {

			int num = 0;

			String file = StringPool.PROJECT_DIR + "/data/1yilist/all.txt";
			List<GuPiaoBaseVO> list = RunUtils.getGpList(file);

			for (int i = 0; i < list.size(); i++) {
				GuPiaoBaseVO vo = list.get(i);

				// System.out.println(vo.toString());

				// 上市天数>100
				int upnum = RunUtils.getUpday(vo.getCid());
				if (upnum < 100) {
					continue;
				}

				// 20天平均交易量>4亿
				Double liangAve = RunUtils.getDayLiangAve(vo.getCid(), 20);
				if (liangAve < 4) {
					System.out.println("20天平均交易量【" + liangAve + "】<4亿" + vo.toBaseString());
					continue;
				}

				// 68天内必须涨停>1
				ZhangDieBiVO zdbvo = RunUtils.getZdb(vo.getCid(), 68);
				if (zdbvo.getZt() < 1) {
					System.out.println("68天内必须涨停>1" + vo.toBaseString());
					continue;
				}

				// 60亿>总市值>6000亿过滤
				Double zsz = RunUtils.getZsz(vo.getCid());
				if (zsz.compareTo(10000.0) > 0 || zsz.compareTo(50.0) < 0) {
					System.out.println("50亿>总市值【" + zsz + "】>10000亿过滤：" + vo.toBaseString());
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
					System.out.println("连续5年扣非净利润>0元" + vo.toBaseString());
					continue;
				}

				// 最近财务更新
				CaiWuVO cwvo = cwlist.get(0);
				if (Double.valueOf(cwvo.getKfjlr()) < 0) {
					System.out.println("最近财务扣非净利润【" + cwvo.getKfjlr() + "】<0：" + vo.toBaseString());
					continue;
				}
				// cwvo.p();

				// 营收增长>10%
				if (Double.valueOf(cwvo.getYyzsrtbzz()).compareTo(10.0) < 0) {
					System.out.println("营收增长【" + cwvo.getYyzsrtbzz() + "】>10%：" + vo.toBaseString());
					continue;
				}

				// 净利润或扣非净利润增长比>80%
				if (Double.valueOf(cwvo.getKfjlrtbzz()).compareTo(80.0) < 0
						|| Double.valueOf(cwvo.getGsjlrtbzz()).compareTo(60.0) < 0) {
					System.out.println("净利润【" + cwvo.getGsjlrtbzz() + "】或扣非净利润【" + cwvo.getKfjlrtbzz() + "】增长比>80%："
							+ vo.toBaseString());
					continue;
				}

				// 净利润率（%） > 7 && 扣非净利润率（%） > 7
				// Double jdjlr =
				// DoubleUtil.div(Double.valueOf(cwvo.getGsjlr()), 100000000.0,
				// 2);
				// Double jdkfjlr =
				// DoubleUtil.div(Double.valueOf(cwvo.getKfjlr()), 100000000.0,
				// 2);
				Double jlrl = DoubleUtil.div(Double.valueOf(cwvo.getGsjlr()), Double.valueOf(cwvo.getYyzsr()), 2);
				Double kfjlrl = DoubleUtil.div(Double.valueOf(cwvo.getKfjlr()), Double.valueOf(cwvo.getYyzsr()), 2);
				if (jlrl.compareTo(0.08) < 0 || kfjlrl.compareTo(0.08) < 0) {
					System.out
							.println("净利润率（%）【" + jlrl + "】 > 8 && 扣非净利润率（%） 【" + kfjlrl + "】> 8：" + vo.toBaseString());
					continue;
				}

				// 年营业额>10亿
				// Double jdyye =
				// DoubleUtil.div(Double.valueOf(cwvo.getYyzsr()), 100000000.0,
				// 2);
				// Double nyye = DoubleUtil.mul(jdyye, _cxj, 2);
				// if(nyye.compareTo(10.0) < 0)
				// {
				// continue;
				// }

				// 年营业额/总市值>10%
				// Double nyyzszb = DoubleUtil.div(nyye, zsz, 2);
				// if(nyyzszb.compareTo(0.1) < 0)
				// {
				// continue;
				// }

				// 扣非净利润
				// 扣非净利润/总市值>1% * 扣非增长比>300%
				// Double nkfjlr =
				// DoubleUtil.div(DoubleUtil.mul(Double.valueOf(cwvo.getKfjlr()),
				// _cxj, 2), 100000000.0, 2);
				// Double kfjlrzszb = DoubleUtil.div(nkfjlr, zsz, 2);
				// if(kfjlrzszb.compareTo(0.01) < 0)
				// {
				// continue;
				// }
				// if(kfjlrzszb.compareTo(0.01) == 0 &&
				// Double.valueOf(cwvo.getKfjlrtbzz()).compareTo(300.0) < 0)
				// {
				// continue;
				// }
				Double kfjlr = DoubleUtil.div(Double.valueOf(cwvo.getKfjlr()), 100000000.0, 2);
				if (kfjlr.compareTo(4.0) < 0) {
					System.out.println("净利润 < 4亿：" + vo.toBaseString());
					continue;
				}

				// 基本面
				// JiBenMianVO jbmvo = RunUtils.getJiBenMianString(vo.getCid());

				// //资产负债率<60%
				// if(Double.valueOf(jbmvo.getZcfzl()).compareTo(60.0) > 0)
				// {
				// //System.out.println("资产负债率>60%" + vo.toBaseString());
				// //continue;
				// }
				//
				// //十大股东占比>50% & 流通股东占比 > 50%
				// if(Double.valueOf(jbmvo.getQsdgdcghj()).compareTo(70.0) < 0
				// || Double.valueOf(jbmvo.getQsdltgdcghj()).compareTo(70.0) <
				// 0)
				// {
				//
				// System.out.println("流通【" + jbmvo.getQsdltgdcghj() + "】 < 70%
				// && 十大【" + jbmvo.getQsdgdcghj() + "】 < 70% ：" +
				// vo.toBaseString());
				// //System.out.println("十大股东占比【" + jbmvo.getQsdgdcghj() +
				// "】<50%：" + vo.toBaseString());
				// continue;
				// }
				// if(Double.valueOf(jbmvo.getQsdltgdcghj()).compareTo(70.0) <
				// 0)
				// {
				// //System.out.println("流通股东占比【" + jbmvo.getQsdltgdcghj() + "】
				// < 50%：" + vo.toBaseString());
				// //continue;
				// }

				// 30天内涨跌比
				// ZhangDieBiVO _30zdbvo = RunUtils.getZdb(vo.getCid(), 30);

				// 换手率>1%
				// Double hsl = DoubleUtil.div(liangAve, zsz, 2);
				// if(hsl.compareTo(0.01) < 0)
				// {
				// System.out.println(hsl);
				// continue;
				// }

				System.out.println(vo.toString());
				num++;
			}

			System.out.println("一共：" + num + "条数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 中短业绩线
	public static void runFilterGP() {
		try {

			int num = 0;

			// String file = StringPool.PROJECT_DIR + "\\data\\zz500.txt";
			String file = StringPool.PROJECT_DIR + "/data/1yilist/all.txt";
			// String file = StringPool.PROJECT_DIR + "\\data\\hygp.txt";
			List<GuPiaoBaseVO> list = RunUtils.getGpList(file);

			for (int i = 0; i < list.size(); i++) {
				GuPiaoBaseVO vo = list.get(i);

				// System.out.println(vo.toString());

				// 上市天数>100
				int upnum = RunUtils.getUpday(vo.getCid());
				if (upnum < 80) {
					continue;
				}

				// 25天平均交易量>1.3亿
				// Double liangAve = RunUtils.getDayLiangAve(vo.getCid(), 15);
				// if(liangAve < 5)
				// {
				// System.out.println("25天平均交易量【" + liangAve + "】<5亿" +
				// vo.toBaseString());
				// continue;
				// }

				// 68天内必须涨停>1
				ZhangDieBiVO zdbvo = RunUtils.getZdb(vo.getCid(), 68);
				if (zdbvo.getZt() < 1) {
					System.out.println("68天内必须涨停>1" + vo.toBaseString());
					continue;
				}

				// 60亿>总市值>6000亿过滤
				Double zsz = RunUtils.getZsz(vo.getCid());
				if (zsz.compareTo(10000.0) > 0 || zsz.compareTo(50.0) < 0) {
					System.out.println("50亿>总市值【" + zsz + "】>10000亿过滤：" + vo.toBaseString());
					continue;
				}

				// 上市20年市值<70亿过滤
				// if((upnum/200) > 20 && zsz.compareTo(70.0) < 0)
				// {
				// //System.out.println("上市20年市值<70亿过滤" + vo.toBaseString());
				// //continue;
				// }

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
					System.out.println("连续5年扣非净利润>0元" + vo.toBaseString());
					continue;
				}

				// 最近财务更新
				CaiWuVO cwvo = cwlist.get(0);
				// if(Double.valueOf(cwvo.getKfjlr()) < 0)
				// {
				// System.out.println("最近财务扣非净利润【" + cwvo.getKfjlr() + "】<0：" +
				// vo.toBaseString());
				// continue;
				// }
				// cwvo.p();

				// 营收增长>25%
				if (Double.valueOf(cwvo.getYyzsrtbzz()).compareTo(20.0) < 0) {
					System.out.println("营收增长【" + cwvo.getYyzsrtbzz() + "】>20%：" + vo.toBaseString());
					continue;
				}

				// 净利润或扣非净利润增长比>50%
				if (Double.valueOf(cwvo.getKfjlrtbzz()).compareTo(100.0) < 0
						|| Double.valueOf(cwvo.getGsjlrtbzz()).compareTo(100.0) < 0) {
					System.out.println("净利润【" + cwvo.getGsjlrtbzz() + "】或扣非净利润【" + cwvo.getKfjlrtbzz() + "】增长比>100%："
							+ vo.toBaseString());
					continue;
				}

				// 净利润率（%） > 7 && 扣非净利润率（%） > 7
				// Double jdjlr =
				// DoubleUtil.div(Double.valueOf(cwvo.getGsjlr()), 100000000.0,
				// 2);
				// Double jdkfjlr =
				// DoubleUtil.div(Double.valueOf(cwvo.getKfjlr()), 100000000.0,
				// 2);
				Double jlrl = DoubleUtil.div(Double.valueOf(cwvo.getGsjlr()), Double.valueOf(cwvo.getYyzsr()), 2);
				Double kfjlrl = DoubleUtil.div(Double.valueOf(cwvo.getKfjlr()), Double.valueOf(cwvo.getYyzsr()), 2);
				if (jlrl.compareTo(0.1) < 0 || kfjlrl.compareTo(0.1) < 0) {
					System.out.println(
							"净利润率（%）【" + jlrl + "】 > 10 && 扣非净利润率（%） 【" + kfjlrl + "】> 10：" + vo.toBaseString());
					continue;
				}

				// 年营业额>10亿
				// Double jdyye =
				// DoubleUtil.div(Double.valueOf(cwvo.getYyzsr()), 100000000.0,
				// 2);
				// Double nyye = DoubleUtil.mul(jdyye, _cxj, 2);
				// if(nyye.compareTo(10.0) < 0)
				// {
				// continue;
				// }

				// 年营业额/总市值>10%
				// Double nyyzszb = DoubleUtil.div(nyye, zsz, 2);
				// if(nyyzszb.compareTo(0.1) < 0)
				// {
				// continue;
				// }

				// 扣非净利润
				// 扣非净利润/总市值>1% * 扣非增长比>300%
				// Double nkfjlr =
				// DoubleUtil.div(DoubleUtil.mul(Double.valueOf(cwvo.getKfjlr()),
				// _cxj, 2), 100000000.0, 2);
				// Double kfjlrzszb = DoubleUtil.div(nkfjlr, zsz, 2);
				// if(kfjlrzszb.compareTo(0.01) < 0)
				// {
				// continue;
				// }
				// if(kfjlrzszb.compareTo(0.01) == 0 &&
				// Double.valueOf(cwvo.getKfjlrtbzz()).compareTo(300.0) < 0)
				// {
				// continue;
				// }
				Double kfjlr = DoubleUtil.div(Double.valueOf(cwvo.getKfjlr()), 100000000.0, 2);
				if (kfjlr.compareTo(5.0) < 0) {
					System.out.println("净利润 < 5亿：" + vo.toBaseString());
					continue;
				}

				// 基本面
				// JiBenMianVO jbmvo = RunUtils.getJiBenMianString(vo.getCid());

				// //资产负债率<60%
				// if(Double.valueOf(jbmvo.getZcfzl()).compareTo(60.0) > 0)
				// {
				// //System.out.println("资产负债率>60%" + vo.toBaseString());
				// //continue;
				// }
				//
				// //十大股东占比>50% & 流通股东占比 > 50%
				// if(Double.valueOf(jbmvo.getQsdgdcghj()).compareTo(70.0) < 0
				// || Double.valueOf(jbmvo.getQsdltgdcghj()).compareTo(70.0) <
				// 0)
				// {
				//
				// System.out.println("流通【" + jbmvo.getQsdltgdcghj() + "】 < 70%
				// && 十大【" + jbmvo.getQsdgdcghj() + "】 < 70% ：" +
				// vo.toBaseString());
				// //System.out.println("十大股东占比【" + jbmvo.getQsdgdcghj() +
				// "】<50%：" + vo.toBaseString());
				// continue;
				// }
				// if(Double.valueOf(jbmvo.getQsdltgdcghj()).compareTo(70.0) <
				// 0)
				// {
				// //System.out.println("流通股东占比【" + jbmvo.getQsdltgdcghj() + "】
				// < 50%：" + vo.toBaseString());
				// //continue;
				// }

				// 30天内涨跌比
				// ZhangDieBiVO _30zdbvo = RunUtils.getZdb(vo.getCid(), 30);

				// 换手率>1%
				// Double hsl = DoubleUtil.div(liangAve, zsz, 2);
				// if(hsl.compareTo(0.01) < 0)
				// {
				// System.out.println(hsl);
				// continue;
				// }

				System.out.println(vo.toString());
				num++;
			}

			System.out.println("一共：" + num + "条数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 行业股票
	public static void runHangYeGP(String cate) {
		try {

			int num = 0;

			String file = StringPool.PROJECT_DIR + "/data/catelist/" + cate;
			if (cate.startsWith("30")) {
				file = StringPool.PROJECT_DIR + "/data/gnlist/" + cate;
			}

			List<GuPiaoBaseVO> list = RunUtils.getGpList(file);
			StringBuffer buf = new StringBuffer();

			for (int i = 0; i < list.size(); i++) {
				GuPiaoBaseVO vo = list.get(i);

				if(!vo.isOK() || vo.is3068())
				{
					continue;
				}

				// System.out.println(vo.toString());

				// 上市天数>100
				int upnum = RunUtils.getUpday(vo.getCid());
				if (upnum < 100) {
					System.out.println("上市天数【" + upnum + "】<100天过滤：" + vo.toBaseString());
					continue;
				}

				// 25天平均交易量>3.0亿
				Double liangAve = RunUtils.getDayLiangAve(vo.getCid(), 25);
				if (liangAve < 1.0) {
					System.out.println("25天平均交易量【" + liangAve + "】<1.0亿过滤：" + vo.toBaseString());
					continue;
				}

				// 30亿>总市值>10000亿过滤
				Double zsz = RunUtils.getZsz(vo.getCid());
				if (zsz.compareTo(10000.0) > 0 || zsz.compareTo(50.0) < 0) {
					System.out.println("60亿>总市值【" + zsz + "】>10000亿过滤：" + vo.toBaseString());
					continue;
				}

				// 连续5年扣非净利润>0元
				//boolean isCwFu = true;
				List<CaiWuVO> cwlist = RunUtils.getCaiWuList(vo.getCid());
				for (CaiWuVO caiWuVO : cwlist) {
					if (Double.valueOf(caiWuVO.getKfjlr()) < 0) {
						//isCwFu = false;
						break;
					}
				}

				// 最近财务更新
				CaiWuVO cwvo = cwlist.get(0);
				if (Double.valueOf(cwvo.getKfjlr()) < 0) {
					System.out.println("最近财务扣非净利润【" + cwvo.getKfjlr() + "】<0：" + vo.toBaseString());
					continue;
				}
				// cwvo.p();

				// 营收增长>10%
				if (Double.valueOf(cwvo.getYyzsrtbzz()).compareTo(10.0) < 0) {
					System.out.println("营收增长【" + cwvo.getYyzsrtbzz() + "】>10%：" + vo.toBaseString());
					continue;
				}

				// //净利润或扣非净利润增长比>100%
				// if(Double.valueOf(cwvo.getKfjlrtbzz()).compareTo(100.0) > 0
				// || Double.valueOf(cwvo.getGsjlrtbzz()).compareTo(100.0) > 0)
				// {
				// System.out.println(vo.toString());
				// num++;
				// continue;
				// }
				//
				// //基本面
				// JiBenMianVO jbmvo = RunUtils.getJiBenMianString(vo.getCid());
				//
				// //十大股东占比>50% & 流通股东占比 > 50%
				// if(Double.valueOf(jbmvo.getQsdgdcghj()).compareTo(70.0) < 0
				// || Double.valueOf(jbmvo.getQsdltgdcghj()).compareTo(70.0) <
				// 0)
				// {
				//
				// //System.out.println("流通【" + jbmvo.getQsdltgdcghj() + "】 <
				// 70% && 十大【" + jbmvo.getQsdgdcghj() + "】 < 70% ：" +
				// vo.toBaseString());
				// //System.out.println("十大股东占比【" + jbmvo.getQsdgdcghj() +
				// "】<50%：" + vo.toBaseString());
				// //continue;
				// }
				//
				//
				 //68天内必须涨停>1
				 ZhangDieBiVO zdbvo = RunUtils.getZdb(vo.getCid(), 90);
				 if(zdbvo.getZt() == 0)
				 {
					 System.out.println("90天内必须涨停>1" + vo.toBaseString());
					 continue;
				 }
				// if(Double.valueOf(jbmvo.getQsdltgdcghj()).compareTo(70.0) >
				// 0)
				// {
				// isok = true;
				// System.out.println(vo.toString());
				// num++;
				// }

				System.out.println(vo.toString());
				buf.append(vo.toString()).append("\n");

				num++;
			}

			FileUtil.writeFile(StringPool.PROJECT_DIR + "/data/hygp.txt", buf.toString(), StringPool.UTF_8);

			System.out.println("一共：" + num + "条数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void runJBMDatas() {

		System.out.print(
				"编号#名称#分类#基本面评分#评分说明#上市日期#上市天数#上市年数#总市值#流通市值#总股本(万股)#流通股本(万股)#30天平均交易额#平均换手率%#60天涨比%#60天涨停数#30天涨比%#30天涨停#财报时间#季度营业额（亿）#年营业额占市值比%#营业同长比#季度净利润额（亿）#利润额同长比#净利润率（%）#季度扣非利润额(亿)#扣非年净利润市值比%#扣非同长比#扣非净利润率（%）");
		System.out.println(
				"#市盈（TTM）#每股收益(元)#扣非每股收益(元)#每股净资产(元)#资产负债率(%)#股东人数#股东增比#人均流通股数#十大股东占比#十大流通股股东占比#基金持仓数#持仓基金占流通比#香港中央结算有限公司#全国社保基金#核心题材");

		Double _cxj = 1.0;

		int num = 0;

		// String file = StringPool.PROJECT_DIR + "\\data/1yilist/all.txt";
		String file = StringPool.PROJECT_DIR + "/data/hygp.txt";

		List<GuPiaoBaseVO> list = RunUtils.getGpList(file);

		for (int i = 0; i < list.size(); i++) {
			try {
				GuPiaoBaseVO vo = list.get(i);

				// System.out.println(vo.toString());

				// 上市天数
				int upnum = RunUtils.getUpday(vo.getCid());

				// 25天平均交易量
				Double liangAve = RunUtils.getDayLiangAve(vo.getCid(), 25);

				// 68天内必须涨停比
				ZhangDieBiVO zdbvo = RunUtils.getZdb(vo.getCid(), 68);

				// 总市值
				Double zsz = RunUtils.getZsz(vo.getCid());

				// 连续5年扣非净利润>0元
				List<CaiWuVO> cwlist = RunUtils.getCaiWuList(vo.getCid());

				// 最近财务更新
				CaiWuVO cwvo = cwlist.get(0);

				// cwvo.p();

				// 净利润率（%） > 7 && 扣非净利润率（%） > 7
				Double jdjlr = DoubleUtil.div(Double.valueOf(cwvo.getGsjlr()), 100000000.0, 2); // 净利润
				Double jdkfjlr = DoubleUtil.div(Double.valueOf(cwvo.getKfjlr()), 100000000.0, 2);// 扣非净利润
				Double jlrl = DoubleUtil.div(Double.valueOf(cwvo.getGsjlr()), Double.valueOf(cwvo.getYyzsr()), 2);// 净利润率
				Double kfjlrl = DoubleUtil.div(Double.valueOf(cwvo.getKfjlr()), Double.valueOf(cwvo.getYyzsr()), 2);// 扣非净利润率

				// 年营业额>10亿
				Double jdyye = DoubleUtil.div(Double.valueOf(cwvo.getYyzsr()), 100000000.0, 2);// 季度营业总收入(元)
				Double nyye = DoubleUtil.mul(jdyye, _cxj, 2); // 年营业总收入(元)

				// 年营业额/总市值>10%
				Double nyyzszb = DoubleUtil.div(nyye, zsz, 2);

				// 扣非净利润
				// 扣非净利润/总市值>1% * 扣非增长比>300%
				Double nkfjlr = DoubleUtil.div(DoubleUtil.mul(Double.valueOf(cwvo.getKfjlr()), _cxj, 2), 100000000.0,
						2);// 季度扣非净利润
				Double kfjlrzszb = DoubleUtil.div(nkfjlr, zsz, 2);// 年扣非净利润(元)

				// 基本面
				JiBenMianVO jbmvo = RunUtils.getJiBenMianString(vo.getCid());

				// 30天内涨跌比
				ZhangDieBiVO _30zdbvo = RunUtils.getZdb(vo.getCid(), 30);

				// 换手率>1%
				Double hsl = DoubleUtil.div(liangAve, zsz, 2);

				// 基本面分数算法
				// 1、业绩占比50% （扣非净利润，净利润率，营收同比）
				// 2、十大股东及流通占比35%
				// 3、基金，社保，港资 占比15%

				// 基本面评价
				int grade = 0;
				StringBuffer pfbuf = new StringBuffer();

				// 1、扣非净利润率（%） >30 10分，>25 9分 ， >20 8分， >15 6分， >10 4分， >7 3分 0
				int kfjlrlInt = Double.valueOf(kfjlrl * 100).intValue();
				if (kfjlrlInt >= 30) {
					grade += 10;

					pfbuf.append("扣非净利润率（" + kfjlrlInt + "%）  >30：" + 10).append("/");
				} else if (kfjlrlInt >= 25) {
					grade += 9;

					pfbuf.append("扣非净利润率（" + kfjlrlInt + "%）  >25：" + 9).append("/");

				} else if (kfjlrlInt >= 20) {
					grade += 8;

					pfbuf.append("扣非净利润率（" + kfjlrlInt + "%）  >20：" + 8).append("/");
				} else if (kfjlrlInt >= 15) {
					grade += 6;

					pfbuf.append("扣非净利润率（" + kfjlrlInt + "%）  >15：" + 6).append("/");
				} else if (kfjlrlInt >= 10) {
					grade += 4;

					pfbuf.append("扣非净利润率（" + kfjlrlInt + "%）  >10：" + 4).append("/");
				} else if (kfjlrlInt >= 7) {
					grade += 3;

					pfbuf.append("扣非净利润率（" + kfjlrlInt + "%）  >7：" + 3).append("/");
				}

				// 2、扣非同长比 >500 15分, >300 13分, >200 11分, >100 10分, >70 8分, > 50
				// 7分，>30 5分， >20 3分
				int kftzbInt = Double.valueOf(cwvo.getGsjlrtbzz()).intValue();
				if (kftzbInt >= 500) {
					grade += 15;

					pfbuf.append("扣非同长比（" + kftzbInt + "%）  >500：" + 15).append("/");
				} else if (kftzbInt >= 300) {
					grade += 13;

					pfbuf.append("扣非同长比（" + kftzbInt + "%）  >300：" + 13).append("/");
				} else if (kftzbInt >= 200) {
					grade += 11;
					pfbuf.append("扣非同长比（" + kftzbInt + "%）  >200：" + 11).append("/");
				} else if (kftzbInt >= 100) {
					grade += 10;
					pfbuf.append("扣非同长比（" + kftzbInt + "%）  >100：" + 10).append("/");

				} else if (kftzbInt >= 70) {
					grade += 8;

					pfbuf.append("扣非同长比（" + kftzbInt + "%）  >70：" + 8).append("/");
				} else if (kftzbInt >= 50) {
					grade += 7;

					pfbuf.append("扣非同长比（" + kftzbInt + "%）  >50：" + 7).append("/");
				} else if (kftzbInt >= 30) {
					grade += 5;
					pfbuf.append("扣非同长比（" + kftzbInt + "%）  >30：" + 5).append("/");
				} else if (kftzbInt >= 20) {
					grade += 3;
					pfbuf.append("扣非同长比（" + kftzbInt + "%）  >20：" + 3).append("/");
				}

				// 3、扣非年净利润市值比% >15 10分 ，>10 9分 ，>8 7分 ，>5 5分， >3 3分
				int kfnjliszbInt = Double.valueOf(kfjlrzszb * 100).intValue();
				if (kfnjliszbInt >= 15) {
					grade += 10;
					pfbuf.append("扣非年净利润市值比（" + kfnjliszbInt + "%）  >15：" + 10).append("/");
				} else if (kfnjliszbInt >= 10) {
					grade += 9;
					pfbuf.append("扣非年净利润市值比（" + kfnjliszbInt + "%）  >10：" + 9).append("/");
				} else if (kfnjliszbInt >= 8) {
					grade += 7;
					pfbuf.append("扣非年净利润市值比（" + kfnjliszbInt + "%）  >8：" + 7).append("/");
				} else if (kfnjliszbInt >= 5) {
					grade += 5;
					pfbuf.append("扣非年净利润市值比（" + kfnjliszbInt + "%）  >5：" + 5).append("/");
				} else if (kfnjliszbInt >= 3) {
					grade += 3;
					pfbuf.append("扣非年净利润市值比（" + kfnjliszbInt + "%）  >3：" + 3).append("/");
				}

				// 4、扣非净利润（主业业绩）

				// 2021中报
				CaiWuVO cwvo0 = cwlist.get(0);
				Double kfjlr0 = Double
						.valueOf(DoubleUtil.mul(Double.valueOf(cwvo0.getKfjlr()).doubleValue(), _cxj.doubleValue(), 2));
				Double kfjlrtbzz0 = Double.valueOf(cwvo0.getKfjlrtbzz());

				// 2020年报
				CaiWuVO cwvo1 = cwlist.get(1);
				Double kfjlr1 = Double.valueOf(cwvo1.getKfjlr());
				Double kfjlrtbzz1 = Double.valueOf(cwvo1.getKfjlrtbzz());

				// 2019年报
				CaiWuVO cwvo2 = cwlist.get(2);
				Double kfjlr2 = Double.valueOf(cwvo2.getKfjlr());
				Double kfjlrtbzz2 = Double.valueOf(cwvo2.getKfjlrtbzz());

				// 2018年报
				CaiWuVO cwvo3 = cwlist.get(3);
				Double kfjlr3 = Double.valueOf(cwvo3.getKfjlr());
				Double kfjlrtbzz3 = Double.valueOf(cwvo3.getKfjlrtbzz());

				// 2017年报
				CaiWuVO cwvo4 = cwlist.get(4);
				Double kfjlr4 = Double.valueOf(cwvo4.getKfjlr());
				Double kfjlrtbzz4 = Double.valueOf(cwvo4.getKfjlrtbzz());

				// 5年连续增长（10分）
				if (kfjlr0.compareTo(kfjlr1) > 0 && kfjlr1.compareTo(kfjlr2) > 0 && kfjlr2.compareTo(kfjlr3) > 0
						&& kfjlr3.compareTo(kfjlr4) > 0) {
					grade += 10;
					pfbuf.append("5年连续增长（10分）").append("/");
					if (kfjlrtbzz0.compareTo(30.0) > 0 && kfjlrtbzz1.compareTo(30.0) > 0
							&& kfjlrtbzz2.compareTo(30.0) > 0 && kfjlrtbzz3.compareTo(30.0) > 0
							&& kfjlrtbzz4.compareTo(30.0) > 0) {
						// 5年连续+30%（10分）
						grade += 10;
						pfbuf.append("5年连续+30%（10分）").append("/");
					} else if (kfjlrtbzz0.compareTo(25.0) > 0 && kfjlrtbzz1.compareTo(25.0) > 0
							&& kfjlrtbzz2.compareTo(25.0) > 0 && kfjlrtbzz3.compareTo(25.0) > 0
							&& kfjlrtbzz4.compareTo(25.0) > 0) {
						// 5年连续+25%（9分）
						grade += 9;
						pfbuf.append("5年连续+25%（9分）").append("/");
					} else if (kfjlrtbzz0.compareTo(20.0) > 0 && kfjlrtbzz1.compareTo(20.0) > 0
							&& kfjlrtbzz2.compareTo(20.0) > 0 && kfjlrtbzz3.compareTo(20.0) > 0
							&& kfjlrtbzz4.compareTo(20.0) > 0) {
						// 5年连续+20%（8分）
						grade += 8;
						pfbuf.append("5年连续+20%（8分）").append("/");
					} else if (kfjlrtbzz0.compareTo(15.0) > 0 && kfjlrtbzz1.compareTo(15.0) > 0
							&& kfjlrtbzz2.compareTo(15.0) > 0 && kfjlrtbzz3.compareTo(15.0) > 0
							&& kfjlrtbzz4.compareTo(15.0) > 0) {
						// 5年连续+15%（7分）
						grade += 7;
						pfbuf.append("5年连续+15%（7分）").append("/");
					} else if (kfjlrtbzz0.compareTo(10.0) > 0 && kfjlrtbzz1.compareTo(10.0) > 0
							&& kfjlrtbzz2.compareTo(10.0) > 0 && kfjlrtbzz3.compareTo(10.0) > 0
							&& kfjlrtbzz4.compareTo(10.0) > 0) {
						// 5年连续+10%（6分）
						grade += 6;
						pfbuf.append("5年连续+10%（6分）").append("/");
					} else if (kfjlrtbzz0.compareTo(5.0) > 0 && kfjlrtbzz1.compareTo(5.0) > 0
							&& kfjlrtbzz2.compareTo(5.0) > 0 && kfjlrtbzz3.compareTo(5.0) > 0
							&& kfjlrtbzz4.compareTo(5.0) > 0) {
						// 5年连续+5%（5分）
						grade += 5;
						pfbuf.append("5年连续+5%（5分）").append("/");
					}
				}
				// 4年连续增长（8分）
				else if (kfjlr0.compareTo(kfjlr1) > 0 && kfjlr1.compareTo(kfjlr2) > 0 && kfjlr2.compareTo(kfjlr3) > 0) {
					grade += 8;
					pfbuf.append("4年连续增长（8分）").append("/");

					if (kfjlrtbzz0.compareTo(30.0) > 0 && kfjlrtbzz1.compareTo(30.0) > 0
							&& kfjlrtbzz2.compareTo(30.0) > 0 && kfjlrtbzz3.compareTo(30.0) > 0) {
						// 4年连续+30%（8分）
						grade += 8;
						pfbuf.append("4年连续+30%（8分）").append("/");
					} else if (kfjlrtbzz0.compareTo(20.0) > 0 && kfjlrtbzz1.compareTo(20.0) > 0
							&& kfjlrtbzz2.compareTo(20.0) > 0 && kfjlrtbzz3.compareTo(20.0) > 0) {
						// 4年连续+20%（6分）
						grade += 6;
						pfbuf.append("4年连续+20%（6分）").append("/");
					} else if (kfjlrtbzz0.compareTo(13.0) > 0 && kfjlrtbzz1.compareTo(13.0) > 0
							&& kfjlrtbzz2.compareTo(13.0) > 0 && kfjlrtbzz3.compareTo(13.0) > 0) {
						// 4年连续+10%（4分）
						grade += 4;
						pfbuf.append("4年连续+10%（4分）").append("/");
					} else if (kfjlrtbzz0.compareTo(5.0) > 0 && kfjlrtbzz1.compareTo(5.0) > 0
							&& kfjlrtbzz2.compareTo(5.0) > 0 && kfjlrtbzz3.compareTo(5.0) > 0) {
						// 4年连续+5%（3分）
						grade += 3;
						pfbuf.append("4年连续+5%（3分）").append("/");
					}
				}
				// 3年连续增长（6分）
				else if (kfjlr0.compareTo(kfjlr1) > 0 && kfjlr1.compareTo(kfjlr2) > 0) {
					grade += 6;

					pfbuf.append("3年连续增长（6分）").append("/");
					if (kfjlrtbzz0.compareTo(30.0) > 0 && kfjlrtbzz1.compareTo(30.0) > 0
							&& kfjlrtbzz2.compareTo(30.0) > 0) {
						// 3年连续+30%（6分）
						grade += 6;
						pfbuf.append("3年连续+30%（6分）").append("/");
					} else if (kfjlrtbzz0.compareTo(20.0) > 0 && kfjlrtbzz1.compareTo(20.0) > 0
							&& kfjlrtbzz2.compareTo(20.0) > 0) {
						// 3年连续+20%（4分）
						grade += 4;
						pfbuf.append("3年连续+20%（4分）").append("/");
					} else if (kfjlrtbzz0.compareTo(10.0) > 0 && kfjlrtbzz1.compareTo(10.0) > 0
							&& kfjlrtbzz2.compareTo(10.0) > 0) {
						// 3年连续+10%（2分）
						grade += 2;
						pfbuf.append("3年连续+10%（2分）").append("/");
					}
				}

				// System.out.println("扣非净利润（主业业绩） :" + grade);

				// 5、营业同长比 >300 5分， >200 4分， >100 3分，>50 2分， >20 1分
				if (Double.valueOf(cwvo.getYyzsrtbzz()).compareTo(300.0) >= 0) {
					grade += 5;
					pfbuf.append("营业同长比（" + cwvo.getYyzsrtbzz() + "%）  >300：" + 5).append("/");
				} else if (Double.valueOf(cwvo.getYyzsrtbzz()).compareTo(200.0) >= 0) {
					grade += 4;
					pfbuf.append("营业同长比（" + cwvo.getYyzsrtbzz() + "%）  >200：" + 4).append("/");
				} else if (Double.valueOf(cwvo.getYyzsrtbzz()).compareTo(100.0) >= 0) {
					grade += 3;
					pfbuf.append("营业同长比（" + cwvo.getYyzsrtbzz() + "%）  >100：" + 3).append("/");
				} else if (Double.valueOf(cwvo.getYyzsrtbzz()).compareTo(50.0) >= 0) {
					grade += 2;
					pfbuf.append("营业同长比（" + cwvo.getYyzsrtbzz() + "%）  >50：" + 2).append("/");
				} else if (Double.valueOf(cwvo.getYyzsrtbzz()).compareTo(20.0) >= 0) {
					grade += 1;
					pfbuf.append("营业同长比（" + cwvo.getYyzsrtbzz() + "%）  >20：" + 1).append("/");
				}

				// 6、资产负债率(%)
				if (!ValidateUtil.isNull(jbmvo.getZcfzl())) {
					Double zcfzl = Double.valueOf(jbmvo.getZcfzl());
					if (zcfzl.compareTo(20.0) < 0) {
						grade += 5;
						pfbuf.append("资产负债率（" + jbmvo.getZcfzl() + "%）  <20：" + 5).append("/");
					} else if (zcfzl.compareTo(30.0) < 0) {
						grade += 4;
						pfbuf.append("资产负债率（" + jbmvo.getZcfzl() + "%） <30：" + 4).append("/");
					} else if (zcfzl.compareTo(35.0) < 0) {
						grade += 3;
						pfbuf.append("资产负债率（" + jbmvo.getZcfzl() + "%） <35：" + 3).append("/");
					} else if (zcfzl.compareTo(40.0) < 0) {
						grade += 2;
						pfbuf.append("资产负债率（" + jbmvo.getZcfzl() + "%） <40：" + 2).append("/");
					}
				}

				// 6、前十大股东&流通
				if (Double.valueOf(jbmvo.getQsdltgdcghj()).compareTo(90.0) > 0) {
					// 90+%（15分）
					grade += 15;
					pfbuf.append("前十大股东&流通（" + jbmvo.getQsdltgdcghj() + "%） >90：" + 15).append("/");
				} else if (Double.valueOf(jbmvo.getQsdltgdcghj()).compareTo(85.0) > 0) {
					// 85+%（13分）
					grade += 13;
					pfbuf.append("前十大股东&流通（" + jbmvo.getQsdltgdcghj() + "%） >85：" + 13).append("/");
				} else if (Double.valueOf(jbmvo.getQsdltgdcghj()).compareTo(80.0) > 0) {
					// 80+%（11分）
					grade += 11;
					pfbuf.append("前十大股东&流通（" + jbmvo.getQsdltgdcghj() + "%） >80：" + 11).append("/");
				} else if (Double.valueOf(jbmvo.getQsdltgdcghj()).compareTo(75.0) > 0) {
					// 75+%（9分）
					grade += 9;
					pfbuf.append("前十大股东&流通（" + jbmvo.getQsdltgdcghj() + "%） >75：" + 9).append("/");
				} else if (Double.valueOf(jbmvo.getQsdltgdcghj()).compareTo(70.0) > 0) {
					// 70+%（8分）
					grade += 8;
					pfbuf.append("前十大股东&流通（" + jbmvo.getQsdltgdcghj() + "%） >70：" + 8).append("/");
				} else if (Double.valueOf(jbmvo.getQsdltgdcghj()).compareTo(65.0) > 0) {
					// 65+%（6分）
					grade += 6;
					pfbuf.append("前十大股东&流通（" + jbmvo.getQsdltgdcghj() + "%） >65：" + 6).append("/");
				} else if (Double.valueOf(jbmvo.getQsdltgdcghj()).compareTo(60.0) > 0) {
					// 60+%（5分）
					grade += 5;
					pfbuf.append("前十大股东&流通（" + jbmvo.getQsdltgdcghj() + "%） >60：" + 5).append("/");
				} else if (Double.valueOf(jbmvo.getQsdltgdcghj()).compareTo(50.0) > 0) {
					// 50+%（4分）
					grade += 4;
					pfbuf.append("前十大股东&流通（" + jbmvo.getQsdltgdcghj() + "%） >50：" + 4).append("/");
				}

				// 7、基金持仓
				if (!ValidateUtil.isNull(jbmvo.getCcjs()) && !jbmvo.getCcjs().startsWith("-")) {
					int jjcc = Integer.valueOf(jbmvo.getCcjs());
					if (jjcc >= 100) {
						grade += 5;

						pfbuf.append("基金持仓数（" + jjcc + "）  >100：" + 5).append("/");
					} else if (jjcc >= 70) {
						grade += 4;
						pfbuf.append("基金持仓数（" + jjcc + "）  >70：" + 4).append("/");
					} else if (jjcc >= 50) {
						grade += 3;
						pfbuf.append("基金持仓数（" + jjcc + "）  >50：" + 3).append("/");
					} else if (jjcc >= 20) {
						grade += 2;
						pfbuf.append("基金持仓数（" + jjcc + "）  >20：" + 2).append("/");
					} else if (jjcc >= 10) {
						grade += 1;
						pfbuf.append("基金持仓数（" + jjcc + "）  >10：" + 1).append("/");
					}
				}

				// 8、香港中央结算有限公司
				if (!ValidateUtil.isNull(jbmvo.getHkprice())) {
					Double hkcc = Double.valueOf(jbmvo.getHkprice().replace("%", ""));
					if (hkcc.compareTo(10.0) > 0) {
						grade += 5;
						pfbuf.append("港资占比（" + hkcc + "%）  >10：" + 5).append("/");
					} else if (hkcc.compareTo(6.0) > 0) {
						grade += 4;
						pfbuf.append("港资占比（" + hkcc + "%）  >6：" + 4).append("/");
					} else if (hkcc.compareTo(4.0) > 0) {
						grade += 3;
						pfbuf.append("港资占比（" + hkcc + "%）  >4：" + 3).append("/");
					} else if (hkcc.compareTo(1.0) > 0) {
						grade += 2;
						pfbuf.append("港资占比（" + hkcc + "%）  >1：" + 2).append("/");
					} else if (hkcc.compareTo(0.0) > 0) {
						grade += 1;
						pfbuf.append("港资占比（" + hkcc + "%）  >0：" + 2).append("/");
					}
					// System.out.println("香港中央结算有限公司 :" + grade);
				}

				// 9、全国社保基金
				if (!ValidateUtil.isNull(jbmvo.getGjjprice())) {

					Double gjjcc = Double.valueOf(jbmvo.getGjjprice().replace("%", ""));
					if (gjjcc.compareTo(0.0) > 0) {
						if (gjjcc.compareTo(10.0) > 0) {
							grade += 5;
							pfbuf.append("社保占比（" + gjjcc + "%）  >10：" + 5).append("/");
						} else if (gjjcc.compareTo(6.0) > 0) {
							grade += 4;
							pfbuf.append("社保占比（" + gjjcc + "%）  >6：" + 4).append("/");
						} else if (gjjcc.compareTo(4.0) > 0) {
							grade += 3;
							pfbuf.append("社保占比（" + gjjcc + "%）  >4：" + 3).append("/");
						} else if (gjjcc.compareTo(1.0) > 0) {
							grade += 2;
							pfbuf.append("社保占比（" + gjjcc + "%）  >1：" + 2).append("/");
						} else {
							grade += 1;
							pfbuf.append("社保占比（" + gjjcc + "%）  >0：" + 1).append("/");
						}
						// System.out.println("全国社保基金 :" + grade);
					}
				}

				System.out.print(vo.getCid());
				System.out.print("#");
				System.out.print(vo.getName());
				System.out.print("#");
				// System.out.print(RunUtils.getCate(vo.getCid()));
				System.out.print(vo.getCate());
				System.out.print("#");
				System.out.print(grade);
				System.out.print("#");
				System.out.print(pfbuf);
				System.out.print("#");
				System.out.print(RunUtils.getUptime(vo.getCid()));
				System.out.print("#");
				System.out.print(upnum);
				System.out.print("#");
				System.out.print(upnum / 200);
				System.out.print("#");
				System.out.print(zsz);
				System.out.print("#");
				System.out.print(jbmvo.getLtsz());
				System.out.print("#");

				System.out.print(jbmvo.getZgb());
				System.out.print("#");
				System.out.print(jbmvo.getLtgb());
				System.out.print("#");

				System.out.print(liangAve);
				System.out.print("#");
				System.out.print(Double.valueOf(hsl * 100).intValue() + "%");
				System.out.print("#");
				System.out.print(zdbvo.getZhangb().intValue() + "%");
				System.out.print("#");
				System.out.print(zdbvo.getZt());
				System.out.print("#");
				System.out.print(_30zdbvo.getZhangb().intValue() + "%");
				System.out.print("#");
				System.out.print(_30zdbvo.getZt());
				System.out.print("#");

				System.out.print(cwvo.getCwdate()); // 财务时间
				System.out.print("#");

				System.out.print(jdyye);
				System.out.print("#");
				System.out.print(Double.valueOf(nyyzszb * 100).intValue() + "%");
				System.out.print("#");
				System.out.print(Double.valueOf(cwvo.getYyzsrtbzz()).intValue() + "%");
				System.out.print("#");

				// 一季度净利润额（亿）#利润额同长比#净利润率（%）#
				System.out.print(jdjlr);
				System.out.print("#");
				System.out.print(Double.valueOf(cwvo.getGsjlrtbzz()).intValue() + "%");
				System.out.print("#");
				System.out.print(Double.valueOf(jlrl * 100).intValue() + "%");
				System.out.print("#");

				// 一季度扣非利润额(亿)#扣非年净利润市值比%#扣非同长比#扣非净利润率（%）"
				System.out.print(jdkfjlr);
				System.out.print("#");
				System.out.print(Double.valueOf(kfjlrzszb * 100).intValue() + "%");
				System.out.print("#");
				System.out.print(Double.valueOf(cwvo.getKfjlrtbzz()).intValue() + "%");
				System.out.print("#");
				System.out.print(Double.valueOf(kfjlrl * 100).intValue() + "%");

				System.out.print("#");
				System.out.print(jbmvo.getSylTTM());
				System.out.print("#");
				System.out.print(jbmvo.getJbmgsy());
				System.out.print("#");
				System.out.print(jbmvo.getKfmgsy());
				System.out.print("#");
				System.out.print(jbmvo.getMgjzc());
				System.out.print("#");
				System.out.print(jbmvo.getZcfzl());
				System.out.print("#");

				// 股东人数#股东增比#人均流通股数
				System.out.print(jbmvo.getGdrs());
				System.out.print("#");
				System.out.print(jbmvo.getGdrs_jsqbh());
				System.out.print("#");
				System.out.print(jbmvo.getRjltg());
				System.out.print("#");

				System.out.print(jbmvo.getQsdgdcghj());
				System.out.print("#");
				System.out.print(jbmvo.getQsdltgdcghj());
				System.out.print("#");
				System.out.print(jbmvo.getCcjs());
				System.out.print("#");
				System.out.print(jbmvo.getCcjjzb());
				System.out.print("#");

				System.out.print(jbmvo.getHkprice());
				System.out.print("#");
				System.out.print(jbmvo.getGjjprice());
				System.out.print("#");
				System.out.print(jbmvo.getHxtc());

				System.out.println();
				num++;
			} catch (Exception e) {
				e.printStackTrace();
			}

			//break;
		}

		System.out.println("一共：" + num + "条数据");

	}

	public static void zhangdiefu() {
		String yyfile = StringPool.PROJECT_DIR + "/data/1yilist/yjbz.txt";
		List<String> yistrlist = FileUtil.readToStringList(yyfile, "UTF-8");
		List<ZhangDieBiVO> yilist = new ArrayList<ZhangDieBiVO>();
		for (String yistr : yistrlist) {
			ZhangDieBiVO vo = new ZhangDieBiVO();
			String[] rdcxSps = yistr.split("#");
			vo.setCid(rdcxSps[0]);
			vo.setName(rdcxSps[1]);
			vo.setCate(rdcxSps[2]);

			ZhangDieBiVO _60vo = RunUtils.getZdb(vo.getCid(), 90);
			_60vo.getZhangb();
			_60vo.getZt();

			ZhangDieBiVO _30vo = RunUtils.getZdb(vo.getCid(), 30);
			_30vo.getZhangb();
			_30vo.getZt();
			System.out.println(vo.getCid() + "#" + vo.getName() + "#" + vo.getCate() + "#" + _60vo.getZhangb() + "#"
					+ _60vo.getZt() + "#" + _30vo.getZhangb() + "#" + _30vo.getZt());
			yilist.add(vo);
		}
	}

	// 2005-2107涨3倍以上的股
	public static void run3BeiGu() {
		List<GuPiaoBaseVO> list = RunUtils.getAllGpVoList();

		for (int i = 0; i < list.size(); i++) {
			GuPiaoBaseVO vo = list.get(i);
			if (vo.isOK()) {
				String kpjstr = "";
				String spjstr = "";

				try {
					// System.out.println(vo.toString());
					String gpmlist = RunUtils.getGpMonthBody(vo.getCid());

					if (gpmlist.indexOf("200529") == -1) {
						continue;
					}

					if (gpmlist.indexOf("210712") == -1) {
						continue;
					}

					String[] splist = gpmlist.split("/");
					for (int n = 1; n < splist.length; n++) {
						String text = splist[n].replace("\n\\", "");

						if (text.startsWith("200529")) {
							kpjstr = text;
						}

						if (text.startsWith("2107")) {
							spjstr = text;
						}
					}

					Double kpj = Double.valueOf(kpjstr.split(" ")[2]);
					// System.out.println(spjstr);
					Double spj = Double.valueOf(spjstr.split(" ")[1]);

					// 增长倍数
					Double zzbs = DoubleUtil.div(DoubleUtil.sub(spj, kpj), kpj, 2);

					if (zzbs.compareTo(3.0) < 0) {
						continue;
					}

					// 总市值
					String zsz = HttpClientUtil.getInstance().doGet("http://qt.gtimg.cn/q=s_" + vo.getCid());
					Double zsz_yy = Double.valueOf(zsz.split("~")[9]);

					if (zsz_yy < 30) {
						continue;
					}

					// Double liang = RunUtils.getDayLiangAve(vo.getCid(), 20);
					//
					// if(liang < 1)
					// {
					// continue;
					// }

					if (ValidateUtil.isNull(vo.getCate())) {
						vo.setCate(RunUtils.getCate(vo.getCid()));
					}

					System.out.println(vo.toString() + "#" + zzbs);

				} catch (Exception e) {
					System.out.println(vo.getCid() + vo.getName() + "-" + spjstr);
					e.printStackTrace();
				}
			}
		}
	}

	public static void runFanBeiGP() {
		List<GuPiaoBaseVO> list = RunUtils.getAllGpVoList();

		for (int i = 0; i < list.size(); i++) {
			GuPiaoBaseVO vo = list.get(i);
			if (!vo.isOK()) {
				continue;
			}

			try {

				String gpmlist = RunUtils.getGpMonthBody(vo.getCid());
				if (gpmlist.split("\n").length < 16) {
					continue;
				}

				// 三一重工 开盘，收盘，最高，最低
				// 210129 35.25 40.46 48.90 34.30 28330860\n\
				// 2021年开盘价,2021年收盘价,2021年全量
				Double _21kpj = 0.0;
				Double _21spj = 0.0;
				//Double _21zgj = 0.0;

				String[] splist = gpmlist.split("\n");
				for (int n = 1; n < splist.length; n++) {
					String text = splist[n].replace("\\n\\", "");
					String[] tspl = text.split(" ");

					// 202101年数据
					if (text.startsWith("2101")) {
						_21kpj = Double.valueOf(tspl[1]);
					}
					if (text.startsWith("2111")) {
						_21spj = Double.valueOf(tspl[2]);
						//_21zgj = Double.valueOf(tspl[3]);
					}
				}

				Double zf = DoubleUtil.sub(_21spj, _21kpj);
				if (zf.compareTo(_21kpj) > 0) {
					System.out.println(vo.toString() + "#" + _21kpj + "#" + _21spj);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 底背离股票
	public static void runDibeiliGP()
	{
		List<GuPiaoBaseVO> list = RunUtils.get1YiList(false);

		int num = 0;

		System.out.println("编号#名称#分类#20天交易量#最高价#最高价时间#最低价#最低时间#当前收盘价#5-10-20日线差比");
		for (int i = 0; i < list.size(); i++) {
			GuPiaoBaseVO vo = list.get(i);

			if (!vo.isOK() || vo.is3068())
			{
				continue;
			}

			try {

				// 1、3个月内2次涨停
				int ztnum = RunUtils.getZhangTingDays(vo.getCid(), 99);
				if (ztnum < 1) {
					continue;
				}

				String gpdlist = RunUtils.getGpDayBody(vo.getCid());

				String[] splist = gpdlist.split("\n");
				// System.out.println(splist.length);

				if (splist.length < 103) {
					continue;
				}

				int stnum = 2;
				int ednum = splist.length - 2;

				// System.out.println(splist[stnum]);
				// System.out.println(splist[ednum]);

				// if(vo.getName().equals("广汇能源"))
				// {
				// System.out.println("广汇能源 来啦");
				// }

				// 最新当天数据
				String[] infos = splist[ednum].split(" ");
				// Double kpj = Double.valueOf(infos[1]);//今日开盘价
				Double spj = Double.valueOf(infos[2]);// 今日收盘价
				// Double zdj = Double.valueOf(infos[4]);//今日最低价

				// 100天内 最高价 & 时间
				Double zgj = 0.0; // 最高价
				String zgjday = "";// 最高价时间
				int zgjnum = 0; // 最高价点数量

				for (int n = stnum; n <= ednum; n++) {
					String[] str = splist[n].split(" ");
					Double myzgj = Double.valueOf(str[3]);// 今日最高价
					// Double myzdj = Double.valueOf(str[4]);//今日最低价
					if (myzgj.compareTo(zgj) > 0) {
						zgj = myzgj;
						zgjday = str[0];
						zgjnum = n;
					}
				}

				// 100天内最高价 - 昨天收盘价 > -25%
				Double zdf = DoubleUtil.div(DoubleUtil.sub(zgj, spj, 2), zgj, 2);
				if (zdf.compareTo(0.30) < 0) {
					continue;
				}

				Double zdj = 1000.0; // 最低价
				String zdjday = ""; // 最低价时间
				int zdjnum = 0; // 最低价点数量

				// 最高价 到 当前之间的最低价
				for (int n = zgjnum; n <= ednum; n++) {

					// System.out.println(splist[n]);
					String[] str = splist[n].split(" ");
					Double myzdj = Double.valueOf(str[4]);// 今日最低价
					if (myzdj.compareTo(zdj) < 0) {
						zdj = myzdj;
						zdjday = str[0];
						zdjnum = n;
					}
				}

				// 探底 和 当前需要有20天的调整企稳
				if (ednum - zdjnum < 8) {
					//System.out.println("探底 和 当前需要有20天的调整企稳:"+ (ednum - zdjnum) + "->" + vo.toString());
					continue;
				}

				// 最低价 > 当前收盘价
				if (zdj.compareTo(spj) >= 0) {
					continue;
				}
				
				// 最低价涨跌幅 大于10% 跳过
				Double zdjzdf = DoubleUtil.div(DoubleUtil.sub(spj, zdj, 2), zdj, 2);
				if (zdjzdf.compareTo(0.15) > 0) {
					continue;
				}
				
				Double xzgj = 0.0; // 最高价
				// String xzgjday = "";//最高价时间
				// 最高价 到 当前之间的最低价
				for (int n = zdjnum; n <= ednum; n++) {
					String[] str = splist[n].split(" ");
					Double myzgj = Double.valueOf(str[3]);// 今日最高价
					if (myzgj.compareTo(xzgj) > 0) {
						xzgj = myzgj;
						// xzgjday = str[0];
					}
				}

				// 最低点到当前企稳中波动不超过 20%
				Double xzdf = DoubleUtil.div(DoubleUtil.sub(xzgj, zdj, 2), zdj, 2);
				if (xzdf.compareTo(0.20) > 0) {
					continue;
				}
				
				Double day5 = RunUtils.getDayAvePrice(vo.getCid(), 5);
				Double day10 = RunUtils.getDayAvePrice(vo.getCid(), 10);
				Double day20 = RunUtils.getDayAvePrice(vo.getCid(), 20);
				Double day30 = RunUtils.getDayAvePrice(vo.getCid(), 30);

				// 3、5 - 10 - 20 相差不能>8%
				Double min = day5;
				if (min.compareTo(day10) > 0) {
					min = day10;
				}
				if (min.compareTo(day20) > 0) {
					min = day20;
				}

				Double max = day5;
				if (max.compareTo(day10) < 0) {
					max = day10;
				}
				if (max.compareTo(day20) < 0) {
					max = day20;
				}

				// 均线涨跌幅 不超过8%
				Double jxzdf = DoubleUtil.div(DoubleUtil.sub(max, min, 2), min, 2);
				if (jxzdf.compareTo(0.15) > 0) {
					//System.out.println("均线涨跌幅 不超过15%" + vo.toString());
					continue;
				}
				
				// 5日线 & 10日线 > 20日线
				if (day5.compareTo(day10) < 0 && day10.compareTo(day20) < 0 && day20.compareTo(day30) < 0)
				{
					//System.out.println("探底 和 当前需要有20天的调整企稳:"+ (ednum - zdjnum) + "->" + vo.toString());
					continue;
				}
				
				//System.out.println("探底 和 当前需要有20天的调整企稳:"+ (ednum - zdjnum) + "->" + vo.toString());
				
				// 收盘价低于 30日线 跳过
				if (spj.compareTo(day30) < 0) {
					// 30均线涨跌幅 不超过5%
					Double day30zdf = DoubleUtil.div(DoubleUtil.sub(max, day30, 2), day30, 2);
					if (day30zdf.compareTo(0.03) > 0) {
						// System.out.println(vo.getCid());
						continue;
					}
				}

				// 5月线 < 10月线 < 20月线 & 5-10月线 < 30月线
				//

				// 50亿>总市值>10000亿过滤
				Double zsz = RunUtils.getZsz(vo.getCid());
				if (zsz.compareTo(10000.0) > 0 || zsz.compareTo(50.0) < 0) {
					// System.out.println("60亿>总市值【" + zsz + "】>10000亿过滤：" +
					// vo.toBaseString());
					continue;
				}

				// 连续5年扣非净利润>0元
				boolean isCwFu = true;
				List<CaiWuVO> cwlist = RunUtils.getCaiWuList(vo.getCid());
				for (CaiWuVO caiWuVO : cwlist) {
					if (Double.valueOf(caiWuVO.getKfjlr()) < 0) {
						isCwFu = false;
						break;
					}
				}

				// 最近财务更新
				CaiWuVO cwvo = cwlist.get(0);
				if (Double.valueOf(cwvo.getKfjlr()) < 0) {
					// System.out.println("最近财务扣非净利润【" + cwvo.getKfjlr() +
					// "】<0：" + vo.toBaseString());
					continue;
				}

				if (!isCwFu) {
					continue;
				}

				// 营收增长>10%
				if (Double.valueOf(cwvo.getYyzsrtbzz()).compareTo(10.0) < 0) {
					// System.out.println("营收增长【" + cwvo.getYyzsrtbzz() +
					// "】>10%：" + vo.toBaseString());
					continue;
				}

				// 净利润或扣非净利润增长比>100%
				if (Double.valueOf(cwvo.getKfjlrtbzz()).compareTo(30.0) < 0
						&& Double.valueOf(cwvo.getGsjlrtbzz()).compareTo(30.0) < 0) {
					continue;
				}

				num++;

				Double liang = RunUtils.getDayLiangAve(vo.getCid(), 20);
				System.out.println(vo.toString() + "#" + liang + "#" + zgj + "#" + zgjday + "#" + zdj + "#" + zdjday
						+ "#" + zdf + "#" + zdjzdf);

				// break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(num);
	}

	public static void runZhengdangGP()
	{
		List<GuPiaoBaseVO> list = RunUtils.get1YiList(false);

		int num = 0;

		System.out.println("编号#名称#分类#20天交易量#最高价#最高价时间#最低价#最低时间#当前收盘价#5-10-20日线差比");
		for (int i = 0; i < list.size(); i++) {
			GuPiaoBaseVO vo = list.get(i);

			if (!vo.isOK() || vo.is3068())
			{
				continue;
			}

			try {

				// 1、3个月内2次涨停
				int ztnum = RunUtils.getZhangTingDays(vo.getCid(), 99);
				if (ztnum < 1) {
					continue;
				}

				String gpdlist = RunUtils.getGpDayBody(vo.getCid());

				String[] splist = gpdlist.split("\n");
				// System.out.println(splist.length);

				if (splist.length < 103) {
					continue;
				}

				int stnum = 2;
				int ednum = splist.length - 2;

				// System.out.println(splist[stnum]);
				// System.out.println(splist[ednum]);

				// if(vo.getName().equals("广汇能源"))
				// {
				// System.out.println("广汇能源 来啦");
				// }

				// 最新当天数据
				String[] infos = splist[ednum].split(" ");
				// Double kpj = Double.valueOf(infos[1]);//今日开盘价
				Double spj = Double.valueOf(infos[2]);// 今日收盘价
				// Double zdj = Double.valueOf(infos[4]);//今日最低价

				// 100天内 最高价 & 时间
				Double zgj = 0.0; // 最高价
				String zgjday = "";// 最高价时间
				int zgjnum = 0; // 最高价点数量

				for (int n = stnum; n <= ednum; n++) {
					String[] str = splist[n].split(" ");
					Double myzgj = Double.valueOf(str[3]);// 今日最高价
					// Double myzdj = Double.valueOf(str[4]);//今日最低价
					if (myzgj.compareTo(zgj) > 0) {
						zgj = myzgj;
						zgjday = str[0];
						zgjnum = n;
					}
				}

				// 100天内最高价 - 昨天收盘价 > -25%
				Double zdf = DoubleUtil.div(DoubleUtil.sub(zgj, spj, 2), zgj, 2);
				if (zdf.compareTo(0.30) < 0) {
					continue;
				}

				Double zdj = 1000.0; // 最低价
				String zdjday = ""; // 最低价时间
				int zdjnum = 0; // 最低价点数量

				// 最高价 到 当前之间的最低价
				for (int n = zgjnum; n <= ednum; n++) {

					// System.out.println(splist[n]);
					String[] str = splist[n].split(" ");
					Double myzdj = Double.valueOf(str[4]);// 今日最低价
					if (myzdj.compareTo(zdj) < 0) {
						zdj = myzdj;
						zdjday = str[0];
						zdjnum = n;
					}
				}

				// 探底 和 当前需要有20天的调整企稳
				if (ednum - zdjnum < 8) {
					//System.out.println("探底 和 当前需要有20天的调整企稳:"+ (ednum - zdjnum) + "->" + vo.toString());
					continue;
				}

				// 最低价 > 当前收盘价
				if (zdj.compareTo(spj) >= 0) {
					continue;
				}
				
				// 最低价涨跌幅 大于10% 跳过
				Double zdjzdf = DoubleUtil.div(DoubleUtil.sub(spj, zdj, 2), zdj, 2);
				if (zdjzdf.compareTo(0.15) > 0) {
					continue;
				}
				
				Double xzgj = 0.0; // 最高价
				// String xzgjday = "";//最高价时间
				// 最高价 到 当前之间的最低价
				for (int n = zdjnum; n <= ednum; n++) {
					String[] str = splist[n].split(" ");
					Double myzgj = Double.valueOf(str[3]);// 今日最高价
					if (myzgj.compareTo(xzgj) > 0) {
						xzgj = myzgj;
						// xzgjday = str[0];
					}
				}

				// 最低点到当前企稳中波动不超过 20%
				Double xzdf = DoubleUtil.div(DoubleUtil.sub(xzgj, zdj, 2), zdj, 2);
				if (xzdf.compareTo(0.20) > 0) {
					continue;
				}
				
				Double day5 = RunUtils.getDayAvePrice(vo.getCid(), 5);
				Double day10 = RunUtils.getDayAvePrice(vo.getCid(), 10);
				Double day20 = RunUtils.getDayAvePrice(vo.getCid(), 20);
				Double day30 = RunUtils.getDayAvePrice(vo.getCid(), 30);

				// 3、5 - 10 - 20 相差不能>8%
				Double min = day5;
				if (min.compareTo(day10) > 0) {
					min = day10;
				}
				if (min.compareTo(day20) > 0) {
					min = day20;
				}

				Double max = day5;
				if (max.compareTo(day10) < 0) {
					max = day10;
				}
				if (max.compareTo(day20) < 0) {
					max = day20;
				}

				// 均线涨跌幅 不超过8%
				Double jxzdf = DoubleUtil.div(DoubleUtil.sub(max, min, 2), min, 2);
				if (jxzdf.compareTo(0.15) > 0) {
					//System.out.println("均线涨跌幅 不超过15%" + vo.toString());
					continue;
				}
				
				// 5日线 & 10日线 > 20日线
				if (day5.compareTo(day10) < 0 && day10.compareTo(day20) < 0 && day20.compareTo(day30) < 0)
				{
					//System.out.println("探底 和 当前需要有20天的调整企稳:"+ (ednum - zdjnum) + "->" + vo.toString());
					continue;
				}
				
				//System.out.println("探底 和 当前需要有20天的调整企稳:"+ (ednum - zdjnum) + "->" + vo.toString());
				
				// 收盘价低于 30日线 跳过
				if (spj.compareTo(day30) < 0) {
					// 30均线涨跌幅 不超过5%
					Double day30zdf = DoubleUtil.div(DoubleUtil.sub(max, day30, 2), day30, 2);
					if (day30zdf.compareTo(0.03) > 0) {
						// System.out.println(vo.getCid());
						continue;
					}
				}

				// 5月线 < 10月线 < 20月线 & 5-10月线 < 30月线
				//

				// 50亿>总市值>10000亿过滤
				Double zsz = RunUtils.getZsz(vo.getCid());
				if (zsz.compareTo(10000.0) > 0 || zsz.compareTo(50.0) < 0) {
					// System.out.println("60亿>总市值【" + zsz + "】>10000亿过滤：" +
					// vo.toBaseString());
					continue;
				}

				// 连续5年扣非净利润>0元
				boolean isCwFu = true;
				List<CaiWuVO> cwlist = RunUtils.getCaiWuList(vo.getCid());
				for (CaiWuVO caiWuVO : cwlist) {
					if (Double.valueOf(caiWuVO.getKfjlr()) < 0) {
						isCwFu = false;
						break;
					}
				}

				// 最近财务更新
				CaiWuVO cwvo = cwlist.get(0);
				if (Double.valueOf(cwvo.getKfjlr()) < 0) {
					// System.out.println("最近财务扣非净利润【" + cwvo.getKfjlr() +
					// "】<0：" + vo.toBaseString());
					continue;
				}

				if (!isCwFu) {
					continue;
				}

				// 营收增长>10%
				if (Double.valueOf(cwvo.getYyzsrtbzz()).compareTo(10.0) < 0) {
					// System.out.println("营收增长【" + cwvo.getYyzsrtbzz() +
					// "】>10%：" + vo.toBaseString());
					continue;
				}

				// 净利润或扣非净利润增长比>100%
				if (Double.valueOf(cwvo.getKfjlrtbzz()).compareTo(30.0) < 0
						&& Double.valueOf(cwvo.getGsjlrtbzz()).compareTo(30.0) < 0) {
					continue;
				}

				num++;

				Double liang = RunUtils.getDayLiangAve(vo.getCid(), 20);
				System.out.println(vo.toString() + "#" + liang + "#" + zgj + "#" + zgjday + "#" + zdj + "#" + zdjday
						+ "#" + zdf + "#" + zdjzdf);

				// break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(num);
	}

	public static void runDaGP() {
		try {

			int num = 0;

			String file = StringPool.PROJECT_DIR + "/data/1yilist/all.txt";
			List<GuPiaoBaseVO> list = RunUtils.getGpList(file);

			for (int i = 0; i < list.size(); i++) {
				GuPiaoBaseVO vo = list.get(i);

				if (!vo.isOK() || !vo.is3068()) {
					continue;
				}

				// 上市天数>100
				int upnum = RunUtils.getUpday(vo.getCid());
				if (upnum < 100) {
					continue;
				}

				// 20天平均交易量>4亿
				Double liangAve = RunUtils.getDayLiangAve(vo.getCid(), 10);
				if (liangAve.compareTo(10.0) < 0) {
					// System.out.println("10天平均交易量【" + liangAve + "】<10亿" +
					// vo.toBaseString());
					continue;
				}

				// 68天内必须涨停>1
				ZhangDieBiVO zdbvo = RunUtils.getZdb(vo.getCid(), 10);
				if (zdbvo.getZt() < 1) {
					// System.out.println("68天内必须涨停>1" + vo.toBaseString());
					continue;
				}
				//
				//
				// //连续5年扣非净利润>0元
				// boolean isCwFu = false;
				// List<CaiWuVO> cwlist = RunUtils.getCaiWuList(vo.getCid());
				// if(ValidateUtil.isEmpty(cwlist))
				// {
				// continue;
				// }
				// for (CaiWuVO caiWuVO : cwlist)
				// {
				// if(Double.valueOf(caiWuVO.getKfjlr()) < 0)
				// {
				// isCwFu = true;
				// break;
				// }
				// }
				//
				//// if(isCwFu)
				//// {
				//// System.out.println("连续5年扣非净利润>0元" + vo.toBaseString());
				//// continue;
				//// }
				//
				// //最近财务更新
				// CaiWuVO cwvo = cwlist.get(0);
				// if(Double.valueOf(cwvo.getKfjlr()) < 0)
				// {
				// System.out.println("最近财务扣非净利润【" + cwvo.getKfjlr() + "】<0：" +
				// vo.toBaseString());
				// continue;
				// }
				//
				// //营收增长>10%
				// if(Double.valueOf(cwvo.getYyzsrtbzz()).compareTo(10.0) < 0)
				// {
				// System.out.println("营收增长【" + cwvo.getYyzsrtbzz() + "】>10%：" +
				// vo.toBaseString());
				// continue;
				// }
				//
				// //净利润或扣非净利润增长比>80%
				// if(Double.valueOf(cwvo.getKfjlrtbzz()).compareTo(30.0) < 0 ||
				// Double.valueOf(cwvo.getGsjlrtbzz()).compareTo(30.0) < 0)
				// {
				// System.out.println("净利润【" + cwvo.getGsjlrtbzz() + "】或扣非净利润【"
				// + cwvo.getKfjlrtbzz() + "】增长比>50%：" + vo.toBaseString());
				// continue;
				// }
				//
				// //净利润率（%） > 7 && 扣非净利润率（%） > 7
				// Double jlrl = DoubleUtil.div(Double.valueOf(cwvo.getGsjlr()),
				// Double.valueOf(cwvo.getYyzsr()), 2);
				// Double kfjlrl =
				// DoubleUtil.div(Double.valueOf(cwvo.getKfjlr()),
				// Double.valueOf(cwvo.getYyzsr()), 2);
				// if(jlrl.compareTo(0.08) < 0 || kfjlrl.compareTo(0.08) < 0)
				// {
				// System.out.println("净利润率（%）【" + jlrl + "】 > 8 && 扣非净利润率（%） 【"
				// + kfjlrl + "】> 8：" + vo.toBaseString());
				// continue;
				// }
				//
				// Double kfjlr =
				// DoubleUtil.div(Double.valueOf(cwvo.getKfjlr()), 100000000.0,
				// 2);
				// if(kfjlr.compareTo(5.0) < 0)
				// {
				// System.out.println("净利润 < 5亿：" + vo.toBaseString());
				// continue;
				// }

				System.out.println(vo.toString() + "#" + liangAve);
				num++;
			}

			System.out.println("一共：" + num + "条数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void runZhangtingHuiceGP()
	{
		//10天内1次涨停
		//10天内平均成交量5亿
		List<GuPiaoBaseVO> list = RunUtils.get1YiList(false);

		int num = 0;
		
		for (int i = 0; i < list.size(); i++)
		{
			GuPiaoBaseVO vo = list.get(i);
			if (!vo.isOK() || vo.is3068())
			{
				continue;
			}

			try {

				//10天内1次涨停
				int ztnum = RunUtils.getZhangTingDays(vo.getCid(), 10);
				if (ztnum < 1 || ztnum > 1)
				{
					continue;
				}
				
				//10天内平均成交量 > 5亿
				Double liangAve = RunUtils.getDayLiangAve(vo.getCid(), 10);
				if (liangAve.compareTo(5.0) < 0)
				{
					continue;
				}
				
				//上市大于100天的
				String gpdlist = RunUtils.getGpDayBody(vo.getCid());
				String[] splist = gpdlist.split("\n");
				if (splist.length < 103)
				{
					continue;
				}
				
				//int stnum = 2;
				int ednum = splist.length - 2;
				
				Double ssj = 0.0; //今日实时价
				Double kpj = 0.0; //今日开盘价
				Double spj = 0.0; //今日收盘价
				Double zdj = 0.0; //今日最低价
				
				Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				//int minute = c.get(Calendar.MINUTE);
				
				//晚上
				if(hour > 19 || hour < 9)
				{
					// 最新当天数据
					String[] infos = splist[ednum].split(" ");
					kpj = Double.valueOf(infos[1]);//今日开盘价
					spj = Double.valueOf(infos[2]);//今日收盘价
					zdj = Double.valueOf(infos[4]);//今日最低价
					ssj = zdj;
				} else {

					//实时数据
					String zsz = HttpClientUtil.getInstance().doGet("http://qt.gtimg.cn/q=" + vo.getCid());
					String[] spz = zsz.split("~");
					kpj = Double.valueOf(spz[5]);
					spj = Double.valueOf(spz[3]);
					zdj = Double.valueOf(spz[34]);
					ssj = spj;
					
					if(Double.valueOf(spz[32]).compareTo(5.0) > 0)
					{
						continue;
					}
				}
				
				if(ssj.compareTo(kpj) < 0)
				{
					//System.out.println("当前跌：" + spj + "-" + kpj + ">" + vo.toString());
					continue;
				}
				
				//最低价 跌破 20日线
				//Double _20jx = RunUtils.getDayAvePrice(vo.getCid(), 20);
				//if(spj.compareTo(_20jx) < 0)
				//{
				//	System.out.println("最低价破20日线：>" + vo.toString());
				//	continue;
				//}
				
				//Double ztkpj = 0.0; //涨停开盘价
				Double ztspj = 0.0; //涨停收盘价
				
				//涨停开盘价
				for (int n = ednum - 1; n >= 92; n--)
				{
					//System.out.println(splist[n]);
					String[] str = splist[n].split(" ");
					
					//Double dtkpj = Double.valueOf(str[1]); //当天开盘价
					Double dtspj = Double.valueOf(str[2]); //当天收盘价
 
					// 昨天数据
					String ztdayStr = splist[n - 1].replace("\\n\\", "");
					String[] ztspdayStr = ztdayStr.split(" ");
					if (ztspdayStr.length != 6) {
						continue;
					}
					// 昨天收盘价
					Double zspj = Double.valueOf(ztspdayStr[2]);

					// 涨停算法 = （当天收盘价 - 昨天收盘价） / 昨天收盘价 > 9.8
					if (DoubleUtil.div(DoubleUtil.sub(dtspj, zspj), zspj, 2) > 0.098)
					{
						//ztkpj = dtkpj;
						ztspj = dtspj;
						break;
					}
				}
				
				//已上涨 剔除 
				if(ssj > ztspj)
				{
					continue;
				}
				
				Double sszdf = DoubleUtil.div(DoubleUtil.sub(ztspj, ssj), ztspj , 2);
				if(sszdf.compareTo(0.07) < 0 || sszdf.compareTo(0.13) > 0 )
				{
					//System.out.println("跌幅太大或太小:#涨停价：" + ztspj + " - 实时价：" + ssj + " > 跌幅:[" + sszdf + "]" + vo.toString());
					continue;
				}
				
				//System.out.println("涨停开盘价:" + ztkpj);
				//System.out.println("今日最低价:" + zdj);
				//System.out.println("涨停距离:" + jzt);
				System.out.println(vo.toString() + "#涨停价：" + ztspj + " - 实时价：" + ssj + " > 跌幅:[" + sszdf + "]");
				//break;
				//System.out.println(vo.toString());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			num++;
		}
		
		System.out.println("一共：" + num + "条数据");
	}
	
	public static void runLongtouDixiGP(boolean flag)
	{
		String date = DateUtil.format(new Date(), DateUtil.YYYYMMDD);
		String dxgpfile = StringPool.PROJECT_DIR + "/data/dxlist/dxgp-" + date + ".txt";
		String dxdayfile = StringPool.PROJECT_DIR + "/data/dxlist/dxgp.txt";

		try {

			if (FileUtil.isExist(dxgpfile)) {
				FileUtil.removeFile(dxgpfile, true);
			}

			if (FileUtil.isExist(dxdayfile)) {
				FileUtil.removeFile(dxdayfile, true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//10天内1次涨停
		//10天内平均成交量5亿
		//List<GuPiaoBaseVO> list = RunUtils.get1YiList(false);
		List<ZhangTingDiXiVO> dxlist = new ArrayList<ZhangTingDiXiVO>();
		
		List<GuPiaoBaseVO> list = RunUtils.getAllGpVoList();
		for (int i = 0; i < list.size(); i++)
		{
			GuPiaoBaseVO vo = list.get(i);
			if (!vo.isOK() || vo.is3068()) //我不可以操作300 68的票，你可以去掉
			{
				continue;
			}
			
			int ztnum = 0;
			
			//3连板
			ztnum = RunUtils.getZhangTingDays(vo.getCid(), 3);
			if(ztnum == 3)
			{
				ZhangTingDiXiVO dxvo = new ZhangTingDiXiVO();
				dxvo.setCid(vo.getCid());
				dxvo.setName(vo.getName());
				dxvo.setCate(vo.getCate());
				dxvo.setBsstr("3连板");
				dxlist.add(dxvo);
				continue;
			}
			
			//15天内5次涨停
			int allnum = RunUtils.getZhangTingDays(vo.getCid(), 15);
			if (allnum < 5)
			{
				continue;
			}
			
			//5天内2次 || 3次涨停
			ztnum = RunUtils.getZhangTingDays(vo.getCid(), 5);
			if (ztnum < 2)
			{
				continue;
			}
			
			ZhangTingDiXiVO dxvo = new ZhangTingDiXiVO();
			dxvo.setCid(vo.getCid());
			dxvo.setName(vo.getName());
			dxvo.setCate(vo.getCate());
			dxvo.setBsstr("5内" + ztnum + "板");
			dxlist.add(dxvo);
		}
		
		try {
			
			List<ZhangTingDiXiVO> newlist = new ArrayList<ZhangTingDiXiVO>();
			

			StringBuffer strbuf = new StringBuffer("编号#名称#分类#标识#连板#15天板#昨封板#筹码#15d均量#均线值#昨天涨跌#评分#操作\n");
			System.out.println("编号#名称#分类#标识#连板#15天板#昨封板#筹码#15d均量#均线值#昨天涨跌#评分#操作");
			
			for (int i = 0; i < dxlist.size(); i++)
			{
				ZhangTingDiXiVO dxvo = dxlist.get(i);
				
				//昨天封板情况 & 封板资金
				String s_getbodys = HttpClientUtil.getInstance().doGet("http://qt.gtimg.cn/q=" + dxvo.getCid());
				String[] sspls = s_getbodys.split("~");
				Double dymd = DoubleUtil.div(DoubleUtil.mul(DoubleUtil.mul(Double.valueOf(sspls[9]), Double.valueOf(sspls[10]), 2), 100, 2 ) , 100000000.0, 2) ;
				
				// 涨停类型
				String ztlx = "";
				int jtkpj = (int) (Double.valueOf(sspls[5])  * 100);   // 开盘价
				int jtspj = (int) (Double.valueOf(sspls[3])  * 100);   // 收盘价
				int jtzgj = (int) (Double.valueOf(sspls[33]) * 100);   // 最高价
				int jtzdj = (int) (Double.valueOf(sspls[34]) * 100);   // 最低价
				if (jtkpj == jtspj && jtzgj == jtzdj && jtkpj == jtzdj)
				{
					ztlx = "一字板";
				}
				if (jtkpj == jtspj && jtzgj > jtzdj) {
					ztlx = "T字板";
				}
				if(dymd.compareTo(0.1) > 0)
				{
					dxvo.setFbstr(dymd + "亿" + ztlx);
				}
				
				//15天平均交易量
				Double liang15d = RunUtils.getDayLiangAve(dxvo.getCid(), 15);
				dxvo.setLiang15d(liang15d.toString());
				
				int allnum = RunUtils.getZhangTingDays(dxvo.getCid(), 15);
				dxvo.setBan15d(allnum);
				
				int dtnum = RunUtils.getDieTingDays(dxvo.getCid(), 10);
				dxvo.setDt10d(dtnum);
				
				for (int j = 1; j <= 15; j++)
				{
					int ztnum = RunUtils.getZhangTingDays(dxvo.getCid(), j);
					if(ztnum == j)
					{
						continue;
					}
					
					if(ztnum > 0)
					{
						dxvo.setLbstr(ztnum);
					}
					
					if(ztnum > 3)
					{
						dxvo.setBsstr(ztnum + "板");
					}
					
					break;
				}
				
				String jxz = "";
				
				Double ave5p =  RunUtils.getDayAvePrice(dxvo.getCid(), 5);
				Double ave10p =  RunUtils.getDayAvePrice(dxvo.getCid(), 10);
				Double ave20p =  RunUtils.getDayAvePrice(dxvo.getCid(), 20);
				
				GuPiaoInfoVO ifvo = RunUtils.getGuPiaoInfo(dxvo.getCid());
				Double jtssj = ifvo.getJtssj();
				
				if(jtssj.compareTo(ave5p) >= 0)
				{
					jxz = "5均线上";
				}
				else if(jtssj.compareTo(ave10p) >= 0)
				{
					jxz = "10均线上";
				}
				else if(jtssj.compareTo(ave20p) >= 0)
				{
					jxz = "20均线上";
				}
				
				dxvo.setJxz(jxz);
				
				if(ifvo.getZdf().compareTo(0.0) > 0)
				{
					dxvo.setZtzd("昨涨");
				} else {
					dxvo.setZtzd("昨跌");
				}
				
				dxvo.setScore(RunZhangTingDiXi.score(dxvo));
				newlist.add(dxvo);
			}
			
			Collections.sort(newlist, new Comparator<ZhangTingDiXiVO>() {
				@Override
				public int compare(ZhangTingDiXiVO o1, ZhangTingDiXiVO o2) {
					return o2.getScore() - o1.getScore();
				}
			});
			
			for (int i = 0; i < newlist.size(); i++)
			{
				ZhangTingDiXiVO dxvo = newlist.get(i);
				System.out.println(dxvo.toString());
				strbuf.append(dxvo.toString()).append("\n");
			}
			
			FileUtil.writeFile(dxgpfile, strbuf.toString());
			FileUtil.writeFile(dxdayfile, strbuf.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("一共：" + dxlist.size() + "条数据");
	}
	
	/**
	 * 高板票（15天5次涨停）
	 */
	public static void runGaoBanGP()
	{
		String date = DateUtil.format(new Date(), DateUtil.YYYYMMDD);
		String gbgpfile = StringPool.PROJECT_DIR + "/data/jklist/gbgp-" + date + ".txt";
		String gbdayfile = StringPool.PROJECT_DIR + "/data/jklist/gbgp.txt";

		try {

			if (FileUtil.isExist(gbgpfile)) {
				FileUtil.removeFile(gbgpfile, true);
			}

			if (FileUtil.isExist(gbdayfile)) {
				FileUtil.removeFile(gbdayfile, true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StringBuffer strbuf = new StringBuffer("编号#名称#分类#15天板#15d成交均量#均线值\n");
		System.out.println("编号#名称#分类#15天板#15d成交均量#均线值");
		
		int num = 0;
		
		//15天内5次涨停
		List<GuPiaoBaseVO> list = RunUtils.getAllGpVoList();
		for (int i = 0; i < list.size(); i++)
		{
			GuPiaoBaseVO vo = list.get(i);
			if (!vo.isOK() || vo.is3068())
			{
				continue;
			}

			//15天内5次涨停
			int allnum = RunUtils.getZhangTingDays(vo.getCid(), 15);
			if (allnum < 4)
			{
				continue;
			}
			
			String jxz = "";
			
			Double ave5p =  RunUtils.getDayAvePrice(vo.getCid(), 5);
			Double ave10p =  RunUtils.getDayAvePrice(vo.getCid(), 10);
			Double ave20p =  RunUtils.getDayAvePrice(vo.getCid(), 20);
			
			GuPiaoInfoVO ifvo = RunUtils.getGuPiaoInfo(vo.getCid());
			Double jtssj = ifvo.getJtssj();
			
			if(jtssj.compareTo(ave5p) >= 0)
			{
				jxz = "5均线上";
			}
			else if(jtssj.compareTo(ave10p) >= 0)
			{
				jxz = "10均线上";
			}
			else if(jtssj.compareTo(ave20p) >= 0)
			{
				jxz = "20均线上";
			}
			
			//15天平均交易量
			Double liang15d = RunUtils.getDayLiangAve(vo.getCid(), 15);
			
			System.out.println(vo.toString() + "#" + allnum + "#" + liang15d + "#" + jxz);
			strbuf.append(vo.toString() + "#" + allnum + "#" + liang15d + "#" + jxz).append("\n");
			
			num ++;
		}
		
		try {
			FileUtil.writeFile(gbgpfile, strbuf.toString());
			FileUtil.writeFile(gbdayfile, strbuf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("一共：" + num + "条数据");
	}
	
	/**
	 * 行业监控票（15天5次涨停，3天1次涨停）
	 */
	public static void runHangYeJKGP()
	{
		String date = DateUtil.format(new Date(), DateUtil.YYYYMMDD);
		String hygpfile = StringPool.PROJECT_DIR + "/data/jklist/hygp-" + date + ".txt";
		String hydayfile = StringPool.PROJECT_DIR + "/data/jklist/hygp.txt";

		try {

			if (FileUtil.isExist(hygpfile)) {
				FileUtil.removeFile(hygpfile, true);
			}

			if (FileUtil.isExist(hydayfile)) {
				FileUtil.removeFile(hydayfile, true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StringBuffer strbuf = new StringBuffer("编号#名称#分类#15天板#15d成交均量\n");
		System.out.println("编号#名称#分类#15天板#15d成交均量");
		
		int num = 0;
		
		List<GuPiaoBaseVO> list = RunUtils.getAllGpVoList();
		for (int i = 0; i < list.size(); i++)
		{
			GuPiaoBaseVO vo = list.get(i);
			if (!vo.isOK() || vo.is3068())
			{
				continue;
			}
			
			//15天平均交易量
			Double liang15d = 0.0;
			
			//5天1次涨停
			int allnum = RunUtils.getZhangTingDays(vo.getCid(), 4);
			if (allnum >= 1)
			{
				liang15d = RunUtils.getDayLiangAve(vo.getCid(), 15);
				System.out.println(vo.toString() + "#" + allnum + "#" + liang15d);
				strbuf.append(vo.toString() + "#" + allnum + "#" + liang15d).append("\n");
				num ++;
				continue;
			}
			
			//15天内5次涨停
			allnum = RunUtils.getZhangTingDays(vo.getCid(), 15);
			if (allnum >= 4)
			{
				liang15d = RunUtils.getDayLiangAve(vo.getCid(), 15);
				System.out.println(vo.toString() + "#" + allnum + "#" + liang15d);
				strbuf.append(vo.toString() + "#" + allnum + "#" + liang15d).append("\n");
				num ++;
				continue;
			}
		}
		
		try {
			FileUtil.writeFile(hygpfile, strbuf.toString());
			FileUtil.writeFile(hydayfile, strbuf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("一共：" + num + "条数据");
	}
	
	public static void runZt23HuiceGP()
	{
		//10天内1次涨停
		//10天内平均成交量5亿
		List<GuPiaoBaseVO> list = RunUtils.get1YiList(false);

		int num = 0;
		
		for (int i = 0; i < list.size(); i++)
		{
			GuPiaoBaseVO vo = list.get(i);
			if (!vo.isOK() || vo.is3068())
			{
				continue;
			}

			try {

				//10天内2-3次涨停
				int ztnum = RunUtils.getZhangTingDays(vo.getCid(), 10);
				if (ztnum < 2 || ztnum > 3)
				{
					continue;
				}
				
				ztnum = RunUtils.getZhangTingDays(vo.getCid(), 60);
				if(ztnum > 3)
				{
					continue;
				}
				
				//10天内平均成交量 > 5亿
				Double liangAve = RunUtils.getDayLiangAve(vo.getCid(), 10);
				
				//上市大于100天的
				String gpdlist = RunUtils.getGpDayBody(vo.getCid());
				String[] splist = gpdlist.split("\n");
				if (splist.length < 103)
				{
					continue;
				}
				
				//int stnum = 2;
				int ednum = splist.length - 2;
				
				Double ssj = 0.0; //今日实时价
				Double kpj = 0.0; //今日开盘价
				Double spj = 0.0; //今日收盘价
				Double zdj = 0.0; //今日最低价
				
				Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				//int minute = c.get(Calendar.MINUTE);
				
				//晚上
				if(hour > 19 || hour < 9)
				{
					// 最新当天数据
					String[] infos = splist[ednum].split(" ");
					kpj = Double.valueOf(infos[1]);//今日开盘价
					spj = Double.valueOf(infos[2]);//今日收盘价
					zdj = Double.valueOf(infos[4]);//今日最低价
					ssj = zdj;
				} else {

					//实时数据
					String zsz = HttpClientUtil.getInstance().doGet("http://qt.gtimg.cn/q=" + vo.getCid());
					String[] spz = zsz.split("~");
					kpj = Double.valueOf(spz[5]);
					spj = Double.valueOf(spz[3]);
					zdj = Double.valueOf(spz[34]);
					ssj = spj;
					
					if(Double.valueOf(spz[32]).compareTo(5.0) > 0)
					{
						continue;
					}
				}
				
				if(ssj.compareTo(kpj) < 0)
				{
					//System.out.println("当前跌：" + spj + "-" + kpj + ">" + vo.toString());
					continue;
				}
				
				//最低价 跌破 20日线
				//Double _20jx = RunUtils.getDayAvePrice(vo.getCid(), 20);
				//if(spj.compareTo(_20jx) < 0)
				//{
				//	System.out.println("最低价破20日线：>" + vo.toString());
				//	continue;
				//}
				
				//Double ztkpj = 0.0; //涨停开盘价
				Double ztspj = 0.0; //涨停收盘价
				
				//涨停开盘价
				for (int n = ednum - 1; n >= 92; n--)
				{
					//System.out.println(splist[n]);
					String[] str = splist[n].split(" ");
					
					//Double dtkpj = Double.valueOf(str[1]); //当天开盘价
					Double dtspj = Double.valueOf(str[2]); //当天收盘价
 
					// 昨天数据
					String ztdayStr = splist[n - 1].replace("\\n\\", "");
					String[] ztspdayStr = ztdayStr.split(" ");
					if (ztspdayStr.length != 6) {
						continue;
					}
					// 昨天收盘价
					Double zspj = Double.valueOf(ztspdayStr[2]);

					// 涨停算法 = （当天收盘价 - 昨天收盘价） / 昨天收盘价 > 9.8
					if (DoubleUtil.div(DoubleUtil.sub(dtspj, zspj), zspj, 2) > 0.098)
					{
						//ztkpj = dtkpj;
						ztspj = dtspj;
						break;
					}
				}
				
				//已上涨 剔除 
				if(ssj > ztspj)
				{
					continue;
				}
				
				Double sszdf = DoubleUtil.div(DoubleUtil.sub(ztspj, ssj), ztspj , 2);
				if(sszdf.compareTo(0.07) < 0 || sszdf.compareTo(0.13) > 0 )
				{
					//System.out.println("跌幅太大或太小:#涨停价：" + ztspj + " - 实时价：" + ssj + " > 跌幅:[" + sszdf + "]" + vo.toString());
					continue;
				}
				
				//System.out.println("涨停开盘价:" + ztkpj);
				//System.out.println("今日最低价:" + zdj);
				//System.out.println("涨停距离:" + jzt);
				System.out.println(vo.toString() + "#涨停价：" + ztspj + " - 实时价：" + ssj + " > 跌幅:[" + sszdf + "]");
				//break;
				
				System.out.println(vo.toString() + "#" + liangAve);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			num++;
		}
		
		System.out.println("一共：" + num + "条数据");
	}
}
