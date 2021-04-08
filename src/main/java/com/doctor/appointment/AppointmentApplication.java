package com.doctor.appointment;

import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.model.Role;
import com.doctor.appointment.repository.DoctorRepository;
import com.doctor.appointment.repository.HobbyRepository;
import com.doctor.appointment.repository.RoleRepository;
import com.doctor.appointment.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

@SpringBootApplication
public class AppointmentApplication implements CommandLineRunner {

	@Autowired
	HobbyRepository hobbyRepository;

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(AppointmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		emailService.sendMail("george.brande@cst.ro", "test subject",
				"test text");

//		Optional<Doctor> optionalDoctor = doctorRepository.findByEmail("gxg@cst.ro");
//		if (optionalDoctor.isPresent()) {
//			Doctor doctor = optionalDoctor.get();
//
//			doctor.setPassword(BCrypt.hashpw("Password2", BCrypt.gensalt()));
//
//			doctorRepository.save(doctor);
//		}


//		Role admin = new Role("ROLE_ADMIN");
//		Role roleDefault = new Role("ROLE_DEFAULT");
//
//		roleRepository.save(admin);
//		roleRepository.save(roleDefault);

//		Optional<Doctor> optionalDoctor =
//				doctorRepository.findById(1L);
//
//		if (optionalDoctor.isPresent()) {
//			Doctor doctor = optionalDoctor.get();
//
//			Optional<Role> optionalRole = roleRepository.findByRole("ROLE_ADMIN");
//			if (optionalRole.isPresent()) {
//				Role role = optionalRole.get();
//
//				doctor.setRole(role);
//				doctorRepository.save(doctor);
//			}
//
//		}
//
//		Doctor defaultDoctor = new Doctor("gxg@cst.ro", "George", 18);
//		doctorRepository.save(defaultDoctor);
//
//		Optional<Role> optionalRole = roleRepository.findByRole("ROLE_DEFAULT");
//		if (optionalRole.isPresent()) {
//			Role role = optionalRole.get();
//
//			defaultDoctor.setRole(role);
//			doctorRepository.save(defaultDoctor);
//		}

	}

}
