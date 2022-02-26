package com.huangxifeng.gupiao.vo;

public class JianKongVO extends GuPiaoBaseVO
{
	String type = Type.ZT_LIST;

	Double ztspj = 0.0; // 昨天收盘价
	Double jtkpj = 0.0; // 今天开盘价
	Double jtzdj = 0.0; // 今天最低价
	Double jtzgj = 0.0; // 今天最高价
	Double kpzdf = 0.0; // 今天开盘涨跌幅 开盘价 - 昨收盘价 / 昨收盘价
	Double dqj   = 0.0; // 今天当前价
	Double zdf   = 0.0; // 当前涨跌幅
	Double jtspj = 0.0; // 今天收盘价
	Double spzbf = 0.0; // 收盘涨跌幅
	Double zkb   = 0.0; // 赚亏比
	
	String liang15d = "-"; // 15天量
	Integer ban15d  = 0;   // 15天涨停量
	String jxz      = "";  // 均线值说明
	
	public static class Type
	{	public static String ZT2D_LIST = "ZT2D_LIST";
		public static String ZT_LIST = "ZT_LIST";
		public static String DT_LIST = "DT_LIST";
		public static String GB_LIST = "GB_LIST";
		public static String HY_LIST = "HY_LIST";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	public Double getKpzdf() {
		return kpzdf;
	}

	public void setKpzdf(Double kpzdf) {
		this.kpzdf = kpzdf;
	}

	public Double getDqj() {
		return dqj;
	}

	public void setDqj(Double dqj) {
		this.dqj = dqj;
	}

	public Double getZdf() {
		return zdf;
	}

	public void setZdf(Double zdf) {
		this.zdf = zdf;
	}

	public Double getJtspj() {
		return jtspj;
	}

	public void setJtspj(Double jtspj) {
		this.jtspj = jtspj;
	}

	public Double getSpzbf() {
		return spzbf;
	}

	public void setSpzbf(Double spzbf) {
		this.spzbf = spzbf;
	}

	public Double getZkb() {
		return zkb;
	}

	public void setZkb(Double zkb) {
		this.zkb = zkb;
	}

	public String getLiang15d() {
		return liang15d;
	}

	public void setLiang15d(String liang15d) {
		this.liang15d = liang15d;
	}

	public Integer getBan15d() {
		return ban15d;
	}

	public void setBan15d(Integer ban15d) {
		this.ban15d = ban15d;
	}

	public String getJxz() {
		return jxz;
	}

	public void setJxz(String jxz) {
		this.jxz = jxz;
	}

}
