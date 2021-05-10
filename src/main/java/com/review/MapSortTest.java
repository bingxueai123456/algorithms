package com.review;

import java.util.*;

/**
 * @Auther: eclair
 * @Date: 2019/9/1 14:20
 * @Description:
 */
public class MapSortTest {
	public static void main(String[] args) {
		User user = new User("A", 12);
		User user2 = new User("B", 10);
		User user3 = new User("C", 17);
		User user4 = new User("A", 18);
		User user5 = new User("B", 11);
		User user6 = new User("B", 14);
		List<User> users = Arrays.asList(user, user2, user3, user6, user4, user5);
		Map<String, List<User>> map = new HashMap<>();
		for (User u : users) {
			List<User> o = map.computeIfAbsent(u.getName(), (k) -> new ArrayList<>());
			o.add(u);
		}
		map.entrySet().stream().sorted(Comparator.comparingInt(e -> -e.getValue().size())).forEach(System.out::print);

	}
}

class User {
	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

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

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}