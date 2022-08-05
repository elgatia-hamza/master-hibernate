package com.gatia.jpa.hibernate.demo.repository;

import com.gatia.jpa.hibernate.demo.entity.Course;
import com.gatia.jpa.hibernate.demo.entity.Review;
import com.gatia.jpa.hibernate.demo.entity.ReviewRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    // findById
    public Course findById(Long id){
     return em.find(Course.class, id);
    }

    // save
    public Course save(Course course){
        if(course.getId() == null){
            em.persist(course);
        }else {
            em.merge(course);
        }

        return course;
    }

    // deleteById
    public void deleteById(Long id){
        Course course = findById(id);
        em.remove(course);
    }

    public void playWithEntityManager(){
        /*
        Course course1 = new Course("Master Hibernate");
        em.persist(course1);
        Course course2 = new Course("Master Webservices");
        em.persist(course2);

        em.flush();

        //em.detach(course1);

        course1.setName("Master Hibernate - update");
        course2.setName("Master Webservices - update");

        em.refresh(course1);*/

        Course course = new Course("Master Hibernate");
        // course.setName(null);
        em.persist(course);

        em.flush();
    }

    public void addHardcodedReviewsForCourse(){
        // get the course 1003
        Course course = findById(1003L);
        logger.info("course.getReviewList() -> {}", course.getReviewList());

        // add 2 reviews to it
        Review review1 = new Review(ReviewRating.FOUR,"Great Hands-on Stuff");
        Review review2 = new Review(ReviewRating.FIVE,"Great practical course!!!");

        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        // save it to the database
        em.persist(review1);
        em.persist(review2);
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviewList){
        Course course = findById(courseId);
        logger.info("course.getReviewList() -> {}", course.getReviewList());

        for (Review review: reviewList){
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        }
    }
}
