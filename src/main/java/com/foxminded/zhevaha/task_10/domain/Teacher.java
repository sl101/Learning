package com.foxminded.zhevaha.task_10.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Teacher extends Person {
	private long id;
	private Set<Course> teacherCourses;

	public Teacher(String name, Date date) {
		super(name, date);
		this.teacherCourses = new HashSet<Course>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		if (id != other.id)
			return false;
		return true;
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

	public Set<Course> getTeacherCourses() {
		return teacherCourses;
	}

}
