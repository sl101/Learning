package com.foxminded.zhevaha.task_10.domain;

import java.util.HashSet;
import java.util.Set;

public class AcademicPlan {
	private long id;
	private int year;
	private Set<Lecture> lectures;

	public AcademicPlan(int year) {
		this.year = year;
		this.lectures = new HashSet<Lecture>();
	}

	public int getYear() {
		return year;
	}

	public void addLecture(Lecture lecture) {
		lectures.add(lecture);
	}

	public Set<Lecture> getLectures() {
		return lectures;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
