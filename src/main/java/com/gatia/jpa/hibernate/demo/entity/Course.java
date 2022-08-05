package com.gatia.jpa.hibernate.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NamedQueries(value = {
        @NamedQuery(name = "query_get_all_courses", query = "select c from Course c"),
        @NamedQuery(name = "query_get_all_courses_join_fetch", query = "select c from Course c JOIN FETCH c.studentList s"),
        @NamedQuery(name = "query_get_100_step_course", query = "select c from Course c where c.name like '%100 steps'")
})
@SQLDelete(sql="update course set is_deleted=true where id=?")
@Where(clause = "is_deleted = false")
@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "course")
    private List<Review> reviewList;

    @JsonIgnore
    @ManyToMany(mappedBy = "courseList")
    List<Student> studentList = new ArrayList<>();

    @NotNull
    boolean isDeleted=false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastUpdateAt;

    protected Course(){}

    public Course(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void addReview(Review review){
        this.reviewList.add(review);
    }

    public void removeReview(Review review){
        this.reviewList.remove(review);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void addStudent(Student student){
        studentList.add(student);
    }

    public void removeStudent(Student student){
        studentList.remove(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
