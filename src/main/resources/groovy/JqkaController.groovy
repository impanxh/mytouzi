package com.pantuo.web;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody;

import com.huangxifeng.gupiao.util.HtmlUtil

@Controller
@RequestMapping(value = "/10jqka")
public class JqkaController {
	HtmlUtil  util = new HtmlUtil();
	@RequestMapping(value = "/indexflash")
	@ResponseBody
	public def jqka(HttpServletRequest request) {
		String url = "http://q.10jqka.com.cn/";
		return util.work(url,"http://q.10jqka.com.cn/api.php?t=indexflash&");
	}
}
