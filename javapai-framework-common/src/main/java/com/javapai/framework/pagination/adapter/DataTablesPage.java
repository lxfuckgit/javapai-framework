//package com.javapai.framework.pagination.adapter;
//
//import java.io.Serializable;
//
//import com.javapai.framework.pagination.Paginate;
//
///**
// * 
// * @author liu.xiang
// *
// */
//@SuppressWarnings("serial")
//public class DataTablesPage implements Paginate, Serializable {
//	/** 记录起始位置 **/
//	private int startIndex;
//	
//	/** 当前页数 **/
//	private int pageIndex;
//
//	/** 每页记录数 **/
//	private int pageSize;
//	
//	/**
//	 * 
//	 */
//	public DataTablesPage(){}
//
//	/**
//	 * 
//	 * @param pageIndex
//	 *            当前页页码.
//	 * @param pageSize
//	 *            每页数据记录数.
//	 */
//	public DataTablesPage(int pageIndex, int pageSize) {
//		if (pageIndex < 1) {
//			throw new IllegalArgumentException(">>>Page index must not be less than zero!");
//		}
//		if (pageSize < 1) {
//			throw new IllegalArgumentException(">>>Page size must not be less than one!");
//		}
//
//		this.pageIndex = pageIndex;
//		this.pageSize = pageSize;
//	}
//	
//	public int getStartIndex() {
//		if (pageIndex < 0 || pageSize < 1) {
//			throw new IllegalArgumentException(">>>pageSize/pageIndex must not be less than one!");
//		} else {
//			startIndex = (pageIndex - 1) * pageSize;
//		}
//		return startIndex;
//	}
//
//	public void setStartIndex(int startIndex) {
//		this.startIndex = startIndex;
//	}
//
//	@Override
//	public int getPageIndex() {
//		return pageIndex;
//	}
//
//	public void setPageIndex(int pageIndex) {
//		this.pageIndex = pageIndex;
//	}
//
//	@Override
//	public int getPageSize() {
//		return pageSize;
//	}
//
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//
//}
