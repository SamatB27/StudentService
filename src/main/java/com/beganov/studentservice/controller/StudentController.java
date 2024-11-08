package com.beganov.studentservice.controller;

import com.beganov.studentservice.dto.StudentRequest;
import com.beganov.studentservice.dto.StudentResponse;
import com.beganov.studentservice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> list = studentService.getAllStudents();
        return list.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Optional<StudentResponse> response = studentService.getStudentById(id);
        return response
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Студент с ID " + id + " не найден"));
    }

    @PutMapping
    public ResponseEntity<StudentResponse> saveStudent(@RequestBody StudentRequest request) {
        StudentResponse createdStudent = studentService.saveStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentRequest request) {
        Optional<StudentResponse> updatedStudent = studentService.updateStudent(id, request);
        if (updatedStudent.isPresent()) {
            return ResponseEntity.ok(updatedStudent.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Студент с ID " + id + " не существует");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        if (studentService.getStudentById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Студент с ID " + id + " не существует");
        } else {
            studentService.deleteStudent(id);
            return ResponseEntity.status(HttpStatus.OK).body("Студен с ID " + id + " удален" );
        }
    }
}
