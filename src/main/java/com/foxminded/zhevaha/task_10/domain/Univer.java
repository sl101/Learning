package com.foxminded.zhevaha.task_10.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.foxminded.zhevaha.task_10.dao.CourseDao;
import com.foxminded.zhevaha.task_10.dao.UniverException;
import com.foxminded.zhevaha.task_10.dao.SchedulePositionDao;
import com.foxminded.zhevaha.task_10.dao.StudentDao;
import com.foxminded.zhevaha.task_10.dao.TeacherDao;
import com.foxminded.zhevaha.task_10.dao.UniverDao;

public class Univer {
	private long id;
	private String name;
	private Set<Teacher> teachers;
	private Set<Student> students;
	private Set<Group> groups;
	private Set<Course> courses;
	private Set<Room> rooms;
	private Set<SchedulePosition> schedule;
	private static final Logger log = Logger.getLogger(Univer.class);

	public Univer(String name) {
		this.name = name;
		teachers = new HashSet<Teacher>();
		students = new HashSet<Student>();
		groups = new HashSet<Group>();
		courses = new HashSet<Course>();
		rooms = new HashSet<Room>();
		schedule = new HashSet<SchedulePosition>();
	}

	public Univer enrollTeacher(Teacher teacher, Course course) throws UniverException {
		log.info("Enroll teacher");
		if (teacher.getId() == 0) {
			new TeacherDao().create(teacher);
		}
		if (course.getId() != 0) {
			course.addTeacher(teacher);
			teacher.addCourse(course);
			new CourseDao().enroll(teacher, course);
		} else {
			log.fatal("This course is not exist");
		}
		return new UniverDao().update(this);
	}

	public Univer fireTeacher(Teacher teacher, List<Teacher> courseTeachers) throws UniverException {
		if (!teachers.contains(teacher)) {
			log.fatal("This person did not identified");
		} else {
			new TeacherDao().delete(teacher);
			if (courseTeachers.contains(teacher)) {
				courseTeachers.remove(teacher);
			}
		}
		return new UniverDao().update(this);
	}

	public Univer enrollStudent(Student student, Group group) throws UniverException {
		log.info("Enroll student");
		if (student.getId() == 0) {
			new StudentDao().create(student);
		}
		List<Student> enrolledStudents = new ArrayList<Student>();
		Iterator<Group> iteratorGroups = groups.iterator();
		while (iteratorGroups.hasNext()) {
			Group existGroup = iteratorGroups.next();
			List<Student> groupstudens = new ArrayList<Student>(existGroup.getStudents());
			for (int i = 0; i < groupstudens.size(); i++) {
				enrolledStudents.add(groupstudens.get(i));
			}
		}
		if (enrolledStudents.contains(student)) {
			log.fatal("This person was already enrolled");
		} else {
			group.addStudent(student);
			new StudentDao().enroll(student, group);
		}
		return new UniverDao().update(this);
	}

	public Univer expelStudent(Student student) throws UniverException {
		if (!students.contains(student)) {
			log.fatal("This person did not identified");
		} else {
			new StudentDao().delete(student);
		}
		return new UniverDao().update(this);
	}

	public Univer createSchedule(Set<SchedulePosition> schedulePositions) throws UniverException {
		Iterator<SchedulePosition> schedulePositionIterator = schedulePositions.iterator();
		while (schedulePositionIterator.hasNext()) {
			SchedulePosition schedulePosition = schedulePositionIterator.next();
			new SchedulePositionDao().create(schedulePosition);
		}
		return new UniverDao().update(this);
	}

	public String showSchedule(Timestamp period, Person person) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
		StringBuilder result = new StringBuilder();
		result.append("");
		List<SchedulePosition> periodSchedule = new ArrayList<SchedulePosition>(schedule);
		for (int i = 0; i < periodSchedule.size(); i++) {
			if (periodSchedule.get(i).getLectureTime().after(period)) {
				if (person.equals(periodSchedule.get(i).getTeacher())) {
					result.append("#" + (i + 1) + " group: " + periodSchedule.get(i).getLecture().getGroup().getName()
							+ " time: " + simpleDateFormat.format(periodSchedule.get(i).getLectureTime()) + " room: "
							+ periodSchedule.get(i).getRoom().getName() + " course: "
							+ periodSchedule.get(i).getLecture().getCourse().getName() + " topic: "
							+ periodSchedule.get(i).getLecture().getLectureTopic() + " teacher: "
							+ periodSchedule.get(i).getTeacher().getName() + "\n");
				} else if (periodSchedule.get(i).getLecture().getGroup().getStudents().contains(person)) {
					System.out.println("student in schedule: " + person);
					result.append(
							"#" + (i + 1) + " time: " + simpleDateFormat.format(periodSchedule.get(i).getLectureTime())
									+ " room: " + periodSchedule.get(i).getRoom().getId() + " course: "
									+ periodSchedule.get(i).getLecture().getCourse().getName() + " topic: "
									+ periodSchedule.get(i).getLecture().getLectureTopic() + " teacher: "
									+ periodSchedule.get(i).getTeacher().getName());
				} else {
					log.error("No schedule for this person");
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

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		students.add(student);
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void addGroup(Group group) {
		groups.add(group);
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void addCourse(Course course) {
		courses.add(course);
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}

	public Set<SchedulePosition> getSchedule() {
		return schedule;
	}

	public void addSchedulePosition(SchedulePosition schedulePosition) {
		schedule.add(schedulePosition);
	}

}
