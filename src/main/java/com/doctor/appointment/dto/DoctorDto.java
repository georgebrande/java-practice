package com.doctor.appointment.dto;

import com.doctor.appointment.model.Doctor;

public class DoctorDto {
    private String name;
    private int age;

    public DoctorDto() {
    }

    public DoctorDto(Doctor doctor) {
        this.name = doctor.getName();
        this.age = doctor.getAge();
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
