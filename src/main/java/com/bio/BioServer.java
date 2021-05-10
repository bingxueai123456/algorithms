package com.bio;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: eclair
 * @Date: 2021/4/12 21:54
 * @Description:
 */
public class BioServer {
	static ExecutorService executorService = Executors.newFixedThreadPool(1);

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(6666);
		System.out.println("服务器启动。。。。");

		while (true) {
			Socket accept = serverSocket.accept();
			System.out.println("有客户端链接");
			executorService.execute(new Runnable() {
				@SneakyThrows
				@Override
				public void run() {
					handler(accept);
				}
			});
		}
	}

	public static void handler(Socket socket) {
		try {
			byte[] bytes = new byte[1024];
			int len = 0;
			InputStream inputStream = socket.getInputStream();
			StringBuilder sb = new StringBuilder();
			while (true) {
				int read = inputStream.read(bytes);
				if (read != -1) {
					System.out.println(new String(bytes, 0, read));
				} else {
					break;
				}
			}
			System.out.println("接受到的消息：" + sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
