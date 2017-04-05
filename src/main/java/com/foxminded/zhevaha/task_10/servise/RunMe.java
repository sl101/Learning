package com.foxminded.zhevaha.task_10.servise;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.foxminded.zhevaha.task_10.dao.AcademicPlanDao;
import com.foxminded.zhevaha.task_10.dao.CourseDao;
import com.foxminded.zhevaha.task_10.dao.DaoException;
import com.foxminded.zhevaha.task_10.dao.GroupDao;
import com.foxminded.zhevaha.task_10.dao.LectureDao;
import com.foxminded.zhevaha.task_10.dao.RoomDao;
import com.foxminded.zhevaha.task_10.dao.StudentDao;
import com.foxminded.zhevaha.task_10.dao.TeacherDao;
import com.foxminded.zhevaha.task_10.dao.UniverDao;
import com.foxminded.zhevaha.task_10.domain.AcademicPlan;
import com.foxminded.zhevaha.task_10.domain.Course;
import com.foxminded.zhevaha.task_10.domain.Group;
import com.foxminded.zhevaha.task_10.domain.Lecture;
import com.foxminded.zhevaha.task_10.domain.Room;
import com.foxminded.zhevaha.task_10.domain.SchedulePosition;
import com.foxminded.zhevaha.task_10.domain.Student;
import com.foxminded.zhevaha.task_10.domain.Teacher;
import com.foxminded.zhevaha.task_10.domain.Univer;

public class RunMe {
	static Univer univer = null;
	static AcademicPlan academicPlan = null;
	static UniverDao univerDao = null;
	static AcademicPlanDao academicPlanDao = null;

	private static String[] courses = { "Mathematics", "Physics", "Chemistry" };
	private static String[] teachers = { "Ivanov", "Petrov", "Sidorov", "Ekhel", "Ivanov", "Joli" };
	private static String[] students = { "Vania", "Sasha", "Masha", "Kolia", "Lena", "Petro", "Dima", "Stas", "Taras",
			"Sofija", "Vania", "Sasha" };
	private static String[] groups = { "001", "002", "003" };

	private static final Logger log = Logger.getLogger(RunMe.class);

	public static void main(String[] args) throws Exception {
		univer = new Univer("Foxminded University");
		univerDao = new UniverDao();
		academicPlan = new AcademicPlan(2017);
		academicPlanDao = new AcademicPlanDao();

		List<AcademicPlan> academicPlans = new ArrayList<AcademicPlan>(academicPlanDao.getAll());
		for (int i = 0; i < academicPlans.size(); i++) {
			System.out.println(academicPlans.get(i).getYear());
		}
		// createUnivers();
		// deleteUnivers();
		// createCourses();
		// deleteCourses();
		// createGroups();
		// deleteGroups();
		// appointGroupCourse();
		// createTeachers();
		// deleteTeachers();
		// createRooms();
		// deleteRoms();
		// createStudents();
		// deleteStudents();
		// createLectures();
		// deleteLectures();
		// createAcademicPlan();
		// enrollTeachers();
		// fireTeacher();
		// enrollStudent();
		// expelStudent();
		// createSchedule();
		// showSchedule();

	}

	private static void createAcademicPlan() throws DaoException {
		new AcademicPlanDao().create(academicPlan);
		academicPlan = academicPlanDao.update(academicPlan);

	}

	private static void showSchedule() {
		System.out.println("show Schedule");
		Teacher person = null;
		Iterator<Teacher> teacherIterator = univer.getTeachers().iterator();
		while (teacherIterator.hasNext()) {
			person = teacherIterator.next();
			if (person.getName() == "Joli") {
				break;
			}
		}
		Timestamp period = setTime(2017, 3, 5, 10, 0);
		String schedulInfo = univer.showSchedule(period, person);
		System.out.println(schedulInfo);
	}

