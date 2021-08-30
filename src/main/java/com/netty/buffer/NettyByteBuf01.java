package com.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Auther: eclair
 * @Date: 2021/7/20 21:36
 * @Description:
 */
public class NettyByteBuf01 {
	public static void main(String[] args) {
		ByteBuf buffer = Unpooled.buffer(10);
		for (int i = 0; i < 10; i++) {
			buffer.writeByte(i);
		}
		System.out.println(buffer.capacity());
		for (int i = 0; i < buffer.capacity(); i++) {
			System.out.println(buffer.readByte());
		}


	}
}
