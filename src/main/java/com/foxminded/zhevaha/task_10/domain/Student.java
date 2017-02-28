package com.foxminded.zhevaha.task_10.domain;

import java.util.Date;

public class Student extends Person {
	private long id;

	public Student(String name, Date dayOfBirth) {
		super(name, dayOfBirth);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
