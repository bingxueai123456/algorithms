package com.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: eclair
 * @Date: 2021/4/20 21:40
 * @Description:
 */
public class NIOFileChannel {
	public static void main(String[] args) throws IOException {
//		String str = "你好，我是你爹";
//		FileOutputStream fileOutputStream = new FileOutputStream("D:\\file01.txt");
//		FileChannel channel = fileOutputStream.getChannel();
//		ByteBuffer wrap = ByteBuffer.wrap(str.getBytes());
//		ByteBuffer.allocate(2);
//		channel.write(wrap);
//		channel.close();
		File file = new File("D:\\file01.txt");
		FileInputStream fileInputStream = new FileInputStream(file);
		FileChannel channel = fileInputStream.getChannel();
		ByteBuffer allocate = ByteBuffer.allocate((int) file.length());
		channel.read(allocate);
		System.out.println(new String(allocate.array()));
		fileInputStream.close();
	}
}
