package com.javapai.framework.pagination;

/**
 * 分页参数对象.<br>
 */
public final class PageArgs {
	/**
	 * 页记录数.
	 */
	private int pageSize;
	/**
	 * 页索引号.
	 */
	private int pageIndex;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}


}
