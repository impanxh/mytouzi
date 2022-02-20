package com.huangxifeng.gupiao.vo;

public class DaPanVO {

	private int znum = 0; // 涨天数
	private int dnum = 0; // 跌天数
	private Double zfd = 0.0; // 涨幅度
	private Double dfd = 0.0; // 跌幅度
	private Double avezhang = 0.0; // 50天大盘平均涨幅
	private Double avedie = 0.0; // 50天大盘平均跌幅
	private Double maxzhang = 0.0; // 50天大盘平均最高涨幅
	private Double minzhang = 0.0; // 50天大盘平均最低跌幅
	private Double avezf = 0.0; // 50天大盘平均振幅
	private Double dd = 0.0; // 预测明天大盘平均低点
	private Double gd = 0.0; // 预测明天大盘平均高点
	private Double zd = 0.0; // 预测明天大盘最低点
	private Double zg = 0.0; // 预测明天大盘最高点
	private Double ylw = 0.0; // 压力位
	private Double zcw = 0.0; // 支撑位

	private Double zdylw = 0.0; // 震荡压力位
	private Double zdzcw = 0.0; // 震荡支撑位

	public int getZnum() {
		return znum;
	}

	public void setZnum(int znum) {
		this.znum = znum;
	}

	public int getDnum() {
		return dnum;
	}

	public Double getZfd() {
		return zfd;
	}

	public void setZfd(Double zfd) {
		this.zfd = zfd;
	}

	public Double getDfd() {
		return dfd;
	}

	public void setDfd(Double dfd) {
		this.dfd = dfd;
	}

	public void setDnum(int dnum) {
		this.dnum = dnum;
	}

	public Double getAvezhang() {
		return avezhang;
	}

	public void setAvezhang(Double avezhang) {
		this.avezhang = avezhang;
	}

	public Double getAvedie() {
		return avedie;
	}

	public void setAvedie(Double avedie) {
		this.avedie = avedie;
	}

	public Double getMaxzhang() {
		return maxzhang;
	}

	public void setMaxzhang(Double maxzhang) {
		this.maxzhang = maxzhang;
	}

	public Double getMinzhang() {
		return minzhang;
	}

	public void setMinzhang(Double minzhang) {
		this.minzhang = minzhang;
	}

	public Double getAvezf() {
		return avezf;
	}

	public void setAvezf(Double avezf) {
		this.avezf = avezf;
	}

	public Double getDd() {
		return dd;
	}

	public void setDd(Double dd) {
		this.dd = dd;
	}

	public Double getGd() {
		return gd;
	}

	public void setGd(Double gd) {
		this.gd = gd;
	}

	public Double getZd() {
		return zd;
	}

	public void setZd(Double zd) {
		this.zd = zd;
	}

	public Double getZg() {
		return zg;
	}

	public void setZg(Double zg) {
		this.zg = zg;
	}

	public Double getYlw() {
		return ylw;
	}

	public void setYlw(Double ylw) {
		this.ylw = ylw;
	}

	public Double getZcw() {
		return zcw;
	}

	public void setZcw(Double zcw) {
		this.zcw = zcw;
	}

	public Double getZdylw() {
		return zdylw;
	}

	public void setZdylw(Double zdylw) {
		this.zdylw = zdylw;
	}

	public Double getZdzcw() {
		return zdzcw;
	}

	public void setZdzcw(Double zdzcw) {
		this.zdzcw = zdzcw;
	}
}
