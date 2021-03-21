package com.doctor.appointment.repository;

import com.doctor.appointment.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long>,
CompanyRepositoryCustom {
    List<Company> findByName(String name);
}
