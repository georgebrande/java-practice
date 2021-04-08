package com.doctor.appointment.controller;

import com.doctor.appointment.dto.*;
import com.doctor.appointment.exception.CustomEntityNotFoundException;
import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.repository.DoctorRepository;
import com.doctor.appointment.security.UserPrinciple;
import com.doctor.appointment.service.CompanyService;
import com.doctor.appointment.service.DoctorService;
import com.doctor.appointment.service.EmployeeService;
import com.doctor.appointment.service.MediaService;
import com.doctor.appointment.util.HttpStatusHelper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.doctor.appointment.util.HttpStatusHelper.success;

//controller => service => repository

@RestController
public class TestController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    HttpStatusHelper httpStatusHelper;

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("/getProfile")
    public UserInfoDto getProfile(OAuth2Authentication auth2Authentication) throws CustomEntityNotFoundException {
        UserPrinciple userPrinciple = (UserPrinciple) auth2Authentication.getPrincipal();

        String email = userPrinciple.getUsername();
        Doctor doctor = doctorRepository.findByEmail(email).orElseThrow(() -> new CustomEntityNotFoundException(Doctor.class));

        UserInfoDto infoDto = new UserInfoDto();
        infoDto.setEmail(doctor.getEmail());
        infoDto.setAge(doctor.getAge());
        infoDto.setName(doctor.getName());
        return infoDto;
    }

    @PreAuthorize("hasRole('ROLE_DEFAULT')")
    @GetMapping("/userAccess")
    public String getUserAccess() {
        return "user";
    }

//    ROLE_ADMIN > ROLE_DEFAULT

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/adminAccess")
    public String getAdminAccess() {
        return "admin";
    }

    @PostMapping("/addPicture")
    public ResponseEntity<Object> addPictureToEmployee(@RequestParam String fileName,
                                       @RequestParam Long employeeId,
                                       @RequestParam String type) {
        try {
            return success("ok", mediaService.addMediaToEmployee(fileName, employeeId, type));
        } catch (Exception e) {
            return httpStatusHelper.commonErrorMethod(e);
        }
    }

    @PostMapping("/addHobby")
    public ResponseEntity<Object> addHobbyToEmployee(@RequestBody EmployeeHobbyDto employeeHobbyDto) {
        return employeeService.addHobbyToEmployee(employeeHobbyDto);
    }

    @PostMapping("/createCompany")
    public String createCompany(@RequestBody CompanyDto companyDto) {
        return companyService.createCompany(companyDto);
    }

////    creezi un employee John Doe catre compania Euroins by Id
//    @PostMapping("/createEmployee")
//    public ResponseEntity<Object> createEmployee(@RequestBody CreateEmployeeDto createEmployeeDto) {
//        try {
//            return success("employee", employeeService.createEmployeeAndAssignToCompany(createEmployeeDto));
//        } catch (Exception e) {
//
//        }
//    }

    @GetMapping("/getCompanyByName")
    public ResponseEntity<Object> getCompanyByName(@RequestParam String companyName)
            throws Exception {
        return companyService.getCompanyByName(companyName);
    }


    @GetMapping("/publicEndpoint")
    public String getPublicEndpoint() {
        return "public";
    }

//    controller -> service -> repository
    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) throws Exception {
        try {
            return success("employee ",
                    employeeService.getEmployeeById(id));
        } catch (Exception e) {
            return httpStatusHelper.commonErrorMethod(e);
        }
    }

    @GetMapping("/getAll")
    public List<DoctorDto> getAllDoctors() {
        return doctorService.getAll();
    }

    @PostMapping("/register")
    public String addDoctor(@RequestBody DoctorDto doctorDto) {
        return doctorService.addDoctor(doctorDto);

    }

    @PostMapping("/get-doctors")
    public DoctorResponseDto getPaginatedDoctors(@RequestBody PagingDto pagingDto) {
        int page = pagingDto.getPage();
        int size = pagingDto.getSize();

        int firstResults = page * size;
        int maxResults = firstResults + size;

        List<Doctor> doctors = doctorRepository.getPaginatedDoctors(firstResults, maxResults);


        DoctorResponseDto responseDto = new DoctorResponseDto();
        responseDto.setDoctors(doctors);
        responseDto.setFrom(firstResults);
        responseDto.setTo(maxResults);

        return responseDto;
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
