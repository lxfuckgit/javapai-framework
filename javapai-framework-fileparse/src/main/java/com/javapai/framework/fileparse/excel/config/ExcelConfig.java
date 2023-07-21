package com.javapai.framework.fileparse.excel.config;

import java.util.List;
import java.util.Map;

/**
 * Excel解析配置.<br>
 * 
 * 1:指定读取指定sheet,支持多sheet读取。<br>
 * 2：指定动态读取表行或列. <br>
 * 3：可配置指定表头内容.<br>
 * 
 * @author pooja
 * 
 * @deprecated 不准备对整个excel提供解决配置，后续会有SheetConfig、RowConfig。
 *
 */
public abstract class ExcelConfig {
	/**
	 * 读取指定excel工作表sheet编号。<br>
	 * 提示：默认为-1，代表读取所有sheet表单。<br>
	 */
	private int sheetSeq = -1;
	
	/**
	 * 
	 */
	private ReadSheetConfig sheetConfig;
	
	/**
	 * excel读取配置项(验证、转换等)
	 */
	private Map<String, Object> config;

	/**
	 * excel表头键值对(key=列索引,value=列标题). <br>
	 * 
	 * @deprecated excel标题没必要当成一个配置项。<br>
	 *             理由：找不到理由为什么要这样传进来，再传出来，除非有二次加工需求。
	 */
	private Map<Integer, String> titleMap;
	
	public int getSheetSeq() {
		return sheetSeq == 0 ? -1 : sheetSeq;
	}

	public void setSheetSeq(int sheetSeq) {
		this.sheetSeq = sheetSeq;
	}

	public void setConfigs(Map<String, Object> config) {
		this.config = config;
	}

	public Map<String, Object> getConfig() {
		return config;
	}

	public void setTitleMap(Map<Integer, String> title) {
		this.titleMap = title;
	}

	public Map<Integer, String> getTitleMap() {
		return titleMap;
	}

	/**
	 * 解析excel，最好的实现是List<Map<String,Object>>
	 * list,这里的map是每一条数据集，有时候我们会以第一行表头某列作为某数据。<br>
	 * 但有时候，我想如果用A,B,C这样的列标，是不是会更好，与表头名也无关，在修改表头名称的情况也，也不会有影响。
	 * 结合titleMap属性和config属性的配置，是可以实现.
	 * 
	 * @param sheetIndex
	 * @param rowIndex
	 * @param rowDataList
	 * @return
	 */
	// protected abstract ParseReport readRows(int sheetIndex, int rowIndex,
	// List<String> rowDataList);

}
