package com.javapai.framework.common.page;

/**
 * 数据分页对象（组合情况下用）。<br>
 * 
 * <br>
 * 此对象应用于在分页场景下的参数处理(此处组合优于继承)，默认情况下pageSize的页记录数为20条，页索引号为1。<br>
 */
public final class FinalPageArgs implements Paginate {
	/**
	 * 页-记录数.
	 */
	private int pageSize;
	/**
	 * 页-索引号.
	 */
	private int pageIndex;

	@Override
	public int getPageSize() {
		if (pageSize >= DEFAULT_PAGE_SIZE) {
			return pageSize;
		} else {
			return DEFAULT_PAGE_SIZE;
		}
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public int getPageIndex() {
		if (pageIndex >= DEFAULT_PAGE_INDEX) {
			return pageIndex;
		} else {
			return DEFAULT_PAGE_INDEX;
		}
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	@Override
	public int getStartIndex() {
		return DEFAULT_PAGE_INDEX;
	}

}
