package com.foxminded.zhevaha.task_10.domain.tests;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.foxminded.zhevaha.task_10.dao.UniverException;
import com.foxminded.zhevaha.task_10.domain.Course;
import com.foxminded.zhevaha.task_10.domain.Group;
import com.foxminded.zhevaha.task_10.domain.Lecture;
import com.foxminded.zhevaha.task_10.domain.Person;
import com.foxminded.zhevaha.task_10.domain.Room;
import com.foxminded.zhevaha.task_10.domain.SchedulePosition;
import com.foxminded.zhevaha.task_10.domain.Student;
import com.foxminded.zhevaha.task_10.domain.Teacher;
import com.foxminded.zhevaha.task_10.domain.Univer;

public class DomainTests {
	private static Univer univer;
	private static Course course;
	private static String[] courses = { "Mathematics", "Physics", "hemistry" };
	private static Group group;
	private static String[] groups = { "001", "002", "003" };
	private static Person person;
	private static Person testScheduleTeacher;
	private static String[] teachers = { "Ivanov", "Petrov", "Sidorov", "Ekhel", "Joli" };
	private static String[] students = { "Vania", "Sasha", "Masha", "Kolia", "Lena", "Petro", "Dima", "Stas", "Taras",
			"Sofija" };
	private static Room room;
	private static Lecture newLecture;
	private static SchedulePosition schedulePosition;
	private static Timestamp period;
	private static SimpleDateFormat periodDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:ss");

	@BeforeClass
	public static void initiateVariables() throws UniverException {
		univer = new Univer("\"FOXminded\" University");
		for (String courseName : courses) {
			course = new Course(courseName);
			for (int i = 0; i < (3 + 10 * Math.random()); i++) {
				course.addTopic("00" + (i + 1));
			}
			univer.addCourse(course);
		}
		for (String groupName : groups) {
			group = new Group(groupName);
			univer.addGroup(group);
		}
		for (int i = 0; i < 9; i++) {
			room = new Room("" + (int) (4 * Math.random()) + "0" + (i + 1));
			univer.addRoom(room);
		}
		List<Teacher> arrayTeachers = new ArrayList<Teacher>(univer.getTeachers());
		List<Course> arrayCourses = new ArrayList<Course>(univer.getCourses());
		for (int i = 0; i < arrayTeachers.size(); i++) {
			univer = univer.enrollTeacher(arrayTeachers.get(i),
					arrayCourses.get((int) (univer.getCourses().size() * Math.random())));
		}

		List<Student> arrayStudents = new ArrayList<Student>(univer.getStudents());
		List<Group> arrayGroups = new ArrayList<Group>(univer.getGroups());
		for (int i = 0; i < arrayStudents.size(); i++) {
			univer = univer.enrollStudent(arrayStudents.get(i),
					arrayGroups.get((int) (arrayGroups.size() * Math.random())));
		}

		period = setTime(2017, (int) (4 * Math.random()), (int) (30 * Math.random()), (int) (9 + 6 * Math.random()),
				(int) 0);
		periodDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
		newLecture = new Lecture(new Group("111"), new Course("English"), "Past Perfect");
		testScheduleTeacher = new Teacher("Bruce Eckel", createRandomDayOfBirth("teacher"));
		schedulePosition = new SchedulePosition(newLecture, new Room("5"), period, (Teacher) testScheduleTeacher);
		Set<SchedulePosition> schedulePositions = new HashSet<SchedulePosition>();
		schedulePositions.add(schedulePosition);
		univer.createSchedule(schedulePositions);
	}

