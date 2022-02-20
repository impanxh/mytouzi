package com.huangxifeng.core.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ExcelUtil {
	public static void main(String[] args) throws Exception {
		String file = "D:\\SVN\\project\\SaleERP\\codes\\wgb.sound.bin\\lot 27457 status 8 plus above CHECKING(1).xlsx";
		Map<String, String> map = getData(file);
		int row = 1;
		while (true) {
			if (map.get(row + "_0") == null) {
				break;
			}
			String code = map.get(row + "_1");
			String lock = map.get(row + "_3");
			System.out.println(code + "-" + lock);

			row++;
		}
	}

	public static Map<String, String> getData(String file) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);

			for (Row row : sheet) {
				int colSize = row.getLastCellNum();
				for (int c = 0; c < colSize; c++) {
					Cell cell = row.getCell(c);

					map.put(row.getRowNum() + "_" + c, getCellValue(cell));
				}
			}

		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		return map;
	}

	public static void setData(String file, Map<String, String> map) throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("sheet1");
		// HSSFRow row = sheet.createRow();
		int rowSize = 0;
		int colSize = 0;

		for (String key : map.keySet()) {
			String[] arr = key.split("_");
			int row = Integer.parseInt(arr[0]);
			int col = Integer.parseInt(arr[1]);
			rowSize = Math.max(rowSize, row);
			colSize = Math.max(colSize, col);
		}
		Map<String, Cell> mapCell = new HashMap<String, Cell>();
		for (int i = 0; i <= rowSize; i++) {
			Row row = sheet.createRow(i);
			for (int j = 0; j <= colSize; j++) {
				Cell cell = row.createCell(j);
				mapCell.put(i + "_" + j, cell);
			}
		}
		for (String key : map.keySet()) {
			String value = map.get(key);
			mapCell.get(key).setCellValue(value);
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		fos.flush();

		fos.close();
	}

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
			java.util.regex.Pattern p = Pattern.compile(".0$");
			java.util.regex.Matcher m = p.matcher(doubleStr);
			if (m.find()) {
				doubleStr = doubleStr.replace(".0", "");
			}
		}

		return doubleStr;
	}
}
