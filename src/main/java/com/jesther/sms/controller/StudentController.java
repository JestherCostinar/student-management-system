package com.jesther.sms.controller;

import com.jesther.sms.dto.StudentDto;
import com.jesther.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // handler method to handle list of students
    @GetMapping
    public String listOfStudents(Model model) {
        List<StudentDto> students = studentService.getAllStudents();
        model.addAttribute("students", students);

        return "students";
    }
}
