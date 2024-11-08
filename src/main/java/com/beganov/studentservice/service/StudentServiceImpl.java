package com.beganov.studentservice.service;

import com.beganov.studentservice.dto.StudentDto;
import com.beganov.studentservice.exception.EmptyValueException;
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
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StudentDto> getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toDto);
    }

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        Student saved = studentRepository.save(student);
        return studentMapper.toDto(saved);
    }

    @Override
    public Optional<StudentDto> updateStudent(Long id, StudentDto studentDto) {
        if (!studentRepository.existsById(id)) {
            throw new EmptyValueException("Student with id " + id + " does not exist");
        }
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setLastName(studentDto.getLastName());
                    existingStudent.setFirstName(studentDto.getFirstName());
                    existingStudent.setMiddleName(studentDto.getMiddleName());
                    existingStudent.setStudentGroup(studentDto.getGroup());
                    existingStudent.setAverageGrade(studentDto.getAverageGrade());
                    Student updatedStudent = studentRepository.save(existingStudent);
                    return studentMapper.toDto(updatedStudent);
                });
    }

    @Override
    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new EmptyValueException("Student with id " + id + " does not exist");
        }
    }
}
