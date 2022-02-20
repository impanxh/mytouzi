/*
 * Copyright @2012 dnbiz.com. All rights reserved.
 */
package com.huangxifeng.core.utils;

/**
 *
 * @author JWinder.Huang (Jun 20, 2012)
 */
public class StringPool {
	//public static final String PROJECT_DIR = "/opt/tomcat_gpz/"  ;
	
	public static final String PROJECT_DIR =  "/Users/xinghuapan/Documents/gupiaodata"; 

	public static final String AMPERSAND = "&";

	public static final String APOSTROPHE = "'";

	public static final String AT = "@";

	public static final String BACK_SLASH = "\\";

	public static final String BLANK = "";

	public static final String CLOSE_BRACKET = "[";

	public static final String CLOSE_CURLY_BRACE = "}";

	/** 冒号[:] */
	public static final String COLON = ":";

	/** 双冒号[::] */
	public static final String COLON_TWO = "::";

	public static final String COMMA = ",";

	/** 引号["] */
	public static final String INVERTED_COMMA = "\"";

	public static final String DASH = "-";

	public static final String EQUAL = "=";

	public static final String FORWARD_SLASH = "/";

	public static final String NULL = "null";

	public static final String OPEN_BRACKET = "[";

	public static final String OPEN_CURLY_BRACE = "{";

	public static final String PERCENT = "%";

	public static final String PERIOD = ".";

	public static final String QUESTION = "?";

	public static final String SEMICOLON = ";";

	public static final String SLASH = FORWARD_SLASH;

	public static final String SPACE = " ";

	public static final String STAR = "*";

	public static final String UNDERLINE = "_";

	public static final String EXCALMATORY_POINT = "!";

	/** # */
	public static final String WELL = "#";

	/** <code>(String) '\n' </code> */
	public static final String _N_SLASH = "\n";

	public static final String N = "N";
	public static final String Y = "Y";

	public static final String N_CN = "�?";
	public static final String Y_CN = "�?";

	// add:HuangFeng(2009-12-09) {
	public static final String UTF_8 = "UTF-8";
	public static final String UTF_16 = "UTF-16";
	public static final String ISO_8859_1 = "iso-8859-1";
	public static final String GBK = "GBK";
	public static final String GB2312 = "GB2312";

	// }

	public static final String JPEG = "jpeg";
	public static final String BMP = "bmp";
	public static final String JPG = "jpg";
	public static final String PNG = "png";
	public static final String GIF = "gif";
	public static final String TBI = "tbi";
	public static final String XML = "xml";
	public static final String CSV = "csv";
	public static final String ZIP = "zip";
	/** access数据文件�? */
	public static final String MDB = "mdb";

	/** <code>(char) ',' </code> */
	public static final char CHAR_COMMA = ',';

	/** <code>(char) '\t' </code> */
	public static final char CHAR_T = '\t';

	public static final String SHTML = "shtml";

	public static void main(String[] args) {
		System.out.println("号码-13968127807;面额-50;磊在在在�?".replace(";", ",").replace("-", "_"));
	}

}
