package com.gatia.jpa.hibernate.demo;

import com.gatia.jpa.hibernate.demo.entity.Course;
import com.gatia.jpa.hibernate.demo.repository.CourseSpringDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class CourseSpringDataRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseSpringDataRepository courseSpringDataRepository;

	@Test
	void course_isPresent(){
		Optional<Course> course = courseSpringDataRepository.findById(1001L);
		assertTrue(course.isPresent());
	}

	@Test
	void course_isNotPresent(){
		Optional<Course> course = courseSpringDataRepository.findById(2001L);
		assertFalse(course.isPresent());
	}

	@Test
	void course_sort_desc(){
		Sort sort = Sort.by(Sort.Direction.DESC, new String[]{"name"});
		logger.info("select c from Course c order by name desc -> {}", courseSpringDataRepository.findAll(sort));
	}

	@Test
	void course_pagination(){
		PageRequest pageRequest = PageRequest.of(0,3);
		Page<Course> firstPage = courseSpringDataRepository.findAll(pageRequest);
		logger.info("First page -> {}", firstPage.getContent());

		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = courseSpringDataRepository.findAll(secondPageable);
		logger.info("Second page -> {}", secondPage.getContent());

		Pageable thirdPageable = secondPage.nextPageable();
		Page<Course> thirdPage = courseSpringDataRepository.findAll(thirdPageable);
		logger.info("Third page -> {}", thirdPage.getContent());
	}

	@Test
	void course_customer_spring_data_query_findByName(){
		logger.info("findByName -> {}", courseSpringDataRepository.findByName("Dummy1"));
	}

	@Test
	void course_customer_spring_data_query_countByName(){
		logger.info("countByName -> {}", courseSpringDataRepository.countByName("Dummy1"));
	}

	@Test
	@DirtiesContext
	@Transactional
	void course_customer_spring_data_query_deleteByName(){
		logger.info("deleteByName -> {}", courseSpringDataRepository.deleteByName("Dummy1"));
	}

	@Test
	void course_customer_spring_data_query_courseWith100Steps(){
		logger.info("with100Steps -> {}", courseSpringDataRepository.courseWith100Steps());
	}
}
