package se.g21.backend.enrollcoursesystem.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.stream.Collectors;

import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;

import se.g21.backend.enrollcoursesystem.entities.*;
import se.g21.backend.enrollcoursesystem.repository.*;

import se.g21.backend.employeesystem.entities.*;
import se.g21.backend.employeesystem.repository.*;

import se.g21.backend.studentsystem.entities.*;
import se.g21.backend.studentsystem.repository.*;

import se.g21.backend.coursesystem.entities.*;
import se.g21.backend.coursesystem.repository.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EnrollCourseController {

    @Autowired
    private final EnrollCourseRepository enrollCourseRepository;

    @Autowired
    private  StudentRepository studentRepository;

    @Autowired
    private  CourseRepository courseRepository;

    @Autowired
    private  EmployeeRepository employeeRepository;

    public EnrollCourseController(
        EnrollCourseRepository enrollCourseRepository ,
        StudentRepository studentRepository ,
        CourseRepository courseRepository ,
        EmployeeRepository employeeRepository 
        ) {

        this.enrollCourseRepository = enrollCourseRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/enrollCourse/")
    public Collection<EnrollCourse> EnrollCourse() {
        return enrollCourseRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/enrollCourse/{student_id}/{course_id}/{dateTime}/{employee_id}")
    public EnrollCourse newEnrollCourse(EnrollCourse newEnrollCourse,
    @PathVariable long student_id,
    @PathVariable long course_id,
    @PathVariable String dateTime,
    @PathVariable long employee_id) {

        Student student = studentRepository.findById(student_id);
        newEnrollCourse.setStudent(student);

        Course course = courseRepository.findById(course_id);
        newEnrollCourse.setCourse(course);

        
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataDate = LocalDateTime.parse((String)dateTime,dateFormat);
        newEnrollCourse.setDate(dataDate);

        Employee employee = employeeRepository.findById(employee_id);
        newEnrollCourse.setEmployee(employee);
        
    return enrollCourseRepository.save(newEnrollCourse);
    }


}