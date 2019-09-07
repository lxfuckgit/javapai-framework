package com.javapai.framework.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author pooja
 *
 */
public class UtilHttpServlet {

	/**
	 * 获取请求源(客户端)的IP地址.<br>
	 * 支持多级反向代理.<br>
	 * 
	 * @param request
	 * @return 客户端真实IP地址
	 */
	public static String getRequestIpAddr(final HttpServletRequest request) {
		String port = request.getHeader("remote-port");
		System.out.println(">>>客户端源端口为："+port);
		
		String ip = request.getHeader("x-forwarded-for");
//		String remoteAddr = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// request.getRemoteHost() 获取的就是访问者的ip地址~
		return ip;
	}
	
	/**
	 * 设置客户端缓存过期时间 Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		long timeMillis = System.currentTimeMillis();
		// Http 1.0 header
		response.setDateHeader("Expires", timeMillis + expiresSeconds * 1000);
		// Http 1.1 header
		response.setHeader("Cache-Control", "max-age=" + expiresSeconds);
	}
	
	/**
	 * 设置客户端无缓存Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 0);
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache");
	}
	
	/**
	 * 设置LastModified Header.
	 */
	public static void setLastModifiedHeader(HttpServletResponse response,
			long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	/**
	 * 设置Etag Header.
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	/**
	 * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
	 * 
	 * 如果无修改, checkIfModify返回false ,设置304 not modify status.
	 * 
	 * @param lastModified
	 *            内容的最后修改时间.
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request,
			HttpServletResponse response, long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 检查浏览器客户端是否支持gzip编码.
	 */
	public static boolean checkAccetptGzip(HttpServletRequest request) {
		// Http1.1 header
		String acceptEncoding = request.getHeader("Accept-Encoding");
		if (StringUtils.contains(acceptEncoding, "gzip")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 设置Gzip Header并返回GZIPOutputStream.
	 */
	public static OutputStream buildGzipOutputStream(
			HttpServletResponse response) throws IOException {
		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("Vary", "Accept-Encoding");
		return new GZIPOutputStream(response.getOutputStream());
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * @param fileName
	 *            下载后的文件名.
	 */
	public static void setDownloadableHeader(HttpServletResponse response, String fileName) {
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");
	}

	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public static void setCookieValue(HttpServletResponse response,
			String cookieName, String value, int cookieAge) {
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setPath("/");
		cookie.setMaxAge(cookieAge);
		response.addCookie(cookie);
	}

	/**
	 * 根据浏览器 If-None-Match Header, 计算Etag是否已无效.
	 * 
	 * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
	 * 
	 * @param etag
	 *            内容的ETag.
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request,
			HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(
						headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * 取得带相同前缀的Request Parameters.
	 * 
	 * 返回的结果Parameter名已去除前缀.
	 */
	public static Map<String, String> getParametersStartingWith(
			HttpServletRequest request, String prefix) {
		return null;// org.springframework.web.util.WebUtils.getParametersStartingWith(request,
					// prefix);
	}

	/**
	 * 在Java上的HTTP代理设置
	 */
	public void setHttpProxy() {
		System.getProperties().put("http.proxyHost", "someProxyURL");
		System.getProperties().put("http.proxyPort", "someProxyPort");
		System.getProperties().put("http.proxyUser", "someUserName");
		System.getProperties().put("http.proxyPassword", "somePassword");
		System.getProperties().put("http.proxyHost", "someProxyURL");
		System.getProperties().put("http.proxyPort", "someProxyPort");
		System.getProperties().put("http.proxyUser", "someUserName");
		System.getProperties().put("http.proxyPassword", "somePassword");
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public String getServerRootUrl(HttpServletRequest request) {
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append(request.getScheme());
		return requestUrl.toString();
	}
	
//	public static HttpServletRequest getRequest() {
//		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//	}

	/**
	 * 判断是否为ajax请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		if (request == null) {
			return false;
		}
		String httpHeader = request.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(httpHeader)) {
			return true;
		}
		
		return false;
	}
	
}
