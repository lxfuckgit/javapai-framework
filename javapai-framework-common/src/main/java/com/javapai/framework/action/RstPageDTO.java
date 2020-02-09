package com.javapai.framework.action;

import com.javapai.framework.common.dto.BaseRstDTO;
import com.javapai.framework.pagination.Paginate;

/**
 * 基础分页DTO参数说明。<br>
 * 
 * @author pooja
 *
 */
@SuppressWarnings("serial")
public class RstPageDTO extends BaseRstDTO implements Paginate {
	/**
	 * 页-记录数.
	 */
	private int pageSize;
	/**
	 * 页-索引号.
	 */
	private int pageIndex;

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
