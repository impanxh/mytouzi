package com.huangxifeng.gupiao.vo;

public class ZhangTingVO extends ZhangTingDiXiVO {

	int zhang = 0; // 涨数量
	int die = 0; // 跌数量
	int zdb = 0; // 30天涨跌比
	int ztdays = 0; // 涨停天数
	String zttype = ""; // 涨停类型
	Double _8gyzgj = 0.0; // 8月最高价
	String _8gyzgjTime = ""; // 最高价时间
	Double _zgjdb = 0.0; // 最高价跌比

	Double _8yzgj = 0.0; // 8月最高价
	String _8yzgjTime = ""; // 最高价时间

	int top = 0; // 排行榜


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

	public int getZdb() {
		return zdb;
	}

	public void setZdb(int zdb) {
		this.zdb = zdb;
	}

	public int getZtdays() {
		return ztdays;
	}

	public void setZtdays(int ztdays) {
		this.ztdays = ztdays;
	}

	public String getZttype() {
		return zttype;
	}

	public void setZttype(String zttype) {
		this.zttype = zttype;
	}

	public Double get_8gyzgj() {
		return _8gyzgj;
	}

	public void set_8gyzgj(Double _8gyzgj) {
		this._8gyzgj = _8gyzgj;
	}

	public String get_8gyzgjTime() {
		return _8gyzgjTime;
	}

	public void set_8gyzgjTime(String _8gyzgjTime) {
		this._8gyzgjTime = _8gyzgjTime;
	}

	public Double get_zgjdb() {
		return _zgjdb;
	}

	public void set_zgjdb(Double _zgjdb) {
		this._zgjdb = _zgjdb;
	}

	public Double get_8yzgj() {
		return _8yzgj;
	}

	public void set_8yzgj(Double _8yzgj) {
		this._8yzgj = _8yzgj;
	}

	public String get_8yzgjTime() {
		return _8yzgjTime;
	}

	public void set_8yzgjTime(String _8yzgjTime) {
		this._8yzgjTime = _8yzgjTime;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	@Override
	public void valueOf(String str) {
		String[] sps = str.split("#");
		this.cid = sps[0];
		this.name = sps[1];
		this.cate = sps[2];
		this.uptime = sps[3];
		this.upday = Integer.valueOf(sps[4]);
		this.zsz = sps[5];
		this.ltsz = sps[6];
		this.sylTTM = sps[7];
		this.ztdays = Integer.valueOf(sps[8]);
		this.zttype = sps[9];
		this._8gyzgj = Double.valueOf(sps[10]);
		this._8gyzgjTime = sps[11];
		this._zgjdb = Double.valueOf(sps[12].replace("%", ""));
		this._8yzgj = Double.valueOf(sps[13]);
		this._8yzgjTime = sps[14];

		this.zhang = Integer.valueOf(sps[15]);
		this.die = Integer.valueOf(sps[16]);
		this.zdb = Double.valueOf(sps[17]).intValue();

	}
}
