package com.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: eclair
 * @Date: 2021/4/20 22:07
 * @Description:
 */
public class TestSingleBufferReadWrite {
	public static void main(String[] args) throws IOException {
		FileInputStream fileInputStream = new FileInputStream("D://file01.txt");
		FileChannel readChannel = fileInputStream.getChannel();

		ByteBuffer allocate = ByteBuffer.allocate(1);

		FileOutputStream fileOutputStream = new FileOutputStream("D://file02.txt");
		FileChannel channel = fileOutputStream.getChannel();
		while (true) {
			allocate.clear();
			int read = readChannel.read(allocate);
			if(read == -1) {
				break;
			}
			allocate.flip();
			channel.write(allocate);
		}
		fileOutputStream.close();





	}
}
