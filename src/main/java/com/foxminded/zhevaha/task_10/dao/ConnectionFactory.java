package com.foxminded.zhevaha.task_10.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class ConnectionFactory {

	public static final String URL = "jdbc:postgresql://localhost:5432/university";
	public static final String USER = "postgres";
	public static final String PASSWORD = "university";
	public static final String DRIVER_CLASS = "org.postgresql.Driver";
	private static final Logger log = Logger.getLogger(ConnectionFactory.class);

	private ConnectionFactory() {
		try {
			log.info("Create sql driver");
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			log.error("Driver not found", e);
		}
	}

	private static Connection createConnection() {
		Connection connection = null;
		try {
			log.info("Create Connection");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			log.error("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}

	public static Connection getConnection() {
		return createConnection();
	}

	public static void enterData(String query) {
		log.info("Enter data in DB");
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			log.info("Connection was created");
			try {
				statement = connection.createStatement();
				log.info("Statement was created");
				statement.execute(query);
			} catch (SQLException e) {
				log.error("ERROR. Statement was not created", e);
			}
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}

	public static long findMaxID(String query) {
		Long maxId = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.createStatement();
			log.info("statement was created");
			try {
				resultSet = statement.executeQuery(query);
				log.info("resultSet was created");
				while (resultSet.next()) {
					maxId = resultSet.getLong(1);
				}
			} catch (SQLException e) {
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return maxId;
	}

	public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
				log.info("Close ResulSet");
			} catch (SQLException e) {
				log.error("Error. ResultSet not closed", e);
			}
		}
		if (statement != null) {
			try {
				statement.close();
				log.info("Close Statement");
			} catch (SQLException e) {
				log.error("Error. Statement not closed", e);
			}
		}
		if (connection != null) {
			try {
				connection.close();
				log.info("Close Connection");
			} catch (SQLException e) {
				log.error("Error. Connection not closed", e);
			}
		}
	}
}
