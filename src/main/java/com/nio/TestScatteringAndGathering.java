package com.nio;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: eclair
 * @Date: 2021/4/21 22:18
 * @Description:
 */
public class TestScatteringAndGathering {
	public static void main(String[] args) throws Exception {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
		serverSocketChannel.socket().bind(inetSocketAddress);

		ByteBuffer[] buffer = new ByteBuffer[2];
		buffer[0] = ByteBuffer.allocate(5);
		buffer[1] = ByteBuffer.allocate(3);

		SocketChannel accept = serverSocketChannel.accept();
		int messageLength = 8;

		while (true) {
			int byteRead = 0;
			while (byteRead < messageLength) {
				long read = accept.read(buffer);
				byteRead += read;
				System.out.println("读取到的字节数是：" + byteRead);
				List<ByteBuffer> byteBuffers = Arrays.asList(buffer);
				byteBuffers.stream().map(e -> "position=" + e.position() + ",limit=" + e.limit()).forEach(System.out::println);
			}

			Arrays.asList(buffer).forEach(Buffer::flip);

			long byteWrite = 0;
			while (byteWrite < messageLength) {
				long write = accept.write(buffer);
				byteWrite += write;
				System.out.println("写了" + write);
			}

			Arrays.asList(buffer).forEach(Buffer::clear);
			System.out.println("ByteRead:=" + byteRead + ",byteWrite=" + byteWrite + ", messageLegth=" + messageLength);

		}

	}
}
