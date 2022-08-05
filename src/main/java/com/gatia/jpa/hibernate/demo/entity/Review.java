package com.gatia.jpa.hibernate.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ReviewRating rating;

    private String description;

    @ManyToOne
    private Course course;

    protected Review(){}

    public Review(ReviewRating rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public ReviewRating getRating() {
        return rating;
    }

    public void setRating(ReviewRating rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                ", course=" + course +
                '}';
    }
}
