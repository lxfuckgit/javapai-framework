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
//	private Map<Integer, Object> tableTitle = new HashMap<Integer, Object>();
	
	/**
	 * 二维表-内容行。 <br>
	 */
	private List<Map<Integer, Object>> tableConent = new ArrayList<Map<Integer, Object>>();
	
	/**
	 * 特殊备注行.<br>
	 * 为区别数据行，将某些备注说明单独存放。<br>
	 */
	private List<String> noteList = new ArrayList<>();
	
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
	
	/*不希望表头或表体被整体set修改*/
//	public void setTableConent(List<Map<Integer, Object>> tableConent) {
//	this.tableConent = tableConent;
//}
	
	public List<String> getNoteList() {
		return noteList;
	}

//	public void setNoteList(List<String> noteList) {
//		this.noteList = noteList;
//	}
	
	public List<Map<Integer, Object>> getErroDataList() {
		return erroDataList;
	}

	public void setErroDataList(List<Map<Integer, Object>> erroDataList) {
		this.erroDataList = erroDataList;
	}

//	public void addTitle(String title) {
//		tableTitle.put(title);
//	}

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

	public void addContent(Map<Integer, Object> content) {
		tableConent.add(content);
	}

	public void addContent(Map<Integer, Object> content, int index) {
		tableConent.add(index, content);
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
