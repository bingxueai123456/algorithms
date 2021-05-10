package com.basis;

import lombok.Data;
import org.apache.commons.compress.utils.Lists;
import org.apache.flink.shaded.guava18.com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Auther: eclair
 * @Date: 2019/12/14 16:08
 * @Description:
 */
public class TestClass {
	public static void main(String[] args) {
		User user = new User("nihao", 12.0);
		User user2 = new User("nihao2", 12.0);
		User user3 = new User("nihao", 12.0);
		User user4 = new User("nihao", 12.0);
		User user5 = new User("nihao3", 12.0);
		User user6 = new User("nihao2", 12.0);

		ArrayList<User> objects = Lists.newArrayList();
		objects.add(user);
		objects.add(user2);
		objects.add(user3);
		objects.add(user4);
		objects.add(user5);
		objects.add(user6);
//		Map<String, Double> maps = Maps.newHashMap();
//		for (User u : objects) {
//			String name = u.getName();
//			Double amount = u.getAmount();
//			maps.put(name, Optional.ofNullable(maps.get(name)).orElse(0.0) + amount);
//		}
		Map<String, Double> collect = objects.stream().collect(Collectors.groupingBy(User::getName, Collectors.summingDouble(User::getAmount)));
		System.out.println(collect);
	}
}

@Data
class User {
	private String name;
	private Double amount;

	public User(String name, Double amount) {
		this.name = name;
		this.amount = amount;
	}
}
