package com.huangxifeng.gupiao.vo;

public class JiBenMianVO extends GuPiaoBaseVO {

	String hxtc = ""; // 核心题材
	String cateid = "";// 分类ID

	String jtspj = ""; // 当前价格

	String ljzdf1 = ""; // 1个月涨跌幅（%）
	String ljzdf3 = ""; // 3个月涨跌幅（%）

	String hs300zdf1 = ""; // 沪深300-1个月涨跌幅（%）
	String hs300zdf3 = ""; // 沪深300-3个月涨跌幅（%）

	String zsz = ""; // 总市值(元)
	String ltsz = ""; // 流通市值(元)
	String hyzsz = ""; // 行业平均市值(元)
	String hyltsz = ""; // 平均流通市值(元)

	String zgb = ""; // 总股本(万股)
	String ltgb = ""; // 流通股本(万股)

	String syl = ""; // 市盈（静）
	String sylTTM = ""; // 市盈（TTM）
	String hysyl = ""; // 行业平均市盈（静）
	String hysylTTM = ""; // 行业平均市盈（TTM）
	String sylcz = ""; // 市盈率差值

	String yysr = ""; // 营业收入(元)
	String jlr = ""; // 净利润(元)
	String jlrl = ""; // 净利润率(%)

	String hyyysr = ""; // 行业平均营业收入(元)
	String hyjlr = ""; // 行业平均净利润(元)
	String hyjlrl = ""; // 行业平均净利润率(%)
	String jlrldyhy = ""; // 净利润率大于行业平均

	String jbmgsy = ""; // 每股收益(元)
	String mgjzc = ""; // 每股净资产(元)
	String kfmgsy = ""; // 扣非每股收益(元)
	String jzcsyl = ""; // 净资产收益率
	String zcfzl = ""; // 资产负债率(%)

	String cwdate = ""; // 财务时间

	String yyzsr = ""; // 营业总收入(元)
	String yyzsrtbzz = ""; // 营业总收入同比增长(%)
	String yyzsrgdhbzz = ""; // 营业总收入环比增长(%)

	String mlr = ""; // 毛利润(元)
	String gsjlr = ""; // 净利润(元)
	String gsjlrtbzz = ""; // 净利润同比增长(%)
	String gsjlrgdhbzz = ""; // 净利润环比增长(%)

	String kfjlr = ""; // 扣非净利润(元)
	String kfjlrtbzz = ""; // 扣非净利润同比增长(%)
	String kfjlrgdhbzz = ""; // 扣非净利润环比增长(%)

	String ccjs = ""; // 基金持仓总数
	String ccjjzb = ""; // 持仓基金占流通比

	String gdrs = ""; // 股东人数(户)
	String gdrs_jsqbh = ""; // 较上期变化(%)
	String rjltg = ""; // 人均流通股(股)
	String rjltg_jsqbh = ""; // 较上期变化(%)
	String cmjzd = ""; // 筹码集中度
	String cmgj = ""; // 筹码股价(元)
	String cmgjbl = ""; // 筹码当前价格比

	String rjcgje = ""; // 人均持股金额(元)
	String qsdgdcghj = ""; // 前十大股东持股合计(%)
	String qsdltgdcghj = ""; // 前十大流通股东持股合计(%)

	String hkprice = ""; // 香港中央结算有限公司
	String gjjprice = ""; // 全国社保基金

	String zfbl = ""; // 2个月涨幅比例

	public String getCateid() {
		return cateid;
	}

	public void setCateid(String cateid) {
		this.cateid = cateid;
	}

	public String getHxtc() {
		return hxtc;
	}

	public void setHxtc(String hxtc) {
		this.hxtc = hxtc;
	}

	public String getJtspj() {
		return jtspj;
	}

	public void setJtspj(String jtspj) {
		this.jtspj = jtspj;
	}

	public String getLjzdf1() {
		return ljzdf1;
	}

	public void setLjzdf1(String ljzdf1) {
		this.ljzdf1 = ljzdf1;
	}

	public String getLjzdf3() {
		return ljzdf3;
	}

	public String getKfmgsy() {
		return kfmgsy;
	}

	public void setKfmgsy(String kfmgsy) {
		this.kfmgsy = kfmgsy;
	}

	public void setLjzdf3(String ljzdf3) {
		this.ljzdf3 = ljzdf3;
	}

