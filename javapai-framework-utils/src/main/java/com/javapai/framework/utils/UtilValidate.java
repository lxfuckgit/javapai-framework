package com.javapai.framework.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * <br>
 * 依赖apache-commons.jar <br>
 * 
 * @author pooja
 *
 */
public class UtilValidate {
	
	/**
	 * linux or unix.
	 * 
	 * @return
	 */
	public static boolean isUnix() {
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
	}

	/**
	 * windows.
	 * 
	 * @return
	 */
	public static boolean isWindows() {
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("win") >= 0);
	}

	/**
	 * Mac.
	 * 
	 * @return
	 */
	public static boolean isMac() {
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("mac") >= 0);
	}

	/**
	 * Solaris.
	 * 
	 * @return
	 */
	public static boolean isSolaris() {
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("sunos") >= 0);
	}

	/**
	 * 定义所传入的字符串(S)是否为空或为null;
	 * 
	 * @param value
	 * @return true or false;
	 */
	public static boolean isEmpty(String value) {
		return org.apache.commons.lang3.StringUtils.isEmpty(value);
//		return ((value == null) || (value.length() == 0)) || "".equals(value);
	}
	
	/**
	 * Return <code>true</code> if the supplied <code>Map</code> is null or
	 * empty. Otherwise, return <code>false</code>.
	 * 
	 * @param map
	 *            the <code>Map</code> to check
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return (map == null || map.isEmpty());
	}
	
	/**
	 * Check whether collection c is empty.<br>
	 * Return <code>true</code> if the supplied <code>Collection</code> is null or empty.
	 * Otherwise, return <code>false</code>.
	 * @param collection the <code>Collection</code> to check
	 */
	public static boolean isEmpty(Collection<?> c) {
		return ((c == null) || c.isEmpty() || (c.size() == 0));
	}

	/**
	 * Check whether string s is NOT empty.<br>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
//		return ((s != null) && (s.length() > 0));
		return org.apache.commons.lang3.StringUtils.isNotEmpty(str);
	}

	/**
	 * Check whether collection c is NOT empty.
	 * 
	 **/
	public static boolean isNotEmpty(Collection<?> c) {
		return ((c != null) && (c.size() > 0));
	}
	
	/**
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isNullOrEmptyString(Object o) {
		if (o == null) {
			return true;
		}

		if (o instanceof String) {
			String str = (String) o;
			if (str.length() == 0)
				return true;
		}
		return false;
	}
	
	/**
	 * 可以用于判断 Map,Collection,String,Array是否为空
	 * 
	 * @param o
	 * @return
	 */
	@SuppressWarnings("all")
	public static boolean isEmpty(Object o) {
		if (o == null){
			return true;
		}
		if (o instanceof String) {
			if (((String) o).length() == 0) {
				return true;
			}
		} else if (o instanceof Collection) {
			if (((Collection) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (Array.getLength(o) == 0) {
				return true;
			}
		} else if (o instanceof Map) {
			if (((Map) o).isEmpty()) {
				return true;
			}
		} else {
			return false;
		}

		return false;
	}

	/**
	 * 可以用于判断 Map,Collection,String,Array是否不为空
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isNotEmpty(Object c) {
		return !isEmpty(c);
	}
	
    /**
     * <p>Checks if a String is whitespace, empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     * @since 2.0
     */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
    
    /**
     * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is
     *  not empty and not null and not whitespace
     * @since 2.0
     */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}
	
	/**
	 * 读取OS操作系统-环境变量(Environment Variable).<br>
	 * 
	 * @return
	 */
	public static Map<String, String> getOSEnvironment() {
		Map<String, String> map = System.getenv();
//		Set<Map.Entry<String, String>> entries = map.entrySet();
//		for (Map.Entry<String, String> entry : entries) {
//			System.out.println(entry.getKey() + ":" + entry.getValue());
//		}
//		System.out.println(map.get("ENV"));
		return map;
	}
	
	/**
	 * 读取jvm虚拟机系统-属性（System Properties）。<br>
	 * 
	 * jvm属性包括:<br>
	 * 1、jvm常规基本属性。<br>
	 * 2、主程序main(String[] args)的程序传参。 <br>
	 * 3、jvm启动传参(-Dxxx=???)。
	 * 
	 * @return
	 */
	public static Properties getJVMProperties() {
		Properties properties = System.getProperties();
//		Set<Map.Entry<Object, Object>> set = properties.entrySet();
//		for (Map.Entry<Object, Object> objectObjectEntry : set) {
//			System.out.println(objectObjectEntry.getKey() + ":" + objectObjectEntry.getValue());
//		}
//		System.out.println(properties.getProperty("env"));
		return properties;
	}

}
