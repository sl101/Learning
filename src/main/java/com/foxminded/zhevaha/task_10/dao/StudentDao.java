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

public class StudentDao implements DaoFactory<Student, Long> {

	private static final Logger log = Logger.getLogger(StudentDao.class);
	private final String CREATE_ENTITY = "INSERT INTO Students (name, dayOfBirth) VALUES (?, ?);";
	private final String GET_ALL = "SELECT * FROM Students;";
	private final String GET_BY_ID = "SELECT * FROM Students WHERE id = ?;";
	private final String UPDATE = "UPDATE Students SET name = ?, dayOfBirth = ? WHERE id = ?;";
	private final String DELETE_ENTITY = "DELETE FROM Students WHERE id = ?;";
	private final String ENROLL_STUDENT = "UPDATE Students set group_id = ? WHERE id = ?";
	private final String GET_GROUP_STUDENTS = "SELECT * FROM Students WHERE group_id = ?";

	public Set<Student> getAll() {
		log.info("Find students in date base");
		Set<Student> students = new HashSet<Student>();
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
					String name = resultSet.getString(2);
					Date dayOfBirth = resultSet.getDate(3);
					Student student = new Student(name, dayOfBirth);
					student.setId(resultSet.getLong(1));
					students.add(student);
				}
			} catch (SQLException e) {
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (students.isEmpty()) {
			log.fatal("There were no registered students\nThe list is empty");
		} else {
			log.info("Students list was created");
		}
		return students;
	}

	public Student getEntityById(Long id) {
		log.info("Find student by ID");
		Student student = null;
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
				log.info("resultSet was created");
				if (resultSet.next()) {
					String name = resultSet.getString(2);
					Date dayOfBirth = resultSet.getDate(3);
					student = new Student(name, dayOfBirth);
					student.setId(id);
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
		return student;
	}

	public Student update(Student student) {
		log.info("Update Student");
		if (student.getId() != 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(UPDATE);
				statement.setString(1, student.getName());
				statement.setString(2, student.getDayOfBirth().toString());
				statement.setLong(3, student.getId());
				statement.executeUpdate();
				log.info("statement was created");
			} catch (SQLException e) {
				log.error("ERROR. Statement was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
			student = getEntityById(student.getId());
			return student;
		} else {
			log.info("Student was not created");
			return null;
		}

	}

	public void delete(Student student) {
		log.info("Delete student");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE_ENTITY);
			statement.setLong(1, student.getId());
			statement.executeUpdate();
			log.info("statement was created");
			log.info("Student was deleted");
		} catch (SQLException e) {
			log.fatal("Student was not deleted", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}

	public void create(Student student) {
		log.info("Create student");
		if (student.getId() == 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(CREATE_ENTITY, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, student.getName());
				statement.setDate(2, (java.sql.Date) student.getDayOfBirth());
				statement.executeUpdate();
				log.info("statement was created");
				try {
					resultSet = statement.getGeneratedKeys();
					if (resultSet.next()) {
						log.info("resultSet get generated key");
						student.setId(resultSet.getLong(1));
						log.info("Student was created");
					}
				} catch (SQLException e) {
					log.error("ERROR. ResultSet was not created", e);
				}
			} catch (SQLException e) {
				log.fatal("Student was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
		}
		log.fatal("Student is already exist");
	}

	public Set<Student> getGroupStudets(Long id) {
		log.info("Get studens of group");
		Set<Student> students = new HashSet<Student>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_GROUP_STUDENTS);
			statement.setLong(1, id);
			log.info("statement was created");
			try {
				resultSet = statement.executeQuery();
				log.info("resultSet was created");
				while (resultSet.next()) {
					String name = resultSet.getString(2);
					Date dayOfBirth = resultSet.getDate(3);
					Student student = new Student(name, dayOfBirth);
					student.setId(resultSet.getLong(1));
					students.add(student);
				}
			} catch (SQLException e) {
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return students;
	}

	public void enroll(Student student, Group group) {
		log.info("Enroll student in group in database");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(ENROLL_STUDENT);
			statement.setLong(1, group.getId());
			statement.setLong(2, student.getId());
			statement.executeUpdate();
			log.info("statement was created");
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}
}
