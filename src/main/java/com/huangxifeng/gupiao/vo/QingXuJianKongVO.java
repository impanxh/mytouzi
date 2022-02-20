package com.huangxifeng.gupiao.vo;

import java.util.List;

import com.huangxifeng.core.utils.DoubleUtil;
import com.huangxifeng.core.utils.ValidateUtil;

public class QingXuJianKongVO
{
	//自选
	int zx_allnum = 0; //所有自选
	int zx_gknum = 0; //自选高开数
	Double zx_gkb = 0.0;  //自选高开比
	Double zx_jzf = 0.0;  //自选开盘均涨幅
	
	int zx_ssznum = 0;
	Double zx_sszdb = 0.0;  //实时涨跌比
	Double zx_ssjzf = 0.0;  //自选开盘均涨幅
	Double zx_sszkb = 0.0; //自选平均赚亏比
	
	String zx_css = "";  //监控CSS
	
	//所以个股
	int jk_allnum = 0; //所有涨停
	int jk_gknum = 0; //涨停高开数
	Double jk_gkb = 0.0;  //涨停高开比
	Double jk_jzf = 0.0;  //涨停开盘均涨幅
	
	int jk_ssznum = 0; //实时
	Double jk_sszdb = 0.0;  //涨停实时涨跌比
	Double jk_ssjzf = 0.0;  //涨停开盘均涨幅
	Double jk_sszkb = 0.0;  //涨停平均赚亏比
	
	String jk_css = ""; //监控CSS
	
