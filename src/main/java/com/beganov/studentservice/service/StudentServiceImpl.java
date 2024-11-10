package com.beganov.studentservice.service;

import com.beganov.studentservice.dto.StudentRequest;
import com.beganov.studentservice.dto.StudentResponse;
import com.beganov.studentservice.mapper.StudentMapper;
import com.beganov.studentservice.model.Student;
import com.beganov.studentservice.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> responseList = studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .toList();
        if (responseList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(responseList);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<StudentResponse> getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student
                .map(value -> ResponseEntity.ok(studentMapper.toResponse(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

    @Override
    public ResponseEntity<StudentResponse> saveStudent(StudentRequest request) {
        Student student = studentMapper.toEntity(request);
        Student saved = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper.toResponse(saved));
    }

    @Override
    public ResponseEntity<StudentResponse> updateStudent(Long id, StudentRequest request) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()) {
            existingStudent.get().setLastName(request.getLastName());
            existingStudent.get().setFirstName(request.getFirstName());
            existingStudent.get().setMiddleName(request.getMiddleName());
            existingStudent.get().setStudentGroup(request.getStudentGroup());
            existingStudent.get().setAverageGrade(request.getAverageGrade());
            Student updatedStudent = studentRepository.save(existingStudent.get());
            return ResponseEntity.ok(studentMapper.toResponse(updatedStudent));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public ResponseEntity<Void> deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
