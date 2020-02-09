package com.javapai.framework.action;

import java.io.Serializable;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.javapai.framework.common.dto.BaseRstDTO;

/**
 * 字符类型的userId标识入参。<br>
 * 
 * @see {@link RstUserIdDTO.java}
 * @author pooja
 *
 */
@SuppressWarnings("serial")
public final class RstUserId2DTO extends BaseRstDTO implements Serializable {
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
