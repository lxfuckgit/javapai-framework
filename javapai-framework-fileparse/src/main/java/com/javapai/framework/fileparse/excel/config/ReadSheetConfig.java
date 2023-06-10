package com.javapai.framework.fileparse.excel.config;

import java.util.Arrays;
import java.util.List;

import com.javapai.framework.config.XConfig;

/**
 * Sheet表单操作策略配置项.<br>
 * <br>
 * <strong>提示：</strong>
 * 本配置下的描述的行号值有区别于索引值，一般索引值第1行为0，而为了更好的方便使用的，我们使用Sheet表单显示的行号值作的参数源。<br>
 * 
 * @author pooja
 *
 */
public final class ReadSheetConfig extends XConfig {
	/**
	 * 
	 */
	private static final int DEFAULT_TITLE_INDEX = 1;
	
//	// 未放开原因：每个ReadSheetConfig配置项不应绑定在一个sheet上，ReadSheetConfig是应用于当前处理对象(可能是个sheet表单也可能是整个数据文件)。
//	/**
//	 * 指定当前Sheet表单的索引号。<br>
//	 */
//	private int sheetIndex;

	/**
	 * 指定当前sheet表单的标题行的行号。<br>
	 * <br>
	 * 提示：默认表单的第1行为标题行，默认行号值为1。<br>
	 */
	private Integer titleIndex;

	/**
	 * 指定当前sheet表单的数据行的启始行号。<br>
	 * <br>
	 * 提示：默认表单的第2行为数据行，默认索引值为2(即标题行的下一行是数据行)。<br>
	 */
	private Integer dataIndex;

	/**
	 * 当前sheet表单的备注行的行号(支持多行，格式：i,j,k...)。<br>
	 * 提示：用于特定情况下提取备注数据，默认无特殊备注行。<br>
	 */
	private List<Integer> noteIndex;

	/**
	 * 使用场景：用于将已存在列之间[内容合并]的场景。
	 * 
	 * 争论点：之前有考虑过不在数据解析期间来进行数据后续操作，理由是功能独立(数据解析只针对于excel现有格式，数据组装可根据业务单自行解决)。
	 */
	// public List<MergInfo> mergerIndex;
	// //谁和谁合，怎么合（合并后的单元格内容怎么拼接，表头叫什么）//poi只对excel格式&数据进行解析，对于后续数据(如单元格内容)的合并，建立单独处理.

	public ReadSheetConfig() {
		this.titleIndex = DEFAULT_TITLE_INDEX;
	}

	/**
	 * 
	 * @param titleIndex
	 *            {@linkplain ReadSheetConfig#titleIndex}
	 */
	public ReadSheetConfig(Integer titleIndex) {
		this.titleIndex = titleIndex;
	}

	/**
	 * 
	 * @param titleIndex
	 *            {@linkplain ReadSheetConfig#titleIndex}
	 * @param dataIndex
	 *            {@linkplain ReadSheetConfig#dataIndex}
	 */
	public ReadSheetConfig(Integer titleIndex, Integer dataIndex) {
		this.titleIndex = titleIndex;
		this.dataIndex = dataIndex;
	}

	/**
	 * 读取标题行索引值(索引号)。<br>
	 * 
	 * @see #titleIndex
	 */
	public Integer getTitleIndex() {
		return titleIndex == null ? DEFAULT_TITLE_INDEX : titleIndex;
	}

	/**
	 * @see #titleIndex
	 */
	public void setTitleIndex(Integer titleIndex) {
		this.titleIndex = titleIndex;
	}

	/**
	 * 读取数据行索引值(索引号)。<br>
	 * 
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

	/**
	 * @see #noteIndex
	 */
	public void setNoteIndex(Integer[] noteIndex) {
		this.noteIndex = Arrays.asList(noteIndex);
	}
	
}
