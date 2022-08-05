package com.gatia.jpa.hibernate.demo;

import com.gatia.jpa.hibernate.demo.entity.Course;
import com.gatia.jpa.hibernate.demo.entity.Review;
import com.gatia.jpa.hibernate.demo.entity.Student;
import com.gatia.jpa.hibernate.demo.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class PerformanceTuningTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	EntityManager em;

	@Test
	@DirtiesContext
	void creatingNPlusOneProblem_basic() {
		List<Course> courses = em.createNamedQuery("query_get_all_courses").getResultList();
		for(Course course: courses){
			logger.info("Course -> {} Students -> {}", course, course.getStudentList());
		}
	}

	@Test
	@DirtiesContext
	void solvingNPlusOneProblem_EntityGraph() {
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		Subgraph<Object> subgraph = entityGraph.addSubgraph("studentList");

		List<Course> courses = em.createNamedQuery("query_get_all_courses")
				.setHint("javax.persistence.loadgraph", entityGraph)
				.getResultList();
		for(Course course: courses){
			logger.info("Course -> {} Students -> {}", course, course.getStudentList());
		}
	}

	@Test
	void solvingNPlusOneProblem_joinFetch() {
		List<Course> courses = em.createNamedQuery("query_get_all_courses_join_fetch")
				.getResultList();
		for(Course course: courses){
			logger.info("Course -> {} Students -> {}", course, course.getStudentList());
		}
	}
}
