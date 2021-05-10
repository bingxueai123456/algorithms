package jvm.stack;

/**
 * @Auther: eclair
 * @Date: 2020/11/8 13:28
 * @Description:
 */
public class StackTest {
	public static void main(String[] args) {
		new StackTest().methodA();
	}

	public void methodA() {
		int i = 10;
		int j = 20;
		methodB();
	}

	public void methodB() {
		int k = 20;
		int m = 40;
	}
}
