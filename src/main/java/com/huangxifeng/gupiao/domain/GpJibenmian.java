package com.huangxifeng.gupiao.domain;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author JWinder HF
 * @since 2020-11-16
 */
public class GpJibenmian {

	private static final long serialVersionUID = 1L;

	private String cid;

	private String name;

	private String cate;

	/**
	 * 总市值(元)
	 */
	private String zsz;

	/**
	 * 流通市值(元)
	 */
	private String ltsz;

	/**
	 * 行业平均市值(元)
	 */
	private String hyzsz;

	/**
	 * 平均流通市值(元)
	 */
	private String hyltsz;

	/**
	 * 大于行业市值率
	 */
	private String dyhyszl;

	/**
	 * 市盈（静）
	 */
	private String syl;

	/**
	 * 市盈（TTM）
	 */
	private String sylTTM;

	/**
	 * 行业平均市盈（静）
	 */
	private String hysyl;

	/**
	 * 行业平均市盈（TTM）
	 */
	private String hysylTTM;

	/**
	 * 市盈率差值
	 */
	private String sylcz;

	/**
	 * 营业收入(元)
	 */
	private String yysr;

	/**
	 * 净利润(元)
	 */
	private String jlr;

	/**
	 * 净利润率(%)
	 */
	private String jlrl;

	/**
	 * 行业平均营业收入(元)
	 */
	private String hyyysr;

	/**
	 * 行业平均净利润(元)
	 */
	private String hyjlr;

	/**
	 * 行业平均净利润率(%)
	 */
	private String hyjlrl;

	/**
	 * 净利润率大于行业平均
	 */
	private String jlrldyhy;

	/**
	 * 每股收益(元)
	 */
	private String jbmgsy;

	/**
	 * 每股净资产(元)
	 */
	private String mgjzc;

	/**
	 * 净资产收益率
	 */
	private String jzcsyl;

	/**
	 * 营业总收入(元)
	 */
	private String yyzsr;

	/**
	 * 营业总收入同比增长(%)
	 */
	private String yyzsrtbzz;

	/**
	 * 营业总收入环比增长(%)
	 */
	private String yyzsrgdhbzz;

	/**
	 * 毛利润(元)
	 */
	private String mlr;

	/**
	 * 净利润(元)
	 */
	private String gsjlr;

	/*** 毛利净利润率 */
	private String mljlrl;

	/**
	 * 净利润同比增长(%)
	 */
	private String gsjlrtbzz;

	/**
	 * 净利润环比增长(%)
	 */
	private String gsjlrgdhbzz;

	/**
	 * 基金持仓总数
	 */
	private String ccjs;

	/**
	 * 股东人数(户)
	 */
	private String gdrs;

	/**
	 * 较上期变化(%)
	 */
	private String gdrsJsqbh;

	/**
	 * 人均流通股(股)
	 */
	private String rjltg;

	/**
	 * 较上期变化(%)
	 */
	private String rjltgJsqbh;

	/**
	 * 筹码集中度
	 */
	private String cmjzd;

	/**
	 * 筹码股价(元)
	 */
	private String cmgj;

	/**
	 * 筹码当前价格比(筹码比)
	 */
	private String cmgjbl;

	/**
	 * 人均持股金额(元)
	 */
	private String rjcgje;

	/**
	 * 前十大股东持股合计(%)
	 */
	private String qsdgdcghj;

	/**
	 * 前十大流通股东持股合计(%)
	 */
	private String qsdltgdcghj;

	/**
	 * 香港中央结算有限公司
	 */
	private String hkprice;

	/**
	 * 全国社保基金
	 */
	private String gjjprice;

}
