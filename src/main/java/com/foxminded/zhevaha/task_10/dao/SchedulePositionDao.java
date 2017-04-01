package com.foxminded.zhevaha.task_10.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.foxminded.zhevaha.task_10.domain.Lecture;
import com.foxminded.zhevaha.task_10.domain.Room;
import com.foxminded.zhevaha.task_10.domain.SchedulePosition;
import com.foxminded.zhevaha.task_10.domain.Teacher;

public class SchedulePositionDao implements GenericDao<SchedulePosition, Long> {

	private static final Logger log = Logger.getLogger(SchedulePositionDao.class);
	private final String CREATE = "INSERT INTO schedule_position (lecture_id, room_id, lecturetime, teacher_id) VALUES (?, ?, ?, ?);";
	private final String GET_ALL = "SELECT * FROM schedule_position;";
	private final String GET_BY_ID = "SELECT * FROM schedule_position WHERE id = ?;";
	private final String UPDATE = "UPDATE schedule_position SET lecture_id = ?, room_id = ?, lecturetime = ?, teacher_id = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM schedule_position WHERE id = ?;";

	public Set<SchedulePosition> getAll() throws DaoException {
		log.info("Get all schedulePositions");
		Set<SchedulePosition> schedule = new HashSet<SchedulePosition>();
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
				long id = resultSet.getLong("id");
				Lecture lecture = new LectureDao().getById(resultSet.getLong("lecture_id"));
				Room room = new RoomDao().getById(resultSet.getLong("room_id"));
				java.sql.Timestamp lectureTime = resultSet.getTimestamp("lecturetime");
				Teacher teacher = new TeacherDao().getById(resultSet.getLong("teacher_id"));
				SchedulePosition schedulePosition = new SchedulePosition(lecture, room, lectureTime, teacher);
				schedulePosition.setId(id);
				schedule.add(schedulePosition);
			}
		} catch (SQLException e) {
			log.error("Schedule was not got: - " + e.getMessage());
			throw new DaoException(SchedulePositionDao.class.getName() + ": - schedule was not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (schedule.isEmpty()) {
			log.fatal("There were no schedule. The list is empty");
		} else {
			log.info("Schedule was created");
		}
		return schedule;
	}

	public SchedulePosition getById(Long id) throws DaoException {
		log.info("Get schedulePosition by ID");
		SchedulePosition schedulePosition = null;
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
				Lecture lecture = new LectureDao().getById(resultSet.getLong("lecture_id"));
				Room room = new RoomDao().getById(resultSet.getLong("room_id"));
				java.sql.Timestamp lectureTime = resultSet.getTimestamp("lecturetime");
				Teacher teacher = new TeacherDao().getById(resultSet.getLong("teacher_id"));
				schedulePosition = new SchedulePosition(lecture, room, lectureTime, teacher);
				schedulePosition.setId(id);
			} else {
				log.info("resultSet has not data");
			}
		} catch (SQLException e) {
			log.error("SchedulePOsition was not got: - " + e.getMessage());
			throw new DaoException(
					SchedulePositionDao.class.getName() + ": - schedulePosition was not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return schedulePosition;
	}

	public SchedulePosition update(SchedulePosition schedulePosition) throws DaoException {
		log.info("Update SchedulePosition");
		if (schedulePosition.getId() != 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(UPDATE);
				statement.setLong(1, schedulePosition.getLecture().getId());
				statement.setLong(2, schedulePosition.getRoom().getId());
				statement.setLong(4, schedulePosition.getTeacher().getId());
				statement.setLong(3, schedulePosition.getId());
				statement.executeUpdate();
				log.info("Create statement");
			} catch (SQLException e) {
				log.error("SchedulePosition was not updated: - " + e.getMessage());
				throw new DaoException(
						SchedulePositionDao.class.getName() + ": - schedulePosition was not got updated to " + e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement);
			}
			schedulePosition = getById(schedulePosition.getId());
			return schedulePosition;
		} else {
			log.info("SchedulePosition is not existed");
			return null;
		}
	}

	public void delete(SchedulePosition schedulePosition) throws DaoException {
		log.info("Delete schedulePosition");
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, schedulePosition.getId());
			statement.executeUpdate();
			log.info("Create statement");
			log.info("SchedulePosition was deleted");
		} catch (SQLException e) {
			log.error("SchedulePosition was not deleted: - " + e.getMessage());
			throw new DaoException(
					SchedulePositionDao.class.getName() + ": - schedulePosition was not got deleted to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void create(SchedulePosition schedulePosition) throws DaoException {
		log.info("Create schedulePosition");
		if (schedulePosition.getId() == 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
				statement.setLong(1, schedulePosition.getLecture().getId());
				statement.setLong(2, schedulePosition.getRoom().getId());
				statement.setTimestamp(3, schedulePosition.getLectureTime());
				statement.setLong(4, schedulePosition.getTeacher().getId());
				statement.executeUpdate();
				log.info("Create statement");
				resultSet = statement.getGeneratedKeys();
				log.info("Create resultSet");
				if (resultSet.next()) {
					schedulePosition.setId(resultSet.getLong("id"));
					log.info("SchedulePosition was created");
				}
			} catch (SQLException e) {
				log.error("SchedulePosition was not created: - " + e.getMessage());
				throw new DaoException(
						SchedulePositionDao.class.getName() + ": - schedulePosition was not got created to " + e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
		} else {
			log.fatal("SchedulePosition is already exist");
		}
	}

}
