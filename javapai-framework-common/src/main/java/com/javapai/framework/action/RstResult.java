package com.javapai.framework.action;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * 常规请求下系统返回的报文结果对象。<br>
 * 
 * @author liu.xiang
 *
 * @param <T>
 *            报文中具体数据报文对象。<br>
 */
@SuppressWarnings("serial")
public final class RstResult<T> extends BaseResult implements Serializable {
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
		super(code, message);
	}
	
	public RstResult(String code, T data) {
		super.setCode(code);
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * 
	 * @param supplier
	 * @return
	 */
	public static <T> T call(Supplier<RstResult<T>> reslut) {
		RstResult<T> result = reslut.get();
		if (null == result) {
			throw new RuntimeException("远程调用未获得结果。原因未知！！");
		}

		switch (result.getCode()) {
		case "00000000":
			return result.getData();
			
		default:
			throw new RuntimeException();
		}
	}

}
