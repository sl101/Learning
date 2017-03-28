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

	private static final Logger log = Logger.getLogger(TeacherDao.class);

	private final String CREATE = "INSERT INTO Teachers (name, dayOfBirth) VALUES (?, ?);";
	private final String GET_ALL = "SELECT * FROM Teachers;";
	private final String GET_BY_ID = "SELECT * FROM Teachers WHERE id = ?;";
	private final String UPDATE = "UPDATE Teachers SET name = ?, dayOfBirth = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM Teachers WHERE id = ?;";
	private final String GET_COURSE_TEACHERS = "SELECT * FROM Teachers WHERE id IN (SELECT teacher_id FROM courses_teachers WHERE course_id = ?);";

	public Set<Teacher> getAll() {
		log.info("Find teachers in date base");
		Set<Teacher> teachers = new HashSet<Teacher>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_ALL);
			log.info("statement was created");
			try {
				resultSet = statement.executeQuery();
				log.info("resultSet was created");
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
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (teachers.isEmpty()) {
			log.fatal("There were no registered teachers. The list is empty");
		} else {
			log.info("Teachers list was created");
		}
		return teachers;
	}

	public Teacher getById(Long id) {
		log.info("Find teacher by ID");
		Teacher teacher = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_BY_ID);
			statement.setLong(1, id);
			log.info("statement was created");
			try {
				resultSet = statement.executeQuery();
				if (resultSet.next()) {
					log.info("resultSet was created");
					String name = resultSet.getString("name");
					Date dayOfBirth = resultSet.getDate("dayofbirth");
					teacher = new Teacher(name, dayOfBirth);
					teacher.setId(id);
					Set<Course> teacherCourses = new CourseDao().getTeacherCourses(id);
					Iterator<Course> iteratorTeacherCourses = teacherCourses.iterator();
					while (iteratorTeacherCourses.hasNext()) {
						teacher.addCourse(iteratorTeacherCourses.next());
					}
				} else {
					log.info("resultSet has not data");
				}
			} catch (SQLException e) {
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return teacher;
	}

	public Teacher update(Teacher teacher) {
		log.info("Update Teacher");
		if (teacher.getId() != 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(UPDATE);
				statement.setString(1, teacher.getName());
				statement.setString(2, teacher.getDayOfBirth().toString());
				statement.setLong(3, teacher.getId());
				statement.executeUpdate();
				log.info("statement was created");
			} catch (SQLException e) {
				log.error("ERROR. Statement was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement);
			}
			teacher = getById(teacher.getId());
			return teacher;
		} else {
			log.info("Teacher was not created");
			return null;
		}

	}

	public void delete(Teacher teacher) {
		log.info("Delete teacher");
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, teacher.getId());
			statement.executeUpdate();
			log.info("statement was created");
			log.info("Teacher was deleted");
		} catch (SQLException e) {
			log.fatal("Teacher was not deleted", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void create(Teacher teacher) {
		log.info("Create teacher");
		if (teacher.getId() == 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, teacher.getName());
				statement.setDate(2, (java.sql.Date) teacher.getDayOfBirth());
				statement.executeUpdate();
				log.info("statement was created");
				try {
					resultSet = statement.getGeneratedKeys();
					if (resultSet.next()) {
						teacher.setId(resultSet.getLong("id"));
						log.info("Teacher was created");
					}
				} catch (SQLException e) {
					log.error("ERROR. ResultSet was not created", e);
				}
			} catch (SQLException e) {
				log.fatal("Teacher was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
		} else {
			log.fatal("Teacher is already exist");
		}
	}

	public Set<Teacher> getCourseTeachers(long id) {
		log.info("Get course teachers");
		Set<Teacher> teachers = new HashSet<Teacher>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_COURSE_TEACHERS);
			statement.setLong(1, id);
			log.info("statement was created");
			try {
				resultSet = statement.executeQuery();
				log.info("resultSet was created");
				while (resultSet.next()) {
					String name = resultSet.getString("name");
					Date dayOfBirth = resultSet.getDate("dayofbirth");
					Teacher teacher = new Teacher(name, dayOfBirth);
					teacher.setId(resultSet.getLong("id"));
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
		} else {
			log.info("List of course teachers was created");
		}
		return teachers;
	}

}
