package com.javapai.framework.common.page;

/**
 * 数据分页对象（继承情况下用）。<br>
 * 
 * @author pooja
 *
 */
public abstract class BasePageArgs implements Paginate {
	/**
	 * 页-记录数（默认值：12）。
	 */
	private int pageSize;
	/**
	 * 页-索引号（默认值：1）.
	 */
	private int pageIndex;

	public BasePageArgs() {
		super();
	}

	public BasePageArgs(int pageIndex, Integer pageSize) {
		if (pageIndex <= 0) {
			pageIndex = DEFAULT_PAGE_INDEX;
		}
		if (pageSize <= DEFAULT_PAGE_SIZE) {
			pageSize = DEFAULT_PAGE_SIZE;
		}

		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public int getPageSize() {
		if (pageSize >= DEFAULT_PAGE_SIZE) {
			return pageSize;
		} else {
			return DEFAULT_PAGE_SIZE;
		}
	}

	@Override
	public int getPageIndex() {
		if (pageIndex >= DEFAULT_PAGE_INDEX) {
			return pageIndex;
		} else {
			return DEFAULT_PAGE_INDEX;
		}
	}

	@Override
	public int getStartIndex() {
		return DEFAULT_PAGE_INDEX;
	}

}
