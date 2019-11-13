package com.javapai.framework.enums;

/**
 * 区域类型枚举<br>
 * 
 * @author liu.xiang
 *
 */
public enum ZdGeoEnum {
	/**
	 * 省
	 */
	PROVINCE("PROVINCE"),
	/**
	 * 市
	 **/
	CITY("CITY"),
	/**
	 * 县
	 */
	COUNTY("COUNTY"),
	/**
	 * 自治区
	 */
	REGION("REGION"),
	/**
	 * 直辖市
	 */
	MUNICIPALITY("MUNICIPALITY");

	private String value = "";

	private ZdGeoEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
