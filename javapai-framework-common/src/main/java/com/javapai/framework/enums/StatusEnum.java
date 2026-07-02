package com.javapai.framework.enums;

/**
 * 公共状态机-枚举.<br>
 * <p>
 * <strong>提示：</strong> 系统内置了部分常见的状态码可满足日常使用，如果有特殊情况情况，使用者请根据场景自行定义；例如：<br>
 * 
 * <li>UserStatusEnum
 * <li>OrderStatusEnum
 * <li>......<br>
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
	 * 检查
	 */
	CHECK("CHECK"),
	/**
	 * 复核
	 */
	REVIEW("REVIEW"),
	/**
	 * 审批/审核
	 */
	APPROVED("APPROVED"),
	/**
	 * 驳回/拒绝
	 */
	REJIECT("REJIECT"),
	/**
	 * 支付
	 */
	PAYMENT("PAYMENT"),
	/**
	 * 退款/退还
	 */
	REFUND("REFUND"),
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
