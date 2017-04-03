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

import com.foxminded.zhevaha.task_10.domain.Course;
import com.foxminded.zhevaha.task_10.domain.Group;
import com.foxminded.zhevaha.task_10.domain.Teacher;

public class CourseDao implements GenericDao<Course, Long> {

	private static final Logger log = Logger.getLogger(CourseDao.class);
	private final String CREATE = "INSERT INTO Courses (name) VALUES (?) ON CONFLICT (name) DO UPDATE SET name = excluded.name;";
	private final String CREATE_TOPIC = "INSERT INTO Topics (course_id, topic) VALUES (?, ?);";
	private final String APPOINT_GROUP_COURSE = "INSERT INTO courses_groups (course_id, group_id) VALUES (?, ?) ON CONFLICT (course_id, group_id) DO UPDATE SET course_id = excluded.course_id, group_id = excluded.group_id;";
	private final String GET_ALL = "SELECT * FROM Courses;";
	private final String GET_BY_ID = "SELECT * FROM Courses WHERE id = ?;";
	private final String UPDATE = "UPDATE Courses SET name = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM Courses WHERE id = ?;";
	private final String GET_GROUP_COURSES = "SELECT * FROM Courses WHERE id IN (SELECT course_id FROM courses_groups WHERE group_id = ?);";
	private final String GET_TEACHER_COURSES = "SELECT * FROM Courses WHERE id IN (SELECT course_id FROM courses_teachers WHERE teacher_id = ?);";
	private final String GET_TOPICS = "SELECT topic FROM Topics WHERE course_id = ?;";
	private final String ENROLL_TEACHER = "INSERT INTO courses_teachers (course_id, teacher_id) VALUES (?, ?) ON CONFLICT (course_id, teacher_id) DO UPDATE SET course_id = excluded.course_id, teacher_id = excluded.teacher_id";

	public Set<Course> getAll() throws DaoException {
		Set<Course> courses = new HashSet<Course>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_ALL);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				Course course = new Course(name);
				long id = resultSet.getLong("id");
				course.setId(id);
				Set<Teacher> courseTeachers = new TeacherDao().getCourseTeachers(id);
				Iterator<Teacher> iteratorCourseTeachers = courseTeachers.iterator();
				while (iteratorCourseTeachers.hasNext()) {
					course.addTeacher(iteratorCourseTeachers.next());
				}
				Set<String> topics = getCourseTopics(id);
				Iterator<String> iteratorTopics = topics.iterator();
				while (iteratorTopics.hasNext()) {
					course.addTopic(iteratorTopics.next());
				}
				courses.add(course);
			}
		} catch (SQLException e) {
			log.error("Courses list was not got: - " + e.getMessage());
			throw new DaoException(CourseDao.class.getName() + ": - courses list was not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return courses;
	}

	public Course getById(Long id) throws DaoException {
		Course course = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_BY_ID);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			String name = resultSet.getString("name");
			course = new Course(name);
			course.setId(id);
			Set<Teacher> courseTeachers = new TeacherDao().getCourseTeachers(id);
			Iterator<Teacher> iteratorCourseTeachers = courseTeachers.iterator();
			while (iteratorCourseTeachers.hasNext()) {
				course.addTeacher(iteratorCourseTeachers.next());
			}
			Set<String> topics = getCourseTopics(id);
			Iterator<String> iteratorTopics = topics.iterator();
			while (iteratorTopics.hasNext()) {
				course.addTopic(iteratorTopics.next());
			}
		} catch (SQLException e) {
			log.error("Course was not got: - " + e.getMessage());
			throw new DaoException(CourseDao.class.getName() + ": - course was not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return course;
	}

	public Course update(Course course) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(UPDATE);
			statement.setString(1, course.getName());
			statement.setLong(2, course.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("Course was not updated: - " + e.getMessage());
			throw new DaoException(CourseDao.class.getName() + ": - course was not updated due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
		course = getById(course.getId());
		return course;
	}

	public void delete(Course course) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, course.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("Course was not deleted: - " + e.getMessage());
			throw new DaoException(CourseDao.class.getName() + ": - course was not deleted due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void create(Course course) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, course.getName());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			course.setId(resultSet.getLong("id"));
		} catch (SQLException e) {
			log.error("Course was not created: - " + e.getMessage());
			throw new DaoException(CourseDao.class.getName() + ": - course was not created due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
	}

	public Set<Course> getTeacherCourses(long teacherId) throws DaoException {
		Set<Course> courses = new HashSet<Course>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_TEACHER_COURSES);
			statement.setLong(1, teacherId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				Course course = new Course(name);
				long id = resultSet.getLong("id");
				course.setId(id);
				Set<String> topics = getCourseTopics(id);
				Iterator<String> iteratorTopics = topics.iterator();
				while (iteratorTopics.hasNext()) {
					course.addTopic(iteratorTopics.next());
				}
				courses.add(course);
			}
		} catch (SQLException e) {
			log.error("Teacher courses were not created: - " + e.getMessage());
			throw new DaoException(CourseDao.class.getName() + ": - teacher courses were not created due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return courses;
	}

	public void appointGroupCourse(Group group, Course course) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(APPOINT_GROUP_COURSE);
			statement.setLong(1, course.getId());
			statement.setLong(1, group.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("Course was not appointed: - " + e.getMessage());
			throw new DaoException(CourseDao.class.getName() + ": - course was not appointed due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	private Set<String> getCourseTopics(long courseId) throws DaoException {
		Set<String> topics = new HashSet<String>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_TOPICS);
			statement.setLong(1, courseId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String topic = resultSet.getString("topic");
				topics.add(topic);
			}
		} catch (SQLException e) {
			log.error("Course topics were not got: - " + e.getMessage());
			throw new DaoException(CourseDao.class.getName() + ": - course topics were not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return topics;
	}

	public Set<Course> getGroupCourses(long groupId) throws DaoException {
		Set<Course> courses = new HashSet<Course>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_GROUP_COURSES);
			statement.setLong(1, groupId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				Course course = new Course(name);
				long courseId = resultSet.getLong("id");
				course.setId(courseId);
				Set<String> topics = getCourseTopics(courseId);
				Iterator<String> iteratorTopics = topics.iterator();
				while (iteratorTopics.hasNext()) {
					course.addTopic(iteratorTopics.next());
				}
				courses.add(course);
			}
		} catch (SQLException e) {
			log.error("Group courses were not got: - " + e.getMessage());
			throw new DaoException(CourseDao.class.getName() + ": - group courses were not got due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		return courses;
	}

	public void enroll(Teacher teacher, Course course) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(ENROLL_TEACHER);
			statement.setLong(1, course.getId());
			statement.setLong(2, teacher.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("Teacher was not enrolled: - " + e.getMessage());
			throw new DaoException(CourseDao.class.getName() + ": - Teacher was not enrolled due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void createTopic(Course course, String topic) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(CREATE_TOPIC);
			statement.setLong(1, course.getId());
			statement.setString(2, topic);
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("Topic was not created: - " + e.getMessage());
			throw new DaoException(CourseDao.class.getName() + ": - topic was not created due to " + e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}
}
