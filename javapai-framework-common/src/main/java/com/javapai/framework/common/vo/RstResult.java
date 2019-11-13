package com.javapai.framework.common.vo;

import java.io.Serializable;

/**
 * 请求结果对象.<br>
 * 
 * @author liu.xiang
 *
 * @param <T> 报文中具体数据包对象。<br>
 */
@SuppressWarnings("serial")
public final class RstResult<T> implements Serializable {
	/**
	 * 响应状态码.<br>
	 */
	private String code;
	/**
	 * 状态码消息。<br>
	 */
	private String message;
	/**
	 * 返回数据包对象。<br>
	 */
	private T data;

	/**
	 * 无参构造函数
	 *
	 */
	public RstResult() {
	}

	/**
	 * 构造一个请求返回对象.<br>
	 * 
	 * @param code
	 * @param message
	 */
	public RstResult(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public RstResult(String code, T data) {
		this.code = code;
		this.data = data;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
