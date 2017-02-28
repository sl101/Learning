package com.foxminded.zhevaha.task_10.domain;

import java.util.Date;

public class Person {
	private String name;
	private Date dayOfBirth;

	public Person(String name, Date dayOfBirth) {
		this.name = name;
		this.dayOfBirth = dayOfBirth;
	}

	public String getName() {
		return name;
	}

	public Date getDayOfBirth() {
		return dayOfBirth;
	}
}
