package jvm.stack;

import java.util.Date;

/**
 * @Auther: eclair
 * @Date: 2020/11/14 13:24
 * @Description:
 */
public class TestLocalVairiables {
	private int count = 0;

	public static void main(String[] args) {
		TestLocalVairiables test = new TestLocalVairiables();
		int num = 10;
		test.test1();

	}

	public TestLocalVairiables() {

	}

	public static void testStatic() {
		TestLocalVairiables test = new TestLocalVairiables();
		Date date = new Date();
		int count = 10;
		System.out.println(count);
	}

	public void test1() {
		Date date = new Date();
		String name1 = "atguigu.com";
		String info = test2(date, name1);
		System.out.println(date + name1);
	}

	public String test2(Date dataP, String name2, int... ags) {
		dataP = null;
		name2 = "songwukong";
		double wegight = 130.5;
		char gender = '男';
		return dataP + name2;

	}

	public void test3() {
		count++;
	}

	public void test4() {
		int a = 0;
		{
			int b = 0;
			b = a + 1;
		}
		int c = a + 1;
	}

}
