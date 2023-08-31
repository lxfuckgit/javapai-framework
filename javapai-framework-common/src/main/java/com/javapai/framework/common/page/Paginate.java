package com.javapai.framework.common.page;

/**
 * 分页动作/分页请求.<br>
 * <br>
 * <p>
 * 定义数据分页过程的属性和行为，功能类似于 {@link org.springframework.data.domain.Pageable} 对象<br>
 * 
 * 
 * @author liu.xiang
 *
 */
public interface Paginate {
	/**
	 * 
	 */
	public static final int DEFAULT_PAGE_SIZE = 12;
	/**
	 * 
	 */
	public static final int DEFAULT_PAGE_INDEX = 1;

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
	 * 
	 * @return
	 */
	int getStartIndex();

}
