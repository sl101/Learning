package com.foxminded.zhevaha.task_10.domain;

public class Lecture {
	private long id;
	private Group group;
	private Course course;
	private String lectureTopic;

	public Lecture(Group group, Course course, String lectureTopic) {
		this.group = group;
		this.course = course;
		this.lectureTopic = lectureTopic;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Group getGroup() {
		return group;
	}

	public Course getCourse() {
		return course;
	}

	public String getLectureTopic() {
		return lectureTopic;
	}

}
