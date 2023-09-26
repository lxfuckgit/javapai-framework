package com.javapai.framework.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 数据类型相关工具类.<br>
 * 
 * 基本类型有以下四种：
 * 
 * int长度数据类型有：byte(8bits)、short(16bits)、int(32bits)、long(64bits)。
 * float长度数据类型有：单精度(32bits float)、双精度(64bits double).<br>
 * boolean类型变量的取值有：ture、false.<br>
 * char数据类型有：unicode字符,16位.<br>
 * 
 * 对应的类类型：Integer、Float、Boolean、Character、Double、Short、Byte、Long
 * <br>
 * 转换原则:<br>
 * 从低精度向高精度转换 byte 、short、int、long、float、double、char
 * 注：两个char型运算时，自动转换为int型；当char与别的类型运算时，也会先自动转换为int型的，再做其它类型的自动转换
 * 
 * 基本类型向类类型转换
 * 
 * 正向转换：通过类包装器来new出一个新的类类型的变量 Integer a= new Integer(2); 反向转换：通过类包装器来转换 int
 * b=a.intValue();
 * 
 * 类类型向字符串转换
 * 
 * 正向转换：因为每个类都是object类的子类，而所有的object类都有一个toString()函数，所以通过toString()函数来转换即可
 * 反向转换：通过类包装器new出一个新的类类型的变量 eg1: int i=Integer.valueOf(“123”).intValue()
 * 说明：上例是将一个字符串转化成一个Integer对象，然后再调用这个对象的intValue()方法返回其对应的int数值。 eg2: float
 * f=Float.valueOf(“123”).floatValue()
 * 说明：上例是将一个字符串转化成一个Float对象，然后再调用这个对象的floatValue()方法返回其对应的float数值。 eg3: boolean
 * b=Boolean.valueOf(“123”).booleanValue()
 * 说明：上例是将一个字符串转化成一个Boolean对象，然后再调用这个对象的booleanValue()方法返回其对应的boolean数值。
 * eg4:double d=Double.valueOf(“123”).doublue()
 * 说明：上例是将一个字符串转化成一个Double对象，然后再调用这个对象的doublue()方法返回其对应的double数值。 eg5: long
 * l=Long.valueOf(“123”).longValue()
 * 说明：上例是将一个字符串转化成一个Long对象，然后再调用这个对象的longValue()方法返回其对应的long数值。 eg6:
 * char=Character.valueOf(“123”).charValue()
 * 说明：上例是将一个字符串转化成一个Character对象，然后再调用这个对象的charValue()方法返回其对应的char数值。
 * 
 * 基本类型向字符串的转换 正向转换： 如：int a=12; String b;b=a+””;
 * 
 * 反向转换： 通过类包装器 eg1:int i=Integer.parseInt(“123”) 说明：此方法只能适用于字符串转化成整型变量 eg2:
 * float f=Float.valueOf(“123”).floatValue()
 * 说明：上例是将一个字符串转化成一个Float对象，然后再调用这个对象的floatValue()方法返回其对应的float数值。 eg3: boolean
 * b=Boolean.valueOf(“123”).booleanValue()
 * 说明：上例是将一个字符串转化成一个Boolean对象，然后再调用这个对象的booleanValue()方法返回其对应的boolean数值。
 * eg4:double d=Double.valueOf(“123”).doublue()
 * 说明：上例是将一个字符串转化成一个Double对象，然后再调用这个对象的doublue()方法返回其对应的double数值。 eg5: long
 * l=Long.valueOf(“123”).longValue()
 * 说明：上例是将一个字符串转化成一个Long对象，然后再调用这个对象的longValue()方法返回其对应的long数值。 eg6:
 * char=Character.valueOf(“123”).charValue() 说明：上例是将一个字符串转化成一个Character对象，然后再
 * 
 *  * 与小数位精度(四舍五入等)相关的一些常用工具方法.
 * 
 * float/double的精度取值方式分为以下几种: <br>
 * java.math.BigDecimal.ROUND_UP <br>
 * java.math.BigDecimal.ROUND_DOWN <br>
 * java.math.BigDecimal.ROUND_CEILING <br>
 * java.math.BigDecimal.ROUND_FLOOR <br>
 * java.math.BigDecimal.ROUND_HALF_UP<br>
 * java.math.BigDecimal.ROUND_HALF_DOWN <br>
 * java.math.BigDecimal.ROUND_HALF_EVEN <br>
 * @author lx
 * 
 */
public class UtilNumber {
    // default scale and rounding mode for BigDecimals
    private static final int DEFAULT_BD_SCALE = 2;

	/**
	 * return true if characer c is a digit(0-9)
	 * 
	 * @param c
	 *            待认定字符
	 * @return true or false
	 */
	public static boolean isDigit(char c) {
		return Character.isDigit(c);
	}

