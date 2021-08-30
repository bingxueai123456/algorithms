package com.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @Auther: eclair
 * @Date: 2021/6/22 22:13
 * @Description:
 */
public class TestServer {
	public static void main(String[] args) throws Exception {
		EventLoopGroup boosGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(boosGroup, workGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new TestServerInitializer());
			ChannelFuture sync = serverBootstrap.bind(80).addListener(new GenericFutureListener<Future<? super Void>>() {
				@Override
				public void operationComplete(Future<? super Void> future) throws Exception {
					if(future.isSuccess()) {
						System.out.printf("链接成功\n");
					} else {
						System.out.printf("链接失败\n");
					}
				}
			 });
			sync.channel().closeFuture().sync();

		} finally {
			boosGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
}
