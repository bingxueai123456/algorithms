package com.nio;

import java.nio.ByteBuffer;

/**
 * @Auther: eclair
 * @Date: 2021/4/13 22:02
 * @Description:
 */
public class TestBuffer {
	public static void main(String[] args) {
		ByteBuffer allocate = ByteBuffer.allocateDirect(20);
		 allocate.put((byte) 10);
		 allocate.put((byte) 11);
		 allocate.put((byte) 12);
		 allocate.put((byte) 13);
		 allocate.put((byte) 14);

		 allocate.flip();
		allocate.limit(6);
		allocate.position(4);
		
		 while(allocate.hasRemaining()) {
			 System.out.println(allocate.get());
		 }
//		 allocate.flip();
//
//		 allocate.put(1);
//		 allocate.put(2);
//		 allocate.put(3);
//		 allocate.put(4);
//		 allocate.put(5);
//		allocate.flip();
//
//		while(allocate.hasRemaining()) {
//			System.out.println(allocate.get());
//		}
	}
}
