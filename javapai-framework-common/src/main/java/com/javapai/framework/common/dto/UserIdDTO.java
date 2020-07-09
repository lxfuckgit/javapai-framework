package com.javapai.framework.common.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 用户标识参数请求数据DTO.<br>
 * 
 * @author liuxiang
 *
 */
@SuppressWarnings("serial")
public final class UserIdDTO extends BaseRstDTO implements Serializable {
	/**
	 * 用户标识.<br>
	 */
	@NotNull
	private Long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
