package com.javapai.framework.action;

import java.io.Serializable;
import java.util.List;

/**
 * 分页请求下系统返回的报文结果对象。<br>
 * 
 * @author liu.xiang
 * 
 * @param <T>
 */
public final class PageResult<T> extends BaseResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 当前页记录条数(current page size).<br>
	 * 每页记录条数. <br>
	 * 
	 * @see com.javapai.framework.paginationPageArgs.pageSize
	 */
	private int pageSize;

	/**
	 * 当前页索引(current page index).<br>
	 * 当前页的索引页号.<br>
	 * 
	 * @see com.javapai.framework.paginationPageArgs.pageIndex
	 */
	private int pageIndex;

	/**
	 * 总页数.<br>
	 * 查询结果集的总页数=总记录数/每页记录数.<br>
	 * 
	 * @deprecated 理由：记录总页数被假定为不可人为通过get/set修改，故被列为无意义属性。<br>
	 *             如需知道总页数，请参见方法：{@link PageResult#totalPages()};<br>
	 */
	private long totalPages;

	/**
	 * 总记录数.<br>
	 * 在未分页情况下，查询结果集的总条数.<br>
	 * 
	 * @deprecated 参见{@link PageResult#totalRecords()}
	 */
	private long totalRecord;

	/**
	 * 当前页数据集.
	 */
	private List<T> data;// 和RstResult保持一样的属性头信息.
//	private List<T> pageList;

	/**
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param pageList
	 */
	public PageResult(int pageIndex, int pageSize, List<T> data) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.data = data;
	}

	/**
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param pageList
	 * @param totalRecord
	 */
	public PageResult(int pageIndex, int pageSize, List<T> data, long totalRecord) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.data = data;
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

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getTotalPages() {
		totalPages = totalRecord / pageSize;// 这样会不会有线程问题
		return totalPages;
	}

	// public void setTotalPages(int totalPages) {
	// this.totalPages = totalPages;
	// }

	public long getTotalRecord() {
		return totalRecord;
	}

	// public void setTotalRecord(int totalRecord) {
	// this.totalRecord = totalRecord;
	// }

}
