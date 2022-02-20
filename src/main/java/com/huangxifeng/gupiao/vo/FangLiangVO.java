package com.huangxifeng.gupiao.vo;

public class FangLiangVO {

	private int num = 0;
	private String cid = "";
	private String name = "";
	private String cate = "";
	private int level = 0; // 量级

	private String opt = "";

	private Double thisPrice = 0.0; // 当前价格
	private Double thisZdf = 0.0;// 当前涨幅
	private String thisTime = "";// 当前时间
	private Double thisLiang = 0.0; // 当前量

	private Double buyPrice = 0.0; // 买入价格
	private Double buyZdf = 0.0;// 买入时涨幅
	private String buyTime = ""; // 买入时间

	private int buyNum = 1; // 1万元买入，大于100元股只买1手

	private Double fukui = 0.0;// 浮亏

	// 所有平均量
	private String aveAll = "";

	// 11天的量
	private String ave11 = "";

	// 最终量
	private String avefin = "";

	// 最大量
	private String avemax = "";

	private String oneday = "";

	// 是否保存
	private boolean saveflag = false;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public Double getThisPrice() {
		return thisPrice;
	}

	public void setThisPrice(Double thisPrice) {
		this.thisPrice = thisPrice;
		this.fukui = Double.valueOf((int) (this.thisPrice * 100 - this.buyPrice * 100) * this.buyNum + "");
	}

	public Double getThisZdf() {
		return thisZdf;
	}

	public void setThisZdf(Double thisZdf) {
		this.thisZdf = thisZdf;
	}

	public String getThisTime() {
		return thisTime;
	}

	public void setThisTime(String thisTime) {
		this.thisTime = thisTime;
	}

	public Double getThisLiang() {
		return thisLiang;
	}

	public void setThisLiang(Double thisLiang) {
		this.thisLiang = thisLiang;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
		if ((int) (buyPrice * 100) > 10000) {
			this.buyNum = 1;
		} else {
			// 1万元买多少手
			this.buyNum = 1000000 / (int) (buyPrice * 100 * 100);
		}
	}

	public Double getBuyZdf() {
		return buyZdf;
	}

	public void setBuyZdf(Double buyZdf) {
		this.buyZdf = buyZdf;
	}

	public String getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}

	public Double getFukui() {
		return fukui;
	}

	public void setFukui(Double fukui) {
		this.fukui = fukui;
	}

	public String getAveAll() {
		return aveAll;
	}

	public void setAveAll(String aveAll) {
		this.aveAll = aveAll;
	}

	public String getAvefin() {
		return avefin;
	}

	public void setAvefin(String avefin) {
		this.avefin = avefin;
	}

	public String getAvemax() {
		return avemax;
	}

	public void setAvemax(String avemax) {
		this.avemax = avemax;
	}

	public String getAve11() {
		return ave11;
	}

	public void setAve11(String ave11) {
		this.ave11 = ave11;
	}

	public String getOneday() {
		return oneday;
	}

	public void setOneday(String oneday) {
		this.oneday = oneday;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public boolean isSaveflag() {
		return saveflag;
	}

	public void setSaveflag(boolean saveflag) {
		this.saveflag = saveflag;
	}

	@Override
	public String toString() {
		return "FangLiangVO{" + "num=" + num + ", cid='" + cid + '\'' + ", name='" + name + '\'' + ", cate='" + cate
				+ '\'' + ", level=" + level + ", opt='" + opt + '\'' + ", thisPrice=" + thisPrice + ", thisZdf="
				+ thisZdf + ", thisTime='" + thisTime + '\'' + ", thisLiang=" + thisLiang + ", buyPrice=" + buyPrice
				+ ", buyZdf=" + buyZdf + ", buyTime='" + buyTime + '\'' + ", buyNum=" + buyNum + ", fukui=" + fukui
				+ ", aveAll='" + aveAll + '\'' + ", avefin='" + avefin + '\'' + ", avemax='" + avemax + '\''
				+ ", oneday='" + oneday + '\'' + ", saveflag=" + saveflag + '}';
	}
}
