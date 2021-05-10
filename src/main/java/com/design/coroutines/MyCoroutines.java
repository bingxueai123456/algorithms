package com.design.coroutines;

import com.offbynull.coroutines.user.Continuation;
import com.offbynull.coroutines.user.Coroutine;
import com.offbynull.coroutines.user.CoroutineRunner;

/**
 * @Auther: eclair
 * @Date: 2019/9/9 10:28
 * @Description:
 */
public class MyCoroutines implements Coroutine {
	@Override
	public void run (Continuation c){
		System.out.println("started");
		for (int i = 0; i < 10; i++) {
			echo(c, i);
		}
	}

	private void echo (Continuation c,int x){
		System.out.println(x);
		c.suspend();
	}

	public static void main(String[] args) {
		CoroutineRunner runner= new CoroutineRunner(new MyCoroutines());
		runner.execute();
	}
}
