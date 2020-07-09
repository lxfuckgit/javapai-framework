package com.javapai.framework.common.dto;

import com.javapai.framework.pagination.Paginate;

/**
 * 默认分页DTO参数说明。<br>
 * 
 * <br>
 * 此类不可被继承，仅应用于无参且需要分页的情况;
 * 
 * <br>如果需要对请求中参数对象加入分页参数可自行<strong>implements</strong> {@link com.javapai.framework.pagination}接口并增加接口中要求的分页参数.<br>
 * <br>
 * @author pooja
 *
 */
@SuppressWarnings("serial")
public final class RstPageDTO extends BaseRstDTO implements Paginate {
	/**
	 * 页-记录数.
	 */
	private int pageSize;
	/**
	 * 页-索引号.
	 */
	private int pageIndex;
	
	public RstPageDTO() {
		super();
	}

	public RstPageDTO(int pageIndex, Integer pageSize) {
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
		// TODO Auto-generated method stub
		if (pageSize <= DEFAULT_PAGE_SIZE) {
			return DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}

	@Override
	public int getPageIndex() {
		// TODO Auto-generated method stub
		if (pageIndex <= 0) {
			return DEFAULT_PAGE_INDEX;
		}
		return pageIndex;
	}

	@Override
	public int getStartIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

}
