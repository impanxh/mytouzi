package com.huangxifeng.gupiao.vo;

public class HangYeVO {
	// 序号,板块,涨跌幅(%),总成交量（万手）,总成交额（亿元）,净流入（亿元）,上涨家数,下跌家数,均价
	// 1,汽车整车,4.01,1127.96,253.32,45.28,18,5,22.46

	private int num = 0;
	private String cid = "";
	private String cname = "";
	private Double zdf = 0.0; // 涨跌幅
	private Double cjl = 0.0; // 成交额(亿)
	private Double jlr = 0.0; // 静流入(亿)
	private int zhang = 0; // 涨
	private int die = 0; // 跌
	private int zdb = 0; // 涨跌比
	private String remark = ""; // 说明

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Double getZdf() {
		return zdf;
	}

	public void setZdf(Double zdf) {
		this.zdf = zdf;
	}

	public Double getCjl() {
		return cjl;
	}

	public void setCjl(Double cjl) {
		this.cjl = cjl;
	}

	public Double getJlr() {
		return jlr;
	}

	public void setJlr(Double jlr) {
		this.jlr = jlr;
	}

	public int getZhang() {
		return zhang;
	}

	public void setZhang(int zhang) {
		this.zhang = zhang;
	}

	public int getDie() {
		return die;
	}

	public void setDie(int die) {
		this.die = die;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getZdb() {
		return zdb;
	}

	public void setZdb(int zdb) {
		this.zdb = zdb;
	}

	public HangYeVO valueOf(String str) {
		String[] sps = str.split("#");
		this.cid = sps[0];
		this.cname = sps[1];
		//this.num = Integer.valueOf(sps[2]);

		return this;
	}

	public String toCateString() {
		return cid + "#" + cname + "#" + num;
	}

	@Override
	public String toString() {
		return "HangYeVO{" + "num=" + num + ", cid='" + cid + '\'' + ", cname='" + cname + '\'' + ", zdf=" + zdf
				+ ", cjl=" + cjl + ", jlr=" + jlr + ", zhang=" + zhang + ", die=" + die + ", remark='" + remark + '\''
				+ '}';
	}
}
