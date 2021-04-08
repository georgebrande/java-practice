package com.doctor.appointment.security;

import com.doctor.appointment.dto.AuthDto;
import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.model.Role;
import com.doctor.appointment.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

//    private static List<String> userList =Arrays.asList("gxg@cst.ro");

    private DoctorRepository doctorRepository;

    @Autowired
    public void setServices(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String email = authentication.getName().trim();
        String inputPassword = authentication.getCredentials().toString();


        Optional<AuthDto> optionalAuthDto = doctorRepository.getByEmailWithPasswordAndRole(email);
//        Optional<Doctor> optionalDoctor = doctorRepository.findByEmail(email);

        if (optionalAuthDto.isPresent()) {

            AuthDto authDto = optionalAuthDto.get();
//            Doctor doctor = optionalDoctor.get();
            String dbPassword = authDto.getPassword();

            if (BCrypt.checkpw(inputPassword, dbPassword)) {
                UserDetails userDetails = UserPrinciple.build(authDto);
                Authentication newAuth = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuth);

                return newAuth;
            } throw new BadCredentialsException("Wrong password");

        } return null;
    }
}
