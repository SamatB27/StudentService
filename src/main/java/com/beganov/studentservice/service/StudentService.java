package com.beganov.studentservice.service;

import com.beganov.studentservice.dto.StudentRequest;
import com.beganov.studentservice.dto.StudentResponse;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentResponse> getAllStudents();
    Optional<StudentResponse> getStudentById(Long id);
    StudentResponse saveStudent(StudentRequest request);
    Optional <StudentResponse> updateStudent(Long id, StudentRequest request);
    void deleteStudent(Long id);
}
