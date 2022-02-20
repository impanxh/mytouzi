package com.huangxifeng.gupiao.vo;

import com.huangxifeng.gupiao.run.RunUtils;

public class GuPiaoBaseVO {

	String cid = ""; // 编号
	String name = ""; // 名称
	String cate = ""; // 分类
	
	boolean isOK = true; // 是否需要的股票
	boolean is3068 = false; // 30，68 票
	boolean is30 = false;
	boolean is68 = false;

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

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean OK) {
		isOK = OK;
	}

	public boolean is3068() {
		return is3068;
	}

	public void setIs3068(boolean is3068) {
		this.is3068 = is3068;
	}

	public boolean is30() {
		return is30;
	}

	public void setIs30(boolean is30) {
		this.is30 = is30;
	}

	public boolean is68() {
		return is68;
	}

	public void setIs68(boolean is68) {
		this.is68 = is68;
	}

	@Override
	public String toString() {
		return cid + "#" + name + "#" + cate;
	}

	public String toBaseString() {
		return cid + "#" + name;
	}

	public void valueOf(String str)
	{
		GuPiaoBaseVO vo = RunUtils.getGuPiaoBaseVO(str);
		this.cid = vo.getCid();
		this.name = vo.getName();
		this.cate = vo.getCate();
		this.isOK = vo.isOK();
		this.is3068 = vo.is3068();
		this.is30 = vo.is30();
		this.is68 = vo.is68();
	}

}
