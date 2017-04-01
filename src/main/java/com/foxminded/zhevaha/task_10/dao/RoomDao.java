package com.foxminded.zhevaha.task_10.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.foxminded.zhevaha.task_10.domain.Room;

public class RoomDao implements GenericDao<Room, Long> {

	private static final Logger log = Logger.getLogger(RoomDao.class);
	private final String CREATE = "INSERT INTO Rooms (name) VALUES (?) ON CONFLICT (name) DO UPDATE SET name = excluded.name";
	private final String GET_ALL = "SELECT * FROM Rooms;";
	private final String GET_BY_ID = "SELECT * FROM Rooms WHERE id = ?;";
	private final String UPDATE = "UPDATE Rooms SET name = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM Rooms WHERE id = ?;";

	public Set<Room> getAll() throws DaoException {
		log.info("Get all rooms");
		Set<Room> rooms = new HashSet<Room>();
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
				Room room = new Room(name);
				long id = resultSet.getLong("id");
				room.setId(id);
				rooms.add(room);
			}
		} catch (SQLException e) {
			log.error("Rooms list was not got: - " + e.getMessage());
			throw new DaoException(RoomDao.class.getName() + ": - rooms list was not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (rooms.isEmpty()) {
			log.fatal("There were no registered rooms\nThe list is empty");
		} else {
			log.info("Rooms list was created");
		}
		return rooms;
	}

	public Room getById(Long id) throws DaoException {
		log.info("Find Room by ID");
		Room room = null;
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
				room = new Room(name);
				room.setId(id);
			} else {
				log.info("resultSet has not data");
			}
		} catch (SQLException e) {
			log.error("Room was not got: - " + e.getMessage());
			throw new DaoException(RoomDao.class.getName() + ": - room was not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return room;
	}

	public Room update(Room room) throws DaoException {
		log.info("Update Room");
		if (room.getId() != 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(UPDATE);
				statement.setString(1, room.getName());
				statement.setLong(2, room.getId());
				statement.executeUpdate();
				log.info("Create statement");
			} catch (SQLException e) {
				log.error("Room was not updated: - " + e.getMessage());
				throw new DaoException(RoomDao.class.getName() + ": - room was not updated due to " + e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement);
			}
			room = getById(room.getId());
			return room;
		} else {
			log.info("Room is not existed");
			return null;
		}
	}

	public void delete(Room room) throws DaoException {
		log.info("Delete room");
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, room.getId());
			statement.executeUpdate();
			log.info("Create statement");
			log.info("Room was deleted");
		} catch (SQLException e) {
			log.error("Room was not deleted: - " + e.getMessage());
			throw new DaoException(RoomDao.class.getName() + ": - room was not deleted due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void create(Room room) throws DaoException {
		log.info("Create Room");
		if (room.getId() == 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, room.getName());
				statement.executeUpdate();
				log.info("Create statement");
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					room.setId(resultSet.getLong("id"));
					log.info("Room was created");
				}
			} catch (SQLException e) {
				log.error("Room was not created: - " + e.getMessage());
				throw new DaoException(RoomDao.class.getName() + ": - room was not created due to " + e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
		} else {
			log.fatal("Room is already exist");
		}
	}
}
