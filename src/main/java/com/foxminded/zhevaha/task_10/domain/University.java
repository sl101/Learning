package com.foxminded.zhevaha.task_10.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class University {
	private long id;
	private String name;
	private List<Teacher> teachers;
	private List<Student> students;
	private List<Group> groups;
	private List<Course> courses;
	private List<Room> rooms;
	private List<SchedulePosition> schedule;

	public University(String name) {
		this.name = name;
		teachers = new ArrayList<Teacher>();
		students = new ArrayList<Student>();
		groups = new ArrayList<Group>();
		courses = new ArrayList<Course>();
		rooms = new ArrayList<Room>();
		schedule = new ArrayList<SchedulePosition>();
	}

	public void enrollTeacher(Teacher teacher, List<Teacher> courseTeachers) {
		if (teachers.contains(teacher)) {
			throw new RuntimeException("This person was already enrolled");
		} else {
			teachers.add(teacher);
			courseTeachers.add(teacher);
		}
	}

	public void fireTeacher(Teacher teacher, List<Teacher> courseTeachers) {
		if (!teachers.contains(teacher)) {
			throw new RuntimeException("This person did not identified");
		} else {
			teachers.remove(teacher);
			if (courseTeachers.contains(teacher)) {
				courseTeachers.remove(teacher);
			}
		}
	}

	public void enrollStudent(Student student, List<Student> groupStudents) {
		if (students.contains(student)) {
			throw new RuntimeException("This person was already enrolled");
		} else {
			students.add(student);
			groupStudents.add(student);
		}
	}

	public void expelStudent(Student student, List<Student> groupStudents) {
		if (!students.contains(student)) {
			throw new RuntimeException("This person did not identified");
		} else {
			students.remove(student);
			if (groupStudents.contains(student)) {
				groupStudents.remove(student);
			}
		}
	}

	public void createSchedule(SchedulePosition schedulePosition) {
		if (!schedule.contains(schedulePosition)) {
			schedule.add(schedulePosition);
		} else {
			throw new RuntimeException("This position was already included");
		}
	}

	public String showSchedule(Date period, Person person) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
		StringBuilder result = new StringBuilder();
		result.append("");
		for (int i = 0; i < schedule.size(); i++) {
			if (period.equals(schedule.get(i).getLectureTime())) {
				if (person.equals(schedule.get(i).getTeacher())) {
					result.append(
							"#" + (i + 1) + " group: " + schedule.get(i).getScheduledLecture().getGroup().getName()
									+ " time: " + simpleDateFormat.format(schedule.get(i).getLectureTime()) + " room: "
									+ schedule.get(i).getRoom().getName() + " course: "
									+ schedule.get(i).getScheduledLecture().getCourse().getName() + " topic: "
									+ schedule.get(i).getScheduledLecture().getLectureTopic());
				} else if (schedule.get(i).getScheduledLecture().getGroup().getStudents().contains(person)) {
					result.append("#" + (i + 1) + " time: " + simpleDateFormat.format(schedule.get(i).getLectureTime())
							+ " room: " + schedule.get(i).getRoom().getId() + " course: "
							+ schedule.get(i).getScheduledLecture().getCourse().getName() + " topic: "
							+ schedule.get(i).getScheduledLecture().getLectureTopic() + " teacher: "
							+ schedule.get(i).getTeacher());
				} else {
					throw new RuntimeException("The person was not identified");
				}
			}
		}

		return result.toString();
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

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addGroup(Group group) {
		groups.add(group);
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void addCourse(Course course) {
		courses.add(course);
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public List<SchedulePosition> getSchedule() {
		return schedule;
	}

}
