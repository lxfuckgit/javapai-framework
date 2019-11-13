package com.javapai.framework.common.dto;

public class RstPageDTO extends BaseRstDTO {
	// @ApiModelProperty(value = "查询页数",dataType = "Integer")
	private Integer pageNumber;

	// @ApiModelProperty(value = "每页数量",dataType = "Integer")
	private Integer pageSize;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
