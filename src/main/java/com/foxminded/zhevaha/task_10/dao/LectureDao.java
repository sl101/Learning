package com.foxminded.zhevaha.task_10.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.foxminded.zhevaha.task_10.domain.AcademicPlan;
import com.foxminded.zhevaha.task_10.domain.Course;
import com.foxminded.zhevaha.task_10.domain.Group;
import com.foxminded.zhevaha.task_10.domain.Lecture;

public class LectureDao implements GenericDao<Lecture, Long> {

	private static final Logger log = Logger.getLogger(LectureDao.class);
	private final String CREATE = "INSERT INTO Lectures (group_id, course_id, topic, academic_plan_id) VALUES (?,?,?,?) ON CONFLICT (group_id, course_id, topic) DO UPDATE SET group_id = excluded.group_id, course_id = excluded.course_id, topic = excluded.topic;";
	private final String GET_ALL = "SELECT * FROM Lectures;";
	private final String GET_PLANNED_LECTURES = "SELECT * FROM Lectures WHERE academic_plan_id = ?;";
	private final String GET_BY_ID = "SELECT * FROM Lectures WHERE id = ?;";
	private final String UPDATE = "UPDATE Lectures SET group_id = ?, course_id =?, topic = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM Lectures WHERE id = ?;";

	public Set<Lecture> getAll() {
		log.info("Find all lectures in database");
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
					long groupID = resultSet.getLong("group_id");
					long courseID = resultSet.getLong("course_id");
					String topic = resultSet.getString("topic");
					Group group = new GroupDao().getById(groupID);
					Course course = new CourseDao().getById(courseID);
					Lecture lecture = new Lecture(group, course, topic);
					lecture.setId(resultSet.getLong("id"));
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
			log.fatal("There were no registered lectures. The list is empty");
		} else {
			log.info("Lectures list was created");
		}
		return lectures;
	}

	public Lecture getById(Long id) {
		log.info("Find Lecture by ID");
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
					Group group = new GroupDao().getById(resultSet.getLong("group_id"));
					Course course = new CourseDao().getById(resultSet.getLong("course_id"));
					String topic = resultSet.getString("topic");
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
			lecture = getById(lecture.getId());
			return lecture;
		} else {
			log.info("Lecture was not created");
			return null;
		}

	}

	public void delete(Lecture lecture) {
		log.info("Delete Lecture");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
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

	@Deprecated
	public void create(Lecture lecture) {
		log.info("Method deprecated");
		throw new RuntimeException("Method deprecated");
	}

	public void create(Lecture lecture, AcademicPlan academicPlan) {
		log.info("Create planed Lecture");
		if (academicPlan.getId() != 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
				statement.setLong(1, lecture.getGroup().getId());
				statement.setLong(2, lecture.getCourse().getId());
				statement.setString(3, lecture.getLectureTopic());
				statement.setLong(4, academicPlan.getId());
				statement.executeUpdate();
				log.info("statement was created");
				try {
					resultSet = statement.getGeneratedKeys();
					if (resultSet.next()) {
						lecture.setId(resultSet.getLong("id"));
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
		} else {
			log.fatal("AcademicPlan is not exist");
		}
	}

	public Set<Lecture> getPlannedLectures(Long id) {
		log.info("Find planned lectures");
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
					long groupID = resultSet.getLong("group_id");
					long courseID = resultSet.getLong("course_id");
					String topic = resultSet.getString("topic");
					Group group = new GroupDao().getById(groupID);
					Course course = new CourseDao().getById(courseID);
					Lecture lecture = new Lecture(group, course, topic);
					lecture.setId(resultSet.getLong("id"));
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
