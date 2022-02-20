/*
 * Copyright @2010 Net365. All rights reserved.
 */
package com.huangxifeng.core.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 * 
 * @author HuangFeng(2010-11-2)
 */
public class DateUtil {

	public static final String YYYY = "yyyy";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM_SS_S = "yyyy-MM-dd HH:mm:ss.S";

	public static final String YYYYMM = "yyyyMM";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDHH = "yyyyMMddHH";
	public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String YYYYMMDDHHMMSSS = "yyyyMMddHHmmssS";

	private final static String[] oldCase = new String[] { "00", "0", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十",
			"十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "二十一", "二十二", "二十三", "二十四", "二十五", "二十六", "二十七",
			"二十八", "二十九", "三十", "三十一", "年", "月", "日", "时", "分", "秒", "零", "-", "/" };

	private final static String[] numberCase = new String[] { "00", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
			"28", "29", "30", "31", "年", "月", "日", "时", "分", "秒", "零", "-", "/", };

	private final static Map<String, String> map = new HashMap<String, String>();

	private DateUtil() {
		for (int i = 0; i < oldCase.length; i++) {
			map.put(oldCase[i], numberCase[i]);
			map.put(numberCase[i], oldCase[i]);
		}
	}

	/**
	 * Method：时间格式化，一般在页面上显示比较多，用户可以自定义格式化格式<br>
	 * Remark：把数据库里的DATE格式转换成HTML页面需要的时间字符串格式：
	 * <code>DateUtil.format(date, DateUtil.YYYY_MM_DD);</code><br>
	 * <br>
	 * Author：HF-JWinder(2008-10-6 下午04:04:12)
	 * 
	 * @param date
	 *            时间Date
	 * @param format
	 *            时间格式
	 * @return 时间字符串
	 */
	public static final String format(Date date, String format) {
		if (format == null || format.trim().length() == 0) {
			format = YYYY_MM_DD;
		}
		if (date == null) {
			date = new Date();
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * Method：时间格式化，一般在页面上显示比较多，用户可以自定义格式化格式<br>
	 * Remark：把数据库里的DATE格式转换成HTML页面需要的时间字符串格式：
	 * <code>DateUtil.format(date, DateUtil.YYYY_MM_DD);</code><br>
	 * <br>
	 * Author：HF-JWinder(2008-10-6 下午04:04:12)
	 * 
	 * @param date
	 *            时间Date
	 * @param format
	 *            时间格式
	 * @return 时间字符串
	 */
	public static final String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
		if (null != date) {
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * Method：时间格式化系统当前时间，一般在页面上显示比较多，用户可以自定义格式化。
	 * <code>DateUtil.format(DateUtil.YYYY_MM_DD);</code><br>
	 * <br>
	 * Author：HF-JWinder(2008-10-6 下午04:04:12)
	 * 
	 * @param format
	 *            时间格式
	 * @return 时间字符串
	 */
	public static final String format(String format) {
		if (format == null || format.trim().length() == 0) {
			format = YYYY_MM_DD;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * Method：时间格式化，一般在HTML页面的时间格式转换成数据库存储DATE格式，根据时间格式，用户可以自定义格式化。
	 * <code>DateUtil.parse(date, DateUtil.YYYY_MM_DD);</code><br>
	 * <br>
	 * Author：HF-JWinder(2008-10-6 下午04:11:13)
	 * 
	 * @param date
	 *            时间字符串
	 * @param format
	 *            时间格式
	 * @return Date 数据库存储时间
	 */
	public static final Date parse(String date, String format) {
		if (date == null || date.trim().length() == 0) {
			return null;
		}
		if (format == null || format.trim().length() == 0) {
			format = YYYY_MM_DD;
		}
		try {

			DateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.parse(date);

		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 时间格式转换
	 * <li>yyyy-MM-dd 转 yyyy年MM月dd日</li>
	 * <li>yyyy年MM月dd日 转 yyyy-MM-dd</li>
	 * 
	 * @param date
	 *            Old Date
	 * @return New Date To String
	 * @throws Exception
	 */
	public static String replaceFormat(String date) throws Exception {
		String str = date.trim().replace("/", "-");
		// 判断是否符合一般时间格式长度：2007-12-29 or 2007年12月29日
		if (str.length() >= 6) {
			// yyyy-MM-dd 转 yyyy年MM月dd日
			if (str.indexOf("-") > 0) {
				String[] sqlit = str.split("-");
				if (sqlit.length >= 3) {
					return (sqlit[0] + "年" + sqlit[1] + "月" + sqlit[2] + "日");
				} else {
					throw new Exception("‘" + date + "’ 时间格式错误.");
				}
			} else {
				// yyyy年MM月dd日 转 yyyy-MM-dd
				str = str.replace("年", "-");
				str = str.replace("月", "-");
				str = str.replace("日", "");
				return str;
			}
		} else {
			return str;
		}
	}

	/**
	 * Method:时间格式转换,****年**月**日**时**分<br>
	 * Remark:
	 * <li>2009年1月3日23点34分 Author:QP(2009-2-20下午04:19:14)
	 */
	public static String replaceFormatYMRHM(String date) {
		// System.out.println(date);
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
		int hour = Integer.parseInt(date.substring(11, 13));
		int minute = Integer.parseInt(date.substring(14));
		String str = year + " 年 " + month + " 月 " + day + " 日 " + hour + " 时 " + minute + " 分";
		return str;
	}

	/**
	 * 时间格式转换
	 * <li>yyyy/MM/dd, yyyy-MM-dd , yyyy年MM月dd日 转 二00八年一月七日</li>
	 * <li>二00八年一月七日 转 2008-01-07</li>
	 * 
	 * @param date
	 *            Old Date String
	 * @return New Date To String
	 * @throws Exception
	 *             <b>Date Format is Error Throws Exception.</b>
	 */
	public static String replaceDate(String date) throws Exception {
		try {
			// 过滤时间里的/, 年, 月, 日,
			String str = date.trim().replace("/", "-").replace("年", "-").replace("月", "-").replace("日", "-");
			StringBuffer buf = new StringBuffer();
			String[] sqlit = str.split("-");
			if (sqlit.length >= 3) {
				// 年份格式判断，并且转换
				char[] year = sqlit[0].toCharArray();
				if (year.length == 4 || year.length == 2) {
					for (int i = 0; i < year.length; i++) {
						if (map.isEmpty()) {
							new DateUtil();
						}
						buf.append(map.get(String.valueOf(year[i])));
					}
					buf.append("年");
				} else {
					throw new Exception("‘" + date + "’ 时间格式错误.");
				}

				// 月份格式判断，并且转换
				char[] month = sqlit[1].toCharArray();
				if (month.length <= 2) {
					if (month[0] == '0') {
						buf.append(map.get(String.valueOf(month[1])));
					} else {
						buf.append(map.get(sqlit[1]));
					}
					buf.append("月");
				} else {
					throw new Exception("‘" + date + "’ 时间格式错误.");
				}

				// 日格式判断，并且转换
				char[] day = sqlit[2].toCharArray();
				if (day.length <= 3) {
					if (day[0] == '0') {
						buf.append(map.get(String.valueOf(day[1])));
					} else {
						buf.append(map.get(sqlit[2]));
					}
					buf.append("日");
				} else {
					throw new Exception("‘" + date + "’ 时间格式错误.");
				}
				// 返回时间格式
				return buf.toString();
			} else {
				throw new Exception("‘" + date + "’ 时间格式错误.");
			}
		} catch (Exception e) {
			throw new Exception("‘" + date + "’ 时间格式错误.", e);
		}
	}

	/**
	 * 获取当前时间的时间对象
	 * 
	 * @return
	 */
	public static final Date nowDate() {
		return new Date();
	}

	/**
	 * 现有时间添加天数
	 * 
	 * @param date
	 *            指定时间
	 * @param days
	 *            添加或减少指定天数
	 * @return 最终时间
	 * @author JWinder.Huang (Jun 26, 2012)
	 */
	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 二个日期比较，返回天数
	 * 
	 * @author HuangFeng
	 */
	public static int compareDate(String dt1, String dt2) {
		DateFormat df = new SimpleDateFormat(YYYY_MM_DD);

		try {
			Date date1 = df.parse(dt1);
			Date date2 = df.parse(dt2);

			return (int) ((date1.getTime() - date2.getTime()) / 1000 / 60 / 60 / 24);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static String getYmdDate(String year, String month, String day, String format) {
		if (year == null || year.length() == 0) {
			return null;
		}
		if (month == null || month.length() == 0) {
			return null;
		}
		if (day == null || day.length() == 0) {
			return null;
		}
		String s = year + "-" + month + "-" + day;
		Date date = DateUtil.parse(s, format);
		if (date == null) {
			return "";
		}
		return DateUtil.format(date, format);
	}

	public static Timestamp getSystemTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}

	public static void main(String[] arg) throws Exception {
		System.out.println(DateUtil.compareDate("2016-08-09", "2016-08-17"));
	}
}
