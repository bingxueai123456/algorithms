package com.review;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: eclair
 * @Date: 2019/8/28 10:31
 * @Description:
 */
public class Seriable implements Serializable {
	private static final long serialVersionUID = 6167343030306494510L;
	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public static void main(String[] args) throws Exception {
		Calendar c = Calendar.getInstance();
		c.set(1990, 3, 3);
		System.out.println(c.get(Calendar.YEAR));//1990
		System.out.println(c.get(Calendar.MONTH));//3
		System.out.println(c.get(Calendar.DAY_OF_MONTH));//3
		System.out.println(c.get(Calendar.DAY_OF_YEAR));//93
		Date time = c.getTime();
		System.out.println(time);

	}

	@Override
	public String toString() {
		return "Seriable{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
enum Fruit {
	APPLE("string");

	Fruit(String string) {

	}
}
