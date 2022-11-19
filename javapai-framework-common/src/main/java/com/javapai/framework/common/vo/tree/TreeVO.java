package com.javapai.framework.common.vo.tree;

import java.util.List;

/**
 * 树结构对象.<br>
 * 
 * @author pooja
 *
 */
public final class TreeVO {
	/**
	 * 
	 */
	private String key;
	/**
	 * 
	 */
	private String value;
	/**
	 * 
	 */
	private List<TreeVO> children;
	
	public TreeVO() {
	}
	
	public TreeVO(String key, String value) {
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

	public List<TreeVO> getChildren() {
		return children;
	}

	public void setChildren(List<TreeVO> children) {
		this.children = children;
	}

}
