package com.huangxifeng.gupiao.vo;

public class ZhangDieBiVO extends GuPiaoCiXinVO {

	int day = 0; // 数据天数
	int zhang = 0; // 涨数量
	int die = 0; // 跌数量
	int ping = 0;
	int zt = 0; // 涨停数量
	int dt = 0; // 跌停数据
	int z5 = 0; // 涨大于5%的数量
	int d5 = 0; // 跌大于5%的数量

	Double zhangb = 0.0; // 涨比
	Double dieb = 0.0; // 跌比

	Double avezhang = 0.0; // 平均涨
	Double avedie = 0.0; // 平均跌

	Double sumzhang = 0.0; // 总涨
	Double sumdie = 0.0; // 总跌

	Double maxzhang = 0.0; // 最大涨
	Double maxdie = 0.0; // 最大跌

	int ave2zhangnum = 0;
	int avezhangmaxnum = 0;
	Double ave2zhangb = 0.0;
	Double avezhangmaxb = 0.0;

	int ave2dienum = 0;
	int avediemaxnum = 0;
	Double ave2dieb = 0.0;
	Double avediemaxb = 0.0;

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

	public int getPing() {
		return ping;
	}

	public void setPing(int ping) {
		this.ping = ping;
	}

	public int getZt() {
		return zt;
	}

	public void setZt(int zt) {
		this.zt = zt;
	}

	public int getDt() {
		return dt;
	}

	public void setDt(int dt) {
		this.dt = dt;
	}

	public int getZ5() {
		return z5;
	}

	public void setZ5(int z5) {
		this.z5 = z5;
	}

	public int getD5() {
		return d5;
	}

	public void setD5(int d5) {
		this.d5 = d5;
	}

	public Double getZhangb() {
		return zhangb;
	}

	public void setZhangb(Double zhangb) {
		this.zhangb = zhangb;
	}

	public Double getDieb() {
		return dieb;
	}

	public void setDieb(Double dieb) {
		this.dieb = dieb;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
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

	public Double getMaxdie() {
		return maxdie;
	}

	public void setMaxdie(Double maxdie) {
		this.maxdie = maxdie;
	}

	public Double getSumzhang() {
		return sumzhang;
	}

	public void setSumzhang(Double sumzhang) {
		this.sumzhang = sumzhang;
	}

	public Double getSumdie() {
		return sumdie;
	}

	public void setSumdie(Double sumdie) {
		this.sumdie = sumdie;
	}

	public int getAve2zhangnum() {
		return ave2zhangnum;
	}

	public void setAve2zhangnum(int ave2zhangnum) {
		this.ave2zhangnum = ave2zhangnum;
	}

	public int getAvezhangmaxnum() {
		return avezhangmaxnum;
	}

	public void setAvezhangmaxnum(int avezhangmaxnum) {
		this.avezhangmaxnum = avezhangmaxnum;
	}

	public Double getAve2zhangb() {
		return ave2zhangb;
	}

	public void setAve2zhangb(Double ave2zhangb) {
		this.ave2zhangb = ave2zhangb;
	}

	public Double getAvezhangmaxb() {
		return avezhangmaxb;
	}

	public void setAvezhangmaxb(Double avezhangmaxb) {
		this.avezhangmaxb = avezhangmaxb;
	}

	public int getAve2dienum() {
		return ave2dienum;
	}

	public void setAve2dienum(int ave2dienum) {
		this.ave2dienum = ave2dienum;
	}

	public int getAvediemaxnum() {
		return avediemaxnum;
	}

	public void setAvediemaxnum(int avediemaxnum) {
		this.avediemaxnum = avediemaxnum;
	}

	public Double getAve2dieb() {
		return ave2dieb;
	}

	public void setAve2dieb(Double ave2dieb) {
		this.ave2dieb = ave2dieb;
	}

	public Double getAvediemaxb() {
		return avediemaxb;
	}

	public void setAvediemaxb(Double avediemaxb) {
		this.avediemaxb = avediemaxb;
	}

	public void p() {
		System.out.println("总天数：" + day);
		System.out.println("涨天数：" + zhang);
		System.out.println("跌天数：" + die);
		System.out.println("平天数：" + ping);
		System.out.println("涨停数：" + zt);
		System.out.println("跌停数：" + dt);
		System.out.println("涨5%数：" + z5);
		System.out.println("跌5%数：" + d5);
		System.out.println("涨比：" + zhangb + "%");
		System.out.println("跌比：" + dieb + "%");
		System.out.println("总涨比：" + sumzhang + "%");
		System.out.println("总跌比：" + sumdie + "%");
		System.out.println("平均涨比：" + avezhang + "%");
		System.out.println("平均跌比：" + avedie + "%");

		System.out.println("均半涨比：" + ave2zhangb + "%");
		System.out.println("大均涨比：" + avezhangmaxb + "%");

		System.out.println("均半跌比：" + ave2dieb + "%");
		System.out.println("大均跌比：" + avediemaxb + "%");

		System.out.println("最大涨比：" + maxzhang + "%");
		System.out.println("最大跌比：" + maxdie + "%");

	}

	@Override
	public String toString() {
		return "ZhangDieBiVO{" + "day=" + day + ", zhang=" + zhang + ", die=" + die + ", zt=" + zt + ", dt=" + dt
				+ ", z5=" + z5 + ", d5=" + d5 + ", zhangb=" + zhangb + ", dieb=" + dieb + ", avezhang=" + avezhang
				+ ", avedie=" + avedie + ", maxzhang=" + maxzhang + ", maxdie=" + maxdie + '}';
	}
}
