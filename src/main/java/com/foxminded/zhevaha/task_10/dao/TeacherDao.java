package com.foxminded.zhevaha.task_10.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.foxminded.zhevaha.task_10.domain.Course;
import com.foxminded.zhevaha.task_10.domain.Teacher;

public class TeacherDao implements GenericDao<Teacher, Long> {

	private static final Logger log = Logger.getLogger(CourseDao.class);
	private final String CREATE = "INSERT INTO Teachers (name, dayOfBirth) VALUES (?, ?);";
	private final String GET_ALL = "SELECT * FROM Teachers;";
	private final String GET_BY_ID = "SELECT * FROM Teachers WHERE id = ?;";
	private final String UPDATE = "UPDATE Teachers SET name = ?, dayOfBirth = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM Teachers WHERE id = ?;";
	private final String GET_COURSE_TEACHERS = "SELECT * FROM Teachers WHERE id IN (SELECT teacher_id FROM courses_teachers WHERE course_id = ?);";

	public Set<Teacher> getAll() throws DaoException {
		Set<Teacher> teachers = new HashSet<Teacher>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_ALL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				Date teacherDayOfBirth = (Date) resultSet.getDate("dayofbirth");
				Teacher teacher = new Teacher(name, teacherDayOfBirth);
				long id = resultSet.getLong("id");
				teacher.setId(id);
				Set<Course> teacherCourses = new CourseDao().getTeacherCourses(id);
				Iterator<Course> iteratorTeacherCourses = teacherCourses.iterator();
				while (iteratorTeacherCourses.hasNext()) {
					teacher.addCourse(iteratorTeacherCourses.next());
				}
				teachers.add(teacher);
			}
		} catch (SQLException e) {
			log.error("Problem get data", e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return teachers;
	}

	public Teacher getById(Long id) throws DaoException {
		Teacher teacher = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_BY_ID);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			String name = resultSet.getString("name");
			Date dayOfBirth = resultSet.getDate("dayofbirth");
			teacher = new Teacher(name, dayOfBirth);
			teacher.setId(id);
			Set<Course> teacherCourses = new CourseDao().getTeacherCourses(id);
			Iterator<Course> iteratorTeacherCourses = teacherCourses.iterator();
			while (iteratorTeacherCourses.hasNext()) {
				teacher.addCourse(iteratorTeacherCourses.next());
			}
		} catch (SQLException e) {
			log.error("Problem get data", e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return teacher;
	}

	public Teacher update(Teacher teacher) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(UPDATE);
			statement.setString(1, teacher.getName());
			statement.setString(2, teacher.getDayOfBirth().toString());
			statement.setLong(3, teacher.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("Problem update data", e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
		teacher = getById(teacher.getId());
		return teacher;
	}

	public void delete(Teacher teacher) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, teacher.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("Problem delete data", e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void create(Teacher teacher) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, teacher.getName());
			statement.setDate(2, (java.sql.Date) teacher.getDayOfBirth());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			teacher.setId(resultSet.getLong("id"));
		} catch (SQLException e) {
			log.error("Problem create data", e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}

	public Set<Teacher> getCourseTeachers(long id) throws DaoException {
		Set<Teacher> teachers = new HashSet<Teacher>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_COURSE_TEACHERS);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				Date dayOfBirth = resultSet.getDate("dayofbirth");
				Teacher teacher = new Teacher(name, dayOfBirth);
				teacher.setId(resultSet.getLong("id"));
				teachers.add(teacher);
			}
		} catch (SQLException e) {
			log.error("Problem get data", e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return teachers;
	}

}
