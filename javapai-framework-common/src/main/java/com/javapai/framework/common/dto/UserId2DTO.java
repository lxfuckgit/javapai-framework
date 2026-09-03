package com.javapai.framework.common.dto;

import java.io.Serializable;

/**
 * 字符类型的userId标识入参。<br>
 * 
 * @see {@link UserIdDTO.java}
 * @author pooja
 *
 */
@SuppressWarnings("serial")
public final class UserId2DTO extends AppBaseDTO implements Serializable {
	/**
	 * 用户标识.<br>
	 */
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
