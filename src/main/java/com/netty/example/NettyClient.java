package com.netty.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Auther: eclair
 * @Date: 2021/5/27 21:50
 * @Description:
 */
public class NettyClient {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup eventExecutors = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		try {

			bootstrap.group(eventExecutors)
					.channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new NettyClientHandler());
						}
					});
			System.out.println("客户端已经Ok。。。。");
			ChannelFuture sync = bootstrap.connect(new InetSocketAddress("127.0.0.1", 6668)).sync();
			sync.channel().closeFuture().sync();
		} finally {
			eventExecutors.shutdownGracefully();
		}
	}
}
