package com.foxminded.zhevaha.task_10.domain;

import java.util.ArrayList;
import java.util.List;

public class AcademicPlan {
	private long id;
	private List<Lecture> lectures;

	public AcademicPlan() {
		this.lectures = new ArrayList<Lecture>();
	}

	public void addLecture(Lecture lecture) {
		lectures.add(lecture);
	}

	public List<Lecture> getLectures() {
		return lectures;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
