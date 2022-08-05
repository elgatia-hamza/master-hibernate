package com.gatia.jpa.hibernate.demo.repository;

import com.gatia.jpa.hibernate.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
    List<Course> findByName(String name);
    int countByName(String name);
    int deleteByName(String name);

    @Query("select c from Course c where c.name like '%100 Steps'")
    List<Course> courseWith100Steps();

}