	/**
	 * 自选监控
	 */
	public static QingXuJianKongVO zxmonitor(List<ZhangTingDiXiVO> zxlist)
	{
		//监控自选股低高开比
		QingXuJianKongVO jkvo = new QingXuJianKongVO();
				
		if(ValidateUtil.isEmpty(zxlist))
		{
			return jkvo;
		}
		
		//自选
		int zx_allnum = zxlist.size(); //所有自选
		int zx_gknum = 0; //自选高开数
		Double zx_gkb = 0.0;  //自选高开比
		Double zx_jzf = 0.0;  //自选开盘均涨幅
		
		int zx_ssznum = 0;
		int zx_yxnum  = 0;   //有效赚亏数量
		Double zx_sszdb = 0.0;  //自选涨跌比
		Double zx_ssjzf = 0.0;  //自选开盘均涨幅
		Double zx_sszkb = 0.0;  //自选平均赚亏比
		
		for (ZhangTingDiXiVO zxvo : zxlist)
		{
			//开盘高开比
			if(zxvo.getKpzdb().compareTo(0.0) > 0)
			{
				zx_gknum++;
			}
			
			zx_jzf = DoubleUtil.sum(zx_jzf, zxvo.getKpzdb());
			
			//当前价 -> 昨收价
			if(zxvo.getDqj().compareTo(zxvo.getZtspj()) >= 0)
			{
				zx_ssznum++;
			}
			
			zx_ssjzf = DoubleUtil.sum(zx_ssjzf, Double.valueOf(zxvo.getZdf()));
			
			//实际赚钱比（劈开涨停和跌停）
			//zx_sszkb = DoubleUtil.sum(zx_sszkb, zxvo.getZkb());
			if(zxvo.getZkb().compareTo(0.0) != 0)
			{
				zx_sszkb = DoubleUtil.sum(zx_sszkb, zxvo.getZkb());
				zx_yxnum ++;
			}
		}
		
		zx_gkb = DoubleUtil.mul(DoubleUtil.div(Double.valueOf(zx_gknum), Double.valueOf(zx_allnum), 4), 100.0, 2);
		zx_jzf = DoubleUtil.div(Double.valueOf(zx_jzf), Double.valueOf(zx_allnum), 2);
		zx_sszdb = DoubleUtil.mul(DoubleUtil.div(Double.valueOf(zx_ssznum), Double.valueOf(zx_allnum), 4), 100.0, 2);
		zx_ssjzf = DoubleUtil.div(Double.valueOf(zx_ssjzf), Double.valueOf(zx_allnum), 2);
		if(zx_yxnum > 0)
		{
			zx_sszkb = DoubleUtil.div(Double.valueOf(zx_sszkb), Double.valueOf(zx_yxnum), 2);
		}
		
		jkvo.setZx_allnum(zx_allnum);
		jkvo.setZx_gknum(zx_gknum);
		jkvo.setZx_gkb(zx_gkb);
		jkvo.setZx_jzf(zx_jzf);
		jkvo.setZx_ssznum(zx_ssznum);
		jkvo.setZx_sszdb(zx_sszdb);
		jkvo.setZx_ssjzf(zx_ssjzf);
		jkvo.setZx_sszkb(zx_sszkb);
		
		if(zx_gkb.compareTo(35.0) <= 0)
		{
			jkvo.setZx_css("green");
		}
		else if (zx_gkb.compareTo(62.0) >= 0)
		{
			jkvo.setZx_css("red");
		} else {
			jkvo.setZx_css("orange");
		}
		
		return jkvo;
	}
	
	
	/**
	 * 监控内容
	 */
	public static QingXuJianKongVO monitor(List<JianKongVO> list)
	{
		//监控自选股低高开比
		QingXuJianKongVO jkvo = new QingXuJianKongVO();
				
		if(ValidateUtil.isEmpty(list))
		{
			return jkvo;
		}
		
		//涨停
		int jk_allnum = list.size(); //所有涨停
		int jk_gknum = 0; //涨停高开数
		Double jk_gkb = 0.0;  //涨停高开比
		Double jk_jzf = 0.0;  //涨停开盘均涨幅
		
		int jk_ssznum = 0;  //涨数量
		int jk_yxnum = 0;   //有效赚亏数量
		Double jk_sszdb = 0.0;  //涨停实时涨跌比
		Double jk_ssjzf = 0.0;  //涨停实时均涨幅
		Double jk_sszkb = 0.0;  //涨停平均赚亏比
		
		for (JianKongVO ztvo : list)
		{
			//开盘高开比
			if(ztvo.getKpzdf().compareTo(0.0) > 0)
			{
				jk_gknum++;
			}
			
			jk_jzf = DoubleUtil.sum(jk_jzf, ztvo.getKpzdf());
			
			//当前价 -> 昨收价
			if(ztvo.getDqj().compareTo(ztvo.getZtspj()) > 0)
			{
				jk_ssznum++;
			}
			
			jk_ssjzf = DoubleUtil.sum(jk_ssjzf, Double.valueOf(ztvo.getZdf()));
			
			if(ztvo.getZkb().compareTo(0.0) != 0)
			{
				jk_sszkb = DoubleUtil.sum(jk_sszkb, ztvo.getZkb());
				jk_yxnum ++;
			}
		}
		
		jk_gkb = DoubleUtil.mul(DoubleUtil.div(Double.valueOf(jk_gknum), Double.valueOf(jk_allnum), 4), 100.0, 2);
		jk_jzf = DoubleUtil.div(Double.valueOf(jk_jzf), Double.valueOf(jk_allnum), 2);
		jk_sszdb = DoubleUtil.mul(DoubleUtil.div(Double.valueOf(jk_ssznum), Double.valueOf(jk_allnum), 4), 100.0, 2);
		jk_ssjzf = DoubleUtil.div(Double.valueOf(jk_ssjzf), Double.valueOf(jk_allnum), 2);
		if(jk_yxnum > 0)
		{
			jk_sszkb = DoubleUtil.div(Double.valueOf(jk_sszkb), Double.valueOf(jk_yxnum), 2);
		}
		
		jkvo.setJk_allnum(jk_allnum);
		jkvo.setJk_gknum(jk_gknum);
		jkvo.setJk_gkb(jk_gkb);
		jkvo.setJk_jzf(jk_jzf);
		jkvo.setJk_ssznum(jk_ssznum);
		jkvo.setJk_sszdb(jk_sszdb);
		jkvo.setJk_ssjzf(jk_ssjzf);
		jkvo.setJk_sszkb(jk_sszkb);
		
		if(jk_gkb.compareTo(35.0) <= 0)
		{
			jkvo.setJk_css("green");
		}
		else if (jk_gkb.compareTo(62.0) >= 0)
		{
			jkvo.setJk_css("red");
		} else {
			jkvo.setJk_css("orange");
		}
		
		return jkvo;
	}

