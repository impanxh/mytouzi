package com.huangxifeng.gupiao.vo;

public class GuPiaoCiXinVO extends JiBenMianVO {

	String uptime; // 上市时间
	int upday; // 上市天数

	String zsz = ""; // 总市值(元)
	String ltsz = ""; // 流通市值(元)
	String sylTTM = ""; // 市盈（TTM）

	String jbmgsy = ""; // 每股收益(元)
	String mgjzc = ""; // 每股净资产(元)
	String yyzsr = ""; // 营业总收入(元)
	String yyzsrtbzz = ""; // 营业总收入同比增长(%)
	String gsjlr = ""; // 净利润(元)
	String gsjlrtbzz = ""; // 净利润同比增长(%)

	String thisprice = ""; // 当前价格
	String zdf = "0.0"; // 涨跌%
	String zljlr = ""; // 主力静流入
	String shjlr = ""; // 散户静流入

	String cje = ""; // 成交额
	String hsl = ""; // 换手率

	private String jjcc = ""; // 基金持仓

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public int getUpday() {
		return upday;
	}

	public void setUpday(int upday) {
		this.upday = upday;
	}

	public String getZsz() {
		return zsz;
	}

	public void setZsz(String zsz) {
		this.zsz = zsz;
	}

	public String getLtsz() {
		return ltsz;
	}

	public void setLtsz(String ltsz) {
		this.ltsz = ltsz;
	}

	public String getSylTTM() {
		return sylTTM;
	}

	public void setSylTTM(String sylTTM) {
		this.sylTTM = sylTTM;
	}

	public String getJbmgsy() {
		return jbmgsy;
	}

	public void setJbmgsy(String jbmgsy) {
		this.jbmgsy = jbmgsy;
	}

	public String getMgjzc() {
		return mgjzc;
	}

	public void setMgjzc(String mgjzc) {
		this.mgjzc = mgjzc;
	}

	public String getYyzsr() {
		return yyzsr;
	}

	public void setYyzsr(String yyzsr) {
		this.yyzsr = yyzsr;
	}

	public String getYyzsrtbzz() {
		return yyzsrtbzz;
	}

	public void setYyzsrtbzz(String yyzsrtbzz) {
		this.yyzsrtbzz = yyzsrtbzz;
	}

	public String getGsjlr() {
		return gsjlr;
	}

	public void setGsjlr(String gsjlr) {
		this.gsjlr = gsjlr;
	}

	public String getGsjlrtbzz() {
		return gsjlrtbzz;
	}

	public void setGsjlrtbzz(String gsjlrtbzz) {
		this.gsjlrtbzz = gsjlrtbzz;
	}

	public String getThisprice() {
		return thisprice;
	}

	public void setThisprice(String thisprice) {
		this.thisprice = thisprice;
	}

	public String getZdf() {
		return zdf;
	}

	public void setZdf(String zdf) {
		this.zdf = zdf;
	}

	public String getZljlr() {
		return zljlr;
	}

	public void setZljlr(String zljlr) {
		this.zljlr = zljlr;
	}

	public String getShjlr() {
		return shjlr;
	}

	public void setShjlr(String shjlr) {
		this.shjlr = shjlr;
	}

	public String getJjcc() {
		return jjcc;
	}

	public void setJjcc(String jjcc) {
		this.jjcc = jjcc;
	}

	public String getCje() {
		return cje;
	}

	public void setCje(String cje) {
		this.cje = cje;
	}

	public String getHsl() {
		return hsl;
	}

	public void setHsl(String hsl) {
		this.hsl = hsl;
	}

	public void valueOf(String rdcx) {
		String[] rdcxSps = rdcx.split("#");

		this.setCid(rdcxSps[0]);
		this.setName(rdcxSps[1]);
		this.setCate(rdcxSps[2]);
		this.setUptime(rdcxSps[3]);
		this.setUpday(Integer.valueOf(rdcxSps[4]));

		this.setZsz(rdcxSps[5]);
		this.setLtsz(rdcxSps[6]);
		this.setSylTTM(rdcxSps[7]);
		this.setJbmgsy(rdcxSps[8]);
		this.setMgjzc(rdcxSps[9]);
		this.setYyzsr(rdcxSps[10]);
		this.setYyzsrtbzz(rdcxSps[11]);
		this.setGsjlr(rdcxSps[12]);
		this.setGsjlrtbzz(rdcxSps[13].equals("--") ? "0" : rdcxSps[13]);
	}

	public void valueOfJBM(JiBenMianVO jbmvo) {
		// String[] spls = jbmstr.split("#");
		this.setZsz(jbmvo.getZsz());
		this.setLtsz(jbmvo.getLtsz());
		this.setSylTTM(jbmvo.getSylTTM());
		this.setJbmgsy(jbmvo.getJbmgsy());
		this.setMgjzc(jbmvo.getMgjzc());
		this.setYyzsr(jbmvo.getYyzsr());
		this.setYyzsrtbzz(jbmvo.getYyzsrtbzz());
		this.setGsjlr(jbmvo.getGsjlr());
		this.setGsjlrtbzz(jbmvo.getGsjlrgdhbzz().equals("--") ? "0" : jbmvo.getGsjlrgdhbzz());
		this.setJjcc(jbmvo.getCcjs());
	}

	public void valueOfCiXin(String jbmstr) {
		String[] spls = jbmstr.split("#");
		this.setZsz(spls[0]);
		this.setLtsz(spls[1]);
		this.setSylTTM(spls[2]);
		this.setJbmgsy(spls[3]);
		this.setMgjzc(spls[4]);
		this.setYyzsr(spls[5]);
		this.setYyzsrtbzz(spls[6]);
		this.setGsjlr(spls[7]);
		this.setGsjlrtbzz(spls[8].equals("--") ? "0" : spls[8]);
	}

	@Override
	public String toString() {
		return cid + "#" + name + "#" + cate + "#" + uptime + "#" + upday + "#" + zsz + "#" + ltsz + "#" + sylTTM + "#"
				+ jbmgsy + "#" + mgjzc + "#" + yyzsr + "#" + yyzsrtbzz + "#" + gsjlr + "#" + gsjlrtbzz;
	}
}
