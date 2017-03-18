package com.foxminded.zhevaha.task_10.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.foxminded.zhevaha.task_10.domain.Course;
import com.foxminded.zhevaha.task_10.domain.Group;
import com.foxminded.zhevaha.task_10.domain.Student;

public class GroupDao implements DaoFactory<Group, Long> {

	private static final Logger log = Logger.getLogger(GroupDao.class);
	private final String CREATE_ENTITY = "INSERT INTO Groups (name) VALUES (?) ON CONFLICT (name) DO UPDATE SET name = excluded.name;";
	private final String GET_ALL = "SELECT * FROM Groups;";
	private final String GET_BY_ID = "SELECT * FROM Groups WHERE id = ?;";
	private final String UPDATE = "UPDATE Groups SET name = ? WHERE id = ?;";
	private final String DELETE_ENTITY = "DELETE FROM Groups WHERE id = ?;";

	public Set<Group> getAll() {
		log.info("Find groups in database");
		Set<Group> groups = new HashSet<Group>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_ALL);
			log.info("statement was created");
			try {
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					String name = resultSet.getString(2);
					Group group = new Group(name);
					long id = resultSet.getLong(1);
					group.setId(id);
					Set<Student> students = new StudentDao().getGroupStudets(id);
					Iterator<Student> iteratorStudents = students.iterator();
					while (iteratorStudents.hasNext()) {
						group.addStudent(iteratorStudents.next());
					}
					Set<Course> groupCourses = new CourseDao().getGroupCourses(id);
					Iterator<Course> iteratorGroupCourses = groupCourses.iterator();
					while (iteratorGroupCourses.hasNext()) {
						group.addCourse(iteratorGroupCourses.next());
					}
					groups.add(group);
				}
				log.info("resultSet was created");
			} catch (SQLException e) {
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (groups.isEmpty()) {
			log.fatal("There were no registered groups\nThe list is empty");
		} else {
			log.info("Groups list was created");
		}
		return groups;
	}

	public Group getEntityById(Long id) {
		log.info("Find group by ID");
		Group group = null;
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
					group = new Group(name);
					group.setId(id);
					Set<Student> students = new StudentDao().getGroupStudets(id);
					Iterator<Student> iteratorStudents = students.iterator();
					while (iteratorStudents.hasNext()) {
						group.addStudent(iteratorStudents.next());
					}
					Set<Course> groupCourses = new CourseDao().getGroupCourses(id);
					Iterator<Course> iteratorGroupCourses = groupCourses.iterator();
					while (iteratorGroupCourses.hasNext()) {
						group.addCourse(iteratorGroupCourses.next());
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
		return group;
	}

	public Group update(Group group) {
		log.info("Update Group");
		if (group.getId() != 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();

			try {
				statement = connection.prepareStatement(UPDATE);
				statement.setString(1, group.getName());
				statement.setLong(2, group.getId());
				statement.executeUpdate();
				log.info("statement was created");
			} catch (SQLException e) {
				log.error("ERROR. Statement was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
			group = getEntityById(group.getId());
			return group;
		} else {
			log.info("Group was not created");
			return null;
		}
	}

	public void delete(Group group) {
		log.info("Delete group");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE_ENTITY);
			statement.setLong(1, group.getId());
			statement.executeUpdate();
			log.info("statement was created");
			log.info("Group was deleted");
		} catch (SQLException e) {
			log.fatal("Group was not deleted", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}

	public void create(Group group) {
		log.info("Create group");
		if (group.getId() == 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(CREATE_ENTITY, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, group.getName());
				statement.executeUpdate();
				log.info("statement was created");
				try {
					resultSet = statement.getGeneratedKeys();
					if (resultSet.next()) {
						group.setId(resultSet.getLong(1));
						log.info("Group was created");
					}

				} catch (SQLException e) {
					log.error("ERROR. ResultSet was not created", e);
				}
			} catch (SQLException e) {
				log.fatal("Group was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
		}
		log.fatal("Group is already exist");
	}

}
