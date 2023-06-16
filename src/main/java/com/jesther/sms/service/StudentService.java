package com.jesther.sms.service;

import com.jesther.sms.dto.StudentDto;
import jakarta.validation.constraints.Future;

import java.util.List;

public interface StudentService {

    List<StudentDto> getAllStudents();

    void createStudent(StudentDto student);

    StudentDto getStudentById(Long id);

    void updateStudent(StudentDto studentDto);
}
