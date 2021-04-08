package com.doctor.appointment.service;

import com.doctor.appointment.dto.AuthDto;
import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.repository.DoctorRepository;
import com.doctor.appointment.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<AuthDto> optionalAuthDto = doctorRepository.getByEmailWithPasswordAndRole(email);
//        Optional<Doctor> optionalDoctor = doctorRepository.findByEmail(email);

        if (optionalAuthDto.isPresent()) {
            return UserPrinciple.build(optionalAuthDto.get());
        } throw new UsernameNotFoundException("No User found with email: " + email);

    }
}
