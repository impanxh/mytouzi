package com.huangxifeng.gupiao.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huangxifeng.core.utils.DateUtil;
import com.huangxifeng.gupiao.jymodel.RunJianKong;
import com.huangxifeng.gupiao.jymodel.RunZhangTingDiXi;
import com.huangxifeng.gupiao.jymodel.RunZhuiZhangTing;
import com.huangxifeng.gupiao.run.RunIndustryMonitorSpider;
import com.huangxifeng.gupiao.util.TableRequest;
import com.huangxifeng.gupiao.vo.HangYeVO;
import com.huangxifeng.gupiao.vo.JianKongVO;
import com.huangxifeng.gupiao.vo.QingXuJianKongVO;
import com.huangxifeng.gupiao.vo.ZhangTingDiXiVO;

@Controller
@RequestMapping(value = "/gp")
public class GuPiaoController {

	// @Autowired
	// private GpJiaoyiService gpJiaoyiService;

	@RequestMapping(value = "/")
	public String index(Model model) {
		return "index";
	}

	/**
	 * 个股 ajax 接口
	 */
	@RequestMapping(value = "/ztdx-zx")
	@ResponseBody
	public Object gotoZtdx_zxPage(TableRequest req) {
		String px = req.getFilter("px");
		// 自选个列表
		List<ZhangTingDiXiVO> zxlist = RunZhangTingDiXi.getZtdxList();
		// 个股排序
		RunZhangTingDiXi.sort(zxlist,
				org.apache.commons.lang3.StringUtils.defaultString(px, org.apache.commons.lang3.StringUtils.EMPTY));
		return zxlist;
	}

	/**
	 * 情绪 ajax 接口
	 */
	@RequestMapping(value = "/ztdx-qx")
	@ResponseBody
	public Object gotoZtdx_xPage(TableRequest req) {
		Map<String, Object> model = new HashMap<>();

		// 自选个列表
		List<ZhangTingDiXiVO> zxlist = RunZhangTingDiXi.getZtdxList();
		// 监控自选股低高开比
		QingXuJianKongVO zxjkvo = QingXuJianKongVO.zxmonitor(zxlist);
		// 个股排序

		// 涨停个列表
		List<JianKongVO> ztlist = RunJianKong.getList(JianKongVO.Type.ZT_LIST);
		// 监控涨停股低高开比
		QingXuJianKongVO ztjkvo = QingXuJianKongVO.monitor(ztlist);

		// 跌停个列表
		List<JianKongVO> dtlist = RunJianKong.getList(JianKongVO.Type.DT_LIST);
		// 监控跌停股低高开比
		QingXuJianKongVO dtjkvo = QingXuJianKongVO.monitor(dtlist);

		// 高标个列表
		List<JianKongVO> gblist = RunJianKong.getList(JianKongVO.Type.GB_LIST);
		// 监控跌停股低高开比
		QingXuJianKongVO gbjkvo = QingXuJianKongVO.monitor(gblist);

		// 行业个列表
		List<JianKongVO> hygplist = RunJianKong.getList(JianKongVO.Type.HY_LIST);
		// 监控跌停股低高开比
		QingXuJianKongVO hygpjkvo = QingXuJianKongVO.monitor(hygplist);

		model.put("zxjkvo", zxjkvo);

		model.put("dtjkvo", dtjkvo);

		model.put("ztjkvo", ztjkvo);

		model.put("gbjkvo", gbjkvo);

		model.put("hygpjkvo", hygpjkvo);
		return model;
	}

	/**
	 * 行业ajax接口
	 */
	@RequestMapping(value = "/ztdx-hylist")
	@ResponseBody
	public Object gotoZtdx_hyPage(TableRequest req) {
		String hpx = req.getFilter("hpx");
		// 行业列表
		List<HangYeVO> hylist = RunIndustryMonitorSpider.getHyList();
		// 行业排序
		RunIndustryMonitorSpider.sort(hylist,
				org.apache.commons.lang3.StringUtils.defaultString(hpx, org.apache.commons.lang3.StringUtils.EMPTY));
		return hylist;
	}

	@RequestMapping(value = "/ztdxAjax")
	public String ztdxAjax(Model model, @RequestParam(name = "px", required = false, defaultValue = "sort") String px,
			@RequestParam(name = "hpx", required = false, defaultValue = "zdf") String hpx) {
		pageInfo(model, px, hpx);
		;
		return "gupiao/ztdxAjax";
	}

