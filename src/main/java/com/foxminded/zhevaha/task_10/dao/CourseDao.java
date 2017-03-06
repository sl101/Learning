package com.foxminded.zhevaha.task_10.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.foxminded.zhevaha.task_10.domain.Course;
import com.foxminded.zhevaha.task_10.domain.Teacher;

public class CourseDao {

	private static final Logger log = Logger.getLogger(CourseDao.class);

	public void addTeacher(Course course, Teacher teacher) {
		log.info("Add Teacher to course");
		if (course.getId() != 0) {
			if (teacher.getId() != 0) {
				List<Long> teachersID = new ArrayList<Long>();
				teachersID = findTeachersID(course.getId());
				if (!teachersID.contains(teacher.getId())) {
					StringBuilder completeList = new StringBuilder();
					for (int i = 0; i < teachersID.size(); i++) {
						completeList.append(teachersID.get(i) + ",");
					}
					completeList.append(teacher.getId());
					String query = "UPDATE Courses SET teachers_id = '{" + completeList + "}' WHERE id = "
							+ course.getId() + ";";
					ConnectionFactory.enterData(query);
					log.info("Teacher was added on the course");
				} else {
					log.fatal("Teacher already is enrolled in this course");
					throw new RuntimeException("Teacher already is enrolled in this course");
				}
			} else {
				log.fatal("This teacher is not registered");
				throw new NullPointerException("This teacher is not registered");
			}
		} else {
			log.fatal("This course is not exist");
			throw new NullPointerException("This course is not exist");
		}
	}

	public void deleteTeacher(Course course, Teacher teacher) {
		log.info("Delete the teacher from this course");
		if (course.getId() != 0) {
			if (teacher.getId() != 0) {
				List<Long> teachersID = new ArrayList<Long>();
				teachersID = findTeachersID(course.getId());
				if (teachersID.contains(teacher.getId())) {
					teachersID.remove(teacher.getId());
					StringBuilder completeList = new StringBuilder();
					for (int i = 0; i < teachersID.size(); i++) {
						completeList.append(teachersID.get(i) + ",");
					}
					completeList.deleteCharAt(completeList.length() - 1);
					String query = "UPDATE Courses SET teachers_id = '{" + completeList + "}' WHERE id = "
							+ course.getId() + ";";
					ConnectionFactory.enterData(query);
					log.info("The teacher was deleted from the course");
				} else {
					log.fatal("The teacher does not teach in this course");
					throw new RuntimeException("The teacher does not teach in this course");
				}
			} else {
				log.fatal("This teacher is not registered");
				throw new NullPointerException("This teacher is not registered");
			}
		} else {
			log.fatal("This course is not exist");
			throw new NullPointerException("This course is not exist");
		}
	}

	private List<Long> findTeachersID(Long courseId) {
		log.info("Find list ID of teachers of course in database");
		List<Long> result = new ArrayList<Long>();
		String query = "SELECT teachers_id FROM Courses WHERE id = " + courseId + ";";
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
					if (resultSet.getArray(1) != null) {
						Long[] javaArray = (Long[]) resultSet.getArray(1).getArray();
						for (int i = 0; i < javaArray.length; i++) {
							result.add(javaArray[i]);
						}
					}
				}
			} catch (SQLException e) {
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (result.isEmpty()) {
			log.fatal("No teachers on the course");
			throw new NullPointerException("No teachers on the course");
		} else {
			log.info("The list of teachers of the course was created");
		}
		return result;
	}

	public long findMaxCourseId() {
		log.info("get max id from Courses");
		String query = "SELECT MAX(id) FROM Courses;";
		return ConnectionFactory.findMaxID(query);
	}
}
