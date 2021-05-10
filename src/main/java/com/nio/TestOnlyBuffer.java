package com.nio;

import java.nio.ByteBuffer;

/**
 * @Auther: eclair
 * @Date: 2021/4/20 22:43
 * @Description:
 */
public class TestOnlyBuffer {
	public static void main(String[] args) {
		ByteBuffer allocate = ByteBuffer.allocate(64);
		for (int i = 0; i < 64; i++) {
			allocate.put((byte) i);
		}
		allocate.flip();
		ByteBuffer byteBuffer = allocate.asReadOnlyBuffer();
		while (byteBuffer.hasRemaining()) {
			System.out.println(byteBuffer.get());
		}
		byteBuffer.clear();
		byteBuffer.put((byte) 1);

	}
}