	private static void createSchedule() throws DaoException {
		System.out.println("create Schedule");
		createAcademicPlan();
		List<Lecture> lectures = new ArrayList<Lecture>(academicPlan.getLectures());
		// System.out.println("lectures size = " + lectures.size());
		Set<java.sql.Timestamp> dates = new TreeSet<java.sql.Timestamp>();
		while (lectures.size() != dates.size()) {
			dates.add(setTime(2017, (int) (4 * Math.random()), (int) (30 * Math.random()),
					(int) (9 + 6 * Math.random()), (int) 0));
		}
		// System.out.println("arrayDates size = " + dates.size());
		Set<SchedulePosition> schedulePositions = new HashSet<SchedulePosition>();
		Iterator<java.sql.Timestamp> dateIterator = dates.iterator();
		dateIterator.hasNext();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		for (int i = 0; i < lectures.size(); i++) {
			java.sql.Timestamp dateFotSchedule = dateIterator.next();
			// System.out.println(dateFormat.format(dateFotSchedule));
			List<Teacher> courseTeachers = new ArrayList<Teacher>(lectures.get(i).getCourse().getCourseTeachers());
			List<Room> rooms = new ArrayList<Room>(univer.getRooms());
			SchedulePosition schedulePosition = new SchedulePosition(lectures.get(i),
					rooms.get((int) (rooms.size() * Math.random())), (java.sql.Timestamp) dateFotSchedule,
					courseTeachers.get((int) (courseTeachers.size() * Math.random())));
			schedulePositions.add(schedulePosition);
		}
		univer = univer.createSchedule(schedulePositions);
	}