	public String getHs300zdf1() {
		return hs300zdf1;
	}

	public void setHs300zdf1(String hs300zdf1) {
		this.hs300zdf1 = hs300zdf1;
	}

	public String getHs300zdf3() {
		return hs300zdf3;
	}

	public void setHs300zdf3(String hs300zdf3) {
		this.hs300zdf3 = hs300zdf3;
	}

	public String getZsz() {
		return zsz;
	}

	public String getZcfzl() {
		return zcfzl;
	}

	public void setZcfzl(String zcfzl) {
		this.zcfzl = zcfzl;
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

	public String getHyzsz() {
		return hyzsz;
	}

	public void setHyzsz(String hyzsz) {
		this.hyzsz = hyzsz;
	}

	public String getHyltsz() {
		return hyltsz;
	}

	public void setHyltsz(String hyltsz) {
		this.hyltsz = hyltsz;
	}

	public String getSyl() {
		return syl;
	}

	public void setSyl(String syl) {
		this.syl = syl;
	}

	public String getSylTTM() {
		return sylTTM;
	}

	public void setSylTTM(String sylTTM) {
		this.sylTTM = sylTTM;
	}

	public String getHysyl() {
		return hysyl;
	}

	public void setHysyl(String hysyl) {
		this.hysyl = hysyl;
	}

	public String getHysylTTM() {
		return hysylTTM;
	}

	public void setHysylTTM(String hysylTTM) {
		this.hysylTTM = hysylTTM;
	}

	public String getSylcz() {
		return sylcz;
	}

	public void setSylcz(String sylcz) {
		this.sylcz = sylcz;
	}

	public String getKfjlr() {
		return kfjlr;
	}

	public void setKfjlr(String kfjlr) {
		this.kfjlr = kfjlr;
	}

	public String getKfjlrtbzz() {
		return kfjlrtbzz;
	}

	public void setKfjlrtbzz(String kfjlrtbzz) {
		this.kfjlrtbzz = kfjlrtbzz;
	}

	public String getKfjlrgdhbzz() {
		return kfjlrgdhbzz;
	}

	public void setKfjlrgdhbzz(String kfjlrgdhbzz) {
		this.kfjlrgdhbzz = kfjlrgdhbzz;
	}

	public String getCwdate() {
		return cwdate;
	}

	public void setCwdate(String cwdate) {
		this.cwdate = cwdate;
	}

	public String getYysr() {
		return yysr;
	}

	public void setYysr(String yysr) {
		this.yysr = yysr;
	}

	public String getJlr() {
		return jlr;
	}

	public void setJlr(String jlr) {
		this.jlr = jlr;
	}

	public String getJlrl() {
		return jlrl;
	}

	public void setJlrl(String jlrl) {
		this.jlrl = jlrl;
	}

	public String getHyyysr() {
		return hyyysr;
	}

	public void setHyyysr(String hyyysr) {
		this.hyyysr = hyyysr;
	}

	public String getHyjlr() {
		return hyjlr;
	}

	public void setHyjlr(String hyjlr) {
		this.hyjlr = hyjlr;
	}

	public String getHyjlrl() {
		return hyjlrl;
	}

	public void setHyjlrl(String hyjlrl) {
		this.hyjlrl = hyjlrl;
	}

	public String getJlrldyhy() {
		return jlrldyhy;
	}

	public void setJlrldyhy(String jlrldyhy) {
		this.jlrldyhy = jlrldyhy;
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

	public String getYyzsrgdhbzz() {
		return yyzsrgdhbzz;
	}

	public void setYyzsrgdhbzz(String yyzsrgdhbzz) {
		this.yyzsrgdhbzz = yyzsrgdhbzz;
	}

	public String getMlr() {
		return mlr;
	}

	public void setMlr(String mlr) {
		this.mlr = mlr;
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

	public String getGsjlrgdhbzz() {
		return gsjlrgdhbzz;
	}

	public void setGsjlrgdhbzz(String gsjlrgdhbzz) {
		this.gsjlrgdhbzz = gsjlrgdhbzz;
	}

	public String getCcjs() {
		return ccjs;
	}

	public void setCcjs(String ccjs) {
		this.ccjs = ccjs;
	}

	public String getCcjjzb() {
		return ccjjzb;
	}

	public void setCcjjzb(String ccjjzb) {
		this.ccjjzb = ccjjzb;
	}

	public String getGdrs() {
		return gdrs;
	}

	public void setGdrs(String gdrs) {
		this.gdrs = gdrs;
	}

	public String getGdrs_jsqbh() {
		return gdrs_jsqbh;
	}

	public void setGdrs_jsqbh(String gdrs_jsqbh) {
		this.gdrs_jsqbh = gdrs_jsqbh;
	}

	public String getRjltg() {
		return rjltg;
	}

	public void setRjltg(String rjltg) {
		this.rjltg = rjltg;
	}

	public String getRjltg_jsqbh() {
		return rjltg_jsqbh;
	}

	public void setRjltg_jsqbh(String rjltg_jsqbh) {
		this.rjltg_jsqbh = rjltg_jsqbh;
	}

	public String getCmjzd() {
		return cmjzd;
	}

	public void setCmjzd(String cmjzd) {
		this.cmjzd = cmjzd;
	}

	public String getCmgj() {
		return cmgj;
	}

	public void setCmgj(String cmgj) {
		this.cmgj = cmgj;
	}

	public String getCmgjbl() {
		return cmgjbl;
	}

	public void setCmgjbl(String cmgjbl) {
		this.cmgjbl = cmgjbl;
	}

	public String getRjcgje() {
		return rjcgje;
	}

	public void setRjcgje(String rjcgje) {
		this.rjcgje = rjcgje;
	}

	public String getQsdgdcghj() {
		return qsdgdcghj;
	}

	public void setQsdgdcghj(String qsdgdcghj) {
		this.qsdgdcghj = qsdgdcghj;
	}

	public String getQsdltgdcghj() {
		return qsdltgdcghj;
	}

	public void setQsdltgdcghj(String qsdltgdcghj) {
		this.qsdltgdcghj = qsdltgdcghj;
	}

	public String getHkprice() {
		return hkprice;
	}

	public void setHkprice(String hkprice) {
		this.hkprice = hkprice;
	}

	public String getGjjprice() {
		return gjjprice;
	}

	public void setGjjprice(String gjjprice) {
		this.gjjprice = gjjprice;
	}

	public String getZfbl() {
		return zfbl;
	}

	public void setZfbl(String zfbl) {
		this.zfbl = zfbl;
	}

	public String getZgb() {
		return zgb;
	}

	public void setZgb(String zgb) {
		this.zgb = zgb;
	}

	public String getLtgb() {
		return ltgb;
	}

	public void setLtgb(String ltgb) {
		this.ltgb = ltgb;
	}

	public void p() {
		System.out.println("编号：" + cid);
		System.out.println("总市值(亿元)：" + zsz);
		System.out.println("流通市值(亿元)：" + ltsz);
		System.out.println("市盈（TTM）：" + sylTTM);
		System.out.println("每股收益(元)：" + jbmgsy);
		System.out.println("每股净资产(元)：" + mgjzc);
		System.out.println("营业总收入(元)：" + yyzsr);
		System.out.println("营业总收入同比增长(%)：" + yyzsrtbzz);
		System.out.println("净利润(元)：" + gsjlr);
		System.out.println("净利润同比增长(%)：" + gsjlrtbzz);
		System.out.println("基金持仓总数：" + ccjs);
	}

	public void p_base() {
		System.out.println("股票编号：" + cid);
		System.out.println("股票名称：" + name);
		System.out.println("总市值(亿元)：" + zsz);
		System.out.println("流通市值(亿元)：" + ltsz);
		System.out.println("市盈（TTM）：" + sylTTM);
		System.out.println("每股收益(元)：" + jbmgsy);
		System.out.println("扣非每股收益(元)：" + kfmgsy);
		System.out.println("每股净资产(元)：" + mgjzc);
		System.out.println("资产负债率(%)：" + zcfzl);

		System.out.println("十大股东占比：" + qsdgdcghj);
		System.out.println("十大流通股股东占比：" + qsdltgdcghj);

		System.out.println("基金持仓总数：" + ccjs);
		System.out.println("持仓基金占流通比：" + ccjjzb);

		System.out.println("香港中央结算有限公司：" + hkprice);
		System.out.println("全国社保基金：" + gjjprice);

		System.out.println("核心题材：" + hxtc);

	}
}
