package com.nio.chat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @Auther: eclair
 * @Date: 2021/5/9 20:14
 * @Description:
 */
public class NioClient {
	private final static int PORT = 6667;
	private final static String HOST = "127.0.0.1";
	private Selector selector;
	private String username;
	private SocketChannel socketChannel;

	public NioClient() {
		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_READ);
			username = socketChannel.getLocalAddress().toString();
			System.out.println(username + " is ok ......");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendInfo(String info) {
		info = username + " 说：" + info;
		try {
			socketChannel.write(ByteBuffer.wrap(info.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readInfo() {
		try {
			int count = selector.select();
			if (count > 0) {
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					if (key.isReadable()) {
						SocketChannel channel = (SocketChannel) key.channel();
						ByteBuffer allocate = ByteBuffer.allocate(1024);
						int read = channel.read(allocate);
						if (read > 0) {
							String msg = new String(allocate.array());
							System.out.println(msg.trim());
						}
					}
					iterator.remove();
				}
			} else {
				System.out.println("没有可用的通道");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		NioClient nioClient = new NioClient();
		new Thread(() -> {
			while(true){
				nioClient.readInfo();
				try {
					Thread.sleep(3000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			String msg = scanner.nextLine();
			nioClient.sendInfo(msg);
		}
	}

}
