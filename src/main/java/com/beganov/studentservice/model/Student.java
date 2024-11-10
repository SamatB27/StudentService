package com.beganov.studentservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 50)
    private String lastName;

    @NotNull
    @Column(nullable = false, length = 50)
    private String firstName;

    private String middleName;

    @NotNull
    @Column(nullable = false, length = 50)
    private String studentGroup;

    @Min(1)
    @Max(5)
    @Column(precision = 2, scale = 1)
    private BigDecimal averageGrade;
}
