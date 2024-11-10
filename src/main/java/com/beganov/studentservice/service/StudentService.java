package com.beganov.studentservice.service;

import com.beganov.studentservice.dto.StudentRequest;
import com.beganov.studentservice.dto.StudentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    ResponseEntity<List<StudentResponse>> getAllStudents();

    ResponseEntity<StudentResponse> getStudentById(Long id);

    ResponseEntity<StudentResponse> saveStudent(StudentRequest request);

    ResponseEntity<StudentResponse> updateStudent(Long id, StudentRequest request);

    ResponseEntity<Void> deleteStudent(Long id);
}
