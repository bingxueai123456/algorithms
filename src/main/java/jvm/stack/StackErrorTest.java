package jvm.stack;

/**
 * @Auther: eclair
 * @Date: 2020/11/8 13:44
 * @Description:
 */
public class StackErrorTest {
	public static int count = 1;

	public static void main(String[] args) {
		System.out.println(count);
		count++;
		main(args);
	}
}
