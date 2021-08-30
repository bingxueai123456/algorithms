package com.netty.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Auther: eclair
 * @Date: 2021/5/24 21:44
 * @Description:
 */
public class NettyServer {
	public static void main(String[] args) throws InterruptedException {

		EventLoopGroup boosgroup = new NioEventLoopGroup(2);
		EventLoopGroup workerGroup = new NioEventLoopGroup(2);
		try {

			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(boosgroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							System.out.println("客戶socketChannel ip=" + ch.remoteAddress());
							ch.pipeline().addLast(new NettyServerHandler());
						}
					});

			System.out.println(".....服务器 is ready");
			ChannelFuture channelFuture = serverBootstrap.bind(6668).addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if (future.isSuccess()) {
						System.out.println("绑定成功");
					} else {
						System.out.println("绑定失败！");
					}
				}
			});
			channelFuture.channel().closeFuture().sync();
		} finally {
			boosgroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
