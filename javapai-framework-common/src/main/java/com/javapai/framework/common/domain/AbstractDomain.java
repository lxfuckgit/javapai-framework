package com.javapai.framework.common.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractDomain extends TopBaseDomain {
	/**
	 * {@link com.javapai.common.dto.BaseDTO#getDeviceId()}<br>
	 */
	@Column(name = "deviceId", length = 30)
	private String deviceId;
	
	/**
	 * {@link com.javapai.common.dto.BaseDTO#getDeviceIp()}<br>
	 */
	@Column(name = "deviceIp", length = 30)
	private String deviceIp;
	
	/**
	 * {@link com.javapai.common.dto.BaseDTO#getDeviceMac()}<br>
	 */
	@Column(name = "deviceMac", length = 30)
	private String deviceMac;
	
	/**
	 * {@link com.javapai.common.dto.BaseDTO#getDeviceIdfa()}<br>
	 */
	@Column(name = "deviceIdfa", length = 30)
	private String deviceIdfa;
	
	/**
	 * {@link com.javapai.common.dto.BaseDTO#getDeviceIdfv()}<br>
	 */
	@Column(name = "deviceIdfv", length = 30)
	private String deviceIdfv;
	
	/**
	 * @see com.javapai.common.dto.BaseRstDTO#getAppId()<br>
	 */
	@Column(name = "appId", length = 16)
	private String appId;
	
	/**
	 * {@link com.javapai.common.dto.BaseRstDTO#getAppName()}<br>
	 */
//	@Column(name = "appName", length = 30)
//	private String appName;

	/**
	 * {@link com.javapai.common.dto.BaseRstDTO#getVersion()}<br>
	 */
	@Column(name = "version", length = 12)
	private String version;

	// /**
	// * 请求来源(相当于以前字段中的addChannel).<br>
	// * 取值范围参考{@link RstSource.java}.<br>
	// */
	// private String rstSource;
	/**
	 * {@link com.javapai.common.dto.BaseRstDTO#getFromChannel()}
	 */
	@Column(name = "fromChannel", length = 30)
	private String fromChannel;
	
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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

//	public String getAppName() {
//		return appName;
//	}
//
//	public void setAppName(String appName) {
//		this.appName = appName;
//	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFromChannel() {
		return fromChannel;
	}

	public void setFromChannel(String fromChannel) {
		this.fromChannel = fromChannel;
	}
}