	// string->byte
	// Byte static byte parseByte(String s)
	// byte->string
	// Byte static String toString(byte b)
	// char->string
	// Character static String to String (char c)
	// string->Short
	// Short static Short parseShort(String s)
	// Short->String
	// Short static String toString(Short s)
	// String->Integer
	// Integer static int parseInt(String s)
	// Integer->String
	// Integer static String tostring(int i)
	// String->Long
	// Long static long parseLong(String s)
	// Long->String
	// Long static String toString(Long i)
	// String->Float
	// Float static float parseFloat(String s)
	// Float->String
	// Float static String toString(float f)
	// String->Double
	// Double static double parseDouble(String s)
	// Double->String
	// Double static String toString(Double)
	/**
	 * 验证一个字符串是否是Double类型
	 * 
	 * @param s
	 *            要验证的字符串
	 * @return 如果是Double类型则返回true,否则返回false
	 */
	public static boolean isDouble(String s) {
		if (s == null || s.equals(""))
			return false;
		String num = "0123456789.";
		if (s.indexOf('.') >= 0)
			if (s.indexOf('.', s.indexOf('.') + 1) > 0)
				return false;
		for (int i = 0; i < s.length(); i++) {
			if (num.indexOf(s.charAt(i)) < 0) {
				return false;
			} else {
				try {
					Double.parseDouble(s);
				} catch (NumberFormatException e) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 验证一个字符串是否是Float类型
	 * 
	 * @param s
	 *            要验证的字符串
	 * @return 如果是Float类型则返回true,否则返回false
	 */
	public static boolean isFloat(String s) {
		if (s == null || s.equals(""))
			return false;
		String num = "0123456789.";
		if (s.indexOf('.') >= 0)
			if (s.indexOf('.', s.indexOf('.') + 1) > 0)
				return false;
		for (int i = 0; i < s.length(); i++) {
			if (num.indexOf(s.charAt(i)) < 0) {
				return false;
			} else {
				try {
					Float.parseFloat(s);
				} catch (NumberFormatException e) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 验证一个字符串是否是整形
	 * 
	 * @param s
	 *            要验证的字符串
	 * @return 如果是整形则返回true,否则返回false
	 */
	public static boolean isInteger(String s) {
		if (s == null || s.length() == 0) {
			return false;
		} else {
			String str = "0123456789";
			String num = "-0123456789";
			if (num.indexOf(s.charAt(0)) < 0)
				return false;
			for (int i = 1; i < s.length(); i++) {
				if (str.indexOf(s.charAt(i)) < 0) {
					return false;
				} else {
					try {
						Integer.parseInt(s);
					} catch (NumberFormatException e) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 验证一个字符串是否是一个.和一组数字组成
	 * 
	 * @param s
	 *            要传入的数字字符串
	 * @return 如果是一个长类型数字返回true,否则返回false
	 */
	public static boolean isLongNumber(String s) {
		if (s == null || s.equals(""))
			return false;
		String num = "0123456789.";
		if (s.indexOf('.') >= 0)
			if (s.indexOf('.', s.indexOf('.') + 1) > 0)
				return false;
		for (int i = 0; i < s.length(); i++) {
			if (num.indexOf(s.charAt(i)) < 0)
				return false;
		}
		return true;
	}

	/**
	 * 验证一个字符串是否是数字组成
	 * 
	 * @param s
	 *            要验证的字符串
	 * @return 如果字符串是数字组成的则返回true,否则返回false
	 */
	public static boolean isNumber(String s) {
		if (s == null || s.equals(""))
			return false;
		String num = "0123456789";
		for (int i = 0; i < s.length(); i++) {
			if (num.indexOf(s.charAt(i)) < 0)
				return false;
		}
		return true;
	}

	/**
	 * 验证一个字符串是否一个合法日期,日期格式：yyyy-MM-dd
	 * 
	 * @param date
	 *            要验证的字符串日期
	 * @return 如果字符串是一个合法的日期返回true,否则返回false
	 */
	public static boolean isDate(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	/**
	 * 对double数据进行取精度.
	 * <p>
	 * For example: <br>
	 * double value = 100.345678; <br>
	 * double ret = round(value,4,BigDecimal.ROUND_HALF_UP); <br>
	 * ret为100.3457 <br>
	 * 
	 * @param value
	 *            double数据.
	 * @param scale
	 *            精度位数(保留的小数位数).
	 * @param roundingMode
	 *            精度取值方式.
	 * @return 精度计算后的数据.
	 */
	public static double round(double value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		double d = bd.doubleValue();
		bd = null;
		return d;
	}
	
	public static BigDecimal A_Divide_B(BigDecimal a, BigDecimal b) {
		return A_Divide_B(a, b, DEFAULT_BD_SCALE, RoundingMode.HALF_UP);
	}
    
	public static BigDecimal A_Divide_B(BigDecimal a, BigDecimal b, int scale, RoundingMode roundingMode) {
		if (UtilValidate.isNotEmpty(a) && UtilValidate.isNotEmpty(b)) {
			if (b.doubleValue() == 0) {
				return new BigDecimal("0.0").setScale(scale);
			} else {
				return a.divide(b, scale, roundingMode);
			}
		} else {
			return new BigDecimal("0.0").setScale(scale);
		}
	}
    
    /**
     * Convert String to long
     * @param value
     * @param def default value
     * @return
     */
    public static long toLong(String value, long def) {
        if (UtilValidate.isEmpty(value)) {
            return def;
        }

        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return def;
        }
    }

    /**
     * Convert String to int
     * @param value
     * @param def default value
     * @return
     */
    public static int toInt(String value, int def) {
        if (UtilValidate.isEmpty(value)) {
            return def;
        }
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return def;
        }
    }
    
}