	private static java.sql.Timestamp setTime(int year, int month, int day, int hourOfDay, int minute) {
		java.util.Date date = new java.util.Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, hourOfDay, minute);
		date = calendar.getTime();
		return new java.sql.Timestamp(date.getTime());
	}

	private static java.sql.Date createRandomDayOfBirth(String person) {
		int minAge = 25;
		int randomYears = 50;
		if (person.equals("student")) {
			minAge = 15;
			randomYears = 25;
		}
		java.util.Date date = new java.util.Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,
				(int) (2017 + (new Date(0).getTime() - (int) (minAge + randomYears * Math.random()))));
		calendar.set(Calendar.DAY_OF_YEAR, (int) (365 * Math.random()));
		date = calendar.getTime();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}

	@Test
	public void testEnrollTeacher() throws UniverException {
		person = new Teacher("Trump", createRandomDayOfBirth("teacher"));
		course = new Course("Geography");
		univer.enrollTeacher((Teacher) person, course);
		assertTrue(univer.getTeachers().contains(person));
		assertTrue(course.getCourseTeachers().contains(person));
	}

	@Test(expected = RuntimeException.class)
	public void testEnrollExistingTeacher() throws UniverException {
		Iterator<Teacher> iteratorTeacher = univer.getTeachers().iterator();
		iteratorTeacher.hasNext();
		person = iteratorTeacher.next();
		Iterator<Course> iteratorCourse = univer.getCourses().iterator();
		iteratorCourse.hasNext();
		course = iteratorCourse.next();
		univer.enrollTeacher((Teacher) person, course);
	}

	@Test
	public void testFireTeacher() throws UniverException {
		List<Teacher> courseTeachers = new ArrayList<Teacher>(course.getCourseTeachers());
		univer.fireTeacher((Teacher) person, courseTeachers);
		assertTrue(!univer.getTeachers().contains(person));
	}

	@Test(expected = RuntimeException.class)
	public void testFireAbsentTeacher() throws UniverException {
		person = new Teacher("Pupkin", createRandomDayOfBirth("teacher"));
		course = new Course("History");
		List<Teacher> courseTeachers = new ArrayList<Teacher>(course.getCourseTeachers());
		univer.fireTeacher((Teacher) person, courseTeachers);
	}

	@Test
	public void testEnrollStudent() throws UniverException {
		Person newStudent = new Student("Petrov", createRandomDayOfBirth("student"));
		Group newGroup = new Group("testGroup");
		univer.enrollStudent((Student) newStudent, newGroup);
		assertTrue(univer.getStudents().contains(newStudent));
		assertTrue(newGroup.getStudents().contains(newStudent));
	}

	@Test
	public void testExpelStudent() throws UniverException {
		List<Student> arrayStudents = new ArrayList<Student>(univer.getStudents());
		Student student = arrayStudents.get((int) (arrayStudents.size() * Math.random()));
		univer = univer.expelStudent(student);
		assertTrue(!univer.getStudents().contains(student));
	}

	@Test(expected = RuntimeException.class)
	public void testExpelAbsentStudent() throws UniverException {
		Person newStudent = new Student("Petrov", createRandomDayOfBirth("student"));
		Group newGroup = new Group("testGroup");
		univer.expelStudent((Student) newStudent);
	}

	@Test
	public void testCeateLectureTopic() throws UniverException {
		Lecture testLecture = new Lecture(new Group("777"), new Course("Biology"), "Some lecture");
		period = setTime(2017, (int) (4 * Math.random()), (int) (30 * Math.random()), (int) (9 + 6 * Math.random()),
				(int) 0);
		SchedulePosition newSchedulePosition = new SchedulePosition(testLecture, new Room("222"), period,
				new Teacher("Shevchenko", createRandomDayOfBirth("teacher")));
		Set<SchedulePosition> schedulePositions = new HashSet<SchedulePosition>();
		schedulePositions.add(schedulePosition);
		univer.createSchedule(schedulePositions);
		assertTrue(univer.getSchedule().contains(newSchedulePosition));

	}

	@Test
	public void testShowShedule() {
		String expected = "#1 group: 111 time: " + periodDateFormat.format(period)
				+ " room: 5 course: English topic: Past Perfect";
		String actual = univer.showSchedule(period, testScheduleTeacher);
		assertTrue(expected, expected.equals(actual));
	}

	@Test(expected = RuntimeException.class)
	public void testShowAbsentShedule() {
		Person wrongPerson = new Teacher("WrongPerson", createRandomDayOfBirth("teacher"));
		univer.showSchedule(period, wrongPerson);
	}
}
