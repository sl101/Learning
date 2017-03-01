package com.foxminded.zhevaha.task_10.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.foxminded.zhevaha.task_10.domain.Teacher;

public class RunMe {

	private static String[] teachers = { "Trump", "Petrov", "Sidorov", "Bruce Eckel" };

	public static void main(String[] args) {

		TeacherDao teacherDao = new TeacherDao();

		// findTeacherByName(teacherDao);

		// insertIntoTeachers(teacherDao);

		findAllTeachers(teacherDao);

	}

	private static void findAllTeachers(TeacherDao teacherDao) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		teachers.addAll(teacherDao.findAll());

		for (int i = 0; i < teachers.size(); i++) {
			Teacher teacher = teachers.get(i);
			System.out.println(teacher.getId() + ". " + teacher.getName() + " " + teacher.getDayOfBirth());
		}
	}

	private static void insertIntoTeachers(TeacherDao teacherDao) {
		for (int i = 0; i < teachers.length; i++) {
			teacherDao.insertTable(teachers[i], createRandomDayOfBirth("teacher"));
		}
	}

	private static void findTeacherByName(TeacherDao teacherDao) {
		Teacher teacher = teacherDao.findByName("Ivanov");
		System.out.println(teacher.getId() + ". " + teacher.getName() + " " + teacher.getDayOfBirth());
		for (int i = 0; i < teacher.getTeacherCourses().size(); i++) {
			System.out.println(teacher.getTeacherCourses().get(i).getName());
		}
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
