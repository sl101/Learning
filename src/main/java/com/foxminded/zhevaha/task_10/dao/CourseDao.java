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

	public Set<Course> getAll() {
		log.info("Find courses in datebase");
		Set<Course> courses = new HashSet<Course>();
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
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (courses.isEmpty()) {
			log.fatal("There were no registered courses\nThe list is empty");
		} else {
			log.info("Courses list was created");
		}
		return courses;
	}

	public Course getById(Long id) {
		log.info("Find course by ID");
		Course course = null;
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
		return course;
	}

	public Course update(Course course) {
		log.info("Update Course");
		if (course.getId() != 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(UPDATE);
				statement.setString(1, course.getName());
				statement.setLong(2, course.getId());
				statement.executeUpdate();
				log.info("statement was created");
			} catch (SQLException e) {
				log.error("ERROR. Statement was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement);
			}
			course = getById(course.getId());
			return course;
		} else {
			log.info("Course was not created");
			return null;
		}
	}

	public void delete(Course course) {
		log.info("Delete course");
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(DELETE);
			statement.setLong(1, course.getId());
			statement.executeUpdate();
			log.info("statement was created");
			log.info("Course was deleted");
		} catch (SQLException e) {
			log.fatal("Course was not deleted", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void create(Course course) {
		log.info("Create course");
		if (course.getId() == 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, course.getName());
				statement.executeUpdate();
				log.info("statement was created");
				try {
					resultSet = statement.getGeneratedKeys();
					if (resultSet.next()) {
						course.setId(resultSet.getLong("id"));
						log.info("Course was created");
					}
				} catch (SQLException e) {
					log.error("ERROR. ResultSet was not created", e);
				}
			} catch (SQLException e) {
				log.fatal("Course was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement, resultSet);
			}
		}
		log.fatal("Course is already exist");
	}

	public Set<Course> getTeacherCourses(long teacherId) {
		log.info("Choose courses by teachers_id");
		Set<Course> courses = new HashSet<Course>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_TEACHER_COURSES);
			statement.setLong(1, teacherId);
			log.info("statement was created");
			try {
				resultSet = statement.executeQuery();
				log.info("resultSet was created");
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
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (courses.isEmpty()) {
			log.fatal("No teacher courses. The list is empty");
		} else {
			log.info("Courses of teacher was created");
		}

		return courses;
	}

	public void appointGroupCourse(Group group, Course course) {
		log.info("Appoint course to group");
		if (group != null && course != null) {
			Connection connection = null;
			PreparedStatement statement = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(APPOINT_GROUP_COURSE);
				statement.setLong(1, course.getId());
				statement.setLong(1, group.getId());
				statement.executeUpdate();
				log.info("statement was created");
			} catch (SQLException e) {
				log.fatal("Course was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement);
			}
		}
		log.fatal("Course or group is empty");
	}

	private Set<String> getCourseTopics(long courseId) {
		log.info("Get course topics");
		Set<String> topics = new HashSet<String>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_TOPICS);
			statement.setLong(1, courseId);
			log.info("statement was created");
			try {
				resultSet = statement.executeQuery();
				log.info("resultSet was created");
				while (resultSet.next()) {
					String topic = resultSet.getString("topic");
					topics.add(topic);
				}
				log.info("List topics of course was formed");
			} catch (SQLException e) {
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (topics.isEmpty()) {
			log.fatal("No topics. The list is empty");
		} else {
			log.info("Topics of course was created");
		}
		return topics;
	}

	public Set<Course> getGroupCourses(long groupId) {
		log.info("Get courses of group");
		Set<Course> courses = new HashSet<Course>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(GET_GROUP_COURSES);
			statement.setLong(1, groupId);
			log.info("statement was created");
			try {
				resultSet = statement.executeQuery();
				log.info("resultSet was created");
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
				log.error("ERROR. ResultSet was not created", e);
			}
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement, resultSet);
		}
		if (courses.isEmpty()) {
			log.fatal("No group courses. The list is empty");
		} else {
			log.info("Courses of group was created");
		}
		return courses;
	}

	public void enroll(Teacher teacher, Course course) {
		log.info("Enroll teacher in course in database");
		Connection connection = null;
		PreparedStatement statement = null;
		connection = ConnectionFactory.getConnection();
		try {
			statement = connection.prepareStatement(ENROLL_TEACHER);
			statement.setLong(1, course.getId());
			statement.setLong(2, teacher.getId());
			statement.executeUpdate();
			log.info("statement was created");
		} catch (SQLException e) {
			log.error("ERROR. Statement was not created", e);
		} finally {
			ConnectionFactory.closeConnection(connection, statement);
		}
	}

	public void createTopic(Course course, String topic) {
		log.info("Create topic");
		if (course.getId() != 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			connection = ConnectionFactory.getConnection();
			try {
				statement = connection.prepareStatement(CREATE_TOPIC);
				statement.setLong(1, course.getId());
				statement.setString(2, topic);
				statement.executeUpdate();
				log.info("statement was created");
			} catch (SQLException e) {
				log.fatal("Topic was not created", e);
			} finally {
				ConnectionFactory.closeConnection(connection, statement);
			}
		}
		log.fatal("There is not such course");
	}
}
