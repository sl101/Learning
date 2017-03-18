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

public class RoomDao implements DaoFactory<Room, Long> {

	private static final Logger log = Logger.getLogger(RoomDao.class);
	private final String CREATE_ENTITY = "INSERT INTO Rooms (name) VALUES (?) ON CONFLICT (name) DO UPDATE SET name = excluded.name";
	private final String GET_ALL = "SELECT * FROM Rooms;";
	private final String GET_BY_ID = "SELECT * FROM Rooms WHERE id = ?;";
	private final String UPDATE = "UPDATE Rooms SET name = ? WHERE id = ?;";
	private final String DELETE_ENTITY = "DELETE FROM Rooms WHERE id = ?;";

	public Set<Room> getAll() {
		log.info("Find rooms in date base");
		Set<Room> rooms = new HashSet<Room>();
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
					Room room = new Room(name);
					long id = resultSet.getLong(1);
					room.setId(id);
					rooms.add(room);
				}
			} catch (SQLException e) {
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
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

	public Room getEntityById(Long id) {
		log.info("Find room by ID");
		Room room = null;
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
					room = new Room(name);
					room.setId(id);
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
		return room;
	}

	public Room update(Room room) {
		log.info("Update Room");
		if (room.getId() != 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(UPDATE);
				statement.setString(1, room.getName());
				statement.setLong(2, room.getId());
				statement.executeUpdate();
				log.info("statement was created");
			} catch (SQLException e) {
				log.error("ERROR. Statement was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
			room = getEntityById(room.getId());
			return room;
		} else {
			log.info("Room was not created");
			return null;
		}
	}

	public void delete(Room room) {
		log.info("Delete room");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE_ENTITY);
			statement.setLong(1, room.getId());
			statement.executeUpdate();
			log.info("statement was created");
			log.info("Room was deleted");
		} catch (SQLException e) {
			log.fatal("Room was not deleted", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}

	public void create(Room room) {
		log.info("Create room");
		if (room.getId() == 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(CREATE_ENTITY, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, room.getName());
				statement.executeUpdate();
				log.info("statement was created");
				try {
					resultSet = statement.getGeneratedKeys();
					if (resultSet.next()) {
						log.info("resultSet get generated key");
						room.setId(resultSet.getLong(1));
						log.info("Room was created");
					}
				} catch (SQLException e) {
					log.error("ERROR. ResultSet was not created", e);
				}
			} catch (SQLException e) {
				log.fatal("Room was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
		}
		log.fatal("Room is already exist");
	}
}
