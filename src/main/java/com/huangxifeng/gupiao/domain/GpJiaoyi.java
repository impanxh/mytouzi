package com.huangxifeng.gupiao.domain;

import java.io.Serializable;
import java.util.Date;

import com.huangxifeng.core.utils.DateUtil;
import com.huangxifeng.core.utils.DoubleUtil;
import com.huangxifeng.gupiao.vo.FangLiangVO;

/**
 * <p>
 * 股票交易表
 * </p>
 *
 * @author JWinder HF
 * @since 2020-11-09
 */

public class GpJiaoyi {

	private static final long serialVersionUID = 1L;

	/**
	 * 表ID
	 */
	private Long id;

	/**
	 * 股票ID
	 */
	private String cid;

	/**
	 * 股票名称
	 */
	private String name;

	/**
	 * 分类
	 */
	private String cate;

	/**
	 * 量级
	 */
	private int level = 0;

	/**
	 * 买入时间
	 */
	private Date buyTime;

	/**
	 * 买入价
	 */
	private Double buyPrice;

	/**
	 * 买入数量
	 */
	private Integer buyQuantity;

	/**
	 * 买入总金额
	 */
	private Double buyTotalPrice;

	/**
	 * 买卖服务费
	 */
	private Double serviceFee;

	/**
	 * 持仓总成本
	 */
	private Double allTotalPrice;

	/**
	 * 卖出时间
	 */
	private Date sellTime;

	/**
	 * 卖出价
	 */
	private Double sellPrice;

	/**
	 * 卖出数量
	 */
	private Integer sellQuantity;

	/**
	 * 卖出总金额
	 */
	private Double sellTotalPrice;

	/**
	 * 盈亏金额
	 */
	private Double profit;

	/**
	 * 盈亏比例
	 */
	private Double profitPercentage;

	/**
	 * 3天卖出价格
	 */
	private Double day3SellPrice;

	/**
	 * 5天卖出价格
	 */
	private Double day5SellPrice;

	/**
	 * 7天卖出价格
	 */
	private Double day7SellPrice;

	/**
	 * 状态（持仓Y,清仓N）
	 */
	private String status = JiaoYiState.N;

	@Override
	public String toString() {
		return "GpJiaoyi{" + "id=" + id + ", cid='" + cid + '\'' + ", name='" + name + '\'' + ", cate='" + cate + '\''
				+ ", level=" + level + ", buyTime=" + buyTime + ", buyPrice=" + buyPrice + ", buyQuantity="
				+ buyQuantity + ", buyTotalPrice=" + buyTotalPrice + ", serviceFee=" + serviceFee + ", allTotalPrice="
				+ allTotalPrice + ", sellTime=" + sellTime + ", sellPrice=" + sellPrice + ", sellQuantity="
				+ sellQuantity + ", sellTotalPrice=" + sellTotalPrice + ", profit=" + profit + ", profitPercentage="
				+ profitPercentage + ", day3SellPrice=" + day3SellPrice + ", day5SellPrice=" + day5SellPrice
				+ ", day7SellPrice=" + day7SellPrice + ", status='" + status + '\'' + '}';
	}

	public static class JiaoYiState {
		public static String Y = "Y";
		public static String N = "N";
	}

	public static Double countServiceFee(Double buyPrice, int buynum) {
		// 当前交易费由三部分组成：佣金、印花税、过户费（仅上海股票收取）佣金如果按照1‰计的话，买卖一次股票的手续费为1‰（买进佣金）+1‰（卖出佣金）+1‰（卖出印花税）+2次过户费（深证股票无此费用）。
		Double yj = DoubleUtil.mul(DoubleUtil.mul(buyPrice, buynum * 100, 2), 0.00025, 2); // 佣金万2.5
		if ((int) (yj * 100) > 500) {
			yj = DoubleUtil.mul(yj, 2, 2);
		} else {
			yj = 10.0;
		}

		System.out.println("佣金-》" + yj);

		Double yhs = DoubleUtil.mul(DoubleUtil.mul(buyPrice, buynum * 100, 2), 0.001, 2); // 印花税
		// if(buyPrice > 0.0)
		// {
		// yhs = DoubleUtil.mul(DoubleUtil.mul(buyPrice, buynum * 100, 2) ,
		// 0.001, 2);
		// }

		System.out.println("印花税-》" + yhs);

		Double ghf = 0.0; // 过户费 买卖各收一次千1，按股数量来
		if (buynum * 100 > 1000) {
			ghf = DoubleUtil.mul(DoubleUtil.div(buynum * 100.0, 1000.0, 2), 2, 2);
		} else {
			ghf = 2.0;
		}

		System.out.println("过户费-》" + ghf);

		return DoubleUtil.sum(DoubleUtil.sum(yj, yhs), ghf);
	}

}
