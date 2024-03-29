package com.javapai.framework.common.page;

/**
 * 数据分页对象（继承情况下用）。<br>
 * 
 * @author pooja
 *
 */
public abstract class BasePageArgs implements Paginate {
	/**
	 * 页-记录数.
	 */
	private int pageSize;
	/**
	 * 页-索引号.
	 */
	private int pageIndex;

	public BasePageArgs() {
		super();
	}

	public BasePageArgs(int pageIndex, Integer pageSize) {
		if (pageIndex < 0) {
			pageIndex = DEFAULT_PAGE_INDEX;// throw exception?
		}
		if (pageSize < 1) {
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
		if (pageSize <= DEFAULT_PAGE_SIZE) {
			return DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}

	@Override
	public int getPageIndex() {
		if (pageIndex <= 0) {
			return DEFAULT_PAGE_INDEX;
		}
		return pageIndex;
	}

	@Override
	public int getStartIndex() {
		return 0;
	}

}
