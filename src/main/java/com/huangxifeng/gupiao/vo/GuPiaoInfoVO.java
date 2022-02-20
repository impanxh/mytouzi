package com.huangxifeng.gupiao.vo;

public class GuPiaoInfoVO extends GuPiaoBaseVO
{

//	  0: 未知
//	  1: 股票名字
//	  2: 股票代码
//	  3: 当前价格
//	  4: 昨收
//	  5: 今开
//	  6: 成交量（手）
//	  7: 外盘
//	  8: 内盘
//	  9: 买一
//	 10: 买一量（手）
//	 11-18: 买二 买五
//	 19: 卖一
//	 20: 卖一量
//	 21-28: 卖二 卖五
//	 29: 最近逐笔成交
//	 30: 时间
//	 31: 涨跌
//	 32: 涨跌%
//	 33: 最高
//	 34: 最低
//	 35: 价格/成交量（手）/成交额
//	 36: 成交量（手）
//	 37: 成交额（万）
//	 38: 换手率
//	 39: 市盈率
//	 40: 
//	 41: 最高
//	 42: 最低
//	 43: 振幅
//	 44: 流通市值
//	 45: 总市值
//	 46: 市净率
//	 47: 涨停价
//	 48: 跌停价
	
	Double ztspj = 0.0; // 昨天收盘价
	Double jtkpj = 0.0; // 今天开盘价
	Double jtzdj = 0.0; // 今天最低价
	Double jtzgj = 0.0; // 今天最高价
	Double jtspj = 0.0; // 今天收盘价
	Double jtssj = 0.0; // 今天实时（当前价）
	Double kpzdf = 0.0; //开盘涨跌幅
	Double zdf   = 0.0; // 当前涨跌幅
	Double cje   = 0.0; // 成交额（亿）
	Double zsz   = 0.0; // 总市值
	Double ltsz  = 0.0; // 流通市值
	Double hsl   = 0.0; // 换手率
	Double syl   = 0.0; // 市盈率
	Double sjl   = 0.0; // 市净率
	
	boolean iszt = false; //涨停
	boolean isdt = false; //跌停
	
	public Double getZtspj() {
		return ztspj;
	}
	public void setZtspj(Double ztspj) {
		this.ztspj = ztspj;
	}
	public Double getJtkpj() {
		return jtkpj;
	}
	public void setJtkpj(Double jtkpj) {
		this.jtkpj = jtkpj;
	}
	public Double getJtzdj() {
		return jtzdj;
	}
	public void setJtzdj(Double jtzdj) {
		this.jtzdj = jtzdj;
	}
	public Double getJtzgj() {
		return jtzgj;
	}
	public void setJtzgj(Double jtzgj) {
		this.jtzgj = jtzgj;
	}
	public Double getJtspj() {
		return jtspj;
	}
	public void setJtspj(Double jtspj) {
		this.jtspj = jtspj;
	}
	public Double getJtssj() {
		return jtssj;
	}
	public void setJtssj(Double jtssj) {
		this.jtssj = jtssj;
	}
	public Double getZdf() {
		return zdf;
	}
	public void setZdf(Double zdf) {
		this.zdf = zdf;
	}
	public Double getCje() {
		return cje;
	}
	public void setCje(Double cje) {
		this.cje = cje;
	}
	public Double getHsl() {
		return hsl;
	}
	public void setHsl(Double hsl) {
		this.hsl = hsl;
	}
	public Double getSyl() {
		return syl;
	}
	public void setSyl(Double syl) {
		this.syl = syl;
	}
	public Double getZsz() {
		return zsz;
	}
	public void setZsz(Double zsz) {
		this.zsz = zsz;
	}
	public Double getLtsz() {
		return ltsz;
	}
	public void setLtsz(Double ltsz) {
		this.ltsz = ltsz;
	}
	public Double getSjl() {
		return sjl;
	}
	public void setSjl(Double sjl) {
		this.sjl = sjl;
	}
	public boolean isIszt() {
		return iszt;
	}
	public void setIszt(boolean iszt) {
		this.iszt = iszt;
	}
	public boolean isIsdt() {
		return isdt;
	}
	public void setIsdt(boolean isdt) {
		this.isdt = isdt;
	}
	public Double getKpzdf() {
		return kpzdf;
	}
	public void setKpzdf(Double kpzdf) {
		this.kpzdf = kpzdf;
	}
	
}
