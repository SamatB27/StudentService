package com.beganov.studentservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StudentRequest {

    @NotNull
    @Size(max = 50)
    private String firstName;

    @NotNull
    @Size(max = 50)
    private String lastName;

    private String middleName;

    @NotNull
    @Size(max = 50)
    private String studentGroup;

    @Min(1)
    @Max(5)
    private BigDecimal averageGrade;
}
