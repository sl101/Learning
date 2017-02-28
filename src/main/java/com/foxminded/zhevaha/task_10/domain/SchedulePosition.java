package com.foxminded.zhevaha.task_10.domain;

import java.util.Date;

public class SchedulePosition {
	private long id;
	private Lecture scheduledLecture;
	private Room room;
	private Date lectureTime;
	private Teacher teacher;

	public SchedulePosition(Lecture scheduledLecture, Room room,
			Date lectureTime, Teacher teacher) {
		this.scheduledLecture = scheduledLecture;
		this.room = room;
		this.lectureTime = lectureTime;
		this.teacher = teacher;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Lecture getScheduledLecture() {
		return scheduledLecture;
	}

	public Room getRoom() {
		return room;
	}

	public Date getLectureTime() {
		return lectureTime;
	}

	public Teacher getTeacher() {
		return teacher;
	}

}
