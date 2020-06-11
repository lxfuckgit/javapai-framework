package com.javapai.framework.enums;

/**
 * 公共状态机-枚举.<br>
 * 
 * <p>
 * 如果有定义不合适请根据场景自行定义，例如OrderStatusEnum.<br>
 * 
 * @author liu.xiang
 *
 */
public enum StatusEnum {
	/**
	 * 初始化
	 */
	INIT("INIT"),
	
	/**
	 * 可用
	 */
	ENABLE("ENABLE"),

	/**
	 * 禁用
	 */
	DISABLE("DISABLE"),

	/**
	 * 创建
	 */
	CREATED("CREATED"),
	/**
	 * 取消
	 */
	CANCAL("CANCAL"),
	/**
	 * 审核
	 */
	CHECK("CHECK"),
	/**
	 * 审批
	 */
	APPROVED("APPROVED"),
	/**
	 * 完成
	 */
	COMPLATE("COMPLATE"),
	/**
	 * 关闭
	 */
	CLOSED("CLOSED");

	private String value = "";

	private StatusEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
