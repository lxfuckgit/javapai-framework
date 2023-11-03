package com.javapai.framework.enums;

/**
 * 路径下目标对象类型。<br>
 * 
 * Path Object Type Enums.
 * 
 * @author pooja
 *
 */
public enum POTEnum implements Enums<String, String> {// POT= Path Object Type

	/**
	 * 文件
	 */
	FILE("FILE", "文件类型"),

	/**
	 * 文件夹
	 */
	FOLDER("FOLDER", "文件夹类型");

	private String key;
	private String value;

	POTEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getValue() {
		return value;
	}
}
