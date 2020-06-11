package com.javapai.framework.common.dto;

/**
 * 默认请求基础入参对象。<br>
 * 
 * BaseRstDTO主要用以描述当前请求中涉及到和App应用相关的参数信息。
 * 
 * @author pooja
 *
 */
@SuppressWarnings("serial")
public abstract class BaseRstDTO extends BaseDTO {
	/**
	 * 请求应用ID标识.
	 */
	private String appId;

	/**
	 * 请求应用名称(标识).<br>
	 */
	private String appName;

	/**
	 * 请求版本号.<br>
	 * 设计理解：相应版本的app请求相应版本的接口.<br>
	 */
	private String version;
	/**
	 * 请求时间戳.
	 */
	private String timeTx;
	/**
	 * 请求源IP=BaseDTO.deviceIp
	 */
//	private String requestIp;
	
	/**
	 * 请求来源产线(相当于以前字段中的addProduct).<br>
	 * 取值参考{@link ProductLine.java}
	 */
	private String productLine;

	/**
	 * 请求来源(相当于以前字段中的addChannel).<br>
	 * 取值范围参考{@link RstSource.java}.<br>
	 */
	private String rstSource;
	/**
	 * 流量渠道(相当于以前字段中的marketchannel).<br>
	 * <strong>约定：严格按分销平台中定义的<渠道号>进行参数传递。</strong>
	 */
	private String fromChannel;

	// /**
	// * 请求参数.
	// * <br>
	// * 由RequestResponse.requestArgs代替。
	// */
	// private String requestArgs;

	public String getProductLine() {
		if(null == productLine) {
			productLine = "_NA_";
		}
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTimeTx() {
		return timeTx;
	}

	public void setTimeTx(String timeTx) {
		this.timeTx = timeTx;
	}

	/**
	 * 请求来源(相当于以前字段中的addChannel).<br>
	 */
	public String getRstSource() {
		return rstSource;
	}

	/**
	 * 请求来源(相当于以前字段中的addChannel).<br>
	 * 取值参考{@link RstSource.java}
	 */
	public void setRstSource(String rstSource) {
		this.rstSource = rstSource;
	}

	public String getFromChannel() {
		return fromChannel;
	}

	public void setFromChannel(String fromChannel) {
		this.fromChannel = fromChannel;
	}

}
