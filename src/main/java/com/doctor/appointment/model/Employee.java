package com.doctor.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

//    email

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="employee_hobbies",
    joinColumns=@JoinColumn(name="employee_id"),
    inverseJoinColumns=@JoinColumn(name="hobby_id"))
    private Set<Hobby> hobbySet = new HashSet<>();

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="profileImage_id", referencedColumnName="id")
    private Media profileImage;

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

    public void addHobbyToEmployee(Hobby hobby) {
        this.hobbySet.add(hobby);
    }

    public void deleteHobbyFromEmployee(Hobby hobby) {
        this.hobbySet.remove(hobby);
    }

    public Set<Hobby> getHobbySet() {
        return hobbySet;
    }

    public void setHobbySet(Set<Hobby> hobbySet) {
        this.hobbySet=hobbySet;
    }

    public Media getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Media profileImage) {
        this.profileImage=profileImage;
    }
}
