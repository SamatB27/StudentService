package com.beganov.studentservice.mapper;

import com.beganov.studentservice.dto.StudentRequest;
import com.beganov.studentservice.dto.StudentResponse;
import com.beganov.studentservice.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentResponse toResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setLastName(student.getLastName());
        response.setFirstName(student.getFirstName());
        response.setMiddleName(student.getMiddleName());
        response.setStudentGroup(student.getStudentGroup());
        response.setAverageGrade(student.getAverageGrade());
        return response;
    }

    public Student toEntity(StudentRequest request) {
        Student student = new Student();
        student.setLastName(request.getLastName());
        student.setFirstName(request.getFirstName());
        student.setMiddleName(request.getMiddleName());
        student.setStudentGroup(request.getStudentGroup());
        student.setAverageGrade(request.getAverageGrade());
        return student;
    }
}
