package com.gatia.jpa.hibernate.demo;

import com.gatia.jpa.hibernate.demo.entity.Course;
import com.gatia.jpa.hibernate.demo.entity.Student;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class CriteriaTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	void criteria_basic() {
		// SELECT c FROM Course c
		// 1. Use criteria builder to create criteria query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define the roots for tables which are involved in the query.
		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define predicates etc using Criteria Builder

		// 4. Add predicates etc to criteria query.

		// 5. Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> courseList = query.getResultList();
		logger.info("SELECT c FROM Course c -> {}", courseList);
	}


	@Test
	void criteria_having_100_steps() {
		// SELECT c FROM Course c where name like '%100 Steps'
		// 1. Use criteria builder to create criteria query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define the roots for tables which are involved in the query.
		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define predicates etc using Criteria Builder
		Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");

		// 4. Add predicates etc to criteria query.
		cq.where(like100Steps);

		// 5. Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> courseList = query.getResultList();
		logger.info("SELECT c FROM Course c where name like '%100 Steps' -> {}", courseList);
	}

	@Test
	void criteria_all_courses_without_students() {
		// SELECT c FROM Course c where student is null
		// 1. Use criteria builder to create criteria query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define the roots for tables which are involved in the query.
		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define predicates etc using Criteria Builder
		Predicate coursesWithoutStudent = cb.isEmpty(courseRoot.get("studentList"));

		// 4. Add predicates etc to criteria query.
		cq.where(coursesWithoutStudent);

		// 5. Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> courseList = query.getResultList();
		logger.info("SELECT c FROM Course c where student is null -> {}", courseList);
	}

	@Test
	void criteria_join() {
		// SELECT c FROM Course c Join c.studentList
		// 1. Use criteria builder to create criteria query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define the roots for tables which are involved in the query.
		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define predicates etc using Criteria Builder
		courseRoot.join("studentList");

		// 4. Add predicates etc to criteria query.

		// 5. Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> courseList = query.getResultList();
		logger.info("result size-> {}", courseList.size());
		logger.info("SELECT c FROM Course c Join c.studentList -> {}", courseList);
	}

	@Test
	void criteria_left_join() {
		// SELECT c FROM Course c Join c.studentList
		// 1. Use criteria builder to create criteria query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define the roots for tables which are involved in the query.
		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define predicates etc using Criteria Builder
		courseRoot.join("studentList", JoinType.LEFT);

		// 4. Add predicates etc to criteria query.

		// 5. Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> courseList = query.getResultList();
		logger.info("result size-> {}", courseList.size());
		logger.info("SELECT c FROM Course c Join c.studentList -> {}", courseList);
	}

}
