package com.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

/**
 * @Auther: eclair
 * @Date: 2021/8/2 20:32
 * @Description:
 */
public class NettyByteBuf02 {
	public static void main(String[] args) {
		ByteBuf byteBuf = Unpooled.copiedBuffer("hello world!,北京1", StandardCharsets.UTF_8);

		if(byteBuf.hasArray()) {
			byte[] array = byteBuf.array();
			System.out.println(new String(array,StandardCharsets.UTF_8));
			System.out.println(byteBuf);
			System.out.println(byteBuf.readableBytes() );
			System.out.println(byteBuf.getCharSequence(6,byteBuf.readableBytes(),StandardCharsets.UTF_8).toString());
			System.out.println(byteBuf.readableBytes());
		} else {
			System.out.printf("wu");
		}


	}
}
