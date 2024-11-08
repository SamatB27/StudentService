package com.beganov.studentservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String studentGroup;
    private Double averageGrade;
}
