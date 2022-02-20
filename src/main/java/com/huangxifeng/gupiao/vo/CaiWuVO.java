package com.huangxifeng.gupiao.vo;

public class CaiWuVO extends JiBenMianVO {

	@Override
	public String toString() {
		return yyzsr + "#" + yyzsrtbzz + "#" + gsjlr + "#" + gsjlrtbzz + "#" + kfjlr + "#" + kfjlrtbzz + "#" + jzcsyl;
	}

	public void p() {
		System.out.println(cid);
		System.out.println("财务报表时间：" + cwdate);
		System.out.println("营业总收入(元)：" + yyzsr);
		System.out.println("营业总收入同比增长(%)：" + yyzsrtbzz);
		System.out.println("归属净利润(元)：" + gsjlr);
		System.out.println("归属净利润同比增长(%)：" + gsjlrtbzz);
		System.out.println("扣非净利润(元)：" + kfjlr);
		System.out.println("扣非净利润同比增长(%)：" + kfjlrtbzz);
		System.out.println("净资产收益率(加权)(%)：" + jzcsyl);
	}
}
