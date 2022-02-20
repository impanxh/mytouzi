package com.huangxifeng.core.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 验证�?
 * 
 * @author JWinder(2010-09-03)
 */
public class ValidateUtil {

	public static boolean isAddress(String address) {
		if (isNull(address)) {
			return false;
		}

		char[] c = address.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if ((!isChar(c[i])) && (!isDigit(c[i])) && (!Character.isWhitespace(c[i]))) {

				return false;
			}
		}

		return true;
	}

	public static boolean isChar(char c) {
		return Character.isLetter(c);
	}

	public static boolean isChar(String s) {
		if (isNull(s)) {
			return false;
		}

		char[] c = s.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (!isChar(c[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean isDigit(char c) {
		int x = (int) c;

		if ((x >= 48) && (x <= 57)) {
			return true;
		}

		return false;
	}

	public static boolean isDigit(String s) {
		if (isNull(s)) {
			return false;
		}

		char[] c = s.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (!isDigit(c[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean isHex(String s) {
		if (isNull(s)) {
			return false;
		}

		return true;
	}

	public static boolean isHTML(String s) {
		if (isNull(s)) {
			return false;
		}

		if (((s.indexOf("<html>") != -1) || (s.indexOf("<HTML>") != -1))
				&& ((s.indexOf("</html>") != -1) || (s.indexOf("</HTML>") != -1))) {

			return true;
		}

		return false;
	}

	public static boolean isLUHN(String number) {
		if (number == null) {
			return false;
		}

		number = StringUtil.reverse(number);

		int total = 0;

		for (int i = 0; i < number.length(); i++) {
			int x = 0;

			if (((i + 1) % 2) == 0) {
				x = Integer.parseInt(number.substring(i, i + 1)) * 2;

				if (x >= 10) {
					String s = Integer.toString(x);

					x = Integer.parseInt(s.substring(0, 1)) + Integer.parseInt(s.substring(1, 2));
				}
			} else {
				x = Integer.parseInt(number.substring(i, i + 1));
			}

			total = total + x;
		}

		if ((total % 10) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isName(String name) {
		if (isNull(name)) {
			return false;
		}

		char[] c = name.trim().toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (((!isChar(c[i])) && (!Character.isWhitespace(c[i]))) || (c[i] == ',')) {

				return false;
			}
		}

		return true;
	}

	public static boolean isPassword(String password) {
		if (isNull(password)) {
			return false;
		}

		if (password.length() < 4) {
			return false;
		}

		char[] c = password.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if ((!isChar(c[i])) && (!isDigit(c[i]))) {

				return false;
			}
		}

		return true;
	}

	public static boolean isNumber(String number) {
		if (isNull(number)) {
			return false;
		}

		char[] c = number.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (!isDigit(c[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		}

		obj = obj.toString().trim();

		if ((obj.equals(StringPool.NULL)) || (obj.equals(StringPool.BLANK))) {
			return true;
		}

		return false;
	}

	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	/**
	 * 判断Map集合是否为Null或isEmpty
	 * 
	 * @author HuangFeng(2009-10-21)
	 * @param map
	 *            Map
	 * @return <code>true</code>->isEmpty, <code>false</code>->isNotEmpty
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		if (null == map || map.isEmpty() || map.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断Map集合不为Null或isEmpty
	 * 
	 * @author HuangFeng(2009-10-21)
	 * @param map
	 *            Map
	 * @return <code>true</code>->isNotEmpty, <code>false</code>->isEmpty
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

	/**
	 * 判断Collection集合类包括子类List,Set是否为Null或isEmpty
	 * 
	 * @author HuangFeng(2009-10-21)
	 * @param coll
	 *            Collection及子类：<code>List</code>, <code>Set</code>
	 * @return <code>true</code>->isEmpty, <code>false</code>->isNotEmpty
	 */
	public static boolean isEmpty(Collection<?> coll) {
		if (null == coll || coll.isEmpty() || coll.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断Collection集合类包括子类List,Set为为Null或isEmpty
	 * 
	 * @author HuangFeng(2009-10-21)
	 * @param coll
	 *            Collection及子类：<code>List</code>, <code>Set</code>
	 * @return <code>true</code>->isNotEmpty, <code>false</code>->isEmpty
	 */
	public static boolean isNotEmpty(Collection<?> coll) {
		return !isEmpty(coll);
	}

	/**
	 * 是否Long型ID：[<code>id!=null && id != 0</code>]
	 * 
	 * @author HuangFeng(Feb 1, 2010)
	 * @param id
	 *            Long型ID
	 */
	public static boolean isLongId(Long id) {
		if (isNull(id)) {
			return false;
		} else if (0l >= id) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 是否Long型ID：[<code>id!=null && id != 0</code>]
	 * 
	 * @author HuangFeng(Feb 1, 2010)
	 * @param id
	 *            Long型ID
	 */
	public static boolean isLongId(String id) {
		try {
			if (isNull(id)) {
				return false;
			} else if (0l >= Long.valueOf(id)) {
				return false;
			} else {
				return true;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isNotLongId(Long id) {
		return !isLongId(id);
	}

	/**
	 * 判断不为空并不为0.0的Double数据
	 * 
	 * @author HuangFeng(2011-3-15)
	 * @param doub
	 *            Double
	 */
	public static final boolean isDouble(Double doub) {
		if (isNull(doub)) {
			return false;
		}

		if (0.0 == doub) {
			return false;
		}

		return true;
	}

	public static final boolean isNotDouble(Double doub) {
		return !isDouble(doub);
	}

	public static boolean equals(Long long0, Long long1) {
		if (ValidateUtil.isNull(long0) || ValidateUtil.isNull(long1)) {
			return false;
		}

		return (long0.longValue() == long1.longValue());
	}

	public static boolean equals(Integer integer0, Integer integer1) {
		if (ValidateUtil.isNull(integer0) || ValidateUtil.isNull(integer1)) {
			return false;
		}

		return (integer0.intValue() == integer1.intValue());
	}

	public static boolean equals(Double double0, Double double1) {
		if (ValidateUtil.isNull(double0) || ValidateUtil.isNull(double1)) {
			return false;
		}

		return (double0.doubleValue() == double1.doubleValue());
	}

	public static boolean equals(String string0, String string1) {
		if (ValidateUtil.isNull(string0) || ValidateUtil.isNull(string1)) {
			return false;
		}

		return (string0.toString().equals(string1.toString()));
	}
}
