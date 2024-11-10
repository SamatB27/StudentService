package com.beganov.studentservice.controller;

import com.beganov.studentservice.dto.StudentRequest;
import com.beganov.studentservice.dto.StudentResponse;
import com.beganov.studentservice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Вошел в систему");
    }

    @GetMapping("/oauth2/callback")
    public Map<String, String> handleGoogleCallback(@RequestParam("code") String code) {
        System.out.println("Authorization code received: " + code);
        Map<String, String> response = new HashMap<>();
        response.put("authorization_code", code);
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStudents() {
        ResponseEntity<List<StudentResponse>> responseList = studentService.getAllStudents();
        if (responseList.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("В базе не существует ни одного студента");
        } else {
            return studentService.getAllStudents();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        ResponseEntity<StudentResponse> studentResponse = studentService.getStudentById(id);
        if (studentResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Студент с ID " + id + " не существует");
        } else {
            return studentResponse;
        }
    }

    @PutMapping
    public ResponseEntity<StudentResponse> saveStudent(@RequestBody StudentRequest request) {
        return studentService.saveStudent(request);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentRequest request) {
        ResponseEntity<StudentResponse> updatedStudentResponse = studentService.updateStudent(id, request);
        if (updatedStudentResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Студент с ID " + id + " не существует");
        } else {
            return updatedStudentResponse;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        ResponseEntity<Void> response = studentService.deleteStudent(id);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Студент с ID " + id + " не существует");
        } else {
            studentService.deleteStudent(id);
            return ResponseEntity.status(HttpStatus.OK).body("Студен с ID " + id + " удален");
        }
    }
}
