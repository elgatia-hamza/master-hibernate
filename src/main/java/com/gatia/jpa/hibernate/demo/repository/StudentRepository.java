package com.gatia.jpa.hibernate.demo.repository;

import com.gatia.jpa.hibernate.demo.entity.Course;
import com.gatia.jpa.hibernate.demo.entity.Passport;
import com.gatia.jpa.hibernate.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {
    @Autowired
    EntityManager em;

    public Student findById(Long id){
     return em.find(Student.class, id);
    }

    public Student save(Student student){
        if(student.getId() == null){
            em.persist(student);
        }else {
            em.merge(student);
        }

        return student;
    }

    public void deleteById(Long id){
        Student student = findById(id);
        em.remove(student);
    }

    public void playWithEntityManager(){
        Student student = new Student("Master Hibernate");
        em.persist(student);

        em.flush();
    }

    public void saveStudentWithPassport(){
        Passport passport = new Passport("D12345");
        em.persist(passport);

        Student student = new Student("Hamza");
        student.setPassport(passport);
        em.persist(student);
    }

    public void insertHardcodedCourseAndStudent(){
        Student student = new Student("Karim");
        Course course = new Course("Groovy in 10 steps");

        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
    }

    public void insertCourseAndStudent(Student student, Course course){
        //Student student = new Student("Karim");
        //Course course = new Course("Groovy in 10 steps");

        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
        em.persist(course);

    }
}
