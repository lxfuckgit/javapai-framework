package com.javapai.framework.protocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public abstract class ServerNodeImpl {
	/**/
	private static final Logger logger = LoggerFactory.getLogger(ServerNode.class);

	/**/
	EventLoopGroup bossGroup = new NioEventLoopGroup();
	// EventLoopGroup bossGroup = new NioEventLoopGroup(1);
	EventLoopGroup workerGroup = new NioEventLoopGroup();

	/**
	 * 
	 * @param initChannel
	 */
	public void startServer(ChannelInitializer<Channel> initChannel) {
		// TODO Auto-generated method stub
		startServer("127.0.0.1", 11111, initChannel);
	}

	/**
	 * 
	 * @param host
	 * @param port
	 * @param initChannel
	 */
	public void startServer(String host, int port, ChannelInitializer<Channel> initChannel) {
		// TODO Auto-generated method stub
		ServerBootstrap server = new ServerBootstrap();
		server.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO)).childHandler(initChannel)
				.option(ChannelOption.SO_BACKLOG, 128).option(ChannelOption.SO_KEEPALIVE, true);

		try {
			// 启动代理监听.
			Channel channel = server.bind(host, port).sync().channel();
			logger.info("------服务启动完毕,映射端口:{}", port);

			// 监听关闭事件.
			channel.closeFuture().addListener((ChannelFutureListener) future -> {
				channel.deregister();
				logger.warn("------channel从workerGroup取消注册!!!");
				channel.close();
				logger.warn("------channel通道已关闭!!!");
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			stop();
			e.printStackTrace();
		}

	}

	public void stop() {
		// TODO Auto-generated method stub
		// 释放线程资源.
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}

}
