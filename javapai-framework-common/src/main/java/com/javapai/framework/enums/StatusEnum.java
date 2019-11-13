package com.javapai.framework.enums;

/**
 * 公共枚举.<br>
 * 
 * <p>
 * 状态机.<br>
 * 
 * @author liu.xiang
 *
 */
public enum StatusEnum {
//	/**
//	 * 可用
//	 */
//	STATUS_ENABLE("STATUS_ENABLE"),//特殊场景下的状态，交由特定class，例如OrderStatusEnum.
//
//	/**
//	 * 禁用
//	 */
//	STATUS_DISABLE("STATUS_DISABLE"),
	
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
