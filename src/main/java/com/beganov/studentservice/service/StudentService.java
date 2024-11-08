package com.beganov.studentservice.service;

import com.beganov.studentservice.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDto> getAllStudents();
    Optional<StudentDto> getStudentById(Long id);
    StudentDto saveStudent(StudentDto studentDto);
    Optional <StudentDto> updateStudent(Long id, StudentDto studentDto);
    void deleteStudent(Long id);
}