	/**
	 * 连板涨停低吸战法
	 */
	@RequestMapping(value = "/ztdx")
	public String gotoZtdxPage(Model model,
			@RequestParam(name = "px", required = false, defaultValue = "sort") String px,
			@RequestParam(name = "hpx", required = false, defaultValue = "zdf") String hpx) {

		pageInfo(model, px, hpx);

		return "gupiao/ztdx";
	}

	private void pageInfo(Model model, String px, String hpx) {
		// 行业列表
		List<HangYeVO> hylist = RunIndustryMonitorSpider.getHyList();
		// 行业排序
		RunIndustryMonitorSpider.sort(hylist, hpx);

		// 自选个列表
		List<ZhangTingDiXiVO> zxlist = RunZhangTingDiXi.getZtdxList();
		// 监控自选股低高开比
		QingXuJianKongVO zxjkvo = QingXuJianKongVO.zxmonitor(zxlist);
		// 个股排序
		RunZhangTingDiXi.sort(zxlist, px);

		// 涨停个列表
		List<JianKongVO> ztlist = RunJianKong.getList(JianKongVO.Type.ZT_LIST);
		// 监控涨停股低高开比
		QingXuJianKongVO ztjkvo = QingXuJianKongVO.monitor(ztlist);
		RunJianKong.sort(ztlist, "cate");

		// 跌停个列表
		List<JianKongVO> dtlist = RunJianKong.getList(JianKongVO.Type.DT_LIST);
		// 监控跌停股低高开比
		QingXuJianKongVO dtjkvo = QingXuJianKongVO.monitor(dtlist);
		RunJianKong.sort(dtlist, "cate");

		// 高标个列表
		List<JianKongVO> gblist = RunJianKong.getList(JianKongVO.Type.GB_LIST);
		// 监控跌停股低高开比
		QingXuJianKongVO gbjkvo = QingXuJianKongVO.monitor(gblist);
		RunJianKong.sort(gblist, "cate");

		// 行业个列表
		List<JianKongVO> hygplist = RunJianKong.getList(JianKongVO.Type.HY_LIST);
		// 监控跌停股低高开比
		QingXuJianKongVO hygpjkvo = QingXuJianKongVO.monitor(hygplist);
		RunJianKong.sort(hygplist, "cate");

		model.addAttribute("time", DateUtil.format(DateUtil.YYYY_MM_DD_HH_MM_SS));
		model.addAttribute("hylist", hylist);
		model.addAttribute("hpx", hpx);

		model.addAttribute("zxlist", zxlist);
		model.addAttribute("zxjkvo", zxjkvo);
		model.addAttribute("px", px);

		model.addAttribute("dtlist", dtlist);
		model.addAttribute("dtjkvo", dtjkvo);

		model.addAttribute("ztlist", ztlist);
		model.addAttribute("ztjkvo", ztjkvo);

		model.addAttribute("gblist", gblist);
		model.addAttribute("gbjkvo", gbjkvo);

		model.addAttribute("hygplist", hygplist);
		model.addAttribute("hygpjkvo", hygpjkvo);
	}

	@RequestMapping(value = "/run")
	public String run(@RequestParam(name = "p", required = false, defaultValue = "index") String p) {

		// 跑行业
		if (p.equals("hy")) {
			RunIndustryMonitorSpider.run();
		}

		// 涨停低吸
		if (p.equals("ztdx")) {
			RunZhangTingDiXi.run();
		}

		// 所以个股
		if (p.equals("all")) {
			RunJianKong.run();
		}

		// 跑涨停线
		if (p.equals("zt")) {
			RunZhuiZhangTing.run();
		}

		return "gupiao/run";
	}

	/**
	 * 操作控制器
	 * 
	 * @param key
	 */
	@RequestMapping(value = "/opt")
	public String opt(@RequestParam(name = "key", required = false, defaultValue = "all") String key) {
		// 停行业跑数据
		if (key.equals("cate")) {
			RunIndustryMonitorSpider.stop();
		}

		// 停自选低吸跑数据
		if (key.equals("ztdx")) {
			RunZhangTingDiXi.stop();
		}

		// 停监控个股跑数据
		if (key.equals("jk")) {
			RunJianKong.stop();
		}

		if (key.equals("all")) {
			RunIndustryMonitorSpider.stop();

			RunZhangTingDiXi.stop();

			RunJianKong.stop();
		}

		return "gupiao/opt";
	}

}
