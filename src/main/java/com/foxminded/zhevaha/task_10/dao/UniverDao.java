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

	private static final Logger log = Logger.getLogger(UniverDao.class);
	private final String CREATE = "INSERT INTO Univers (name) VALUES (?) ON CONFLICT (name) DO UPDATE SET name = excluded.name";
	private final String GET_ALL = "SELECT * FROM Univers;";
	private final String GET_BY_ID = "SELECT * FROM Univers WHERE id = ?;";
	private final String UPDATE = "UPDATE Univers SET name = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM Univers WHERE id = ?;";

	public Set<Univer> getAll() {
		log.info("Find univers in date base");
		Set<Univer> univers = new HashSet<Univer>();
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
					Univer univer = new Univer(name);
					long id = resultSet.getLong(1);
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
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (univers.isEmpty()) {
			log.fatal("There were no registered university\nThe list is empty");
		} else {
			log.info("Universities list was created");
		}
		return univers;
	}

	public Univer getById(Long id) {
		log.info("Find univer by ID");
		if (id != 0) {
			Univer univer = null;
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
						String name = resultSet.getString(2);
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
			return univer;
		}
		log.info("Entity's ID is null");
		return null;
	}

	public Univer update(Univer univer) {
		log.info("\tUpdate Univer");
		if (univer.getId() != 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(UPDATE);
				statement.setString(1, univer.getName());
				statement.setLong(2, univer.getId());
				statement.executeUpdate();
				log.info("statement was created");
			} catch (SQLException e) {
				log.error("ERROR. Statement was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
			univer = getById(univer.getId());
			log.info("\tUniver was updated");
			return univer;
		} else {
			log.info("Univer was not created");
			return null;
		}

	}

	public void delete(Univer univer) {
		log.info("Delete univer");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, univer.getId());
			statement.executeUpdate();
			log.info("statement was created");
			log.info("Univer was deleted");
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
			log.fatal("Univer was not deleted", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}

	public void create(Univer univer) {
		log.info("\tCreate univer");
		if (univer.getId() == 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, univer.getName());
				statement.executeUpdate();
				log.info("statement was created");
				try {
					resultSet = statement.getGeneratedKeys();
					if (resultSet.next()) {
						log.info("resultSet get generated key");
						univer.setId(resultSet.getLong(1));
						log.info("\tUniver was created");
					}
				} catch (SQLException e) {
					log.error("ERROR. ResultSet was not created", e);
				}
			} catch (SQLException e) {
				log.error("ERROR. Statement was not created", e);
				log.error("Univer was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
		}
		log.info("\tUniver is already exist");
	}
}
