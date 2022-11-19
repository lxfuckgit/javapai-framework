package com.javapai.framework.common.vo.graph;

import com.javapai.framework.common.vo.node.NodeVO;

public class GraphVO extends NodeVO {
	/**
	 * 节点名称.
	 */
	private String name;
	/**
	 * 节点symbol.
	 */
	private String symbol;
	
//	private String symbolSize;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
