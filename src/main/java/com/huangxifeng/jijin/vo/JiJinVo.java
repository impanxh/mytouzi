package com.huangxifeng.jijin.vo;

public class JiJinVo {

	private String id = "";
	private String name = "";
	private String gupiaos = "";
	private String time = "";
	private double unitzhi = 0.0;
	private String zhangzhi = "";
	private double addmoney = 0.0;
	private double number = 0.0;
	private double money = 0.0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id.length() == 1) {
			this.id = "00000" + id;
		} else if (id.length() == 2) {
			this.id = "0000" + id;
		} else if (id.length() == 3) {
			this.id = "000" + id;
		} else if (id.length() == 4) {
			this.id = "00" + id;
		} else if (id.length() == 5) {
			this.id = "0" + id;
		} else {
			this.id = id;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGupiaos() {
		return gupiaos;
	}

	public void setGupiaos(String gupiaos) {
		this.gupiaos = gupiaos;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getZhangzhi() {
		return zhangzhi;
	}

	public void setZhangzhi(String zhangzhi) {
		this.zhangzhi = zhangzhi;
	}

	public double getUnitzhi() {
		return unitzhi;
	}

	public void setUnitzhi(double unitzhi) {
		this.unitzhi = unitzhi;
	}

	public double getAddmoney() {
		return addmoney;
	}

	public void setAddmoney(double addmoney) {
		this.addmoney = addmoney;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String jijinAndGupiaoToString() {
		return this.id + "|" + this.gupiaos;
	}

	@Override
	public String toString() {
		return "JiJinVo [time=" + time + ", unitzhi=" + unitzhi + ", zhangzhi=" + zhangzhi + ", addmoney=" + addmoney
				+ ", number=" + number + ", money=" + money + "]";
	}

}
