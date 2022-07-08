package com.javapai.framework.action;

import java.util.List;

import com.javapai.framework.enums.Enums;
import com.javapai.framework.enums.ErrorCode;

/**
 * 定义：服务返回报文统一构造类。<br>
 * <br>
 * 作用：构造RstResult和PageResult对象。<br>
 * 
 * 约定：所有请求正确时均返回code＝00000000 message="请求返回成功!"，错误时返回自定义code及message。<br>
 * 
 * @author pooja
 *
 */
public final class ResultBuilder {
	/**
	 * 返回成功.
	 */
	public static final String RESPONSE_OK = "00000000";
	/**
	 * 返回成功.
	 */
	public static final String RESPONSE_MSG = "请求成功!";

	/**
	 * 返回失败-状态码.
	 */
	public static final String RESPONSE_FAIL = "99999999";
	/**
	 * 返回失败-状态描述.
	 */
	public static final String RESPONSE_FAIL_MSG = "请求失败!";

	/**
	 * 构造一个自定义返回响应对象。<br>
	 * 
	 * <br>
	 * <strong>使用建议：</strong> <br>
	 * 通常情况下，我们不建议使用此方法来构造一个返回对象，因为自定义的code->message无法管理，容易造成code lost control。<br>
	 * 所以建议使用{@link ResultBuilder#buildResult(Enums enums)}代替本方法；当然，你也可以使用{@link ResultBuilder#nomarResult}来明确返回一个正常对象或使用{@link ResultBuilder#errorResult}返回一个错误对象。<br>
	 * <br>
	 *
	 * @param code
	 *            自定义编码。<br>
	 * @param message
	 *            自定义说明。<br>
	 * @return RstResult普通报文对象。
	 * 
	 * @deprecated 由{@link ResultBuilder#buildResult(Enums enums)}代替.
	 *
	 */
	public static <T> RstResult<T> buildResult(String code, String message) {
		RstResult<T> entity = new RstResult<>();
		if ("".equals(code) || !"".equals(message)) {
			code = RESPONSE_FAIL;
		}
		entity.setCode(code);

		if (null == message || "".equals(message)) {
			message = RESPONSE_FAIL_MSG;
		}
		entity.setMessage(message);
		return entity;
	}
	
	/**
	 * 构造返回响应对象。<br>
	 * 
	 * @param enums
	 *            成功({@linkplain ResultBuilder#RESPONSE_OK})或具体失败({@linkplain ErrorCode}任何属性)的枚举实例。<br>
	 * @return
	 */
	public static <T> RstResult<T> buildResult(Enums<String, String> enums) {
		RstResult<T> entity = new RstResult<>();
		entity.setCode(enums.getKey());
		entity.setMessage(enums.getValue());
		return entity;
	}

	/**
	 * 构造返回响应对象。<br>
	 * 
	 * @param enums
	 *            自定义枚举对象。<br>
	 * @param message
	 *            定义定消息说明。<br>
	 * @return
	 */
	public static <T> RstResult<T> buildResult(Enums<String, String> enums, String message) {
		RstResult<T> entity = new RstResult<>();
		entity.setCode(enums.getKey());
		if (null != message && message.length() > 0) {
			entity.setMessage(message);
		} else {
			entity.setMessage(enums.getValue());
		}
		return entity;
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
	 * @deprecated 由{@link ResultBuilder#buildResult(String code, String message)}代替.
	 */
	public static <T> RstResult<T> errorResult(String code, String message) {
		return buildResult(code, message);
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
	 * @deprecated 由{@linkplain ResultBuilder#buildResult(Enums<String, String> enums)}代替。
	 */
	public static <T> RstResult<T> failedResult(Enums<String, String> enums) {
		return buildResult(enums);
	}
	
	/**
	 * 
	 * @return
	 */
	public static <T> RstResult<T> normalResult() {
		RstResult<T> entity = new RstResult<>();
		entity.setCode(RESPONSE_OK);
		entity.setMessage(RESPONSE_MSG);
		return entity;
	}
	
	/**
	 * 构造一个正常的响应报文。<br>
	 * 
	 * @param data
	 * @return
	 */
	public static <T> RstResult<T> normalResult(T data) {
		RstResult<T> entity = new RstResult<>();
		entity.setCode(RESPONSE_OK);
		entity.setMessage(RESPONSE_MSG);
		entity.setData(data);
		return entity;
	}

	/**
	 * 构造一个正常的响应报文。<br>
	 *
	 * @param message
	 *            自定义响应说明描述。<br>
	 * @return
	 * 
	 */
	public static <T> RstResult<T> normalResult(String message) {
		RstResult<T> entity = new RstResult<>();
		entity.setCode(RESPONSE_OK);
		entity.setMessage(message);
		return entity;
	}

	/**
	 * 构造一个正常的分页响应报文.<br>
	 *
	 * @param data
	 *            响应数据.<br>
	 * @return
	 */
	public static <T> PageResult<T> buildPageResult(int pageIndex, int pageSize, List<T> pageList, long totalRecord) {
		PageResult<T> result = new PageResult<>(pageIndex, pageSize, pageList, totalRecord);
		result.setCode(RESPONSE_OK);
		result.setMessage(RESPONSE_MSG);
		return result;
	}

}
