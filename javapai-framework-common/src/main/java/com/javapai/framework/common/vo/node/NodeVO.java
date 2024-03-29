package com.javapai.framework.common.vo.node;

import com.javapai.framework.common.I_KV;

/**
 * 结点-VO值对象。<br>
 * 
 * 结点对象由两个【必选属性（key和value）】和两个【可选属性（icon和route）】组成。<br>
 * 
 * @author pooja
 *
 */
public class NodeVO implements I_KV<String, String> {
	/**
	 * 结点标识.
	 */
	private String key;
	/**
	 * 结点名称.
	 */
	private String value;
	/**
	 * 结点图标.
	 */
	private String icon;
	/**
	 * 结点路由(jump url)
	 */
	private String route;

	public NodeVO() {
		super();
	}

	public NodeVO(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public NodeVO(String key, String value, String icon) {
		this.key = key;
		this.value = value;
		this.icon = icon;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

}
