package com.nio.select;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther: eclair
 * @Date: 2021/4/26 22:17
 * @Description:
 */
public class NIOServer {
	public static void main(String[] args) throws Exception{
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.bind(new InetSocketAddress(9999));

		Selector selector = Selector.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		while(true){
			if(selector.select(6000)==0){
				System.out.println("服务器等待了1秒，没有链接");
				continue;
			}

			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			if(selectionKeys.isEmpty()) {
				continue;
			}

			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			while(iterator.hasNext()) {
				SelectionKey key = iterator.next();
				if(key.isAcceptable()) {
					SocketChannel accept = serverSocketChannel.accept();
					accept.configureBlocking(false);
					accept.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(3));
					System.out.println("收到客户端链接！");
				} else if (key.isReadable()) {
					SocketChannel socketChannel = (SocketChannel)key.channel();
					ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
					socketChannel.read(byteBuffer);
					System.out.println("从客户端接受到：" + new String(byteBuffer.array()));
					byteBuffer.clear();
				} else if(key.isValid()) {
					ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
					byteBuffer.clear();
					System.out.println("断开连接");
				}

				iterator.remove();
			}

		}
 	}
}
