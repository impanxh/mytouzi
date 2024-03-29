package com.pantuo.web;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody;

import com.huangxifeng.gupiao.service.NotifyService
import com.huangxifeng.gupiao.util.HtmlUtil
import com.huangxifeng.gupiao.util.TableRequest

@Controller
@RequestMapping(value = "/10jqka")
public class JqkaController {
	
	
	def cache  = null;
	def lastUpdate =  -1;
	HtmlUtil  util = new HtmlUtil();
	
	@Autowired
	@Lazy
	NotifyService notifyService;
	
	
	@RequestMapping(value = "/indexflash")
	@ResponseBody
	public def jqka(HttpServletRequest request) {
		if(cache == null  ||  (  ( System.currentTimeMillis() -lastUpdate) > 30000   )  ) {
			println "fetch-from utl "
			String url = "http://q.10jqka.com.cn/";
			def r =  util.work(url,"http://q.10jqka.com.cn/api.php?t=indexflash&");
			 cache = r;
			 lastUpdate  = System.currentTimeMillis()
			 return cache;
		}
		return cache;
	}
	
	@RequestMapping(value = "/mind")
	public String mind( ) {
		return "gupiao/mind";
	}
	
	@RequestMapping(value = "/lianghua")
	@ResponseBody
	public def lianghua(HttpServletRequest request) {
		return notifyService.startStockDetail();
	}
	
	@RequestMapping(value = "/lianghuaResult")
	@ResponseBody
	public def lianghuaResult(HttpServletRequest request) {
		return notifyService.getStockDetailResult();
	}
	
	
	
	@RequestMapping(value = "/queryDataFromCommand")
	@ResponseBody
	public def lianghuaResult(TableRequest req) {
		return notifyService.queryDataFromCommand(req);
	}
	
}