	public int getZx_gknum() {
		return zx_gknum;
	}
	
	public void setZx_gknum(int zx_gknum) {
		this.zx_gknum = zx_gknum;
	}

	public int getZx_allnum() {
		return zx_allnum;
	}
	
	public void setZx_allnum(int zx_allnum) {
		this.zx_allnum = zx_allnum;
	}


	public Double getZx_gkb() {
		return zx_gkb;
	}


	public void setZx_gkb(Double zx_gkb) {
		this.zx_gkb = zx_gkb;
	}


	public Double getZx_jzf() {
		return zx_jzf;
	}


	public void setZx_jzf(Double zx_jzf) {
		this.zx_jzf = zx_jzf;
	}


	public int getZx_ssznum() {
		return zx_ssznum;
	}


	public void setZx_ssznum(int zx_ssznum) {
		this.zx_ssznum = zx_ssznum;
	}


	public Double getZx_sszdb() {
		return zx_sszdb;
	}


	public void setZx_sszdb(Double zx_sszdb) {
		this.zx_sszdb = zx_sszdb;
	}


	public Double getZx_ssjzf() {
		return zx_ssjzf;
	}


	public void setZx_ssjzf(Double zx_ssjzf) {
		this.zx_ssjzf = zx_ssjzf;
	}


	public Double getZx_sszkb() {
		return zx_sszkb;
	}


	public void setZx_sszkb(Double zx_sszkb) {
		this.zx_sszkb = zx_sszkb;
	}


	public int getJk_allnum() {
		return jk_allnum;
	}


	public void setJk_allnum(int jk_allnum) {
		this.jk_allnum = jk_allnum;
	}


	public int getJk_gknum() {
		return jk_gknum;
	}


	public void setJk_gknum(int jk_gknum) {
		this.jk_gknum = jk_gknum;
	}


	public Double getJk_gkb() {
		return jk_gkb;
	}


	public void setJk_gkb(Double jk_gkb) {
		this.jk_gkb = jk_gkb;
	}


	public Double getJk_jzf() {
		return jk_jzf;
	}


	public void setJk_jzf(Double jk_jzf) {
		this.jk_jzf = jk_jzf;
	}


	public int getJk_ssznum() {
		return jk_ssznum;
	}


	public void setJk_ssznum(int jk_ssznum) {
		this.jk_ssznum = jk_ssznum;
	}


	public Double getJk_sszdb() {
		return jk_sszdb;
	}


	public void setJk_sszdb(Double jk_sszdb) {
		this.jk_sszdb = jk_sszdb;
	}


	public Double getJk_ssjzf() {
		return jk_ssjzf;
	}


	public void setJk_ssjzf(Double jk_ssjzf) {
		this.jk_ssjzf = jk_ssjzf;
	}

	public Double getJk_sszkb() {
		return jk_sszkb;
	}


	public void setJk_sszkb(Double jk_sszkb) {
		this.jk_sszkb = jk_sszkb;
	}


	public String getZx_css() {
		return zx_css;
	}


	public void setZx_css(String zx_css) {
		this.zx_css = zx_css;
	}


	public String getJk_css() {
		return jk_css;
	}


	public void setJk_css(String jk_css) {
		this.jk_css = jk_css;
	}
	
	
	
}
