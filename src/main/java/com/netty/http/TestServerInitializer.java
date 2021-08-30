package com.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Auther: eclair
 * @Date: 2021/6/23 21:55
 * @Description:
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
		pipeline.addLast("MyHttpServerHandler",new TestHttpServerHandler());

	}
}
