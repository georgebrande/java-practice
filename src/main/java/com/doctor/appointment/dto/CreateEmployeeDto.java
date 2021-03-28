package com.doctor.appointment.dto;

public class CreateEmployeeDto {
    private Long companyId;
    private String employeeName;
    private Integer age;

    public Long getCompanyId() {
        return companyId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Integer getAge() {
        return age;
    }
}
