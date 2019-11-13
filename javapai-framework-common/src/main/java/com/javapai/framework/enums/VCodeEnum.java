package com.javapai.framework.enums;

/**
 * 验证码类型枚举.<br>
 * <p>
 * 
 * @author liu.xiang
 *
 */
public enum VCodeEnum {
	/**
	 * 注册类型
	 */
	CODE_REGISTER("CODE_REGISTER"),

	/**
	 * 找回密码类型
	 */
	CODE_RETRIEVE("CODE_RETRIEVE"),
	
	/**
	 * 重设密码类型
	 */
	CODE_RESET("CODE_RESET"),
	
	/**
	 * 支付验证码类型
	 */
	CODE_PYAMENT("CODE_PYAMENT"),

	/**
	 * 待定/其它
	 */
	CODE_OTHER("CODE_OTHER");

	private String value = "";

	private VCodeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
