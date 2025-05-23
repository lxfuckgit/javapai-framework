package com.javapai.framework.common.dto;

import com.javapai.framework.common.page.BasePageArgs;
import com.javapai.framework.common.page.Paginate;

/**
 * 默认分页对象。<br>
 * 
 * <br>
 * <strong>应用场景：</strong>此对象应用于一些默认分页查询场景（即：不需要其它业务参数时，仅需要简单分页查询的情况）。<br>
 * <strong>更多提示：</strong>因为此类被定义为不可被继承，所以当您需要更多业务参数条件且需要分页参数条件查询时，请自行定义查询DTO然后继承{@link BasePageArgs}对象。
 * <br>
 * 例如：<br>
 * public class ListUserDTO extends BasePageArgs {<br>
 * ......<br>
 * ......<br>
 * }<br>
 * 
 * <br>
 * 
 * @author pooja
 *
 */
@SuppressWarnings("serial")
public final class DefaultPageDTO extends BaseRstDTO implements Paginate {
	/**
	 * 页-记录数.
	 */
	private int pageSize;
	/**
	 * 页-索引号.
	 */
	private int pageIndex;

	public DefaultPageDTO() {
		super();
	}

	public DefaultPageDTO(int pageIndex, Integer pageSize) {
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
