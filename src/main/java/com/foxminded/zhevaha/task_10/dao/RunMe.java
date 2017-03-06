package com.foxminded.zhevaha.task_10.dao;

import java.util.Calendar;
import java.util.Date;

import com.foxminded.zhevaha.task_10.domain.Course;
import com.foxminded.zhevaha.task_10.domain.Teacher;
import com.foxminded.zhevaha.task_10.domain.Univer;

public class RunMe {
	private static Univer univer;
	static TeacherDao teacherDao;

	public static void main(String[] args) {
		univer = new Univer("Foxminded University");
		teacherDao = new TeacherDao();
		UniverDao univerDao = new UniverDao();
		CourseDao courseDao = new CourseDao();

		// deleteTeacher(teacherDao);
		// deleteTeacherFromCourse(courseDao);
		enlorrNewTeacher(univer);
	}

	private static void enlorrNewTeacher(Univer univer) {
		Course course = univer.getCourses().get(2);
		// System.out.println(course.getName() + " " + course.getId());
		Teacher newTeacher = new Teacher("Google", createRandomDayOfBirth("teacher"));
		// Teacher newTeacher = teacherDao.findTeacherById(8254);
		univer.enrollTeacher(newTeacher, course);

	}

	private static void deleteTeacherFromCourse(CourseDao courseDao) {
		Course course = univer.getCourses().get(0);
		Teacher existTeacher = univer.getTeachers().get(1);
		System.out.println(
				"course: " + course.getName() + "\nteacher: " + existTeacher.getName() + " id " + existTeacher.getId());
		courseDao.deleteTeacher(course, existTeacher);

	}

	private static void deleteTeacher(TeacherDao teacherDao) {
		// Teacher teacher = new Teacher("Gogol",
		// createRandomDayOfBirth("teacher"));
		// teacherDao.remove(teacher);
		Teacher existTeacher = univer.getTeachers().get(2);
		System.out.println(existTeacher.getName());
		teacherDao.removeTeacher(existTeacher);
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
}
