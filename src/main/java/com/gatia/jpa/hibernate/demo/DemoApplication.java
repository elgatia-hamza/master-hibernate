package com.gatia.jpa.hibernate.demo;

import com.gatia.jpa.hibernate.demo.entity.*;
import com.gatia.jpa.hibernate.demo.repository.CourseRepository;
import com.gatia.jpa.hibernate.demo.repository.EmployeeRepository;
import com.gatia.jpa.hibernate.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//logger.info("Course 1001 -> {}", courseRepository.findById(1001L));
		//logger.info("Saving Student 'Hamza' with Passport 'D12345'");
		//studentRepository.saveStudentWithPassport();
		//courseRepository.addHardcodedReviewsForCourse();

		//List<Review> reviewList = new ArrayList<>();
		//reviewList.add(new Review("5", "great !!"));
		//reviewList.add(new Review("5", ":) good"));
		//courseRepository.addReviewsForCourse(1001L, reviewList);

//		Student student = new Student("Karim");
//		Course course = new Course("Groovy in 10 steps");
//		studentRepository.insertCourseAndStudent(student, course);
		/*employeeRepository.insert(new FullTimeEmployee("Jill", new BigDecimal("5000")));

		employeeRepository.insert(new PartTimeEmployee("Jack", new BigDecimal("50")));

		logger.info("All PartTimeEmployees -> {}", employeeRepository.retrievePartTimeEmployees());
		logger.info("All FullTimeEmployees -> {}", employeeRepository.retrieveFullTimeEmployees());*/
	}
}
