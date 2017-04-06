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
import com.foxminded.zhevaha.task_10.domain.Room;
import com.foxminded.zhevaha.task_10.domain.SchedulePosition;
import com.foxminded.zhevaha.task_10.domain.Student;
import com.foxminded.zhevaha.task_10.domain.Teacher;
import com.foxminded.zhevaha.task_10.domain.Univer;

public class UniverDao implements GenericDao<Univer, Long> {

	private final String CREATE = "INSERT INTO Univers (name) VALUES (?) ON CONFLICT (name) DO UPDATE SET name = excluded.name";
	private final String GET_ALL = "SELECT * FROM Univers;";
	private final String GET_BY_ID = "SELECT * FROM Univers WHERE id = ?;";
	private final String UPDATE = "UPDATE Univers SET name = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM Univers WHERE id = ?;";

	public Set<Univer> getAll() throws DaoException {
		Set<Univer> univers = new HashSet<Univer>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_ALL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				Univer univer = new Univer(name);
				long id = resultSet.getLong("id");
				univer.setId(id);
				Set<Teacher> teachers = new TeacherDao().getAll();
				Iterator<Teacher> iteratorTeachers = teachers.iterator();
				while (iteratorTeachers.hasNext()) {
					univer.addTeacher(iteratorTeachers.next());
				}
				Set<Student> students = new StudentDao().getAll();
				Iterator<Student> iteratorStudents = students.iterator();
				while (iteratorStudents.hasNext()) {
					univer.addStudent(iteratorStudents.next());
				}
				Set<Group> groups = new GroupDao().getAll();
				Iterator<Group> iteratorGroups = groups.iterator();
				while (iteratorGroups.hasNext()) {
					univer.addGroup(iteratorGroups.next());
				}
				Set<Course> courses = new CourseDao().getAll();
				Iterator<Course> iteratorCourses = courses.iterator();
				while (iteratorCourses.hasNext()) {
					univer.addCourse(iteratorCourses.next());
				}
				Set<Room> rooms = new RoomDao().getAll();
				Iterator<Room> iteratorRooms = rooms.iterator();
				while (iteratorRooms.hasNext()) {
					univer.addRoom(iteratorRooms.next());
				}
				Set<SchedulePosition> schedule = new SchedulePositionDao().getAll();
				Iterator<SchedulePosition> iteratorSchedule = schedule.iterator();
				while (iteratorSchedule.hasNext()) {
					univer.addSchedulePosition(iteratorSchedule.next());
				}
				univers.add(univer);
			}
		} catch (SQLException e) {
			addLogError(e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return univers;
	}

	public Univer getById(Long id) throws DaoException {
		Univer univer = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_BY_ID);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			String name = resultSet.getString("name");
			univer = new Univer(name);
			univer.setId(id);
			Set<Teacher> teachers = new TeacherDao().getAll();
			Iterator<Teacher> iteratorTeachers = teachers.iterator();
			while (iteratorTeachers.hasNext()) {
				univer.addTeacher(iteratorTeachers.next());
			}
			Set<Student> students = new StudentDao().getAll();
			Iterator<Student> iteratorStudents = students.iterator();
			while (iteratorStudents.hasNext()) {
				univer.addStudent(iteratorStudents.next());
			}
			Set<Group> groups = new GroupDao().getAll();
			Iterator<Group> iteratorGroups = groups.iterator();
			while (iteratorGroups.hasNext()) {
				univer.addGroup(iteratorGroups.next());
			}
			Set<Course> courses = new CourseDao().getAll();
			Iterator<Course> iteratorCourses = courses.iterator();
			while (iteratorCourses.hasNext()) {
				univer.addCourse(iteratorCourses.next());
			}
			Set<Room> rooms = new RoomDao().getAll();
			Iterator<Room> iteratorRooms = rooms.iterator();
			while (iteratorRooms.hasNext()) {
				univer.addRoom(iteratorRooms.next());
			}
			Set<SchedulePosition> schedule = new SchedulePositionDao().getAll();
			Iterator<SchedulePosition> iteratorSchedule = schedule.iterator();
			while (iteratorSchedule.hasNext()) {
				univer.addSchedulePosition(iteratorSchedule.next());
			}
		} catch (SQLException e) {
			addLogError(e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return univer;
	}

	public Univer update(Univer univer) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(UPDATE);
			statement.setString(1, univer.getName());
			statement.setLong(2, univer.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			addLogError(e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
		univer = getById(univer.getId());
		return univer;
	}

	public void delete(Univer univer) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, univer.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			addLogError(e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void create(Univer univer) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, univer.getName());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			univer.setId(resultSet.getLong("id"));
		} catch (SQLException e) {
			addLogError(e);
			throw new DaoException(e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}

	private static void addLogError(Exception e) {
		Logger log = Logger.getLogger(UniverDao.class);
		log.error("Problem with data base", e);
	}
}
