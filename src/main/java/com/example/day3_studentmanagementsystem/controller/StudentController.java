package com.example.day3_studentmanagementsystem.controller;

import com.example.day3_studentmanagementsystem.dto.StudentRequestDto;
import com.example.day3_studentmanagementsystem.dto.StudentResponseDto;
import com.example.day3_studentmanagementsystem.model.StudentModel;
import com.example.day3_studentmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {

        this.service = service;

    }

    @PostMapping("/add-student")
    public StudentResponseDto addStudent(@RequestBody @Valid StudentRequestDto student) {

        return service.addStudent(student);

    }

    @GetMapping("/students")
    public List<StudentResponseDto> getStudents() {
        return service.getStudent();
    }

    @PutMapping("/update/{id}")
    public StudentResponseDto updateStudent(@PathVariable String id, @RequestBody @Valid StudentRequestDto student) {

        return service.updateStudent(id, student);

    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable String id) {

        return service.deleteStudent(id);

    }
}