package com.foxminded.zhevaha.task_10.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.foxminded.zhevaha.task_10.domain.AcademicPlan;
import com.foxminded.zhevaha.task_10.domain.Lecture;

public class AcademicPlanDao implements GenericDao<AcademicPlan, Long> {

	private static final Logger log = Logger.getLogger(AcademicPlanDao.class);
	private final String CREATE = "INSERT INTO academic_plan (year) VALUES (?) ON CONFLICT (year) DO UPDATE SET year = excluded.year;";
	private final String GET_ALL = "SELECT * FROM academic_plan;";
	private final String GET_BY_ID = "SELECT * FROM academic_plan WHERE id = ?;";
	private final String UPDATE = "UPDATE academic_plan SET year = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM academic_plan WHERE id = ?;";

	public Set<AcademicPlan> getAll() {
		log.info("Find all academicPlans in database");
		Set<AcademicPlan> academicPlans = new HashSet<AcademicPlan>();
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
					int year = resultSet.getInt("year");
					AcademicPlan academicPlan = new AcademicPlan(year);
					long id = resultSet.getLong("id");
					academicPlan.setId(id);
					Set<Lecture> lectures = new LectureDao().getPlannedLectures(id);
					Iterator<Lecture> iteratorLectures = lectures.iterator();
					while (iteratorLectures.hasNext()) {
						academicPlan.addLecture(iteratorLectures.next());
					}
					academicPlans.add(academicPlan);
				}
			} catch (SQLException e) {
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (academicPlans.isEmpty()) {
			log.fatal("There were no registered any AcademicPlans. The list is empty");
		} else {
			log.info("AcademicPlans list was created");
		}
		return academicPlans;
	}

	public AcademicPlan getById(Long id) {
		log.info("Find AcademicPlan by ID");
		AcademicPlan academicPlan = null;
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
					int year = resultSet.getInt("year");
					academicPlan = new AcademicPlan(year);
					academicPlan.setId(id);
					Set<Lecture> lectures = new LectureDao().getPlannedLectures(id);
					Iterator<Lecture> iteratorLectures = lectures.iterator();
					while (iteratorLectures.hasNext()) {
						academicPlan.addLecture(iteratorLectures.next());
					}
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
		return academicPlan;
	}

	public AcademicPlan update(AcademicPlan academicPlan) {
		log.info("Update AcademicPlan");
		if (academicPlan.getId() != 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(UPDATE);
				statement.setInt(1, academicPlan.getYear());
				statement.setLong(2, academicPlan.getId());
				statement.executeUpdate();
				log.info("statement was created");
			} catch (SQLException e) {
				log.error("ERROR. Statement was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement);
			}
			academicPlan = getById(academicPlan.getId());
			return academicPlan;
		} else {
			log.info("AcademicPlan was not created");
			return null;
		}
	}

	public void delete(AcademicPlan academicPlan) {
		log.info("Delete AcademicPlan");
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, academicPlan.getId());
			statement.executeUpdate();
			log.info("statement was created");
			log.info("AcademicPlan was deleted");
		} catch (SQLException e) {
			log.fatal("AcademicPlan was not deleted", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void create(AcademicPlan academicPlan) {
		log.info("Create AcademicPlan");
		if (academicPlan.getId() == 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, academicPlan.getYear());
				statement.executeUpdate();
				log.info("statement was created");
				try {
					resultSet = statement.getGeneratedKeys();
					if (resultSet.next()) {
						academicPlan.setId(resultSet.getLong("id"));
						log.info("AcademicPlan was created");
					}
				} catch (SQLException e) {
					log.error("ERROR. ResultSet was not created", e);
				}
			} catch (SQLException e) {
				log.fatal("Lecture was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
		}
		log.fatal("AcademicPlan is already exist");
	}

}
