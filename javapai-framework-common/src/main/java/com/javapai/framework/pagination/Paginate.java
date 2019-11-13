package com.javapai.framework.pagination;

/**
 * 分页动作/分页请求.<br>
 * <br>
 * <p>
 * 用来描述一次分页过程中参数 .<br>
 * 
 * @see org.springframework.data.domain.Pageable
 * 
 * @author liu.xiang
 *
 */
public interface Paginate {
	/**
	 * 返回当前页记录条数.<br>
	 * 
	 * @return
	 */
	int getPageSize();

	/**
	 * 返回当前页索引.<br>
	 */
	int getPageIndex();
	
	/**
	 * 返回数据起始行索引.<br>
	 * @return
	 */
	int getStartIndex();

}
