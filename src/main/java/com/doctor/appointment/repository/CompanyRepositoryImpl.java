package com.doctor.appointment.repository;

import com.doctor.appointment.model.Company;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CompanyRepositoryImpl implements CompanyRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Company> getCustomCompanies(String companyName) {
        return entityManager.createQuery("select c from Company c where c.name = :name ", Company.class)
                .setParameter("name", companyName)
                .getResultList();
    }
}
