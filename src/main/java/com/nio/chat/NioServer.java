package com.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Auther: eclair
 * @Date: 2021/5/9 19:29
 * @Description:
 */
public class NioServer {
	private Selector selector;
	private ServerSocketChannel listeneChannel;
	private final static int PORT = 6667;

	public NioServer() {
		try {
			selector = Selector.open();
			listeneChannel = ServerSocketChannel.open();
			listeneChannel.socket().bind(new InetSocketAddress(PORT));
			listeneChannel.configureBlocking(false);
			listeneChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listen() {
		try {
			while (true) {
				int count = selector.select();
				if (count > 0) {
					Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
					while (iterator.hasNext()) {
						SelectionKey key = iterator.next();

						if (key.isAcceptable()) {
							SocketChannel sc = listeneChannel.accept();
							sc.configureBlocking(false);
							sc.register(selector, SelectionKey.OP_READ);
							System.out.println(sc.getRemoteAddress() + " 上线 ");
						}

						if (key.isReadable()) {
							readData(key);
						}

						iterator.remove();

					}
				} else {
					System.out.println("等待......");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	private void readData(SelectionKey key) {
		SocketChannel accept = null;

		try {
			accept = (SocketChannel) key.channel();
			ByteBuffer allocate = ByteBuffer.allocate(1024);
			int count = accept.read(allocate);
			if (count > 0) {
				String s = new String(allocate.array());
				System.out.println("from 客户端:" + s);

				//向其他客户端转发消息
				sendInfoToOtherClient(s, accept);
			}

		} catch (Exception e) {
			try {
				System.out.println(accept.getRemoteAddress() + "离线了....");
				key.cancel();
				accept.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	private void sendInfoToOtherClient(String msg, SocketChannel self) throws IOException {
		for (SelectionKey key : selector.keys()) {
			Channel channel = key.channel();
			if (!(channel instanceof SocketChannel) || channel == self) {
				continue;
			}

			SocketChannel socketChannel = (SocketChannel) channel;
			ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
			socketChannel.write(buffer);
		}
	}

	public static void main(String[] args) {
		NioServer nioServer = new NioServer();
		nioServer.listen();
	}

}
