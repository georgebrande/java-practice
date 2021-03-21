package com.doctor.appointment.service;

import com.doctor.appointment.dto.CompanyDto;
import com.doctor.appointment.model.Company;
import com.doctor.appointment.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public String createCompany(CompanyDto companyDto) {
        Company company = new Company(companyDto);
        companyRepository.save(company);

        return "saved";
    }

    public ResponseEntity<Object> getCompanyByName(String companyName) throws Exception {

        List<Company> companyList = companyRepository.getCustomCompanies(companyName);

        if (companyList.size() > 0) {
            Company company = companyList.get(0);
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        throw new Exception("Not Found with name: " + companyName);
    }
}
