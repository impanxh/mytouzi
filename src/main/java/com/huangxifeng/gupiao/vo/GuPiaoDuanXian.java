package com.huangxifeng.gupiao.vo;

public class GuPiaoDuanXian extends GuPiaoCiXinVO {

	Double _30liangAve = 0.0;
	Double _30zdb = 0.0;
	int _30zt = 0;

	Double jtzdj = 0.0; // 今天最低价
	Double jtkpj = 0.0; // 今天开盘价
	Double dqj = 0.0; // 今天当前价
	Double ztspj = 0.0; // 昨天收盘价
	Double ztzgj = 0.0; // 昨天最高价
	Double gkb = 0.0; // 高开比
	Double jdhbk = 0.0; // 今低回补亏
	Double spzb = 0.0; // 收盘涨比
	Double zkb = 0.0; // 赚亏比

	public Double getJdhbk() {
		return jdhbk;
	}

	public void setJdhbk(Double jdhbk) {
		this.jdhbk = jdhbk;
	}

	public Double getSpzb() {
		return spzb;
	}

	public void setSpzb(Double spzb) {
		this.spzb = spzb;
	}

	public Double getZkb() {
		return zkb;
	}

	public void setZkb(Double zkb) {
		this.zkb = zkb;
	}

	public Double getGkb() {
		return gkb;
	}

	public void setGkb(Double gkb) {
		this.gkb = gkb;
	}

	public Double get_30liangAve() {
		return _30liangAve;
	}

	public void set_30liangAve(Double _30liangAve) {
		this._30liangAve = _30liangAve;
	}

	public Double get_30zdb() {
		return _30zdb;
	}

	public void set_30zdb(Double _30zdb) {
		this._30zdb = _30zdb;
	}

	public int get_30zt() {
		return _30zt;
	}

	public void set_30zt(int _30zt) {
		this._30zt = _30zt;
	}

	public Double getJtzdj() {
		return jtzdj;
	}

	public void setJtzdj(Double jtzdj) {
		this.jtzdj = jtzdj;
	}

	public Double getJtkpj() {
		return jtkpj;
	}

	public void setJtkpj(Double jtkpj) {
		this.jtkpj = jtkpj;
	}

	public Double getDqj() {
		return dqj;
	}

	public void setDqj(Double dqj) {
		this.dqj = dqj;
	}

	public Double getZtspj() {
		return ztspj;
	}

	public void setZtspj(Double ztspj) {
		this.ztspj = ztspj;
	}

	public Double getZtzgj() {
		return ztzgj;
	}

	public void setZtzgj(Double ztzgj) {
		this.ztzgj = ztzgj;
	}

	@Override
	public String toString() {
		return cid + "#" + name + "#" + cate + "#" + uptime + "#" + upday + "#" + zsz + "#" + ltsz + "#" + sylTTM + "#"
				+ jbmgsy + "#" + mgjzc + "#" + yyzsr + "#" + yyzsrtbzz + "#" + gsjlr + "#" + gsjlrtbzz + "#"
				+ _30liangAve + "#" + _30zdb + "#" + _30zt + "#" + ztspj + "#" + ztzgj + "#" + jtzdj + "#" + jtkpj + "#"
				+ dqj;
	}

	public String toBaseString() {
		return cid + "#" + name + "#" + cate + "#" + uptime + "#" + upday + "#" + zsz + "#" + _30liangAve + "#" + _30zdb
				+ "#" + _30zt;
	}

	public void valueOfBase(String str) {
		String[] sps = str.split("#");
		this.cid = sps[0];
		this.name = sps[1];
		this.cate = sps[2];
		this.uptime = sps[3];
		this.upday = Integer.valueOf(sps[4]);
		this.zsz = sps[5];
		this._30liangAve = Double.valueOf(sps[6]);
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
		this.jbmgsy = sps[8];
		this.mgjzc = sps[9];
		this.yyzsr = sps[10];
		this.yyzsrtbzz = sps[11];
		this.gsjlr = sps[12];
		this.gsjlrtbzz = sps[13];
		this._30liangAve = Double.valueOf(sps[14]);
		this._30zdb = Double.valueOf(sps[15]);
		this._30zt = Integer.valueOf(sps[16]);
	}
}
