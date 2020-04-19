package com.javapai.framework.protocal;

/**
 * 接口：服务结点.<br>
 * 用以描述各通信服务器的基本功能。<br>
 * 
 * @author pooja
 *
 */
public interface ServerNode {
	/**
	 * 服务初始化
	 */
	public void init();

	/**
	 * 服务启动
	 */
	public void startUp();

	/**
	 * 服务关闭
	 */
	public void shutDown();
}
