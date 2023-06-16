package com.jesther.sms.service.impl;

import com.jesther.sms.dto.StudentDto;
import com.jesther.sms.entity.Student;
import com.jesther.sms.mapper.StudentMapper;
import com.jesther.sms.repository.StudentRepository;
import com.jesther.sms.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private StudentMapper mapper;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map((student) -> mapper.mapToStudentDto(student))
                .collect(Collectors.toList());
    }
}
