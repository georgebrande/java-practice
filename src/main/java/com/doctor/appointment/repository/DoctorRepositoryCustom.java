package com.doctor.appointment.repository;

import com.doctor.appointment.dto.AuthDto;
import com.doctor.appointment.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepositoryCustom {
    Optional<AuthDto> getByEmailWithPasswordAndRole(String email);

    List<Doctor> getPaginatedDoctors(int firstResults, int maxResults);
    long getTotalDoctors();
}
