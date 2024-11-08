package com.beganov.studentservice.service;

import com.beganov.studentservice.dto.StudentRequest;
import com.beganov.studentservice.dto.StudentResponse;
import com.beganov.studentservice.mapper.StudentMapper;
import com.beganov.studentservice.model.Student;
import com.beganov.studentservice.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StudentResponse> getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toResponse);
    }

    @Override
    public StudentResponse saveStudent(StudentRequest request) {
        Student student = studentMapper.toEntity(request);
        Student saved = studentRepository.save(student);
        return studentMapper.toResponse(saved);
    }

    @Override
    public Optional<StudentResponse> updateStudent(Long id, StudentRequest request) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setLastName(request.getLastName());
                    existingStudent.setFirstName(request.getFirstName());
                    existingStudent.setMiddleName(request.getMiddleName());
                    existingStudent.setStudentGroup(request.getStudentGroup());
                    existingStudent.setAverageGrade(request.getAverageGrade());
                    Student updatedStudent = studentRepository.save(existingStudent);
                    return studentMapper.toResponse(updatedStudent);
                });
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
