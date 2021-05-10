package com.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: eclair
 * @Date: 2021/4/21 21:45
 * @Description:
 */
public class TestMappedByteBuffer {
	public static void main(String[] args) throws Exception {
		RandomAccessFile rw = new RandomAccessFile("D:\\test.txt", "rw");
		FileChannel channel = rw.getChannel();

		MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
		map.put(1, (byte) 'A');
		map.put(3, (byte) 'B');
//		map.put(5, (byte) '1');
//		rw.close();
	}
}
