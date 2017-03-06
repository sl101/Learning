package com.foxminded.zhevaha.task_10.domain.tests;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

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
	private static String[] teachers = { "Ivanov", "Petrov", "Sidorov" };
	private static String[] students = { "Vania", "Sasha", "Masha", "Kolia", "Lena", "Petro", "Dima", "Stas", "Taras",
			"Sofija" };
	private static Room room;
	private static Lecture newLecture;
	private static SchedulePosition schedulePosition;
	private static Date period;
	private static SimpleDateFormat periodDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:ss");

	@BeforeClass
	public static void initiateVariables() {
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
		for (int i = 0; i < teachers.length; i++) {
			person = new Teacher(teachers[i], createRandomDayOfBirth("teacher"));
			univer.enrollTeacher((Teacher) person, univer.getCourses().get(i).getCourseTeachers());
		}
		for (int i = 0; i < students.length; i++) {
			person = new Student(students[i], createRandomDayOfBirth("student"));
			if (i <= students.length / 3) {
				group = univer.getGroups().get(0);
			} else if (i > students.length / 3 && i <= students.length / 3 * 2) {
				group = univer.getGroups().get(1);
			} else if (i > students.length / 3 * 2) {
				group = univer.getGroups().get(2);
			}
			univer.enrollStudent((Student) person, group.getStudents());
		}
		period = new Date();
		periodDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
		newLecture = new Lecture(new Group("111"), new Course("English"), "Past Perfect");
		testScheduleTeacher = new Teacher("Bruce Eckel", createRandomDayOfBirth("teacher"));
		schedulePosition = new SchedulePosition(newLecture, new Room("5"), period, (Teacher) testScheduleTeacher);
		univer.createSchedule(schedulePosition);
	}

	private static Date createRandomDayOfBirth(String person) {
		int minAge = 25;
		int randomYears = 50;
		if (person.equals("student")) {
			minAge = 15;
			randomYears = 25;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1900 + (new Date().getYear()) - (int) (minAge + randomYears * Math.random()));
		calendar.set(Calendar.DAY_OF_YEAR, (int) (365 * Math.random()));
		Date randomDayOfBirth = calendar.getTime();
		return randomDayOfBirth;
	}

	@Test
	public void testEnrollTeacher() {
		person = new Teacher("Trump", createRandomDayOfBirth("teacher"));
		course = new Course("Geography");
		univer.enrollTeacher((Teacher) person, course.getCourseTeachers());
		assertTrue(univer.getTeachers().contains(person));
		assertTrue(course.getCourseTeachers().contains(person));
	}

	@Test(expected = RuntimeException.class)
	public void testEnrollExistingTeacher() {
		person = univer.getTeachers().get(0);
		course = univer.getCourses().get(0);
		univer.enrollTeacher((Teacher) person, course.getCourseTeachers());
	}

	@Test
	public void testFireTeacher() {
		univer.fireTeacher((Teacher) person, course.getCourseTeachers());
		assertTrue(!univer.getTeachers().contains(person));
	}

	@Test(expected = RuntimeException.class)
	public void testFireAbsentTeacher() {
		person = new Teacher("Pupkin", createRandomDayOfBirth("teacher"));
		course = new Course("History");
		univer.fireTeacher((Teacher) person, course.getCourseTeachers());
	}

	@Test
	public void testEnrollStudent() {
		Person newStudent = new Student("Petrov", createRandomDayOfBirth("student"));
		Group newGroup = new Group("testGroup");
		univer.enrollStudent((Student) newStudent, newGroup.getStudents());
		assertTrue(univer.getStudents().contains(newStudent));
		assertTrue(newGroup.getStudents().contains(newStudent));
	}

	@Test(expected = RuntimeException.class)
	public void testEnrollExistingStudent() {
		person = univer.getStudents().get(0);
		group = univer.getGroups().get(0);
		univer.enrollStudent((Student) person, group.getStudents());
	}

	@Test
	public void testExpelStudent() {
		Group testGroup = univer.getGroups().get(0);
		Person testStudent = testGroup.getStudents().get(0);
		univer.expelStudent((Student) testStudent, testGroup.getStudents());
		assertTrue(!univer.getStudents().contains(testStudent));
		assertTrue(!testGroup.getStudents().contains(testStudent));
	}

	@Test(expected = RuntimeException.class)
	public void testExpelAbsentStudent() {
		Person newStudent = new Student("Petrov", createRandomDayOfBirth("student"));
		Group newGroup = new Group("testGroup");
		univer.expelStudent((Student) newStudent, newGroup.getStudents());
	}

	@Test
	public void testCeateLectureTopic() {
		Lecture testLecture = new Lecture(new Group("777"), new Course("Biology"), "Some lecture");
		SchedulePosition newSchedulePosition = new SchedulePosition(testLecture, new Room("222"), new Date(),
				new Teacher("Shevchenko", createRandomDayOfBirth("teacher")));
		univer.createSchedule(newSchedulePosition);
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
