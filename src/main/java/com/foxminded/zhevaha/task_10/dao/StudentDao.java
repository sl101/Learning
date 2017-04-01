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
		log.info("Get all students");
		Set<Student> students = new HashSet<Student>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_ALL);
			log.info("Create statement");
			resultSet = statement.executeQuery();
			log.info("Create resultSet");
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				Date dayOfBirth = resultSet.getDate("dayofbirth");
				Student student = new Student(name, dayOfBirth);
				student.setId(resultSet.getLong("id"));
				students.add(student);
			}
		} catch (SQLException e) {
			log.error("Stidents list was not got: - " + e.getMessage());
			throw new DaoException(StudentDao.class.getName() + ": - students list was not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (students.isEmpty()) {
			log.fatal("There were no registered students. The list is empty");
		} else {
			log.info("Students list was created");
		}
		return students;
	}

	public Student getById(Long id) throws DaoException {
		log.info("Get student by ID");
		Student student = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_BY_ID);
			statement.setLong(1, id);
			log.info("Create statement");
			resultSet = statement.executeQuery();
			log.info("Create resultSet");
			if (resultSet.next()) {
				String name = resultSet.getString("name");
				Date dayOfBirth = resultSet.getDate("dayofbirth");
				student = new Student(name, dayOfBirth);
				student.setId(id);
			} else {
				log.info("resultSet has not data");
			}
		} catch (SQLException e) {
			log.error("Student was not got: - " + e.getMessage());
			throw new DaoException(StudentDao.class.getName() + ": - student was not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return student;
	}

	public Student update(Student student) throws DaoException {
		log.info("Update Student");
		if (student.getId() != 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(UPDATE);
				statement.setString(1, student.getName());
				statement.setString(2, student.getDayOfBirth().toString());
				statement.setLong(3, student.getId());
				statement.executeUpdate();
				log.info("Create statement");
			} catch (SQLException e) {
				log.error("Student was not updated: - " + e.getMessage());
				throw new DaoException(StudentDao.class.getName() + ": - student was not updated due to " + e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement);
			}
			student = getById(student.getId());
			return student;
		} else {
			log.info("Student is not existed");
			return null;
		}

	}

	public void delete(Student student) throws DaoException {
		log.info("Delete student");
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, student.getId());
			statement.executeUpdate();
			log.info("Create statement");
			log.info("Student was deleted");
		} catch (SQLException e) {
			log.error("Student was not deleted: - " + e.getMessage());
			throw new DaoException(StudentDao.class.getName() + ": - student was not deleted due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void create(Student student) throws DaoException {
		log.info("Create student");
		if (student.getId() == 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, student.getName());
				statement.setDate(2, (java.sql.Date) student.getDayOfBirth());
				statement.executeUpdate();
				log.info("Create statement");
				resultSet = statement.getGeneratedKeys();
				log.info("Create resultSet");
				if (resultSet.next()) {
					student.setId(resultSet.getLong("id"));
					log.info("Student was created");
				}
			} catch (SQLException e) {
				log.error("Student was not created: - " + e.getMessage());
				throw new DaoException(StudentDao.class.getName() + ": - student was not created due to " + e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
		} else {
			log.fatal("Student is already exist");
		}
	}

	public Set<Student> getGroupStudets(Long id) throws DaoException {
		log.info("Get studens of group");
		Set<Student> students = new HashSet<Student>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_GROUP_STUDENTS);
			statement.setLong(1, id);
			log.info("Create statement");
			resultSet = statement.executeQuery();
			log.info("Create resultSet");
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				Date dayOfBirth = resultSet.getDate("dayofbirth");
				Student student = new Student(name, dayOfBirth);
				student.setId(resultSet.getLong("id"));
				students.add(student);
			}

		} catch (SQLException e) {
			log.error("Group students list was not got: - " + e.getMessage());
			throw new DaoException(StudentDao.class.getName() + ": - group students list was not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (students.isEmpty()) {
			log.fatal("This group has not registered students. The list is empty");
		} else {
			log.info("Group students list was created");
		}
		return students;
	}

	public void enroll(Student student, Group group) throws DaoException {
		log.info("Enroll student in group");
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(ENROLL_STUDENT);
			statement.setLong(1, group.getId());
			statement.setLong(2, student.getId());
			statement.executeUpdate();
			log.info("Create statement");
		} catch (SQLException e) {
			log.error("Student was not enrolled: - " + e.getMessage());
			throw new DaoException(StudentDao.class.getName() + ": - student was not enrolled due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}
}
