package com.doctor.appointment.repository;

import com.doctor.appointment.dto.AuthDto;
import com.doctor.appointment.model.Doctor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
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

    @Override
    public List<Doctor> getPaginatedDoctors(int firstResults, int maxResults) {

        String query = "select d from Doctor d ";

        List<Doctor> doctors = entityManager.createQuery(query, Doctor.class)
                .setFirstResult(firstResults)
                .setMaxResults(maxResults)
                .getResultList();

        return doctors;
    }
}
