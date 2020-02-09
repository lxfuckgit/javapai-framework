package com.javapai.framework.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author liu.xiang
 * 
 * @param <T>
 */
public final class RstPageResult<T> extends BaseResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 当前页记录条数.<br>
	 * 每页记录条数. <br>
	 * 
	 * @see com.javapai.framework.paginationPageArgs.pageSize
	 */
	private int pageSize;
	/**
	 * 当前页索引.<br>
	 * 当前用户正在浏览数据页.<br>
	 * 
	 * @see com.javapai.framework.paginationPageArgs.pageIndex
	 */
	private int pageIndex;
	/**
	 * 总页数.<br>
	 * 在未分页情况下，查询结果集的总页数.<br>
	 */
	private int totalPages;
	/**
	 * 总记录数.<br>
	 * 在未分页情况下，查询结果集的总条数.<br>
	 */
	private int totalRecord;

	/**
	 * 当前页数据集.
	 */
	private List<T> pageList;

	/**
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param pageList
	 */
	public RstPageResult(int pageIndex, int pageSize, List<T> pageList) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.pageList = pageList;
	}

	/**
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param pageList
	 * @param totalRecord
	 */
	public RstPageResult(int pageIndex, int pageSize, List<T> pageList, int totalRecord) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.pageList = pageList;
		this.totalRecord = totalRecord;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
		// return (start / pageSize) + 1;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}

	public int getTotalPages() {
		totalPages = totalRecord / pageSize;// 这样会不会有线程问题
		return totalPages;
	}

	// public void setTotalPages(int totalPages) {
	// this.totalPages = totalPages;
	// }

	public int getTotalRecord() {
		return totalRecord;
	}

	// public void setTotalRecord(int totalRecord) {
	// this.totalRecord = totalRecord;
	// }

}
