package com.javapai.framework.common.dto;

import java.io.Serializable;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 字符类型的userId标识入参。<br>
 * 
 * @see {@link UserIdDTO.java}
 * @author pooja
 *
 */
@SuppressWarnings("serial")
public final class UserId2DTO extends BaseRstDTO implements Serializable {
	/**
	 * 用户标识.<br>
	 */
	@NotNull
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
