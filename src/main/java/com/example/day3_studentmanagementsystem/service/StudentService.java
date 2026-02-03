package com.example.day3_studentmanagementsystem.service;

import com.example.day3_studentmanagementsystem.dto.StudentRequestDto;
import com.example.day3_studentmanagementsystem.dto.StudentResponseDto;
import com.example.day3_studentmanagementsystem.exception.StudentNotFoundException;
import com.example.day3_studentmanagementsystem.model.StudentModel;
import com.example.day3_studentmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {

        this.repository = repository;

    }

    // create
    public StudentResponseDto addStudent(StudentRequestDto dto) {

        StudentModel student = new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel savedStudent = repository.save(student);

        return new StudentResponseDto(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getAge(),
                savedStudent.getEmail());
    }

    // display students

    public List<StudentResponseDto> getStudent() {
        return repository.findAll().stream().map(student -> new StudentResponseDto(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getEmail())).toList();
    }

    public StudentResponseDto updateStudent(String id, StudentRequestDto dto) {

        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("No student found with this id"));
        existingStudent.setName(dto.getName());
        existingStudent.setAge(dto.getAge());
        existingStudent.setEmail(dto.getEmail());

        StudentModel savedStudent = repository.save(existingStudent);

        return new StudentResponseDto(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getAge(),
                savedStudent.getEmail());
    }

    public String deleteStudent(String id) {

        StudentModel student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        repository.delete(student);

        return "Student deleted!";
    }
}