	private static java.sql.Timestamp setTime(int year, int month, int day, int hourOfDay, int minute) {
		java.util.Date date = new java.util.Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, hourOfDay, minute);
		date = calendar.getTime();
		return new java.sql.Timestamp(date.getTime());
	}

	private static void expelStudent() throws DaoException {
		System.out.println("expel student");
		List<Student> arrayStudents = new ArrayList<Student>(univer.getStudents());
		Student student = arrayStudents.get((int) (arrayStudents.size() * Math.random()));
		univer = univer.expelStudent(student);
	}

	private static void enrollStudent() throws DaoException {
		System.out.println("enroll student");
		List<Student> arrayStudents = new ArrayList<Student>(univer.getStudents());
		List<Group> arrayGroups = new ArrayList<Group>(univer.getGroups());
		for (int i = 0; i < arrayStudents.size(); i++) {
			univer = univer.enrollStudent(arrayStudents.get(i),
					arrayGroups.get((int) (arrayGroups.size() * Math.random())));
		}
	}

	private static void fireTeacher() throws DaoException {
		System.out.println("fire teacher");
		List<Teacher> arrayTeachers = new ArrayList<Teacher>(univer.getTeachers());
		Teacher teacher = arrayTeachers.get((int) (arrayTeachers.size() * Math.random()));
		List<Course> arrayCourses = new ArrayList<Course>(teacher.getTeacherCourses());
		Course course = arrayCourses.get((int) (arrayCourses.size() * Math.random()));
		List<Teacher> courseTeacher = new ArrayList<Teacher>(course.getCourseTeachers());
		univer = univer.fireTeacher(teacher, courseTeacher);
	}

	private static void enrollTeachers() throws DaoException {
		System.out.println("enroll teacher");
		List<Teacher> arrayTeachers = new ArrayList<Teacher>(univer.getTeachers());
		List<Course> arrayCourses = new ArrayList<Course>(univer.getCourses());
		System.out.println("teachers = " + arrayTeachers.size() + "\ncourses = " + arrayCourses.size());
		for (int i = 0; i < arrayTeachers.size(); i++) {
			univer = univer.enrollTeacher(arrayTeachers.get(i),
					arrayCourses.get((int) (univer.getCourses().size() * Math.random())));
		}

	}

	private static void deleteLectures() throws DaoException {
		System.out.println("delete Lectures:");
		LectureDao lectureDao = new LectureDao();
		Set<Lecture> lectures = academicPlan.getLectures();
		Iterator<Lecture> iLecture = lectures.iterator();
		while (iLecture.hasNext()) {
			lectureDao.delete(iLecture.next());
		}
	}

	private static void createLectures() throws DaoException {
		System.out.println("create lectures:");
		createAcademicPlan();
		LectureDao lectureDao = new LectureDao();
		List<Group> arrayGroups = new ArrayList<Group>(univer.getGroups());
		List<Course> arrayCourse = new ArrayList<Course>(univer.getCourses());
		for (int i = 0; i < arrayGroups.size(); i++) {
			Group group = arrayGroups.get(i);
			for (int j = 0; j < arrayCourse.size(); j++) {
				Course course = arrayCourse.get(j);
				List<String> topics = new ArrayList<String>(course.getTopics());
				Lecture lecture = null;
				for (int k = 0; k < topics.size(); k++) {
					lecture = new Lecture(group, course, topics.get(k));
					academicPlan.addLecture(lecture);
					lectureDao.create(lecture, academicPlan);
				}
			}
		}
	}

	private static void deleteStudents() throws DaoException {
		System.out.println("delete Students:");
		StudentDao studentDao = new StudentDao();
		Set<Student> entities = univer.getStudents();
		Iterator<Student> iterator = entities.iterator();
		while (iterator.hasNext()) {
			studentDao.delete(iterator.next());
		}
	}

	private static void createStudents() throws DaoException {
		System.out.println("create students:");
		StudentDao studentDao = new StudentDao();
		for (int i = 0; i < students.length; i++) {
			Student student = new Student(students[i], createRandomDayOfBirth("student"));
			studentDao.create(student);
		}
	}

	private static void deleteRoms() throws DaoException {
		System.out.println("delete Rooms:");
		RoomDao roomDao = new RoomDao();
		Set<Room> entities = new HashSet<Room>();
		entities = univer.getRooms();
		Iterator<Room> iterator = entities.iterator();
		while (iterator.hasNext()) {
			roomDao.delete(iterator.next());
		}
	}

	private static void createRooms() throws DaoException {
		System.out.println("create Rooms:");
		RoomDao roomDao = new RoomDao();
		for (int i = 0; i < 9; i++) {
			Room room = new Room((int) (4 * Math.random()) + "0" + (i + 1));
			roomDao.create(room);
		}
	}

	private static void deleteTeachers() throws DaoException {
		System.out.println("delete Teachers:");
		TeacherDao teacherDao = new TeacherDao();
		Set<Teacher> teachers = univer.getTeachers();
		Iterator<Teacher> iterator = teachers.iterator();
		while (iterator.hasNext()) {
			teacherDao.delete(iterator.next());
		}
	}

	private static void createTeachers() throws DaoException {
		System.out.println("create teachers:");
		TeacherDao dao = new TeacherDao();
		for (int i = 0; i < teachers.length; i++) {
			Teacher entity = new Teacher(teachers[i], createRandomDayOfBirth("teacher"));
			dao.create(entity);
		}
		univer = univerDao.update(univer);
	}

	private static void deleteGroups() throws DaoException {
		System.out.println("delete Groups:");
		GroupDao dao = new GroupDao();
		Set<Group> entities = univer.getGroups();
		Iterator<Group> iterator = entities.iterator();
		while (iterator.hasNext()) {
			dao.delete(iterator.next());
		}
	}

	private static void createGroups() throws DaoException {
		System.out.println("create Groups:");
		GroupDao groupDao = new GroupDao();
		for (int i = 0; i < courses.length; i++) {
			Group group = new Group(groups[i]);
			groupDao.create(group);
		}
	}

	private static void deleteCourses() throws DaoException {
		System.out.println("delete Courses:");
		CourseDao dao = new CourseDao();
		Set<Course> courses = univer.getCourses();
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			dao.delete(iterator.next());
		}

	}

	private static void createCourses() throws DaoException {
		System.out.println("create Courses:");
		CourseDao courseDao = new CourseDao();
		for (int i = 0; i < courses.length; i++) {
			Course course = new Course(courses[i]);
			courseDao.create(course);
			for (int j = 1; j < 6; j++) {
				courseDao.createTopic(course, "topic # " + j);
			}
		}
	}

	private static void deleteUnivers() throws DaoException {
		System.out.println("delete Univer:");
		univerDao.delete(univer);
		if (univer == null) {
			System.out.println("no data");
		}

	}

	private static void createUnivers() throws DaoException {
		new UniverDao().create(univer);
		univer = univerDao.update(univer);
	}

	private static Date createRandomDayOfBirth(String person) {
		int minAge = 25;
		int randomYears = 50;
		if (person.equals("student")) {
			minAge = 15;
			randomYears = 25;
		}
		java.util.Date date = new java.util.Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,
				(int) (2017 + (new Date(0).getTime() - (int) (minAge + randomYears * Math.random()))));
		calendar.set(Calendar.DAY_OF_YEAR, (int) (365 * Math.random()));
		date = calendar.getTime();
		Date sqlDate = new Date(date.getTime());
		return sqlDate;
	}
}
