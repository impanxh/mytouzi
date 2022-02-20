package com.huangxifeng.gupiao.vo;

public class ShangZhengVO {

	private Double kpj = 0.0; // 今日开盘价
	private Double ztspj = 0.0; // 昨日收盘价
	private Double jtspj = 0.0; // 今日收盘价(当前价)
	private Double zgj = 0.0; // 今日最高价
	private Double zdj = 0.0; // 今日最低价
	private Double ttprice = 0.0; // 成交总金额
	private Double zdf = 0.0; // 涨跌幅
	private Double zfd = 0.0; // 振幅度

	public Double getKpj() {
		return kpj;
	}

	public void setKpj(Double kpj) {
		this.kpj = kpj;
	}

	public Double getZtspj() {
		return ztspj;
	}

	public void setZtspj(Double ztspj) {
		this.ztspj = ztspj;
	}

	public Double getJtspj() {
		return jtspj;
	}

	public void setJtspj(Double jtspj) {
		this.jtspj = jtspj;
	}

	public Double getZgj() {
		return zgj;
	}

	public void setZgj(Double zgj) {
		this.zgj = zgj;
	}

	public Double getZdj() {
		return zdj;
	}

	public void setZdj(Double zdj) {
		this.zdj = zdj;
	}

	public Double getTtprice() {
		return ttprice;
	}

	public void setTtprice(Double ttprice) {
		this.ttprice = ttprice;
	}

	public Double getZdf() {
		return zdf;
	}

	public void setZdf(Double zdf) {
		this.zdf = zdf;
	}

	public Double getZfd() {
		return zfd;
	}

	public void setZfd(Double zfd) {
		this.zfd = zfd;
	}
}
