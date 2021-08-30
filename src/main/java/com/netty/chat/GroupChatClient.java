package com.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @Auther: eclair
 * @Date: 2021/8/16 21:48
 * @Description:
 */
public class GroupChatClient {
	private String host = "127.0.0.1";
	private int port = 5577;

	public GroupChatClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() throws InterruptedException {
		NioEventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group)
					.channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							pipeline.addLast("encoder", new StringDecoder());
							pipeline.addLast("decoder", new StringEncoder());
							pipeline.addLast(new GroupChatClientHandler());
						}
					});

			ChannelFuture sync = bootstrap.connect(host, port).sync();
			Channel channel = sync.channel();
			System.out.println("-----------" + channel.localAddress() + "---------");
			Scanner scanner = new Scanner(System.in);
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				channel.writeAndFlush(s + "\r\n");
			}
			sync.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new GroupChatClient("127.0.0.1", 5577).run();
	}
}
