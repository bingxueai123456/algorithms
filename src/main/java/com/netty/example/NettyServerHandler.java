package com.netty.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Auther: eclair
 * @Date: 2021/5/27 21:27
 * @Description:
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("服务器端接受到数据：" + ((ByteBuf) msg).toString(CharsetUtil.UTF_8));

//		ctx.channel().eventLoop().scheduleWithFixedDelay(()->{
//			String name = Thread.currentThread().getName();
//			System.out.println(name);
//			ctx.writeAndFlush(Unpooled.copiedBuffer("hell,客户端1~我是定时任务",CharsetUtil.UTF_8));
//
//		},5,2, TimeUnit.SECONDS);

//
//		ctx.channel().eventLoop().execute(new Runnable() {
//			@Override
//			public void run() {
//				String name = Thread.currentThread().getName();
//				System.out.println(name);
//				try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				ctx.writeAndFlush(Unpooled.copiedBuffer("hell,客户端1~",CharsetUtil.UTF_8));
//			}
//		});
		ctx.writeAndFlush(Unpooled.copiedBuffer("hell,客户端1~", CharsetUtil.UTF_8));

		System.out.println("go on .....");

	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.copiedBuffer("hell,客户端2~", CharsetUtil.UTF_8));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.channel().close();
	}
}
