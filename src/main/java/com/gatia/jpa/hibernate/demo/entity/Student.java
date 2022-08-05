package com.gatia.jpa.hibernate.demo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    @Embedded
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "STUDENT_COURSE",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    List<Course> courseList = new ArrayList<>();

    protected Student(){}

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Address address) {
        this.name = name;
        this.address = address;
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

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void addCourse(Course course){
        courseList.add(course);
    }

    public void removeCourse(Course course){
        courseList.remove(course);
    }

    @Override
    public String toString() {
        return "\nStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
