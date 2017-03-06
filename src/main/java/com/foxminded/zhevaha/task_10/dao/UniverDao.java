package com.foxminded.zhevaha.task_10.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.foxminded.zhevaha.task_10.domain.Course;
import com.foxminded.zhevaha.task_10.domain.Group;
import com.foxminded.zhevaha.task_10.domain.Lecture;
import com.foxminded.zhevaha.task_10.domain.Room;
import com.foxminded.zhevaha.task_10.domain.Student;
import com.foxminded.zhevaha.task_10.domain.Teacher;
import com.foxminded.zhevaha.task_10.domain.Univer;

public class UniverDao {

	private static final Logger log = Logger.getLogger(UniverDao.class);

	public void registerUniver(String name) {
		log.info("Register Univer");
		String query = "INSERT INTO Univer (name) VALUES ('" + name + "');";
		ConnectionFactory.enterData(query);
	}

	public void updateUniver(Univer univer) {
		log.info("Update Univer");
		// String query ="UPDATE Univers SET teachers_id =
		// "+univer.getTeachers();
		List<Teacher> teachers = findTeachers();
		List<Course> courses = findCourses();
		// System.out.println("ID teachers: ");
		// for (int i = 0; i < teachers.size(); i++) {
		// System.out.println("" + teachers.get(i).getId());
		// }
		// System.out.println("ID courses: ");
		// for (int i = 0; i < courses.size(); i++) {
		// System.out.println("" + courses.get(i).getId());
		// }

	}

	public Long findUniverMaxID() {
		log.info("get max id from Univer");
		String query = "SELECT MAX(id) FROM Univers;";
		return ConnectionFactory.findMaxID(query);
	}

	public List<Teacher> findTeachers() {
		log.info("Find teachers in date base");
		List<Teacher> teachers = new ArrayList<Teacher>();
		String query = "SELECT * FROM Teachers;";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.createStatement();
			log.info("statement was created");
			try {
				resultSet = statement.executeQuery(query);
				log.info("resultSet was created");
				while (resultSet.next()) {
					String teacherName = resultSet.getString(2);
					Date teacherDayOfBirth = resultSet.getDate(3);
					Teacher teacher = new Teacher(teacherName, teacherDayOfBirth);
					teacher.setId(resultSet.getLong(1));
					teachers.add(teacher);
				}
			} catch (SQLException e) {
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (teachers.isEmpty()) {
			log.fatal("There were no registered teachers");
			throw new NullPointerException();
		} else {
			log.info("Teachers list was created");
		}
		return teachers;
	}

	public List<Student> findStudents() {
		List<Student> students = new ArrayList<Student>();
		// ...

		return students;
	}

	public List<Course> findCourses() {
		log.info("Find courses in date base");
		List<Course> courses = new ArrayList<Course>();
		String query = "SELECT * FROM Courses order by id;";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.createStatement();
			log.info("statement was created");
			try {
				resultSet = statement.executeQuery(query);
				log.info("resultSet was created");
				while (resultSet.next()) {
					String courseName = resultSet.getString(2);
					Course course = new Course(courseName);
					course.setId(resultSet.getLong(1));
					courses.add(course);
				}
			} catch (SQLException e) {
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (courses.isEmpty()) {
			log.fatal("There were no registered courses");
			throw new NullPointerException("There were no registered courses");
		} else {
			log.info("Courses list was created");
		}

		return courses;
	}

	public List<Group> findGroups() {
		List<Group> groups = new ArrayList<Group>();
		// ...

		return groups;
	}

	public List<Room> findRooms() {
		List<Room> rooms = new ArrayList<Room>();
		// ...

		return rooms;
	}

	public List<Lecture> findLectures() {
		List<Lecture> lectures = new ArrayList<Lecture>();
		// ...

		return lectures;
	}
}
