package com.huangxifeng.gupiao.vo;

public class DingPanVO {
	private int num = 0;
	private String cid = "";
	private String name = "";
	private String cate = "";
	private String iscc = ""; // 是否持仓
	private String catezdf = ""; // 分类涨跌幅

	private Double dqj = 0.0; // 当前价
	private Double kpj = 0.0; // 开盘价
	private Double zdj = 0.0; // 最低价
	private Double zgj = 0.0; // 最高价
	private Double zdf = 0.0; // 涨跌幅
	private Double ztspj = 0.0; // 昨天收盘价

	private int day5m = 0; // 5天每分钟成交量
	private Double lb = 0.0; // 量比
	private Double cjl = 0.0; // 成交额(亿)
	private Double jlr = 0.0; // 静流入(亿)

	private Double jlr3 = 0.0; // 静流入(亿)
	private Double jlr5 = 0.0; // 静流入(亿)
	private Double jlr20 = 0.0; // 静流入(亿)

	private Double hsl = 0.0; // 换手率
	private Double zfl = 0.0; // 振幅率

	private String jbm = "0"; // 基本面
	private Double zcw = 0.0; // 支撑位
	private Double ylw = 0.0; // 压力位
	private int zcwTips = 0; // 支压位提醒 小于200

	private String psx = ""; // 票属性
	private String dqhq = ""; // 当前行情
	private String czsk = ""; // 操作思考
	private String czdz = ""; // 操作动作

	private String zsz = ""; // 总市值(元)
	private String zszdy = ""; // 大于行业市值率

	private String sylTTM = ""; // 市盈（TTM）
	private String sylcz = ""; // 市盈率差值

	private String mgsy = ""; // 每股收益(元)
	private String mgjzc = ""; // 每股净资产(元)
	private String jzcsyl = ""; // 净资产收益率

	private String yyzsr = ""; // 营业总收入(元)

	private String yyzsrtbzz = ""; // 营业总收入同比增长(%)
	private String yyzsrhbzz = ""; // 营业总收入环比增长(%)

	private String gsjlr = ""; // 净利润(元)
	private String gsjlrl = ""; // 净利润率(%)
	private String gsjlrtbzz = ""; // 净利润同比增长(%)
	private String gsjlrhbzz = ""; // 净利润环比增长(%)

	private String jjcc = ""; // 基金持仓总数
	private String hkcy = ""; // 香港中央结算有限公司
	private String gjjcy = ""; // 全国社保基金

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

	public String getIscc() {
		return iscc;
	}

	public void setIscc(String iscc) {
		this.iscc = iscc;
	}

	public String getCatezdf() {
		return catezdf;
	}

	public void setCatezdf(String catezdf) {
		this.catezdf = catezdf;
	}

	public Double getDqj() {
		return dqj;
	}

	public void setDqj(Double dqj) {
		this.dqj = dqj;
	}

	public Double getKpj() {
		return kpj;
	}

	public void setKpj(Double kpj) {
		this.kpj = kpj;
	}

	public Double getZdj() {
		return zdj;
	}

	public int getZcwTips() {
		return zcwTips;
	}

	public void setZcwTips(int zcwTips) {
		this.zcwTips = zcwTips;
	}

	public void setZdj(Double zdj) {
		this.zdj = zdj;
	}

	public Double getZgj() {
		return zgj;
	}

	public void setZgj(Double zgj) {
		this.zgj = zgj;
	}

	public Double getZdf() {
		return zdf;
	}

	public void setZdf(Double zdf) {
		this.zdf = zdf;
	}

	public Double getZtspj() {
		return ztspj;
	}

	public void setZtspj(Double ztspj) {
		this.ztspj = ztspj;
	}

	public Double getLb() {
		return lb;
	}

	public void setLb(Double lb) {
		this.lb = lb;
	}

	public Double getCjl() {
		return cjl;
	}

	public void setCjl(Double cjl) {
		this.cjl = cjl;
	}

	public Double getJlr() {
		return jlr;
	}

	public void setJlr(Double jlr) {
		this.jlr = jlr;
	}

	public Double getJlr3() {
		return jlr3;
	}

	public void setJlr3(Double jlr3) {
		this.jlr3 = jlr3;
	}

	public Double getJlr5() {
		return jlr5;
	}

	public void setJlr5(Double jlr5) {
		this.jlr5 = jlr5;
	}

	public Double getJlr20() {
		return jlr20;
	}

	public void setJlr20(Double jlr20) {
		this.jlr20 = jlr20;
	}

	public Double getHsl() {
		return hsl;
	}

	public void setHsl(Double hsl) {
		this.hsl = hsl;
	}

	public Double getZfl() {
		return zfl;
	}

	public void setZfl(Double zfl) {
		this.zfl = zfl;
	}

	public int getDay5m() {
		return day5m;
	}

	public void setDay5m(int day5m) {
		this.day5m = day5m;
	}

	public String getPsx() {
		return psx;
	}

	public void setPsx(String psx) {
		this.psx = psx;
	}

	public String getDqhq() {
		return dqhq;
	}

	public void setDqhq(String dqhq) {
		this.dqhq = dqhq;
	}

