package com.javapai.framework.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 二维表(行&列)形式数据解析报告。<br>
 * 
 * @author lx
 * @author pooja
 *
 */
public final class TableFormat extends XFormat {

	/**
	 * 二维表-标题栏。 <br>
	 * 注：为保证title和conent数据的对应，对每条记录的索引值(map.key)都进行了一一的有序对应。
	 */
	private Map<Integer, String> tableTitle = new LinkedHashMap<Integer, String>();

	/**
	 * 二维表-内容行。 <br>
	 */
	private List<Map<Integer, Object>> tableConent = new ArrayList<Map<Integer, Object>>();

	/**
	 * 二维表-备注行.<br>
	 * 注：为区别内容行，可将某些特殊行(备注之类)单独存放。<br>
	 */
	private List<Map<Integer, Object>> tableRemark = new ArrayList<>();

	/**
	 * excel错误消息。 记录excel解析过程中，行解析消息。
	 */
	private List<Map<Integer, Object>> erroDataList = new ArrayList<Map<Integer, Object>>();

	/**
	 * 读取表-标题.<br>
	 * 
	 * @return
	 */
	public Map<Integer, String> getTableTitle() {
		return tableTitle;
	}

	public void setTableTitle(Map<Integer, String> tableTitle) {
		this.tableTitle = tableTitle;
	}

	/**
	 * 读取表-内容.<br>
	 * 
	 * @return
	 */
	public List<Map<Integer, Object>> getTableConent() {
		return tableConent;
	}

	/* 不希望表体被整体set修改 */
	// public void setTableConent(List<Map<Integer, Object>> tableConent) {
	// this.tableConent = tableConent;
	// }

	public List<Map<Integer, Object>> getTableRemark() {
		return tableRemark;
	}

	// public void setTableRemark(List<Map<Integer, String>> noteList) {
	// this.tableRemark = tableRemark;
	// }

	public List<Map<Integer, Object>> getErroDataList() {
		return erroDataList;
	}

	public void setErroDataList(List<Map<Integer, Object>> erroDataList) {
		this.erroDataList = erroDataList;
	}

	/**
	 * 
	 * @param title
	 */
	public void addTitle(String title) {
		// tableTitle索引从0开始，所以tableTitle.size()就是空索引位。
		tableTitle.put(tableTitle.size(), title);
	}

	/**
	 * 标记行的列标题。<br>
	 * 
	 * @param index
	 *            列索引.<br>
	 * @param title
	 *            列标题.<br>
	 */
	public void addTitle(Integer index, String title) {
		tableTitle.put(index, title);
	}

	/**
	 * @see TableFormat#addContent(Map, int);<br>
	 * @param content
	 */
	public void addContent(Map<Integer, Object> content) {
		tableConent.add(content);
	}

	/**
	 * 向容器增加一行内容。<br>
	 * 
	 * @param content
	 * @param index
	 */
	public void addContent(Map<Integer, Object> content, int index) {
		tableConent.add(index, content);
	}

	/**
	 * 向容器增加一行备注。<br>
	 * 
	 * @param content
	 */
	public void addRemark(Map<Integer, Object> remark) {
		tableRemark.add(remark);
	}

	/**
	 * 导入数据正确性.
	 *
	 * @return
	 */
	public boolean hasErro() {
		return erroDataList.size() > 0 ? true : false;
	}
}
