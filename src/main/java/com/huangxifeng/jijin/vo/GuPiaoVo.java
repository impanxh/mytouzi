package com.huangxifeng.jijin.vo;

public class GuPiaoVo {

	private String id = "";
	private String name = "";
	private String hangye = "";// 所属行业
	private String thisprice = ""; // 当前价格
	private String liutonggb = ""; // 流通股本(万股)
	private String zongshizhi = ""; // 总市值
	private String shiyinlv = ""; // 市盈率
	private String meigusy = ""; // 基本每股收益(元)
	private String meigujzc = ""; // 每股净资产(元)
	private String dangqianyinshou = ""; // 当前营收
	private String qntbyinshou = ""; // 去年同比营收
	private String yinshoutbzz = ""; // 营业总收入同比增长(%)
	private String jinglirun = ""; // 归属净利润(元)
	private String tqjinglirun = ""; // 上年同期净利润
	private String jingliruntbzz = ""; // 净利润同比增长(%)上
	private String jijinshu = ""; // 基金持仓家数
	private String choumajzd = ""; // 筹码集中度
	private String choumaprice = ""; // 筹码股价(元)
	private String gudongshu = ""; // 股东人数(户)当前
	private String gudongshusq = ""; // 股东人数(户）上期

	private int sum = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHangye() {
		return hangye;
	}

	public void setHangye(String hangye) {
		this.hangye = hangye;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getThisprice() {
		return thisprice;
	}

	public void setThisprice(String thisprice) {
		this.thisprice = thisprice;
	}

	public String getLiutonggb() {
		return liutonggb;
	}

	public void setLiutonggb(String liutonggb) {
		this.liutonggb = liutonggb;
	}

	public String getZongshizhi() {
		return zongshizhi;
	}

	public void setZongshizhi(String zongshizhi) {
		this.zongshizhi = zongshizhi;
	}

	public String getShiyinlv() {
		return shiyinlv;
	}

	public void setShiyinlv(String shiyinlv) {
		this.shiyinlv = shiyinlv;
	}

	public String getMeigusy() {
		return meigusy;
	}

	public void setMeigusy(String meigusy) {
		this.meigusy = meigusy;
	}

	public String getMeigujzc() {
		return meigujzc;
	}

	public void setMeigujzc(String meigujzc) {
		this.meigujzc = meigujzc;
	}

	public String getDangqianyinshou() {
		return dangqianyinshou;
	}

	public void setDangqianyinshou(String dangqianyinshou) {
		this.dangqianyinshou = dangqianyinshou;
	}

	public String getQntbyinshou() {
		return qntbyinshou;
	}

	public void setQntbyinshou(String qntbyinshou) {
		this.qntbyinshou = qntbyinshou;
	}

	public String getYinshoutbzz() {
		return yinshoutbzz;
	}

	public void setYinshoutbzz(String yinshoutbzz) {
		this.yinshoutbzz = yinshoutbzz;
	}

	public String getJinglirun() {
		return jinglirun;
	}

	public void setJinglirun(String jinglirun) {
		this.jinglirun = jinglirun;
	}

	public String getTqjinglirun() {
		return tqjinglirun;
	}

	public void setTqjinglirun(String tqjinglirun) {
		this.tqjinglirun = tqjinglirun;
	}

	public String getJingliruntbzz() {
		return jingliruntbzz;
	}

	public void setJingliruntbzz(String jingliruntbzz) {
		this.jingliruntbzz = jingliruntbzz;
	}

	public String getJijinshu() {
		return jijinshu;
	}

	public void setJijinshu(String jijinshu) {
		this.jijinshu = jijinshu;
	}

	public String getChoumajzd() {
		return choumajzd;
	}

	public void setChoumajzd(String choumajzd) {
		this.choumajzd = choumajzd;
	}

	public String getChoumaprice() {
		return choumaprice;
	}

	public void setChoumaprice(String choumaprice) {
		this.choumaprice = choumaprice;
	}

	public String getGudongshu() {
		return gudongshu;
	}

	public void setGudongshu(String gudongshu) {
		this.gudongshu = gudongshu;
	}

	public String getGudongshusq() {
		return gudongshusq;
	}

	public void setGudongshusq(String gudongshusq) {
		this.gudongshusq = gudongshusq;
	}

	@Override
	public String toString() {
		return id + "," + name + "," + sum;
	}
}