	public String getCzdz() {
		return czdz;
	}

	public void setCzdz(String czdz) {
		this.czdz = czdz;
	}

	public String getJbm() {
		return jbm;
	}

	public void setJbm(String jbm) {
		this.jbm = jbm;
	}

	public Double getZcw() {
		return zcw;
	}

	public void setZcw(Double zcw) {
		this.zcw = zcw;
	}

	public Double getYlw() {
		return ylw;
	}

	public void setYlw(Double ylw) {
		this.ylw = ylw;
	}

	public String getCzsk() {
		return czsk;
	}

	public void setCzsk(String czsk) {
		this.czsk = czsk;
	}

	public String getZsz() {
		return zsz;
	}

	public void setZsz(String zsz) {
		this.zsz = zsz;
	}

	public String getZszdy() {
		return zszdy;
	}

	public void setZszdy(String zszdy) {
		this.zszdy = zszdy;
	}

	public String getSylTTM() {
		return sylTTM;
	}

	public void setSylTTM(String sylTTM) {
		this.sylTTM = sylTTM;
	}

	public String getSylcz() {
		return sylcz;
	}

	public void setSylcz(String sylcz) {
		this.sylcz = sylcz;
	}

	public String getMgsy() {
		return mgsy;
	}

	public void setMgsy(String mgsy) {
		this.mgsy = mgsy;
	}

	public String getMgjzc() {
		return mgjzc;
	}

	public void setMgjzc(String mgjzc) {
		this.mgjzc = mgjzc;
	}

	public String getJzcsyl() {
		return jzcsyl;
	}

	public void setJzcsyl(String jzcsyl) {
		this.jzcsyl = jzcsyl;
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

	public String getYyzsrhbzz() {
		return yyzsrhbzz;
	}

	public void setYyzsrhbzz(String yyzsrhbzz) {
		this.yyzsrhbzz = yyzsrhbzz;
	}

	public String getGsjlr() {
		return gsjlr;
	}

	public void setGsjlr(String gsjlr) {
		this.gsjlr = gsjlr;
	}

	public String getGsjlrl() {
		return gsjlrl;
	}

	public void setGsjlrl(String gsjlrl) {
		this.gsjlrl = gsjlrl;
	}

	public String getGsjlrtbzz() {
		return gsjlrtbzz;
	}

	public void setGsjlrtbzz(String gsjlrtbzz) {
		this.gsjlrtbzz = gsjlrtbzz;
	}

	public String getGsjlrhbzz() {
		return gsjlrhbzz;
	}

	public void setGsjlrhbzz(String gsjlrhbzz) {
		this.gsjlrhbzz = gsjlrhbzz;
	}

	public String getJjcc() {
		return jjcc;
	}

	public void setJjcc(String jjcc) {
		this.jjcc = jjcc;
	}

	public String getHkcy() {
		return hkcy;
	}

	public void setHkcy(String hkcy) {
		this.hkcy = hkcy;
	}

	public String getGjjcy() {
		return gjjcy;
	}

	public void setGjjcy(String gjjcy) {
		this.gjjcy = gjjcy;
	}

	@Override
	public String toString() {
		return "DingPanVO{" + "num=" + num + ", cid='" + cid + '\'' + ", name='" + name + '\'' + ", cate='" + cate
				+ '\'' + ", iscc='" + iscc + '\'' + ", catezdf='" + catezdf + '\'' + ", dqj=" + dqj + ", kpj=" + kpj
				+ ", zdj=" + zdj + ", zgj=" + zgj + ", zdf=" + zdf + ", ztspj=" + ztspj + ", day5m=" + day5m + ", lb="
				+ lb + ", cjl=" + cjl + ", jlr=" + jlr + ", jlr3=" + jlr3 + ", jlr5=" + jlr5 + ", jlr20=" + jlr20
				+ ", hsl=" + hsl + ", zfl=" + zfl + ", jbm=" + jbm + ", zcw=" + zcw + ", ylw=" + ylw + ", psx='" + psx
				+ '\'' + ", dqhq='" + dqhq + '\'' + ", czsk='" + czsk + '\'' + ", czdz='" + czdz + '\'' + ", zsz='"
				+ zsz + '\'' + ", zszdy='" + zszdy + '\'' + ", sylTTM='" + sylTTM + '\'' + ", sylcz='" + sylcz + '\''
				+ ", mgsy='" + mgsy + '\'' + ", mgjzc='" + mgjzc + '\'' + ", jzcsyl='" + jzcsyl + '\'' + ", yyzsr='"
				+ yyzsr + '\'' + ", yyzsrtbzz='" + yyzsrtbzz + '\'' + ", yyzsrhbzz='" + yyzsrhbzz + '\'' + ", gsjlr='"
				+ gsjlr + '\'' + ", gsjlrl='" + gsjlrl + '\'' + ", gsjlrtbzz='" + gsjlrtbzz + '\'' + ", gsjlrhbzz='"
				+ gsjlrhbzz + '\'' + ", jjcc='" + jjcc + '\'' + ", hkcy='" + hkcy + '\'' + ", gjjcy='" + gjjcy + '\''
				+ '}';
	}
}
