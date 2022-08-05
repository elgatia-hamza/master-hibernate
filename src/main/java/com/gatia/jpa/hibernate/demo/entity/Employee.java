package com.gatia.jpa.hibernate.demo.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    protected Employee(){
    }

    protected Employee(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
