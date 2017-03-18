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
			log.info("connection was created");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			log.error("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}

	public static Connection getConnection() {
		return createConnection();
	}

	public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
				log.info("resulSet was closed");
			} catch (SQLException e) {
				log.error("Error. ResultSet not closed", e);
			}
		}
		if (statement != null) {
			try {
				statement.close();
				log.info("statement was cloused");
			} catch (SQLException e) {
				log.error("Error. Statement not closed", e);
			}
		}
		if (connection != null) {
			try {
				connection.close();
				log.info("connection was cloused");
			} catch (SQLException e) {
				log.error("Error. Connection not closed", e);
			}
		}
	}
}
