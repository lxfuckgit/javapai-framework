package com.javapai.framework.action;

/**
 * 请求结果统一报文头。<br>
 * 
 * @author pooja
 *
 */
public abstract class BaseResult {
	/**
	 * 响应-状态编码.<br>
	 * 状态码取值参考：{@link com.javapai.framework.enums.ErrorCode}.<br>
	 */
	private String code;
	/**
	 * 响应-状态消息.<br>
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
