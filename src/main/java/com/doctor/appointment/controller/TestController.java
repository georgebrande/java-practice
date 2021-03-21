package com.doctor.appointment.controller;

import com.doctor.appointment.dto.CompanyDto;
import com.doctor.appointment.dto.CreateEmployeeDto;
import com.doctor.appointment.dto.DoctorDto;
import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.service.CompanyService;
import com.doctor.appointment.service.DoctorService;
import com.doctor.appointment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//controller => service => repository

@RestController
public class TestController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/createCompany")
    public String createCompany(@RequestBody CompanyDto companyDto) {
        return companyService.createCompany(companyDto);
    }

//    creezi un employee John Doe catre compania Euroins by Id
    @PostMapping("/createEmployee")
    public ResponseEntity<Object> createEmployee(@RequestBody CreateEmployeeDto createEmployeeDto) {
        return employeeService.createEmployeeAndAssignToCompany(createEmployeeDto);
    }

    @GetMapping("/getCompanyByName")
    public ResponseEntity<Object> getCompanyByName(@RequestParam String companyName)
            throws Exception {
        return companyService.getCompanyByName(companyName);
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/getAll")
    public List<DoctorDto> getAllDoctors() {
        return doctorService.getAll();
    }

    @PostMapping("/register")
    public String addDoctor(@RequestBody DoctorDto doctorDto) {
        return doctorService.addDoctor(doctorDto);

    }

    @GetMapping("/test")
    public String getTest() {
        return "Ok";
    }

    @GetMapping("/add")
    public String addMethod(@RequestParam String value) {

        return value;
    }

    @GetMapping("/doctor/{id}")
    public int sendDoctor(@PathVariable int id) {
        return id;
    }

    @PostMapping("/catalog")
    public String addToCatalog(@RequestBody DoctorDto doctorDto) {
        System.out.println(doctorDto.getName());
        System.out.println(doctorDto.getAge());

        return "Ok";
    }

}
