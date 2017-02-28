package com.foxminded.zhevaha.task_10.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class CustomDriverManager {

	private static final Logger log = Logger.getLogger(CustomDriverManager.class);

	public String readFromDB(String query, String login, String password) {
		log.info("start readFromDB()");
		StringBuilder result = new StringBuilder();

		try {
			log.info("Create postgresql driver");
			Class.forName("org.postgresql.Driver");

			Connection connection = null;
			Properties properties = new Properties();
			properties.setProperty("user", login);
			properties.setProperty("password", password);
			try {
				log.info("Create connection");
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", properties);
				Statement statement = null;
				try {
					log.info("Create statement");
					statement = connection.createStatement();
					ResultSet resultSet = null;
					try {
						log.info("Create resultSet");
						resultSet = statement.executeQuery(query);
						int columnsAmount = resultSet.getMetaData().getColumnCount();
						for (int i = 0; i < columnsAmount; i++) {
							result.append(resultSet.getMetaData().getColumnName(i + 1));
							result.append(" ");
						}
						result.append("\n");

						while (resultSet.next()) {
							for (int i = 0; i < columnsAmount; i++) {
								result.append(resultSet.getString(i + 1));
								result.append(" ");
							}
							result.append("\n");
						}
					} catch (SQLException e) {
						log.error("ResultSet not created", e);
					} finally {
						try {
							if (resultSet != null) {
								log.info("Ñlose resultSet");
								resultSet.close();
							}
						} catch (SQLException e) {
							log.error("Exception. ResultSet not closed ", e);
						}
					}
				} catch (SQLException e1) {
					log.error("Statement not created", e1);
				} finally {
					try {
						if (statement != null) {
							log.info("Close statement");
							statement.close();
						}
					} catch (SQLException e) {
						log.error("Exception. Statement not closed ", e);
					}
				}
			} catch (SQLException e2) {
				log.error("Connection not open", e2);
			} finally {
				try {
					if (connection != null) {
						log.info("Close connection");
						connection.close();
					}

				} catch (SQLException e) {
					log.error("Exception. Connection not closed", e);
				}
			}
		} catch (ClassNotFoundException e) {
			log.error("Driver not found", e);
		}

		return result.toString();
	}
}
