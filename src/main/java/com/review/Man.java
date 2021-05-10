package com.review;

/**
 * @Auther: eclair
 * @Date: 2019/8/28 11:27
 * @Description:
 */
public class Man implements IMan{
	public Integer fun() {
		System.out.println("本人fun");
		return 10;
	}

	@Override
	public String fun2() {
		return "hello";
	}
}
