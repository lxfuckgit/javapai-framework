package com.javapai.framework.pagination;

/**
 * 分页参数对象.<br>
 * 
 * <br>
 * 此对象应用于在分页场景下的参数处理(此处组合优于继承)，默认情况下pageSize的页记录数为20条，页索引号为1。<br>
 */
public final class PageArgs {
	/**
	 * 页-记录数.
	 */
	private int pageSize;
	/**
	 * 页-索引号.
	 */
	private int pageIndex;

	public int getPageSize() {
		if (pageSize <= 20) {
			return 20;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		if (pageIndex <= 0) {
			return 1;
		}
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}


}
