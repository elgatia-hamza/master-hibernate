package com.gatia.jpa.hibernate.demo;

import com.gatia.jpa.hibernate.demo.entity.Course;
import com.gatia.jpa.hibernate.demo.entity.Student;
import com.gatia.jpa.hibernate.demo.repository.CourseRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class JPQLTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Before
	void dbInit(){
		em.persist(new Course("JPA in 50 steps"));
		em.persist(new Course("123 services"));
	}

	@Test
//	@DirtiesContext
	void findAll_basic() {
		Query query = em.createNamedQuery("query_get_all_courses");
		List courseList = query.getResultList();
		logger.info("SELECT c FROM Course c -> {}", courseList);
	}

	@Test
//	@DirtiesContext
	void findAll_typed() {
		TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> courseList = query.getResultList();
		logger.info("SELECT c FROM Course c -> {}", courseList);
	}

	@Test
//	@DirtiesContext
	void findAll_where() {
		TypedQuery<Course> query =
				em.createNamedQuery("query_get_100_step_course", Course.class);
		List<Course> courseList = query.getResultList();
		logger.info("SELECT c FROM Course c c.name like '%1000 steps' -> {}", courseList);
	}

	@Test
	void find_courses_without_students() {
		TypedQuery<Course> query =
				em.createQuery("select c from Course c where c.studentList is empty", Course.class);
		List<Course> courseList = query.getResultList();
		logger.info("results -> {}", courseList);
	}

	@Test
	void find_courses_at_least_2_students() {
		TypedQuery<Course> query =
				em.createQuery("select c from Course c order by size(c.studentList) desc", Course.class);
		List<Course> courseList = query.getResultList();
		logger.info("results -> {}", courseList);
	}

	@Test
	void find_student_passport_in_a_certain_pattern() {
		TypedQuery<Student> query =
				em.createQuery("select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> studentList = query.getResultList();
		logger.info("results -> {}", studentList);
	}

	// like
	// BETWEEN 100 and 1000
	// IS NULL
	// upper, lower, trim, length

	// JOIN => Select c, s from Course c JOIN c.studentList s
	// LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s => return course even if course don't have student
	// CROSS JOIN => Select c, s from Course c, Student s [3 c and 4 s => 12 rows]

	@Test
	void join() {
		Query query =
				em.createQuery("select c, s from Course c join c.studentList s");
		List<Object[]> resultList = query.getResultList();
		logger.info("results size -> {}", resultList.size());
		for (Object[] result: resultList){
			logger.info("Course: {}, Student: {}", result[0], result[1]);
		}
		logger.info("results -> {}", resultList);
	}

	@Test
	void left_join() {
		Query query =
				em.createQuery("select c, s from Course c left join c.studentList s");
		List<Object[]> resultList = query.getResultList();
		logger.info("results size -> {}", resultList.size());
		for (Object[] result: resultList){
			logger.info("Course: {}, Student: {}", result[0], result[1]);
		}
		logger.info("results -> {}", resultList);
	}

	@Test
	void cross_join() {
		Query query =
				em.createQuery("select c, s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("results size -> {}", resultList.size());
		for (Object[] result: resultList){
			logger.info("Course: {}, Student: {}", result[0], result[1]);
		}
		logger.info("results -> {}", resultList);
	}
}
