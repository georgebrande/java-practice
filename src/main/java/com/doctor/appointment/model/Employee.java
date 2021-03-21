package com.doctor.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    private Company company;

    public Employee() {
    }

    public Employee(String name) {
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company=company;
    }
}
