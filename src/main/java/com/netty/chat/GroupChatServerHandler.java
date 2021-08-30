package com.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: eclair
 * @Date: 2021/8/16 21:20
 * @Description:
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {
	public static Map<String, Channel> channelMap = new HashMap<>();
	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.forEach(ch -> {
			if (channel != ch) {
				ch.writeAndFlush("[客户]" + ch.remoteAddress() + " 发送了消息:" + msg);
			} else {
				ch.writeAndFlush("[自己]发送了消息：" + msg);
			}
		});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		channelGroup.remove(ctx.channel());
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		channelGroup.writeAndFlush("[客户端]:" + simpleDateFormat.format(new Date()) + ":" + ctx.channel().remoteAddress() + "加入了\n");
		channelGroup.add(ctx.channel());
		channelMap.put("id100",ctx.channel());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + "上線了");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + "离线了");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		channelGroup.writeAndFlush("[客户端]:" + simpleDateFormat.format(new Date()) + ":" + ctx.channel().remoteAddress() + "离开了\n");
		System.out.println("chanel'size is " + channelGroup.size());
	}
}
