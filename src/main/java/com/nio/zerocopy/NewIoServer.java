package com.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Auther: eclair
 * @Date: 2021/5/11 22:57
 * @Description:
 */
public class NewIoServer {
	public static void main(String[] args) throws IOException {
		InetSocketAddress inetSocketAddress = new InetSocketAddress(7001);
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.bind(inetSocketAddress);

		ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
		while(true) {
			SocketChannel socketChannel = serverSocketChannel.accept();

			int readCount = 0;
			while(-1 != readCount) {
				try {
					readCount = socketChannel.read(byteBuffer);
				}catch (Exception e) {
					break;
				}
				byteBuffer.rewind();
			}

		}

	}

}
