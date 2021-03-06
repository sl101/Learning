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
	private static Properties property;
	private static final Logger log = Logger.getLogger(ConnectionFactory.class);

	private ConnectionFactory() throws UniverException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			log.error("Problem to get driver", e);
			throw new UniverException("Problem to get driver", e);
		}
	}

	private static void getProperties() throws UniverException {
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
			log.error("Problem to get properties", e);
			throw new UniverException("Problem to get properties", e);
		}
	}

	private static Connection createConnection() throws UniverException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			log.error("Problem to create connection", e);
			throw new UniverException("Problem to create connection", e);
		}
		return connection;
	}

	public static Connection getConnection() throws UniverException {
		getProperties();
		return createConnection();
	}

	public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet)
			throws UniverException {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				log.error("Problem to close resultSet", e);
				throw new UniverException("Problem to close resultSet", e);
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				log.error("Problem to close statement", e);
				throw new UniverException("Problem to close statement", e);
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Problem to close connection", e);
				throw new UniverException("Problem to close connection", e);
			}
		}
	}

	public static void closeConnection(Connection connection, Statement statement) throws UniverException {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				log.error("Problem to close statement", e);
				throw new UniverException("Problem to close statement", e);
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error("Problem to close connection", e);
				throw new UniverException("Problem to close connection", e);
			}
		}
	}
}
