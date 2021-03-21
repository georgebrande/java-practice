package com.doctor.appointment.model;


import com.doctor.appointment.dto.DoctorDto;

import javax.persistence.*;

@Entity
@Table(name="doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    public Doctor() {
    }

    public Doctor(DoctorDto doctorDto) {
        this.name=doctorDto.getName();
        this.age=doctorDto.getAge();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age=age;
    }
}
