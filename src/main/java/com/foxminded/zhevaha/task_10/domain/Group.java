package com.foxminded.zhevaha.task_10.domain;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private long id;
	private String name;
	private List<Student> students;
	private List<Course> courses;

	public Group(String name) {
		this.name = name;
		this.students = new ArrayList<Student>();
		this.courses = new ArrayList<Course>();
	}

	public void addStudent(Student student) {
		students.add(student);
	}

	public void addCourse(Course cource) {
		courses.add(cource);
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

	public List<Student> getStudents() {
		return students;
	}

	public List<Course> getCourses() {
		return courses;
	}

}
