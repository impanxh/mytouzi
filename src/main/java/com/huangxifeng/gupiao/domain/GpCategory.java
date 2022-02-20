package com.huangxifeng.gupiao.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 股票交易表
 * </p>
 *
 * @author JWinder HF
 * @since 2020-11-09
 */

public class GpCategory {

	private static final long serialVersionUID = 1L;

	/**
	 * 表ID
	 */
	private Long id;

	/**
	 * 行业ID
	 */
	private String cid;

	/**
	 * 行业名称
	 */
	private String cname;

	/**
	 * 涨跌幅(%)
	 */
	private Double zdf;

	/**
	 * 量总成交量（万手）
	 */
	private Double zcjiaoliang;

	/**
	 * 总成交额（亿元）
	 */
	private Double zcjiaoe;

	/**
	 * 净流入（亿元）
	 */
	private Double jliuru;

	/**
	 * 上涨数
	 */
	private int szshu;

	/**
	 * 下跌数
	 */
	private int xdshu;

	/**
	 * 均价
	 */
	private int junjia;

}
