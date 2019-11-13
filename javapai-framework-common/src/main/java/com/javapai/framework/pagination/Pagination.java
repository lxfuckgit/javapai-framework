package com.javapai.framework.pagination;

import java.io.Serializable;
import java.util.Map;

/**
 * A skeleton implementation is used for describing pagination info.
 * 
 * @author lx
 * 
 */
public interface Pagination extends Cloneable, Serializable {
	/**
	 * 初始分页大小.<br>
	 */
	public static final int PAGESIZE = 30;
	/**
	 * 
	 */
	public static final String PARAM_TABLE_ID = "tableId";

	/**
	 * 取得第一行.
	 * 
	 * @return
	 */
	public int getFirstRowIndex();

	/**
	 * Get the row count per page.
	 * 
	 * @return
	 */
	public int getPagesize();

	/**
	 * 设置每页记录数
	 * 
	 * @param pagesize
	 */
	public void setPagesize(int pagesize);

	/**
	 * Get total count of the rows.
	 * 
	 * @return
	 */
	public int getTotalRows();

	/**
	 * 设置总条数.
	 * 
	 * @param totalRows
	 */
	public void setTotalRows(int totalRows);

	/**
	 * Get the index of current page
	 * 
	 * @return
	 */
	public int getCurrentPage();

	public void setCurrentPage(int currentPage);

	/**
	 * Get the total count of pages.<br>
	 * 每页记录数。
	 * 
	 * @return
	 */
	public int getPageCount();

	/**
	 * Get the SortInfo associating with this Pagination.
	 * 
	 * @return
	 */
	public SortInfo getSortInfo();//SortInfo可以作成内部类吗？

	/**
	 * 
	 * @param sortInfo
	 */
	public void setSortInfo(SortInfo sortInfo);

	/**
	 * Get extra parameter with paramName.
	 * 
	 * @param paramName
	 *            The name of parameter.
	 * @return The value of parameter.
	 */
	public Object getParam(String paramName);

	public void setParam(String paramName, Object paramValue);

	public Map<String, Object> getParamMap();

	/**
	 * Check whether the pagination function is disabled or not.
	 * 
	 * @return
	 */
	public boolean isDisabled();

	public void setDisabled(boolean disabled);

	public Object getCustomRealm();

	public void setCustomRealm(Object customRealm);
}