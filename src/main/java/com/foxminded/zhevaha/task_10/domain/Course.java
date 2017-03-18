package com.foxminded.zhevaha.task_10.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Course {
	private long id;
	private String name;
	private Set<String> topics;
	private Set<Teacher> courseTeachers;

	public Course(String name) {
		this.name = name;
		topics = new TreeSet<String>();
		courseTeachers = new HashSet<Teacher>();
	}

	public void addTopic(String topic) {
		topics.add(topic);
	}

	public void addTeacher(Teacher teacher) {
		courseTeachers.add(teacher);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Set<String> getTopics() {
		return topics;
	}

	public Set<Teacher> getCourseTeachers() {
		return courseTeachers;
	}

}
