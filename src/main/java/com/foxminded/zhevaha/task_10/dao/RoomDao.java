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
		Set<Room> rooms = new HashSet<Room>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_ALL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				Room room = new Room(name);
				long id = resultSet.getLong("id");
				room.setId(id);
				rooms.add(room);
			}
		} catch (SQLException e) {
			log.error("Problem connect DB", e);
			throw new DaoException("Problem connect DB due to ", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return rooms;
	}

	public Room getById(Long id) throws DaoException {
		Room room = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_BY_ID);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			String name = resultSet.getString("name");
			room = new Room(name);
			room.setId(id);
		} catch (SQLException e) {
			log.error("Problem connect DB", e);
			throw new DaoException("Problem connect DB due to ", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return room;
	}

	public Room update(Room room) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(UPDATE);
			statement.setString(1, room.getName());
			statement.setLong(2, room.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("Problem connect DB", e);
			throw new DaoException("Problem connect DB due to ", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
		room = getById(room.getId());
		return room;
	}

	public void delete(Room room) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, room.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("Problem connect DB", e);
			throw new DaoException("Problem connect DB due to ", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void create(Room room) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, room.getName());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			room.setId(resultSet.getLong("id"));
		} catch (SQLException e) {
			log.error("Problem connect DB", e);
			throw new DaoException("Problem connect DB due to ", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}
}
