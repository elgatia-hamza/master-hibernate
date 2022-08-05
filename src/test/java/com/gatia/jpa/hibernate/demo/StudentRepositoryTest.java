package com.gatia.jpa.hibernate.demo;

import com.gatia.jpa.hibernate.demo.entity.Address;
import com.gatia.jpa.hibernate.demo.entity.Course;
import com.gatia.jpa.hibernate.demo.entity.Passport;
import com.gatia.jpa.hibernate.demo.entity.Student;
import com.gatia.jpa.hibernate.demo.repository.CourseRepository;
import com.gatia.jpa.hibernate.demo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class StudentRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;

	@Autowired
	EntityManager em;

	@Test
	@Transactional
	void retrieveStudentAndPassportDetails(){
		Student student = em.find(Student.class, 2001L);
		logger.info("Student -> {}", student);
		logger.info("Passport -> {}", student.getPassport());
	}

	@Test
	@Transactional
	void retrievePassportAndAssociatedStudent(){
		Passport passport = em.find(Passport.class, 3001L);
		logger.info("Passport -> {}", passport);
		logger.info("Student -> {}", passport.getStudent());
	}

	@Test
	@Transactional
	void retrieveStudentAndCourse(){
		Student student = em.find(Student.class, 2001L);
		logger.info("Student -> {}", student);
		logger.info("Course list -> {}", student.getCourseList());
	}

	@Test
	@Transactional
	void saveStudentWithAddress(){
		Student student = new Student("Hamada", new Address("line1","line2","casa"));
		logger.info("Student -> {}", repository.save(student));
	}
}
