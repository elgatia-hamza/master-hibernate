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

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class CourseRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	EntityManager em;

	@Test
	@DirtiesContext
	void findById_basic() {
		courseRepository.save(new Course("JPA in 50 steps"));

		Course course = courseRepository.findById(1L);
		assertEquals("JPA in 50 Steps", course.getName());
	}

	@Test
	@DirtiesContext
	void deleteById_basic() {
		courseRepository.deleteById(1001L);
		assertNull(courseRepository.findById(1001L));
	}

	@Test
	@DirtiesContext
	void save_basic() {
		Course persistedCourse = courseRepository.save(new Course("123 services"));
		Course course = courseRepository.findById(persistedCourse.getId());
		assertEquals("123 services", course.getName());

		// Update
		Course foundCourse = courseRepository.findById(1L);
		foundCourse.setName("123 services - updated");
		courseRepository.save(foundCourse);

		assertEquals("123 services - updated", courseRepository.findById(1L).getName());
	}

	@Test
	@DirtiesContext
	void playWithEntityManager() {
		// courseRepository.playWithEntityManager();
	}

	@Test
	@Transactional
	void retrieveCourseForReview() {
		Review review = em.find(Review.class, 4001L);
		logger.info("{}", review.getCourse());
	}

	@Test
	@Transactional
	void retrieveReviewForCourse() {
		Course course = courseRepository.findById(1001L);
		logger.info("{}", course.getReviewList());
	}

	@Test
	@org.springframework.transaction.annotation.Transactional(isolation = Isolation.READ_COMMITTED)
	void retrieveCourseAndStudents(){
		Course course = courseRepository.findById(1001L);
		logger.info("Course -> {}", course);
		logger.info("Student list -> {}", course.getStudentList());
	}

	@Test
	void firstLevelCash(){
		Course course = courseRepository.findById(1001L);
		logger.info("course -> {}", course.getName());

		Course course1 = courseRepository.findById(1001L);
		logger.info("course retrieved again -> {}", course1.getName());
	}
}
