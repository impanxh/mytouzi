package com.huangxifeng.jijin;

import com.huangxifeng.core.utils.FileUtil;
import com.huangxifeng.jijin.vo.GuPiaoVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuPiaoMain {

	public static void main(String[] args) {

		List<String> list = FileUtil.readToStringList("D:\\SVN\\feng\\投资\\allgupiao.txt", "GBK");
		// 501008|卫宁健康,300253#华大基因,300676#老百姓,603883#美年健康,002044#泰格医药,300347#益丰药房,603939#创业慧康,300451#鱼跃医疗,002223#一心堂,002727#久远银海,002777#
		Map<String, GuPiaoVo> gpmap = new HashMap<String, GuPiaoVo>();

		List<String> topjjList = FileUtil.readToStringList("D:\\SVN\\feng\\投资\\股票基金占比排行.txt", "UTF-8");
		List<String> tpnameList = new ArrayList<String>();
		for (int i = 0; i < topjjList.size(); i++) {
			String string = topjjList.get(i);
			tpnameList.add(string.split("#")[1]);
			// System.out.println(string.split("#")[1]);
		}

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			String gupiao = list.get(i);
			int chnum = 0;
			for (int j = 0; j < tpnameList.size(); j++) {
				if (gupiao.contains(tpnameList.get(j))) {
					chnum++;
				}
			}
			buf.append(gupiao + "|" + gupiao.split("#").length + "|" + chnum).append("\n");
		}

		FileUtil.writeFile("D:\\SVN\\feng\\投资\\基金TOP股票占比.txt", buf.toString(), "UTF-8");

		//
		// GuPiaoVo vo = null;
		// for (int i = 0; i < list.size(); i++)
		// {
		// String gupiao = list.get(i);
		// String gps = gupiao.replace("|", "&").split("&")[1];
		// String[] gpsp = gps.split("#");
		// for (int j = 0; j < gpsp.length; j++)
		// {
		// String[] gcs = gpsp[j].split(",");
		// if(gcs.length < 2)
		// {
		// System.out.println(gpsp[j] + "-" + gcs.length);
		// continue;
		// }
		//
		//
		// if(gpmap.containsKey(gcs[1]))
		// {
		// vo = gpmap.get(gcs[1]);
		// vo.setSum(vo.getSum() + 1);
		// } else {
		// vo = new GuPiaoVo();
		// vo.setId(gcs[1]);
		// vo.setName(gcs[0]);
		// vo.setSum(1);
		// }
		// gpmap.put(gcs[1], vo);
		// }
		// }
		//
		// for (String key : gpmap.keySet())
		// {
		// System.out.println(gpmap.get(key).toString());
		// }
	}

}
