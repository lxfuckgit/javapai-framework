package com.javapai.framework.fileparse.excel.config;

import java.util.List;

/**
 * Sheet表单操作策略配置项.<br>
 * 
 * @author pooja
 * @Deprecated
 *
 */
public final class SheetConfig {
	/**
	 * 当前sheet表单的标题行索引值。<br>
	 * 提示：默认第1行为标题行，索引值为0。<br>
	 */
	private Integer titleIndex;

	/**
	 * 当前sheet表单的数据行开始索引值。<br>
	 * 提示：默认第2行为数据行，索引值为1。<br>
	 */
	private Integer dataIndex;

	/**
	 * 当前sheet表单的备注行索引值.<br>
	 * 提示：用于特定情况下提取备注数据，默认无特殊备注行。<br>
	 */
	private List<Integer> noteIndex;
	
	public List<Integer> mergerIndex;

	public SheetConfig() {
	}

	public SheetConfig(Integer titleIndex) {
		this.titleIndex = titleIndex;
	}

	public SheetConfig(Integer titleIndex, Integer dataIndex) {
		this.titleIndex = titleIndex;
		this.dataIndex = dataIndex;
	}

	/**
	 * @see #titleIndex
	 */
	public Integer getTitleIndex() {
		return titleIndex == null ? 0 : titleIndex;
	}

	/**
	 * @see #titleIndex
	 */
	public void setTitleIndex(Integer titleIndex) {
		this.titleIndex = titleIndex;
	}

	/**
	 * @see #dataIndex
	 */
	public Integer getDataIndex() {
		return dataIndex == null ? titleIndex + 1 : dataIndex;
	}

	/**
	 * @see #dataIndex
	 */
	public void setDataIndex(Integer dataIndex) {
		this.dataIndex = dataIndex;
	}

	public List<Integer> getNoteIndex() {
		return noteIndex;
	}

	/**
	 * @see #noteIndex
	 */
	public void setNoteIndex(List<Integer> noteIndex) {
		this.noteIndex = noteIndex;
	}
}
