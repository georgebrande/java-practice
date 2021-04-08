package com.doctor.appointment.dto;

import com.doctor.appointment.model.Doctor;

import java.util.List;

public class DoctorResponseDto {
    private List<Doctor> doctors;
    private int totalDoctors;
    private int from;
    private int to;

    public DoctorResponseDto() {
    }

    public DoctorResponseDto(List<Doctor> doctors, int totalDoctors, int from, int to) {
        this.doctors=doctors;
        this.totalDoctors=totalDoctors;
        this.from=from;
        this.to=to;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors=doctors;
    }

    public int getTotalDoctors() {
        return totalDoctors;
    }

    public void setTotalDoctors(int totalDoctors) {
        this.totalDoctors=totalDoctors;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from=from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to=to;
    }
}
