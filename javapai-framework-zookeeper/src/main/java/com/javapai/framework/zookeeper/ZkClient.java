package com.javapai.framework.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import com.javapai.framework.registry.RegistryClient;

/**
 * zookeeper registry implements.<br>
 * 
 * <br>
 * 需要自行导入依赖包：
 * <br>
 * <pre>
 * 		<dependency>
 *			<groupId>org.apache.curator</groupId>
 *			<artifactId>curator-framework</artifactId>
 *		</dependency>
 * 		<dependency>
 * 			<groupId>org.apache.curator</groupId>
 * 			<artifactId>curator-client</artifactId>
 * 		</dependency>
 * </pre>
 * <br>
 * 
 * @author lx
 *
 */
public class ZkClient implements RegistryClient {
	private final int SESSION_TIMEOUT = 30 * 1000;
	private final int CONNECTION_TIMEOUT = 3 * 1000;
	
	private String zkString = "101.37.118.103:20181,101.37.20.213:20181,101.37.205.187:20181";

	private RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//	CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.128.129:2181", 5000, 5000, retryPolicy);
	CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zkString)
			.connectionTimeoutMs(CONNECTION_TIMEOUT).sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(retryPolicy)
			// 命名空间 .namespace("super")
			.build();
}
