package com.huangxifeng.gupiao.service;

import com.huangxifeng.gupiao.util.TableRequest;

public interface NotifyService {

	public void analyDataAndSendMsg();

	public Object startStockDetail();
	
	
	public Object getStockDetailResult();
	
	public Object queryDataFromCommand(TableRequest req); 

}
