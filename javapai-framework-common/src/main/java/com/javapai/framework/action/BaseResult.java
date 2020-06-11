package com.javapai.framework.action;

public abstract class BaseResult {
	/**
	 * 响应状态码.<br>
	 */
	private String code;
	/**
	 * 状态码消息。<br>
	 */
	private String message;

	public BaseResult() {
		super();
	}

	public BaseResult(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
