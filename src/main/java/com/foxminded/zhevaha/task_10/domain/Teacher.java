package com.foxminded.zhevaha.task_10.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Teacher extends Person {
	private long id;
	private List<Course> teacherCourses;

	public Teacher(String name, Date dayOfBirth) {
		super(name, dayOfBirth);
		this.teacherCourses = new ArrayList<Course>();
	}

	public void addCourse(Course course) {
		teacherCourses.add(course);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Course> getTeacherCourses() {
		return teacherCourses;
	}

}
