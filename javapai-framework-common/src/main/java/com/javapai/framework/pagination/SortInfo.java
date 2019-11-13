package com.javapai.framework.pagination;

/**
 * A data object storaging sortable info.
 */
public final class SortInfo {
	/**
	 * 排序属性名.<br>
	 */
	private String name;
	/**
	 * 排序方式.<br>
	 */
	private boolean ascending;

	/**
	 * @param name
	 *            The name used to comparing.
	 * @param ascending
	 *            Wether ascending or not
	 */
	public SortInfo(String name, boolean ascending) {
		super();
		this.name = name;
		this.ascending = ascending;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}