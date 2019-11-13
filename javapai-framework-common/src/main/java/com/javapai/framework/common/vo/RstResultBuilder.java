package com.javapai.framework.common.vo;

import com.javapai.framework.common.enums.Enums;

/**
 *
 * @author liuxiang
 *
 */
public final class RstResultBuilder {
	/**
	 * 返回成功.
	 */
	public static final String RESPONSE_OK = "00000000";
	/**
	 * 返回成功.
	 */
	public static final String RESPONSE_MSG = "请求成功!";
	
//	/**
//	 * 构建一个错误报文体.<br>
//	 * 错误码统一定义为：FFFFFFFF<br>
//	 * 
//	 * @param message
//	 * @return
//	 */
//	public static <T> RstResult<T> buildErrorResponse(String message) {
//		RstResult<T> entity = new RstResult<>();
//		entity.setCode(ErrorCode.ERROR_EXCEPTION.getKey());
//		entity.setMessage(message);
//		return entity;
//	}

	/**
	 * 构造一个失败响应.<br>
	 * 用于构造一个比较用见或是常用的失败响应。<br>
	 *
	 * @param enums
	 *            常见错误枚举类的一个实例.<br>
	 * @return
	 */
	public static <T> RstResult<T> buildErrorResponse(Enums<String, String> enums) {
		//ErrorCode枚举类不能被继承从而实现多态传参数，导致ErrorCode不能由业务方定义，只能写在框架里怎么办？->每个枚举类都实现一个接口，over!
		RstResult<T> result = new RstResult<>();
		result.setCode(String.valueOf(enums.getKey()));
		result.setMessage(enums.getValue());
		return result;
	}
	
	/**
	 * 构造一个失败响应.<br>
	 *
	 * @param code
	 *            失败错误编码.<br>
	 * @param message
	 *            失败错误说明.<br>
	 * @return
	 * 
	 * @deprecated 由{@link RstResultBuilder#buildErrorResponse(ErrorCode enums)}代替.
	 *
	 */
	public static <T> RstResult<T> buildErrorResponse(String code, String message) {
		RstResult<T> entity = new RstResult<>();
		if ("".equals(code) || !"".equals(message)) {
			code = "42000001";
		}
		entity.setCode(code);
		entity.setMessage(message);
		return entity;
	}

	/**
	 * 构造一个正常响应.<br>
	 *
	 * 响应数据.<br>
	 * 
	 * @deprecated {@linkplain 已由RstResultBuilder.buildResult()方法代替.}
	 * 
	 * @return
	 */
	@Deprecated
	public static <T> RstResult<T> buildNormalResult() {
		return buildResult();
	}
	
	/**
	 * 构造一个正常响应.<br>
	 *
	 * 响应数据.<br>
	 * 
	 * @return
	 */
	public static <T> RstResult<T> buildResult() {
		return new RstResult<T>(RESPONSE_OK, RESPONSE_MSG);
	}
	
	/**
	 * 构造一个正常响应.<br>
	 *
	 * @param data
	 *            响应数据.<br>
	 * @return
	 */
	public static <T> RstResult<T> buildResult(T data) {
		RstResult<T> entity = buildNormalResult();
		entity.setData(data);
		return entity;
	}
	
}
