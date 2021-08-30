package com.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @Auther: eclair
 * @Date: 2021/5/11 23:05
 * @Description:
 */
public class NewIoClient {
	public static void main(String[] args) throws IOException {
		SocketChannel open = SocketChannel.open();
		open.connect(new InetSocketAddress("127.0.0.1", 7001));

		String fileName = "F:\\视屏学习资料\\Netty学习资料\\软件\\软件.zip";
		FileChannel channel = new FileInputStream(fileName).getChannel();

		//window的stransferto只能发送8m
		long startTime = System.currentTimeMillis();
		long transferCount = 0;
		int position = 0;
		while (true) {
			long result = channel.transferTo(position,8000008 , open);
			position +=result;
			if (0 == result) {
				break;
			}
			transferCount+=result;

		}
		System.out.println("发送的总的字节数 =" + transferCount + ",耗时:" + (System.currentTimeMillis() - startTime));
		channel.close();

	}
}
