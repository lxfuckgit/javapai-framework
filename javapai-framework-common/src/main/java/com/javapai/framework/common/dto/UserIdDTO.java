package com.javapai.framework.common.dto;

import java.io.Serializable;

/**
 * 用户标识参数请求数据DTO.<br>
 * 
 * @author liuxiang
 *
 */
@SuppressWarnings("serial")
public final class UserIdDTO extends AppBaseDTO implements Serializable {
	/**
	 * 用户标识.<br>
	 */
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
