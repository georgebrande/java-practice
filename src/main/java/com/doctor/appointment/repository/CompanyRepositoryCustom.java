package com.doctor.appointment.repository;

import com.doctor.appointment.model.Company;

import java.util.List;

public interface CompanyRepositoryCustom {

    List<Company> getCustomCompanies(String string);

//    JPQL
}
