package com.javapai.framework.common.vo.tree;

import java.util.List;

import com.javapai.framework.common.vo.node.NodeVO;

/**
 * 树结构对象.<br>
 * 
 * @author pooja
 *
 */
public final class TreeVO extends NodeVO {
	/**
	 * 树子节点。
	 */
	private List<TreeVO> children;

	public TreeVO() {
	}

	public TreeVO(String key, String value) {
		super(key, value);
	}

	public List<TreeVO> getChildren() {
		return children;
	}

	public void setChildren(List<TreeVO> children) {
		this.children = children;
	}

}
