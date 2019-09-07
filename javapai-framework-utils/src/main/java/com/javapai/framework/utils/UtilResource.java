package com.javapai.framework.utils;

import java.io.File;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * 资源访问工具类。<br>
 *  此工具类用于处理常资源文件相关的操作:<br>
 *  包括文件URL(UtilUrl)、文件路径(UtilPath)、属性文件内容读取(UtilProperty)。<br>
 * Resource路径问题无非归结为一点：找基点(相对某路路经，比如web、j2ee或jar包等)。 <br>
 * 通过合适的方式找到一个稳定的基点，然后通过这个基点找到你要的resource。 <br>
 * 
 * @author liu.xiang
 *
 */
public class UtilResource {
	private static final String module = UtilResource.class.getName();
	
	/**
	 * Web应用程序的根目录为基点读取资源文件.<br>
	 * 
	 * @return
	 */
	public String getWebappResource(HttpServletRequest request) {
		return getWebappResource(request.getServletContext());
	}
	
	/**
	 * @see getWebappResource(HttpServletRequest request)
	 * @param sc
	 * @return
	 */
	public String getWebappResource(ServletContext sc) {
		System.out.println(sc.getRealPath("/" ));
		return null;
	}

	/**
	 * 当前用户目录为根，读取文件.<br>
	 * 
	 * @return
	 */
	public String getUserdirResource() {
		System.out.println(System.getProperty("user.dir"));
		File f = new File("xxx");
		System.out.println(f.getAbsolutePath());
		
		return null;
	}

	public String getClasspathResource() {
		System.out
				.println("@" + Thread.currentThread().getContextClassLoader());
		System.out.println("@" + UtilResource.class.getClassLoader());
		System.out.println("@" + ClassLoader.getSystemClassLoader());

		return null;
	};
	
	/**
	 * 读取当前类路下的resource资源文件.<br>
	 * UtilResource以当前工具类为起点，读取resource参数所标识的资源文件.<br>
	 * 
	 * @param resourceName
	 *            资源文件名.<br>
	 * @return 返回一个java.net.URL对象。
	 */
	public static URL getResource(String resourceName) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);
        if (url == null) {
            url = UtilResource.class.getClassLoader().getResource(resourceName);
        }
        if ((url == null) && (resourceName != null) && ((resourceName.length() == 0) || (resourceName.charAt(0) != '/'))) { 
            return getResource('/' + resourceName);
        }
        return url;
    }
	
//	/**
//	 * 根据资源文件的名称和Name，获取中文文字
//	 * 
//	 * @param resource 资源文件名称
//	 * @param name 要查找的KEY
//	 * @return 如果不存在返回Name
//	 */
//	public static String getMessage(String resource, String name) {
//		if (resource == null || resource.length() <= 0)
//			return "";
//		if (name == null || name.length() <= 0)
//			return "";
//		ResourceBundle bundle = null;
//		bundle = getResourceBundle(resource, Locale.CHINESE);
//		if (bundle == null)
//			return name.toUpperCase();
//		String value = null;
//		try {
//			value = (String) bundle.getString(name);
//		} catch (Exception e) {
//			value = null;
////			Debug.log(e.getMessage(), module);
//		}
//		return value == null ? name.toUpperCase() : value.trim();
//	}
//
//	/**
//	 * 根据资源文件的名称和Name，获取中文文字
//	 * 
//	 * @param resource 资源文件名称
//	 * @param name 要查找的KEY
//	 * @return 如果不存在返回Name
//	 */
//	public static String getMessageForNull(String resource, String name) {
//		if (resource == null || resource.length() <= 0)
//			return "";
//		if (name == null || name.length() <= 0)
//			return "";
//		ResourceBundle bundle = null;
//		bundle = getResourceBundle(resource, Locale.CHINESE);
//		if (bundle == null)
//			return null;
//		String value = null;
//		try {
//			value = (String) bundle.getString(name);
//		} catch (Exception e) {
//			value = null;
////			Debug.log(e.getMessage(), module);
//		}
//		return value == null ? null : value.trim();
//	}
//	/**
//	 * 根据资源文件的名称和Name，判断资源文件中是否存在该Name
//	 * @param resource 资源文件名称
//	 * @param name 要查找的KEY
//	 * @return 如果不存在返回Name
//	 * @throws Exception 
//	 */
//	public static boolean isContainsKeyFromResource(String resource, String name) {
//		if (resource == null || resource.length() <= 0)
//			return false;
//		if (name == null || name.length() <= 0)
//			return false;
//		ResourceBundle bundle = null;
//		bundle = getResourceBundle(resource, Locale.CHINESE);
//		if (bundle == null)
//			return false;
//		String value = null;
//		try {
//			value = (String) bundle.getString(name);
//		} catch (Exception e) {
//			value = null;
////			Debug.log(e.getMessage(), module);
//		}
//		return value == null ? false : true;
//	}
//	/**
//	 * 根据资源文件名称和语言来查询文件Bundle
//	 * 
//	 * @param resource 资源文件名称
//	 * @param locale 语言
//	 * @return 文件Bundle
//	 */
//	public static ResourceBundle getResourceBundle(String resource, Locale locale) {
//		ResourceBundle bundle = null;
//		try {
//			if (UtilValidate.isEmpty(resource) || locale == null) {
//				return bundle;
//			}
//			bundle = UtilResourceBundle.getBundle(resource, locale, (ClassLoader) null);
//		} catch (MissingResourceException e) {
//			bundle = null;
//			Debug.log(e.getMessage(), module);
//		}
//		return bundle;
//	}


}
