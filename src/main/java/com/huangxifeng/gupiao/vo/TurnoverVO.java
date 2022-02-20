package com.huangxifeng.gupiao.vo;

public class TurnoverVO {

	private String cid = "";
	private String name = "";
	private String cate = "";

	// 交易级别
	private int tlevel = 0;

	// 50天量累加
	private int day50sum = 0;

	// 25天量累加 一个月
	private int day25sum = 0;

	// 11天量累加 半个月
	private int day11sum = 0;

	// 7天量累加
	private int day7sum = 0;

	// 3天量累加(过滤已放量)
	private int day3sum = 0;

	// 昨天的量
	private int day1sum = 0;

	// 50天平均量
	private int ave50 = 0;

	// 一个月平均量
	private int ave25 = 0;;

	// 半个月平均量
	private int ave11 = 0;

	// 1周平均量
	private int ave7 = 0;

	// 3天平均量
	private int ave3 = 0;

	// 1天平均量
	private int ave1 = 0;

	// 所有平均量
	private int aveAll = 0;

	// 最终量
	private int avefin = 0;

	// 最大量
	private int avemax = 0;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public int getDay50sum() {
		return day50sum;
	}

	public void setDay50sum(int day50sum) {
		this.day50sum = day50sum;
	}

	public int getDay25sum() {
		return day25sum;
	}

	public void setDay25sum(int day25sum) {
		this.day25sum = day25sum;
	}

	public int getDay11sum() {
		return day11sum;
	}

	public void setDay11sum(int day11sum) {
		this.day11sum = day11sum;
	}

	public int getDay7sum() {
		return day7sum;
	}

	public void setDay7sum(int day7sum) {
		this.day7sum = day7sum;
	}

	public int getDay3sum() {
		return day3sum;
	}

	public void setDay3sum(int day3sum) {
		this.day3sum = day3sum;
	}

	public int getDay1sum() {
		return day1sum;
	}

	public void setDay1sum(int day1sum) {
		this.day1sum = day1sum;
	}

	public int getAve50() {
		return ave50;
	}

	public void setAve50(int ave50) {
		this.ave50 = ave50;
	}

	public int getAve25() {
		return ave25;
	}

	public void setAve25(int ave25) {
		this.ave25 = ave25;
	}

	public int getAve11() {
		return ave11;
	}

	public void setAve11(int ave11) {
		this.ave11 = ave11;
	}

	public int getAve7() {
		return ave7;
	}

	public void setAve7(int ave7) {
		this.ave7 = ave7;
	}

	public int getAve3() {
		return ave3;
	}

	public void setAve3(int ave3) {
		this.ave3 = ave3;
	}

	public int getAveAll() {
		return aveAll;
	}

	public void setAveAll(int aveAll) {
		this.aveAll = aveAll;
	}

	public int getAvefin() {
		return avefin;
	}

	public void setAvefin(int avefin) {
		this.avefin = avefin;
	}

	public int getAve1() {
		return ave1;
	}

	public void setAve1(int ave1) {
		this.ave1 = ave1;
	}

	public int getAvemax() {
		return avemax;
	}

	public void setAvemax(int avemax) {
		this.avemax = avemax;
	}

	public int getTlevel() {
		return tlevel;
	}

	public void setTlevel(int tlevel) {
		this.tlevel = tlevel;
	}

	@Override
	public String toString() {
		return "TurnoverVO{" + "cid='" + cid + '\'' + ", name='" + name + '\'' + ", cate='" + cate + '\'' + ", ave50="
				+ ave50 + ", ave25=" + ave25 + ", ave11=" + ave11 + ", ave7=" + ave7 + ", ave3=" + ave3 + ", ave1="
				+ ave1 + ", aveAll=" + aveAll + '}';
	}

	public String toStr() {
		return cid + "#" + name + "#" + cate + "#" + avemax + "#" + aveAll + "#" + avefin + "#" + tlevel;
	}
}
