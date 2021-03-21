package com.doctor.appointment.service;

import com.doctor.appointment.dto.CreateEmployeeDto;
import com.doctor.appointment.model.Company;
import com.doctor.appointment.model.Employee;
import com.doctor.appointment.repository.CompanyRepository;
import com.doctor.appointment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseEntity<Object> getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> createEmployeeAndAssignToCompany(CreateEmployeeDto createEmployeeDto) {
        Long companyId = createEmployeeDto.getCompanyId();

        Optional<Company> optionalCompany = companyRepository.findById(companyId);

        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();

            Employee employee = new Employee(createEmployeeDto.getEmployeeName());

            company.addEmployeeToCompanyList(employee);
            employeeRepository.save(employee);

            return new ResponseEntity<>("saved", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
    }
}
