package com.foxminded.zhevaha.task_10.domain;

import java.util.HashSet;
import java.util.Set;

public class Group {
	private long id;
	private String name;
	private Set<Student> students;
	private Set<Course> courses;

	public Group(String name) {
		this.name = name;
		this.students = new HashSet<Student>();
		this.courses = new HashSet<Course>();
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

	public Set<Student> getStudents() {
		return students;
	}

	public Set<Course> getCourses() {
		return courses;
	}

}
