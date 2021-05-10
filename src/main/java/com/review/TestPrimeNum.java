package com.review;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: eclair
 * @Date: 2019/9/17 20:36
 * @Description:
 */
public class TestPrimeNum {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(200);
		int splitNum = 2100 / 200;
		for (int i = 0; i < 200; i++) {
			for (int j = splitNum * i; j < splitNum * (i + 1); j++) {
				int finalJ = j;
				executorService.execute(() -> {
					isPrime(finalJ);
				});
			}
		}
		executorService.shutdown();
	}

	public static void isPrime(int num) {
		for (int i = 2; i < Math.sqrt(num); i++) {
			if (num % i == 0) {
				return;
			}
		}
		System.out.println(num);

	}
}
