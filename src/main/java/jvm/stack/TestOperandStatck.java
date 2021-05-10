package jvm.stack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: eclair
 * @Date: 2020/11/14 15:15
 * @Description:
 */
public class TestOperandStatck {
	public int test6AddOperation() {
		byte i = 15;
		int j = 8;
		j = i + j;
		int m = 12222222;
		return j + m;
	}

	public void testI() {
		int i = 1;
		int b = i++;
		int j = 1;
		int c = ++j;

	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		while (true) {
			executorService.execute(() -> {

				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int i = 1 / 0;
			});
			Thread.sleep(1000L);
		}
	}
}
