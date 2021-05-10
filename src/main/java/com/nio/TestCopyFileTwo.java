package com.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Auther: eclair
 * @Date: 2021/4/20 22:27
 * @Description:
 */
public class TestCopyFileTwo {
	public static void main(String[] args) throws IOException {
		FileInputStream fileInputStream = new FileInputStream("D://file01.txt");
		FileOutputStream fileOutputStream = new FileOutputStream("D://file02.txt");
		FileChannel readChannel = fileInputStream.getChannel();
		FileChannel writeChannel = fileOutputStream.getChannel();

		writeChannel.transferFrom(readChannel,0,readChannel.size());
	}
}
