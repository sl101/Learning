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

public class GroupDao implements GenericDao<Group, Long> {

	private static final Logger log = Logger.getLogger(GroupDao.class);
	private final String CREATE = "INSERT INTO Groups (name) VALUES (?) ON CONFLICT (name) DO UPDATE SET name = excluded.name;";
	private final String GET_ALL = "SELECT * FROM Groups;";
	private final String GET_BY_ID = "SELECT * FROM Groups WHERE id = ?;";
	private final String UPDATE = "UPDATE Groups SET name = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM Groups WHERE id = ?;";

	public Set<Group> getAll() throws DaoException {
		log.info("Get all groups");
		Set<Group> groups = new HashSet<Group>();
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
				Group group = new Group(name);
				long id = resultSet.getLong("id");
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
		} catch (SQLException e) {
			log.error("Groups list was not got: - " + e.getMessage());
			throw new DaoException(GroupDao.class.getName() + ": - groups list was not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (groups.isEmpty()) {
			log.fatal("There were no registered groups. The list is empty");
		} else {
			log.info("Groups list was created");
		}
		return groups;
	}

	public Group getById(Long id) throws DaoException {
		log.info("Find group by ID");
		Group group = null;
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
			log.error("Group was not got: - " + e.getMessage());
			throw new DaoException(GroupDao.class.getName() + ": - group was not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return group;
	}

	public Group update(Group group) throws DaoException {
		log.info("Update group");
		if (group.getId() != 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			connection = ConnectionFactory.getConnection();

			try {
				statement = connection.prepareStatement(UPDATE);
				statement.setString(1, group.getName());
				statement.setLong(2, group.getId());
				statement.executeUpdate();
				log.info("Create statement");
			} catch (SQLException e) {
				log.error("Group was not updated: - " + e.getMessage());
				throw new DaoException(GroupDao.class.getName() + ": - group was not updated due to " + e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement);
			}
			group = getById(group.getId());
			return group;
		} else {
			log.info("Group is not existed");
			return null;
		}
	}

	public void delete(Group group) throws DaoException {
		log.info("Delete group");
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, group.getId());
			statement.executeUpdate();
			log.info("Create statement");
			log.info("Group was deleted");
		} catch (SQLException e) {
			log.error("Group was not deleted: - " + e.getMessage());
			throw new DaoException(GroupDao.class.getName() + ": - group was not deleted due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void create(Group group) throws DaoException {
		log.info("Create group");
		if (group.getId() == 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, group.getName());
				statement.executeUpdate();
				log.info("Create statement");
				resultSet = statement.getGeneratedKeys();
				log.info("Create resultSet");
				if (resultSet.next()) {
					group.setId(resultSet.getLong("id"));
					log.info("Group was created");
				}
			} catch (SQLException e) {
				log.error("Group was not created: - " + e.getMessage());
				throw new DaoException(GroupDao.class.getName() + ": - group was not ctreated due to " + e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
		} else {
			log.fatal("Group is already exist");
		}
	}

}
