package com.huangxifeng.core.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.math.BigInteger;
import java.util.regex.Pattern;

public class PoiParser {

	public static String getCellValue(Cell cell) {
		if (null == cell) {
			return "";
		}

		CellType type = cell.getCellType();

		if (type.equals(CellType.NUMERIC)) {
			return getRealStringValueOfDouble(cell.getNumericCellValue());
		}

		if (type.equals(CellType.FORMULA)) {
			try {
				return String.valueOf(cell.getNumericCellValue());
			} catch (IllegalStateException e) {
				return String.valueOf(cell.getRichStringCellValue());
			}
		}

		return cell.toString().trim();
	}

	// 处理科学计数法与普通计数法的字符串显示，尽最大努力保持精度
	private static String getRealStringValueOfDouble(Double d) {
		String doubleStr = d.toString();
		boolean b = doubleStr.contains("E");
		int indexOfPoint = doubleStr.indexOf('.');
		if (b) {
			int indexOfE = doubleStr.indexOf('E');
			// 小数部分
			BigInteger xs = new BigInteger(doubleStr.substring(indexOfPoint + BigInteger.ONE.intValue(), indexOfE));
			// 指数
			int pow = Integer.valueOf(doubleStr.substring(indexOfE + BigInteger.ONE.intValue()));
			int xsLen = xs.toByteArray().length;
			int scale = xsLen - pow > 0 ? xsLen - pow : 0;
			doubleStr = String.format("%." + scale + "f", d);
		} else {
			Pattern p = Pattern.compile(".0$");
			java.util.regex.Matcher m = p.matcher(doubleStr);
			if (m.find()) {
				doubleStr = doubleStr.replace(".0", "");
			}
		}

		return doubleStr;
	}
}
