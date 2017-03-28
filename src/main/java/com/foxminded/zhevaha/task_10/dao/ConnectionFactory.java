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

	private ConnectionFactory() {
		try {
			log.info("Create sql driver");
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			log.error("Driver not found", e);
		}
	}

	private static void getProperties() {
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
			log.error("ERROR:properties file is disavaliable ", e);
		}
	}

	private static Connection createConnection() {
		Connection connection = null;
		try {
			log.info("connection was created");
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			log.error("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}

	public static Connection getConnection() {
		getProperties();
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

	public static void closeConnection(Connection connection, Statement statement) {
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
