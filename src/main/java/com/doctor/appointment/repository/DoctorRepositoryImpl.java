package com.doctor.appointment.repository;

import com.doctor.appointment.dto.AuthDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class DoctorRepositoryImpl implements DoctorRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<AuthDto> getByEmailWithPasswordAndRole(String email) {

        String query = "select new com.doctor.appointment.dto.AuthDto" +
                " (d.email, d.password, d.role.role) " +
                " from Doctor d " +
                " where d.email = :email ";

        AuthDto authDto =
                entityManager.createQuery(query, AuthDto.class)
                .setParameter("email", email)
                .getSingleResult();

        return Optional.of(authDto);
    }
}
