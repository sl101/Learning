package com.foxminded.zhevaha.task_10.domain;

import java.sql.Timestamp;

public class SchedulePosition {
	private long id;
	private Lecture lecture;
	private Room room;
	private Timestamp lectureTime;
	private Teacher teacher;

	public SchedulePosition(Lecture lecture, Room room, Timestamp dateFotSchedule, Teacher teacher) {
		this.lecture = lecture;
		this.room = room;
		this.lectureTime = dateFotSchedule;
		this.teacher = teacher;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public Room getRoom() {
		return room;
	}

	public Timestamp getLectureTime() {
		return lectureTime;
	}

	public Teacher getTeacher() {
		return teacher;
	}

}
