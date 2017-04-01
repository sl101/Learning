package com.foxminded.zhevaha.task_10.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionFactory {

	public static String url = null;
	public static String login = null;
	public static String password = null;
	public static String driver = null;
	private static final Logger log = Logger.getLogger(ConnectionFactory.class);
	private static Properties property;

	private ConnectionFactory() throws DaoException {
		try {
			Class.forName(driver);
			log.info("Create sql driver");
		} catch (ClassNotFoundException e) {
			log.error("Driver was not created: - " + e.getMessage());
			throw new DaoException(ConnectionFactory.class.getName() + ": - driver was not created due to " + e);
		}
	}

	private static void getProperties() throws DaoException {
		FileInputStream fis;
		property = new Properties();
		try {
			fis = new FileInputStream("src/main/resources/config.properties");
			property.load(fis);
			driver = property.getProperty("db.driver = org.postgresql.Driver");
			url = property.getProperty("db.host");
			login = property.getProperty("db.login");
			password = property.getProperty("db.password");
		} catch (IOException e) {
			log.error("Properties was not got: - " + e.getMessage());
			throw new DaoException(ConnectionFactory.class.getName() + ": - Properties was not got due to " + e);
		}
	}

	private static Connection createConnection() throws DaoException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, login, password);
			log.info("Create connection");
		} catch (SQLException e) {
			log.error("Connection was not created: - " + e.getMessage());
			throw new DaoException(ConnectionFactory.class.getName() + ": - connection was not created due to " + e);
		}
		return connection;
	}

	public static Connection getConnection() throws DaoException {
		getProperties();
		return createConnection();
	}

	public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet)
			throws DaoException {
		if (resultSet != null) {
			try {
				resultSet.close();
				log.info("Close resulSet");
			} catch (SQLException e) {
				log.error("ResultSet was not closed: - " + e.getMessage());
				throw new DaoException(ConnectionFactory.class.getName() + ": - resultSet was not closed due to " + e);
			}
		}
		if (statement != null) {
			try {
				statement.close();
				log.info("Close statement");
			} catch (SQLException e) {
				log.error("Statement was not closed: - " + e.getMessage());
				throw new DaoException(ConnectionFactory.class.getName() + ": - statement was not closed due to " + e);
			}
		}
		if (connection != null) {
			try {
				connection.close();
				log.info("Close connection");
			} catch (SQLException e) {
				log.error("Connection was not closed: - " + e.getMessage());
				throw new DaoException(ConnectionFactory.class.getName() + ": - connection was not closed due to " + e);
			}
		}
	}

	public static void closeConnection(Connection connection, Statement statement) throws DaoException {
		if (statement != null) {
			try {
				statement.close();
				log.info("Close statement");
			} catch (SQLException e) {
				log.error("Statement was not closed: - " + e.getMessage());
				throw new DaoException(ConnectionFactory.class.getName() + ": - statement was not closed due to " + e);
			}
		}
		if (connection != null) {
			try {
				connection.close();
				log.info("Close connection");
			} catch (SQLException e) {
				log.error("Connection was not closed: - " + e.getMessage());
				throw new DaoException(ConnectionFactory.class.getName() + ": - connection was not closed due to " + e);
			}
		}
	}
}
