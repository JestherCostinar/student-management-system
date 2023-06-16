package com.jesther.sms.controller;

import com.jesther.sms.dto.StudentDto;
import com.jesther.sms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    // handler method to handle new student request
    @GetMapping("/new")
    public String createStudent(Model model) {
        // student model object to store student form data
        StudentDto studentDto = new StudentDto();
        model.addAttribute("student", studentDto);

        return "create_student";
    }

    // handler method to handle save student form submit request
    @PostMapping
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", student);
            return "create_student";
        }

        studentService.createStudent(student);

        return "redirect:/students";
    }

    // handler method to handle edit student request

    @GetMapping("/{studentId}/edit")
    public String editStudent(@PathVariable("studentId") Long id, Model model) {
        StudentDto student = studentService.getStudentById(id);
        model.addAttribute("student", student);

        return "edit_student";
    }

    // handler method to handle edit student form request

    @PostMapping("{studentId}")
    public String updateStudent(@PathVariable Long studentId,
                                @ModelAttribute("student") StudentDto studentDto,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", studentDto);
            return "edit_student";
        }

        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);

        return "redirect:/students";
    }

    // Handler method to handle delete student request
    @GetMapping("/{studentId}/delete")
    public String deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);

        return "redirect:/students";
    }
}
