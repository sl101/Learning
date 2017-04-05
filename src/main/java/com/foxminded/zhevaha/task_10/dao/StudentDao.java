package com.foxminded.zhevaha.task_10.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.foxminded.zhevaha.task_10.domain.Group;
import com.foxminded.zhevaha.task_10.domain.Student;

public class StudentDao implements GenericDao<Student, Long> {

	private static final Logger log = Logger.getLogger(StudentDao.class);
	private final String CREATE = "INSERT INTO Students (name, dayOfBirth) VALUES (?, ?);";
	private final String GET_ALL = "SELECT * FROM Students;";
	private final String GET_BY_ID = "SELECT * FROM Students WHERE id = ?;";
	private final String UPDATE = "UPDATE Students SET name = ?, dayOfBirth = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM Students WHERE id = ?;";
	private final String ENROLL_STUDENT = "UPDATE Students set group_id = ? WHERE id = ?";
	private final String GET_GROUP_STUDENTS = "SELECT * FROM Students WHERE group_id = ?";

	public Set<Student> getAll() throws DaoException {
		Set<Student> students = new HashSet<Student>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_ALL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				Date dayOfBirth = resultSet.getDate("dayofbirth");
				Student student = new Student(name, dayOfBirth);
				student.setId(resultSet.getLong("id"));
				students.add(student);
			}
		} catch (SQLException e) {
			log.error("Problem get data", e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return students;
	}

	public Student getById(Long id) throws DaoException {
		Student student = null;
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
			student = new Student(name, dayOfBirth);
			student.setId(id);
		} catch (SQLException e) {
			log.error("Problem get data", e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return student;
	}

	public Student update(Student student) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(UPDATE);
			statement.setString(1, student.getName());
			statement.setString(2, student.getDayOfBirth().toString());
			statement.setLong(3, student.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("Problem get data", e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
		student = getById(student.getId());
		return student;

	}

	public void delete(Student student) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, student.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("Problem get data", e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void create(Student student) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, student.getName());
			statement.setDate(2, (java.sql.Date) student.getDayOfBirth());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			student.setId(resultSet.getLong("id"));
		} catch (SQLException e) {
			log.error("Problem get data", e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}

	public Set<Student> getGroupStudets(Long id) throws DaoException {
		Set<Student> students = new HashSet<Student>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_GROUP_STUDENTS);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				Date dayOfBirth = resultSet.getDate("dayofbirth");
				Student student = new Student(name, dayOfBirth);
				student.setId(resultSet.getLong("id"));
				students.add(student);
			}
		} catch (SQLException e) {
			log.error("Problem get data", e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return students;
	}

	public void enroll(Student student, Group group) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(ENROLL_STUDENT);
			statement.setLong(1, group.getId());
			statement.setLong(2, student.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("Problem get data", e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}
}
