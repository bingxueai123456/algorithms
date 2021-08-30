package com.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @Auther: eclair
 * @Date: 2021/6/23 21:55
 * @Description:
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		if (msg instanceof HttpRequest) {
			System.out.println("pipline: " + ctx.pipeline().hashCode());
			System.out.println("msg 类型：" + msg.getClass());
			System.out.println("客户端地址：" + ctx.channel().remoteAddress());
			HttpRequest httpRequest = (HttpRequest) msg;
			URI uri = new URI(httpRequest.uri());
			if ("/favicon.ico".equals(uri.getPath())) {
				System.out.println("nihao");
				return;
			}

			ByteBuf byteBuf = Unpooled.copiedBuffer("hello，我是服務器", CharsetUtil.UTF_8);
			DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
			defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf-8");
			defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
			ctx.writeAndFlush(defaultFullHttpResponse);
		}
	}
}
