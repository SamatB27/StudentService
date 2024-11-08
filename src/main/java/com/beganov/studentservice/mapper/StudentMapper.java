package com.beganov.studentservice.mapper;

import com.beganov.studentservice.dto.StudentDto;
import com.beganov.studentservice.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto toDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setLastName(student.getLastName());
        dto.setFirstName(student.getFirstName());
        dto.setMiddleName(student.getMiddleName());
        dto.setGroup(student.getStudentGroup());
        dto.setAverageGrade(student.getAverageGrade());
        return dto;
    }

    public Student toEntity(StudentDto dto) {
        Student student = new Student();
        student.setLastName(dto.getLastName());
        student.setFirstName(dto.getFirstName());
        student.setMiddleName(dto.getMiddleName());
        student.setStudentGroup(dto.getGroup());
        student.setAverageGrade(dto.getAverageGrade());
        return student;
    }
}
