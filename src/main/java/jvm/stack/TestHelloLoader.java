package jvm.stack;

/**
 * @Auther: eclair
 * @Date: 2020/10/31 14:54
 * @Description:
 */
public class TestHelloLoader {
	public static void main(String[] args) {
		System.out.println("谢谢加载我");
		System.out.println("w我是你爹");
		new Thread(()->{
			System.out.println("我是你爸爸");
		}).start();
	}
}
