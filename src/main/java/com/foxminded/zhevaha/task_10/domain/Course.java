package com.foxminded.zhevaha.task_10.domain;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private long id;
	private String name;
	private List<String> topics;
	private List<Teacher> courseTeachers;

	public Course(String name) {
		this.name = name;
		topics = new ArrayList<String>();
		courseTeachers = new ArrayList<Teacher>();
	}

	public void addTopic(String topic) {
		topics.add(topic);
	}

	public void addCourseTeacher(Teacher teacher) {
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

	public List<String> getTopics() {
		return topics;
	}

	public List<Teacher> getCourseTeachers() {
		return courseTeachers;
	}

}
