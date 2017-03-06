package com.foxminded.zhevaha.task_10.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.foxminded.zhevaha.task_10.domain.Course;
import com.foxminded.zhevaha.task_10.domain.Teacher;

public class TeacherDao {

	private static final Logger log = Logger.getLogger(TeacherDao.class);

	public void registerTeacher(Teacher teacher) {
		log.info("Register teacher");
		if (teacher.getId() == 0) {
			String query = "INSERT INTO Teachers (name, dayOfBirth) VALUES ('" + teacher.getName() + "', '"
					+ teacher.getDayOfBirth() + "');";
			ConnectionFactory.enterData(query);
		} else {
			log.fatal("This teacher has already registered");
		}
	}

	public void removeTeacher(Teacher teacher) {
		log.info("Delete teacher");
		if (teacher.getId() != 0) {
			String query = "DELETE FROM Teachers WHERE id = " + teacher.getId() + ";";
			ConnectionFactory.enterData(query);
			log.info("Teacher was deleted");
		} else {
			log.fatal("This teacher is not registered");
		}
	}

	public void addCourse(Course course, Teacher teacher) {
		log.info("Add a course for the teacher");
		if (course.getId() != 0) {
			if (teacher.getId() != 0) {
				List<Long> coursesID = new ArrayList<Long>();
				coursesID = findCoursesID(teacher.getId());
				if (!coursesID.contains(course.getId())) {
					StringBuilder completeList = new StringBuilder();
					for (int i = 0; i < coursesID.size(); i++) {
						completeList.append(coursesID.get(i) + ",");
					}
					completeList.append(course.getId());
					String query = "UPDATE Teachers SET courses_id ='{" + completeList + "}' WHERE id = "
							+ teacher.getId() + ";";
					ConnectionFactory.enterData(query);
					log.info("Course was appended to teacher");
				} else {
					log.info("This teacher already has has this course");
				}
			} else {
				log.fatal("Teacher already is enrolled in this course");
				throw new RuntimeException("Teacher already is enrolled in this course");
			}
		} else {
			log.fatal("This course is not exist");
			throw new NullPointerException("This course is not exist");
		}
	}

	public void deleteCourse(Course course, Teacher teacher) {
		log.info("Remove course from teacher");
		if (course.getId() != 0) {
			if (teacher.getId() != 0) {
				List<Long> coursesID = findCoursesID(teacher.getId());
				coursesID = findCoursesID(teacher.getId());
				if (coursesID.contains(course.getId())) {
					coursesID.remove(course.getId());
					StringBuilder completeList = new StringBuilder();
					for (int i = 0; i < coursesID.size(); i++) {
						completeList.append(coursesID.get(i) + ",");
					}
					completeList.deleteCharAt(completeList.length() - 1);
					String query = "UPDATE Teachers SET courses_id = '{" + completeList + "}' WHERE id = "
							+ teacher.getId() + ";";
					ConnectionFactory.enterData(query);
					log.info("Course was deleted from teacher");
				} else {
					log.info("This teacher does not has this course");
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

	private List<Long> findCoursesID(Long teacherID) {
		log.info("Find list ID of courses of teacher in database");
		List<Long> result = new ArrayList<Long>();
		String query = "SELECT courses_id FROM Teachers WHERE id = " + teacherID + ";";
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
			log.fatal("The teacher has not has courses yet");
			// throw new NullPointerException("The teacher has not has courses
			// yet");
		} else {
			log.info("The list of courses of the teacher was created");
		}
		return result;
	}

	public long findMaxTeacherId() {
		log.info("get max id from Teachers");
		String query = "SELECT MAX(id) FROM Teachers;";
		return ConnectionFactory.findMaxID(query);
	}

	public Teacher findTeacherById(long newTeacherID) {
		log.info("Find teacher by ID");
		Teacher teacher = null;
		String query = "SELECT * FROM Teachers WHERE id = " + newTeacherID + ";";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.createStatement();

			try {

				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					String name = resultSet.getString(2);
					Date dayOfBirth = resultSet.getDate(3);
					teacher = new Teacher(name, dayOfBirth);
					teacher.setId(resultSet.getLong(1));
				}
			} catch (SQLException e) {
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return teacher;
	}

}
