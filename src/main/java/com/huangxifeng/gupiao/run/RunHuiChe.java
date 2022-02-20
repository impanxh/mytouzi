package com.huangxifeng.gupiao.run;

import com.huangxifeng.core.utils.DoubleUtil;
import com.huangxifeng.core.utils.FileUtil;
import com.huangxifeng.core.utils.HttpClientUtil;
import com.huangxifeng.core.utils.StringPool;
import com.huangxifeng.gupiao.vo.TurnoverVO;
import org.apache.poi.util.SystemOutLogger;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunHuiChe {

	private static Map<String, String> cateMap = new HashMap<String, String>();
	private static Map<String, TurnoverVO> trvoMap = new HashMap<String, TurnoverVO>();

	public static void main(String[] args) {

		// 读取所有股票文件方法
		List<String> list = FileUtil.readToStringList("D:\\SVN\\feng\\投资\\股票\\大市值及高价票.txt", StringPool.UTF_8);
		DecimalFormat df = new DecimalFormat("0.00");

		for (int i = 0; i < list.size(); i++) {
			String txt = list.get(i);
			// System.out.println(txt);
			String[] txtarray = txt.split("#");
			String cid = txtarray[0];
			String name = txtarray[1];
			String cate = txtarray[2];

			try {

				if (cid.startsWith("6")) {
					cid = "sh" + cid;
				} else {
					cid = "sz" + cid;
				}

				String daybody = "";

				String cidfile = StringPool.PROJECT_DIR + "\\data\\daylist\\" + cid + ".txt";
				// System.out.println(cidfile);
				// if(FileUtil.isExist(cidfile))
				// {
				// daybody = FileUtil.readLine(cidfile, "UTF-8");
				// System.out.println("file read:" + daybody);
				// } else {
				// 获取个股日线表
				String daylistapi = "https://data.gtimg.cn/flashdata/hushen/latest/daily/" + cid + ".js?maxage=43201";
				daybody = HttpClientUtil.getInstance().doGet(daylistapi);
				// System.out.println("url read:" + daybody);
				FileUtil.writeFile(cidfile, daybody);
				// }

				String[] splist = daybody.split("\n");

				// 新股不考虑，至少大于50个交易日
				if (splist.length < 75) {
					continue;
				}

				// 当前价格
				// 最高价格
				// 回测比例

				String yesterday = splist[splist.length - 2].replace("\\n\\", "");
				// System.out.println(yesterday);

				String[] ydStrs = yesterday.split(" ");
				Double spj = Double.valueOf(ydStrs[2]);
				// System.out.println(spj);

				int maxprice = 0;
				String maxday = "";

				for (int n = 1; n <= 100; n++) {
					String daystr = "";
					// System.out.println(splist[0]);
					try {
						daystr = splist[splist.length - 1 - n].replace("\\n\\", "");
					} catch (Exception e) {
						System.out.println(splist.length);
						System.out.println(daybody);
						e.printStackTrace();
					}

					String[] spdaystr = daystr.split(" ");
					if (spdaystr.length < 5) {
						// System.out.println(day50str);
						continue;
					}

					int mp = (int) (Double.valueOf(spdaystr[3]) * 100);

					if (mp > maxprice) {
						maxprice = mp;
						maxday = spdaystr[0];
					}
				}

				System.out.println(txt + "#" + spj + "#"
						+ DoubleUtil.div(Double.valueOf(String.valueOf(maxprice)), 100.00, 2) + "#" + maxday);

			} catch (Exception e) {
				System.out.println(txt);
				e.printStackTrace();
			}
		}
	}
}
