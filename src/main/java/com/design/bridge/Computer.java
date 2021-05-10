package com.design.bridge;

/**
 * @Auther: eclair
 * @Date: 2019/8/29 19:52
 * @Description:
 */
public class Computer {
	private Brand brand;

	public Computer(Brand brand) {
		this.brand = brand;
	}

	public void sale() {
		this.brand.tell();
	}
}
