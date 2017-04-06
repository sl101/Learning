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

	private final String CREATE = "INSERT INTO Groups (name) VALUES (?) ON CONFLICT (name) DO UPDATE SET name = excluded.name;";
	private final String GET_ALL = "SELECT * FROM Groups;";
	private final String GET_BY_ID = "SELECT * FROM Groups WHERE id = ?;";
	private final String UPDATE = "UPDATE Groups SET name = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM Groups WHERE id = ?;";

	public Set<Group> getAll() throws DaoException {
		Set<Group> groups = new HashSet<Group>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_ALL);
			resultSet = statement.executeQuery();
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
			addLogError(e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return groups;
	}

	public Group getById(Long id) throws DaoException {
		Group group = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_BY_ID);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
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
		} catch (SQLException e) {
			addLogError(e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return group;
	}

	public Group update(Group group) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(UPDATE);
			statement.setString(1, group.getName());
			statement.setLong(2, group.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			addLogError(e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
		group = getById(group.getId());
		return group;
	}

	public void delete(Group group) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, group.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			addLogError(e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void create(Group group) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, group.getName());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			group.setId(resultSet.getLong("id"));
		} catch (SQLException e) {
			addLogError(e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}

	private static void addLogError(Exception e) {
		Logger log = Logger.getLogger(GroupDao.class);
		log.error("Problem with data base", e);
	}
}
