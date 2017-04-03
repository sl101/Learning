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

	public Set<AcademicPlan> getAll() throws DaoException {
		Set<AcademicPlan> academicPlans = new HashSet<AcademicPlan>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_ALL);
			resultSet = statement.executeQuery();
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
			log.error("AcademicPlans list was not got: - " + e.getMessage());
			throw new DaoException(AcademicPlan.class.getName() + ": - academicPlans list was not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return academicPlans;
	}

	public AcademicPlan getById(Long id) throws DaoException {
		AcademicPlan academicPlan = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_BY_ID);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			int year = resultSet.getInt("year");
			academicPlan = new AcademicPlan(year);
			academicPlan.setId(id);
			Set<Lecture> lectures = new LectureDao().getPlannedLectures(id);
			Iterator<Lecture> iteratorLectures = lectures.iterator();
			while (iteratorLectures.hasNext()) {
				academicPlan.addLecture(iteratorLectures.next());
			}
		} catch (SQLException e) {
			log.error("AcademicPlan was not got: - " + e.getMessage());
			throw new DaoException(AcademicPlan.class.getName() + ": - academicPlan was not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return academicPlan;
	}

	public AcademicPlan update(AcademicPlan academicPlan) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(UPDATE);
			statement.setInt(1, academicPlan.getYear());
			statement.setLong(2, academicPlan.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("AcademicPlan was not updated: - " + e.getMessage());
			throw new DaoException(AcademicPlan.class.getName() + ": - academicPlan was not updated due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
		academicPlan = getById(academicPlan.getId());
		return academicPlan;
	}

	public void delete(AcademicPlan academicPlan) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, academicPlan.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("AcademicPlan was not deleted: - " + e.getMessage());
			throw new DaoException(AcademicPlan.class.getName() + ": - academicPlan was not deleted due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void create(AcademicPlan academicPlan) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, academicPlan.getYear());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			academicPlan.setId(resultSet.getLong("id"));
		} catch (SQLException e) {
			log.error("AcademicPlan was not created: - " + e.getMessage());
			throw new DaoException(AcademicPlan.class.getName() + ": - academicPlan was not created due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}
}