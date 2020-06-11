package com.javapai.framework.common.dto;

import java.io.Serializable;

/**
 * 基础DTO，建议所有DTO对象继承。<br>
 * 
 * <br>
 * BaseDTO参数用以描述请求源(调用方)的设备相关信息。 <br>
 * 
 * @author liuxiang
 *
 */
@SuppressWarnings("serial")
public abstract class BaseDTO implements Serializable {
	/**
	 * 请求设备号(ID).<br>
	 */
	private String deviceId;
	/**
	 * 请求设备IP.<br>
	 */
	private String deviceIp;
	/**
	 * 请求设备MAC.<br>
	 */
	private String deviceMac;
	/**
	 * 请求设备idfa(苹果系统专用).<br>
	 */
	private String deviceIdfa;
	/**
	 * 请求设备idfv(苹果系统专用).<br>
	 */
	private String deviceIdfv;
	/**
	 * 设备运行系统.<br>
	 * 哪个平台:ios,android,heimei.
	 */
	private String deviceOs;// phoneOs
	/**
	 * 设备型号 及 设备版本.<br>
	 * 哪个系统型号:android6 android5 ios7p
	 */
	private String deviceModel;// phoneModel

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public String getDeviceIdfa() {
		return deviceIdfa;
	}

	public void setDeviceIdfa(String deviceIdfa) {
		this.deviceIdfa = deviceIdfa;
	}

	public String getDeviceIdfv() {
		return deviceIdfv;
	}

	public void setDeviceIdfv(String deviceIdfv) {
		this.deviceIdfv = deviceIdfv;
	}

	public String getDeviceOs() {
		return deviceOs;
	}

	public void setDeviceOs(String deviceOs) {
		this.deviceOs = deviceOs;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

}
