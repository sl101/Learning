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
import com.foxminded.zhevaha.task_10.domain.Teacher;

public class TeacherDao {

	private static final Logger log = Logger.getLogger(ConnectionFactory.class);

	public void insertTable(String name, Date dayOfBirth) {
		log.info("start insertTable( " + name + ", " + dayOfBirth + " )");
		String query = "INSERT INTO Teachers (name, dayOfBirth) VALUES ( '" + name + "', '" + dayOfBirth + "');";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			log.info("try connection");
			connection = ConnectionFactory.getConnection();
			try {
				log.info("try create statement");
				statement = connection.createStatement();
				statement.execute(query);
			} catch (SQLException e) {
				log.error("ERROR. Not created statement", e);
			}
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}

	public Teacher findByName(String name) {
		log.info("start findByName( " + name + " )");
		String query = "SELECT * FROM Teachers WHERE name = '" + name + "';";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Teacher teacher = null;

		try {
			log.info("try connection");
			connection = ConnectionFactory.getConnection();
			try {
				log.info("try create statement");
				statement = connection.createStatement();
				try {
					log.info("try get resultSet");
					resultSet = statement.executeQuery(query);
					while (resultSet.next()) {
						String teacherName = resultSet.getString(2);
						Date teacherDayOfBirth = resultSet.getDate(3);
						teacher = new Teacher(teacherName, teacherDayOfBirth);
						teacher.setId(resultSet.getLong(1));
					}
				} catch (SQLException e) {
					log.error("ERROR. Not got resultSet", e);
				}
			} catch (SQLException e) {
				log.error("ERROR. Not created statement", e);
			}
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return teacher;
	}

	public List<Teacher> findAll() {
		List<Teacher> teachers = new ArrayList<Teacher>();
		log.info("start findAll teachers");
		String query = "SELECT * FROM Teachers;";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			log.info("try connection");
			connection = ConnectionFactory.getConnection();
			try {
				log.info("try create statement");
				statement = connection.createStatement();
				try {
					log.info("try get resultSet");
					resultSet = statement.executeQuery(query);
					while (resultSet.next()) {
						String teacherName = resultSet.getString(2);
						Date teacherDayOfBirth = resultSet.getDate(3);
						Teacher teacher = new Teacher(teacherName, teacherDayOfBirth);
						teacher.setId(resultSet.getLong(1));
						teachers.add(teacher);
					}
				} catch (SQLException e) {
					log.error("ERROR. Not got resultSet", e);
				}
			} catch (SQLException e) {
				log.error("ERROR. Not created statement", e);
			}
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}

		return teachers;
	}

	public List<Course> findTeacherCourses() {
		List<Course> teacherCourses = new ArrayList<Course>();

		return teacherCourses;
	}
}
