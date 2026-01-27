package com.example.day3_studentmanagementsystem.service;

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

    // Add student
    public StudentModel addStudent(StudentModel student) {
        return repository.save(student);
    }

    // Display all students
    public List<StudentModel> getStudents() {
        return repository.findAll();
    }

    // Update student
    public StudentModel updateStudent(String id, StudentModel student) {

        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No student found with id: " + id));

        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());

        return repository.save(existingStudent);
    }
    public String deleteStudent(String id) {

        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No student found with id: " + id));

        repository.delete(existingStudent);

        return "Student deleted successfully";
    }
}
