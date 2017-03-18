package com.foxminded.zhevaha.task_10.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.foxminded.zhevaha.task_10.domain.Course;
import com.foxminded.zhevaha.task_10.domain.Group;
import com.foxminded.zhevaha.task_10.domain.Lecture;

public class LectureDao implements DaoFactory<Lecture, Long> {

	private static final Logger log = Logger.getLogger(LectureDao.class);
	private final String GET_ALL = "SELECT * FROM Lectures;";
	private final String GET_PLANNED_LECTURES = "SELECT * FROM lectures WHERE id IN (SELECT lectures_id FROM academic_plans_lectures WHERE academic_plan_id = ?);";
	private final String GET_BY_ID = "SELECT * FROM Lectures WHERE id = ?;";
	private final String UPDATE = "UPDATE Lectures SET group_id = ?, course_id =?, topic = ? WHERE id = ?;";
	private final String CREATE_ENTITY = "INSERT INTO Lectures (group_id, course_id, topic) VALUES (?,?,?) ON CONFLICT (group_id, course_id, topic) DO UPDATE SET group_id = excluded.group_id, course_id = excluded.course_id, topic = excluded.topic;";
	private final String DELETE_ENTITY = "DELETE FROM Lectures WHERE id = ?;";

	public Set<Lecture> getAll() {
		log.info("Find lectures in database");
		Set<Lecture> lectures = new HashSet<Lecture>();
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
					long groupID = resultSet.getLong(2);
					long courseID = resultSet.getLong(3);
					String topic = resultSet.getString(4);
					Group group = new GroupDao().getEntityById(groupID);
					Course course = new CourseDao().getEntityById(courseID);
					Lecture lecture = new Lecture(group, course, topic);
					lecture.setId(resultSet.getLong(1));
					lectures.add(lecture);
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
		if (lectures.isEmpty()) {
			log.fatal("There were no registered lectures\nThe list is empty");
		} else {
			log.info("Lectures list was created");
		}
		return lectures;
	}

	public Lecture getEntityById(Long id) {
		log.info("Find lecture by ID");
		Lecture lecture = null;
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
					Group group = new GroupDao().getEntityById(resultSet.getLong(2));
					Course course = new CourseDao().getEntityById(resultSet.getLong(3));
					String topic = resultSet.getString(4);
					lecture = new Lecture(group, course, topic);
					lecture.setId(id);
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
		return lecture;
	}

	public Lecture update(Lecture lecture) {
		log.info("Update Lecture");
		if (lecture.getId() != 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(UPDATE);
				statement.setLong(1, lecture.getGroup().getId());
				statement.setLong(2, lecture.getCourse().getId());
				statement.setString(3, lecture.getLectureTopic());
				statement.setLong(4, lecture.getId());
				statement.executeUpdate();
				log.info("statement was created");
			} catch (SQLException e) {
				log.error("ERROR. Statement was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
			lecture = getEntityById(lecture.getId());
			return lecture;
		} else {
			log.info("Lecture was not created");
			return null;
		}

	}

	public void delete(Lecture lecture) {
		log.info("Delete lecture");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE_ENTITY);
			statement.setLong(1, lecture.getId());
			statement.executeUpdate();
			log.info("statement was created");
			log.info("Lecture was deleted");
		} catch (SQLException e) {
			log.fatal("Lecture was not deleted", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}

	public void create(Lecture lecture) {
		log.info("Create lecture");
		if (lecture.getId() == 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(CREATE_ENTITY, Statement.RETURN_GENERATED_KEYS);
				statement.setLong(1, lecture.getGroup().getId());
				statement.setLong(2, lecture.getCourse().getId());
				statement.setString(3, lecture.getLectureTopic());
				statement.executeUpdate();
				log.info("statement was created");
				try {
					resultSet = statement.getGeneratedKeys();
					if (resultSet.next()) {
						lecture.setId(resultSet.getLong(1));

						log.info("Lecture was created");
					}
				} catch (SQLException e) {
					log.error("ERROR. ResultSet was not created", e);
				}
			} catch (SQLException e) {
				log.fatal("Lecture was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
		}
		log.fatal("Lecture is already exist");
	}

	public Set<Lecture> getPlanedLectures(Long id) {
		log.info("Find lectures in Academic Plan");
		Set<Lecture> lectures = new HashSet<Lecture>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_PLANNED_LECTURES);
			statement.setLong(1, id);
			log.info("statement was created");
			try {
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					long groupID = resultSet.getLong(2);
					long courseID = resultSet.getLong(3);
					String topic = resultSet.getString(4);
					Group group = new GroupDao().getEntityById(groupID);
					Course course = new CourseDao().getEntityById(courseID);
					Lecture lecture = new Lecture(group, course, topic);
					lecture.setId(resultSet.getLong(1));
					lectures.add(lecture);
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
		if (lectures.isEmpty()) {
			log.fatal("There were no registered lectures\nThe list is empty");
		} else {
			log.info("Planned lectures list was created");
		}
		return lectures;
	}
}
