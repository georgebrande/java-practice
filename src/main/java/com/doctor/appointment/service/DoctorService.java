package com.doctor.appointment.service;

import com.doctor.appointment.dto.DoctorDto;
import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<DoctorDto> getAll() {

        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDto> doctorDtoList = new ArrayList<>();
        for (Doctor doctor: doctors) {
            DoctorDto doctorDto = new DoctorDto(doctor);
            doctorDtoList.add(doctorDto);
        }

        return doctorDtoList;
    }

    public String addDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor(doctorDto);
        doctorRepository.save(doctor);
        return "Ok";
    }
}
