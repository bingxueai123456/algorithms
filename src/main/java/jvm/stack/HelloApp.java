package jvm.stack;

/**
 * @Auther: eclair
 * @Date: 2020/10/31 15:18
 * @Description:
 */
public class HelloApp {

	public static void main(String[] args) {
	}
}

class DeadThread {
	static {
		if (true) {
			System.out.println(Thread.currentThread().getName() + "初始化当前类");
			while (true) {

			}
		}
	}
}
