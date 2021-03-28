package com.doctor.appointment.service;

import com.doctor.appointment.dto.CreateEmployeeDto;
import com.doctor.appointment.dto.EmployeeHobbyDto;
import com.doctor.appointment.exception.Conflict;
import com.doctor.appointment.exception.CustomConflictException;
import com.doctor.appointment.exception.CustomEntityNotFoundException;
import com.doctor.appointment.model.Company;
import com.doctor.appointment.model.Employee;
import com.doctor.appointment.model.Hobby;
import com.doctor.appointment.repository.CompanyRepository;
import com.doctor.appointment.repository.EmployeeRepository;
import com.doctor.appointment.repository.HobbyRepository;
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

    @Autowired
    private HobbyRepository hobbyRepository;

    public ResponseEntity<Object> addHobbyToEmployee(EmployeeHobbyDto employeeHobbyDto) {

        long employeeId = employeeHobbyDto.getEmployeeId();
        long hobbyId = employeeHobbyDto.getHobbyId();

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Optional<Hobby> optionalHobby = hobbyRepository.findById(hobbyId);

        if (optionalEmployee.isPresent() && optionalHobby.isPresent()) {
            Employee employee = optionalEmployee.get();
            Hobby hobby = optionalHobby.get();

            employee.addHobbyToEmployee(hobby);

            employeeRepository.save(employee);

            return new ResponseEntity<>("saved", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    public Employee getEmployeeById(Long id) throws Exception {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            return employee;
        } throw new CustomEntityNotFoundException(Employee.class);
    }

    public ResponseEntity<Object> createEmployeeAndAssignToCompany(CreateEmployeeDto createEmployeeDto) throws CustomConflictException {

        if (createEmployeeDto.getAge() <= 0) {
            throw new CustomConflictException(Conflict.INVALID_AGE);
        }
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
