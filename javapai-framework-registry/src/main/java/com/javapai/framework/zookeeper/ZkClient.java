package com.javapai.framework.zookeeper;

import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

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
	
	private String zkString = "101.37.118.x:2181,101.37.20.y:2181";

	private RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//	CuratorFramework client = CuratorFrameworkFactory.newClient(zkString, 5000, 5000, retryPolicy);
	CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zkString)
			.connectionTimeoutMs(CONNECTION_TIMEOUT).sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(retryPolicy)
			// 命名空间 .namespace("super")
			.build();
	
//	public ZkClient(CuratorFramework client) {
//		this.client = client;
//		// 启动
//		client.start();
//	}
	
	/**
	 * 读取根节点的下级节点.<br>
	 */
	public <T> void getNode() {
//		https://www.zifangsky.cn/1367.html
//		https://blog.csdn.net/rongbaojian/article/details/82078368
//		https://www.jianshu.com/p/d9a82906a1e4
	};
	
	/**
	 * 读取指定节点的下级节点.<br>
	 * 
	 * @param path
	 * @return
	 */
	public List<String> getNode(String path) {
		try {
			return client.getChildren().forPath(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	};
	
	public boolean existsNode(String path) {
		try {
			Stat stat = client.checkExists().forPath(path);
			return stat != null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	};
	
//	public <T> void getNodeData(String path) {
	public String getNodeData(String path) {
		try {
			return new String(client.getData().forPath(path));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};

	public <T> void addNode(String path, T value) {

	};
	
//	public <T> void addNode(String path, T value, CreateMode mode) {
	public String addNode(String path, byte[] value, CreateMode mode) {
		try {
			return client.create().withMode(mode).forPath(path, value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};

	public void updateNode(String path) {

	};

	public Void deleteNode(String path) {
		try {
			return client.delete().forPath(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};

	public void watcherNode() {

	};
	
	
}
