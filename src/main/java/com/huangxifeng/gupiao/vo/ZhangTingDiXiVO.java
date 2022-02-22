package com.huangxifeng.gupiao.vo;

public class ZhangTingDiXiVO extends GuPiaoCiXinVO
{
	int sort = 1;
	String bsstr = "-";  //标识
	int ban15d = 0; //15天板
	int lbstr  = 0; //连板
	int dt10d  = 0; //10天跌停
	String fbstr = "-";  //封板
	String czstr = "-";  //操作
	Double jtzdj = 0.0; // 今天最低价
	Double jtkpj = 0.0; // 今天开盘价
	Double kpzdb = 0.0; // 今天开盘涨跌比
	Double dqj = 0.0; // 今天当前价
	Double ztspj = 0.0; // 昨天收盘价
	Double spzb = 0.0; // 收盘涨比
	Double zkb = 0.0; // 赚亏比
	
	String liang15d = "-"; // 15天量
	String jxz      = "";  // 均线值说明
	String ztzd     = "";  // 昨天的涨跌
	
	String cmstr    = "-";  //筹码情况
	
	String hyid = ""; // 行业涨跌
	Double hyzd = 0.0; // 行业涨跌
	Double hyzb = 0.0;   // 行业个股涨比
	
	int score = 0 ; // 评分
	
	
	@Override
	public String toString()
	{
		//"编号#名称#分类#标识#15天板#昨封板#连板#操作#15d成交均量#筹码"
		return cid + "#" + name + "#" + cate + "#" + bsstr + "#" + lbstr + "#" + ban15d + "#" + fbstr + "#" +  cmstr + "#" + liang15d + "#" + jxz+ "#" + ztzd + "#" + score + "#" + czstr ;
	}
	
	public String getLiang15d() {
		return liang15d;
	}
	public void setLiang15d(String liang15d) {
		this.liang15d = liang15d;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getBsstr() {
		return bsstr;
	}
	public void setBsstr(String bsstr) {
		this.bsstr = bsstr;
	}
	public String getFbstr() {
		return fbstr;
	}
	public void setFbstr(String fbstr) {
		this.fbstr = fbstr;
	}
	public String getCzstr() {
		return czstr;
	}
	public void setCzstr(String czstr) {
		this.czstr = czstr;
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
	public Double getKpzdb() {
		return kpzdb;
	}
	public void setKpzdb(Double kpzdb) {
		this.kpzdb = kpzdb;
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
	public Double getHyzd() {
		return hyzd;
	}
	public void setHyzd(Double hyzd) {
		this.hyzd = hyzd;
	}
	public Double getHyzb() {
		return hyzb;
	}
	public void setHyzb(Double hyzb) {
		this.hyzb = hyzb;
	}

	public String getHyid() {
		return hyid;
	}

	public void setHyid(String hyid) {
		this.hyid = hyid;
	}

	public String getJxz() {
		return jxz;
	}

	public void setJxz(String jxz) {
		this.jxz = jxz;
	}

	public String getZtzd() {
		return ztzd;
	}

	public void setZtzd(String ztzd) {
		this.ztzd = ztzd;
	}

	public int getBan15d() {
		return ban15d;
	}

	public void setBan15d(int ban15d) {
		this.ban15d = ban15d;
	}

	public int getLbstr() {
		return lbstr;
	}

	public void setLbstr(int lbstr) {
		this.lbstr = lbstr;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getDt10d() {
		return dt10d;
	}

	public void setDt10d(int dt10d) {
		this.dt10d = dt10d;
	}

	public String getCmstr() {
		return cmstr;
	}

	public void setCmstr(String cmstr) {
		this.cmstr = cmstr;
	}
	
}
