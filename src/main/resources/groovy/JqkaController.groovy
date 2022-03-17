package com.pantuo.web;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody;

import com.huangxifeng.gupiao.util.HtmlUtil

@Controller
@RequestMapping(value = "/10jqka")
public class JqkaController {
	
	
	def cache  = null;
	def lastUpdate =  -1;
	HtmlUtil  util = new HtmlUtil();
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
}
