package com.javapai.framework.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 相关处理类.<br>
 * <p>
 * 
 * 依赖commons-lang3.jar<br>
 * 
 * @author lx
 * 
 */
public class UtilString {
	public static final String phoneNumberDelimiters = "()- ";
	
	public static byte[] hex2Byte(String source) {
		byte[] sourceBytes = new byte[source.length() / 2];
		for (int i = 0; i < sourceBytes.length; i++) {
			sourceBytes[i] = (byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
		}
		return sourceBytes;
	}
	
	/**
	 * 将指定String 转成指定模式的java.util.Date类型. <br>
	 * 
	 * @param string
	 *            String类型日期
	 * @param pattern
	 *            日期显示模式.<br>
	 *            不同的模式以返回不同的日期格式, 当pattern为""时，默认格式为：yyyy-MM-dd kk:mm:ss.<br>
	 * @return date
	 * @throws ParseException
	 *             解析出错
	 */
	public synchronized static Date String2Date(String string, String pattern)
			throws ParseException {
		// java.util.Date date =
		// java.text.DateFormat.getDateInstance().parse(str);

		SimpleDateFormat sdf = new SimpleDateFormat();

		if ("".equals(pattern)) {
			pattern = "yyyy-MM-dd kk:mm:ss";
		} else {
			sdf.applyPattern(pattern);
		}

		java.util.Date date = sdf.parse(string);

		return date;
	}

	/**
	 * String 转换成整数 int.<br>
	 * 
	 * @param str
	 * @return
	 */
	public static int String2Int(String str) {
		if (UtilValidate.isEmpty(str)) {
			return 0;
		} else {
			return Integer.valueOf(str).intValue();
//			return Integer.parseInt([str],[int radix]);
		}
		// 注: 字串转成 Double, Float, Long 的方法大同小异.
	}
	
	/**
	 * String转换成BigDecimal.<br>
	 * 
	 * @param str
	 * @return
	 */
	public static BigDecimal String2BigDecimal(String str) {
		if (UtilValidate.isEmpty(str)) {
			return new BigDecimal(0);
		} else {
			return new BigDecimal(str);
		}
	}

	/**
	 * 功能描述：判断输入的字符是否为大写
	 * 
	 * @param str
	 *            传入的字符
	 * @return 如果是大写返回true,否则返回false
	 */
	public static boolean isUpperCase(String str) {
		Pattern pattern = Pattern.compile("^[A-Z]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 功能描述：判断输入的字符是否为小写
	 * 
	 * @param str
	 *            传入的字符
	 * @return 如果是小写返回true,否则返回false
	 */
	public static boolean isLowerCase(String str) {
		Pattern pattern = Pattern.compile("^[a-z]+$");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	private static final boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) {
			return true;
		}
		if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			/*中文的 “ 号*/
			return true;
		}
		if (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) {
			/*中文的 。 号*/
			return true;
		}
		if (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			/*中文的 ， 号*/
			return true;
		}
		if (ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS) {
			return true;
		}
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static final boolean isChinese(String string) {
		char[] ch = string.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (isChinese(ch[i])) {
				return true;
			}
		}
		return false;
	}
	
    /**
	 * isChinesePhoneNumber returns true if string s is a valid Chinese phone
	 * number. <br>
	 * Must be digits only; any length OK. May be prefixed by + character.
	 */
	public static boolean isChinesePhoneNumber(String s) {
		if (UtilValidate.isEmpty(s)){
			return true;
		}
		String normalizedPhone = stripCharsInBag(s, phoneNumberDelimiters);
		return isPositiveInteger(normalizedPhone);
	}

	/**
	 * isChinesePhoneNumber returns true if string s is a valid Chinese phone
	 * number. <br>
	 * Must be digits only; any length OK. May be prefixed by + character.
	 */
	public static boolean isChineseMobilePhoneNumber(String s) {
		if (UtilValidate.isEmpty(s)) {
			return true;
		}
		String normalizedPhone = stripCharsInBag(s, phoneNumberDelimiters);
		if (normalizedPhone.startsWith("86")) {
			normalizedPhone = normalizedPhone.substring(2);
		}
		if (normalizedPhone.length() == 11) {
			return true;
		}
		return false;
	}
	
    /** Removes all characters which appear in string bag from string s. */
    public static String stripCharsInBag(String s, String bag) {
        int i;
        StringBuilder stringBuilder = new StringBuilder("");

        // Search through string's characters one by one.
        // If character is not in bag, append to returnString.
        for (i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (bag.indexOf(c) == -1) stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
    
    /** Returns true if string s is an integer > 0. NOTE: using the Java Long object for greatest precision */
    public static boolean isPositiveInteger(String s) {
        if (UtilValidate.isEmpty(s)) return true;

        try {
            long temp = Long.parseLong(s);

            if (temp > 0) return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }
	
	/**
	 * 一个String是否带有中文字符。<br>
	 * 
	 * @param string
	 * @return
	 */
	public static final boolean hasChineseCharacter(String string) {
		char[] charArray = string.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			/*中文在Unicode编码区间为：0x4e00--0x9fbb*/
			if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 一个String是否带有英文字符。<br>
	 * 
	 * @param chineseStr
	 * @return
	 */
	public static final boolean hasEnglishCharacter(String chineseStr) {
		char[] charArray = chineseStr.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			/*仅仅判断的是非英文字符，该条件得出的结果有中文字符有中文标点符号有日文和韩文*/
			if (charArray[i] > 255 || charArray[i] < 0) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 去除字符串中的空格、回车、换行符、制表符
	 * 
	 * @param inStr
	 * @return String
	 */
	public static String replaceBlank(String inStr) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(inStr);
		return m.replaceAll("");
	}

	/**
	 * 去除字符串中的回车、换行符、制表符，但保留空格
	 * 
	 * @param inStr
	 * @return String
	 */
	public static String replaceBlank2(String inStr) {
		Pattern p = Pattern.compile("\\[s\\s]|\t|\r|\n");
		Matcher m = p.matcher(inStr);
		return m.replaceAll("");
	}
	
	/**
	 * 过滤特殊HTML字符
	 * 
	 * @param htmlString
	 * @return
	 */
	public String filterHtml(String htmlString) {
		return "";
	}

	/**
	 * 将${str}字符转换成url编码.
	 * 
	 * @param str
	 * @return
	 */
	public static String escapeHtml(String str) {
//		return org.apache.commons.lang.StringEscapeUtils.escapeHtml(str);
		return org.apache.commons.lang3.StringEscapeUtils.escapeHtml3(str);
	}
	
	/**
	 * 将url编码形式的${str}字符转换成原来字符.
	 * 
	 * @param str
	 * @return
	 */
	public static String unescapeHtml(String str) {
//		return org.apache.commons.lang.StringEscapeUtils.unescapeHtml(str);
		return org.apache.commons.lang3.StringEscapeUtils.unescapeHtml3(str);
	}

    /**
     * 将str将多个分隔符进行切分，
     * 
     * 示例：StringTokenizerUtils.split("1,2;3 4"," ,;");
     * 返回: ["1","2","3","4"]
     * 
     * @param str
     * @param seperators
     * @return
     */
	public static String[] split(String str,String seperators) {
		StringTokenizer tokenlizer = new StringTokenizer(str,seperators);
		List<String> result = new ArrayList<String>();
		
		while(tokenlizer.hasMoreElements()) {
			Object s = tokenlizer.nextElement();
			result.add(s.toString());
		}
		return (String[])result.toArray(new String[result.size()]);
	}
	
	/**
	 * <p>
	 * Reverses a String as per {@link StringBuffer#reverse()}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String returns <code>null</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.reverse(null)  = null
	 * StringUtils.reverse("")    = ""
	 * StringUtils.reverse("bat") = "tab"
	 * </pre>
	 * 
	 * @param str
	 *            the String to reverse, may be null
	 * @return the reversed String, <code>null</code> if null String input
	 */
	public static String reverse(String str) {
		if (str == null) {
			return null;
		}
		return new StringBuffer(str).reverse().toString();
	}
	
	/**
	 * <p>
	 * Checks if the String contains only unicode letters.
	 * </p>
	 *
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String ("")
	 * will return <code>true</code>.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isAlpha(null)   = false
	 * StringUtils.isAlpha("")     = true
	 * StringUtils.isAlpha("  ")   = false
	 * StringUtils.isAlpha("abc")  = true
	 * StringUtils.isAlpha("ab2c") = false
	 * StringUtils.isAlpha("ab-c") = false
	 * </pre>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if only contains letters, and is non-null
	 */
	public static boolean isAlpha(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLetter(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the String contains only unicode letters and space (' ').
	 * </p>
	 *
	 * <p>
	 * <code>null</code> will return <code>false</code> An empty String ("")
	 * will return <code>true</code>.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isAlphaSpace(null)   = false
	 * StringUtils.isAlphaSpace("")     = true
	 * StringUtils.isAlphaSpace("  ")   = true
	 * StringUtils.isAlphaSpace("abc")  = true
	 * StringUtils.isAlphaSpace("ab c") = true
	 * StringUtils.isAlphaSpace("ab2c") = false
	 * StringUtils.isAlphaSpace("ab-c") = false
	 * </pre>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if only contains letters and space, and is
	 *         non-null
	 */
	public static boolean isAlphaSpace(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if ((Character.isLetter(str.charAt(i)) == false)
					&& (str.charAt(i) != ' ')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the String contains only unicode letters or digits.
	 * </p>
	 *
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String ("")
	 * will return <code>true</code>.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isAlphanumeric(null)   = false
	 * StringUtils.isAlphanumeric("")     = true
	 * StringUtils.isAlphanumeric("  ")   = false
	 * StringUtils.isAlphanumeric("abc")  = true
	 * StringUtils.isAlphanumeric("ab c") = false
	 * StringUtils.isAlphanumeric("ab2c") = true
	 * StringUtils.isAlphanumeric("ab-c") = false
	 * </pre>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if only contains letters or digits, and is
	 *         non-null
	 */
	public static boolean isAlphanumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLetterOrDigit(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the String contains only unicode letters, digits or space (
	 * <code>' '</code>).
	 * </p>
	 *
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String ("")
	 * will return <code>true</code>.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isAlphanumeric(null)   = false
	 * StringUtils.isAlphanumeric("")     = true
	 * StringUtils.isAlphanumeric("  ")   = true
	 * StringUtils.isAlphanumeric("abc")  = true
	 * StringUtils.isAlphanumeric("ab c") = true
	 * StringUtils.isAlphanumeric("ab2c") = true
	 * StringUtils.isAlphanumeric("ab-c") = false
	 * </pre>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if only contains letters, digits or space, and
	 *         is non-null
	 */
	public static boolean isAlphanumericSpace(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if ((Character.isLetterOrDigit(str.charAt(i)) == false)
					&& (str.charAt(i) != ' ')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the String contains only unicode digits. A decimal point is not
	 * a unicode digit and returns false.
	 * </p>
	 *
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String ("")
	 * will return <code>true</code>.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isNumeric(null)   = false
	 * StringUtils.isNumeric("")     = true
	 * StringUtils.isNumeric("  ")   = false
	 * StringUtils.isNumeric("123")  = true
	 * StringUtils.isNumeric("12 3") = false
	 * StringUtils.isNumeric("ab2c") = false
	 * StringUtils.isNumeric("12-3") = false
	 * StringUtils.isNumeric("12.3") = false
	 * </pre>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if only contains digits, and is non-null
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the String contains only unicode digits or space (
	 * <code>' '</code>). A decimal point is not a unicode digit and returns
	 * false.
	 * </p>
	 *
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String ("")
	 * will return <code>true</code>.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isNumeric(null)   = false
	 * StringUtils.isNumeric("")     = true
	 * StringUtils.isNumeric("  ")   = true
	 * StringUtils.isNumeric("123")  = true
	 * StringUtils.isNumeric("12 3") = true
	 * StringUtils.isNumeric("ab2c") = false
	 * StringUtils.isNumeric("12-3") = false
	 * StringUtils.isNumeric("12.3") = false
	 * </pre>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if only contains digits or space, and is
	 *         non-null
	 */
	public static boolean isNumericSpace(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if ((Character.isDigit(str.charAt(i)) == false)
					&& (str.charAt(i) != ' ')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the String contains only whitespace.
	 * </p>
	 *
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String ("")
	 * will return <code>true</code>.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isWhitespace(null)   = false
	 * StringUtils.isWhitespace("")     = true
	 * StringUtils.isWhitespace("  ")   = true
	 * StringUtils.isWhitespace("abc")  = false
	 * StringUtils.isWhitespace("ab2c") = false
	 * StringUtils.isWhitespace("ab-c") = false
	 * </pre>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if only contains whitespace, and is non-null
	 * @since 2.0
	 */
	public static boolean isWhitespace(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the String contains only lowercase characters.
	 * </p>
	 *
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String ("")
	 * will return <code>false</code>.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isAllLowerCase(null)   = false
	 * StringUtils.isAllLowerCase("")     = false
	 * StringUtils.isAllLowerCase("  ")   = false
	 * StringUtils.isAllLowerCase("abc")  = true
	 * StringUtils.isAllLowerCase("abC") = false
	 * </pre>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if only contains lowercase characters, and is
	 *         non-null
	 * @since 2.5
	 */
	public static boolean isAllLowerCase(String str) {
//		if (str == null || isEmpty(str)) {
//			return false;
//		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLowerCase(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the String contains only uppercase characters.
	 * </p>
	 *
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String ("")
	 * will return <code>false</code>.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isAllUpperCase(null)   = false
	 * StringUtils.isAllUpperCase("")     = false
	 * StringUtils.isAllUpperCase("  ")   = false
	 * StringUtils.isAllUpperCase("ABC")  = true
	 * StringUtils.isAllUpperCase("aBC") = false
	 * </pre>
	 *
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if only contains uppercase characters, and is
	 *         non-null
	 * @since 2.5
	 */
	public static boolean isAllUpperCase(String str) {
//		if (str == null || isEmpty(str)) {
//			return false;
//		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isUpperCase(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

}
