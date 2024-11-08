package com.beganov.studentservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private String lastName;
    private String firstName;
    private String middleName;
    private String group;
    private Double averageGrade;
}
