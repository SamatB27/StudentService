package com.beganov.studentservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String studentGroup;
    private BigDecimal averageGrade;
}
