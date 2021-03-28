package com.doctor.appointment;

import com.doctor.appointment.model.Hobby;
import com.doctor.appointment.repository.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppointmentApplication implements CommandLineRunner {

	@Autowired
	HobbyRepository hobbyRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppointmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Hobby hobby1 = new Hobby("fishing");
//		Hobby hobby2 = new Hobby("hunting");
//
//		hobbyRepository.save(hobby1);
//		hobbyRepository.save(hobby2);
	}

}
