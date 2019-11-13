package com.javapai.framework.enums;

/**
 * 数据来源枚举. <br>
 * 
 * @author liu.xiang
 *
 */
public enum FromEnum {
	/**
	 * 纯pc网页
	 */
	FROM_PC("FROM_PC"),
	/**
	 * ios
	 */
	FROM_IOS("FROM_IOS"),
	/**
	 * android
	 */
	FROM_ANDROID("FROM_ANDROID"),
	/**
	 * 微信对接
	 */
	FROM_WEIXIN("FROM_WEIXIN"),
	/**
	 * wap
	 */
	FROM_WAP("FROM_WAP");

	private String value = "";

	private FromEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
