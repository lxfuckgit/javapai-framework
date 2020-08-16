package com.javapai.framework.constant;

public final class CL_Formater {
	/**
	 * 默认格式:yyyy-MM-dd<br>
	 */
	public final static String FORMAT_1 = "yyyy-MM-dd";
	
	/**
	 * 默认格式:yyyy-MM-dd HH:mm:ss<br>
	 */
	public final static String FORMAT_2 = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 默认格式:yyyy-MM-dd kk:mm:ss<br>
	 */
	public final static String FORMAT_3 = "yyyy-MM-dd kk:mm:ss";// 此格式为跨平台的时间格式，用kk而不用hh是因为kk是24进制的而不随操作系统设置变动。
}
