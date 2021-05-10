package com.algorithms.array;

import org.apache.flink.shaded.guava18.com.google.common.collect.Lists;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Auther: eclair
 * @Date: 2021/4/21 22:53
 * @Description:
 */
public class TestSparseArray {
	public static void main(String[] args) throws IOException {
		byte originArray[][] = new byte[11][11];
		originArray[1][2] = 1;
		originArray[2][3] = 2;
		saveFile(originArray);
//		int sum = 0;
//		for (int i = 0; i < originArray.length; i++) {
//			for (int j = 0; j < originArray[i].length; j++) {
//				System.out.print(originArray[i][j] + " ");
//				if (originArray[i][j] > 0) {
//					sum++;
//				}
//			}
//			System.out.println();
//		}
//
//		int spareArray[][] = new int[sum + 1][3];
		Byte[][] bytes = readFile();
		System.out.println(Arrays.deepToString(bytes));
	}

	public static void saveFile(byte originArray[][]) throws IOException {
		FileOutputStream rw = new FileOutputStream("D://test.txt");
		FileChannel channel = rw.getChannel();
		ByteBuffer allocate = ByteBuffer.allocate(originArray[0].length + 1);
		for (int i = 0; i < originArray.length; i++) {
			for (int j = 0; j < originArray[i].length; j++) {
				allocate.put(originArray[i][j]);
			}
			allocate.put((byte) '\n');
			allocate.flip();
			channel.write(allocate);
			allocate.clear();
		}
		rw.close();
	}

	public static Byte[][] readFile() throws IOException {
		FileInputStream fileInputStream = new FileInputStream("D://test.txt");
		FileChannel channel = fileInputStream.getChannel();
		ByteBuffer allocate = ByteBuffer.allocate(fileInputStream.available());
		int read = channel.read(allocate);
		int sum = 0;
		allocate.flip();

		ArrayList<Byte[]> sumList = Lists.newArrayList();
		ArrayList<Byte> list = Lists.newArrayList();
		while (allocate.hasRemaining()) {
			byte b = allocate.get();
			if (b == '\n') {
				Byte[] bytes = list.toArray(new Byte[0]);
				sumList.add(bytes);
				list.clear();
				continue;
			}
			list.add(b);
		}

		return sumList.toArray(new Byte[0][0]);
	}
}
