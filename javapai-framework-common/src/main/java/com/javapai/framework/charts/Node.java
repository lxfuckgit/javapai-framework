package com.javapai.framework.charts;

import com.javapai.framework.common.I_KV;

/**
 * 结点-VO值对象。<br>
 * 
 * 一个结点对象由两个属性组成：key和value.<br>
 * 
 * @author pooja
 *
 */
public class Node implements I_KV<String, String> {
	/**
	 * 结点标识.
	 */
	private String key;
	/**
	 * 结点值.
	 */
	private String value;

	public Node() {
		super();
	}

	public Node(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
