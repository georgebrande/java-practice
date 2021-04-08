package com.doctor.appointment.repository;

import com.doctor.appointment.dto.AuthDto;

import java.util.Optional;

public interface DoctorRepositoryCustom {
    Optional<AuthDto> getByEmailWithPasswordAndRole(String email);
}
