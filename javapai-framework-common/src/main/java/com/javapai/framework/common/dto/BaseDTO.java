package com.javapai.framework.common.dto;

import java.io.Serializable;

/**
 * 基础DTO(用以描述入参所来源的设备相关信息)，建议所有DTO对象继承。<br>
 * <br>
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
	 * 手机系统.<br>
	 * ios or android.
	 */
	private String phoneOs;
	// ??哪个平台(ios or android )?哪个系统(android6 android5 ios10 )？
	// private String sysVersion;
	/**
	 * 手机型号.<br>
	 */
	private String phoneModel;

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

	public String getPhoneOs() {
		return phoneOs;
	}

	public void setPhoneOs(String phoneOs) {
		this.phoneOs = phoneOs;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

}
