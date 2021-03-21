package com.doctor.appointment.model;

import com.doctor.appointment.dto.CompanyDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy="company", cascade=CascadeType.ALL)
    private List<Employee> employeeList = new ArrayList<>();

    public Company(CompanyDto companyDto) {
        this.name = companyDto.getName();
    }

    public Company() {
    }

    public Company(String name) {
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    @JsonIgnore
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployeeToCompanyList(Employee employee) {
        employee.setCompany(this);
        this.employeeList.add(employee);
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList=employeeList;
    }
}
